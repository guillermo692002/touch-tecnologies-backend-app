package biz.touchtechnologies.backendchallanege.persistence.integration.fakeapi.mapper;

import biz.touchtechnologies.backendchallanege.application.Order;
import biz.touchtechnologies.backendchallanege.application.OrderDetail;
import biz.touchtechnologies.backendchallanege.application.dto.SaveOrder;
import biz.touchtechnologies.backendchallanege.application.dto.SaveOrderDetail;
import biz.touchtechnologies.backendchallanege.persistence.integration.fakeapi.request.SaveOrderApi;
import biz.touchtechnologies.backendchallanege.persistence.integration.fakeapi.response.GetOrder;
import biz.touchtechnologies.backendchallanege.persistence.integration.fakeapi.response.GetOrderDetail;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


public class OrderDetailMapper {

    public static OrderDetail toDomainDTO(GetOrderDetail apiDTO){

        if(apiDTO == null) return null;

        OrderDetail order = new OrderDetail(
                0,
                apiDTO.getProductId(),
                apiDTO.getQuantity(),
                null,
                null
        );
        return order;

    }

    public static List<OrderDetail> toDomainDTOList(List<GetOrderDetail> apiDTOList){
        if(apiDTOList == null) return null;

        return apiDTOList.stream().map(OrderDetailMapper::toDomainDTO)
                .collect(Collectors.toList());
    }

    public static OrderDetail toDomainDTO(SaveOrderDetail saveDTO){
        if(saveDTO == null) return null;

        OrderDetail detail = new OrderDetail();
        detail.setProductId(saveDTO.getProductId());
        detail.setQuantity(saveDTO.getQuantity());
        return detail;

    }

}
