package biz.touchtechnologies.backendchallanege.application.repository;

import biz.touchtechnologies.backendchallanege.application.OrderDetail;
import biz.touchtechnologies.backendchallanege.application.dto.SaveOrderDetail;

import java.util.List;
import java.util.Optional;

public interface OrderDetailRepository {

    List<OrderDetail> findAll();
    List<OrderDetail> findAll(int limit);

    Optional<OrderDetail> findOneById(long id);

    OrderDetail save(SaveOrderDetail saveDto);

    OrderDetail update(long Id, SaveOrderDetail saveDto);

    OrderDetail deleteOneById(long id);

}
