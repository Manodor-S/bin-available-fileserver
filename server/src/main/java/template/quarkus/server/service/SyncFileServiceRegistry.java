package template.quarkus.server.service;

import java.net.URI;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import jakarta.inject.Singleton;

import org.eclipse.microprofile.rest.client.RestClientBuilder;
import template.quarkus.common.SyncFileService;

@Singleton
public class SyncFileServiceRegistry {

    private static SyncFileService createFileService(String nodeId) {
        return RestClientBuilder.newBuilder()
                .baseUri(URI.create("http://" + nodeId + ":8080/api"))
                .build(SyncFileService.class);
    }

    private final Map<String, SyncFileService> cache = new ConcurrentHashMap<>();

    public SyncFileServiceRegistry() {}

    public SyncFileService add(String nodeId) {
        return cache.computeIfAbsent(nodeId, SyncFileServiceRegistry::createFileService);
    }

    public Collection<SyncFileService> getAllRegistered() {
        return Collections.unmodifiableCollection(cache.values());
    }
}
