import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.io.PrintWriter;

public class Board {
    private String path;

    // Read or create a file based on the given path
    public void readOrCreateFile(String path) throws IOException {
        this.path = path;
        Path file = Paths.get(path);
        if (!Files.exists(file)) {
            Files.createFile(file); // Create the file if it does not exist
        }

        try (Stream<String> lines = Files.lines(file)) {
            List<String> data = lines.collect(Collectors.toList()); // Read all lines from the file into a list
            for (int i = 0; i < data.size(); i += 13) { // Iterate through the list with a step of 13 (to read each player's data)
                String name = data.get(i); // Read player's name
                int[][] easy = new int[9][9]; // Create 2D array for easy level
                int[][] hard = new int[9][9]; // Create 2D array for hard level
                for (int j = 0; j < 9; j++) {
                    String[] values = data.get(i + j + 1).split(" "); // Read and split line containing easy level values
                    for (int k = 0; k < 9; k++) {
                        easy[j][k] = Integer.parseInt(values[k]); // Parse and store easy level values in 2D array
                    }
                }
                for (int j = 0; j < 9; j++) {
                    String[] values = data.get(i + j + 10).split(" "); // Read and split line containing hard level values
                    for (int k = 0; k < 9; k++) {
                        hard[j][k] = Integer.parseInt(values[k]); // Parse and store hard level values in 2D array
                    }
                }
                Main.users.add(new player(name, easy, hard, Integer.parseInt(data.get(i + 19)),
                        Integer.parseInt(data.get(i + 20)))); // Create a new Player object and add it to the users list
            }
        }
    }

    // Save player information to the file
    public void saveInfo() throws IOException {
        Path file = Paths.get(path);
        try (PrintWriter print = new PrintWriter(Files.newBufferedWriter(file, StandardCharsets.UTF_8, StandardOpenOption.CREATE))) {
            //PrintWriter is a class in Java that provides methods for writing formatted text to a character stream, such as a file. It is commonly used for writing text data to files, sockets, or other character-based output streams.

//In the code provided, PrintWriter is used to write player information to a file. It is created with Files.newBufferedWriter() method, which returns a buffered character-output stream that writes to the specified file. The StandardCharsets.UTF_8 parameter specifies the character encoding to be used for writing the text, in this case, UTF-8. The StandardOpenOption.CREATE parameter specifies that the file should be created if it does not exist.
            for (player user : Main.users) {
                print.println(user.getName()); // Write player's name
                for (int[] row : user.getEasy()) {
                    for (int value : row) {
                        print.print(value + " "); // Write easy level values separated by space
                    }
                    print.println(); // Write newline character after each row
                }
                for (int[] row : user.getHard()) {
                    for (int value : row) {
                        print.print(value + " "); // Write hard level values separated by space
                    }
                    print.println(); // Write newline character after each row
                }
                print.println(user.getEasyPoints()); // Write player's easy level points
                print.println(user.getHardPoints()); // Write player's hard level points
            }
        }
    }
}
