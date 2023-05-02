import java.util.*;

public class NumberGuessing {
    public static void main(String[] args) {
        GuessingGame.NumberGame();
    }
}

class GuessingGame {
    static Random random = new Random();
    static String option, settings, diff = "easy";
    static String[] ofGuesses = { "first", "second", "last" };;
    static int trials = 3, num = 10, rand = random.nextInt(num), guess, showGuess;
    static Scanner scan = new Scanner(System.in);
    static List<Integer> guesses = new ArrayList<Integer>();

    static void NumberGame() {
        // show game menu
        System.out.println("Please type 'Play' to start the game");
        System.out.println("Please type 'Diff' to to open settings");
        System.out.println("Please type 'Exit' to Exit the game");
        // show default settings
        System.out.printf("Game difficulty is %s with numbers from 0 to %d with %d guesses." + "\n",
                diff.toUpperCase(), num, trials);
        option = scan.nextLine();
        switch (option) {
            case "play":
                play();
                break;
            case "diff":
                GameDifficulty();
                break;
            case "exit":
                exit();
                break;
            default:
                clearScreen();
                System.out.println("Invalid Option");
                NumberGame();
                break;
        }
    }

    static void play() {
        clearScreen();
        rand = random.nextInt(num - 1) + 1;
        // used to check delete this
        System.out.println(diff + num + rand);
        System.out.printf("Game option is %s with %d guesses." + "\n", diff, trials);
        System.out.printf("Guess number from 1 to %d." + "\n", num);
        while (guesses.size() < trials) {
            ShowGuess();
            System.out.printf("This is your %s guess: " + "\n", ofGuesses[guesses.size()]);
            guess = scan.nextInt();
            if (guess == rand) {
                clearScreen();
                System.out.printf("You win! You guessed my number %d" + "\n", rand);
                guesses.clear();
                option = scan.nextLine();
                PlayAgain();
            } else if (guess != rand) {
                guesses.add(guess);
                if (guess > rand) {

                    System.out.println("Your number is greater than my number");
                    clearScreen();
                } else if (guess < rand) {

                    System.out.println("Your number is lower than my number");
                    clearScreen();
                } else {
                    System.out.println("Invalid number");
                }
            } else {
                System.out.println("Invalid Option");
            }
        }
        clearScreen();
        if (guesses.size() == trials)
            System.out.printf("You lose the number is %d" + "\n", rand);
        guesses.clear();
        option = scan.nextLine();
        PlayAgain();
    }

    // change game difficulty and guesses
    static void GameDifficulty() {
        if ("diff".equalsIgnoreCase(option)) {
            System.out.println("Please choose difficulty level: (Easy,Medium,Hard)");
            diff = scan.nextLine();
            difficulty(diff);
            NumberGame();
        } else if ("back".equalsIgnoreCase(option)) {
            NumberGame();
        } else {
            clearScreen();
            System.out.println("Invalid Option");
            GameDifficulty();
        }
    }

    static int difficulty(String a) {
        switch (a) {
            case "easy":
                return num = 10;
            case "medium":
                return num = 20;
            case "hard":
                ofGuesses = new String[] { "first", "second", "third", "forth", "last" };
                trials = 5;
                return num = 30;
            default:
                break;
        }
        return num;
    }

    static void PlayAgain() {
        System.out.println("Do you want to play again? Y/N");
        String a = scan.nextLine();
        if ("y".equalsIgnoreCase(a)) {
            clearScreen();
            NumberGame();
        } else {
            exit();
        }
    }

    // show guesses
    static void ShowGuess() {
        System.out.print("Your guess number are: ");
        for (int i : guesses) {
            System.out.print(i + " ");
        }
        System.out.print("\n");
    }

    // exit the program
    static void exit() {
        clearScreen();
        System.out.println("Thank you playing!");
        System.exit(0);
    }

    // clear terminal
    static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
