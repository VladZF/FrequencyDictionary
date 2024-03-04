import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FrequencyDictionary {

    private final HashMap<Character, Integer> dictionary;
    private final File inputFile;
    private final File outputFile;
    public static String ANSI_WHITE = "\u001B[0m";
    public static String ANSI_GREEN = "\u001B[32m";

    public FrequencyDictionary(File in, File out) throws IOException {
        if (!in.exists()) throw new FileNotFoundException(in.getName() + " not found");
        try {
            if (out.createNewFile())
                System.out.println(ANSI_GREEN + "Output file " + out.getName() + " has created" + ANSI_WHITE);
        } catch (IOException e) {
            throw new IOException(out.getName() + " has not created");
        }
        inputFile = in;
        outputFile = out;
        dictionary = new HashMap<>();
    }

    private String readText() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))){
            StringBuilder textBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null)
                textBuilder.append(line);
            return textBuilder.toString();
        } catch (IOException e) {
            throw new IOException("Cannot read from " + inputFile.getName());
        }
    }

    private void writeText(String name) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile, true))){
            writer.write(name + '\n');
            for (Map.Entry<Character, Integer> item : dictionary.entrySet()) {
                writer.write("'" + item.getKey() + "'" + " - " + item.getValue() + '\n');
            }
        } catch (IOException e) {
            throw new IOException("Cannot write into " + outputFile.getName());
        }
    }

    public void generateTable(String name) throws IOException {
        String text = readText();
        for (int i = 0; i < text.length(); i++) {
            try {
                dictionary.put(text.charAt(i), dictionary.get(text.charAt(i)) + 1);
            } catch (NullPointerException e) {
                dictionary.put(text.charAt(i), 1);
            }
        }
        writeText(name);
    }
}
