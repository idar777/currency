package data;

public class RateObject {
    private String name;
    private double rate;
    public RateObject(String name, double rate) {
        this.name = name;
        this.rate = rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RateObject that = (RateObject) o;

        if (Double.compare(that.rate, rate) != 0) return false;
        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name != null ? name.hashCode() : 0;
        temp = Double.doubleToLongBits(rate);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "RateObject{" +
                "name='" + name + '\'' +
                ", rate=" + rate +
                '}';
    }

    public String getName() {
        return name;
    }

    public double getRate() {
        return rate;
    }
}
