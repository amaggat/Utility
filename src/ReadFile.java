import com.sun.org.apache.xerces.internal.impl.xs.SchemaNamespaceSupport;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ReadFile {

    public static void main(String[] args) {

        while(true){
            List<String> data = new ArrayList<>();

            System.out.println("File to Parse: ");

            Scanner scanner = new Scanner(System.in);
            String nextLine = scanner.nextLine();

            try {
                File myObj = new File("C:\\Users\\Aleister\\OneDrive\\Document\\Year 3\\Sem 2\\Speech Processing\\work\\" + nextLine + "\\" + nextLine + ".txt");
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                    String line = myReader.nextLine();
                    List<String> word = Arrays.asList(line.split(" "));
                    data.addAll(word);
                    System.out.println(word);
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

            try {
                FileWriter myWriter = new FileWriter("C:\\Users\\Aleister\\OneDrive\\Document\\Year 3\\Sem 2\\Speech Processing\\work\\"+ nextLine + "\\data" + nextLine + ".txt");
                for(String str : data) {
                    myWriter.write(str + "\n");
                }
                myWriter.close();
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
        }
}

