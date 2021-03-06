package SP;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ReadFileInSeperatedFolder {

    public static void main(String[] args) {


        System.out.print("Dir to Parse: ");
        Scanner input = new Scanner(System.in);
        String dir = input.nextLine();

        System.out.print("Dir to Print: ");
        String output = input.nextLine();


        while (true) {

            List<String> data = new ArrayList<>();

            System.out.print("File to Parse: ");

            Scanner scanner = new Scanner(System.in);
            String nextLine = scanner.nextLine();

            try {
                File myObj = new File(dir + "\\" + nextLine + "\\" + nextLine + ".txt");
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                    String line = myReader.nextLine();
                    List<String> word = Arrays.asList(line.split(" "));
                    data.addAll(word);
                }
                System.out.println("Done read");
                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("Can't find file to read.");
            }

            try {
                FileWriter myWriter = new FileWriter(output + "\\data" + nextLine + ".txt");
                for (String str : data) {
                    myWriter.write(str.toLowerCase() + "\n");
                }
                System.out.println("Done Write");
                myWriter.close();
            } catch (IOException e) {
                System.out.println("Can't write file.");
            }

            System.out.println();
        }
    }
}

