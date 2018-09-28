// update 2018_09_28_17.39

package com.timothy;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        // write your code here
        Scanner scan = new Scanner(System.in);
        int matriksBrs;
        int matriksKol;
        matriksBrs = matriksKol = 0;
        getBrs(scan, matriksBrs);
        getKol(scan, matriksKol);
        int[][] matriks = new int[matriksBrs+1][matriksKol+1];
        inputMatriks(scan, matriks, matriksBrs, matriksKol);
        printMatriks(matriks, matriksBrs, matriksKol);
    }

    public static void getBrs(Scanner scan, int matriksBrs) {
        System.out.print("Masukan Jumlah Baris Matriks: ");
        matriksBrs = scan.nextInt();
        while (matriksBrs <= 0) {
            System.out.println("Input salah.");
            System.out.print("Masukan Jumlah Baris Matriks: ");
            matriksBrs = scan.nextInt();
        }
    }

    public static void getKol(Scanner scan, int matriksKol) {
        System.out.print("Masukan Jumlah Kolom Matriks: ");
        matriksKol = scan.nextInt();
        while (matriksKol <= 0) {
            System.out.println("Input salah.");
            System.out.print("Masukan Jumlah Kolom Matriks: ");
            matriksKol = scan.nextInt();
        }
    }

    public static void inputMatriks(Scanner scan, int[][] matriks, int matriksBrs, int matriksKol) {
        System.out.println("Masukan Matriks");
        for (int i = 1; i <= matriksBrs; i++) {
            for (int j = 1; j <= matriksKol; j++) {
                matriks[i][j] = scan.nextInt();
            }
        }
    }

    public static void printMatriks(int[][] matriks, int matriksBrs, int matriksKol){
        for (int i = 1; i <= matriksBrs; i++)
        {
            for (int j=1; j <= matriksKol; j++)
            {
                System.out.print(matriks[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void tukerBrs(int[][] matriks, int matriksKol, int bar1, int bar2){
        int[] brstemp = new int[matriksKol+1];
        for (int i = 1; i<=matriksKol; i++){
            brstemp[i] = matriks[bar1][i];
            matriks[bar1][i] = matriks[bar2][i];
            matriks[bar2][i] = brstemp[i];
        }
    }

    public static void tukerKol(int[][] matriks, int matriksBrs, int kol1, int kol2){
        int[] koltemp = new int[matriksBrs];
        for (int i = 1; i<=matriksBrs; i++){
            koltemp[i] = matriks[i][kol2];
            matriks[i][kol1] = matriks[i][kol2];
            matriks[i][kol2] = koltemp[i];
        }
    }

    public static void gauss(){


    }


}