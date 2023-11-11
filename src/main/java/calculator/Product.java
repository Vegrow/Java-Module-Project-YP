package calculator;

public class Product {
    private final String mName;
    private final double mCost;

    public Product(String name, double cost) {
        mName = name;
        mCost = cost;
    }

    public String getName() {
        return mName;
    }

    public double getCost() {
        return mCost;
    }
}
