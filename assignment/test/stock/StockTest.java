// package stock;

// // import org.junit.Test;
// import stock.controller.Controller;
// import stock.controller.ControllerInterface;
// import stock.model.ModelInterface;
// import stock.model.StockModel;
// import stock.stock.StockInterface;
// import stock.view.View;
// import stock.view.ViewInterface;

// import java.io.IOException;
// import java.io.Reader;
// import java.io.StringReader;
// import java.nio.file.InvalidPathException;
// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.LinkedHashMap;
// import java.util.List;

// // import static org.junit.Assert.assertEquals;

// /**
//  * Test class for the stock portfoliomaker.
//  */
// public class StockTest {

//   abstract class LoggingModel1 implements ModelInterface {

//     private StringBuilder log;

//     private HashMap<String, Integer> stock = new HashMap<>();

//     public LoggingModel1(StringBuilder log) {
//       this.log = log;
//     }

//     @Override
//     public void createStock(String ticker, int share, String date, float purchaseCost) {
//       log.append("IN THE CREATESTOCK METHOD");
//       log.append("\n");
//       log.append("Received:" + " " + ticker + " " + share);
//       log.append("\n");
//       stock.put(ticker, share);
//     }

//     @Override
//     public HashMap returnPortfolio() {
//       return stock;
//     }

//     @Override
//     public List<StockInterface> displayPortfolio(String portfolioName) throws IOException {
//       return null;
//     }

//     @Override
//     public double valueOfPortfolio(HashMap stockOfUser, HashMap loadedCsv, String date) {
//       log.append("\n");
//       log.append("In VALUEOFPORTFOLIO METHOD--");
//       log.append("\n");
//       log.append("stockOfuser -- ");
//       for (Object key : stockOfUser.keySet()) {
//         log.append(key + String.valueOf(stockOfUser.get(key)) + " ");
//       }
//       log.append("\n");

//       log.append("Date --- ");
//       log.append("\n");
//       log.append(date);
//       return 0;
//     }

//     @Override
//     public ArrayList getPortfolioNames() {
//       ArrayList<String> str = new ArrayList<String>();
//       str.add("college.csv");
//       str.add("qqqq.csv");
//       return str;
//     }


//     @Override
//     public void storeCsv(HashMap<String, Integer> stock, String portfolioName) throws IOException {
//       log.append("\n");
//       log.append("IN THE STORECSV\n");
//       for (Object key : stock.keySet()) {
//         log.append(key + String.valueOf(stock.get(key)) + " ");
//       }
//       log.append("\n");
//       log.append("PortfolioName:" + portfolioName);
//       log.append("\n");
//     }

//     @Override
//     public HashMap readingCSV(String portfolioName, boolean b) {
//       log.append("\n");
//       log.append("Method readingCSV " + "\n" + "PortfolioName" + portfolioName);
//       log.append("\n");
//       return stock;
//     }

//     @Override
//     public HashMap loadDataFromCsv() {
//       HashMap<String, Integer> str = new HashMap<>();
//       str.put("GOOGL,3/2/2017", 10);
//       return str;
//     }

//     @Override
//     public boolean checkIfPortfolioFileExists(String portfolioName, ArrayList portfolioNames) {
//       log.append("\n");
//       log.append("IN CHECKPORTFOLIOEXISTS\n");

//       log.append("PortfolioName --" + portfolioName);
//       log.append("portfolio_names -- ");
//       for (int i = 0; i < portfolioNames.size(); i++) {
//         log.append(portfolioNames.get(i));
//       }
//       log.append("\n");

//       return portfolioNames.contains(portfolioName + ".csv");
//     }

//     @Override
//     public int checkTicker(HashMap loadedCsv, String date, String ticker) {
//       log.append("\n");
//       log.append("IN THE CHECKTICKER\n");
//       for (Object key : loadedCsv.keySet()) {
//         log.append(key + String.valueOf(loadedCsv.get(key)) + " ");
//       }
//       log.append("\n");
//       return 0;
//     }

//     @Override
//     public void updateStockList(String symbol, int quantity, String date, float purchaseCost) {
//       return;
//     }

//     @Override
//     public boolean validSymbol(String symbol) {
//       return false;
//     }

//     @Override
//     public boolean validQuantity(String quantity) {
//       return false;
//     }

//     @Override
//     public boolean validDate(String date) {
//       return false;
//     }

//     @Override
//     public String fetchUrlPrices(String symbol) throws IOException {
//       return null;
//     }

//     @Override
//     public void storeExPortfolio(String portfolioName, List<StockInterface> stockList) throws
//             IOException {
//       return;
//     }

//     @Override
//     public String fetchUrlOverview(String symbol) {
//       return null;
//     }

//     @Override
//     public long getNumDays(String fromDate, String toDate) {
//       return 0;
//     }

//     /**
//      * Splits the time period/duration so that it matches the criteria (>=5 && <=30).
//      *
//      * @param fromDate      start date of time period.
//      * @param toDate        end date of time period.
//      * @param portfolioName name of csv portfolio.
//      * @return returns a hashmap with portfolio as key and portfolio value as value.
//      * @throws IOException input output exception.
//      */
//     @Override
//     public LinkedHashMap<String, Float> splitDuration(String fromDate, String toDate,
//                                                       String portfolioName) throws IOException {
//       return null;
//     }

//     /**
//      * Determines the value of the portfolio.
//      *
//      * @param stockList List of stocks mentioned in the portfolio.
//      * @param date      date when the value is to be determined.
//      * @return returns the list containing value of all stocks in the portfolio.
//      * @throws IOException      input output exception.
//      * @throws RuntimeException runtime exception.
//      */
//     @Override
//     public List<Float> determinePortfolio(List<StockInterface> stockList, String date)
//             throws IOException, RuntimeException {
//       return null;
//     }

