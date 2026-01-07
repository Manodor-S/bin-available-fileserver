package template.quarkus.common;

import jakarta.ws.rs.*;

// Used by Client
@Path("/files")
public interface ClientFileService {

    @POST
    @Path("/{file}")
    void write(@PathParam("file") String file, FileContent content);

    @GET
    @Path("/{file}")
    FileContent read(@PathParam("file") String file);
}
