import java.util.ArrayList;

public class LanguageRecognizerG {

    String userInput = "";
    ArrayList<Character> CharacterList = new ArrayList<Character>();
    int initSize;

    LanguageRecognizerG(String input) {
        userInput = input;
        for (char c : input.toCharArray()) {
            CharacterList.add(c);
        }
        initSize = CharacterList.size();
        // ListIterator WordIterating = CharacterList.listIterator(0);
    }

    /**
     * 
     * @param condList 
     * @return true when pattern is detected
     */
    private boolean recursiveRecogR(ArrayList<Character> condList) {
        // empty string condition
        if (condList.size() == 0) {
            return true;
        }
        // & or # condition
        if (condList.size() == 1) {
            if (condList.get(0) == '&' | condList.get(0) == '#') {
                return true;
            }
        }
        // W|A and &|# condition and order doesnt matter
        if (condList.size() == 2) {
            if (condList.get(0) == 'W' | condList.get(0) == 'A') {
                if (condList.get(1) == '&' | condList.get(1) == '#') {
                    return true;
                }
            }
            if (condList.get(0) == '&' | condList.get(0) == '#') {
                if (condList.get(1) == 'W' | condList.get(1) == 'A') {
                    return true;
                }
            }
        }
        // &|# <G> W|A recursive case
        if (condList.size() > 2) {
            if (condList.get(0) == '&' | condList.get(0) == '#') {
                if (condList.get(condList.size() - 1) == 'W' | condList.get(condList.size() - 1) == 'A') {
                    ArrayList fixList = (ArrayList) condList.subList(1, condList.size() - 2);
                    if (recursiveRecogR(fixList)) {
                        return true;
                    }

                }

            }
        }
        return false;
    }

    // prof said to use this to print
    /**
     * Used to recursively print the result to avoid 
     */
    public void recursivePrintG() {
        boolean checker = recursiveRecogR(CharacterList);
        if (checker) {
            System.out.println("Recusion: Word \"" + userInput + "\" Is a word of the G language. \n \n");
        } else {
            System.out.println("Recusion: Word \"" + userInput + "\" Is NOT a word of the G language.");
        }
    }

}