//     /**
//      * Gets the price of the stock on the given date from the last 100
//      * days data as provided by the API.
//      *
//      * @param date   date of stock evaluation.
//      * @param ticker stock symbol.
//      * @return returns the total value of portfolio as a float.
//      */
//     @Override
//     public float getPrices(String date, String ticker) {
//       return 0;
//     }

//     /**
//      * Gets the total amount invested in a portfolio.
//      *
//      * @param portfolioName selected portfolio's name.
//      * @param sum           stores sum of invested amount.
//      * @return returns the total invested value.
//      */
//     @Override
//     public float getInvestedValue(String portfolioName, float sum) throws IOException {
//       return 0;
//     }


//     @Override
//     public void purchaseStock(String portfolioName, List<StockInterface> stockList)
//             throws IOException {
//       //handles purchasing of stock units to existing portfolio.
//     }


//     @Test
//     public void testControllerAllTheMethodsInThe() {
//       StringBuffer out = new StringBuffer();
//       Reader in = new StringReader("1\nyyy\ngoogl\n10\nno\n3\nyyy\n10/2/2017\n6\n");
//       String expectedLog = "\n"
//               + "IN CHECKPORTFOLIOEXISTS\n"
//               + "PortfolioName --yyyportfolio_names -- college.csvqqqq.csv\n"
//               + "\n"
//               + "IN THE CHECKTICKER\n"
//               + "GOOGL,3/2/201710 \n"
//               + "IN THE CREATESTOCK METHOD\n"
//               + "Received: GOOGL 10\n"
//               + "\n"
//               + "IN THE STORECSV\n"
//               + "GOOGL10 \n"
//               + "PortfolioName:yyy\n"
//               + "\n"
//               + "IN CHECKPORTFOLIOEXISTS\n"
//               + "PortfolioName --yyyportfolio_names -- college.csvqqqq.csvyyy.csv\n"
//               + "\n"
//               + "Method readingCSV \n"
//               + "PortfolioNameyyy\n"
//               + "\n"
//               + "In VALUEOFPORTFOLIO METHOD--\n"
//               + "stockOfuser -- GOOGL10 \n"
//               + "Date --- \n"
//               + "10/2/2017";

//       StringBuilder modelLog = new StringBuilder();

//       ModelInterface mockObj = new LoggingModel1(modelLog) {
//         @Override
//         public List<StockInterface> loadFile(String currPath) throws IOException, InvalidPathException {
//           return null;
//         }

//         /**
//          * Function that deletes a stock entry from portfolio when the entire stock is sold.
//          *
//          * @param portfolioName name of csv file.
//          * @param stockList     List of stock objects.
//          * @param symbol        stock symbol.
//          * @param date          date of purchase of stock.
//          * @throws IOException Input Output Exception.
//          */
//         @Override
//         public void sellEntireStock(String portfolioName, List<StockInterface> stockList, String symbol, String date) throws IOException {

//         }

//         /**
//          * Function that handles subtraction in quantity of stock when it is partially sold.
//          *
//          * @param portfolioName     name of csv file.
//          * @param stockList         List of stock objects.
//          * @param symbol            stock symbol.
//          * @param availableQuantity quantity of stock units before selling.
//          * @param sellQuantity      quantity of stock units to be sold.
//          * @param date              date of selling stock units.
//          * @throws IOException Input Output Exception.
//          */
//         @Override
//         public void sellPartialStock(String portfolioName, List<StockInterface> stockList, String symbol, int availableQuantity, int sellQuantity, String date) throws IOException {

//         }

//         /**
//          * Calculates the total sale cost of stock units.
//          *
//          * @param symbol   stock symbol.
//          * @param quantity no of stock units to be sold.
//          * @param date     selling date of stock units.
//          * @return returns the total amount that is generated by selling stock units.
//          * @throws IOException Input Output Exception.
//          */
//         @Override
//         public float totalSell(String symbol, int quantity, String date) throws IOException {
//           return 0;
//         }

//         /**
//          * Calculates commission of broker when selling stock units.
//          *
//          * @param totalSell         total price of sold stock units.
//          * @param percentCommission commission percentage to be paid to the broker.
//          * @return commission value.
//          */
//         @Override
//         public float getCommission(float totalSell, float percentCommission) {
//           return 0;
//         }

//         /**
//          * Calculates the final earnings after commission is cut from the total price of sold stock units.
//          *
//          * @param totalSell       total price of sold stock units.
//          * @param commissionValue commission in dollars($) to  be paid the broker.
//          * @return returns the total earnings of the user after selling stock units.
//          */
//         @Override
//         public float getEarnings(float totalSell, float commissionValue) {
//           return 0;
//         }



//         /**
//          * Total money spent by user while purchasing stock units, includes commission of broker.
//          *
//          * @param totalInvestment total amount spent, includes commission.
//          * @param commissionValue commission in dollars ($) to be paid to the broker.
//          * @return returns total amount spent.
//          */
//         @Override
//         public float totalInvestment(float totalInvestment, float commissionValue) {
//           return 0;
//         }

//         /**
//          * Calculates the commission in dollars ($) based on the base investment plus commission percentage.
//          *
//          * @param investment        base investment that is cost of stock units.
//          * @param percentCommission commission percentage of the broker.
//          * @return returns the commission in dollars($) to be paid to the broker.
//          */
//         @Override
//         public float getPurchaseCommission(float investment, float percentCommission) {
//           return 0;
//         }

