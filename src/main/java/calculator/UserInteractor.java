package calculator;

import java.io.InputStream;

import java.io.PrintStream;
import java.util.Scanner;

public class UserInteractor {

    /**
     * Минимальное количество символов в названии позиции.
     * Поскольку нигде не указано необходимое значение, взято число 2, т.к. интерфейс на русском,
     * и автор ни разу не видел в чеке позицию из одной буквы, а вот про "щи" знает не понаслышке.
     */
    private static final int MIN_PRODUCT_NAME_LENGTH = 2;
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
        String name = requestProductName();
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

    public void printResult(String products, double costForPerson) {
        String rubbles = mRubleFormatter.getFormatterRubles(costForPerson);
        mPrintStream.println("\n====================================================");
        mPrintStream.println("Добавленные товары:\n" + products);
        mPrintStream.printf("Сумма с каждого: %.2f %s", costForPerson, rubbles);
        mPrintStream.println("\n====================================================");
    }

    /**
     * Привет ревьюер, данный метод возвращает название товара.
     * Поскольку точного тз нет, а разраб (т.е. я) вообще не в курсе,
     * может ли название состоять из одних лишь букв или спецсимволов, то считаем, что может.
     *
     * @return название товара
     */
    private String requestProductName() {
        mPrintStream.println("Введите название товара: ");
        String name;
        while (true) {
            name = mScanner.nextLine();
            if (name.length() >= MIN_PRODUCT_NAME_LENGTH) {
                return name;
            } else {
                mPrintStream.println("Название товара не может быть пустым или состоять только из одной буквы, повторите ввод: ");
            }
        }
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
