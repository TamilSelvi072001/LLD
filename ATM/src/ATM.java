import java.util.UUID;

public class ATM {
    String location;
    String atmId;
    BankService bankService;
    CardReader cardReader;
    Card card;
    Screen screen;
    Session session;
    Keyboard keyboard;

    public ATM() {
        this.cardReader=new CardReader();
        this.screen=new Screen();
        this.atmId = UUID.randomUUID().toString();
        this.location = "location";
        this.bankService=new BankService();
        this.keyboard=new Keyboard();
    }

    public void start(){
        System.out.println("Welcome! Please insert card");
        while (true) {
            Card card = cardReader.readCard();
            if (card == null){
                // exit pressed
               continue;
            }

            if(!card.validateCard(card)){
                String expiredMessage="Card expired";
                screen.showMessage(expiredMessage);
                cardReader.ejectCard();
                break;
            }
            session=createSession(card);
            System.out.println(session.account);
            screen.showMessage("Enter pin");
            String pin=screen.getUserInput();

            Boolean validatePin=session.validatePin(pin);

            if(!validatePin){
                screen.showMessage("Invalid pin");
                cardReader.ejectCard();
            }
            else{
                processUserRequest();
            }
        }
    }
    private void processUserRequest(){
        screen.showMessage("1.deposit 2.withdraw 3.Check Balance");
        String selectedOption=keyboard.getInput();
        System.out.println(session.account);
        switch (selectedOption){
            case "3":
                Float balance=session.getBalance();
                screen.showMessage(balance.toString());
                break;
            case "1":
                screen.showMessage("Enter deposit amount");
                try {
                    Float amount = Float.parseFloat(screen.getUserInput());
                    Boolean success = session.deposit(amount);
                    screen.showMessage(success ?
                            "Deposit successful." : "Deposit failed. Technical error.");
                } catch (NumberFormatException e) {
                    screen.showMessage("Invalid amount. Please try again.");
                }
                break;
            case "2":
                screen.showMessage("Enter withdraw amount");
                Float amount = Float.parseFloat(screen.getUserInput());
                Boolean success = session.withdraw(amount);
                screen.showMessage(success ?
                        "Withdraw successful." : "Withdraw failed. Technical error.");

        }

    }

    private Session createSession(Card card){
        return new Session(bankService,card);
    }

}