//         /**
//          * Calculates the base cost of purchasing stock units based on the stock value on the date of purchase
//          * and stock symbol and the quantity of stock units bought.
//          *
//          * @param symbol   stock symbol.
//          * @param quantity no of stock units bought.
//          * @param date     purchase date of stock units.
//          * @return returns the base cost of buying stock units, excludes commission.
//          * @throws IOException Input Output Exception.
//          */
//         @Override
//         public float getInvestment(String symbol, int quantity, String date) throws IOException {
//           return 0;
//         }

//         /**
//          * Verifies the quantity of stock units to be sold is not greater than the current stock units held
//          * by the user, and also ensures non-negative quantity.
//          *
//          * @param quantity no of stock units to be sold.
//          * @param shares   currently held quantity of stock units by the user.
//          * @return returns true if quantity is non-negative and not larger than the currently held stock units.
//          */
//         @Override
//         public boolean verifyQuantity(int quantity, int shares) {
//           return false;
//         }

//         /**
//          * Checks for negative commission percentage.
//          *
//          * @param commission commission percentage should be positive and >=0.
//          * @return returns true if commission percentage is negative.
//          */
//         @Override
//         public boolean validCommission(float commission) {
//           return false;
//         }
//       };
//       ViewInterface view = new View();

//       ControllerInterface obj = new Controller(view, mockObj, in, out);
//       try {
//         obj.goController();
//       } catch (Exception e) {
//         // throw new RuntimeException(e);
//       }

//       assertEquals(expectedLog, modelLog.toString());
//     }

//     @Test
//     public void testCreatePortfolioBasic() {
//       StringBuffer out = new StringBuffer();
//       Reader in = new StringReader("1\nabc6\nMSFT\n10\nno\n6\n");
//       ViewInterface view = new View();
//       ModelInterface model = new StockModel();
//       ControllerInterface obj = new Controller(view, model, in, out);
//       try {
//         obj.goController();
//       } catch (IOException e) {
//         throw new RuntimeException(e);
//       } catch (Exception e) {
//         throw new RuntimeException(e);
//       }
//       String expected = "***** Hello, welcome to the portfolio maker *****\n"
//               + "Please Choose one of the options below:\n"
//               + "0. Print the menu\n"
//               + "1. Create a portfolio\n"
//               + "2. Show the portfolio\n"
//               + "3. Get the total value of a portfolio\n"
//               + "4. Get all the portfolio names\n"
//               + "5. Custom load the portfolio with the csv\n"
//               + "6. End the program\n"
//               + "Enter the name of the portfolio\n"
//               + "Enter the ticker symbol\n"
//               + "How many number of Shares you want to buy?\n"
//               + "Type yes if you want to add more stocks and no to stop adding to the portfolio\n"

//               + "Your portfolio has been created successfully\n"
//               + "***** Hello, welcome to the portfolio maker *****\n"
//               + "Please Choose one of the options below:\n"
//               + "0. Print the menu\n"
//               + "1. Create a portfolio\n"
//               + "2. Show the portfolio\n"
//               + "3. Get the total value of a portfolio\n"
//               + "4. Get all the portfolio names\n"
//               + "5. Custom load the portfolio with the csv\n"
//               + "6. End the program\n";
//       assertEquals(expected, out.toString());

//     }

//     @Test
//     public void testEndProgram() {
//       StringBuffer out = new StringBuffer();
//       Reader in = new StringReader("0\n6\n");
//       ViewInterface view = new View();
//       ModelInterface model = new StockModel();
//       ControllerInterface obj = new Controller(view, model, in, out);
//       try {
//         obj.goController();
//       } catch (IOException e) {
//         throw new RuntimeException(e);
//       } catch (Exception e) {
//         throw new RuntimeException(e);
//       }
//       String expected = "**** Hello, welcome to the portfolio maker ****\n" +
//               "Please Choose one of the options below:\n" +
//               "0. Print the menu\n" +
//               "1. Create a portfolio\n" +
//               "2. Show the portfolio\n" +
//               "3. Get the total value of a portfolio\n" +
//               "4. Get all the portfolio names\n" +
//               "5. Custom load the portfolio with the csv\n" +
//               "6. End the program\n" +
//               "***** Hello, welcome to the portfolio maker *****\n"
//               + "Please Choose one of the options below:\n"
//               + "0. Print the menu\n"
//               + "1. Create a portfolio\n"
//               + "2. Show the portfolio\n"
//               + "3. Get the total value of a portfolio\n"
//               + "4. Get all the portfolio names\n"
//               + "5. Custom load the portfolio with the csv\n"
//               + "6. End the program\n"
//               + "***** Hello, welcome to the portfolio maker *****\n"
//               + "Please Choose one of the options below:\n"
//               + "0. Print the menu\n"
//               + "1. Create a portfolio\n"
//               + "2. Show the portfolio\n"
//               + "3. Get the total value of a portfolio\n"
//               + "4. Get all the portfolio names\n"
//               + "5. Custom load the portfolio with the csv\n"
//               + "6. End the program\n";
//       assertEquals(expected, out.toString());
//     }

