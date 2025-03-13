import java.io.File;
import java.util.*;

/*
Rename files in a given directory.
 */
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<FileToRename> files = new ArrayList<>();

        // Get the contents of a directory
        System.out.print("Enter /path/to/rename/files: ");
        String directoryPath = input.nextLine();
        File directory = new File(directoryPath);
        File[] contents = directory.listFiles();

        // Make sure directory is valid and contents were obtained properly
        if (directory.exists() && directory.isDirectory()) {
            // Check if there are items and if they are accessible
            if (contents != null) {
                if (contents.length == 0) {
                    System.out.println("The directory is empty. No files to rename.");
                    System.exit(0);
                }
            } else {
                System.out.println("Failed to list files. Check permissions or if the path is valid.");
                System.exit(1);
            }
        } else {
            System.out.println("The specified path is not a valid directory.");
            System.exit(1);
        }

        // Collect ONLY valid files
        for (File item : contents) {
            if (item.isFile() && item.getName().lastIndexOf(".") > 0) {
                files.add(new FileToRename(item));
            }
        }

        // Make sure at least one item is a file
        if (files.isEmpty()) {
            System.out.println("The directory is empty. No files to rename.");
            System.exit(0);
        }

        // Store and print queued name changes
        System.out.println("\nRenaming the following files:");
        HashMap<String, Integer> extensions = new HashMap<>();
        for (FileToRename file : files) {
            // Get file extension
            String filename = file.getOldName();
            String extension = filename.substring(filename.lastIndexOf(".") + 1);

            // Update and get extension count
            if (extensions.containsKey(extension)) {
                extensions.put(extension, extensions.get(extension) + 1);
            } else {
                extensions.put(extension, 1);
            }

            // Set new names
            String filenameFmt = directory.getName() + "_%03d.%s";
            file.setNewName(String.format(filenameFmt, extensions.get(extension), extension));

            // Print name changes
            System.out.println(file.getOldName() + " --> " + file.getNewName());
        }

        // Confirm changes
        System.out.print("Confirm changes (y/n): ");
        int filesChanged = 0;
        if (input.nextLine().equalsIgnoreCase("y")) {
            // Rename files if user approves
            for (FileToRename file : files) {
                if (file.rename()) filesChanged++;
            }
        } else {
            // Exit without renaming any files
            System.out.println("\nChanges cancelled.");
            System.exit(0);
        }

        // Print results
        System.out.println("\n"+ filesChanged + " files renamed.");
        System.out.println((files.size() - filesChanged) + " errors.");
    }
}