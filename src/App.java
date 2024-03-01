import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class App {
    public static HashMap<Integer, List<String>> pokeStack = new HashMap<>();
    public static List<String> fileContent = new ArrayList<>();

    public static void main(String[] args) throws Exception {

        // Run Your Code here
        FileService fileService = new FileService();
        String fileName = "";
        if (args.length > 0) {
            fileName = args[0];
        }

        fileContent = fileService.readCSV(fileName);

        // Initialise hashmap of stacks to cards
        for (Integer i = 0; i < fileContent.size(); i++) {
            String[] strArr = fileContent.get(i).split(",");
            List<String> strList = Arrays.asList(strArr);

            pokeStack.put(i + 1, strList);
        }

        // System.out.println(pokeStack.entrySet()); //debug
        // printUniquePokemonStack(1); //debug

        Console cons = System.console();

        // boolean gameOn = true;
        while (true) {
            printHeader();
            String input = cons.readLine("Enter your selection >");
            if (input.equals("q")) {
                printExitMessage();
                break;
            }

            switch (input) {
                case "4":
                    printPokemonCardCount();
                    pressAnyKeyToContinue();
                    input = cons.readLine();
                    break;

                case "1":
                    int stackNo = Integer
                            .parseInt(cons.readLine("Display the list of unique Pokemon in stack (1-8) >"));
                    printUniquePokemonStack(stackNo);
                    pressAnyKeyToContinue();
                    input = cons.readLine();
                    break;

                case "2":
                    String enteredPokemon = cons.readLine(
                            "Search for next occurrence of 5 stars Pokemon in all stacks based on entered Pokemon >");
                    printNext5StarsPokemon(enteredPokemon);
                    pressAnyKeyToContinue();
                    input = cons.readLine();
                    break;

                case "3":
                    String pokemon = cons.readLine("Create a new Pokemon stack and save to a new file >");
                    String fullPathFilename = cons.readLine("Enter filename to save (e.g. path/filename.csv)>");
                    savePokemonStack(pokemon, fullPathFilename);
                    pressAnyKeyToContinue();
                    input = cons.readLine();
                    break;

                default:
                    System.out.println("ERROR >>> Key in a valid input.\n");
                    pressAnyKeyToContinue();
                    input = cons.readLine();
                    break;

            }
        }

    }

    public static void clearConsole() throws IOException {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // Task 1
    public static void pressAnyKeyToContinue() {
        // your code here
        System.out.printf("Press any key to continue...\n");
    }

    // Task 1
    public static void printHeader() {

        // Task 1 - your code here
        System.out.printf(
                "Welcome to Pokemon Gaole Legend 4 Rush 2\n(1) View unique list of Pokemon in the selected stack\n(2) Find next 5 stars Pokemon occurence\n(3) Create new Pokemon stack and save (append) to csv file\n(4) Print distinct Pokemon and cards count\n(q) to exit the program\n");
    }

    // Task 1
    public static void printExitMessage() {

        // Task 1 - your code here
        System.out.printf("Thank you for using the program...\nHope to see you soon...\n");
    }

    // Task 1
    public static void savePokemonStack(String pokemonStack, String filename) {

        // Task 1 - your code here
        try {
            FileService fs = new FileService();
            fs.writeAsCSV(pokemonStack, filename);
            fileContent = fs.readCSV(filename);
            for (Integer i = 0; i < fileContent.size(); i++) {
                String[] strArr = fileContent.get(i).split(",");
                List<String> strList = Arrays.asList(strArr);
    
                pokeStack.put(i + 1, strList);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Task 2
    public static void printUniquePokemonStack(Integer stack) {
        // Task 2 - your code here
        List<String> stackList = new ArrayList<>();
        int stackSize = pokeStack.size();
        if (stackSize == 0) {
            System.out.println("There are no cards in the stack!");
        } else if (stack > stackSize) {
            System.out.printf("You have %d stacks. Please select a number within stack size. Press any key to return back to menu.\n", stackSize);
        } else if (stack > 0 && stack <= stackSize) {
            stackList = pokeStack.get(stack);
            HashSet<Object> uniqueStack = new HashSet<>(stackList);
            Object[] uniqueArr = uniqueStack.toArray();
            for (int i = 0; i < uniqueArr.length; i++) {
                System.out.printf("%d ===> %s\n", i + 1, uniqueArr[i]);
            }
        } else {
            System.out.println("Key in a value between 1 - 8. Press any key to return back to menu.");
        }
    }

    // Task 2
    public static void printNext5StarsPokemon(String enteredPokemon) {
        // Task 2 - your code here
        for (int i = 0; i < pokeStack.size(); i++) {
            List<String> cardList = pokeStack.get(i + 1);
            String fiveStarPokemon = "";
            for (String s : cardList) {
                if (s.startsWith("5")) {
                    fiveStarPokemon = s;
                    continue;
                    }
                }

            if (cardList.contains(enteredPokemon) && cardList.contains(fiveStarPokemon)) {
                int idxEnteredPokemon = cardList.indexOf(enteredPokemon);
                int idx5StarPokemon = cardList.indexOf(fiveStarPokemon);
                System.out.printf("Set %d\n %s >>>>>> %d cards to go\n", i + 1,fiveStarPokemon,(idx5StarPokemon-idxEnteredPokemon));
            } else if (cardList.contains(enteredPokemon)) {
                System.out.printf("Set %d\nNo 5 stars Pokemon found subsequently in the stack.\n", i + 1);
            } else {
                System.out.printf("Set %d\n%s not found in this set.\n", i + 1, enteredPokemon);
            }
        }
    }


    // Task 2
    public static void printPokemonCardCount() {
        // Task 2 - your code here
        HashMap<String, Integer> uniqueCardCount = new HashMap<>();
        for (int i = 0; i < pokeStack.size(); i++) {
            List<String> cardList = pokeStack.get(i + 1);
            for (String card : cardList) {
                if (uniqueCardCount.containsKey(card)) {
                    uniqueCardCount.put(card, uniqueCardCount.get(card) + 1);
                } else {
                    uniqueCardCount.put(card, 1);
                }
            }
        }
        uniqueCardCount.entrySet().stream()
            .sorted((k1, k2) -> k2.getValue().compareTo(k1.getValue()))
            .limit(10)
            .forEach(k -> System.out.println(k.getKey() + ": " + k.getValue()));
    }

}
