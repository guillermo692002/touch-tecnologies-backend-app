package biz.touchtechnologies.backendchallanege.persistence.integration.fakeapi.util;

public class Rating {

    private double rate;
    private int count;

    public Rating() {
    }

    public Rating(double rate, int count) {
        this.rate = rate;
        this.count = count;
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

        Rating rating = (Rating) o;

        if (Double.compare(rating.getRate(), getRate()) != 0) return false;
        return getCount() == rating.getCount();
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(getRate());
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + getCount();
        return result;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "rate=" + rate +
                ", count=" + count +
                '}';
    }
}
