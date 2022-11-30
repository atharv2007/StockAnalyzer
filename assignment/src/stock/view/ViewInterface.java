package stock.view;

import stock.stock.StockInterface;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * This interface represents all the functions the View takes care of.
 */
public interface ViewInterface {

  /**
   * Prints menu of the portfolio program.
   * @param out of type Appendable, output stream for menu.
   * @throws IOException if illegal argument is passed.
   */
  void printMenu(Appendable out) throws IOException;

  /**
   * Asks user to input name of portfolio.
   * @param out of type Appendable, output stream with message.
   * @throws IOException if illegal argument is passed.
   */
  void printNameOfPortfolio(Appendable out) throws IOException;

  /**
   * Asks user to input ticker symbol.
   * @param out of type Appendable, output stream with message.
   * @throws IOException if illegal argument is passed.
   */
  void printTickerSymbol(Appendable out) throws IOException;

  /**
   * Asks user to input number of shares to buy.
   * @param out of type Appendable, output stream with message.
   * @throws IOException if illegal argument is passed.
   */
  void printNumberOfSharesToBuy(Appendable out) throws IOException;

  /**
   * Asks user to input name of portfolio whose value is needed.
   * @param out of type Appendable, output stream with message.
   * @throws IOException if illegal argument is passed.
   */
  void nameOfPortfolioValue(Appendable out) throws IOException;

  /**
   * Alerts user saying portfolio with this name exists.
   * @param out of type Appendable, output stream with message.
   * @throws IOException if illegal argument is passed.
   */
  void portfolioAlreadyExistsError(Appendable out) throws IOException;

  /**
   * To display names of all portfolios persisting.
   * @param portfolioNames of type ArrayList, names of portfolios.
   * @param out            of type Appendable, output stream with message.
   * @throws IOException if illegal argument is passed.
   */
  void displayNamesOfPortfolio(List portfolioNames, Appendable out) throws IOException;

  /**
   * Asks user if they want to add more stocks to portfolio.
   * @param out of type Appendable, output stream with message.
   * @throws IOException if illegal argument is passed.
   */
  void wantToAddMoreStocks(Appendable out) throws IOException;

  /**
   * Alerts user saying input is out of scope of options in the menu.
   * @param out of type Appendable, output stream with message.
   * @throws IOException if illegal argument is passed.
   */
  void defaultErrorSwitch(Appendable out) throws IOException;

  /**
   * Asks user to enter name of portfolio to be retrieved.
   * @param out of type Appendable, output stream with message.
   * @throws IOException if illegal argument is passed.
   */
  void whichPortfolio(Appendable out) throws IOException;

  /**
   * To append names and values of stocks and shares to HashMap.
   * @param stock2 of type HashMap.
   * @param out    of type Appendable, output stream with message.
   * @throws IOException if illegal argument is passed.
   */
  void showPortfolio(HashMap<String, Integer> stock2, Appendable out) throws IOException;

  /**
   * To let user know the portfolio has been saved successfully.
   * @param out of type Appendable, output stream with message.
   * @throws IOException if illegal argument is passed.
   */
  void portfolioSavedSuccessfully(Appendable out) throws IOException;

  /**
   * Alerts user saying the portfolio of given name does not exist.
   * @param out of type Appendable, output stream with message.
   * @throws IOException if illegal argument is passed.
   */
  void portfolioDoesNotExist(Appendable out) throws IOException;


  /**
   * Asks user to enter date.
   * @param out of type Appendable, output stream with message.
   * @throws IOException if illegal argument is passed.
   */

  void enterDate(Appendable out) throws IOException;

  /**
   * To verify if given date is valid market day in calendar.
   * @param out of type Appendable, output stream with message.
   * @throws IOException IOException if illegal argument is passed.
   */
  void checkDate(Appendable out) throws IOException;

  /**
   * Alerts user saying invalid file path entered, enter valid path.
   * @param out of type Appendable, output stream with message.
   * @throws IOException IOException if illegal argument is passed.
   */
  void pathDoesNotExist(Appendable out) throws Exception;

  /**
   * Tells user the value of selected portfolio.
   * @param value of type double, value of portfolio.
   * @param out   of type Appendable, output stream with message.
   * @throws IOException if illegal argument is passed.
   */
  void valueOfPortfolioIs(double value, Appendable out) throws IOException;

  /**
   * Statement preceding the display of composition of given portfolio.
   * @param out   of type Appendable, output stream with message.
   * @throws IOException if illegal argument is passed.
   */
  void compositionView(Appendable out) throws IOException;

