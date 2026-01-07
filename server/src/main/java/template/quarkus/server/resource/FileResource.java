package template.quarkus.server.resource;

import java.util.HashMap;
import java.util.Map;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import template.quarkus.server.NodeConfig;

@Path("/file")
@ApplicationScoped
public class FileResource {

    private String fileContent = "initial content";

    @Inject
    private NodeConfig nodeConfig;

    @GET
    @Path("/read")
    @Produces(MediaType.TEXT_PLAIN)
    public String read() {
        return "[server " + nodeConfig.getNodeId() + "] " + fileContent;
    }

    @POST
    @Path("/write")
    @Consumes(MediaType.TEXT_PLAIN)
    public Response write(String newContent) {
        fileContent = newContent;

        /*System.out.println(
                "Server " + serverConfig.serverId +
                        " received WRITE. Replicas: " + serverConfig.replicas
        );*/

        return Response.ok().build();
    }

    @GET
    @Path("/info")
    @Produces(MediaType.APPLICATION_JSON)
    public Object info() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", nodeConfig.getNodeId());
        return map;
    }
}
