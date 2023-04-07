import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Main {

    
    public static Game easy,hard;
    public static List<player> users = new ArrayList<>();
    public static player user;

    public static Scanner read = new Scanner(System.in);
    public static Board data = new Board();

    public static void main(String[] args) throws IOException {
        data.readOrCreateFile("info.txt");

        System.out.print("Enter your name: "); // print
        String name = read.nextLine();
         // take username

         
        while (!isValidName(name)){ // check if name is valid
            System.out.println("\nEnter the correct number.\n");
            System.out.print("Enter your name again: ");
            name = read.nextLine();
        }
        user = getUser(name);
        while (true) {
            System.out.println("\n1- Play the game \n2- Exit from the system\n3- Help player ");
            String choice = read.nextLine();
            switch (choice) {
                case "1":
                    playe();
                    break;
                case "2":
                    Exit();
                    break;
                case "3":
                    Help();
                    break;
                default:
                    System.out.println("\nIncorrect choice please try again .. \n");
            }
        }
    }
    
    public static void playe(){
        String choice = whileCorrectChoice("Select the level you want to play:\n1- Easy play \n2- Hard to play","1","2");
        if(choice.equals("1")){
            if(!Arrays.deepEquals(easy.getMatrix(),new Easy(0).getMatrix())){
                choice = whileCorrectChoice("You have unfinished game do you want to resume? (Y,N)","Y","N");
                if(choice.equalsIgnoreCase("N"))
                    easy = new Easy(0);
            }
            play(easy);
        }
        else{
            if(!Arrays.deepEquals(hard.getMatrix(),new Hard(0).getMatrix())){
                choice = whileCorrectChoice("You have unfinished game do you want to resume? (Y,N)","Y","N");
                if(choice.equalsIgnoreCase("N"))
                    hard = new Hard(0);
            }
            play(hard);
        }
    }
    public static void Exit() throws FileNotFoundException {
        System.out.println();
        String response = whileCorrectChoice("Are you sure you want to exit? (Y,N)","Y","N");
        if(response.equalsIgnoreCase("Y")){
            System.out.println("Thank you ...");
            
            System.exit(0);
        }
    }
    public static void Help(){
        System.out.println("\n" +
                "Sudoku is a popular logic-based puzzle game where the player fills in a 9x9 grid with digits from 1 to 9, following rules that each row, column, and box must contain all digits without repetition. The puzzle starts with some clues, and the player's goal is to logically deduce and fill in the remaining empty cells to complete the grid. Sudoku challenges cognitive skills like critical thinking and problem-solving and can be played in various forms. It is enjoyed by people of all ages and skill levels, providing a sense of accomplishment when solved.\n");
    }
    public static void play(Game game){
        boolean stop = false;
        while (!stop) {
            game.printBoard();
            System.out.println("\nYour score is: " + game.getPoints());
            System.out.print("Enter cell number row X coloum (example: row:) : ");
            int i = read.nextInt();
            int j = read.nextInt();
            while (!game.isValidRange(i,j)){
                System.out.println("\nInvalid Range try again .. ");
                System.out.print("\nEnter cell number (eg. 1 9) : ");
                i = read.nextInt();
                j = read.nextInt();
            }
            System.out.print("Enter value: ");
            int value = read.nextInt();
            read.nextLine(); // clear line
            if (!game.isValid(i, j, value)) {
                System.out.println("The inserted number ("+value+") in cell ("+i+", "+j+") is not correct.");
                String choice = whileCorrectChoice("\n1.Try again\n2.Main menu\n","1","2");
                if(choice.equalsIgnoreCase("2"))
                    stop = true;
            }
            else{
                System.out.println("The number ("+value+") is successfully inserted in cell ("+i+", "+j+").");
                String choice = whileCorrectChoice("\n1.Continue\n2.Main menu","1","2");
                if(choice.equalsIgnoreCase("2"))
                    stop = true;
            }
            if(game.isFinished()){
                System.out.println("Congrats "+user.getName()+" you have cleared the board !!\nYour points = "+game.getPoints()+"\n");
                if(game == easy)
                    easy = new Easy(0);
                else
                    hard = new Hard(0);
                stop = true;
            }
            user.setEasy(easy.getMatrix());
            user.setHard(hard.getMatrix());
            user.setEasyPoints(easy.getPoints());
            user.setHardPoints(hard.getPoints());
        }
    }
    public static String whileCorrectChoice(String options,String choice1,String choice2){
        System.out.println(options);
        String choice = read.nextLine();
        while (!(choice.equalsIgnoreCase(choice1) || choice.equalsIgnoreCase(choice2))){
            System.out.println("Incorrect , try again.. ");
            System.out.println(options);
            choice = read.nextLine();
        }
        return choice;
    }
    public static boolean isValidName(String name){
        return name.length() >= 3 && name.length() <= 8;
    }
    public static player getUser(String name){
        for(player user : users)
            if(user.getName().equalsIgnoreCase(name)) {
                easy = new Easy(user.getEasyPoints());
                hard = new Hard(user.getHardPoints());
                ((Easy)easy).setMatrix(user.getEasy());
                ((Hard)hard).setMatrix(user.getHard());
                return user;
            }
        easy = new Easy(0);
        hard = new Hard(0);
        player user = new player(name, easy.getMatrix(), hard.getMatrix(),0,0);
        users.add(user);
        return user;
    }
}
