package biz.touchtechnologies.backendchallanege.web;

import biz.touchtechnologies.backendchallanege.application.Product;
import biz.touchtechnologies.backendchallanege.application.dto.ResponseDto;
import biz.touchtechnologies.backendchallanege.application.dto.SaveProduct;
import biz.touchtechnologies.backendchallanege.application.service.ProductService;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    Logger log = LogManager.getLogger(ProductController.class);

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<ResponseDto> findAll(@RequestParam(required = false) Integer limit ){
        List<Product> products = null;

        System.out.println("LIMIT = " + limit);
        if(limit == null){
            products = productService.findAll();
        }else{
            products = productService.findAll(limit);
        }

        ResponseDto body = new ResponseDto(products);
        return ResponseEntity.ok(body);

    }

    @GetMapping("/{productId}")
    public ResponseEntity<ResponseDto> findOneById(@PathVariable Long productId ){
        return productService.findOneById(productId)
                .map(product -> ResponseEntity.ok(new ResponseDto(product)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(new ResponseDto(null, HttpStatus.NOT_FOUND.value())) );
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<ResponseDto> deleteOneById(@PathVariable Long productId ){
        Product product = productService.deleteOneById(productId);
        return Optional.ofNullable(product).map(p -> ResponseEntity.ok(new ResponseDto(p)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(new ResponseDto(null, HttpStatus.NOT_FOUND.value())) );
    }

    @PostMapping
    public ResponseEntity<ResponseDto> save(@Valid @RequestBody SaveProduct saveDTO){
        Product product = productService.save(saveDTO);
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(new ResponseDto(product, HttpStatus.CREATED.value()));
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ResponseDto> update(@PathVariable Long productId, @Valid @RequestBody SaveProduct saveDTO){
        Product product = productService.update(productId, saveDTO);
        return ResponseEntity.ok(new ResponseDto(product));
    }
}
