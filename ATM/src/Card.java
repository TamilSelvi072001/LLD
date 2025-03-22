import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.chrono.ChronoLocalDateTime;
import java.util.Date;

public class Card {
    String cardNumber;
    YearMonth expiryDate;

    public Card(String cardNumber, YearMonth expiryDate) {
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
    }


    public boolean validateCard(Card card){
        YearMonth currentYearMonth = YearMonth.now();
        YearMonth cardExpiry = card.expiryDate;

        if (cardExpiry.isBefore(currentYearMonth)) {
            System.out.println("Card is expired");
            return false;
        }

        return true;
    }

    public String getCardNumber(){
        return cardNumber;
    }
}
