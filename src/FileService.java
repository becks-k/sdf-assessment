import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileService {
    public List<String> readCSV(String fullPathFilename) throws IOException {
        // Task 1 - your code here
        List<String> fileContent = new ArrayList<>();
        File fr = new File(fullPathFilename);
        Scanner scanner = new Scanner(fr);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            fileContent.add(line);
        }
        
        scanner.close();
        //System.out.print(fileContent); // debug
        return fileContent;
    }

    public void writeAsCSV(String pokemons, String fullPathFilename) throws IOException {
        // Task 1 - your code here
        //List<String> content = Arrays.asList(pokemons.split(","));
        File fr = new File(fullPathFilename);

        if (!fr.exists()) {
            fr.createNewFile();
        }

        BufferedWriter bw = new BufferedWriter(new FileWriter(fr, true));
        bw.write(pokemons + "\n");
        bw.flush();
        bw.close();
    }
    

    // public static void main(String[] args) throws IOException {
    //     FileService fs = new FileService();
    //     List<String> test = fs.readCSV("test.csv");
    //     System.out.println(test);
    //     System.out.println(test.size());
    //     String pokemons = "2* Sheigon,L* Dragonite,2* Rhydon,3* Blastoise,1* Squirtle,3* Tentacruel,2* Pikachu,1* Bulbasaur,1* Bagon,4* Flareon,5* Nihilego,1* Tentacool,2* Bayleef,1* Chikorita,2* Croconaw,3* Rhyperior,2* Quilava,2* Ivysaur,2* Wartortle,2* Ivysaur,L* Snorlax,2* Charmeleon,3* Charizard,4* Genesect,3* Megamium,1* Eevee,2* Croconaw,3* Vaporean,1* Rhyhorn,4* Venusaur,1* Cyndaquil,2* Tentacruel,1* Cyndaquil,4* Manaphy,1* Charmander,2* Rhydon,3* Salamence,1* Bonsly,L* Snorlax,3* Sudowoodo,1* Rhyhorn,1* Squirtle,3* Flareon,5* Mewtwo X,2* Charmeleon,1* Totodile,2* Eevee,1* Charmander,2* Sudowoodo,1* Chikorita";

    //     //fs.writeAsCSV(pokemons, "test.csv");
    //     //fs.writeAsCSV(pokemons2, "test.csv");

    // }
}
