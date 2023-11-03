package calculator;

import java.io.InputStream;

import java.io.PrintStream;
import java.util.Scanner;

public class UserInteractor {

    private final Scanner mScanner;
    private final PrintStream mPrintStream;
    private final RubleFormatter mRubleFormatter;

    public UserInteractor(InputStream is, PrintStream ps, RubleFormatter rubleFormatter) {
        mScanner = new Scanner(is);
        mPrintStream = ps;
        mRubleFormatter = rubleFormatter;
    }

    public int requestGuestNumber() {
        int guestNumber;
        mPrintStream.println("Введите количество человек: ");
        while (true) {
            try {
                guestNumber = Integer.parseInt(mScanner.nextLine());
                if (guestNumber > 1) {
                    return guestNumber;
                } else {
                    mPrintStream.println("Количество человек должно быть больше одного, повторите ввод: ");
                }
            } catch (Exception e) {
                mPrintStream.println("Вы ошиблись при вводе, введите количество человек: ");
            }
        }
    }

    public Product requestProduct() {
        mPrintStream.println("Введите название товара: ");
        String name = mScanner.nextLine();
        double cost = requestProductCost(name);
        return new Product(name, cost);
    }

    public void showProductAddSuccessMessage(Product product) {
        mPrintStream.println("Позиция \"" + product.getName() + "\" успешно добавлена");
    }

    public String requestString() {
        mPrintStream.println("Хотите добавить ещё один товар? Введите ЗАВЕРШИТЬ завершения добавления и переходу к расчёту");
        return mScanner.nextLine();
    }

    public void printResult(String products, double costForPerson){
        String rubbles = mRubleFormatter.getFormatterRubles(costForPerson);
        mPrintStream.println("\n====================================================");
        mPrintStream.println("Добавленные товары:\n" + products);
        mPrintStream.printf("Сумма с каждого: %.2f %s", costForPerson, rubbles);
        mPrintStream.println("\n====================================================");
    }

    private double requestProductCost(String productName) {
        double cost;
        mPrintStream.println("Введите цену для \"" + productName + "\":");
        while (true) {
            try {
                cost = Double.parseDouble(mScanner.nextLine());
                if (cost > 0) {
                    return cost;
                } else {
                    mPrintStream.println("Цена должна быть больше нуля, повторите ввод: ");
                }
            } catch (Exception e) {
                mPrintStream.println("Вы ошиблись при вводе, введите цену заново (копейки указывать через \".\"): ");
            }
        }

    }

}
