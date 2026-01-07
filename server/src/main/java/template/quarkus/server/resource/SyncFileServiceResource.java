package template.quarkus.server.resource;

import jakarta.inject.Inject;

import template.quarkus.common.FileContent;
import template.quarkus.common.SyncFileService;
import template.quarkus.server.service.FileService;

public class SyncFileServiceResource implements SyncFileService {

    @Inject
    private FileService fileService;

    public SyncFileServiceResource() {}

    @Override
    public void sync(String file, FileContent content) {
        fileService.store(file, content.content());
    }
}
