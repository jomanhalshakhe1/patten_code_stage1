import java.util.Arrays;

public class Hard extends Game{
    private int[][] matrixSol = {
            {5, 9, 2, 4, 8, 7, 1, 6, 3},
            {6, 3, 4, 1, 5, 9, 7, 2, 8},
            {7, 1, 8, 2, 6, 3, 9, 4, 5},
            {3, 4, 7, 6, 1, 2, 8, 5, 9},
            {2, 5, 6, 3, 9, 8, 4, 7, 1},
            {1, 8, 9, 5, 7, 4, 6, 3, 2},
            {8, 2, 1, 7, 3, 6, 5, 9, 4},
            {9, 7, 3, 8, 4, 5, 2, 1, 6},
            {4, 6, 5, 9, 2, 1, 3, 8, 7}};
    private int[][] matrix = {
            {5, 0, 0, 0, 8, 0, 1, 0, 0},
            {0, 0, 4, 0, 0, 0, 0, 2, 0},
            {7, 0, 8, 2, 0, 0, 0, 4, 0},
            {0, 0, 0, 0, 0, 0, 0, 5, 0},
            {0, 5, 0, 3, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 0, 4, 0, 0, 2},
            {8, 0, 1, 0, 0, 6, 5, 0, 0},
            {0, 0, 3, 0, 0, 0, 0, 0, 0},
            {4, 0, 0, 0, 0, 1, 3, 0, 7}};

    public Hard(int points) {
        super(points);
    }

    @Override
    public boolean isValid(int row, int col,int value) {
        if(isValidRange(row,col) && isValidValue(value)) {
            if (matrixSol[row - 1][col - 1] == value) {
                if (matrix[row - 1][col - 1] == 0)
                    increase();
                matrix[row - 1][col - 1] = value;
                return true;
            } else {
                decrease();
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean isFinished() {
        return Arrays.deepEquals(matrix, matrixSol);
    }
    @Override
    public void printBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if(matrix[i][j] == 0)
                    System.out.print("_|");
                else
                    System.out.print(matrix[i][j]+"|");
            }
            if((i + 1) % 3 == 0)
                System.out.println("\n───── ───── ─────");
            else
                System.out.println();
        }
    }
    public int[][] getMatrix(){
        return matrix;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }
}
