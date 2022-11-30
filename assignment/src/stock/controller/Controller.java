package stock.controller;


import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

import stock.stock.StockInterface;
import stock.stock.stockDetails;
import stock.model.ModelInterface;
import stock.view.ViewInterface;

/**
 * Implementation of the Controller Interface.
 */
public class Controller implements ControllerInterface {

  ArrayList<String> portfolioNames;
  private ModelInterface model;
  private ViewInterface view;
  HashMap loadedCsv;
  final Readable in;
  final Appendable out;

  /**
   * Constructor for structure of Controller.
   *
   * @param view  of type View.
   * @param model of type Model.
   * @param in    of type Readable (InputStream).
   * @param out   of type Appendable (OutputStream).
   */
  public Controller(ViewInterface view, ModelInterface model, Readable in, Appendable out) {
    Objects.requireNonNull(view);
    Objects.requireNonNull(model);
    this.view = view;
    this.model = model;
    this.in = in;
    this.out = out;
    portfolioNames = model.getPortfolioNames();
    loadedCsv = model.loadDataFromCsv();
  }

  private String checkEmpty(String s, Scanner sc) throws IOException {
    while (s.equals("")) {
      view.dontEnterEmpty(out);
      s = sc.nextLine();
      if (!s.equals("")) {
        break;
      }
    }
    return s;
  }

