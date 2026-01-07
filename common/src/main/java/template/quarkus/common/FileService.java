package template.quarkus.common;

import jakarta.ws.rs.*;

public interface FileService {

    record FileContent(String content) {}

    @POST
    @Path("/{file}")
    void write(@PathParam("file") String file, FileContent content);

    @GET
    @Path("/{file}")
    FileContent read(@PathParam("file") String file);
}
