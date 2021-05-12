import java.util.Scanner;

public class Simulation {
  public static void main(String[] args) {
    Scanner reader = new Scanner(System.in);
    // Prompt the user to enter the number of states and the number of symbols.
    System.out.print("Please enter the number of states: ");
    int N = reader.nextInt();
    System.out.print("Please enter the number of symbols: ");
    int M = reader.nextInt();

    // Create an int array to describe the possible transitions.
    // Prompt the user what state to go to given a state and a symbol.
    int[][] state = new int[N][M];
    for (int n = 0; n < N; n++) {
      for (int m = 0; m < M; m++) {
        System.out.print(
            "What state do you want to move to "
                + "if you are in state "
                + n
                + " and you read the symbol "
                + m
                + "? ");
        state[n][m] = reader.nextInt();
      }
    }

    // Create a boolean array to describe whether a state is final or not.
    // Prompt the user if the current state is a final state.
    boolean[] finalStates = new boolean[N];
    for (int n = 0; n < N; n++) {
      System.out.print("Is the state " + n + " a final state: Y/N ");
      String userResponse = reader.next().toUpperCase();
      if (userResponse.equals("Y")) {
        finalStates[n] = true;
      }
    }

    // Prompt the user to input a word.
    // Create an integer array to save the word in.
    System.out.print("Please enter a word: ");
    String wordInput = reader.next();
    int[] word = new int[wordInput.length()];
    for (int i = 0; i < wordInput.length(); i++) {
      word[i] = Character.getNumericValue(wordInput.charAt(i));
    }
    // Call the method to simulate the automaton.
    automatonSimulation(N, M, state, finalStates, word);
  }

  public static void automatonSimulation(
      int N, int M, int[][] state, boolean[] finalStates, int[] word) {
    // Iterate through the word.
    // Update the current state after each symbol in the word.
    int currentState = 0;
    for (int i = 0; i < word.length; i++) {
      currentState = state[currentState][word[i]];
    }
    // Print that the word is accepted if the current state is a final state.
    if (finalStates[currentState]) {
      System.out.println("The word is accepted.");
    } else {
      System.out.println("The word is rejected.");
    }
  }
}
