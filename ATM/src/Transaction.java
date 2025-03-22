import java.time.LocalDateTime;

public class Transaction {
    TransactionStatus transactionStatus;
    LocalDateTime time;
    String transactionId;
    TransactionType transactionType;

    public Transaction(TransactionStatus transactionStatus, LocalDateTime time, String transactionId, TransactionType transactionType) {
        this.transactionStatus = transactionStatus;
        this.time = time;
        this.transactionId = transactionId;
        this.transactionType = transactionType;
    }
}
