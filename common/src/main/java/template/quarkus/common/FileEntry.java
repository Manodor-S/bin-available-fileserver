package template.quarkus.common;

public class FileEntry {
    private int version;
    private byte[] data;

    public FileEntry(int version, byte[] data) {
        this.version = version;
        this.data = data;
    }


    public FileEntry setVersion(int version){
        this.version = version;
        return this;
    }

    public FileEntry setData(byte[] data){
        this.data = data;
        return this;
    }


    public int getVersion() {
        return version;
    }

    public byte[] getData() {
        return data;
    }
}

