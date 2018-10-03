// update 2018_09_28_17.39

package com.timothy;

import java.awt.event.ActionEvent;
import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
    public static void main(String[] args) throws Exception{
        // write your code here
        double[][] matriks = new double[1000][1000];
        int matriksBrs;
        int matriksKol;
        Scanner scan = new Scanner(System.in);

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
        double[][] variabel = new double[matriksKol+1][2];
        inputMatriks(scan, matriks, matriksBrs, matriksKol);
        Interpolasi(matriks, matriksBrs, variabel);
//        gaussjordan(matriks, matriksBrs, matriksKol);
//        splgauss(matriks, matriksBrs, matriksKol);
//        printMatriks(matriks, matriksBrs, matriksKol);
//        System.out.println("MENU");
//        System.out.println("1. Sistem Persamaan Linear");
//        System.out.println("2. Interpolasi Polinom");
//        System.out.println("3. Keluar");
//        int user_choice1, user_choice2, user_choice3; // user_choice1 = pilihan menu; user_choice2 = pilihan metode;
//        // user_choice3 = pilihan input
//
//        user_choice1 = scan.nextInt();
//        while (user_choice1 != 1 && user_choice1 != 2 && user_choice1 != 3) {
//            System.out.println("Input salah, masukan input lagi.");
//            user_choice1 = scan.nextInt();
//        }
//        while (user_choice1 != 3){
//            System.out.println("Pilihan metode :");
//            System.out.println("1. Metode eliminasi Gauss");
//            System.out.println("2. Metode eliminasi Gauss-Jordan");
//            user_choice2 = scan.nextInt();
//            while (user_choice2 != 1 && user_choice2 != 2) {
//                System.out.println("Input salah, masukan input lagi.");
//                user_choice2 = scan.nextInt();
//            }
//            System.out.println("Pilihan metode input :");
//            System.out.println("1. Keyboard");
//            System.out.println("2. Text file");
//            user_choice3 = scan.nextInt();
//            while (user_choice3 != 1 && user_choice3 != 2) {
//                System.out.println("Input salah, masukan input lagi.");
//                user_choice3 = scan.nextInt();
//            }
//            if ((user_choice1 == 1) && (user_choice3 == 1)) { // SPL, baca dari keyboard
//                int m, n, i, j; // m = jmlh baris, n = jumlah kolom, i = index baris, j = index kolom;
//                System.out.println("Masukkan jumlah baris matriks");
//                m = scan.nextInt();
//                while (m < 1) {
//                    System.out.println("Input salah, masukkan input lagi");
//                    m = scan.nextInt();
//                }
//                System.out.println("Masukkan jumlah kolom matriks");
//                n = scan.nextInt();
//                while (n < 1) {
//                    System.out.println("Input salah, masukkan input lagi");
//                    n = scan.nextInt();
//                }
//                System.out.print("Baris :");
//                System.out.println(m);
//                System.out.print("Kolom :");
//                System.out.println(n);
//                System.out.println("Masukkan elemen matriks");
//                for (i = 1; i <= m; i++) {
//                    for (j = 1; j <= n; j++) {
//                        matriks[i][j] = scan.nextDouble();
//                    }
//                }
//            }
//            if ((user_choice1 == 1) && (user_choice3 == 2)) { // SPL, baca dari file external
//                matriksBrs = 0;
//                matriksKol = 0;
//                //FileToMatriks(matriks, matriksBrs, matriksKol);
//
//                BufferedReader br = new BufferedReader(new FileReader("test.txt"));
//                int i;
//                i = 1;
//                int j;
//                j = 1;
//                double bil;
//                // int matriksBrs;
//                // int matriksKol;
//                // double[][] matrix = new double[1000][1000];
//                String line;
//                String text = "";
//                Scanner scan1 = new Scanner(new BufferedReader(new FileReader("test.txt")));
//                scan.useLocale(Locale.US);
//                line = br.readLine();
//                while (line != null) {
//                    text += line;
//                    // System.out.println(scan.hasNext());
//                    if (scan1.hasNext()) {
//                        // System.out.print(text);
//                        // System.out.flush();
//                    }
//                    while (scan1.hasNextDouble()) {
//                        // System.out.print(scan.nextDouble());
//                        bil = scan1.nextDouble();
//                        matriks[i][j] = bil;
//                        // System.out.println(matrix[i][j]);
//                        j++;
//                    }
//                    i++;
//                    line = br.readLine();
//                    // System.out.println(text);
//                    text = "";
//                }
//                matriksBrs = i - 1;
//                matriksKol = j;
//                br.close();
//                scan1.close();
//                printMatriks(matriks, i-1, j);
//            }
//            if ((user_choice1 == 2) && (user_choice3 == 1)) { // Interpolasi, baca dari keyboard
//                int n; // n : jumlah data
//                int i;
//                System.out.print("n : ");
//                n = scan.nextInt();
//                for (i = 1; i <= (n + 1); i++) {
//                    int z = 0; // untuk nulis x dan y keberapa
//                    System.out.print("x" + (z) + " : ");
//                    matriks[i][1] = scan.nextDouble();
//                    System.out.print("x" + (z) + " : ");
//                    matriks[i][2] = scan.nextDouble();
//                    z++;
//                }
//            }
//            if ((user_choice1 == 1) && (user_choice3 == 2)) { // Interpolasi, baca dari file external
//
//            }
//
//        }

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

    public static void gaussjordan(double[][] matriks, int matriksBrs, int matriksKol) {
        double temp; //Menyimpan nilai perkalian
        int brs,l, i;

        gauss(matriks, matriksBrs, matriksKol);
        brs = 1;
        for (int j = 1; j<= matriksKol - 1; j++){
           if (matriks[brs][j] != 0){
              i = brs+1;
              for (int k = j+1; k <= matriksKol - 1; k++){
                  while (matriks[i][k] == 0){
                        k++;
                      }
                  l = k;
                  temp = matriks[brs][l];
                  for (l = k; l<=matriksKol; l++){
                      matriks[brs][l] -= matriks[i][k] * temp;
                      }
                  i++;
                  }
               brs++;
           }
          }
        }

    public static void splgauss(double[][] matriks,int matriksBrs,int matriksKol, double[][] variabel){
        //Matriks yang menjadi input adalah matriks augmented
        int j,k; //Mencari angka yang bukan 0 dalam satu baris
        int count0; //index angka bukan 0 pertama
        int brs;

        //Inisialisasi array spl
        for(int i=1;i<=matriksKol;i++) {
            variabel[i][0] = 0; //0 jika parametrik, 1 jika non-parametriks
            variabel[i][1] = 0; //Value
        }

        //Penyelesaian
        for(int i=matriksBrs;i>=1;i--){
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


    }

    public static void splgaussjordan(double[][] matriks,int matriksBrs,int matriksKol, double[][] variabel){
        //Matriks yang menjadi input adalah matriks augmented
        int brs;

        brs = 1;
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


    }


//    public static void FileToMatriks(double[][] matriks, int matriksBrs, int matriksKol) {
//        try {
//            BufferedReader br = new BufferedReader(new FileReader("test.txt"));
//            int i;
//            i = 1;
//            int j;
//            j = 1;
//            double bil;
//            // int matriksBrs;
//            // int matriksKol;
//            // double[][] matrix = new double[1000][1000];
//            String line;
//            String text = "";
//            Scanner scan = new Scanner(new BufferedReader(new FileReader("test.txt")));
//            scan.useLocale(Locale.US);
//            line = br.readLine();
//            while (line != null) {
//                text += line;
//                // System.out.println(scan.hasNext());
//                if (scan.hasNext() == true) {
//                    // System.out.print(text);
//                    // System.out.flush();
//                }
//                while (scan.hasNextDouble()) {
//                    // System.out.print(scan.nextDouble());
//                    bil = scan.nextDouble();
//                    matriks[i][j] = bil;
//                    // System.out.println(matrix[i][j]);
//                    j++;
//                }
//                i++;
//                line = br.readLine();
//                // System.out.println(text);
//                text = "";
//            }
//            matriksBrs = i - 1;
//            matriksKol = j;
//            br.close();
//            scan.close();
//            // for (int k = 1; k <= NBar; k++) {
//            // for (int l = 1; l <= (NKol - 1); l++) {
//            // System.out.print(matrix[k][l] + " ");
//            // }
//            // System.out.println(matrix[k][NKol]);
//
//        }
//        catch(Exception exc){
//            System.out.println("Gagal Boe");
//        }
//        finally {
//            br.close();
//            scan.close();
//        }
//    }

    public static void Interpolasi(double[][] matriks, int matriksBrs, double[][] variabel) {
        //splgauss( double[][] matriks, int matriksKol, int matriksBrs);
        double[][] tabelinterpolasi = new double[matriksBrs+1][matriksBrs+2];
        double[][] variabel1 = new double [matriksBrs+2][2];
        int i;

        for(int j=1;j<=matriksBrs;j++){
            for(i=1;i<=matriksBrs;i++){
                tabelinterpolasi[i][j] = java.lang.Math.pow(matriks[i][1],j-1);
            }
        }

        for(i=1;i<=matriksBrs;i++){ // Augmented
            tabelinterpolasi[i][matriksBrs+1] = matriks[i][2];
        }
        printMatriks(tabelinterpolasi, matriksBrs, (matriksBrs + 1));
        gauss(tabelinterpolasi,matriksBrs,(matriksBrs + 1));
        printMatriks(tabelinterpolasi, matriksBrs, (matriksBrs + 1));
        splgauss(tabelinterpolasi, matriksBrs,(matriksBrs + 1),variabel1);
        //variabel[i][1]
        //tabelinterpolasi[i][matriksBrs+1];

        //Print persamaan
        i = matriksBrs;
        if (i == 1){
            System.out.print("y = " + variabel1[i][1]);
        }
        else {
            System.out.print("y = " + variabel1[i][1] + "x^" + (i-1));
        }
        i--;
        while (i>1){
            if (variabel1[i][1] >= 0){
                System.out.print(" + " + variabel1[i][1] + "x^" + (i-1));
            }
            else {
                System.out.print(" " + variabel1[i][1] + "x^" + (i-1));
            }
            i--;
        }
        if (tabelinterpolasi[i][matriksBrs+1] > 0){
            System.out.print(" + " + variabel1[i][1]);
        }
        else if (tabelinterpolasi[i][matriksBrs+1] < 0){
            System.out.print(" " + variabel1[i][1]);
        }
        System.out.println();
    }
}
