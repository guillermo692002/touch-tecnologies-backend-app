package biz.touchtechnologies.backendchallanege.persistence.integration.fakeapi.request;

import java.math.BigDecimal;

public class SaveProductApi {
    private String title;
    private BigDecimal price;
    private String category;
    private String description;
    private String image;
    private double rate;
    private int count;

    public SaveProductApi(String title, BigDecimal price, String category, String description, String image, double rate, int count) {
        this.title = title;
        this.price = price;
        this.category = category;
        this.description = description;
        this.image = image;
        this.rate = rate;
        this.count = count;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SaveProductApi that = (SaveProductApi) o;

        if (Double.compare(that.getRate(), getRate()) != 0) return false;
        if (getCount() != that.getCount()) return false;
        if (getTitle() != null ? !getTitle().equals(that.getTitle()) : that.getTitle() != null) return false;
        if (getPrice() != null ? !getPrice().equals(that.getPrice()) : that.getPrice() != null) return false;
        if (getCategory() != null ? !getCategory().equals(that.getCategory()) : that.getCategory() != null)
            return false;
        if (getDescription() != null ? !getDescription().equals(that.getDescription()) : that.getDescription() != null)
            return false;
        return getImage() != null ? getImage().equals(that.getImage()) : that.getImage() == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getTitle() != null ? getTitle().hashCode() : 0;
        result = 31 * result + (getPrice() != null ? getPrice().hashCode() : 0);
        result = 31 * result + (getCategory() != null ? getCategory().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getImage() != null ? getImage().hashCode() : 0);
        temp = Double.doubleToLongBits(getRate());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + getCount();
        return result;
    }

    @Override
    public String toString() {
        return "SaveProductApi{" +
                "title='" + title + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", rate=" + rate +
                ", count=" + count +
                '}';
    }
}
