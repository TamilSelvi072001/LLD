import java.util.UUID;

public class Session {
    String sessionId;
    BankService bankService;
    Card card;
    Boolean authenticate;
    Account account;

    public Session( BankService bankService, Card card) {
        this.sessionId = UUID.randomUUID().toString();
        this.bankService = bankService;
        this.card = card;
        this.authenticate = false;
    }

    public Boolean validatePin(String pin){
        authenticate =bankService.authenticateUser(card.getCardNumber(),pin);
        if(authenticate){
            account=bankService.getCurrentAccount(card.getCardNumber());
        }
        return authenticate;
    }

    public Float getBalance(){
        if (!authenticate) return -1F;
       return account.getCurrentBalance();
    }

    public Boolean deposit(Float amount){
        if(!authenticate) return false;
        TransactionResult result=account.deposit(amount);
        if(result.isSuccess()){
            bankService.updateAccount(account);
            bankService.logTransaction(result.getTransaction());
            return true;
        }
        return false;
    }

    public Boolean withdraw(Float amount){
        if(!authenticate) return false;
        TransactionResult result=account.withdraw(amount);
        if(result.isSuccess()){
            bankService.updateAccount(account);
            bankService.logTransaction(result.getTransaction());
            return true;
        }
        return false;

    }

}
