package template.quarkus.common;

import java.util.HashMap;
import java.util.Map;

public class UpdatePackage {

    private int untilVersion;
    private int afterVersion;
    private Map<String, FileEntry> files;

    public UpdatePackage(int untilVersion, int afterVersion, Map<String, FileEntry> files) {
        this.untilVersion = untilVersion;
        this.afterVersion = afterVersion;
        this.files = files;
    }

    public UpdatePackage(int untilVersion, String name, byte[] file){
        this.untilVersion = untilVersion;
        this.afterVersion = untilVersion -1;
        files = new HashMap<>();
        files.put(name, new FileEntry(untilVersion, file));
    }

    public int getUntilVersion() {
        return untilVersion;
    }

    public int getAfterVersion() {
        return afterVersion;
    }

    public void setUntilVersion(int untilVersion) {
        this.untilVersion = untilVersion;
    }

    public void setAfterVersion(int afterVersion) {
        this.afterVersion = afterVersion;
    }

    public Map<String, FileEntry> getFiles() {
        return files;
    }

    public void setFiles(Map<String, FileEntry> files) {
        this.files = files;
    }
}
