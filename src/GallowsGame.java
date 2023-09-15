import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class GallowsGame {

    private List<Character> theWord= new ArrayList<Character>();
    private  Map<Integer,Character> unsolvedLetters = new HashMap<>();
    private List<Character> suggestedLetters = new ArrayList<Character>();
    protected void startGameLoop() throws IOException {
        while (start())
        {
            getTheWord();
            getMapOfUnsolvedLetters();
            int i = 0;
            while (i < 6 && !isWon()) {
                System.out.print("\nCurrent: ");
                printCurrentWord();
                System.out.println("Enter a letter:");
                Scanner sc = new Scanner(System.in);
                char letter = sc.nextLine().toLowerCase().toCharArray()[0];
                if (!isLetterGuessed(letter)) {
                    drawGallows(i);
                    i++;
                }
            }
            if(i < 5) {
                System.out.println("You win!");
                rightAnswer();
            }
            else
                rightAnswer();

            clearData();
        }
    }
    protected void getTheWord() throws IOException {
        Path path = Paths.get("src/RussianNouns.txt");
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        int randomPosition = (int)Math.floor(Math.random() * lines.size());
        for (var item : lines.get(randomPosition).toLowerCase().strip().toCharArray()) {
            theWord.add(item);
        }
    }
    protected void getMapOfUnsolvedLetters() {
        for (int i = 0; i < theWord.size(); i++) {
            unsolvedLetters.put(i, theWord.get(i));
        }
    }
    protected boolean isLetterGuessed(char letter) {
        if(unsolvedLetters.containsValue(letter)) {
            while(unsolvedLetters.values().remove(letter));
            suggestedLetters.add(letter);
            return true;
        }
        else if (suggestedLetters.contains(letter)) {
            return true;
        }
        else {
            suggestedLetters.add(letter);
            return false;
        }
    }
    protected void rightAnswer (){
        System.out.print("\nThe mystery word was: ");
        for (var item : theWord) {
            System.out.print(item);
        }
        System.out.println();
    }
    protected void clearData() {
        suggestedLetters.clear();
        unsolvedLetters.clear();
        theWord.clear();
    }
    protected boolean start()
    {
        System.out.println("Press '1' to start a new game, or any key to exit.");
        Scanner sc = new Scanner(System.in);

        if(!sc.hasNextInt())
            return false;
        else {
            int x = sc.nextInt();
            return x == 1;
        }

    }
    protected void printCurrentWord() {
        for (int i = 0; i < theWord.size(); i++) {
            if (unsolvedLetters.containsKey(i))
                System.out.print("_");
            else
                System.out.print(theWord.get(i));
        }
        System.out.println();
    }
    protected boolean isWon() {
        return unsolvedLetters.isEmpty();
    }
     protected void drawGallows(int i) {
        if (i == 0) {
            System.out.print("""
                    ┌────────────────┐
                    └──┼──────────┼──┤
                       │          │  │
                       │          │  │
                     ┌─┴─┐        │  │
                     │   │        │  │
                     └───┘        │  │
                                  │  │
                                  │  │
                                  │  │
                                  │  │
                                  │  │
                                  │  │
                                  │  │
                                  │  │
                                  │  │
                                  │  │
                                  └──┘""");
        } else if(i == 1) {
            System.out.print("""
                     ┌────────────────┐
                     └──┼──────────┼──┤
                        │          │  │
                        │          │  │
                      ┌─┴─┐        │  │
                      │   │        │  │
                    ┌─┴───┴──┐     │  │
                    │        │     │  │
                    │        │     │  │
                    │        │     │  │
                    │        │     │  │
                    │        │     │  │
                    └────────┘     │  │
                                   │  │
                                   │  │
                                   │  │
                                   │  │
                                   └──┘""");
        } else if(i == 2) {
            System.out.print("""
                       ┌────────────────┐
                       └──┼──────────┼──┤
                          │          │  │
                          │          │  │
                        ┌─┴─┐        │  │
                        │   │        │  │
                    ┌─┬─┴───┴──┐     │  │
                    │ │        │     │  │
                    │ │        │     │  │
                    │ │        │     │  │
                    └─┤        │     │  │
                      │        │     │  │
                      └────────┘     │  │
                                     │  │
                                     │  │
                                     │  │
                                     │  │
                                     └──┘""");
        } else if (i == 3) {
            System.out.print("""
                       ┌────────────────┐
                       └──┼──────────┼──┤
                          │          │  │
                          │          │  │
                        ┌─┴─┐        │  │
                        │   │        │  │
                    ┌─┬─┴───┴──┬─┐   │  │
                    │ │        │ │   │  │
                    │ │        │ │   │  │
                    │ │        │ │   │  │
                    └─┤        ├─┘   │  │
                      │        │     │  │
                      └────────┘     │  │
                                     │  │
                                     │  │
                                     │  │
                                     │  │
                                     └──┘""");
        } else if (i == 4) {
            System.out.print("""
                       ┌────────────────┐
                       └──┼──────────┼──┤
                          │          │  │
                          │          │  │
                        ┌─┴─┐        │  │
                        │   │        │  │
                    ┌─┬─┴───┴──┬─┐   │  │
                    │ │        │ │   │  │
                    │ │        │ │   │  │
                    │ │        │ │   │  │
                    └─┤        ├─┘   │  │
                      │        │     │  │
                      ├─┬──────┘     │  │
                      │ │            │  │
                      │ │            │  │
                      └─┘            │  │
                                     │  │
                                     └──┘""");
        } else if (i == 5) {
            System.out.print("""
                       ┌────────────────┐
                       └──┼──────────┼──┤
                          │          │  │
                          │          │  │
                        ┌─┴─┐        │  │
                        │   │        │  │
                    ┌─┬─┴───┴──┬─┐   │  │
                    │ │        │ │   │  │        You lose... :(
                    │ │        │ │   │  │
                    │ │        │ │   │  │
                    └─┤        ├─┘   │  │
                      │        │     │  │
                      ├─┬────┬─┤     │  │
                      │ │    │ │     │  │
                      │ │    │ │     │  │
                      └─┘    └─┘     │  │
                                     │  │
                                     └──┘""");
        } else {
            System.out.print("");
        }
         System.out.println();
    }
}
