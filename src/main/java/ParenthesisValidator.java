import java.util.Map;
import java.util.Stack;

public class ParenthesisValidator {
    public boolean checkBalancedParenthesis(String s){

        Stack<Character> stringPar = new Stack<>();
        Map<Character,Character> parTypes = Map.ofEntries(
                Map.entry(']','['),
                Map.entry('}', '{'),
                Map.entry(')', '(')
        );


        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if (parTypes.keySet().contains(c)){
                if (stringPar.empty() || stringPar.pop() != parTypes.get(c)){
                    return false;
                }
            }
            else if (parTypes.values().contains(c)){
                stringPar.push(c);
            }
        }

        return stringPar.empty();

    }
}
