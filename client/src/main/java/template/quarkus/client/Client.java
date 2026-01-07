package template.quarkus.client;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Random;

public class Client {

    private static final String SERVER_URL = "http://localhost:8080/api/file";

    public static void main(String[] args) throws Exception {
        Random random = new Random();
        HttpClient client = HttpClient.newHttpClient();

        while (true) {
            if (random.nextBoolean()) {
                // READ
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(SERVER_URL + "/read"))
                        .GET()
                        .build();

                HttpResponse<String> response =
                        client.send(request, HttpResponse.BodyHandlers.ofString());

                System.out.println("READ: " + response.body());
            } else {
                // WRITE
                String content = "value-" + System.currentTimeMillis();

                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(SERVER_URL + "/write"))
                        .POST(HttpRequest.BodyPublishers.ofString(content))
                        .build();

                client.send(request, HttpResponse.BodyHandlers.discarding());
                System.out.println("WRITE: " + content);
            }

            Thread.sleep(1000);
        }
    }
}
