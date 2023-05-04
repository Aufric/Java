import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class HangMan {
    public static void main(String[] args) {
        Game.Play();

    }
}

class Game {
    static Scanner scan = new Scanner(System.in);
    // example words to be guessed
    static String[] words = { "APPREHENSION", "CONSPICIOUS", "ENHANCEMENT", "KALEIDOSCOPE" };
    static String wordTobeGuessed;
    static String option, diff;
    static char[] hiddenWord;

    static void Play() {
        // return the hidden word
        Randomizer.HideWord(Randomizer.SecretWord());
        List<Character> badGuessed = new ArrayList<>();
        List<Character> goodGuessed = new ArrayList<>();
        List<Character> letterUsed = new ArrayList<>();
        String ToString = new String(hiddenWord);
        clearScreen();
        while (true) {
            try {
                System.out.printf("Letter used: %s" + "\n", letterUsed);
                System.out.printf("Bad Guesses: %s" + "\n", badGuessed);
                BadGuesse(badGuessed.size());

                String bad = badGuessed.toString();
                String good = goodGuessed.toString();

                System.out.println(ToString);

                char[] word2 = wordTobeGuessed.toCharArray();
                String guess = scan.nextLine().toUpperCase();
                char[] word1 = guess.toCharArray();
                // pattern to filter input
                Pattern pattern = Pattern.compile("-?[0-9]+");
                Matcher isNum = pattern.matcher(guess);
                // Filter input
                if (guess.length() >= 2 || guess.length() <= 0 || isNum.matches()) {
                    throw new InputMismatchException();
                } else {
                    if (!(new String(word2).indexOf(word1[0]) == -1)) {
                        // Already guessed in good guesses
                        if (!(new String(good).indexOf(word1[0]) == -1)) {
                            clearScreen();
                            System.out.println("Letter already guessed");
                        } else {
                            // This shows the letter if correct
                            int index = wordTobeGuessed.indexOf(guess);
                            while (index >= 0) {
                                ToString = ToString.substring(0, index) + word1[0] + ToString.substring(index + 1);
                                index = wordTobeGuessed.indexOf(guess, index + 1);
                                goodGuessed.add(word1[0]);
                            }
                            letterUsed.add(word1[0]);
                            System.out.println(ToString);
                        }
                    } else {
                        // Already guessed in bad guesses
                        if (!(new String(bad).indexOf(word1[0]) == -1)) {
                            clearScreen();
                            System.out.println("Letter already guessed");
                        } else {
                            System.out.println("Bad Guessed");
                            badGuessed.add(word1[0]);
                            letterUsed.add(word1[0]);
                        }
                    }
                    if (wordTobeGuessed.length() == goodGuessed.size()) {
                        clearScreen();
                        if (!(wordTobeGuessed.equalsIgnoreCase(good))) {
                            System.out.printf("You Win! Word is %s", wordTobeGuessed);
                            break;
                        }
                        System.out.println("Equal");
                    }
                    if (badGuessed.size() >= 7) {
                        clearScreen();
                        System.out.printf("Game Over! The word is %s", wordTobeGuessed);
                        break;
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Only one letter is allowed. No Space and number are allowed");
            }
        }
    }

    static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void BadGuesse(int i) {
        switch (i) {
            case 0:
                System.out.print("" + "\n" +
                        "" + "\n" +
                        "" + "\n" +
                        "" + "\n" +
                        "" + "\n" +
                        "" + "\n" +
                        "" + "\n" +
                        "__|__" + " \n");
                break;
            case 1:
                System.out.print("          " + "\n" +
                        "  |      " + "\n" +
                        "  |      " + "\n" +
                        "  |      " + "\n" +
                        "  |      " + "\n" +
                        "  |      " + "\n" +
                        "  |      " + "\n" +
                        "__|__" + " \n");
                break;
            case 2:
                System.out.print("   _____ " + "\n" +
                        "  |      " + "\n" +
                        "  |      " + "\n" +
                        "  |      " + "\n" +
                        "  |      " + "\n" +
                        "  |      " + "\n" +
                        "  |      " + "\n" +
                        "__|__" + " \n");
                break;
            case 3:
                System.out.print("   _____ " + "\n" +
                        "  |      |" + "\n" +
                        "  |      |" + "\n" +
                        "  |      |" + "\n" +
                        "  |      " + "\n" +
                        "  |      " + "\n" +
                        "  |      " + "\n" +
                        "__|__" + " \n");
                break;
            case 4:
                System.out.print("   _____ " + "\n" +
                        "  |      |" + "\n" +
                        "  |      |" + "\n" +
                        "  |      |" + "\n" +
                        "  |      O" + "\n" +
                        "  |      " + "\n" +
                        "  |      " + "\n" +
                        "__|__" + " \n");
                break;
            case 5:
                System.out.print("   _____ " + "\n" +
                        "  |      |" + "\n" +
                        "  |      |" + "\n" +
                        "  |      |" + "\n" +
                        "  |      O" + "\n" +
                        "  |     /|\\" + "\n" +
                        "  |      " + "\n" +
                        "__|__" + " \n");
                break;
            case 6:
                System.out.print("   _____ " + "\n" +
                        "  |      |" + "\n" +
                        "  |      |" + "\n" +
                        "  |      |" + "\n" +
                        "  |      O" + "\n" +
                        "  |     /|\\" + "\n" +
                        "  |     / \\" + "\n" +
                        "__|__" + " \n");
                break;
            default:
                break;
        }
    }
}

class Randomizer extends Game {
    // select random word on the the choices
    public static String SecretWord() {
        String secretWord = (words[new Random().nextInt(words.length)]);
        return secretWord;
    }

    // hide selected word
    static char[] HideWord(String word) {
        hiddenWord = new char[word.length()];
        for (int i = 0; i < word.length(); i++) {
            hiddenWord[i] += '_';
        }
        wordTobeGuessed = word;
        return hiddenWord;
    }
}