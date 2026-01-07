package template.quarkus.server;

import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import template.quarkus.common.FileService;

@RegisterClientHeaders
@RegisterRestClient(configKey = "")
public interface FileServiceRESTClient2 extends FileService {}
