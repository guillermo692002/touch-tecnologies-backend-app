package biz.touchtechnologies.backendchallanege.web;

import biz.touchtechnologies.backendchallanege.application.Order;
import biz.touchtechnologies.backendchallanege.application.Product;
import biz.touchtechnologies.backendchallanege.application.dto.ResponseDto;
import biz.touchtechnologies.backendchallanege.application.dto.SaveOrder;
import biz.touchtechnologies.backendchallanege.application.dto.SaveProduct;
import biz.touchtechnologies.backendchallanege.application.repository.ProductRepository;
import biz.touchtechnologies.backendchallanege.application.service.OrderService;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {

    Logger log = LogManager.getLogger(OrderController.class);

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<ResponseDto> findAll(@RequestParam(required = false) Integer limit ){
        List<Order> orders = null;
        if(limit == null){
            orders = orderService.findAll();
        }else{
            orders = orderService.findAll(limit);
        }

        ResponseDto body = new ResponseDto(orders);
        return ResponseEntity.ok(body);

    }

    @GetMapping("/{orderId}")
    public ResponseEntity<ResponseDto> findOneById(@PathVariable Long orderId ){
        return orderService.findOneById(orderId)
                .map(product -> ResponseEntity.ok(new ResponseDto(product)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(new ResponseDto(null, HttpStatus.NOT_FOUND.value())) );
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<ResponseDto> deleteOneById(@PathVariable Long orderId ){
        Order order = orderService.deleteOneById(orderId);
        return Optional.ofNullable(order).map(c -> ResponseEntity.ok(new ResponseDto(c)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(new ResponseDto(null, HttpStatus.NOT_FOUND.value())) );
    }

    @PostMapping
    public ResponseEntity<ResponseDto> save(@Valid @RequestBody SaveOrder saveDTO){
        Order order = orderService.save(saveDTO);
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(new ResponseDto(order, HttpStatus.CREATED.value()));
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<ResponseDto> update(@PathVariable Long orderId, @Valid @RequestBody SaveOrder saveDTO){
        Order order = orderService.update(orderId, saveDTO);
        return ResponseEntity.ok(new ResponseDto(order));
    }
}
