import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        
        List<String> test2 = new ArrayList<>();
        test2 = Arrays.asList("2* Croconaw", "2* Rhydon ", "2* Wartortle", "4* Charizard Y", "3* Venusaur");
        for (String s: test2) {

            System.out.println(s.startsWith("2"));
        }
        
    }
}
