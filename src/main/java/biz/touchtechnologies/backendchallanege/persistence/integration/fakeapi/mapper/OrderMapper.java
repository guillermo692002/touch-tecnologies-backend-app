package biz.touchtechnologies.backendchallanege.persistence.integration.fakeapi.mapper;

import biz.touchtechnologies.backendchallanege.application.Order;
import biz.touchtechnologies.backendchallanege.application.OrderDetail;
import biz.touchtechnologies.backendchallanege.application.Product;
import biz.touchtechnologies.backendchallanege.application.dto.SaveOrder;
import biz.touchtechnologies.backendchallanege.application.dto.SaveProduct;
import biz.touchtechnologies.backendchallanege.persistence.integration.fakeapi.request.SaveOrderApi;
import biz.touchtechnologies.backendchallanege.persistence.integration.fakeapi.request.SaveProductApi;
import biz.touchtechnologies.backendchallanege.persistence.integration.fakeapi.response.GetOrder;
import biz.touchtechnologies.backendchallanege.persistence.integration.fakeapi.response.GetOrderDetail;
import biz.touchtechnologies.backendchallanege.persistence.integration.fakeapi.response.GetProduct;
import biz.touchtechnologies.backendchallanege.persistence.integration.fakeapi.util.Rating;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class OrderMapper {

    private final static Logger log = LogManager.getLogger(OrderMapper.class);
    public static Order toDomainDTO(GetOrder apiDTO){

        if(apiDTO == null) return null;

        Order order = new Order(
                apiDTO.getId(),
                apiDTO.getDate(),
                apiDTO.getUserId(),
                null,
                null
        );

        if(apiDTO.getProducts() == null || apiDTO.getProducts().isEmpty() ) {
            log.info("Products list is null o is empty");
            return order;
        }

        List<OrderDetail> details = apiDTO.getProducts().stream().map(item -> {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setQuantity(item.getQuantity());
            orderDetail.setProductId(item.getProductId());

            return orderDetail;
        }).collect(Collectors.toList());

        order.setDetails(details);

        return order;

    }

    public static List<Order> toDomainDTOList(List<GetOrder> apiDTOList){
        if(apiDTOList == null) return null;

        return apiDTOList.stream().map(OrderMapper::toDomainDTO)
                .collect(Collectors.toList());
    }

    public static SaveOrderApi toSaveProductApiDTO(SaveOrder saveDTO){
        if(saveDTO == null) return null;

        SaveOrderApi saveOrderApi = new SaveOrderApi(
                saveDTO.getUserId(),
                LocalDateTime.now(),
                null
        );

        if(saveDTO.getProducts() == null || saveDTO.getProducts().isEmpty()) {
            return saveOrderApi;
        }

        List<GetOrderDetail> getOrderDetailList = saveDTO.getProducts()
                .stream()
                .map(orderDetail -> {
                    GetOrderDetail getOrderDetail = new GetOrderDetail();
                    getOrderDetail.setProductId(orderDetail.getProductId());
                    getOrderDetail.setQuantity(orderDetail.getQuantity());
                    return getOrderDetail;
                })
                .collect(Collectors.toList());

        saveOrderApi.setProducts(getOrderDetailList);

        return saveOrderApi;
    }

}
