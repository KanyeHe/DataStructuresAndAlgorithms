package data.recursion;

/**
 * 迷宫 labyrinth
 *
 * 定义一个 二维数组， 约定 值为1时墙壁，四周是墙壁，中间有墙壁
 * 0表示该点没有走过，1表示墙壁，2表示走过且是通路，3表示走过且路不通
 * 设定一个入口 和 出口，规划出走出迷宫的路线
 * 走迷宫的时候需要明确一个策略，这里是 下 -> 右 -> 上 -> 左 的顺序，
 * 即先向下面走，如果下面的路不通则向右走，如果还是不通然后依次是 上 、左。
 */
public class Maze {
    //定义出口的左边 (6,5)
    private static final int END_X = 6, END_Y = 5;

    private final int[][] map = new int[8][7];

    public Maze() {
        //设置上面和下面的墙
        for (int i = 0; i < map[0].length; i++) {
            map[0][i] = 1;
            map[map.length - 1][i] = 1;
        }
        //设置左右两边的墙
        for (int i = 0; i < map.length; i++) {
            map[i][0] = 1;
            map[i][map[0].length - 1] = 1;
        }
        //设置中间的障碍墙
        map[3][1] = 1;
        map[3][2] = 1;
        //map[2][2] = 1;
        //map[1][2] = 1;
    }

    public void showMap() {
        for (int[] ints : map) {
            for (int y = 0; y < map[0].length; y++) {
                System.out.print(ints[y] + "    ");
            }
            System.out.println();
        }
    }

    /**
     * 设置一个起点
     * 按照 下右上左的顺序去寻找路 如果是通路返回true, 否则返回false
     * @param (x, y) 起点
     * @return
     */
    public boolean setWay(int x, int y) {
        //判断是否到了终点，其实就是看终点是否被走过
        if (map[END_X][END_Y] == 2) {
            return true;
        }
        //如果路没有走过
        if (map[x][y] == 0) {
            //否则设置当前点为走过的点
            map[x][y] = 2;
            //然后分别按照 下右上左 的顺序是探测周边的点是否可走
            if (setWay(x + 1, y)) {
                return true;
            } else if (setWay(x, y + 1)) {
                return true;
            } else if (setWay(x - 1, y)) {
                return true;
            } else if (setWay(x , y -1)) {
                return true;
            }
            //如果 下右上左 都走不通，则重置这个点为死路 即 3-走过且不通
            map[x][y] = 3;
        }
        //如果已经走过，或者是墙 或者 是死路
        return false;
    }

    public static void main(String[] args) {
        Maze maze = new Maze();
        maze.showMap();
        maze.setWay(1, 1);
        System.out.println("迷宫路线");
        maze.showMap();

    }
}
