package com.cz.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * @Description: 图
 * @Version:
 * @Date: 2021/8/8 16:00
 */
public class Graph {

    // 存储顶点集合
    private ArrayList<String> vertexList;
    // 存储图对应的临接矩阵
    private int[][] edges;
    // 表示边的数目
    private int numOfEdges;
    // 定义一个数组Boolean，记录某个节点是否被访问过
    private boolean[] isVisited;

    public static void main(String[] args) {
        int n = 5;
        String[] vertexes = {"A", "B", "C", "D", "E"};
        Graph graph = new Graph(n);
        // 添加节点
        for (String vertex : vertexes) {
            graph.insertVertex(vertex);
        }
        // 添加边
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);

        graph.showGraph();

        System.out.println("深度遍历：");
//        graph.dfs();
        graph.bfs();
    }

    // 构造器
    public Graph(int n) {
        // 初始化矩阵和vertexList
        edges = new int[n][n];
        vertexList = new ArrayList<String>();
        numOfEdges = 0;
    }

    // 得到当前节点的第一个邻接节点的下标w

    /**
     *
     * @param index
     * @return 找到返回对应的下标，没找到就返回-1
     */
    public int getFirstNeighbor(int index) {
        for (int j=0; j < vertexList.size(); j++) {
            if (edges[index][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    // 根据前一个临接节点的下标来获取下一个邻接节点
    public int getNextNeighbor(int v1, int v2) {
        for (int j = v2 + 1; j < vertexList.size(); j++) {
            if (edges[v1][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    //深度优先算法
    public void dfs(boolean[] isVisited, int i) {
        // 首先访问该节点
        System.out.print(getValueByIndex(i) + "->");
        // 将结点设置为已经访问
        isVisited[i] = true;
        // 查找节点i的第一个邻接节点
        int w = getFirstNeighbor(i);
        while (w != -1) {
            if (!isVisited[w]) {
                dfs(isVisited, w);
            }
            // 如果w节点已经被访问过
            w = getNextNeighbor(i,w);
        }
    }

    // 对dfs进行重载，遍历我们所有的节点，并进行dfs
    public void dfs() {
        isVisited = new boolean[vertexList.size()];
        // 遍历所有的节点，进行dfs回溯
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                dfs(isVisited,i);
            }
        }
    }

    // 对节点进行广度优先遍历的方法
    private void bfs(boolean[] isVisited, int i) {
        int u; // 表示队列的头结点对应下标
        int w; // 邻接节点w
        // 队列，记录节点访问的顺序
        LinkedList queue = new LinkedList();
        // 访问节点，输出节点信息
        System.out.println(getValueByIndex(i) + "=>");;
        // 标记为已访问
        isVisited[i] = true;
        // 将节点加入队列
        queue.addLast(i);

        while (!queue.isEmpty()) {
            // 取出队列的头结点下标
            u = (Integer) queue.removeFirst();
            // 得到第一个邻接节点的下标w
            w = getFirstNeighbor(u);
            while (w != -1) {
                // 是否访问过
                if (!isVisited[w]) {
                    System.out.print(getValueByIndex(w) + "=>");
                    // 标记为已经访问
                    isVisited[w] = true;
                    // 入队
                    queue.addLast(w);
                }
                // 以u为前驱节点，找到w后面的下一个邻接节点
                w = getNextNeighbor(u, w);
            }
        }
    }

    // 遍历所有节点，都进行广度优先搜索
    public void bfs(){
        isVisited = new boolean[vertexList.size()];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                bfs(isVisited,i);
            }
        }
    }

    // 显示图对应的矩阵
    public void showGraph() {
        for (int[] link : edges) {
            System.out.println(Arrays.toString(link));
        }
    }

    // 返回节点的个数
    public int getNumOfVertex() {
        return vertexList.size();
    }

    // 返回边的个数
    public int getNumOfEdges() {
        return numOfEdges;
    }

    // 返回节点i对应的数据
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    // 返回v1和v2的权值
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    // 插入结点
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    // 添加边
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }
}
