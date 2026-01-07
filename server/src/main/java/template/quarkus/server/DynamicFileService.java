package template.quarkus.server;

import org.eclipse.microprofile.rest.client.RestClientBuilder;
import template.quarkus.common.FileService;

public class DynamicFileService {

    private DynamicFileService() {}

    public static FileService createFileService(String nodeId) {
        return RestClientBuilder.newBuilder()
                .baseUri(String.format("http://%s:8080/api", nodeId))
                .build(FileService.class);
    }
}
