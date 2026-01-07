package template.quarkus.common;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;

public interface FileService {

    @POST
    @Path("/queue")
    void foo(@QueryParam("file") String file);
}