//     @Test
//     public void testNegativeShares() {
//       StringBuffer out = new StringBuffer();
//       Reader in = new StringReader("1\nabc10\nMSFT\n-5\n10\nno\n6\n");
//       ViewInterface view = new View();
//       ModelInterface model = new StockModel();
//       ControllerInterface obj = new Controller(view, model, in, out);
//       try {
//         obj.goController();
//       } catch (IOException e) {
//         throw new RuntimeException(e);
//       } catch (Exception e) {
//         throw new RuntimeException(e);
//       }
//       String expected = "***** Hello, welcome to the portfolio maker *****\n"
//               + "Please Choose one of the options below:\n"
//               + "0. Print the menu\n"
//               + "1. Create a portfolio\n"
//               + "2. Show the portfolio\n"
//               + "3. Get the total value of a portfolio\n"
//               + "4. Get all the portfolio names\n"
//               + "5. Custom load the portfolio with the csv\n"
//               + "6. End the program\n"
//               + "Enter the name of the portfolio\n"
//               + "Enter the ticker symbol\n"
//               + "How many number of Shares you want to buy?\n"
//               + "Input is invalid. Please enter a positive whole number.\n"
//               + "Type yes if you want to add more stocks and no to stop adding to the portfolio\n"
//               + "Your portfolio has been created successfully\n" +
//               "***** Hello, welcome to the portfolio maker *****\n" +
//               "Please Choose one of the options below:\n" +
//               "0. Print the menu\n" +
//               "1. Create a portfolio\n" +
//               "2. Show the portfolio\n" +
//               "3. Get the total value of a portfolio\n" +
//               "4. Get all the portfolio names\n" +
//               "5. Custom load the portfolio with the csv\n" +
//               "6. End the program\n";
//       assertEquals(expected, out.toString());

//     }

//     @Test
//     public void testFractionalShares() {
//       StringBuffer out = new StringBuffer();
//       Reader in = new StringReader("1\nabc131\nMSFT\n9.5\n10\nno\n6\n");
//       ViewInterface view = new View();
//       ModelInterface model = new StockModel();
//       ControllerInterface obj = new Controller(view, model, in, out);
//       try {
//         obj.goController();
//       } catch (Exception e) {
//         throw new RuntimeException(e);
//       }
//       String expected = "***** Hello, welcome to the portfolio maker *****\n"
//               + "Please Choose one of the options below:\n"
//               + "0. Print the menu\n"
//               + "1. Create a portfolio\n"
//               + "2. Show the portfolio\n"
//               + "3. Get the total value of a portfolio\n"
//               + "4. Get all the portfolio names\n"
//               + "5. Custom load the portfolio with the csv\n"
//               + "6. End the program\n"
//               + "Enter the name of the portfolio\n"
//               + "Enter the ticker symbol\n"
//               + "How many number of Shares you want to buy?\n"
//               + "Input is invalid. Please enter a positive whole number.\n"
//               + "Type yes if you want to add more stocks and no to stop adding to the portfolio\n"
//               + "Your portfolio has been created successfully\n" +
//               "***** Hello, welcome to the portfolio maker *****\n" +
//               "Please Choose one of the options below:\n" +
//               "0. Print the menu\n" +
//               "1. Create a portfolio\n" +
//               "2. Show the portfolio\n" +
//               "3. Get the total value of a portfolio\n"
//               + "4. Get all the portfolio names\n" +
//               "5. Custom load the portfolio with the csv\n" +
//               "6. End the program\n";
//       assertEquals(expected, out.toString());
//     }

//     @Test
//     public void testStringShares() {
//       StringBuffer out = new StringBuffer();
//       Reader in = new StringReader("1\nabc1345\nMSFT\nqwert\n10\nno\n6\n");
//       ViewInterface view = new View();
//       ModelInterface model = new StockModel();
//       ControllerInterface obj = new Controller(view, model, in, out);
//       try {
//         obj.goController();
//       } catch (Exception e) {
//         throw new RuntimeException(e);
//       }
//       String expected = "***** Hello, welcome to the portfolio maker *****\n"
//               + "Please Choose one of the options below:\n"
//               + "0. Print the menu\n"
//               + "1. Create a portfolio\n"
//               + "2. Show the portfolio\n"
//               + "3. Get the total value of a portfolio\n"
//               + "4. Get all the portfolio names\n"
//               + "5. Custom load the portfolio with the csv\n"
//               + "6. End the program\n"
//               + "Enter the name of the portfolio\n"
//               + "Enter the ticker symbol\n"
//               + "How many number of Shares you want to buy?\n"
//               + "Input is invalid. Please enter a positive whole number.\n"
//               + "Type yes if you want to add more stocks and no to stop adding to the portfolio\n"
//               + "Your portfolio has been created successfully\n" +
//               "***** Hello, welcome to the portfolio maker *****\n" +
//               "Please Choose one of the options below:\n" +
//               "0. Print the menu\n" +
//               "1. Create a portfolio\n" +
//               "2. Show the portfolio\n" +
//               "3. Get the total value of a portfolio\n" +
//               "4. Get all the portfolio names\n" +
//               "5. Custom load the portfolio with the csv\n" +
//               "6. End the program\n";
//       assertEquals(expected, out.toString());
//     }

