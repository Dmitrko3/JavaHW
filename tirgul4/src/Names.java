import java.io.*;

public class Names {
    public static int countValidNames(String fileName) throws IOException {
        int count = 0;

        FileReader fr = new FileReader(fileName);
        BufferedReader br = new BufferedReader(fr);

        String line = br.readLine();

        while (line != null) {
            try {
                if (line.matches("[A-Za-z]+")) {
                    count++;
                } else {
                    throw new RuntimeException("Invalid name: " + line);
                }
            } catch (RuntimeException e) {
                // Continue processing the next line
            }

            line = br.readLine();
        }

        br.close();
        fr.close();

        return count;
    }

    public static void copyValidNames(String inputFileName, String outputFileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(inputFileName));
        PrintWriter pw = new PrintWriter(outputFileName);

        String line = br.readLine();

        while (line != null) {
            try {
                if (line.matches("[A-Za-z]+")) {
                    pw.println(line);
                } else {
                    throw new RuntimeException("Invalid name: " + line);
                }
            } catch (RuntimeException e) {
                // Invalid line is ignored, continue processing
            }

            line = br.readLine();
        }

        br.close();
        pw.close();
    }
    public static void main(String[] args) {
        try {
            int validNames = Names.countValidNames("names.txt");
            System.out.println("Valid names: " + validNames);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        try {
            Names.copyValidNames("names.txt", "valid_names.txt");
            System.out.println("Valid names copied successfully.");
        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
        }
    }
}