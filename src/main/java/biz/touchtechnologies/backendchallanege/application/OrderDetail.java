package biz.touchtechnologies.backendchallanege.application;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDetail implements Serializable {
    private long id;

    private int productId;
    private int quantity;

    private BigDecimal unitPrice;

    private BigDecimal total;

    public OrderDetail() {
    }

    public OrderDetail(long id, int productId, int quantity, BigDecimal unitPrice, BigDecimal total) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.total = total;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderDetail that = (OrderDetail) o;

        if (getId() != that.getId()) return false;
        if (getProductId() != that.getProductId()) return false;
        if (getQuantity() != that.getQuantity()) return false;
        if (getUnitPrice() != null ? !getUnitPrice().equals(that.getUnitPrice()) : that.getUnitPrice() != null)
            return false;
        return getTotal() != null ? getTotal().equals(that.getTotal()) : that.getTotal() == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + getProductId();
        result = 31 * result + getQuantity();
        result = 31 * result + (getUnitPrice() != null ? getUnitPrice().hashCode() : 0);
        result = 31 * result + (getTotal() != null ? getTotal().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "id=" + id +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                ", total=" + total +
                '}';
    }
}
