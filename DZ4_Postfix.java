import java.util.*;
import java.lang.Math;
// import java.util.Stack;

public class DZ4_Postfix {

    public static boolean isDigit(String s) throws NumberFormatException {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static int priority(String s) {
        int p = 0;
        switch (s) {
            case "^": 
                p = 3;
                break;
            case "/": 
                p = 2;
                break;
            case "*": 
                p = 2;
                break;
            case "+": 
                p = 1;
                break;
            case "-": 
                p = 1;
                break;
            case "(": 
                p = 0;
                break;
            case "Sin": 
                p = 1;
                break;
            case "Pi": 
                p = 1;
                break;
        } 
        return p;       
    }

    public static void main(String args[])
    {
        Stack<String> stack = new Stack<>();
        Queue<String> queue = new LinkedList<>();          
        var str = "( 2 ^ 3 * ( 10 / ( 5 - 3 ) ) ) ^ ( Sin ( Pi ) )".split(" ");
        // var str = "2 ^ 3 * ( 10 / ( 5 - 3 ) ) ".split(" ");         
        // var str = "( 2 + 4 ) * 4 ^ 2".split(" ");    
        System.out.println("Инфиксное выражение: + ( 2 ^ 3 * ( 10 / ( 5 - 3 ) ) ) ^ ( Sin ( Pi ) )");
        
        for (int i = 0; i < str.length; i++) { 
                      
            if (isDigit(str[i])) queue.add(str[i]);  
            else if (str[i].equals("(")) stack.push(str[i]);
            else if (str[i].equals(")")) {
                    while (stack.size() > 0) {
                        if (priority(stack.peek()) > 0) queue.add(stack.pop());
                        else {
                            stack.pop();
                            break;                         
                        }
                    }
                }                          
            else {
                if (stack.empty()) stack.push(str[i]); 
                else if (stack.peek().equals("(")) stack.push(str[i]);                
                else if (priority(str[i]) > priority(stack.peek())) stack.push(str[i]);
                else {
                    while (stack.size() > 0 && priority(str[i]) <= priority(stack.peek())) {
                        if (priority(stack.peek()) > 0) queue.add(stack.pop());
                        else {
                            stack.pop();
                            break;                         
                        }
                    }
                    stack.push(str[i]);  
                }               
                  
            }
        
        }    
        while (stack.empty() == false) {
            queue.add(stack.pop());
        }
        System.out.println("Постфиксная запись выражения ");
        // System.out.println(queue);
        for (String item: queue) {
            System.out.print(item + " ");
        }
        System.out.println();
        int res = 0;
               
        Stack<Integer> st = new Stack<>();
        for (String item: queue) {

            if (isDigit(item)) {
                st.push(Integer.parseInt(item));
            } else {
                switch (item) {
                    case "+":
                        res = st.pop() + st.pop();
                        st.push(res);
                        break;
                    case "-":
                        res = -st.pop() + st.pop();
                        st.push(res);
                        break;
                    case "*":
                        res = st.pop() * st.pop();
                        st.push(res);                        
                        break;
                    case "/":
                        res =  st.pop();
                        res =  st.pop() / res;
                        st.push(res);
                        break;
                    case "^":
                        res =  st.pop();
                        res = (int)(Math.pow(st.pop(), res));
                        st.push(res);
                        break;
                    case "Pi":
                        res = (int)Math.PI;                        
                        st.push(res);                        
                        break;                        
                    case "Sin":                      
                        st.push((int)Math.sin((int)st.pop()));                        
                        break;
                    default:
                        break;
                }
            }
        }
        System.out.println("\nРешение: ");
        if (st.size() > 0) System.out.println(st.pop());
        
    }
}
