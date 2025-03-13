import java.io.File;

public class FileToRename {
    private final File file;
    private final String oldName;
    private String newName;

    FileToRename(File file) {
        this.file = file;
        this.oldName = file.getName();
    }

    public File getFile() {
        return file;
    }

    public String getOldName() {
        return oldName;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public boolean rename() {
        return file.renameTo(new File(newName));
    }
}
