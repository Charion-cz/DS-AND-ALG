package com.cz.recursion;

/**
 * @Description: 迷宫问题
 * @Date: 2021/5/25 21:54
 */
public class MiGong {
    public static void main(String[] args) {
        // 先创建一个二维数组，模拟迷宫
        // 地图
        int[][] map = new int[8][7];
        // 使用1表示墙
        // 上下全部置为1
        for (int i = 0; i < 7; i++){
            map[0][i] = 1;
            map[7][i] = 1;
        }
        // 左右全部置为1
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        //设置挡板
        map[3][1] = 1;
        map[3][2] = 1;
        // 输出地图
        for (int i = 0; i < 8;i++) {
            for (int j = 0;j < 7; j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        // 使用递归回溯找路
        setWay(map,1,1);
        // 输出新的地图，小球走过，并标识过的地图
        System.out.println("地图情况");
        for (int i = 0; i < 8;i++) {
            for (int j = 0;j < 7; j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * 使用递归回溯给小球找路
     * 当map[i][j]为0时，表示该点没有走过，1为墙，2表示可以走，3表示已经走过但是走不通
     * 确定一个策略：下->右->上->左,如果该点走不通，再回溯
     * @param map 表示地图
     * @param i 横坐标
     * @param j 纵坐标
     * @return 是否找到通路[6][5]
     */
    public static boolean setWay(int[][] map,int i,int j){
        if (map[6][5] == 2) {
            return true;
        }else {
            if (map[i][j] == 0){ // 如果当前这个点没有走过
                // 按照策略 下->右->上->左
                map[i][j] = 2;
                if (setWay(map,i+1,j)) {
                    return true;
                }else if (setWay(map,i,j+1)){
                    return true;
                }else if (setWay(map, i-1, j)){
                    return true;
                }else if (setWay(map, i, j-1)){
                    return true;
                }else {
                    // 说明该点走不通，是死路
                    map[i][j] = 3;
                    return false;
                }
            }else { // 如果map[i][j] != 0,可能是1,2,3
                return false;

            }
        }
    }
}
