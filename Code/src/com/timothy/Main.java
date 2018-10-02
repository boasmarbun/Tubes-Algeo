// update 2018_09_28_17.39

package com.timothy;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        // write your code here
        Scanner scan = new Scanner(System.in);

        int matriksBrs;
        int matriksKol;
        System.out.print("Masukan Jumlah Baris Matriks: ");
        matriksBrs = scan.nextInt();
        while (matriksBrs <= 0) {
            System.out.println("Input salah.");
            System.out.print("Masukan Jumlah Baris Matriks: ");
            matriksBrs = scan.nextInt();
        }
        System.out.print("Masukan Jumlah Kolom Matriks: ");
        matriksKol = scan.nextInt();
        while (matriksKol <= 0) {
            System.out.println("Input salah.");
            System.out.print("Masukan Jumlah Kolom Matriks: ");
            matriksKol = scan.nextInt();
        }
        double[][] matriks = new double[matriksBrs+1][matriksKol+1];
        inputMatriks(scan, matriks, matriksBrs, matriksKol);
        gaussjordan(matriks, matriksBrs, matriksKol);
        splgauss(matriks, matriksKol, matriksBrs);
        printMatriks(matriks, matriksBrs, matriksKol);
    }

    public static void inputMatriks(Scanner scan, double[][] matriks, int matriksBrs, int matriksKol) {
        System.out.println("Masukan Matriks");
        for (int i = 1; i <= matriksBrs; i++) {
            for (int j = 1; j <= matriksKol; j++) {
                matriks[i][j] = scan.nextInt();
            }
        }
    }

    public static void printMatriks(double[][] matriks, int matriksBrs, int matriksKol){
        for (int i = 1; i <= matriksBrs; i++)
        {
            for (int j=1; j <= matriksKol; j++)
            {
                System.out.print(matriks[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void tukerBrs(double[][] matriks, int matriksKol, int bar1, int bar2){
        double[] brstemp = new double[matriksKol+1];
        for (int i = 1; i<=matriksKol; i++){
            brstemp[i] = matriks[bar1][i];
            matriks[bar1][i] = matriks[bar2][i];
            matriks[bar2][i] = brstemp[i];
        }
    }

    public static void tukerKol(double[][] matriks, int matriksBrs, int kol1, int kol2){
        double[] koltemp = new double[matriksBrs];
        for (int i = 1; i<=matriksBrs; i++){
            koltemp[i] = matriks[i][kol2];
            matriks[i][kol1] = matriks[i][kol2];
            matriks[i][kol2] = koltemp[i];
        }
    }

    public static void gauss(double[][] matriks, int matriksBrs, int matriksKol) {
        double temp=0;
        boolean tuker,isfound;
        int brs;
        brs = 1;
        for(int i=1;i<=(matriksKol - 1);i++){
            // swap
            int l = i;
            tuker = false;
            while ((l <= matriksBrs) && !(tuker)) {
                if (matriks[l][i] != 0){
                    tukerBrs(matriks, matriksKol, i, l);
                    tuker = true;
                }
                l++;
            }
            if (tuker) {
                //Gauss
                for (int j = brs + 1; j <= matriksBrs; j++) {
                    temp = matriks[j][i];
                    for (int k = 1; k <= matriksKol; k++) {
                        matriks[j][k] = matriks[j][k] - ((matriks[brs][k]) * ((temp) / (matriks[brs][i])));
                    }
                }
                brs++;
            }
        }
        //Bagi supaya diagonal utama jadi 1
        for(int i=1;i<=matriksBrs;i++){
            isfound = false;
            for(int j=1;j<=matriksKol;j++){
                if(!isfound){
                    if(matriks[i][j]!=0){
                        isfound = true;
                        temp = matriks[i][j];
                    }
                }
                if(isfound) {
                    matriks[i][j] = matriks[i][j] / temp;
                }
            }
        }
    }

    public static void gaussjordan(double[][] matriks, int matriksBrs, int matriksKol){
        int index1; //Mencari index pertama ketemu 1
        double temp; //Menyimpan nilai perkalian
        boolean found1; //Apakah ada angka 1

        gauss(matriks, matriksBrs, matriksKol);

        for(int j=1; j<=matriksKol-1; j++){
            for(int i=1; i<=matriksBrs; i++){
                if(i!=j){
                    temp=matriks[i][j]/matriks[j][j];
                    for(int k=1; k<=matriksKol; k++){
                        matriks[i][k]=matriks[i][k]-temp*matriks[j][k];
                    }
                }
            }
        }
    }



    public static void splgauss(double[][] matriks,int matriksKol,int matriksBrs){
        //Matriks yang menjadi input adalah matriks augmented
        double[][] variabel = new double[matriksKol+1][2]; //Menyimpan nilai variabel
        double temp; //Menyimpan hasil penjumlahan
        int j,k,l; //Mencari angka yang bukan 0 dalam satu baris
        int count0; //index angka bukan 0 pertama
        int brs;

        //Inisialisasi array spl
        for(int i=1;i<=matriksKol;i++) {
            variabel[i][0] = 0; //0 jika parametrik, 1 jika non-parametriks
            variabel[i][1] = 0; //Value
        }

        //Penyelesaian
        for(int i=matriksBrs;i>=1;i--){
            temp=0;
            count0= 0;

            //Mencari angka diagonal utama
            k = 1;
            while ((count0 == 0) && (k<=matriksKol)) {
                if ((matriks[i][k]) != 0) {
                    count0++;
                }
                k++;
            }
            k--;
            if((count0>0) && (k != matriksKol)) { //Jika terdapatn nilai diagonal utama
                variabel[k][0] = 1;
                variabel[k][1] = matriks[i][matriksKol];
                j = matriksKol - 1;
                while (j > k) {
                    if (variabel[j][0] == 1) {
                        variabel[k][1] -= variabel[j][1] * matriks[i][j];
                    }
                    j--;
                }

            }
        }
        brs = 0;
        for(int i=1;i<=matriksKol-1;i++) {
            if (variabel[i][0] == 0){
                System.out.println("x" + i + "=" + "x" + i);
            }
            else {
                System.out.print("x" + i + "=" + variabel[i][1]);
                brs++;
                for (int m=1; m<=matriksKol-1; m++) {
                    if (variabel[m][0] == 0) {
                        if (matriks[brs][m] > 0) {
                            System.out.print("-" + matriks[brs][m] + "x" + m);
                        }
                        else if (matriks[brs][m] < 0) {
                            System.out.print(matriks[brs][m] + "x" + m);
                        }
                    }
                }
                System.out.println();
            }
        }

//            else { //variabel[j][0] == 0
//
//            }

//            if (variabel[k][1] > 0) {
//                System.out.println("+" + variabel[k][1]);
//            }
//            else if (variabel[k][1] < 0) {
//                System.out.println(variabel[k][1]);
//            }
    }
}