//     @Test
//     public void testShowPortfolio() {
//       StringBuffer out = new StringBuffer();
//       Reader in = new StringReader(
//               "1\nabc991\nMSFT\n10\nyes\naapl\n53\nyes\naap\n437\nno\n2\nabc991\n6\n");
//       ViewInterface view = new View();
//       ModelInterface model = new StockModel();
//       ControllerInterface obj = new Controller(view, model, in, out);
//       try {
//         obj.goController();
//       } catch (Exception e) {
//         throw new RuntimeException(e);
//       }
//       String expected = "***** Hello, welcome to the portfolio maker *****\n"
//               + "Please Choose one of the options below:\n"
//               + "0. Print the menu\n"
//               + "1. Create a portfolio\n"
//               + "2. Show the portfolio\n"
//               + "3. Get the total value of a portfolio\n"
//               + "4. Get all the portfolio names\n"
//               + "5. Custom load the portfolio with the csv\n"
//               + "6. End the program\n"
//               + "Enter the name of the portfolio\n"
//               + "Enter the ticker symbol\n"
//               + "How many number of Shares you want to buy?\n"
//               + "Type yes if you want to add more stocks and no to stop adding to the portfolio\n"
//               + "Enter the ticker symbol\n"
//               + "How many number of Shares you want to buy?\n"
//               + "Type yes if you want to add more stocks and no to stop adding to the portfolio\n"
//               + "Enter the ticker symbol\n"
//               + "How many number of Shares you want to buy?\n"
//               + "Type yes if you want to add more stocks and no to stop adding to the portfolio\n"
//               + "Your portfolio has been created successfully\n" +
//               "***** Hello, welcome to the portfolio maker *****\n" +
//               "Please Choose one of the options below:\n" +
//               "0. Print the menu\n" +
//               "1. Create a portfolio\n" +
//               "2. Show the portfolio\n" +
//               "3. Get the total value of a portfolio\n" +
//               "4. Get all the portfolio names\n" +
//               "5. Custom load the portfolio with the csv\n" +
//               "6. End the program\n"
//               + "Enter the portfolio name you want to retrieve\n"
//               + "MSFT 10\n" +
//               "AAP 437\n" +
//               "AAPL 53\n";
//       assertEquals(expected, out.toString());

//     }

//     //testValueOfPortfolio uses a previously created portfolio.
//     // A newly created portfolio will not have a value on a previous date.
//     // Run this test case AFTER previous test cases.
//     @Test
//     public void testValueOfPortfolio() {
//       StringBuffer out = new StringBuffer();
//       Reader in = new StringReader("3\nabc24\n22/5/2017\n6\n");
//       ViewInterface view = new View();
//       ModelInterface model = new StockModel();
//       ControllerInterface obj = new Controller(view, model, in, out);
//       try {
//         obj.goController();
//       } catch (Exception e) {
//         throw new RuntimeException(e);
//       }
//       String expected = "***** Hello, welcome to the portfolio maker *****\n"
//               + "Please Choose one of the options below:\n"
//               + "0. Print the menu\n"
//               + "1. Create a portfolio\n"
//               + "2. Show the portfolio\n"
//               + "3. Get the total value of a portfolio\n"
//               + "4. Get all the portfolio names\n"
//               + "5. Custom load the portfolio with the csv\n"
//               + "6. End the program\n"

//               + "Enter the name of the portfolio to get the total value\n"
//               + "Enter the any date in 2017 in the dd/mm/yyyy\n"
//               + "The value of the portfolio is: 74784.2\n" +
//               "***** Hello, welcome to the portfolio maker *****\n" +
//               "Please Choose one of the options below:\n" +
//               "0. Print the menu\n" +
//               "1. Create a portfolio\n" +
//               "2. Show the portfolio\n" +
//               "3. Get the total value of a portfolio\n" +
//               "4. Get all the portfolio names\n" +
//               "5. Custom load the portfolio with the csv\n" +
//               "6. End the program\n";
//       assertEquals(expected, out.toString());
//     }

