public class Screen implements Display{
    Keyboard keyboard;

    public Screen() {
        this.keyboard = new Keyboard();
    }

    public String getUserInput() {
        return keyboard.getInput();
    }

    @Override
    public void showMessage(String message){
        System.out.println(message);
    }

}
