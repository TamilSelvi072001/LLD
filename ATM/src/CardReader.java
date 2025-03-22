import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

public class CardReader {

    private final Scanner scanner;

    public CardReader() {
        this.scanner=new Scanner(System.in);
    }

    public Card readCard(){
        System.out.println("Enter the Card Number or exit");
        String cardNumber=scanner.nextLine();
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("MM/yyyy");
        YearMonth expiryDate;

        if(cardNumber.equalsIgnoreCase("exit")){
            return null;
        }

        expiryDate= YearMonth.parse("09/2026",formatter);

        return new Card(cardNumber,expiryDate);
    }

    public void ejectCard(){
        System.out.println("Card Removed");
    }
}
