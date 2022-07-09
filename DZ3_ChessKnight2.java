// Шахматную доску размером NxN обойти конём так, чтобы фигура в каждой клетке была строго один раз.
// Вариант без отката, по наименьшему варианту ходов (Правило Варнсдорфа)

import java.util.Scanner;
import java.util.ArrayList;

public class DZ3_ChessKnight2 {

    public static void fillDesk(int[][] arr) 
    {
        int k = 1;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                arr[i][j] = k + j;                
            }
            k += arr[0].length;
        }
    }

    public static void printDesk(int[][] arr) 
    {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.printf(arr[i][j] + " ");
            }
            System.out.println();
        }
    }                  

    public static void main(String args[])
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите число n: ");
        int n = in.nextInt();        
        int [][] desk = new int [n][n];
        System.out.print("Задайте координаты первой точки\nстрока i = ");
        int k = in.nextInt();
        System.out.print("столбец j = ");
        int m = in.nextInt();
        in.close();

        fillDesk(desk);
        printDesk(desk);

        int [] moveI = new int [] {-2, -2, -1, -1, 1, 1, 2, 2};
        int [] moveJ = new int [] {-1, 1, -2, 2, -2, 2, -1, 1};
        ArrayList<Integer> listNumber = new ArrayList<Integer>();
        
        listNumber.add(desk[k][m]);
        // System.out.println(listNumber);
        desk[k][m] = 0;
        

        for (int l = 1; l < n*n; l++) { 
            ArrayList<Integer> listVariants = new ArrayList<Integer>();                    
            for (int f = 0; f < 8; f++) {            
                if (k+moveI[f] >= 0 && k+moveI[f] <= n-1 && m+moveJ[f] >= 0 && m+moveJ[f] <= n-1) {
                    if (desk[k+moveI[f]][m+moveJ[f]] != 0) {
                        listVariants.add(f);                                                   
                    }                                      
                }                             
            }
            // System.out.println(listVariants);
            ArrayList<Integer> listCounts = new ArrayList<Integer>();             
            for (int v = 0; v < listVariants.size(); v++) {
                int count = 0;
                int i = listVariants.get(v);
                for (int f = 0; f < 8; f++) {                    
                    if (k+moveI[i]+moveI[f] >= 0 && k+moveI[i]+moveI[f] <= n-1 && m+moveJ[i]+moveJ[f] >= 0 && m+moveJ[i]+moveJ[f] <= n-1) {
                        if (desk[k+moveI[i]+moveI[f]][m+moveJ[i]+moveJ[f]] != 0) {
                            count++;                                                   
                        } 
                    }                                        
                }
                listCounts.add(count);                            
            }
            // System.out.println(listCounts);
            if (listCounts.size() > 0) {
                int fmin = listCounts.get(0);
                int indFmin = 0;            
                for (int i = 0; i < listCounts.size(); i++) {
                    if(listCounts.get(i) < fmin) {
                        fmin = listCounts.get(i);
                        indFmin = i; 
                    }               
                }
                listNumber.add(desk[k+moveI[listVariants.get(indFmin)]][m+moveJ[listVariants.get(indFmin)]]);
                desk[k+moveI[listVariants.get(indFmin)]][m+moveJ[listVariants.get(indFmin)]] = 0;
                k = k+moveI[listVariants.get(indFmin)];
                m = m+moveJ[listVariants.get(indFmin)];
            }
            // System.out.println(listNumber);            
        }        
        System.out.println("Последовательность ходов: " + listNumber);
        // System.out.println(listNumber.size());
    }
}
