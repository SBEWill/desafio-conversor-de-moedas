package utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class TaxService {

    public double obterTaxa(int escolha) throws IOException, InterruptedException {

           String busca;

        if (escolha == 1) {

             busca = "https://v6.exchangerate-api.com/v6/11ba23a3d20fe597f293c4fe/pair/usd/brl";

        }else if (escolha == 2) {

             busca = "https://v6.exchangerate-api.com/v6/11ba23a3d20fe597f293c4fe/pair/brl/usd";

        }else if (escolha == 3) {

            busca = "https://v6.exchangerate-api.com/v6/11ba23a3d20fe597f293c4fe/pair/usd/ars";

        }else if (escolha == 4) {

            busca = "https://v6.exchangerate-api.com/v6/11ba23a3d20fe597f293c4fe/pair/ars/usd";

        }else if (escolha == 5) {

            busca = "https://v6.exchangerate-api.com/v6/11ba23a3d20fe597f293c4fe/pair/usd/bob";

        }else  {

            busca = "https://v6.exchangerate-api.com/v6/11ba23a3d20fe597f293c4fe/pair/bob/usd";

        }

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

        double taxa = objectRoot.get("conversion_rate").getAsDouble();

        return taxa;



    }

}
