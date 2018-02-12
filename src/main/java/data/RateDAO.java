package data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class RateDAO {
    public static final String FILE_NAME = "currency.data";
    public double getRate(Currency firstCurrency, Currency secondCurrency) throws IOException {
        ApiResponse apiResponse = null;
        try {
            apiResponse = getFromFile(firstCurrency, secondCurrency);
        } catch (IOException e) {
            apiResponse = getFromHttp(firstCurrency, secondCurrency);
        }
        return apiResponse.getRates().getRate();
    }

    private ApiResponse getFromHttp(Currency firstCurrency, Currency secondCurrency) throws IOException {
        HttpURLConnection urlConnection = null;
        String json = null;
        ApiResponse apiResponse = null;
        String urlString = "http://api.fixer.io/latest?base=" + firstCurrency.toString() + "&symbols=" + secondCurrency.toString();
        URL url = new URL(urlString);

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            try (BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))) {
                json = in.readLine();
            }
        } catch (Exception e) {
            throw new IOException("Ошибка при обращении к сети");
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        apiResponse = getApiResponceFromJson(json);
        return apiResponse;
    }

    private ApiResponse getFromFile(Currency firstCurrency, Currency secondCurrency) throws IOException {
        Path path = Paths.get(RateDAO.FILE_NAME);
        ApiResponse apiResponse;
        if (Files.exists(path)) {
            try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
                String line;
                while ((line=reader.readLine())!=null) {
                    apiResponse = getApiResponceFromJson(line);
                    if ((Currency.valueOf(apiResponse.getBase()).equals(firstCurrency))&&
                    Currency.valueOf(apiResponse.getRates().getName()).equals(secondCurrency)) {
                        return apiResponse;
                    }
                }
            } catch (Exception e) {
                throw new IOException("Записи в базе нет");
            }
        } else {
            throw new IOException("Файл не существует");
        }
        throw new IOException("Записи в базе нет");
    }

    private boolean saveToFile(String json) throws IOException {
        Path path = Paths.get(RateDAO.FILE_NAME);
        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
            writer.write(json);
            writer.newLine();
        }
        return true;
    }

    private ApiResponse getApiResponceFromJson (String json) throws IOException {
        ApiResponse apiResponse = null;
        if (json != null) {
            try {
                Gson builder = new GsonBuilder()
                        .registerTypeAdapter(RateObject.class, new RatesDeserializer())
                        .create();
                apiResponse = builder.fromJson(json, ApiResponse.class);
                try {
                    saveToFile(json);
                } catch (Exception e) {
                }
            } catch (Exception e) {
                throw new IOException("Ошибка считанных данных из сети");
            }
        }
        return apiResponse;
    }
}
