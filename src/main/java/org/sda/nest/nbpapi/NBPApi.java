package org.sda.nest.nbpapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.sda.nest.nbpapi.exception.APICallException;
import org.sda.nest.nbpapi.model.CurrencyExchangeRates;
import org.sda.nest.nbpapi.model.CurrencyRate;
import org.sda.nest.nbpapi.model.UserAnswers;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class NBPApi {
    private final static String API_CALL_URL = "http://api.nbp.pl/api/exchangerates/rates/a/{currency_code}/{date_start}/{date_end}/";
    private final static int HTTP_OK = 200;
    private final static int HTTP_BAD_REQUEST = 400;

    private final HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<CurrencyRate> requestRates(UserAnswers answers) throws APICallException {
        HttpResponse<String> response;
        try {
            response = httpClient.send(
                    createRequestForCurrencyRatesBetween(answers),
                    HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == HTTP_OK) {
                CurrencyExchangeRates rates = objectMapper.readValue(response.body(), CurrencyExchangeRates.class);

                return rates.getRates();
            }
        } catch (IOException e) {
            throw new APICallException(e);
        } catch (InterruptedException e) {
            throw new APICallException(e);
        }
        throw new APICallException("API call error code: " +response.statusCode());
    }

    private HttpRequest createRequestForCurrencyRatesBetween(UserAnswers parameters) {
        return HttpRequest
                .newBuilder()
                .GET()
                //URI.create("http://api.nbp.pl/api/exchangerates/rates/a/"+parameters.getCurrency()+"/"+parameters.getDateStart()+"/"+parameters.getDateEnd()+"/")
                .uri(URI.create(
                        API_CALL_URL
                                .replaceAll("\\{currency_code\\}", parameters.getCurrency().getShortName())
                                .replaceAll("\\{date_start\\}", parameters.getDateStart().toString())
                                .replaceAll("\\{date_end\\}", parameters.getDateEnd().toString())))
                .build();
    }
}
