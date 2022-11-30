package stock.view;

import stock.stock.StockInterface;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import static java.lang.System.out;

/**
 * Implementation of the View interface. View is responsible for all Input/Output streams.
 */
public class View implements ViewInterface {

  @Override
  public void printMenu(Appendable out) throws IOException { // handle the appendable exception
    out.append("***** Hello, welcome to the portfolio maker *****\n");
    out.append("Please Choose one of the options below:\n");
    out.append("0. Print the menu\n");
    out.append("1. Create a portfolio\n");
    out.append("2. Create an Extended Portfolio\n");
    out.append("3. Create an DCA Portfolio\n");
    out.append("4. Show the Portfolio\n");
    out.append("5. Show the Extended Portfolio\n");
    out.append("6. Show the DCA Portfolio\n");
    out.append("7. Examine Composition of Portfolio\n");
    out.append("8. Get the total value of a portfolio\n");
    out.append("9. Get the total investment in a portfolio\n");
    out.append("10. Purchase stock to existing portfolio\n");
    out.append("11. Sell stock from existing portfolio\n");
    out.append("12. Get all the portfolio names\n");
    out.append("13. Custom load the portfolio with the csv\n");
    out.append("14. View performance of portfolio\n");
    out.append("15. End the program\n");
  }

  @Override
  public void portfolioSavedSuccessfully(Appendable out) throws IOException {
    out.append("Your portfolio has been created successfully\n");
  }

  @Override
  public void portfolioDoesNotExist(Appendable out) throws IOException {
    out.append("That portfolio does not exist, try entering a different one\n");
  }

  @Override
  public void compositionView(Appendable out) throws IOException {
    out.append("Composition of a Portfolio :\n");
  }

  @Override
  public void compositionHeader(Appendable out) throws IOException {
    out.append(" Stock Symbol \t Current Quantity \t\t Date of Purchase \t   Purchase Cost \t\t Investment Amount\n");
  }

  @Override
  public void compositionPortfolio(int i, StockInterface stock,String quantity, Appendable out) throws IOException {
    out.append(i + "\t" + stock.getSymbol() + " \t\t      " + quantity + " unit(s)\t\t  " +
            stock.getDate() + "\t\t     $ " + stock.getPurchaseCost() + "\t\t\t $ "+stock.getInvestedAmount()+"\n");
  }

  @Override
  public void enterPathToLoadCsv(Appendable out) throws IOException {
    System.out.println("Enter the path to load the csv file\n");
  }

  @Override
  public void printNameOfPortfolio(Appendable out) throws IOException {
    out.append("Enter the name of the portfolio\n");
  }

  @Override
  public void pathDoesNotExist(Appendable out) throws IOException {
    out.append("Please give the file path with unique portfolio name\n");
  }

  @Override
  public void printTickerSymbol(Appendable out) throws IOException {
    out.append("Enter the ticker symbol of stock you want to purchase.\n");
  }

  @Override
  public void invalidSymbol(Appendable out) throws IOException {
    out.append("Invalid Stock symbol. Try again\n");
  }
  @Override
  public void printNumberOfSharesToBuy(Appendable out) throws IOException {
    out.append("How many number of Shares you want to buy?\n");
  }

  @Override
  public void printDateOfStock(Appendable out) throws  IOException{
    out.append(("Enter Date of Purchase of Stock in YYYY/MM/DD format : \n"));
  }

  @Override
  public void printInvalidDate(Appendable out) throws IOException{
    out.append("Entered Date or Date format is invalid. Try again! \n");
  }

  @Override
  public void printMarketClosed(Appendable out) throws IOException {
    out.append("Sorry the market was closed on the mentioned date. Try again!\n");
  }

  @Override
  public void reEnterDate(Appendable out) throws IOException {
    out.append("Enter a valid date of purchase again\n");
  }

  @Override
  public void nameOfPortfolioValue(Appendable out) throws IOException {
    out.append("Enter the name of the portfolio to get the total value\n");
  }


  @Override
  public void portfolioAlreadyExistsError(Appendable out) throws IOException {
    out.append("Portfolio name already exists, enter a different name\n");
  }

  @Override
  public void defaultErrorSwitch(Appendable out) throws IOException {
    out.append("Invalid menu option: Please enter one of the options in the menu\n");
  }


  @Override
  public void enterDate(Appendable out) throws IOException {
    out.append("Enter the any date in 2017 in the dd/mm/yyyy\n");
  }


  @Override
  public void checkDate(Appendable out) throws IOException {
    out.append("Market is closed on that day, please enter another date\n");
  }

  @Override
  public void valueOfPortfolioIs(double value, Appendable out) throws IOException {
    out.append("The value of the portfolio is: " + value + "\n");
  }

  @Override
  public void displayNamesOfPortfolio(List portfolioNames, Appendable out)
      throws IOException {
    if (portfolioNames.size() == 0) {
      out.append("Sorry, there are no portfolios available to display\n");
    } else {
      out.append("All the portfolios are\n");
      for (int i = 0; i < portfolioNames.size(); i++) {
        System.out.print((i+1) + " " + portfolioNames.get(i) + "\n");
      }
    }
  }

  @Override
  public void wantToAddMoreStocks(Appendable out) throws IOException {
    out.append("Enter y to enter more stocks to portfolio : \n");
  }

