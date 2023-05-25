package biz.touchtechnologies.backendchallanege.application.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SaveOrder implements Serializable {

    @Min(value = 1)
    private int userId;

    @NotEmpty
    @Valid
    private List<SaveOrderDetail> products;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<SaveOrderDetail> getProducts() {
        return products;
    }

    public void setProducts(List<SaveOrderDetail> products) {
        this.products = products;
    }
}
