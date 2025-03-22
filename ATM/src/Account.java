import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Account {
    private String accountNumber;
    private Float balance;

    public Account(String accountNumber,Float balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public Float getCurrentBalance(){
        return balance;
    }

    public String getAccountNumber(){
        return accountNumber;
    }



    public TransactionResult deposit(Float amount){
        if(amount<=0) {
            return new TransactionResult(false,"Invalid amount",balance,null);
        }
        balance+=amount;
        Date date = new Date(); // Current date and time
        LocalDateTime localDateTime = date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();

        System.out.println("Date: " + date);
        System.out.println("LocalDateTime: " + localDateTime);
        Transaction transaction=new Transaction(TransactionStatus.SUCCESS,localDateTime,UUID.randomUUID().toString(),TransactionType.DEPOSIT);
        return new TransactionResult(true,"Deposited successfully",balance,transaction);
    }

    public TransactionResult withdraw(Float amount){
        if(amount<=0) {
            return new TransactionResult(false,"Invalid amount",balance,null);
        }
        balance-=amount;
        Date date = new Date(); // Current date and time
        LocalDateTime localDateTime = date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
        Transaction transaction=new Transaction(TransactionStatus.SUCCESS,localDateTime,UUID.randomUUID().toString(),TransactionType.WITHDRAW);
        return new TransactionResult(true,"Deposited successfully",balance,transaction);
    }

}
