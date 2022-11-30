package stock.stock;


/**
 * Interface to define a stock object.
 * It consists of two entities namely Symbol and Quantity.
 * Symbol is the ticker of a stock
 * Quantity is the number of stocks bought
 */
public interface StockInterface {

  /**
  * Returns the Quantity of the stock.
  *
  * @return an integer showing number of particular stock bought.
  */
  int getQuantity();

  /**
   * Sets quantity of stock units after purchasing or selling of same stock units.
   * @param quantity updated quantity of stock units.
   */
  void setQuantity(int quantity);

  /**
   * Returns the symbol of the stock.
   *
   * @return a string of the stock symbol.
   */
  String getSymbol();

  /**
   * Returns the date of purchase of the stock.
   *
   * @return a string of the date of purchase.
   */
  String getDate();

  /**
   * Holds the cost of purchase of stock units per unit.
   * @return returns the cost of purchase.
   */
  float getPurchaseCost();

  /**
   * Stores the amount in dollars($) as invested in the given stock by the user.
   * @return returns the amount  in dollars($).
   */
  float getInvestedAmount();
}
