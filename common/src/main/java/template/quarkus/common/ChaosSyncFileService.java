package template.quarkus.common;

import java.util.concurrent.ThreadLocalRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChaosSyncFileService implements SyncFileService {

    private static final Logger log = LoggerFactory.getLogger(ChaosSyncFileService.class);

    private final SyncFileService syncFileService;

    public ChaosSyncFileService(SyncFileService syncFileService) {
        this.syncFileService = syncFileService;
    }

    @Override
    public void sync(String file, FileContent content) {
        double v = ThreadLocalRandom.current().nextDouble();
        if (v < 0.9) {
            syncFileService.sync(file, content);
        } else {
            log.error("Failed to sync... < 90%");
        }
    }
}
