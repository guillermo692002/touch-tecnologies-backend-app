package biz.touchtechnologies.backendchallanege.application.service.impl;

import biz.touchtechnologies.backendchallanege.application.Product;
import biz.touchtechnologies.backendchallanege.application.dto.SaveProduct;
import biz.touchtechnologies.backendchallanege.application.repository.ProductRepository;
import biz.touchtechnologies.backendchallanege.application.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findAll(int limit) {
        return productRepository.findAll(limit);
    }

    @Override
    public Optional<Product> findOneById(long id) {
        return productRepository.findOneById(id);
    }

    @Override
    public Product save(SaveProduct saveDto) {
        return productRepository.save(saveDto);
    }

    @Override
    public Product update(long id, SaveProduct saveDto) {
        return productRepository.update(id, saveDto);
    }

    @Override
    public Product deleteOneById(long id) {
        return productRepository.deleteOneById(id);
    }
}
