package biz.touchtechnologies.backendchallanege.persistence.integration.fakeapi.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GetOrderDetail implements Serializable {
    private int productId;
    private int quantity;

    public GetOrderDetail() {
    }

    public GetOrderDetail(int productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GetOrderDetail ordenItem = (GetOrderDetail) o;

        if (getProductId() != ordenItem.getProductId()) return false;
        return getQuantity() == ordenItem.getQuantity();
    }

    @Override
    public int hashCode() {
        int result = getProductId();
        result = 31 * result + getQuantity();
        return result;
    }

    @Override
    public String toString() {
        return "OrdenItem{" +
                "productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }
}
