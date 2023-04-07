import java.util.Arrays;

public class Easy extends Game{
    private int[][] matrixSol ={ // sol matrix
            {1, 2, 9, 4, 5, 3, 7, 8, 6},
            {3, 6, 4, 7, 2, 8, 5, 9, 1},
            {8, 7, 5, 9, 1, 6, 3, 2, 4},
            {7, 5, 2, 8, 9, 1, 6, 4, 3},
            {9, 1, 3, 5, 6, 4, 2, 7, 8},
            {4, 8, 6, 2, 3, 7, 9, 1, 5},
            {6, 9, 8, 3, 4, 2, 1, 5, 7},
            {5, 3, 7, 1, 8, 9, 4, 6, 2},
            {2, 4, 1, 6, 7, 5, 8, 3, 9}};
    private int[][] matrix = {
            {0, 2, 9, 0, 5, 0, 0, 0, 6},
            {3, 0, 4, 7, 2, 8, 0, 0, 1},
            {0, 0, 0, 0, 0, 0, 3, 0, 0},
            {0, 5, 2, 8, 9, 1, 6, 4, 0},
            {0, 1, 3, 0, 6, 4, 2, 7, 0},
            {0, 0, 0, 0, 0, 7, 9, 0, 0},
            {0, 9, 0, 0, 0, 0, 1, 5, 7},
            {5, 0, 0, 1, 8, 0, 4, 0, 2},
            {0, 0, 0, 0, 0, 0, 8, 3, 0}};

    public Easy(int points) {
        super(points);
    }

    @Override
    public boolean isValid(int row, int col,int value) { //
        if(isValidRange(row,col) && isValidValue(value)) {
            if (matrixSol[row - 1][col - 1] == value) {
                if (matrix[row - 1][col - 1] == 0) // if first attempt to the right answer
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
    public boolean isFinished() { // check if sol and matrix are equal
        return Arrays.deepEquals(matrix, matrixSol); // deep because 2d array
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
