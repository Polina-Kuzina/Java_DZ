// волновой алгоритм

import java.util.*;
import java.lang.Math;


public class DZ5_Wave {

    public static void printDesk(int[][] arr) 
    {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.printf(arr[i][j] + " ");
            }
            System.out.println();
        }
    }     


    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите количество строк n: ");
        int n = in.nextInt();  
        System.out.print("Введите количество столбцов v: ");
        int v = in.nextInt();        
        int [][] desk = new int [n][v];
        System.out.print("Введите количество препятствий: ");
        int barriers = in.nextInt();         
        in.close();
       
        // координаты начальной точки
        int k1 = (int)(Math.random()*n);
        int m1 = (int)(Math.random()*v);
        System.out.println("Координаты начальной точки: " + k1 + ";" + m1);
        int k2 = (int)(Math.random()*n);
        int m2 = (int)(Math.random()*v);        
        System.out.println("Координаты конечной точки: " + k2 + ";" + m2);
        // препятствия
        int [] barriersX = new int[barriers];
        int [] barriersY = new int[barriers];
        for (int i = 0; i < barriers; i++) {
            barriersX[i] = (int)(Math.random()*n);
            barriersY[i] = (int)(Math.random()*v);
        }
        System.out.println("Координаты препятствий: ");
        for (int i = 0; i < barriers; i++) {
            System.out.print(barriersX[i] + ";" + barriersY[i] + " ");
        }
        // начальная карта
        desk[k1][m1] = -1000;
        desk[k2][m2] = 1000;
        for (int i = 0; i < barriers; i++) {
            desk[barriersX[i]][barriersY[i]] = -1;
        }
        System.out.println("\nНачальная карта");
        printDesk(desk);

        //процесс
        int [] stepI = new int [] {0, -1, 1, 0};
        int [] stepJ = new int [] {-1, 0, 0, 1};        
        ArrayList<Integer> CountsI = new ArrayList<Integer>();
        ArrayList<Integer> CountsJ = new ArrayList<Integer>();
        CountsI.add(k1);
        CountsJ.add(m1);
        int count = 0;
        boolean b = false;
        while (b == false && count < n*v) {            
            int size = CountsI.size();
            // System.out.println(size);
            count += 1;
            for (int l = 0; l < size; l++) {
                if (b == false) {
                    int x = CountsI.get(l);
                    int y = CountsJ.get(l);                    
                    for (int i = 0; i < 4; i++) {
                        if (x+stepI[i] >= 0 && x+stepI[i] < n && y+stepJ[i] >=0 && y+stepJ[i] < v) {
                            if (desk[x+stepI[i]][y+stepJ[i]] == 1000) {
                                b = true; 
                                i = 4;
                                l = size;                          
                            }
                            else if (desk[x+stepI[i]][y+stepJ[i]] == 0) {
                                desk[x+stepI[i]][y+stepJ[i]] = count;
                                CountsI.add(x+stepI[i]);
                                CountsJ.add(y+stepJ[i]);
                                // System.out.println(CountsI);
                                // System.out.println(CountsJ);
                            }                
                        }
                    }                           
                }
            }
            for (int l = 0; l < size; l++) {
                CountsI.remove(0);
                CountsJ.remove(0);                
            }            
        }

        System.out.println(count);
        if (count > 0) {
            System.out.println("\nМинимальное количество ходов до конечной точки " + count);
        } else System.out.println("\nНе возможно добраться до конечной точки ");
        printDesk(desk);
    }
}
