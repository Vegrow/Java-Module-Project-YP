package calculator;

public class Calculator {

    private final static String FINISH = "завершить";
    private final UserInteractor mUserInteractor;
    private final StringBuilder mStringBuilder = new StringBuilder();
    private double totalSum;

    public Calculator(UserInteractor userInteractor) {
        mUserInteractor = userInteractor;
    }

    public void start() {
        int totalPersons = mUserInteractor.requestGuestNumber();
        do {
            Product product = mUserInteractor.requestProduct();
            mUserInteractor.showProductAddSuccessMessage(product);
            totalSum += product.getCost();
            mStringBuilder.append(product.getName()).append("\n");
        } while (!FINISH.equalsIgnoreCase(mUserInteractor.requestString()));
        mUserInteractor.printResult(mStringBuilder.toString(), totalSum/totalPersons);
    }
}
