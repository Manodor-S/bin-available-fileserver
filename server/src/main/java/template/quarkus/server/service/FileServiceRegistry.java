package template.quarkus.server.service;

import java.net.URI;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import jakarta.inject.Singleton;

import org.eclipse.microprofile.rest.client.RestClientBuilder;
import template.quarkus.common.FileService;

@Singleton
public class FileServiceRegistry {

    private static FileService createFileService(String nodeId) {
        return RestClientBuilder.newBuilder()
                .baseUri(URI.create("http://" + nodeId + ":8080/api"))
                .build(FileService.class);
    }

    private final Map<String, FileService> cache = new ConcurrentHashMap<>();

    public FileServiceRegistry() {}

    public FileService add(String nodeId) {
        return cache.computeIfAbsent(nodeId, FileServiceRegistry::createFileService);
    }

    public Collection<FileService> getAllRegistered() {
        return Collections.unmodifiableCollection(cache.values());
    }
}
