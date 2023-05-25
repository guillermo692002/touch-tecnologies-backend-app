package biz.touchtechnologies.backendchallanege.application.service.impl;

import biz.touchtechnologies.backendchallanege.application.Order;
import biz.touchtechnologies.backendchallanege.application.OrderDetail;
import biz.touchtechnologies.backendchallanege.application.dto.SaveOrder;
import biz.touchtechnologies.backendchallanege.application.dto.SaveOrderDetail;
import biz.touchtechnologies.backendchallanege.application.repository.OrderDetailRepository;
import biz.touchtechnologies.backendchallanege.application.service.OrderDetailService;
import biz.touchtechnologies.backendchallanege.application.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;

    public OrderDetailServiceImpl(OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }

    @Override
    public List<OrderDetail> findAll() {
        return orderDetailRepository.findAll();
    }

    @Override
    public List<OrderDetail> findAll(int limit) {
        return orderDetailRepository.findAll(limit);
    }

    @Override
    public Optional<OrderDetail> findOneById(long id) {
        return orderDetailRepository.findOneById(id);
    }

    @Override
    public OrderDetail save(SaveOrderDetail saveDto) {
        return orderDetailRepository.save(saveDto);
    }

    @Override
    public OrderDetail update(long id, SaveOrderDetail saveDto) {
        return orderDetailRepository.update(id, saveDto);
    }

    @Override
    public OrderDetail deleteOneById(long id) {
        return orderDetailRepository.deleteOneById(id);
    }
}