  @Override
  public void goController() throws Exception {

    Scanner sc = new Scanner(this.in);
    view.printMenu(out);
    int toDo = 0;

    do {
      int tempFlag = 0;
      while (tempFlag == 0) {
        try {
          String temp = sc.nextLine();
          toDo = Integer.parseInt(temp);
          tempFlag = 1;
        } catch (NumberFormatException e) {
          view.defaultErrorSwitch(out);
        }
      }

      switch (toDo) {
        case 0: {
          view.printMenu(out);
          break;
        }
        //toDo = sc.nextInt();

        case 1: {

          view.printNameOfPortfolio(out);
          String portfolioName = sc.nextLine();
          portfolioName = checkEmpty(portfolioName, sc);
          while (model.checkIfPortfolioFileExists(portfolioName, portfolioNames)) {
            view.portfolioAlreadyExistsError(out);
            portfolioName = sc.nextLine();
          }

          takeStocksToPortfolio(sc);
          String bool = "yes";
          while (!bool.toLowerCase().equals("no")) {
            view.wantToAddMoreStocks(out);
            bool = sc.nextLine();
            if (!bool.toLowerCase().equals("yes")) {
              break;
            }
            takeStocksToPortfolio(sc);
          }
          HashMap stock = model.returnPortfolio();

          portfolioNames.add(portfolioName + ".csv");
          model.storeCsv(stock, portfolioName);
          view.portfolioSavedSuccessfully(out);
          break;
        }

        case 2: { //create extended portfolio
          stockDetails stock = getStockInput(sc);
          List<StockInterface> stockList = new ArrayList<StockInterface>();
          stockList.add(stock);
          String ch;

          view.wantToAddMoreStocks(out);
          ch = sc.nextLine();
          while (ch.equalsIgnoreCase("y")) {
            stock = getStockInput(sc);
            stockList.add(stock);
            view.wantToAddMoreStocks(out);
            ch = sc.nextLine();
          }

          view.printNameOfPortfolio(out);
          String portfolioName = sc.nextLine();
          portfolioName = checkEmpty(portfolioName, sc);
          while (model.checkIfPortfolioFileExists(portfolioName, portfolioNames)) {
            view.portfolioAlreadyExistsError(out);
            portfolioName = sc.nextLine();
          }

          model.storeExPortfolio(portfolioName, stockList);
          break;
        }
        case 3: { //create dollar cost average portfolio
          view.printInvestedAmount(out);
          float investedAmount = sc.nextFloat();
          while(investedAmount < 0){
            view.printInvalidAmount(out);
            investedAmount = sc.nextFloat();
          }

          view.printTickerSymbol(out);
          String symbol = sc.nextLine();
          while(!model.validSymbol(symbol)){
            view.invalidSymbol(out);
            symbol = sc.nextLine();
          }
          symbol = symbol.toUpperCase();
          float percent = 100;

          view.printInvestedPercentage(out);
          

          view.printDateOfStock(out);
          String date = sc.nextLine();
          while(!model.validDate(date)) {
            view.printInvalidDate(out);
            view.reEnterDate(out);
            date = sc.nextLine();
          }

          break;
        }

        case 4: { //display basic portfolio

          view.whichPortfolio(out);
          String portfolioName = sc.nextLine();
          portfolioName = checkEmpty(portfolioName, sc);
          while (!model.checkIfPortfolioFileExists(portfolioName, portfolioNames)) {
            view.portfolioDoesNotExist(out);
            portfolioName = sc.nextLine();
          }
          HashMap stock2 = model.readingCSV(portfolioName, false);
          view.showPortfolio(stock2, out);
          break;
        }

        case 5: { //display extended portfolio

          view.displayNamesOfPortfolio(portfolioNames, out);
          view.selectPortfolio(out);
          String input = sc.nextLine();
          int choice = Integer.parseInt(input);
          String portfolioName = portfolioNames.get(choice - 1);
          List<StockInterface> stocks = model.displayPortfolio(portfolioName);
          for (StockInterface stock : stocks) {
            view.displayStocks(stock.getSymbol(), stock.getQuantity(), stock.getDate(), stock.getPurchaseCost(),
                    stock.getInvestedAmount());
          }
          break;
        }

        case 6: { //display dca portfolio
          break;
        }

        case 7: { //examine the composition of a portfolio
          view.compositionView(out);
          view.displayNamesOfPortfolio(portfolioNames, out);
          view.selectPortfolio(out);
          String input = sc.nextLine();
          int choice = Integer.parseInt(input);
          String portfolioName = portfolioNames.get(choice-1);
          List<StockInterface> stockList = new ArrayList<>();
          stockList = model.displayPortfolio(portfolioName);
          int i = 1;
          String quantity = "0";
          view.compositionHeader(out);
          for (StockInterface stock : stockList) {
            if (stock.getQuantity() < 10 && stock.getQuantity() > 0) {
              quantity += stock.getQuantity();
              view.compositionPortfolio(i, stock, quantity, out);
            } else {
              view.compositionPortfolio(i, stock, String.valueOf(stock.getQuantity()), out);
            }

            i++;
          }

          break;
        }
        case 8: { //value of portfolio

          view.nameOfPortfolioValue(out);
          String tempPortfolio = sc.nextLine().toLowerCase();

          while (!model.checkIfPortfolioFileExists(tempPortfolio, portfolioNames)) {
            view.portfolioDoesNotExist(out);
            tempPortfolio = sc.nextLine().toLowerCase();
            if (model.checkIfPortfolioFileExists(tempPortfolio, portfolioNames)) {
              break;
            }
          }

          view.enterDate(out);
          String date = sc.nextLine();
          date = checkEmpty(date, sc);
          String formatted_date = date;
          // model.dateTickerCheck(loadedCsv,formatted_date);
          HashMap<String, Double> stockOfUser = model.readingCSV(tempPortfolio, false);

          double value = model.valueOfPortfolio(stockOfUser, loadedCsv, formatted_date);
          while (value == -10) {
            view.checkDate(out); //handle the date clearly
            date = sc.nextLine();
            formatted_date = date;
            value = model.valueOfPortfolio(stockOfUser, loadedCsv, formatted_date);
          }
          view.valueOfPortfolioIs(value, out);

          break;
        }

        case 9: { //get total invested in a portfolio
          view.displayNamesOfPortfolio(portfolioNames, out);
          view.selectPortfolio(out);
          int choice = sc.nextInt();
          String portfolioName = portfolioNames.get(choice - 1);
          float sum = model.getInvestedValue(portfolioName,0);
          view.displayValue(sum);

          break;
        }

        case 10: { //purchase to existing portfolio

          view.selectPortfolio(out);
          view.displayNamesOfPortfolio(portfolioNames,out);
          int choice = Integer.parseInt(sc.nextLine());
          String portfolioName = portfolioNames.get(choice - 1);
          List<StockInterface> stockList = new ArrayList<>();
          String input;

          stockDetails stock = getStockInput(sc);
          stockList.add(stock);

          view.wantToAddMoreStocks(out);
          input = sc.nextLine();
          while (input.equalsIgnoreCase("y")) {
            stock = getStockInput(sc);
            stockList.add(stock);
            view.wantToAddMoreStocks(out);
            input = sc.nextLine();
          }

          model.purchaseStock(portfolioName, stockList);

          break;
        }

        case 11: { //sell from existing extended portfolio

          view.displayNamesOfPortfolio(portfolioNames,out);
          view.selectPortfolio(out);
          int choice = Integer.parseInt(sc.nextLine());
          String portfolioName = portfolioNames.get(choice - 1);

          view.mentionCommission(out);
          float percentCommission = Float.parseFloat(sc.nextLine());
          while (model.validCommission(percentCommission)) {
            view.invalidCommission(out);
            percentCommission = Float.parseFloat(sc.nextLine());
          }

          List<StockInterface> stockList = model.displayPortfolio(portfolioName);
          String symbol = "";
          int i = 1;
          ArrayList<String> stocks = new ArrayList<String>();
          ArrayList<Integer> shares = new ArrayList<Integer>();
          ArrayList<String> dates = new ArrayList<String>();
          List<StockInterface> temp = new ArrayList<>();
          view.enterChoice(out);
          for (StockInterface stock : stockList) {
            view.printStocks(i, stock.getSymbol(), stock.getDate(), out);
            stocks.add(stock.getSymbol());
            shares.add(stock.getQuantity());
            dates.add(stock.getDate());
            i++;
          }
          int input = Integer.parseInt(sc.nextLine());
          view.availableQuantity(shares.get(input-1), out);
          view.sellQuantity(out);
          int quantity = Integer.parseInt(sc.nextLine());
          while (!model.verifyQuantity(quantity,shares.get(input - 1))) {
            view.printInvalid(out);
            quantity = Integer.parseInt(sc.nextLine());
          }
          if (quantity == shares.get(input - 1)) {
            view.confirmEntireSell(out);
            String ch = sc.nextLine();
            if (ch.equalsIgnoreCase("y")) {
              model.sellEntireStock(portfolioName, stockList, stocks.get(input - 1),dates.get(input - 1));
            } else {
              view.cancelSell(out);
            }
          } else {
            view.confirmSell(out);
            String ch = sc.nextLine();
            if (ch.equalsIgnoreCase("y")) {
              model.sellPartialStock(portfolioName, stockList, stocks.get(input - 1), shares.get(input - 1),
                      quantity, dates.get(input - 1));
            } else {
              view.cancelSell(out);
            }
          }
          view.sellingDate(out);
          String sellingDate = sc.nextLine();

          float totalSell = model.totalSell(stocks.get(input - 1), quantity, sellingDate);
          float commissionValue = model.getCommission(totalSell, percentCommission);
          float earnings = model.getEarnings( totalSell, commissionValue);
          view.showEarnings(earnings, commissionValue, out);

          break;
        }

        case 12: {
          view.displayNamesOfPortfolio(portfolioNames, out);
          break;
        }

        case 13:
          view.enterPathToLoadCsv(out);
          String tempFileName = sc.nextLine();

          tempFileName = checkEmpty(tempFileName, sc);

          File theDir = new File(tempFileName);
          String portfolioName = tempFileName.substring(tempFileName.lastIndexOf("\\") + 1,
              tempFileName.indexOf("."));
          while (!theDir.exists() || portfolioNames.contains(portfolioName)) {
            view.pathDoesNotExist(out);
            tempFileName = sc.nextLine();
            theDir = new File(tempFileName);
            if (theDir.exists() && !portfolioNames.contains(portfolioName)) {
              break;
            }
          }

          HashMap toStore = model.readingCSV(tempFileName, true);

          model.storeCsv(toStore, portfolioName);
          view.loadedSuccessfully(out);
          break;

        case 14: {
          /*
          view.printNameOfPortfolio(out);
          String portName = sc.nextLine();
          portName = checkEmpty(portName, sc);
          while (!model.checkIfPortfolioFileExists(portName, portfolioNames)) {
            view.portfolioDoesNotExist(out);
            portName = sc.nextLine();
          }
          */
          view.displayNamesOfPortfolio(portfolioNames, out);
          view.selectPortfolio(out);
          String input = sc.nextLine();
          int choice = Integer.parseInt(input);
          String portName = portfolioNames.get(choice - 1);

          //boolean flag = false;

          view.enterStartDate(out);
          String fromDate = sc.nextLine();
          fromDate = checkEmpty(fromDate, sc);
          /*
          while(!model.validDate(fromDate)) {
            view.invalidDate(out);
            fromDate = sc.nextLine();
            fromDate = checkEmpty(fromDate, sc);
          }

           */


          view.enterEndDate(out);
          String toDate = sc.nextLine();
          toDate = checkEmpty(toDate, sc);
          /*
          while(!model.validDate(toDate)) {
            view.invalidDate(out);
            fromDate = sc.nextLine();
            toDate = checkEmpty(toDate, sc);
          }

           */

          if (model.validDate(fromDate) && model.validDate(toDate)) {
            LinkedHashMap<String, Float> timestamp = model.splitDuration(fromDate, toDate, portName);
            view.printTimestamp(timestamp, out);
            if (!timestamp.isEmpty()) {
              int scale = (int) (Collections.max(timestamp.values()) / 50);
              view.portfolioPerformance(fromDate, toDate, scale, portName, timestamp);
            } else {
              view.invalidDateRange(out);
            }

          } else {
            view.invalidDate(out);
          }
          break;
        }

        case 15:
          break;

        default:
          view.defaultErrorSwitch(out);

      }
      if (toDo != 15) {
        view.printMenu(out);
      }
    }
    while (toDo != 15);


  }

