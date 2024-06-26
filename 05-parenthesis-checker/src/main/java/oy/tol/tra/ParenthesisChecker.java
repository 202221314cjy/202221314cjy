package oy.tol.tra;

/**
 * Uses the StackInterface implementation to check that parentheses in text files
 * match. 
 * <p>
 * TODO: Students, implement the checkParentheses() method using your StackImplementation
 * to check if parentheses in the two test files match or not.
 * <p>
 * NOTE: The Person.json test file has an error, but the tests expect that. So the test will 
 * not fail with that file -- the erroneus json file is _expected_.
 * <p>
 * Note that you do not have to instantiate this class anywhere. The ParenthesisTests:
 * <ul>
 *  <li>Constructs your implementation of the {@code StackImplementation<Character>}, and
 *  <li>Calls ParenthesisChecker.checkParentheses.
 * </ul>
 * So your job is just to have a working StackImplementation and implement the ParenthesisChecker.checkParentheses.
 * Then execute the ParenthesisTests.
 */
public class ParenthesisChecker {

    private ParenthesisChecker() {
    }

    /**
     * TODO: Implement this function which checks if the given string has matching opening and closing
     * parentheses. It should check for matching parentheses:<p>
     *  <code>Lorem ipsum ( dolor sit {  amet, [ consectetur adipiscing ] elit, sed } do eiusmod tempor ) incididunt ut...</code>,
     * <p>
     * which can be found for example in Java source code and JSON structures.
     * <p>
     * If the string has issues with parentheses, you should throw a {@code ParenthesisException} with a
     * descriptive message and error code. Error codes are already defined for you in the ParenthesesException
     * class to be used.
     * <p>
     * What is to be tested:
     * <ul>
     *  <li>when an opening parenthesis is found in the string, it is successfully pushed to the stack.
     *  <li>when a closing parenthesis is found in the string, a matching opening parenthesis is popped from the stack.
     *  <li>when popping a parenthesis from the stack, it must not be null. Otherwise string has too many closing parentheses.
     *  <li>when the string has been handled, and if the stack still has parentheses, there are too few closing parentheses.
     * </ul>
     * @param stack The stack object used in checking the parentheses from the string.
     * @param fromString A string containing parentheses to check if it is valid.
     * @return Returns the number of parentheses found from the input in total (both opening and closing).
     * @throws ParenthesesException if the parentheses did not match as intended.
     * @throws StackAllocationException If the stack cannot be allocated or reallocated if necessary.
     */
    public static int checkParentheses(StackInterface<Character> stack, String fromString) throws ParenthesesException {
        int count = 0;
        // Convert the input string to a character array
        char[] charArray = fromString.toCharArray();
        // Iterate over each character in the string
        for (int i = 0; i < charArray.length; i++) {
            char ch = charArray[i];
            // Check if the current character is an opening parenthesis
            if (isOpenParenthesis(ch)) {
                stack.push(ch);
                count++;
            }
            // Check if the current character is a closing parenthesis
            else if (isCloseParenthesis(ch)) {
                // If the stack is empty, there are too many closing parentheses
                if (stack.isEmpty()) {
                    throw new ParenthesesException("too many closing parentheses", ParenthesesException.TOO_MANY_CLOSING_PARENTHESES);
                }
                //Pop the top of the stack and check if it matches the current closing parenthesis
                char top = stack.pop();
                if (!isMatch(top, ch)) {
                    throw new ParenthesesException("Wrong kind of parenthesis", ParenthesesException.PARENTHESES_IN_WRONG_ORDER);
                }
                count++;
            }
        }
        // If the stack is not empty, there are too few closing parentheses
        if (!stack.isEmpty()) {
            throw new ParenthesesException("The string has more opening than closing parentheses.", ParenthesesException.TOO_FEW_CLOSING_PARENTHESES);
        }
        // Return the total count of parentheses encountered
        return count;
    }
    //Checks if the given character is an opening parenthesis.
    private static boolean isOpenParenthesis(char ch) {
        return ch == '(' || ch == '[' || ch == '{';
    }
    //Checks if the given character is a closing parenthesis.
    private static boolean isCloseParenthesis(char ch) {
        return ch == ')' || ch == ']' || ch == '}';
    }
    //Checks if the given opening and closing parentheses match.
    private static boolean isMatch(char open, char close) {
        return (open == '(' && close == ')') ||
                (open == '[' && close == ']') ||
                (open == '{' && close == '}');
    }
}
