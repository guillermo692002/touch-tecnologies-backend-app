package biz.touchtechnologies.backendchallanege.config;

import biz.touchtechnologies.backendchallanege.application.dto.SaveOrder;
import biz.touchtechnologies.backendchallanege.application.dto.SaveOrderDetail;
import biz.touchtechnologies.backendchallanege.application.dto.SaveProduct;
import biz.touchtechnologies.backendchallanege.application.repository.OrderRepository;
import biz.touchtechnologies.backendchallanege.application.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class OrderTest {

    private final OrderRepository orderRepository;

    public OrderTest(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Bean("orderTesttwo")
    CommandLineRunner productTest(){
        return args -> {
            System.out.println("===================== ORDER TEST ======================");

            System.out.println("===== ORDER FIND ALL =====");
            orderRepository.findAll()
                    .forEach(System.out::println);



            System.out.println("===== ORDER FIND ONE =====");
            System.out.println("=> " + orderRepository.findOneById(1L));



            System.out.println("===== ORDER DELETE ONE =====");
            System.out.println("DELETE => " + orderRepository.deleteOneById(1L));


            System.out.println("===== ORDER FIND ALL WITH LIMIT =====");
            orderRepository.findAll(5)
                    .forEach(System.out::println);




            System.out.println("===== ORDER SAVE =====");
            SaveOrder saveOrder = new SaveOrder();
            saveOrder.setUserId(2);

            List<SaveOrderDetail> details = new ArrayList<>();
            SaveOrderDetail orderDetail1 = new SaveOrderDetail();
            orderDetail1.setProductId(1);
            orderDetail1.setQuantity(5);
            details.add(orderDetail1);

            SaveOrderDetail orderDetail2 = new SaveOrderDetail();
            orderDetail2.setProductId(2);
            orderDetail2.setQuantity(9);
            details.add(orderDetail2);

            saveOrder.setProducts(details);

            System.out.println("==> ORDER SAVE " + orderRepository.save(saveOrder));



            System.out.println("===== ORDER UPDATE =====");
            SaveOrder updateOrder = new SaveOrder();
            updateOrder.setUserId(2);

            List<SaveOrderDetail> details2 = new ArrayList<>();
            SaveOrderDetail orderDetail3 = new SaveOrderDetail();
            orderDetail3.setProductId(1);
            orderDetail3.setQuantity(5);
            details2.add(orderDetail3);

            SaveOrderDetail orderDetail4 = new SaveOrderDetail();
            orderDetail4.setProductId(2);
            orderDetail4.setQuantity(9);
            details2.add(orderDetail4);

            updateOrder.setProducts(details2);

            System.out.println("==> ORDER UPDATE " + orderRepository.update(1L, updateOrder));

        };
    }
}
