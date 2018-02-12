package presentation;

import data.Currency;
import domain.GetCurrencyInteractor;
import domain.GetRateInteractor;

import java.io.IOException;

public class MainPresenter {
    Main main = new Main();
    public void rateCurrency(){
        main.printPrompt("Enter from currency:");
        Currency firstCurrency = getCurrency();
        main.printPrompt("Enter to currency:");
        Currency secondCurrency = getCurrency();
        double rate = giveRateCurrency(firstCurrency, secondCurrency);
        main.printAnswer(firstCurrency.toString() + " => " + secondCurrency.toString() + " : " + rate);
    }

    public void showError(String err){
        main.showError(err);
    }

    private Currency getCurrency(){
        GetCurrencyInteractor getCurrencyInteractor = new GetCurrencyInteractor();
        try {
            return getCurrencyInteractor.getCurrency();
        } catch (IOException e) {
            showError(e.getMessage());
            System.exit(0);
        }
        return null;
    }

    private double giveRateCurrency(Currency firstCurrency, Currency secondCurrency) {
        GetRateInteractor getRateInteractor = new GetRateInteractor();
        if (firstCurrency==secondCurrency) {
            return 1;
        } else {
            try {
                return getRateInteractor.getRate(firstCurrency, secondCurrency);
            } catch (IOException err) {
                main.showError(err.getMessage());
                System.exit(0);
            }

        }
        return 0;
    }
}
