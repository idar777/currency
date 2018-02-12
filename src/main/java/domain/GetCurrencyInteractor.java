package domain;

import data.Currency;
import data.CurrencyDAO;

import java.io.IOException;

public class GetCurrencyInteractor {
    CurrencyDAO currencyDAO = new CurrencyDAO();
    public Currency getCurrency() throws IOException{
        return currencyDAO.getCurrency();
    }
}
