import java.io.*;
public class WriteFIleTest {
    public static void writeToFile(String file) throws IOException {
        FileWriter fw = new FileWriter("test.txt");
        PrintWriter pw = new PrintWriter(fw);
        pw.println("FirstLine");
        pw.println("SecondLine");
        pw.println("ThirdLine");

        pw.close();
        fw.close();

        System.out.println("Done");
    }
    public static void main(String[] args) throws IOException {
        try{
            writeToFile("out.txt");
            System.out.println("created file successfully");
        }
        catch(IOException e){
            System.out.println("problem with writing file");
        }
    }
}
