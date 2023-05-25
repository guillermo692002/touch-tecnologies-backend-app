package biz.touchtechnologies.backendchallanege.persistence.integration.fakeapi.request;

import biz.touchtechnologies.backendchallanege.persistence.integration.fakeapi.response.GetOrderDetail;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class SaveOrderApi {
    private int userId;
    private LocalDateTime date;
    private List<GetOrderDetail> products;

    public SaveOrderApi() {
    }

    public SaveOrderApi(int userId, LocalDateTime date, List<GetOrderDetail> products) {
        this.userId = userId;
        this.date = date;
        this.products = products;
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

        SaveOrderApi that = (SaveOrderApi) o;

        if (getUserId() != that.getUserId()) return false;
        if (getDate() != null ? !getDate().equals(that.getDate()) : that.getDate() != null) return false;
        return getProducts() != null ? getProducts().equals(that.getProducts()) : that.getProducts() == null;
    }

    @Override
    public int hashCode() {
        int result = getUserId();
        result = 31 * result + (getDate() != null ? getDate().hashCode() : 0);
        result = 31 * result + (getProducts() != null ? getProducts().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SaveOrderApi{" +
                ", userId=" + userId +
                ", date=" + date +
                ", products=" + products +
                '}';
    }
}
