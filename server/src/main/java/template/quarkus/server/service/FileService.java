package template.quarkus.server.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import template.quarkus.common.SyncFileService;
import template.quarkus.common.UpdatePackage;
import template.quarkus.common.FileEntry;
import template.quarkus.common.FileStore;

@ApplicationScoped
public class FileService {

    private static final Logger log = LoggerFactory.getLogger(FileService.class);

    @Inject
    private SyncFileServiceRegistry syncFileServiceRegistry;

    @Inject
    private FileStore filestore;

    private Collection<SyncFileService> syncFileServiceReplicas;

    public FileService() {}

    @PostConstruct
    public void init() {
        syncFileServiceReplicas = syncFileServiceRegistry.getAllRegistered();
    }

    //Funktion die vom main Server aufgerufen wird
    public int store(UpdatePackage updatePackage) {
        return filestore.updateFileStore(updatePackage);
    }

    //Funktion die vom Client aufgerufen wird
    public int writeThrough(String name, byte[] file) {
        filestore.writeFile(name, file);
        syncFileServiceReplicas.parallelStream().forEach(fs -> {
            UpdatePackage nodePackage = new UpdatePackage(filestore.getLatestVersion(), name, file);
            int returnStatusCode;
            do{
                returnStatusCode = fs.sync(nodePackage);
                if(returnStatusCode > 0)nodePackage = new UpdatePackage(filestore.getLatestVersion(),returnStatusCode,filestore.getFilesChangedAfterVersion(returnStatusCode));
                else if(returnStatusCode == -1)log.error("Nachricht an "+fs.toString()+" ist gefailed");;
            } while (returnStatusCode != 0);
        });
        return 0;
    }

    public byte[] read(String file) {
        return filestore.getFileEntry(file).getData();
    }
}
