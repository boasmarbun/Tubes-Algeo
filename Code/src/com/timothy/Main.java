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
        gauss(matriks, matriksBrs, matriksKol);
        splgauss(matriks, matriksBrs, matriksKol);
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

        for(int i=1;i<=matriksKol;i++){
            // swap
            int l = i;
            tuker = false;
            while ((l <= matriksBrs) && !(tuker)){
                if (matriks[l][i] != 0){
                    tukerBrs(matriks, matriksKol, i, l);
                    tuker = true;
                }
                l++;
            }
            //Gauss
            for(int j=i+1;j<=matriksBrs;j++){
                temp=matriks[j][i];
                for(int k=1;k<=matriksKol;k++){
                    matriks[j][k] = matriks[j][k] - ((matriks[i][k]) * ((temp) / (matriks[i][i])));
                }
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
                if(isfound){
                    matriks[i][j] = matriks[i][j] / temp;
                }
            }
        }
    }

    public static void gaussjordan(double[][] matriks, int matriksBrs, int matriksKol){
        double temp;

        gauss(matriks, matriksBrs, matriksKol);
        //atas segitiga atas jadi 0
        for(int i=(matriksKol - 1);i>=1;i--){
            //Gauss
            for(int j=matriksBrs;j>=i+1;j--){
                temp=matriks[j][i];
                for(int k=1;k<=matriksKol;k++){
                    matriks[j][k] = matriks[j][k] - ((matriks[i][k]) * ((temp) / (matriks[i][i])));
                }
            }
        }
    }



    public static void splgauss(double[][] matriks,int matriksKol,int matriksBrs){
        //Matriks yang menjadi input adalah matriks augmented
        double[] variabel = new double[matriksKol+1]; //Menyimpan nilai variabel
        int temp; //Menyimpan hasil penjumlahan
        int k; //Mencari angka yang bukan 0 dalam satu baris
        int count0; //index angka bukan 0 pertama

        //Inisialisasi array spl
        for(int i=1;i<=matriksKol;i++) {
            variabel[i] = 0;
        }

        //Penyelesaian
        for(int i=matriksBrs;i>=1;i--){
            temp=0;
            count0= 0;

            //Mencari angka diagonal utama
            for(k=1;k<=matriksBrs;k++) {
                if ((matriks[i][k]) != 0) {
                    count0++;
                    break;
                }
            }
            if(count0>0) { //Jika terdapatn nilai diagonal utama
                variabel[k] = matriks[i][k]; //Simpan nilai diagonal utama dalam array variabel
                for(int j=matriksKol-1;j>=1;j--) {
                    temp += variabel[j] * matriks[i][j];
                }
                variabel[k] = matriks[i][matriksKol] / temp; //Simpan nilai variabel yang benar
            }
        }

        for(int i=1 ;i<=matriksKol;i++){
            System.out.println(variabel[i]);
        }
    }
}