package biz.touchtechnologies.backendchallanege.persistence.integration.fakeapi;

import biz.touchtechnologies.backendchallanege.application.Order;
import biz.touchtechnologies.backendchallanege.application.OrderDetail;
import biz.touchtechnologies.backendchallanege.application.Product;
import biz.touchtechnologies.backendchallanege.application.dto.SaveOrder;
import biz.touchtechnologies.backendchallanege.application.repository.ProductRepository;
import biz.touchtechnologies.backendchallanege.persistence.integration.fakeapi.mapper.OrderMapper;
import biz.touchtechnologies.backendchallanege.persistence.integration.fakeapi.request.SaveOrderApi;
import biz.touchtechnologies.backendchallanege.persistence.integration.fakeapi.response.GetOrder;
import biz.touchtechnologies.backendchallanege.util.HttpClientAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class OrderRepository implements biz.touchtechnologies.backendchallanege.application.repository.OrderRepository {

    private final RestTemplate restTemplate;
    private final HttpClientAdapter clientHttp;

    private final ProductRepository productRepository;

    @Value("${external.api.shopping-cart.carts.url}")
    private String CARTS_URL;

    public OrderRepository(RestTemplate restTemplate, HttpClientAdapter clientHttp, ProductRepository productRepository) {
        this.restTemplate = restTemplate;
        this.clientHttp = clientHttp;
        this.productRepository = productRepository;
    }

    @Override
    public List<Order> findAll() {
        return this.findAll(0);
    }

    @Override
    public List<Order> findAll(int limit) {
        GetOrder[] response = null;

        if(limit > 0){
            Map<String, String> queryParams = new HashMap<>();
            queryParams.put("limit", Integer.toString(limit));
            response = clientHttp.doGet(CARTS_URL, queryParams, GetOrder[].class);
        }else{
            response = clientHttp.doGet(CARTS_URL, null, GetOrder[].class);
        }

        List<Order> orders = Arrays.stream(response)
                .map(OrderMapper::toDomainDTO)
                .collect(Collectors.toList());
        return orders;
    }

    @Override
    public Optional<Order> findOneById(long id) {
        final String finalUrl = CARTS_URL.concat("/").concat(Long.toString(id));
        GetOrder response = clientHttp.doGet(finalUrl, null, GetOrder.class);

        Optional<Order> order = Optional.ofNullable(response)
                .map(OrderMapper::toDomainDTO);
        return order;
    }

    @Override
    public Order save(SaveOrder saveDto) {
        SaveOrderApi saveProductApiDTO = OrderMapper.toSaveProductApiDTO(saveDto);
        GetOrder productSaved = clientHttp.doPost(CARTS_URL, saveProductApiDTO, GetOrder.class);

        Order order = OrderMapper.toDomainDTO(productSaved);
        return order;
    }

    @Override
    public Order update(long id, SaveOrder saveDto) {
        SaveOrderApi saveProductApiDTO = OrderMapper.toSaveProductApiDTO(saveDto);

        String finalUrl = CARTS_URL.concat("/").concat(Long.toString(id));
        GetOrder productSaved = clientHttp.doPut(finalUrl, saveProductApiDTO, GetOrder.class);

        Order order = OrderMapper.toDomainDTO(productSaved);
        return order;
    }

    @Override
    public Order deleteOneById(long id) {
        String finalUrl = CARTS_URL.concat("/").concat(Long.toString(id));
        GetOrder productSaved = clientHttp.doDelete(finalUrl, GetOrder.class);
        Order order = OrderMapper.toDomainDTO(productSaved);
        return order;
    }

}
