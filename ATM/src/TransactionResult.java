public class TransactionResult {
    private final boolean success;
    private final String message;
    private final float newBalance;
    private final Transaction transaction;
    public TransactionResult(boolean success, String message, float newBalance, Transaction transaction) {
        this.success = success;
        this.message = message;
        this.newBalance = newBalance;
        this.transaction = transaction;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public float getNewBalance() {
        return newBalance;
    }

    public Transaction getTransaction() {
        return transaction;
    }
}
