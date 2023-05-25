package biz.touchtechnologies.backendchallanege.persistence.integration.fakeapi;

import biz.touchtechnologies.backendchallanege.application.Order;
import biz.touchtechnologies.backendchallanege.application.OrderDetail;
import biz.touchtechnologies.backendchallanege.application.Product;
import biz.touchtechnologies.backendchallanege.application.dto.SaveOrderDetail;
import biz.touchtechnologies.backendchallanege.application.exception.ExternalApiException;
import biz.touchtechnologies.backendchallanege.application.repository.ProductRepository;
import biz.touchtechnologies.backendchallanege.persistence.integration.fakeapi.mapper.OrderDetailMapper;
import biz.touchtechnologies.backendchallanege.util.HttpClientAdapter;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class OrderDetailRepository implements biz.touchtechnologies.backendchallanege.application.repository.OrderDetailRepository {

    private final RestTemplate restTemplate;
    private final HttpClientAdapter clientHttp;
    private final OrderRepository orderRepository;

    private final ProductRepository productRepository;

    @Value("${external.api.shopping-cart.carts.url}")
    private String CARTS_URL;

    private final static List<OrderDetail> details = new ArrayList<>();

    public OrderDetailRepository(RestTemplate restTemplate, HttpClientAdapter clientHttp, OrderRepository orderRepository, ProductRepository productRepository) {
        this.restTemplate = restTemplate;
        this.clientHttp = clientHttp;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }


    @PostConstruct
    public void populateDetailsList(){
        /**
         * THIS CODE IS A MESS BUT THE GOAL IS HAVE A LIST OF ORDER DETAIL TO MIRROR A DATABASE TABLE CALLED ORDER_DETAIL
         * I AM REARLY EVER WRITE DOWN CODE LIKE THIS
         */

        List<Order> orders = orderRepository.findAll();

//        details.addAll(
//        orders.stream()
//        .flatMap( order -> {
//            List<OrderDetail> orderDetailsList = new ArrayList<>();
//            orderDetailsList = order.getDetails()
//            .stream()
//            .map(detail -> {
//                OrderDetail orderDetail = new OrderDetail();
//                orderDetail.setId(order.getId());
//
//                orderDetail.setQuantity(detail.getQuantity());
//                orderDetail.setProductId(detail.getProductId());
//
//                BigDecimal unitPrice = productRepository.findOneById(detail.getId())
//                .map(Product::getPrice)
//                .orElseGet(() -> {return BigDecimal.ZERO;});
//
//                orderDetail.setUnitPrice(unitPrice);
//                orderDetail.setTotal(unitPrice.multiply(BigDecimal.valueOf(orderDetail.getQuantity())));
//
//                return orderDetail;
//            })
//            .collect(Collectors.toList());
//
//            return orderDetailsList.stream();
//        })
//        .collect(Collectors.toList())
//        );

    }

    @Override
    public List<OrderDetail> findAll() {
        return details;
    }

    @Override
    public List<OrderDetail> findAll(int limit) {
        return details.subList(0, limit + 1);
    }

    @Override
    public Optional<OrderDetail> findOneById(long id) {
        return details.stream().filter(detail -> detail.getId() == id)
                .findFirst();
    }

    @Override
    public OrderDetail save(SaveOrderDetail saveDto) {
        OrderDetail orderDetail = OrderDetailMapper.toDomainDTO(saveDto);
        details.add(OrderDetailMapper.toDomainDTO(saveDto));

        return orderDetail;
    }

    @Override
    public OrderDetail update(long id, SaveOrderDetail saveDto) {

        boolean borrado = details.removeIf(item -> item.getId() == id);

        if(!borrado){
            throw new ExternalApiException("THE REGISTER WASN'T DELETED: ORDER DETAIL ID " + id);
        }

        OrderDetail orderDetail = OrderDetailMapper.toDomainDTO(saveDto);
        orderDetail.setId(id);
        details.add(orderDetail);

        return orderDetail;
    }

    @Override
    public OrderDetail deleteOneById(long id) {
        return details.stream().filter(item -> item.getId() == id)
                .findFirst()
                .orElseGet(() -> null);
    }

}
