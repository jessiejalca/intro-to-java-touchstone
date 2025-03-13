import java.io.File;
import java.util.*;

/*
Rename files in a given directory.
 */
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<File> files = new ArrayList<>();

        // Get the contents of a directory
        System.out.print("Enter the directory path containing the files to rename: ");
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

        // Collect ONLY files
        for (File item : contents) {
            if (item.isFile()) {
                files.add(item);
            }
        }

        // Make sure at least one item is a file
        if (files.isEmpty()) {
            System.out.println("The directory is empty. No files to rename.");
            System.exit(0);
        }

        // Rename files
        HashMap<String, Integer> extensionCount = new HashMap<>();
        for (File file : files) {
            // Get file extension
            String filename = file.getName();
            String extension = "";
            int extIndex = filename.lastIndexOf(".");
            if (extIndex > 0) {
                extension = filename.substring(extIndex + 1);
            }

            // Update and get extension count
            if (extensionCount.containsKey(extension)) {
                extensionCount.put(extension, extensionCount.get(extension) + 1);
            } else {
                extensionCount.put(extension, 1);
            }
        }

        // Print results
        for (File file : files) {
            System.out.println(file.getName());
        }
        System.out.println("\nNumber of files by extension:");
        for (String extension : extensionCount.keySet()) {
            System.out.printf("%8s:%4d\n", extension, extensionCount.get(extension));
        }
    }
}