package biz.touchtechnologies.backendchallanege.persistence.integration.fakeapi.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GetOrder implements Serializable {
    private long id;
    private int userId;
    private LocalDateTime date;
    private List<GetOrderDetail> products;

    public GetOrder() {
    }

    public GetOrder(int id, int userId, LocalDateTime date, List<GetOrderDetail> products) {
        this.id = id;
        this.userId = userId;
        this.date = date;
        this.products = products;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public List<GetOrderDetail> getProducts() {
        return products;
    }

    public void setProducts(List<GetOrderDetail> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GetOrder order = (GetOrder) o;

        if (getId() != order.getId()) return false;
        if (getUserId() != order.getUserId()) return false;
        if (getDate() != null ? !getDate().equals(order.getDate()) : order.getDate() != null) return false;
        return getProducts() != null ? getProducts().equals(order.getProducts()) : order.getProducts() == null;
    }

    @Override
    public int hashCode() {
        int result = (int) getId();
        result = 31 * result + getUserId();
        result = 31 * result + (getDate() != null ? getDate().hashCode() : 0);
        result = 31 * result + (getProducts() != null ? getProducts().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "GetOrder{" +
                "id=" + id +
                ", userId=" + userId +
                ", date=" + date +
                ", products=" + products +
                '}';
    }
}
