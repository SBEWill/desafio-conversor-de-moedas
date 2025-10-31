package utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class TaxService {
    private final String apiKey = "11ba23a3d20fe597f293c4fe";

    public BigDecimal obterTaxa(String fromCode, String toCode) throws IOException, InterruptedException {

        String from = fromCode.toLowerCase();
        String to = toCode.toLowerCase();

        String busca = String.format("https://v6.exchangerate-api.com/v6/%s/pair/%s/%s", apiKey, from, to);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(busca))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String json = response.body();

        // Conversão para JSON

        JsonElement elemento = JsonParser.parseString(json);
        JsonObject objectRoot = elemento.getAsJsonObject();
// Accessando o JsonObject

        BigDecimal taxa = objectRoot.get("conversion_rate").getAsBigDecimal();

        return taxa;



    }

}
