package dto;

/**
 *
 * @author Jose Carlos Gutierrez
 */
public class File {

    private int fileId;
    private String fileName;
    private String path;
    private int temporal;

    public File() {
        ;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public int getFileId() {
        return this.fileId;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return this.path;
    }

    public void setTemporal(int temporal) {
        this.temporal = temporal;
    }

    public int getTemporal() {
        return this.temporal;
    }
   

}
