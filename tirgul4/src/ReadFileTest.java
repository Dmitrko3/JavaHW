import java.io.*;
public class ReadFileTest {
    public static void readFromFile(String fileName) throws IOException {
        FileReader fr = new FileReader(fileName);
        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine();
        while (line != null) {
            System.out.println(line);
            line = br.readLine();
        }

        br.close();
        fr.close();}

    public static void main(String[] args) {
        try {
            readFromFile("out.txt");
        } catch (IOException e) {}
        System.out.println("Problem reading the file.");
    }
}
