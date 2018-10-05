package com.timothy;

import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
    public static void main(String[] args) throws Exception{
        // write your code here
        double[][] matriks = new double[1000][1000];
        float X;
        int matriksBrs;
        int matriksKol;
        Scanner scan = new Scanner(System.in);
        X = 0;
        PrintStream printfile = new PrintStream(new File("Hasil.txt"));
        int user_choice1, user_choice2, user_choice3, user_choice4; // user_choice1 = pilihan menu; user_choice2 = pilihan metode, user_choice4 = print ke file atau tidak;
        user_choice1 = 0;
        user_choice2 = 0;
        user_choice3 = 0;
        user_choice4 = 0;
        while (user_choice1 != 3) {
            System.out.println("MENU");
            System.out.println("1. Sistem Persamaan Linear");
            System.out.println("2. Interpolasi Polinom");
            System.out.println("3. Keluar");
            // user_choice3 = pilihan input
            matriksBrs = 0;
            matriksKol = 0;
            user_choice1 = scan.nextInt();
            while (user_choice1 != 1 && user_choice1 != 2 && user_choice1 != 3) {
                System.out.println("Input salah, masukan input lagi.");
                user_choice1 = scan.nextInt();
            }
            //if (user_choice1 != 3) {
            System.out.println("Pilihan metode :");
            System.out.println("1. Metode eliminasi Gauss");
            System.out.println("2. Metode eliminasi Gauss-Jordan");
            user_choice2 = scan.nextInt();
            while (user_choice2 != 1 && user_choice2 != 2) {
                System.out.println("Input salah, masukan input lagi.");
                user_choice2 = scan.nextInt();
            }
            System.out.println("Pilihan metode input :");
            System.out.println("1. Keyboard");
            System.out.println("2. Text file");
            user_choice3 = scan.nextInt();
            while (user_choice3 != 1 && user_choice3 != 2) {
                System.out.println("Input salah, masukan input lagi.");
                user_choice3 = scan.nextInt();
            }
            System.out.println("Apakah hasil ingin di save ke file? ");
            System.out.println("1. IYA");
            System.out.println("2. TIDAK");
            user_choice4 = scan.nextInt();
            while ((user_choice4 != 1) && (user_choice4 != 2)){
                System.out.println("Input salah, masukan input lagi.");
                user_choice4 = scan.nextInt();
            }
            if ((user_choice1 == 1) && (user_choice3 == 1)) { // SPL, baca dari keyboard
                int m, n, i, j; // m = jmlh baris, n = jumlah kolom, i = index baris, j = index kolom;
                System.out.println("Masukkan jumlah baris matriks");
                m = scan.nextInt();
                while (m < 1) {
                    System.out.println("Input salah, masukkan input lagi");
                    m = scan.nextInt();
                }
                System.out.println("Masukkan jumlah kolom matriks");
                n = scan.nextInt();
                while (n < 1) {
                    System.out.println("Input salah, masukkan input lagi");
                    n = scan.nextInt();
                }
                System.out.print("Baris: ");
                System.out.println(m);
                System.out.print("Kolom: ");
                System.out.println(n);
                System.out.println("Masukkan elemen matriks");
                for (i = 1; i <= m; i++) {
                    for (j = 1; j <= n; j++) {
                        matriks[i][j] = scan.nextDouble();
                    }
                }
                matriksBrs = m;
                matriksKol = n;
            } else if (((user_choice1 == 1) || (user_choice1 == 2)) && (user_choice3 == 2)) { // baca dari file external
                BufferedReader br = new BufferedReader(new FileReader("Input.txt"));
                int i = 1;
                int j = 1;
                String text = "";
                text = br.readLine();
                while (text != null) {
                    j = 1;
                    Scanner scan1 = new Scanner(text);
                    while (scan1.hasNextDouble()) {
                        matriks[i][j] = scan1.nextDouble();
                        j++;
                    }
                    i++;
                    text = br.readLine();
                    scan1.close();
                }
                matriksBrs = i - 1;
                matriksKol = j - 1;
                br.close();
//                printMatriks(matriks, matriksBrs, matriksKol);
            }
            else if ((user_choice1 == 2) && (user_choice3 == 1)) { // Interpolasi, baca dari keyboard
                int n; // n : jumlah data
                int i;
                System.out.print("n : ");
                n = scan.nextInt();
                int z = 0; // untuk nulis x dan y keberapa
                for (i = 1; i <= (n + 1); i++) {
                    System.out.print("x" + (z) + " : ");
                    matriks[i][1] = scan.nextDouble();
                    System.out.print("y" + (z) + " : ");
                    matriks[i][2] = scan.nextDouble();
                    z++;
                }
                matriksBrs = n + 1;
                matriksKol = 2;
            }
            double[][] variabel = new double[matriksKol + 1][matriksKol + 1];
            int mark[] = new int[matriksKol];
            if (user_choice1 == 1) { //SPL
                if (user_choice2 == 1) { //SPL, Gauss
                    splgauss(matriks, matriksBrs, matriksKol, variabel, mark);
                    printvariabel(matriksKol, variabel, mark);
                } else { //SPL, GaussJordan
                    splgaussjordan(matriks, matriksBrs, matriksKol, variabel, mark);
                    printvariabel(matriksKol, variabel, mark);
                }
            } else if (user_choice1 == 2) { //Interpolasi
                System.out.println("x yang ingin diuji: ");
                X = scan.nextFloat();
                if (user_choice2 == 1) { //Interpolasi, Gauss
                     InterpolasiGauss(matriks, matriksBrs, X);
                } else { //Interpolasi, GaussJordan
                        InterpolasiGaussJordan(matriks, matriksBrs, X);
                }
            }
            if(user_choice4 == 1) { // user minta output di save ke file
                PrintStream console = System.out;
                System.setOut(printfile);
                if(user_choice1 == 1){ // SPL
                    printvariabel(matriksKol, variabel, mark);
                }
                else{
                    if ((user_choice1 == 2) && (user_choice2 == 1)){// Interpolasi, Gauss
                        InterpolasiGauss(matriks, matriksBrs, X);
                    }
                    else if ((user_choice1 == 2) && (user_choice2 == 2)){// Interpolasim Gauss Jordan
                        InterpolasiGaussJordan(matriks, matriksBrs, X);
                    }
                }
                System.setOut(console);
            }
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

    public static void REF(double[][] matriks, int matriksBrs, int matriksKol) {
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

    public static void RREF(double[][] matriks, int matriksBrs, int matriksKol) {
        double temp; //Menyimpan nilai perkalian
        int brs,k;
        int count0;

        REF(matriks, matriksBrs, matriksKol);

        for(int i=matriksBrs;i>=1;i--) {
            count0 = 0;

            //Mencari angka diagonal utama
            k = 1;
            while ((count0 == 0) && (k <= matriksKol)) {
                if ((matriks[i][k]) != 0) {
                    count0++;
                }
                k++;
            }
            k--;
            for (int j = i - 1; j >= 1; j--) {
                temp = matriks[j][k];
                for (int l = 1; l <= matriksKol; l++) {
                    matriks[j][l] -= temp * matriks[i][l];
                }
            }
        }
    }

    public static void splgauss(double[][] matriks,int matriksBrs,int matriksKol, double[][] variabel, int mark[]){
        //Matriks yang menjadi input adalah matriks augmented
        int j,k; //Mencari angka yang bukan 0 dalam satu baris
        int count0; //index angka bukan 0 pertama

        REF(matriks, matriksBrs, matriksKol);

        //Inisialisasi array spl
        for(int i=0;i<=matriksKol-1;i++) {
            mark[i] = 0; //0 jika parametrik, 1 jika non-parametrik
            for (int m=0; m<=matriksKol-1; m++){
                variabel[i][m] = 0;
            }
            variabel[i][i] = 1;
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
            if((count0>0) && (k != matriksKol)) { //Jika terdapat nilai diagonal utama
                mark[k] = 1;
                variabel[k][0] = matriks[i][matriksKol];
                variabel[k][k] = 0;
                j = matriksKol - 1;
                while (j > k) { //Supaya tidak merubah diagonal utama
                    if (mark[j] == 1) {
                        variabel[k][0] -= variabel[j][0] * matriks[i][j];
                    }
                    else if (mark[j] == 0) {
                        mark[k] = 0;
                        for (int l = 1; l <= matriksKol -1; l++) {
                            variabel[k][l] -= matriks[i][j] * variabel[j][l];
                        }
                    }
                    j--;
                }
            }
            else if ((count0 > 0) && (k == matriksKol)) {
                mark[0] = 1; //menandakan tidak ada solusi
            }
        }
    }

    public static void splgaussjordan(double[][] matriks,int matriksBrs,int matriksKol, double[][] variabel, int[] mark){
        //Matriks yang menjadi input adalah matriks augmented
        RREF(matriks, matriksBrs, matriksKol);
        int count0;
        int j,k;

        for(int i=0;i<=matriksKol-1;i++) {
            mark[i] = 0; //0 jika parametrik, 1 jika non-parametrik
            for (int m=0; m<=matriksKol-1; m++){
                variabel[i][m] = 0;
            }
            variabel[i][i] = 1;
        }

        //Penyelesaian
        for(int i=matriksBrs;i>=1;i--) {
            count0 = 0;

            //Mencari angka diagonal utama
            k = 1;
            while ((count0 == 0) && (k <= matriksKol)) {
                if ((matriks[i][k]) != 0) {
                    count0++;
                }
                k++;
            }
            k--;
            if ((count0 > 0) && (k != matriksKol)) { //Jika terdapat nilai diagonal utama
                mark[k] = 1;
                variabel[k][k] = 0;
                variabel[k][0] = matriks[i][matriksKol];
                j = matriksKol - 1;
                while (j > k) { //Supaya tidak merubah diagonal utama
                    if (mark[j] == 0) {
                        mark[k] = 0;
                        for (int l = 1; l <= matriksKol -1; l++) {
                            variabel[k][l] -= matriks[i][j] * variabel[j][l];
                        }
                    }
                    j--;
                }
            }
            else if ((count0 > 0) && (k == matriksKol)) {
                mark[0] = 1; //menandakan tidak ada solusi
            }
        }
    }

    public static void printvariabel(int matriksKol, double[][] variabel, int[] mark){
        //Matriks yang menjadi input adalah matriks augmented
        int j;
        boolean pertama;

        if (mark[0] == 0){
            for (int i = 1; i <= matriksKol - 1; i++) {
                System.out.print("x" + i + " = ");
                pertama = false;
                j = 0;
                if (!(pertama)){
                    if (variabel[i][j] > 0) {
                        System.out.print(variabel[i][j]);
                        pertama = true;
                    } else if (variabel[i][j] < 0) {
                        System.out.print("- " + (variabel[i][j] * -1));
                        pertama = true;
                    }
                }
                j++;
                while (j <= matriksKol - 1){
                    if (!(pertama)){
                        if (variabel[i][j] > 0) {
                            if (variabel[i][j] != 1){
                                System.out.print(variabel[i][j]);
                            }
                            System.out.print("x" + j);
                            pertama = true;
                        } else if (variabel[i][j] < 0) {
                            if (variabel[i][j] != -1){
                                System.out.print("- " + (variabel[i][j] * -1));
                            }
                            System.out.print("x" + j);
                            pertama = true;
                        }
                    }
                    else {
                        if (variabel[i][j] > 0) {
                            System.out.print(" + ");
                            if (variabel[i][j] != 1){
                                System.out.print(variabel[i][j]);
                            }
                            System.out.print("x" + j);
                        } else if (variabel[i][j] < 0) {
                            System.out.print(" - ");
                            if (variabel[i][j] != -1){
                                System.out.print(variabel[i][j] * -1);
                            }
                            System.out.print("x" + j);
                        }
                    }
                    j++;
                }
                System.out.println();
            }
        }
        else if (mark[0] == 1){
            System.out.println("Tidak ada solusi");
        }

    }

    public static void InterpolasiGauss(double[][] matriks, int matriksBrs, float X) {
        double[][] tabelinterpolasi = new double[matriksBrs+1][matriksBrs+2];
        double[][] variabel1 = new double [matriksBrs+2][matriksBrs+2];
        int[] mark1 = new int[matriksBrs+2];
        int i;
        double temp, y;

        for(int j=1;j<=matriksBrs;j++){
            for(i=1;i<=matriksBrs;i++){
                tabelinterpolasi[i][j] = java.lang.Math.pow(matriks[i][1],j-1);
            }
        }

        for(i=1;i<=matriksBrs;i++){ // Augmented
            tabelinterpolasi[i][matriksBrs+1] = matriks[i][2];
        }

        y = 0;
        splgauss(tabelinterpolasi, matriksBrs,(matriksBrs + 1),variabel1, mark1);

        //Print persamaan
        i = matriksBrs;
        if (i == 1){
            System.out.print("y = " + variabel1[i][0]);
            y = variabel1[i][0];
        }
        else {
            System.out.print("y = " + variabel1[i][0] + "x^" + (i-1));
            temp = X;
            for (int j = 2; j<= i-1; j++){
                temp *= X;
            }
            y = variabel1[i][0] * temp;
        }
        i--;
        while (i>1){
            if (variabel1[i][0] > 0){
                System.out.print(" + " + variabel1[i][0] + "x^" + (i-1));
            }
            else if (variabel1[i][0] < 0) {
                System.out.print(" - " + (variabel1[i][0] * -1) + "x^" + (i-1));
            }
            temp = X;
            for (int j = 2; j<= i-1; j++){
                temp *= X;
            }
            y = y + (variabel1[i][0] * temp);
            i--;
        }
        if (tabelinterpolasi[i][matriksBrs+1] > 0){
            if(variabel1[i][0] > 0) {
                System.out.print(" + " + variabel1[i][0]);
            }
            else if(variabel1[i][0] < 0){
                System.out.print(" " + variabel1[i][0]);
            }
            y = y + variabel1[i][0];
        }
        else if (tabelinterpolasi[i][matriksBrs+1] < 0){
            System.out.print(" " + variabel1[i][0]);
            y = y + variabel1[i][0];
        }
        System.out.println();
        System.out.println("Nilai y yang ditafsir untuk x: " + y);
    }

    public static void InterpolasiGaussJordan(double[][] matriks, int matriksBrs, float X) {
        double[][] tabelinterpolasi = new double[matriksBrs+1][matriksBrs+2];
        double[][] variabel1 = new double [matriksBrs+2][matriksBrs+2];
        int[] mark1 = new int[matriksBrs+2];
        int i;
        double y, temp;

        for(int j=1;j<=matriksBrs;j++){
            for(i=1;i<=matriksBrs;i++){
                tabelinterpolasi[i][j] = java.lang.Math.pow(matriks[i][1],j-1);
            }
        }

        for(i=1;i<=matriksBrs;i++){ // Augmented
            tabelinterpolasi[i][matriksBrs+1] = matriks[i][2];
        }

        splgaussjordan(tabelinterpolasi, matriksBrs,(matriksBrs + 1),variabel1, mark1);

        //Print persamaan
        i = matriksBrs;
        if (i == 1){
            System.out.print("y = " + variabel1[i][0]);
            y = variabel1[i][0];
        }
        else {
            System.out.print("y = " + variabel1[i][0] + "x^" + (i-1));
            temp = X;
            for (int j = 2; j<= i-1; j++){
                temp *= X;
            }
            y = variabel1[i][0] * temp;
        }
        i--;
        while (i>1){
            if (variabel1[i][0] > 0){
                System.out.print(" + " + variabel1[i][0] + "x^" + (i-1));
            }
            else if (variabel1[i][0] < 0) {
                System.out.print(" - " + (variabel1[i][0] * -1) + "x^" + (i-1));
            }
            temp = X;
            for (int j = 2; j<= i-1; j++){
                temp *= X;
            }
            y = y + (variabel1[i][0] * temp);
            i--;
        }
        if (tabelinterpolasi[i][matriksBrs+1] > 0){
            if(variabel1[i][0] > 0) {
                System.out.print(" + " + variabel1[i][0]);
            }
            else if(variabel1[i][0] < 0){
                System.out.print(" " + variabel1[i][0]);
            }
            y = y + variabel1[i][0];
        }
        else if (tabelinterpolasi[i][matriksBrs+1] < 0){
            System.out.print(" " + variabel1[i][0]);
            y = y + variabel1[i][0];
        }
        System.out.println();
        System.out.println("Nilai y yang ditafsir untuk x: " + y);
    }
}