  @Override
  public void whichPortfolio(Appendable out) throws IOException {
    out.append("Enter the portfolio name you want to retrieve\n");
  }

  @Override
  public void showPortfolio(HashMap<String, Integer> stock2, Appendable out) throws IOException {
    for (Object name : stock2.keySet()) {

      String value = String.valueOf(stock2.get(name));
      //out.append(name.getClass());

      out.append(name + " " + value + "\n");
    }
  }


  @Override
  public void enterWholeNumber(Appendable out) throws IOException {
    out.append("Input is invalid. Please enter a positive whole number.\n");
  }

  @Override
  public void tickerDoesNotExist(Appendable out) throws IOException {
    out.append("That ticker does not exist, please refer s and p 500 stocks.\n");
  }

  @Override
  public void loadedSuccessfully(Appendable out) throws IOException {
    out.append("The portfolio is loaded successfully.\n");
  }

  @Override
  public void dontEnterEmpty(Appendable out) throws IOException {
    out.append("Dont enter Empty line, please enter a valid input\n");
  }
  @Override
  public void enterDuration(Appendable out) throws IOException {
    out.append("Enter the timespan for observing portfolio performance\n");
  }

  @Override
  public void enterStartDate(Appendable out) throws IOException {
    out.append("Enter start date to observe portfolio performance in YYYY/MM/DD format\n");

  }

  @Override
  public void enterEndDate(Appendable out) throws IOException {
    out.append("Enter end date to observe portfolio performance in YYYY/MM/DD format\n");
  }

  @Override
  public void portfolioPerformance(String fromDate, String toDate, int scale, String portName, LinkedHashMap<String, Float> timestamp){
    out.append("Portfolio performance of " + portName+ "in the given range from " +fromDate + "to " +toDate +"is\n");
            for (String disp: timestamp.keySet()){
              float value = timestamp.get(disp);
              out.append(disp + ": " + "*".repeat((int)value));
            }
            out.append("\n Graph scale: * = " +scale);
  }

  public void invalidDateRange(Appendable out) throws IOException{
    out.append("Date range provided is invalid.\n");
  }
  public void invalidDate(Appendable out) throws IOException{
    out.append("Date for range of performance is invalid.\n");
  }

  @Override
  public void displayStocks(String symbol, int quantity, String date, float purchaseCost, float investment){
    String display = String.format("Stock Symbol : %-5s \t"
            + "\tStock Quantity : %5d \t\t" + "Date of Purchase : %-5s \t\t" + "Purchase Cost : %5f \t\t"+" Investment : %5f\n", symbol, quantity, date, purchaseCost,investment);
    out.append(display);
  }

  @Override
  public void selectPortfolio(Appendable out) throws IOException {
    out.append("Enter the numeric choice for the portfolio\n");
  }

  @Override
  public void displayValue(float sum) {
    DecimalFormat df = new DecimalFormat("###.###");
    out.append("Total Invested for the selected portfolio : $"+df.format(sum)+"\n");
  }

  @Override
  public void printTimestamp(LinkedHashMap<String, Float> timestamp, Appendable out) throws IOException {
    String valDate = timestamp.keySet().toString();
    float value = timestamp.get(valDate);

    out.append(valDate + "\t" + value +"\n");
  }

  @Override
  public void printStocks(int i, String symbol, String date, Appendable out) throws IOException {
    out.append(i + " \t" + symbol + "\t" + date +"\n");
  }

  @Override
  public void enterChoice(Appendable out) throws IOException {
    out.append("Enter numeric choice of stock to sell\n");
    out.append("  Stock Symbol \t Date of Purchase\n");
  }

  @Override
  public void sellQuantity(Appendable out) throws IOException {
    out.append("Enter Quantity to be sold\n");
  }

  @Override
  public void printInvalid(Appendable out) throws IOException {
    out.append("Invalid Quantity. Try again!\n");

  }

  @Override
  public void confirmEntireSell(Appendable out) throws IOException {
    out.append("You are about to sell the entire stock. Are you sure?\n");
  }

  @Override
  public void confirmSell(Appendable out) throws IOException {
    out.append("Are you sell the specific quantity of the given stock?\n");
  }

  @Override
  public void availableQuantity(int quantity, Appendable out) throws IOException {
    out.append("Available Quantity for selected stock : " + quantity + "\n");
  }

  @Override
  public void cancelSell(Appendable out) throws IOException {
    out.append("Good Choice! Hang on to the stock and wait for it grow!!\n");
  }

  @Override
  public void mentionCommission(Appendable out) throws IOException {
    out.append("Enter % commission charged\n");
  }

  @Override
  public void invalidCommission(Appendable out) throws IOException {
    out.append("Commission % value cannot be negative. It can >=0 \n");
  }

  @Override
  public void showInvestment(float totalInvestment, float commissionValue, Appendable out) throws IOException {
    out.append("Commission Value : $ " + commissionValue + "\n");
    out.append("Total Investment (with Commission Value) : $ " + totalInvestment + "\n");
  }
  @Override
  public void showEarnings(float earnings, float commissionValue, Appendable out) throws IOException {
    out.append("Earnings from selling stocks (after commission) : $ " + earnings + "\n");
    out.append("Commission charged : $ " + commissionValue + "\n");
  }

  @Override
  public void sellingDate(Appendable out) throws IOException {
    out.append("Enter Date of selling stocks\n");
  }

}
