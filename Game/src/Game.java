public abstract class Game {
    private int points; // Store points

    public Game(int points) {
        this.points = points;
    }

    public boolean isValidRange(int row, int col) {
        // Check if row and col are within the valid range [1 - 9] inclusive
        return row > 0 && row < 10 && col > 0 && col < 10;
    }

    public boolean isValidValue(int value) {
        // Check if value is within the valid range [1 - 9]
        return value > 0 && value < 10;
    }

    public abstract boolean isValid(int row, int col, int value);
    // Abstract method to be implemented by subclasses
    // Check if the given row, col, and value form a valid move in the game

    public abstract boolean isFinished();
    // Abstract method to be implemented by subclasses
    // Check if the game is finished, i.e., every cell is filled

    public void increase() {
        points += 5;
    }
    // Increase points by 5

    public void decrease() {
        points = Math.max(points - 2, 0);
    }
    // Decrease points by 2, but not below 0

    public abstract void printBoard();
    // Abstract method to be implemented by subclasses
    // Print the game board

    public int getPoints() {
        return points;
    }
    // Get the current points

    public abstract int[][] getMatrix();
    // Abstract method to be implemented by subclasses
    // Get the current game board as a 2D array (matrix)
}
