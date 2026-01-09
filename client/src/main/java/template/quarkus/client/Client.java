package template.quarkus.client;

import java.net.URI;
import java.nio.file.Path;
import java.util.Scanner;

import org.eclipse.microprofile.rest.client.RestClientBuilder;

import template.quarkus.common.ClientFileService;
import template.quarkus.common.UpdatePackage;

public class Client {

    private static final String SERVER_URL = "http://localhost:8081/api/file";
    private int localVersion = 1;

    public Client(){
        ClientFileService clientFileService = RestClientBuilder.newBuilder()
            .baseUri(URI.create("http://localhost:8081/api"))
            .build(ClientFileService.class);
        try (Scanner s1 = new Scanner(System.in);) {
            while (true) {
                String input = s1.nextLine();
                Path file = Path.of(input);
                UpdatePackage p = new UpdatePackage(localVersion, localVersion -1, null);
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            System.out.println("tja");
        }
    };
    public static void main(String[] args) throws Exception {
        Client c1 = new Client();
    }
}
