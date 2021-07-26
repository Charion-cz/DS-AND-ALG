package com.cz.sparsearray;

/**
 * @Description: 稀疏数组
 * @Date: 2021/5/18 16:10
 */
public class SparseArray {
    public static void main(String[] args) {

        int chessArray[][] = new int[11][11];
        chessArray[1][2] = 1;
        chessArray[2][3] = 2;
        chessArray[3][4] = 2;

        int sum = 0;
        for (int[] row : chessArray ){
            for (int data : row){
                System.out.printf("%d\t", data);
                if (data != 0){
                    sum++;
                }
            }
            System.out.println();
        }

        int sparseArr[][] = new int[sum+1][3];
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;

        int count = 0;

        for (int i = 0; i< chessArray.length; i++){
            for(int j = 0; j < chessArray.length; j++){
                if (chessArray[i][j] != 0){
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArray[i][j];
                }
            }
        }

        System.out.println("输出稀疏数组~~");
        for (int i = 0; i<sparseArr.length; i++){
            System.out.printf("%d\t%d\t%d\t\n",sparseArr[i][0],sparseArr[i][1],sparseArr[i][2]);
        }
        System.out.println();

        int chessArray2[][] = new int[sparseArr[0][0]][sparseArr[0][1]];

        for (int i = 1; i < sparseArr.length; i++){
            chessArray2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        for (int[] row : chessArray2 ){
            for (int data : row){
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
    }
}
