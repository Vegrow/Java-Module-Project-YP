import calculator.Calculator;
import calculator.RubleFormatter;
import calculator.UserInteractor;

public class Main {
    public static void main(String[] args) {
        RubleFormatter rubleFormatter = new RubleFormatter();
        UserInteractor userInteractor = new UserInteractor(System.in, System.out, rubleFormatter);
        Calculator calculator = new Calculator(userInteractor);
        calculator.start();
    }
}