//     @Test
//     public void testComplexTest() {
//       StringBuffer out = new StringBuffer();
//       Reader in = new StringReader(
//               "1\nabc3333\nMSFT\n70\nyes\naapl\n54\nyes\ngoog\n100\nno\n1\nabc4444\ngoog\n77\nyes"
//                       + "\nmsft\n99\nno\n1\nabc5555\naap\n50\n"
//                       + "no\n2\nabc3333\n3\nabc4444\n22/11/2017\n6\n");
//       ViewInterface view = new View();
//       ModelInterface model = new StockModel();
//       ControllerInterface obj = new Controller(view, model, in, out);
//       try {
//         obj.goController();
//       } catch (Exception e) {
//         throw new RuntimeException(e);
//       }
//       String expected = "***** Hello, welcome to the portfolio maker *****\n"
//               + "Please Choose one of the options below:\n"
//               + "0. Print the menu\n"
//               + "1. Create a portfolio\n"
//               + "2. Show the portfolio\n"
//               + "3. Get the total value of a portfolio\n"
//               + "4. Get all the portfolio names\n"
//               + "5. Custom load the portfolio with the csv\n"
//               + "6. End the program\n"
//               + "Enter the name of the portfolio\n"
//               + "Enter the ticker symbol\n"
//               + "How many number of Shares you want to buy?\n"
//               + "Type yes if you want to add more stocks and no to stop adding to the portfolio\n"
//               + "Enter the ticker symbol\n"
//               + "How many number of Shares you want to buy?\n"
//               + "Type yes if you want to add more stocks and no to stop adding to the portfolio\n"
//               + "Enter the ticker symbol\n"
//               + "How many number of Shares you want to buy?\n"
//               + "Type yes if you want to add more stocks and no to stop adding to the portfolio\n"
//               + "Your portfolio has been created successfully\n" +
//               "***** Hello, welcome to the portfolio maker *****\n" +
//               "Please Choose one of the options below:\n" +
//               "0. Print the menu\n" +
//               "1. Create a portfolio\n" +
//               "2. Show the portfolio\n" +
//               "3. Get the total value of a portfolio\n" +
//               "4. Get all the portfolio names\n" +
//               "5. Custom load the portfolio with the csv\n" +
//               "6. End the program\n"
//               + "Enter the name of the portfolio\n"
//               + "Enter the ticker symbol\n"
//               + "How many number of Shares you want to buy?\n" +
//               "Type yes if you want to add more stocks and no to stop adding to the portfolio\n"
//               + "Enter the ticker symbol\n"
//               + "How many number of Shares you want to buy?\n"
//               + "Type yes if you want to add more stocks and no to stop adding to the portfolio\n"
//               + "Your portfolio has been created successfully\n" +
//               "***** Hello, welcome to the portfolio maker *****\n" +
//               "Please Choose one of the options below:\n" +
//               "0. Print the menu\n" +
//               "1. Create a portfolio\n" +
//               "2. Show the portfolio\n" +
//               "3. Get the total value of a portfolio\n" +
//               "4. Get all the portfolio names\n" +
//               "5. Custom load the portfolio with the csv\n" +
//               "6. End the program\n"
//               + "Enter the name of the portfolio\n"
//               + "Enter the ticker symbol\n"
//               + "How many number of Shares you want to buy?\n"
//               + "Type yes if you want to add more stocks and no to stop adding to the portfolio\n" +
//               "Your portfolio has been created successfully\n" +
//               "***** Hello, welcome to the portfolio maker *****\n" +
//               "Please Choose one of the options below:\n" +
//               "0. Print the menu\n" +
//               "1. Create a portfolio\n" +
//               "2. Show the portfolio\n" +
//               "3. Get the total value of a portfolio\n" +
//               "4. Get all the portfolio names\n" +
//               "5. Custom load the portfolio with the csv\n" +
//               "6. End the program\n"
//               + "Enter the portfolio name you want to retrieve\n"
//               + "MSFT 70\n" +
//               "GOOG 100\n" +
//               "AAPL 54\n" +
//               "***** Hello, welcome to the portfolio maker *****\n" +
//               "Please Choose one of the options below:\n" +
//               "0. Print the menu\n" +
//               "1. Create a portfolio\n" +
//               "2. Show the portfolio\n" +
//               "3. Get the total value of a portfolio\n" +
//               "4. Get all the portfolio names\n" +
//               "5. Custom load the portfolio with the csv\n" +
//               "6. End the program\n"
//               + "Enter the name of the portfolio to get the total value\n"
//               + "Enter the any date in 2017 in the dd/mm/yyyy\n"
//               + "The value of the portfolio is: 206723.71\n" +
//               "***** Hello, welcome to the portfolio maker *****\n" +
//               "Please Choose one of the options below:\n" +
//               "0. Print the menu\n" +
//               "1. Create a portfolio\n" +
//               "2. Show the portfolio\n" +
//               "3. Get the total value of a portfolio\n" +
//               "4. Get all the portfolio names\n" +
//               "5. Custom load the portfolio with the csv\n" +
//               "6. End the program\n";
//       assertEquals(expected, out.toString());
//     }

//     @Test
//     public void testNonExistentPortfolio() {
//       StringBuffer out = new StringBuffer();
//       Reader in = new StringReader("2\nrrrr\ntttt\nabc\n6\n");
//       ViewInterface view = new View();
//       ModelInterface model = new StockModel();
//       ControllerInterface obj = new Controller(view, model, in, out);
//       try {
//         obj.goController();
//       } catch (Exception e) {
//         throw new RuntimeException(e);
//       }
//       String expected = "***** Hello, welcome to the portfolio maker *****\n"
//               + "Please Choose one of the options below:\n"
//               + "0. Print the menu\n"
//               + "1. Create a portfolio\n"
//               + "2. Show the portfolio\n"
//               + "3. Get the total value of a portfolio\n"
//               + "4. Get all the portfolio names\n"
//               + "5. Custom load the portfolio with the csv\n"
//               + "6. End the program\n"
//               + "Enter the portfolio name you want to retrieve\n"
//               + "That portfolio does not exist, try entering a different one\n"
//               + "That portfolio does not exist, try entering a different one\n"
//               + "GOOG 10\n" +
//               "ASDF 2\n" +
//               "FB 10\n" +
//               "***** Hello, welcome to the portfolio maker *****\n" +
//               "Please Choose one of the options below:\n" +
//               "0. Print the menu\n" +
//               "1. Create a portfolio\n" +
//               "2. Show the portfolio\n" +
//               "3. Get the total value of a portfolio\n" +
//               "4. Get all the portfolio names\n" +
//               "5. Custom load the portfolio with the csv\n" +
//               "6. End the program\n";
//       assertEquals(expected, out.toString());
//     }

//     @Test
//     public void testPrintMenu() {
//       StringBuffer out = new StringBuffer();
//       Reader in = new StringReader("0\n0\n6\n");
//       ViewInterface view = new View();
//       ModelInterface model = new StockModel();
//       ControllerInterface obj = new Controller(view, model, in, out);
//       try {
//         obj.goController();
//       } catch (Exception e) {
//         throw new RuntimeException(e);
//       }
//       String expected = "***** Hello, welcome to the portfolio maker *****\n"
//               + "Please Choose one of the options below:\n"
//               + "0. Print the menu\n"
//               + "1. Create a portfolio\n"
//               + "2. Show the portfolio\n"
//               + "3. Get the total value of a portfolio\n"
//               + "4. Get all the portfolio names\n"
//               + "5. Custom load the portfolio with the csv\n"
//               + "6. End the program\n"
//               + "***** Hello, welcome to the portfolio maker *****\n"
//               + "Please Choose one of the options below:\n"
//               + "0. Print the menu\n"
//               + "1. Create a portfolio\n"
//               + "2. Show the portfolio\n"
//               + "3. Get the total value of a portfolio\n"
//               + "4. Get all the portfolio names\n"
//               + "5. Custom load the portfolio with the csv\n"
//               + "6. End the program\n"
//               + "***** Hello, welcome to the portfolio maker *****\n"
//               + "Please Choose one of the options below:\n"
//               + "0. Print the menu\n"
//               + "1. Create a portfolio\n"
//               + "2. Show the portfolio\n"
//               + "3. Get the total value of a portfolio\n"
//               + "4. Get all the portfolio names\n"
//               + "5. Custom load the portfolio with the csv\n"
//               + "6. End the program\n"
//               + "***** Hello, welcome to the portfolio maker *****\n"
//               + "Please Choose one of the options below:\n"
//               + "0. Print the menu\n"
//               + "1. Create a portfolio\n"
//               + "2. Show the portfolio\n"
//               + "3. Get the total value of a portfolio\n"
//               + "4. Get all the portfolio names\n"
//               + "5. Custom load the portfolio with the csv\n"
//               + "6. End the program\n"
//               + "***** Hello, welcome to the portfolio maker *****\n"
//               + "Please Choose one of the options below:\n"
//               + "0. Print the menu\n"
//               + "1. Create a portfolio\n"
//               + "2. Show the portfolio\n"
//               + "3. Get the total value of a portfolio\n"
//               + "4. Get all the portfolio names\n"
//               + "5. Custom load the portfolio with the csv\n"
//               + "6. End the program\n";
//       assertEquals(expected, out.toString());

