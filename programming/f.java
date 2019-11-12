/*
Дана целочисленная матрица размера MxN, заполненная случайными значениями в диапазоне от -99 до 99.
Удалить её последний столбец, содержащий только отрицательное элементы. Если требуемых столбцов нет,
то вывести матрицу без изменений.
*/
import java.util.Random;
 
public class f {
    public static void main(String[] args) {
        
                int n = 4;
                int m = 5;
                int[][] matrix = new int[n][m];
                initMatrix(matrix);
                int foundedColumn = checkMatrix(matrix);
                for (int i = 0; i < matrix.length; i++) {
                    for (int j = 0; j < matrix[i].length; j++) {
                        if (j != foundedColumn){
                            System.out.print(matrix[i][j]);
                            System.out.print(" ");
                        }
                    }
                    System.out.println();
                }
            }
        
 /*Этот блок заполняет матрицу рандомными числами*/
    private static void initMatrix(int[][] matrix) {
        Random random = new Random(0);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = random.nextInt() % 100;
            }
        }
    }
    
/*Этот блок ищет последний столбец, в котором все элементы отрицательны, и возвращает его индекс. Возвращает -1, если таких столбцов нет
 */
    private static int checkMatrix(int[][] matrix) {
        int foundedColumn = -1;
        boolean trig = false;
        for (int j = matrix[0].length-1; j >= 0; j--) { // Если идти с конца, то первый столбец со всеми отрицательными будет нужным
            trig = true;
            for (int i = 0; i < matrix.length;i++){
                if (matrix[i][j] >= 0){
                    trig = false;
                    break;
                }
            }
            if (trig){
                foundedColumn = j;
                break;
            }
        }
        return foundedColumn;
    }
   
}