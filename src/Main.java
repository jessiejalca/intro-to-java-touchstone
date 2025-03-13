import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<File> files = new ArrayList<>();

        // Get the file names
        System.out.println("List the files you want to rename separated by spaces.");
        System.out.print("Hit enter when you're finished: ");
        String filenamesString = input.nextLine();
        String[] filenames = filenamesString.split(" ");

        // Convert file names to files
        for(String filename : filenames) {
            System.out.println(filename);
        }
    }
}