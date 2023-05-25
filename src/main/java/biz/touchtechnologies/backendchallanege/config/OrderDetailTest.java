package biz.touchtechnologies.backendchallanege.config;

import biz.touchtechnologies.backendchallanege.application.dto.SaveOrder;
import biz.touchtechnologies.backendchallanege.application.dto.SaveOrderDetail;
import biz.touchtechnologies.backendchallanege.application.repository.OrderDetailRepository;
import biz.touchtechnologies.backendchallanege.application.repository.OrderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

//@Configuration
public class OrderDetailTest {

    private final OrderDetailRepository orderDetailRepository;

    public OrderDetailTest(OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }

//    @Bean("orderDetailTesttwo")
    CommandLineRunner productTest(){
        return args -> {
            System.out.println("===================== ORDER DETAIL TEST ======================");

            System.out.println("===== ORDER DETAIL FIND ALL =====");
            orderDetailRepository.findAll()
                    .forEach(System.out::println);



            System.out.println("===== ORDER DETAIL FIND ONE =====");
            System.out.println("=> " + orderDetailRepository.findOneById(1L));



            System.out.println("===== ORDER DETAIL DELETE ONE =====");
            System.out.println("DELETE => " + orderDetailRepository.deleteOneById(1L));


            System.out.println("===== ORDER DETAIL FIND ALL WITH LIMIT =====");
            orderDetailRepository.findAll(5)
                    .forEach(System.out::println);




            System.out.println("===== ORDER DETAIL SAVE =====");

            SaveOrderDetail orderDetail1 = new SaveOrderDetail();
            orderDetail1.setProductId(1);
            orderDetail1.setQuantity(5);

            SaveOrderDetail orderDetail2 = new SaveOrderDetail();
            orderDetail2.setProductId(2);
            orderDetail2.setQuantity(9);

            System.out.println("==> ORDER DETAIL SAVE 1 " + orderDetailRepository.save(orderDetail1));
            System.out.println("==> ORDER DETAIL SAVE 2 " + orderDetailRepository.save(orderDetail2));



            System.out.println("===== ORDER DETAIL UPDATE =====");
            orderDetail1 = new SaveOrderDetail();
            orderDetail1.setProductId(1);
            orderDetail1.setQuantity(29);

            System.out.println("==> ORDER DETAIL UPDATE " + orderDetailRepository.update(1L, orderDetail1));

        };
    }
}
