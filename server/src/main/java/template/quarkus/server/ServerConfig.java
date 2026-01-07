package template.quarkus.server;

import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.List;

@ApplicationScoped
public class ServerConfig {

    @ConfigProperty(name = "server.id")
    public int serverId;

    @ConfigProperty(name = "server.port")
    public int serverPort;

    @ConfigProperty(name = "server.replicas")
    public List<String> replicas;
}
