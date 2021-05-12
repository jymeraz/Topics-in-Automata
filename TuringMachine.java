
public class TuringMachine {

	public static void main(String[] args) {
		// Example used to test the Turing machine.
		// For simplicity, used the Automaton from HW 4 to create rules for the Turing
		// machine.
		int N = 5;
		int M = 3;
		int[][] state = new int[N - 2][M];
		int[][] symbol = new int[N][M];
		char[][] lr = new char[N][M];

		// Populating the state array.
		for (int i = 0; i < state.length; i++) {
			for (int j = 0; j < state[i].length; j++) {
				if ((i == 0 && j == 0) || (i == 1 && j == 1) || (i == 2 && (j == 1 || j == 2))) {
					state[i][j] = 1;
				} else if (i == 1 && j == 2) {
					state[i][j] = 2;
				} else if (i == 1 && j == 0) {
					state[i][j] = 3;
				} else if (i == 2 && j == 0) {
					state[i][j] = 4;
				}
			}
		}

		// Populating the symbol array.
		for (int i = 0; i < symbol.length; i++) {
			for (int j = 0; j < symbol[i].length; j++) {
				symbol[i][j] = j;
			}
		}

		// Populating the lr array.
		for (int i = 0; i < lr.length; i++) {
			for (int j = 0; j < lr[i].length; j++) {
				if ((i == 0 && j == 0) || (i == 1 && (j == 1 || j == 2)) || (i == 2 && (j == 1 || j == 2))) {
					lr[i][j] = 'R';
				}
			}
		}

		// Populating the tape.
		// Tested an accepted word: pqp.
		// Tested a rejected word: pqpq.
		int[] tape = new int[20];
		// - p q p
		tape[0] = 0;
		tape[1] = 1;
		tape[2] = 2;
		tape[3] = 1;
		// tape[4] = 2;

		// Print the results of the Turing machine emulation method.
		System.out.println(check(N, M, tape, symbol, state, lr));

	}

	// Method emulating a general Turing machine.
	// Keeps track of the location of the head.
	public static Boolean check(int N, int M, int[] tape, int[][] symbol, int[][] state, char[][] lr) {
		int location = 0;
		int head = 0;
		int currentSymbol;
		while (head < N - 2) {
			currentSymbol = tape[location];
			tape[location] = symbol[head][currentSymbol];
			int newHead = state[head][currentSymbol];
			if (lr[head][currentSymbol] == 'R') {
				location++;
			} else if (lr[head][currentSymbol] == 'L') {
				location--;
			}
			head = newHead;
		}
		if (head == N - 2) {
			return true;
		}
		return false;
	}
}
