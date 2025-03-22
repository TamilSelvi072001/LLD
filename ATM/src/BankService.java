import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BankService {
    Map<String, String> cardPinMap;
    List<Transaction> transactions;
    Map<String,Account> cardAccountmap;

    public BankService() {
        this.cardPinMap = new ConcurrentHashMap<>();
        this.transactions = new ArrayList<>();
        this.cardAccountmap = new ConcurrentHashMap<>();
        staticData();
    }
    private void staticData(){
        cardPinMap.put("12345","123");
        Account account1 = new Account("12345",345F);
        cardAccountmap.put("12345", account1);
    }

    public Boolean authenticateUser(String cardNumber, String pin){
        return cardPinMap.get(cardNumber)!=null && cardPinMap.get(cardNumber).equals(pin);
    }

    public Account getCurrentAccount(String cardNumber){
        return cardAccountmap.get(cardNumber);
    }

    public void updateAccount(Account account){
        String cardNum = findCardForAccount(account.getAccountNumber());
        if (cardNum != null) {
            cardAccountmap.put(cardNum, account);
            return;
        }
        return;
    }
    private String findCardForAccount(String accountNumber) {
        // Find which card is associated with this account
        for (Map.Entry<String, Account> entry : cardAccountmap.entrySet()) {
            if (entry.getValue().getAccountNumber().equals(accountNumber)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public void logTransaction(Transaction transaction) {
        if (transaction != null) {
            transactions.add(transaction);
        }
    }


}
