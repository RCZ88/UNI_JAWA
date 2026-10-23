import java.util.Scanner;

public class CofactorExpansion {
    static final int ROW = 0;
    static final int COL = 1;
    static void main() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] matrix = new int[n][n];
        int [] rowCol = new int[2];
        System.out.print ("Enter Breaker Cords (1, Y) or (X, 1): ");
        rowCol[COL] = sc.nextInt();
        rowCol[ROW] = sc.nextInt();

        if(rowCol[COL] > matrix.length || rowCol[ROW] > matrix.length || rowCol[COL] < 1 || rowCol[ROW] < 1){
            System.out.println("Invalid Index");
        }

        if(rowCol[ROW] != 1 && rowCol[COL] != 1){
            System.out.println("At least one of the X Y is 1!");
            return;
        }

        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                matrix[i][j] = sc.nextInt();
            }
            sc.nextLine();
        }
        int determinant = getDeter(matrix, rowCol);
        System.out.println("Determinant = " + determinant);
    }
    static int getDeter(int[][] matrix, int[]start){
        int size = matrix.length;
        System.out.println("Determinant of Size: " + size);
        if(size == 2) return matrix[0][0] * matrix[1][1] + matrix[0][1] * matrix[1][0];
        System.out.println("Shrinking to size: " + (size-1));
        StringBuilder equation = new StringBuilder();
        int sum = 0;
        for(int i = 0; i<size; i++){
            int [][]smaller = new int[size-1][size-1];
            int xIndex;
            int yIndex = 0;
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j<size; j++){
                xIndex = 0;
                for(int k = 0; k<size; k++){
                    if(j == start[ROW]-1 || k == start[COL]-1){
                        continue;
                    }
//                    System.out.print("(X,Y) = (" + xIndex + "," + yIndex + ")");
                    sb.append(matrix[j][k]).append(" ");
                    smaller[yIndex][xIndex++] = matrix[j][k];
                }
                if(j != 0){
                    yIndex++;
                }
                sb.append("\n");
            }

            int otherIndex = Math.max(start[ROW], start[COL]);
            int multiplier = (int) Math.pow(-1, i * otherIndex);
            int coef = multiplier * matrix[i][start[COL]-1];
            if(start[ROW] == 1){
                coef = multiplier * matrix[start[ROW]-1][i];
            }

            sum += coef * getDeter(smaller, start);
            equation.append(coef).append("*").append(sb);
            if(i != size-1){
                equation.append(" + ");
                continue;
            }
            equation.append(" = ");
            System.out.println(equation);
        }
        equation.append(sum);
        System.out.println(equation);
        return sum;
    }

}
