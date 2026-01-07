package template.quarkus.server;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Singleton;

import io.quarkus.scheduler.Scheduled;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import template.quarkus.common.FileService;

@Singleton
public class NodeConfig {

    private static final Logger log = LoggerFactory.getLogger(NodeConfig.class);

    private final Map<String, FileService> fileServiceReplicas = new ConcurrentHashMap<>();

    @ConfigProperty(name = "node.id")
    private String nodeId;

    @ConfigProperty(name = "node.replicas")
    private Set<String> replicas;

    public NodeConfig() {}

    @PostConstruct
    public void configure() {
        for (String replica : replicas) {
            log.info("Create REST Client for {}", replica);
            fileServiceReplicas.put(replica, DynamicFileService.createFileService(replica));
        }
    }

    @Scheduled(every = "30s", delay = 5, delayUnit = TimeUnit.SECONDS)
    public void maybeDisable() {
        double v = ThreadLocalRandom.current().nextDouble();
        try (MDC.MDCCloseable closeable = MDC.putCloseable("node_id", nodeId)) {
            if (v < 0.9) {
                boolean die = v < 0.15;
                setDisabled(die);
            } else {
                setEnabled();
            }
        }
    }

    public void setDisabled(boolean die) {
        if (die) {
            log.error("Node {} is dead", nodeId);
            System.exit(1);
        } else {
            log.error("Node {} is down", nodeId);
        }
    }

    public void setEnabled() {
        log.info("Node {} is back", nodeId);
    }

    public Map<String, FileService> getFileServiceReplicas() {
        return fileServiceReplicas;
    }

    public String getNodeId() {
        return nodeId;
    }
}