    /**
   * Asks user to enter path to load .csv file from.
   * @param out of type Appendable, output stream with message.
   * @throws IOException IOException if illegal argument is passed.
   */
  void enterPathToLoadCsv(Appendable out) throws IOException;

  /**
   * Asks user to enter a whole number instead of current invalid input.
   * @param out of type Appendable, output stream with message.
   * @throws IOException IOException if illegal argument is passed.
   */
  void enterWholeNumber(Appendable out) throws IOException;

  /**
   * Alerts user saying ticker symbol is invalid, enter correct symbol.
   * @param out of type Appendable, output stream with message.
   * @throws IOException IOException if illegal argument is passed.
   */
  void tickerDoesNotExist(Appendable out) throws IOException;

  /**
   * To tell user that specified file was successfully loaded.
   * @param out of type Appendable, output stream with message.
   * @throws IOException IOException if illegal argument is passed.
   */
  void loadedSuccessfully(Appendable out) throws IOException;

  /**
   * Asks user to not enter an empty string as input.
   * @param out of type Appendable, output stream with message.
   * @throws IOException IOException if illegal argument is passed.
   */
  void dontEnterEmpty(Appendable out) throws IOException;

  /**
   * Instructs user to input the date of purchase of stock units in "yyyy-MM-dd" format.
   * @param out of type Appendable, output stream with message.
   * @throws IOException if illegal argument is passed.
   */
  void printDateOfStock(Appendable out) throws IOException;

  /**
   * Alerts user that the entered date is invalid and cannot be used.
   * @param out of type Appendable, output stream with message.
   * @throws IOException if illegal argument is passed.
   */
  void printInvalidDate(Appendable out) throws IOException;

  /**
   * Instructs user to input the timespan for portfolio performance.
   * @param out of type Appendable, output stream with message.
   * @throws IOException if illegal argument is passed.
   */
  void enterDuration(Appendable out) throws IOException;

  /**
   * Takes in start date for the timespan/time period.
   * @param out of type Appendable, output stream with message.
   * @throws IOException if illegal argument is passed.
   */
  void enterStartDate(Appendable out) throws IOException;

  /**
   * Takes in end date for the timespan.time period.
   * @param out of type Appendable, output stream with message.
   * @throws IOException if illegal argument is passed.
   */
  void enterEndDate(Appendable out) throws IOException;

  /**
   * Handles the portfolio performance over a given timespan.
   * @param fromDate Start date of timespan.
   * @param toDate end date of timespan.
   * @param scale verifies the parameters set for the timespan, >=5 and <=30.
   * @param portName stores portfolio name whose performance is to be measured.
   * @param timestamp hashmap that stores a string as key and float as value.
   */
  void portfolioPerformance(String fromDate, String toDate, int scale, String portName, LinkedHashMap<String,
          Float> timestamp);


  /**
   * Verifies validity of entered date.
   * @param out of type Appendable, output stream with message.
   * @throws IOException if illegal argument is passed.
   */
  void invalidDate(Appendable out) throws IOException;

  /**
   * Verifies validity of the date range.
   * @param out of type Appendable, output stream with message.
   * @throws IOException
   */
  void invalidDateRange(Appendable out) throws IOException;

  void displayStocks(String symbol, int quantity, String date, float purchaseCost, float investment);

  void selectPortfolio(Appendable out) throws IOException;

  void displayValue(float sum);

  void invalidSymbol(Appendable out) throws IOException;

  void printMarketClosed(Appendable out) throws IOException;

  void reEnterDate(Appendable out) throws IOException;

  void printTimestamp(LinkedHashMap<String, Float> timestamp,Appendable out) throws IOException;

  void printStocks(int i, String symbol, String date,Appendable out) throws IOException;

  void enterChoice(Appendable out) throws IOException;

  void sellQuantity(Appendable out) throws IOException;

  void printInvalid(Appendable out) throws IOException;

  void confirmEntireSell(Appendable out) throws IOException;

  void confirmSell(Appendable out) throws IOException;

  void availableQuantity(int quantity, Appendable out) throws IOException;

  void cancelSell(Appendable out) throws IOException;

  void mentionCommission(Appendable out) throws IOException;

  void compositionHeader(Appendable out) throws IOException;

  void compositionPortfolio(int i, StockInterface stock,String quantity, Appendable out) throws IOException;

  void invalidCommission(Appendable out) throws IOException;

  void showEarnings(float earnings,float commission, Appendable out) throws IOException;

  void sellingDate(Appendable out) throws IOException;

  void showInvestment(float totalInvestment, float commissionValue, Appendable out) throws IOException;

  void printInvestedAmount(Appendable out);

  void printInvalidAmount(Appendable out);

  void printInvestedPercentage(Appendable out);
}
