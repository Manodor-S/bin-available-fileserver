package template.quarkus.common;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

// Used internal by Servers
@Path("/sync")
public interface SyncFileService {

    @POST
    @Path("/{file}")
    void sync(@PathParam("file") String file, FileContent content);
}
