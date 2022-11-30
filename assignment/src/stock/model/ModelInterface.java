package stock.model;

import stock.stock.StockInterface;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * This interface represents all the functions the Model takes care of.
 */
public interface ModelInterface {


  /**
   * Method to create stock details in portfolio.
   *
   * @param ticker of type String, company name symbol.
   * @param share  of type int, number of shares.
   */
  void createStock(String ticker, int share, String date, float purchaseCost) throws IOException;

  /**
   * Returns portfolio of stocks and shares.
   *
   * @return portfolio as hashmap.
   */
  HashMap returnPortfolio();

  List<StockInterface> displayPortfolio(String portfolioName) throws IOException;

  /**
   * To compute value of portfolio.
   *
   * @param portfolioName of type HashMap.
   * @param stock2        of type HashMap.
   * @param date          of type String, value to be computed on this day.
   * @return value of portfolio.
   */
  double valueOfPortfolio(HashMap portfolioName, HashMap stock2, String date);

  /**
   * Array list of portfolios.
   *
   * @return Array list.
   */
  ArrayList getPortfolioNames();

  /**
   * To store the details of portfolio into a .csv file.
   *
   * @param stock         of type Hashmap, data about shares.
   * @param portfolioName of type String.
   * @throws IOException if invalid argument is passed.
   */
  void storeCsv(HashMap<String, Integer> stock, String portfolioName)
      throws IOException;

  /**
   * To read details of portfolio from a .csv file.
   *
   * @param b             of type boolean as checker.
   * @param portfolioName of type String
   * @return stock data as HashMap.
   */
  HashMap readingCSV(String portfolioName, boolean b);

  /**
   * Loads data from .csv file and formats it.
   *
   * @return stock data in the form of HashMap.
   */
  HashMap loadDataFromCsv();

  /**
   * To check if portfolio already exists as a file.
   *
   * @param portfolioNames of type ArrayList, names of portfolios.
   * @param portfolioName  of type String, name of portfolio.
   * @return true or false value based on whether portfolio exists.
   */
  boolean checkIfPortfolioFileExists(String portfolioName, ArrayList portfolioNames);

  /**
   * To check if entered ticker is valid.
   *
   * @param loadedCsv of type HashMap containing stock and share data.
   * @param date      of type String, date on which validation occurs.
   * @param ticker    of type String, ticker symbol.
   * @return value based on whether ticker is valid.
   */
  int checkTicker(HashMap loadedCsv, String date, String ticker);


  /**
   * Updates the stockList with duplicate values.
   * @param symbol Stock symbol.
   * @param quantity Stock quantity.
   * @param date Date of purchase of stock.
   * @param purchaseCost Purchase cost of stock for given date.
   */
  void updateStockList(String symbol, int quantity, String date, float purchaseCost);

  /**
   * Uses the AlphaVantage API to check symbol existence.
   * @param symbol Stock symbol.
   * @return returns true if symbol exists else returns false.
   */
  boolean validSymbol(String symbol);

  /**
   * Verifies the quantity is a non-negative whole number.
   * @param quantity Quantity of stock.
   * @return returns true if value is valid and returns false if quantity is negative or fractional.
   */
  boolean validQuantity(String quantity);

  /**
   * Checks the date format and verifies a valid date.
   * @param date Date of purchase of stock.
   * @return returns true for a valid date else returns false.
   */
  boolean validDate(String date);

  /**
   * Uses the AlphaVantage API to get the stock price on a given date.
   * @param symbol Stock symbol.
   * @return returns a String of all the stock values for the last 100 days.
   * @throws IOException input output exception.
   */
  String fetchUrlPrices(String symbol) throws IOException;

  /**
   * Handles the storing of an extended portfolio.
   * @param portfolioName Name of portfolio as set by user.
   * @throws IOException input output exception.
   */
  void storeExPortfolio(String portfolioName, List<StockInterface> stockList) throws IOException;

  boolean validDate2(String date);

  List<StockInterface> loadFile(String currPath) throws IOException, InvalidPathException;

  /**
   * Uses the AlphaVantage API to check valid symbol by getting overview of company data.
   * @param symbol Stock symbol.
   * @return returns true for a valid symbol.
   */
  String fetchUrlOverview(String symbol);

  /**
   * Gets the number of days in the given time period.
   * @param fromDate start date of time period.
   * @param toDate end date of time period.
   * @return return number of days.
   */
  long getNumDays(String fromDate, String toDate);

  /**
   * Splits the time period/duration so that it matches the criteria (>=5 && <=30).
   * @param fromDate start date of time period.
   * @param toDate end date of time period.
   * @param portfolioName name of csv portfolio.
   * @return returns a hashmap with portfolio as key and portfolio value as value.
   * @throws IOException input output exception.
   */
  LinkedHashMap<String, Float> splitDuration(String fromDate, String toDate, String portfolioName)
          throws IOException;

