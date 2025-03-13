import java.io.File;

/*
Manage file and renaming
 */
public class FileToRename {
    private final File file;
    private final String oldName;
    private String newName;

    FileToRename(File file) {
        this.file = file;
        this.oldName = file.getName(); // Retrieve name from file object
    }

    public File getFile() {
        return file;
    }

    // Get old and new file names
    public String getOldName() {
        return oldName;
    }

    public String getNewName() {
        return newName;
    }

    // Save the new file name
    public void setNewName(String newName) {
        this.newName = newName;
    }

    // Rename the file, keeping its location consistent
    public boolean rename() {
        return file.renameTo(new File(this.file.getParent() + "/" + newName));
    }
}
