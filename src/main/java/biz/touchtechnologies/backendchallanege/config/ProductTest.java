package biz.touchtechnologies.backendchallanege.config;

import biz.touchtechnologies.backendchallanege.application.Product;
import biz.touchtechnologies.backendchallanege.application.dto.SaveProduct;
import biz.touchtechnologies.backendchallanege.application.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class ProductTest {

    private final ProductRepository productRepository;

    public ProductTest(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Bean("productTesttwo")
    CommandLineRunner productTest(){
        return args -> {
            System.out.println("===================== PRODUCT TEST ======================");

            System.out.println("===== FIND ALL =====");
            productRepository.findAll()
                    .forEach(System.out::println);



            System.out.println("===== FIND ONE =====");
            System.out.println("=> " + productRepository.findOneById(1L));



            System.out.println("===== DELETE ONE =====");
            System.out.println("DELETE => " + productRepository.deleteOneById(1L));


            System.out.println("===== FIND ALL WITH LIMIT =====");
            productRepository.findAll(5)
                    .forEach(System.out::println);




            System.out.println("===== SAVE =====");
            SaveProduct saveProduct = new SaveProduct();
            saveProduct.setCategory("Hola XD");
            saveProduct.setDescription(null);
            saveProduct.setImage("https:/dominio.com/image/nombre.jpg");
            saveProduct.setPrice(BigDecimal.valueOf(9.99));
            saveProduct.setTitle("Título 1");
            saveProduct.setCount(14);
            saveProduct.setRate(25.2);

            System.out.println("==> SAVE " + productRepository.save(saveProduct));





            System.out.println("===== UPDATE =====");
            SaveProduct updateProduct = new SaveProduct();
            updateProduct.setCategory("Hola XD");
            updateProduct.setDescription(null);
            updateProduct.setImage("https:/dominio.com/image/nombre.jpg");
            updateProduct.setPrice(BigDecimal.valueOf(9.99));
            updateProduct.setTitle("Título 1");
            updateProduct.setCount(14);
            updateProduct.setRate(25.2);

            System.out.println("==> UPDATE " + productRepository.update(1L, saveProduct));

        };
    }
}
