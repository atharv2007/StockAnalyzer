package stock.stock;

public class DCAImpl implements DCAPortfolios {

    String symbol;
    float percentage;
    String date;

    float quantity;
    float purchaseCost;
    float investedAmount;

    public DCAImpl(String symbol, float quantity, float percentage, String date, float purchaseCost, float investedAmount) {
        this.percentage = percentage;
        this.quantity = quantity;
        this.symbol = symbol;
        this.date = date;
        this.purchaseCost = purchaseCost;
        this.investedAmount = investedAmount;
    }

    /**
     * Returns the Quantity of the stock.
     *
     * @return an integer showing number of particular stock bought.
     */
    @Override
    public float getPercentage() {
        return this.percentage;
    }

    /**
     * Sets quantity of stock units after purchasing or selling of same stock units.
     *
     * @param percentage updated quantity of stock units.
     */
    @Override
    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    /**
     * Returns the Quantity of the stock.
     *
     * @return an integer showing number of particular stock bought.
     */
    @Override
    public float getQuantity() {
        return this.quantity;
    }

    /**
     * Returns the symbol of the stock.
     *
     * @return a string of the stock symbol.
     */
    @Override
    public String getSymbol() {
        return this.symbol;
    }

    /**
     * Returns the date of purchase of the stock.
     *
     * @return a string of the date of purchase.
     */
    @Override
    public String getDate() {
        return this.date;
    }

    /**
     * Holds the cost of purchase of stock units per unit.
     *
     * @return returns the cost of purchase.
     */
    @Override
    public float getPurchaseCost() {
        return this.purchaseCost;
    }

    /**
     * Stores the amount in dollars($) as invested in the given stock by the user.
     *
     * @return returns the amount  in dollars($).
     */
    @Override
    public float getInvestedAmount() {
        return this.investedAmount;
    }
}
