import java.util.ArrayList;

public class PriorityTechnique {
	public static void main(String[] args) {
		// Initialize an array list to use as a stack.
		ArrayList<Character> postfixStack = new ArrayList<Character>();
		String expression = "(2-3)*4";
		String postfix = "";

		// Convert the expression into its postfix form.
		// If the character is a digit, add it to the expression.
		// Otherwise, add it to the stack.
		// Pop the operators in the stack once the whole expression is traversed or if
		// an operator with greater priority is encountered.
		System.out.println("#####");
		System.out.println("# Converting Expression to Postfix Form");
		System.out.println("#####\n");

		System.out.println("Expression: " + expression + "\n");
		for (int i = 0; i < expression.length(); i++) {
			System.out.println("Current character: " + expression.charAt(i));
			System.out.println("Current stack: " + postfixStack);
			System.out.println("Current postfix expression: " + postfix);
			if (Character.isDigit(expression.charAt(i))) {
				postfix += Character.toString(expression.charAt(i));
			} else {
				// If there is a closed parenthesis, pop everything after the open parenthesis.
				if (expression.charAt(i) == ')') {
					while (postfixStack.get(postfixStack.size() - 1) != '(') {
						postfix += postfixStack.get(postfixStack.size() - 1);
						postfixStack.remove(postfixStack.size() - 1);
					}
					postfixStack.remove(postfixStack.size() - 1);
				} else {
					// Check if the operator being inputed has lesser priority than the top one.
					// If it does, pop the operator in the stack and add it to the postfix.
					// Add the current operator to the stack.
					if (postfixStack.size() > 0
							&& (postfixStack.get(postfixStack.size() - 1) == '*'
									|| postfixStack.get(postfixStack.size() - 1) == '/')
							&& (expression.charAt(i) == '+' || expression.charAt(i) == '-')) {
						postfix += postfixStack.get(postfixStack.size() - 1);
						postfixStack.remove(postfixStack.size() - 1);
					}
					postfixStack.add(expression.charAt(i));
				}
			}
			System.out.println();
		}

		// Pop the rest of the operators from the stack into the postfix.
		while (postfixStack.size() > 0) {
			System.out.println("Current stack: " + postfixStack);
			System.out.println("Current postfix expression: " + postfix);
			System.out.println();
			postfix += postfixStack.get(postfixStack.size() - 1);
			postfixStack.remove(postfixStack.size() - 1);
		}

		System.out.println("Resulting Postfix Expression: " + postfix + "\n");

		// Compute the value of the postfix expression.
		System.out.println("#####");
		System.out.println("# Computing its Value");
		System.out.println("#####\n");
		// Initialize a new array list with type Integer.
		// Traverse the postfix expression.
		// Add each integer in the postfix expression to the stack.
		// Once an operator is encountered, pop the two top integers and compute.
		// Add the result to the stack.
		ArrayList<Integer> computationStack = new ArrayList<Integer>();
		for (int i = 0; i < postfix.length(); i++) {
			System.out.println("Current character: " + postfix.charAt(i));
			System.out.println("Current stack: " + computationStack);
			if (Character.isDigit(postfix.charAt(i))) {
				computationStack.add(Character.getNumericValue(postfix.charAt(i)));
			} else {
				// Compute the operation on the top two integers.
				// Add the result to the stack.
				int second = computationStack.get(computationStack.size() - 1);
				computationStack.remove(computationStack.size() - 1);
				int first = computationStack.get(computationStack.size() - 1);
				computationStack.remove(computationStack.size() - 1);
				switch (postfix.charAt(i)) {
				case '*':
					computationStack.add(first * second);
					break;
				case '+':
					computationStack.add(first + second);
					break;
				case '-':
					computationStack.add(first - second);
					break;
				}
			}
			System.out.println();
		}

		// Pop the result from the stack.
		int computation = computationStack.get(computationStack.size() - 1);
		computationStack.remove(computationStack.size() - 1);

		System.out.println("Resulting Computation: " + computation + "\n");

		System.out.println("#####");
		System.out.println("# Overall Results");
		System.out.println("#####\n");
		System.out.println("Inputted Expression: " + expression);
		System.out.println("Postfix Expression: " + postfix);
		System.out.println("Resulting Computation: " + computation);
	}
}
