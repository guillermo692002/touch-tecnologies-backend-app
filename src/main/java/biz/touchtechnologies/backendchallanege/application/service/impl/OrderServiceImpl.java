package biz.touchtechnologies.backendchallanege.application.service.impl;

import biz.touchtechnologies.backendchallanege.application.Order;
import biz.touchtechnologies.backendchallanege.application.OrderDetail;
import biz.touchtechnologies.backendchallanege.application.Product;
import biz.touchtechnologies.backendchallanege.application.dto.SaveOrder;
import biz.touchtechnologies.backendchallanege.application.repository.OrderRepository;
import biz.touchtechnologies.backendchallanege.application.repository.ProductRepository;
import biz.touchtechnologies.backendchallanege.application.service.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger log = LogManager.getLogger(OrderServiceImpl.class);

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderServiceImpl(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    /**
     * Complete each entity in a Order List
     * Complete the filed: total
     * @param details
     */
    private void completeOrderDetail(List<OrderDetail> details ){
        if(details == null) return;

        for(OrderDetail detail : details){

            BigDecimal unitPrice = productRepository.findOneById(detail.getProductId())
                    .map(Product::getPrice)
                    .orElseGet(() -> BigDecimal.ZERO);

            detail.setUnitPrice(unitPrice);

            if(unitPrice != null  && !unitPrice.equals(BigDecimal.ZERO)){
                BigDecimal total = unitPrice.multiply(BigDecimal.valueOf(detail.getQuantity()));
                detail.setTotal(total);
            }
        }

    }

    /**
     * Complete each Order POJO
     * Complete the filed: total
     * @param order
     */
    private void completeOrder(Order order ){

        if(order == null) return;

        completeOrderDetail(order.getDetails());

        BigDecimal bigTotal = order.getDetails().stream().map(OrderDetail::getTotal)
                .reduce(BigDecimal.ZERO, ( detail, total ) -> {
            return total.add(detail);
        });

        order.setTotal(bigTotal);

    }



    @Override
    public List<Order> findAll() {
        List<Order> orders = orderRepository.findAll();

        orders.forEach(order -> completeOrder(order));

        return orders;
    }

    @Override
    public List<Order> findAll(int limit) {
        List<Order> orders = orderRepository.findAll(limit);
        orders.forEach(order -> completeOrder(order));
        return orders;
    }

    @Override
    public Optional<Order> findOneById(long id) {
        Optional<Order> order = orderRepository.findOneById(id);
        if(order.isPresent()) completeOrder(order.get());
        return order;

    }

    @Override
    public Order save(SaveOrder saveDto) {

        Order order = orderRepository.save(saveDto);
        if(order != null) completeOrder(order);
        return order;
    }

    @Override
    public Order update(long id, SaveOrder saveDto) {
        Order order = orderRepository.update(id, saveDto);
        if(order != null) completeOrder(order);
        return order;
    }

    @Override
    public Order deleteOneById(long id) {
        Order order = orderRepository.deleteOneById(id);
        if(order != null) completeOrder(order);
        return order;
    }
}