  private stockDetails getStockInput(Scanner sc) throws IOException {
    view.printTickerSymbol(out);
    String symbol = sc.nextLine();
    while (!model.validSymbol(symbol)) {
      view.invalidSymbol(out);
      symbol = sc.nextLine();
    }
    symbol = symbol.toUpperCase();

    view.printNumberOfSharesToBuy(out);
    String shares = sc.nextLine();
    while (!model.validQuantity(shares)) {
      view.enterWholeNumber(out);
      shares = sc.nextLine();
    }
    int quantity = Integer.parseInt(shares);

    view.printDateOfStock(out);
    String date = sc.nextLine();
    while (!model.validDate(date)) {
      view.printInvalidDate(out);
      view.reEnterDate(out);
      date = sc.nextLine();
    }

    view.mentionCommission(out);
    float percentCommission = Float.parseFloat(sc.nextLine());
    while (model.validCommission(percentCommission)) {
      view.invalidCommission(out);
      percentCommission = Float.parseFloat(sc.nextLine());
    }

    String result = model.fetchUrlPrices(symbol);
    float purchaseCost = model.getPrices(date,result);
    while (purchaseCost == -1) {
      view.printMarketClosed(out);
      view.reEnterDate(out);
      date = sc.nextLine();
      purchaseCost = model.getPrices(date,result);
    }


    DecimalFormat df = new DecimalFormat("###.###");
    df.format(purchaseCost);
    float investment = model.getInvestment(symbol, quantity, date);
    float commissionValue = model.getPurchaseCommission(investment, percentCommission);
    float totalInvestment = model.totalInvestment(investment, commissionValue);
    stockDetails stock = new stockDetails(symbol, quantity, date, purchaseCost, totalInvestment);

    view.showInvestment(totalInvestment,commissionValue,out);

    return stock;
  }


  private void takeStocksToPortfolio(Scanner sc) throws IOException {
    String ticker = sc.nextLine().toUpperCase();
    view.printTickerSymbol(out);
    while (model.checkTicker(loadedCsv, "3/2/2017", ticker) == -10) {
      view.tickerDoesNotExist(out);
      ticker = sc.nextLine().toUpperCase();
    }
    view.printNumberOfSharesToBuy(out);
    String temp = sc.nextLine();

    int tempFlag = 0;
    while (tempFlag == 0) {
      try {
        double dTemp = Integer.parseInt(temp);
        if (dTemp < 0) {
          throw new NumberFormatException();
        }
        tempFlag = 1;
      } catch (NumberFormatException e) {
        view.enterWholeNumber(out);
        temp = sc.nextLine();
      }
    }

    int shares = Integer.parseInt(temp);
    float purchaseCost = Float.parseFloat(model.fetchUrlPrices(ticker));
    model.createStock(ticker, shares, "",purchaseCost);

  }
}
