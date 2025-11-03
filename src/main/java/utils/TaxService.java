package utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Properties;

public class TaxService {
    private final String apiKey;

    public TaxService() {
        this.apiKey = loadApiKey();
    }
    private String loadApiKey() {
        Properties prop = new Properties();
        try (FileInputStream input = new FileInputStream("config.properties")) {
            prop.load(input);
            return prop.getProperty("API_KEY");
        } catch (IOException ex) {
            System.err.println("ERRO: Não foi possível carregar a chave de API do arquivo config.properties.");
            System.err.println("Certifique-se de que o arquivo config.properties existe e está no diretório correto.");

            throw new RuntimeException("Chave de API ausente ou inacessível.", ex);
        }
    }

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



        JsonElement elemento = JsonParser.parseString(json);
        JsonObject objectRoot = elemento.getAsJsonObject();


        BigDecimal taxa = objectRoot.get("conversion_rate").getAsBigDecimal();

        return taxa;



    }

}
