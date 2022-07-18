// Задано уравнение вида q + w = e, q, w, e >= 0. 
// Некоторые цифры могут быть заменены знаком вопроса, например 2? + ?5 = 69. 
// Требуется восстановить выражение до верного равенства. 
// Предложить хотя бы одно решение или сообщить, что его нет.

import java.util.ArrayList;
import java.lang.String;


public class DZ6_equality {

    public static boolean isDigit(String s) throws NumberFormatException {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public static void main(String args[]) {
        String s = "2?6 + 2? = 3?6";
        s = s.trim().replace(" ", "");
        s = s.trim().replace("+", " ");
        s = s.trim().replace("=", " "); 
        String[] Str = s.split(" ");      
        // System.out.println(Arrays.toString(Str));  

        ArrayList<Integer> placeVopros = new ArrayList<Integer>();
        ArrayList<Integer> placeNumbers = new ArrayList<Integer>();
        for (int j = 0; j < Str.length; j++) {
            int k = 0;
            int x = 0;
            String v =  Str[j];           
            for (int i = 0; i < v.length(); i++) {
                String[] vS = v.split("");           
                if (isDigit(vS[i])) {
                    k = k + (Integer.parseInt(vS[i])*(int)Math.pow(10, v.length()-1-i));                
                }
                else {
                    x = (int)Math.pow(10, v.length()-1-i);
                }
            }
            if (x > 0) placeVopros.add(x);
            else placeVopros.add(0);
            placeNumbers.add(k);
        }

        int count = 0; 
        if ((placeNumbers.get(2) - placeNumbers.get(0)) < 0 ||  (placeNumbers.get(2) - placeNumbers.get(1)) < 0) System.out.println("Решения нет");      
        else {  
            for (int x = 0; x < 10; x++) {
                for (int y = 0; y < 10; y++) {
                    for (int z = 0; z < 10; z++) {
                        if ((placeNumbers.get(0) + x * placeVopros.get(0)) + (placeNumbers.get(1) + y * placeVopros.get(1)) == placeNumbers.get(2) + z * placeVopros.get(2)) {
                            System.out.println("Вариант решения: " + (placeNumbers.get(0) + x * placeVopros.get(0)) + " + " + (placeNumbers.get(1) + y * placeVopros.get(1)) + " = " + (placeNumbers.get(2) + z * placeVopros.get(2)));
                            count++;
                            x = 10;
                            y = 10;
                            z = 10;              
                        }
                    }
                }
            }   
        if (count == 0) System.out.println("Решения нет");
        }
    }

}
