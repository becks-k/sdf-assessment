import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileService {
    public List<String> readCSV(String fullPathFilename) throws IOException {
        // Task 1 - your code here
        List<String> fileContent = new ArrayList<>();
        
        String[] fileNameArr = fullPathFilename.split("/");
        if (fileNameArr.length > 1) {
            String directory = fileNameArr[0];
            File dir = new File(directory);

            // Creates directory if it does not exist
            Path d = Paths.get(dir.getPath());
            if (!Files.exists(d)) {
                Files.createDirectory(d);
            }
        }

        File fr = new File(fullPathFilename);
        // Creates file if it does not exist
        if (!fr.exists()) {
            fr.createNewFile();
        }

        // Read file
        Scanner scanner = new Scanner(fr);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            fileContent.add(line);
        }

        scanner.close();
        return fileContent;
    }

    public void writeAsCSV(String pokemons, String fullPathFilename) throws IOException {
        // Task 1 - your code here
        File fr = new File(fullPathFilename);

        if (!fr.exists()) {
            fr.createNewFile();
        }

        BufferedWriter bw = new BufferedWriter(new FileWriter(fr, true));
        bw.write(pokemons + "\n");
        bw.flush();
        bw.close();
    }

}
