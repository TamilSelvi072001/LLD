import java.util.Scanner;

public class Keyboard {
    Scanner scanner;

    public Keyboard(){
        scanner=new Scanner(System.in);
    }
    public String getInput(){
        return scanner.nextLine();
    }
    public Float getAmount(){
        return scanner.nextFloat();
    }
}
