package biz.touchtechnologies.backendchallanege.persistence.integration.fakeapi;

import biz.touchtechnologies.backendchallanege.application.Product;
import biz.touchtechnologies.backendchallanege.application.dto.SaveProduct;
import biz.touchtechnologies.backendchallanege.persistence.integration.fakeapi.mapper.ProductMapper;
import biz.touchtechnologies.backendchallanege.persistence.integration.fakeapi.request.SaveProductApi;
import biz.touchtechnologies.backendchallanege.persistence.integration.fakeapi.response.GetProduct;
import biz.touchtechnologies.backendchallanege.util.HttpClientAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class ProductRepository implements biz.touchtechnologies.backendchallanege.application.repository.ProductRepository {

    private final RestTemplate restTemplate;
    private final HttpClientAdapter clientHttp;

    @Value("${external.api.shopping-cart.products.url}")
    private String PRODUCTS_URL;

    public ProductRepository(RestTemplate restTemplate, HttpClientAdapter clientHttp) {
        this.restTemplate = restTemplate;
        this.clientHttp = clientHttp;
    }

    @Override
    public List<Product> findAll() {

        return this.findAll(0);
    }

    @Override
    public List<Product> findAll(int limit) {
        GetProduct[] response = null;

        if(limit > 0){
            Map<String, String> queryParams = new HashMap<>();
            queryParams.put("limit", Integer.toString(limit));
            response = clientHttp.doGet(PRODUCTS_URL, queryParams, GetProduct[].class);
        }else{
            response = clientHttp.doGet(PRODUCTS_URL, null, GetProduct[].class);
        }

        List<Product> products = Arrays.stream(response)
                .map(ProductMapper::toDomainDTO)
                .collect(Collectors.toList());

        return products;
    }

    @Override
    public Optional<Product> findOneById(long id) {
        final String finalUrl = PRODUCTS_URL.concat("/").concat(Long.toString(id));


        System.out.println("------> " + GetProduct.class);
        GetProduct response = clientHttp.doGet(finalUrl, null, GetProduct.class);

        return Optional.ofNullable(response)
                .map(ProductMapper::toDomainDTO);
    }

    @Override
    public Product save(SaveProduct saveDto) {
        SaveProductApi saveProductApiDTO = ProductMapper.toSaveProductApiDTO(saveDto);
        GetProduct productSaved = clientHttp.doPost(PRODUCTS_URL, saveProductApiDTO, GetProduct.class);

        return ProductMapper.toDomainDTO(productSaved);
    }

    @Override
    public Product update(long id, SaveProduct saveDto) {
        SaveProductApi saveProductApiDTO = ProductMapper.toSaveProductApiDTO(saveDto);

        String finalUrl = PRODUCTS_URL.concat("/").concat(Long.toString(id));
        GetProduct productSaved = clientHttp.doPut(finalUrl, saveProductApiDTO, GetProduct.class);

        return ProductMapper.toDomainDTO(productSaved);
    }

    @Override
    public Product deleteOneById(long id) {
        String finalUrl = PRODUCTS_URL.concat("/").concat(Long.toString(id));
        GetProduct productSaved = clientHttp.doDelete(finalUrl, GetProduct.class);
        return ProductMapper.toDomainDTO(productSaved);
    }

}
