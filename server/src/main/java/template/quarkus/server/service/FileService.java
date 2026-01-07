package template.quarkus.server.service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import template.quarkus.common.FileContent;
import template.quarkus.common.SyncFileService;

@ApplicationScoped
public class FileService {

    private static final Logger log = LoggerFactory.getLogger(FileService.class);

    private final Map<String, String> storage = new ConcurrentHashMap<>();

    @Inject
    private SyncFileServiceRegistry syncFileServiceRegistry;

    private Collection<SyncFileService> syncFileServiceReplicas;

    public FileService() {}

    @PostConstruct
    public void init() {
        syncFileServiceReplicas = syncFileServiceRegistry.getAllRegistered();
    }

    public void store(String file, String content) {
        storage.put(file, content);
    }

    public void writeThrough(String file, String content) {
        storage.put(file, content);
        syncFileServiceReplicas.parallelStream().forEach(fs -> fs.sync(file, new FileContent(content)));
    }

    public String read(String file) {
        return storage.get(file);
    }
}