//     }

//     @Test
//     public void testLoadPortfolio() {
//       StringBuffer out = new StringBuffer();
//       Reader in = new StringReader("5\nC:\\Users\\abhia\\Downloads\\trial.csv\n6\n");
//       ViewInterface view = new View();
//       ModelInterface model = new StockModel();
//       ControllerInterface obj = new Controller(view, model, in, out);
//       try {
//         obj.goController();
//       } catch (Exception e) {
//         throw new RuntimeException(e);
//       }
//       String expected = "***** Hello, welcome to the portfolio maker *****\n"
//               + "Please Choose one of the options below:\n"
//               + "0. Print the menu\n"
//               + "1. Create a portfolio\n"
//               + "2. Show the portfolio\n"
//               + "3. Get the total value of a portfolio\n"
//               + "4. Get all the portfolio names\n"
//               + "5. Custom load the portfolio with the csv\n"
//               + "6. End the program\n" +

//               "The portfolio is loaded successfully.\n" +
//               "***** Hello, welcome to the portfolio maker *****\n" +
//               "Please Choose one of the options below:\n" +
//               "0. Print the menu\n" +
//               "1. Create a portfolio\n" +
//               "2. Show the portfolio\n" +
//               "3. Get the total value of a portfolio\n" +
//               "4. Get all the portfolio names\n" +
//               "5. Custom load the portfolio with the csv\n" +
//               "6. End the program\n";
//       assertEquals(expected, out.toString());

//     }

//     @Test
//     public void testLoadInvalidPortfolio() {
//       StringBuffer out = new StringBuffer();
//       Reader in = new StringReader(
//               "5\nC:\\Users\\abhia\\Downloads\\trial7.csv\nC:\\Users\\abhia\\Downloads"
//                       + "\\trial17.csv\nC:\\Users\\abhia\\Downloads\\trial.csv\n6\n");
//       ViewInterface view = new View();
//       ModelInterface model = new StockModel();
//       ControllerInterface obj = new Controller(view, model, in, out);
//       try {
//         obj.goController();
//       } catch (Exception e) {
//         throw new RuntimeException(e);
//       }
//       String expected = "***** Hello, welcome to the portfolio maker *****\n"
//               + "Please Choose one of the options below:\n"
//               + "0. Print the menu\n"
//               + "1. Create a portfolio\n"
//               + "2. Show the portfolio\n"
//               + "3. Get the total value of a portfolio\n"
//               + "4. Get all the portfolio names\n"
//               + "5. Custom load the portfolio with the csv\n"
//               + "6. End the program\n" +
//               "Please give the file path with unique portfolio name\n" +
//               "Please give the file path with unique portfolio name\n" +
//               "The portfolio is loaded successfully.\n" +
//               "***** Hello, welcome to the portfolio maker *****\n" +
//               "Please Choose one of the options below:\n" +
//               "0. Print the menu\n" +
//               "1. Create a portfolio\n" +
//               "2. Show the portfolio\n" +
//               "3. Get the total value of a portfolio\n" +
//               "4. Get all the portfolio names\n" +
//               "5. Custom load the portfolio with the csv\n" +
//               "6. End the program\n";
//       assertEquals(expected, out.toString());

//     }

//     @Test
//     public void testGetAllPortfolios() {
//       StringBuffer out = new StringBuffer();
//       Reader in = new StringReader("4\n6\n");
//       ViewInterface view = new View();
//       ModelInterface model = new StockModel();
//       ControllerInterface obj = new Controller(view, model, in, out);
//       try {
//         obj.goController();
//       } catch (Exception e) {
//         throw new RuntimeException(e);
//       }
//       String expected = "***** Hello, welcome to the portfolio maker *****\n"
//               + "Please Choose one of the options below:\n"
//               + "0. Print the menu\n"
//               + "1. Create a portfolio\n"
//               + "2. Show the portfolio\n"
//               + "3. Get the total value of a portfolio\n"
//               + "4. Get all the portfolio names\n"
//               + "5. Custom load the portfolio with the csv\n"
//               + "6. End the program\n"
//               + "All the portfolios are\n" +
//               "***** Hello, welcome to the portfolio maker *****\n" +
//               "Please Choose one of the options below:\n" +
//               "0. Print the menu\n" +
//               "1. Create a portfolio\n" +
//               "2. Show the portfolio\n" +
//               "3. Get the total value of a portfolio\n" +
//               "4. Get all the portfolio names\n" +
//               "5. Custom load the portfolio with the csv\n" +
//               "6. End the program\n";
//       assertEquals(expected, out.toString());

