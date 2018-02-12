package data;

import java.io.IOException;
import java.util.Scanner;

public class CurrencyDAO {
    public Currency getCurrency() throws IOException{
        Currency currency = null;
        Scanner sc = new Scanner(System.in);
        if (sc.hasNext()){
            try {
                currency = Currency.valueOf(sc.next());
            } catch (IllegalArgumentException errData) {
                throw new IOException("Введены неверные данные");
            }
        }
        return currency;
    }
}