  /**
   * Determines the value of the portfolio.
   * @param stockList List of stocks mentioned in the portfolio.
   * @param date date when the value is to be determined.
   * @return returns the list containing value of all stocks in the portfolio.
   * @throws IOException input output exception.
   * @throws RuntimeException runtime exception.
   */
  List<Float> determinePortfolio(List<StockInterface> stockList, String date)
          throws IOException, RuntimeException;

  /**
   * Gets the price of the stock on the given date from the last 100 days data as provided by the API.
   * @param date date of stock evaluation.
   * @param ticker stock symbol.
   * @return returns the total value of portfolio as a float.
   */
  float getPrices(String date, String ticker);

  /**
   * Gets the total amount invested in a portfolio.
   *
   * @param portfolioName selected portfolio's name.
   * @return
   */
  float getInvestedValue(String portfolioName, float sum) throws IOException;

  /**
   * Function that deletes a stock entry from portfolio when the entire stock is sold.
   * @param portfolioName name of csv file.
   * @param stockList List of stock objects.
   * @param symbol stock symbol.
   * @param date date of purchase of stock.
   * @throws IOException Input Output Exception.
   */
  void sellEntireStock(String portfolioName, List<StockInterface> stockList, String symbol, String date)
          throws IOException;

  /**
   * Function that handles subtraction in quantity of stock when it is partially sold.
   * @param portfolioName name of csv file.
   * @param stockList List of stock objects.
   * @param symbol stock symbol.
   * @param availableQuantity quantity of stock units before selling.
   * @param sellQuantity quantity of stock units to be sold.
   * @param date date of selling stock units.
   * @throws IOException Input Output Exception.
   */
  void sellPartialStock(String portfolioName, List<StockInterface> stockList, String symbol, int availableQuantity,
                        int sellQuantity, String date) throws IOException;

  /**
   * Handles purchase of new stock units to existing portfolio.
   * @param portfolioName name of csv file.
   * @param stockList list of stock objects.
   * @throws IOException Input Output Exception.
   */
  void purchaseStock(String portfolioName, List<StockInterface> stockList) throws IOException;

  /**
   * Calculates the total sale cost of stock units.
   * @param symbol stock symbol.
   * @param quantity no of stock units to be sold.
   * @param date selling date of stock units.
   * @return returns the total amount that is generated by selling stock units.
   * @throws IOException Input Output Exception.
   */
  float totalSell(String symbol, int quantity, String date) throws IOException;

  /**
   * Calculates commission of broker when selling stock units.
   * @param totalSell total price of sold stock units.
   * @param percentCommission commission percentage to be paid to the broker.
   * @return commission value.
   */
  float getCommission(float totalSell, float percentCommission);

  /**
   * Calculates the final earnings after commission is cut from the total price of sold stock units.
   * @param totalSell total price of sold stock units.
   * @param commissionValue commission in dollars($) to  be paid the broker.
   * @return returns the total earnings of the user after selling stock units.
   */
  float getEarnings(float totalSell, float commissionValue);



  /**
   * Total money spent by user while purchasing stock units, includes commission of broker.
   * @param totalInvestment total amount spent, includes commission.
   * @param commissionValue commission in dollars ($) to be paid to the broker.
   * @return returns total amount spent.
   */
  float totalInvestment(float totalInvestment, float commissionValue);

  /**
   * Calculates the commission in dollars ($) based on the base investment plus commission percentage.
   * @param investment base investment that is cost of stock units.
   * @param percentCommission commission percentage of the broker.
   * @return returns the commission in dollars($) to be paid to the broker.
   */
  float getPurchaseCommission(float investment, float percentCommission);

  /**
   * Calculates the base cost of purchasing stock units based on the stock value on the date of purchase
   * and stock symbol and the quantity of stock units bought.
   * @param symbol stock symbol.
   * @param quantity no of stock units bought.
   * @param date purchase date of stock units.
   * @return returns the base cost of buying stock units, excludes commission.
   * @throws IOException Input Output Exception.
   */
  float getInvestment(String symbol, int quantity, String date) throws IOException;

  /**
   * Verifies the quantity of stock units to be sold is not greater than the current stock units held
   * by the user, and also ensures non-negative quantity.
   * @param quantity no of stock units to be sold.
   * @param shares currently held quantity of stock units by the user.
   * @return returns true if quantity is non-negative and not larger than the currently held stock units.
   */
  boolean verifyQuantity(int quantity, int shares);

  /**
   * Checks for negative commission percentage.
   * @param commission commission percentage should be positive and >=0.
   * @return returns true if commission percentage is negative.
   */
  boolean validCommission(float commission);
}
