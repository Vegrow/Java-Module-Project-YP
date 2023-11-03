package calculator;

public class RubleFormatter {
    public String getFormatterRubles(double amount) {
        double roundedAmount = Math.floor(amount);
        var preLastDigit = roundedAmount % 100 / 10;
        if (preLastDigit == 1) {
            return "рублей";
        }

        return switch ((int) roundedAmount % 10) {
            case 1 -> "рубль";
            case 2, 3, 4 -> "рубля";
            default -> "рублей";
        };
    }
}
