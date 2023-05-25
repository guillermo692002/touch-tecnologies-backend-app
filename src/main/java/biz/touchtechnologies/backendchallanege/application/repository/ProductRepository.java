package biz.touchtechnologies.backendchallanege.application.repository;

import biz.touchtechnologies.backendchallanege.application.Product;
import biz.touchtechnologies.backendchallanege.application.dto.SaveProduct;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    List<Product> findAll();
    List<Product> findAll(int limit);
    Optional<Product> findOneById(long id);
    Product save(SaveProduct saveDto);
    Product update(long id, SaveProduct saveDto);
    Product deleteOneById(long id);

}
