package data.array;

/**
 * @author Kanye
 */

/**
 * 应用场景： 一个数组中存在大量重复值的时候，将数组转换成稀疏数组
 * 实现方式：定义一个多行三列的二维数组，
 * 第一行 定义原数组有多少行，多少个有效值，
 * 剩下的每一行就是有效值所在行列以及对应的值
 */
public class Sparsearray {

    public static void main(String[] args) {
        //创建一个 11 * 11 的二维数组
        // 0-表示默认值，其他表示有效值
        int[][] twoDimensionalArray = new int[11][10];
        twoDimensionalArray[1][2] = 1;
        twoDimensionalArray[3][4] = 2;
        System.out.println("===========二维数组================");
        for (int[] row : twoDimensionalArray) {
            for (int value : row) {
                System.out.printf("%d\t", value);
            }
            System.out.println();
        }
        int[][] sparsearray = twoDimensionalArray2Sparsearray(twoDimensionalArray);
        sparsearray2twoDimensionalArray(sparsearray);
    }

    /**
     * 二维数组 转 稀疏数组 思路
     * 1. 遍历原二维数组，得到有效数据个数 num
     * 2. 根据有效数据个数 num ，创建稀疏数组 sparsearray[num+1][3]
     * 3. 再次遍历二维数组将有效数据位置以及值存入稀疏数组
     */
    public static int[][] twoDimensionalArray2Sparsearray(int[][] twoDimensionalArray) {
        int num = 0;
        for (int[] row : twoDimensionalArray) {
            for (int value : row) {
                if (value != 0) {
                    num++;
                }
            }
        }
        int[][] sparsearray = new int[num + 1][3];
        sparsearray[0][0] = twoDimensionalArray.length;
        sparsearray[0][1] = twoDimensionalArray[0].length;
        sparsearray[0][2] = num;
        //有效数据是从第二行开始存储的，所以当前行默认是1
        int currentRow = 1;
        for (int i = 0; i < twoDimensionalArray.length; i++) {
            for (int j = 0; j < twoDimensionalArray[0].length; j ++) {
                if (twoDimensionalArray[i][j] != 0) {
                    sparsearray[currentRow][0] = i;
                    sparsearray[currentRow][1] = j;
                    sparsearray[currentRow][2] = twoDimensionalArray[i][j];
                    currentRow++;
                }
            }
        }
        System.out.println("===========稀疏数组================");
        for (int[] row : sparsearray) {
            for (int value : row) {
                System.out.printf("%d\t", value);
            }
            System.out.println();
        }
        return sparsearray;
    }

    /**
     * 稀疏数组转二维数组
     * 1. 遍历稀疏数组每一行，
     * 2. 获取第一行数据，创建二维数组
     * 3. 剩下的每一行数据分别赋值给二维数组
     */
    public static void sparsearray2twoDimensionalArray(int[][] sparsearray) {
        int[][] twoDimensionalArray = new int[sparsearray[0][0]][sparsearray[0][1]];
        for (int i = 1; i < sparsearray.length; i++) {
            twoDimensionalArray[sparsearray[i][0]][sparsearray[i][1]] = sparsearray[i][2];
        }
        System.out.println("===========还原二维数组================");
        for (int[] row : twoDimensionalArray) {
            for (int value : row) {
                System.out.printf("%d\t", value);
            }
            System.out.println();
        }
    }
}
