import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class DecodeLabs_Java_P1 {

    private static final int MIN = 1;
    private static final int MAX = 100;
    private static final int MAX_ATTEMPTS = 7; 
    private static final int POINTS_PER_ATTEMPT_LEFT = 10;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        int roundsPlayed = 0;
        int roundsWon = 0;
        int totalScore = 0;
        String playAgain;

        System.out.println("     DecodeLabs Number Guessing Game   ");

        do {
            
            int target = random.nextInt(MAX - MIN + 1) + MIN;
            int attempts = 0;
            boolean win = false;
            roundsPlayed++;

            System.out.println("\n--- Round " + roundsPlayed + " ---");
            System.out.println("I'm thinking of a number between " + MIN + " and " + MAX + ".");
            System.out.println("You have " + MAX_ATTEMPTS + " attempts. Good luck!");

            while (!win && attempts < MAX_ATTEMPTS) {
                int guess = readGuess(sc, MAX_ATTEMPTS - attempts);
                attempts++;

                if (guess == target) {
                    win = true;
                    int points = (MAX_ATTEMPTS - attempts + 1) * POINTS_PER_ATTEMPT_LEFT;
                    totalScore += points;
                    roundsWon++;
                    System.out.println("Correct! You got it in " + attempts + " attempt(s). +" + points + " points!");
                } else if (guess > target) {
                    System.out.println("Too High!");
                } else {
                    System.out.println("Too Low!");
                }
            }

            if (!win) {
                System.out.println("Out of attempts! The number was " + target + ".");
            }

            System.out.println("Current score: " + totalScore);
            playAgain = askPlayAgain(sc);

        } while (playAgain.equals("y"));

        System.out.println("            FINAL SCORE");
        System.out.println("Rounds played : " + roundsPlayed);
        System.out.println("Rounds won    : " + roundsWon);
        System.out.println("Total score   : " + totalScore);
        System.out.println("Thanks for playing!");

        sc.close();
    }

    private static int readGuess(Scanner sc, int attemptsLeft) {
        while (true) {
            System.out.print("Enter your guess (" + attemptsLeft + " left): ");
            try {
                int guess = sc.nextInt();
                sc.nextLine(); // Flush the leftover newline (avoids the "Scanner Trap")

                if (guess < MIN || guess > MAX) {
                    System.out.println("Please enter a number between " + MIN + " and " + MAX + ".");
                    continue;
                }
                return guess;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a whole number.");
                sc.nextLine(); // Discard the bad token so the loop doesn't spin forever
            }
        }
    }

    private static String askPlayAgain(Scanner sc) {
        while (true) {
            System.out.print("Play again? [Y/N]: ");
            String answer = sc.nextLine().trim().toLowerCase();
            if (answer.equals("y") || answer.equals("n")) {
                return answer;
            }
            System.out.println("Please type Y or N.");
        }
    }
}