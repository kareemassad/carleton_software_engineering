import java.util.Map;

class InfixCalculator { 
    private String input;
    private StackListBased<Integer> stack;

    /**
     *Construct InfixCalculator and instance input
     * @param input The postFix convert to infix and solve
     */
    InfixCalculator(String input) {
        this.input = input;
        stack = new StackListBased<>();
    }

    /**
     * This takes a certain operation such as 2+3 in String format and returns 5
     * 
     * @param operator of the operation
     * @param num2  second number in operation
     * @param num1  first number in operation
     * @return  result of the certain operation, case specific
     */
    private static int selectUseOperator(String operator, int num2, int num1) {
        // if operator given then use it and return result
        switch (operator) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                return num1 / num2;
            default:
                return 0;
        }
    }
    /**
     * 
     * @param infix
     * @return
     */
    private String infixToPostfix(String infix) {
        //Map for precedence of operation
        Map<String, Integer> precedence = Map.of("+", 1, "-", 1, "*", 2, "/", 2);
        StringBuilder postFix = new StringBuilder();
        StackListBased<String> stackOfOperators = new StackListBased<>();
        //Remove white spaces and unwanted chars then reorganize
        String[] tokenize = infix.replaceAll("\\s+", "").split("(?=[-+*/()])|(?<=[-+*/()])");
        for (String token : tokenize) {
            //take token that is a number then append it to prefix for parsing
            if (token.matches("\\d+")) {
                postFix.append(token);
                postFix.append(" ");
                //exclude open brakcets > push to top stack
            } else if (token.equals("(")) {
                stackOfOperators.push(token);
                //exclude close and make sure to find next opening brace 
            } else if (token.equals(")")) {
                while (!stackOfOperators.peek().equals("(")) {
                    postFix.append(stackOfOperators.pop());
                    postFix.append(" ");
                }
                stackOfOperators.pop();
                //if token is an operator, sort by precedence
            } else if (token.matches("[-+*/]")) {
                while (!stackOfOperators.isEmpty() && !stackOfOperators.peek().equals("(") && (precedence.get(token) <= precedence.get(stackOfOperators.peek()))) {
                    postFix.append(stackOfOperators.pop());
                    postFix.append(" ");
                }
                stackOfOperators.push(token);
            }
        }
        //Clear operator stack into postfix
        while (!stackOfOperators.isEmpty()) {
            postFix.append(stackOfOperators.pop());
            postFix.append(" ");
        }
        postFix.deleteCharAt(postFix.lastIndexOf(" "));
        return postFix.toString();
    }

    /**
     * 
     * @param postFix
     * @return if token is a number put it to the stack otherwise pop it
     */
    private int getPostfix(String postFix) {
        for (String token : postFix.split(" ")) {
            if (token.matches("\\d+")) {
                stack.push(Integer.parseInt(token));
            } else {    
                //NOTE: recursive call
                stack.push(selectUseOperator(token, stack.pop(), stack.pop()));
            }
        }
        return stack.pop();
    }

    /**
     * Print and run the results of infixToPostfix and getPostfix.
     */
    void evaluateInfix() {
        System.out.println("infix: " + input);
        input = infixToPostfix(input);
        System.out.println("postfix: " + input);
        System.out.println("result: " + getPostfix(input));
    }
}