package biz.touchtechnologies.backendchallanege.application.repository;

import biz.touchtechnologies.backendchallanege.application.Order;
import biz.touchtechnologies.backendchallanege.application.dto.SaveOrder;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {

    List<Order> findAll();
    List<Order> findAll(int limit);

    Optional<Order> findOneById(long id);

    Order save(SaveOrder saveDto);

    Order update(long id, SaveOrder saveDto);

    Order deleteOneById(long id);

}