//     }

//     @Test
//     public void testInvalidDate() {
//       ModelInterface model = new StockModel();
//       assertEquals(true, model.validDate("2020-11-06"));
//       assertEquals(false, model.validDate("2020-13-06"));
//       assertEquals(false, model.validDate("2020-11-35"));
//       assertEquals(false, model.validDate("11-20-2020"));
//       assertEquals(false, model.validDate("20-11-2020"));
//       assertEquals(false, model.validDate("2.3"));
//       assertEquals(false, model.validDate("hello  world"));
//     }

//     @Test
//     public void checkDateView() throws IOException {
//       StringBuffer out = new StringBuffer();
//       ViewInterface view = new View();
//       view.checkDate(out);
//       assertEquals("Market is closed on that day, please enter another date\n",
//               out.toString());
//     }

//     @Test
//     public void enterDateView() throws IOException {
//       StringBuffer out = new StringBuffer();
//       ViewInterface view = new View();
//       view.enterDate(out);
//       assertEquals("Enter the any date in 2017 in the dd/mm/yyyy\n",
//               out.toString());
//     }

//     @Test
//     public void testInvalidCommission() throws IOException {
//       ModelInterface model = new StockModel();
//       assertEquals(true, model.validCommission(5));
//       assertEquals(false, model.validCommission(-5));
//       assertEquals(true, model.validCommission((float) 5.3));

//     }

//     @Test
//     public void testInvalidDatePurchase() {
//       StringBuffer out = new StringBuffer();
//       Reader in = new StringReader("7\n10\n1\n2100-99-99\n2015-11-11\nmsft\n300\nno\n");
//       ViewInterface view = new View();
//       ModelInterface model = new StockModel();
//       ControllerInterface obj = new Controller(view, model, in, out);
//       try {
//         obj.goController();
//       } catch (Exception e) {
//         throw new RuntimeException(e);
//       }

//       String expected = "** Hello, welcome to the portfolio maker **\n" +
//               "Please Choose one of the options below:\n" +
//               "0. Print the menu\n" +
//               "1. Create a portfolio\n" +
//               "2. Create an Extended Portfolio\n" +
//               "3. Show the Portfolio\n" +
//               "4. Show the Extended Portfolio\n" +
//               "5. Get the total value of a portfolio\n" +
//               "6. Get the total investment in a portfolio\n" +
//               "7. Purchase stock to existing portfolio\n" +
//               "8. Sell stock from existing portfolio\n" +
//               "9. Get all the portfolio names\n" +
//               "10. Custom load the portfolio with the csv\n" +
//               "11. View performance of portfolio\n" +
//               "12. End the program\n" +
//               "\"5% Commission is charged for every purchase \" +\n" +
//               "            \"transaction on the total purchase cost\n" +
//               "Enter the numeric choice for the portfolio\n" +
//               "All the portfolios are\n" +
//               "1 abhi.csv\n" +
//               "2 abhishek.csv\n" +
//               "3 amee.csv\n" +
//               "4 atharv.csv\n" +
//               "5 trial.csv\n" +
//               "Enter the ticker symbol of stock you want to purchase.\n" +
//               "How many number of Shares you want to buy?\n" +
//               "Enter Date of Purchase of Stock in YYYY/MM/DD format : \n" +
//               "Date for range of performance is invalid.\n" +
//               "Enter y to enter more stocks to portfolio :\n" +
//               "";

//       assertEquals(expected, out.toString());
//     }


//     @Test
//     public void testInvalidDateSell() {
//       StringBuffer out = new StringBuffer();
//       Reader in = new StringReader("7\n10\n1\n2100-99-99\n2015-11-11\ngoog\n300\nno\n");
//       ViewInterface view = new View();
//       ModelInterface model = new StockModel();
//       ControllerInterface obj = new Controller(view, model, in, out);
//       try {
//         obj.goController();
//       } catch (Exception e) {
//         throw new RuntimeException(e);
//       }

//       String expected = "** Hello, welcome to the portfolio maker **\n" +
//               "Please Choose one of the options below:\n" +
//               "0. Print the menu\n" +
//               "1. Create a portfolio\n" +
//               "2. Create an Extended Portfolio\n" +
//               "3. Show the Portfolio\n" +
//               "4. Show the Extended Portfolio\n" +
//               "5. Get the total value of a portfolio\n" +
//               "6. Get the total investment in a portfolio\n" +
//               "7. Purchase stock to existing portfolio\n" +
//               "8. Sell stock from existing portfolio\n" +
//               "9. Get all the portfolio names\n" +
//               "10. Custom load the portfolio with the csv\n" +
//               "11. View performance of portfolio\n" +
//               "12. End the program\n" +
//               "10% Commission is charged for every sale " +
//               "transaction on the total purchase cost\n" +
//               "Enter the numeric choice for the portfolio\n" +
//               "All the portfolios are\n" +
//               "1 abhi.csv\n" +
//               "2 abhishek.csv\n" +
//               "3 amee.csv\n" +
//               "4 atharv.csv\n" +
//               "5 trial.csv\n" +
//               "Enter the ticker symbol of stock you want to purchase.\n" +
//               "How many number of Shares you want to buy?\n" +
//               "Enter Date of Purchase of Stock in YYYY/MM/DD format : \n" +
//               "Date for range of performance is invalid.\n" +
//               "Enter y to enter more stocks to portfolio :\n" +
//               "";

//       assertEquals(expected, out.toString());
//     }

//   }
// }


