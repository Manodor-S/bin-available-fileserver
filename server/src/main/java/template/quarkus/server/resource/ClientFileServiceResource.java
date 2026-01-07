package template.quarkus.server.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.Path;

import template.quarkus.common.ClientFileService;
import template.quarkus.common.FileContent;
import template.quarkus.server.service.FileService;

@Path("/files")
public class ClientFileServiceResource implements ClientFileService {

    @Inject
    private FileService fileService;

    public ClientFileServiceResource() {}

    @Override
    public void write(String file, FileContent content) {
        fileService.writeThrough(file, content.content());
    }

    @Override
    public FileContent read(String file) {
        return new FileContent(fileService.read(file));
    }
}
