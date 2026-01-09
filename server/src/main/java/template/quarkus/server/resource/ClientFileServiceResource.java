package template.quarkus.server.resource;

import jakarta.inject.Inject;

import template.quarkus.common.ClientFileService;
import template.quarkus.common.FileContent;
import template.quarkus.common.UpdatePackage;
import template.quarkus.server.service.FileService;

public class ClientFileServiceResource implements ClientFileService {

    @Inject
    private FileService fileService;

    public ClientFileServiceResource() {}

    @Override
    public int write(UpdatePackage updatePackage) {
        return fileService.writeThrough(updatePackage);
    }

    @Override
    public byte[] read(String file) {
        return fileService.read(file);
    }
}
