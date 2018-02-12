package data;

public class ApiResponse {
    private String base;
    private String date;
    private RateObject rates;

    public ApiResponse(String base, String date, RateObject rates) {
        this.base = base;
        this.date = date;
        this.rates = rates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ApiResponse that = (ApiResponse) o;

        if (base != null ? !base.equals(that.base) : that.base != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        return rates != null ? rates.equals(that.rates) : that.rates == null;
    }

    @Override
    public int hashCode() {
        int result = base != null ? base.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (rates != null ? rates.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "base='" + base + '\'' +
                ", date='" + date + '\'' +
                ", rates=" + rates +
                '}';
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public RateObject getRates() {
        return rates;
    }

    public void setRates(RateObject rates) {
        this.rates = rates;
    }
}
