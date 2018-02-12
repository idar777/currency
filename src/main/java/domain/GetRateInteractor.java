package domain;

import data.Currency;
import data.RateDAO;

import java.io.IOException;

public class GetRateInteractor {
    RateDAO rateDAO = new RateDAO();
    public double getRate(Currency firstCurrency, Currency secondCurrency) throws IOException {
        return rateDAO.getRate(firstCurrency, secondCurrency);
    }
}
