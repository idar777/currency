package presentation;

public class Main {
    public static void main(String[] args) {
        MainPresenter mainPresenter = new MainPresenter();
        mainPresenter.rateCurrency();
    }

    public void showError(String err){
        System.out.println(err);
    }

    public void printAnswer(String answer){
        System.out.println(answer);
    }

    public boolean printPrompt(String prompt){
        System.out.println(prompt);
        return true;
    }
}
