package stock.model;

import stock.stock.DCAImpl;
import stock.stock.DCAPortfolios;
import stock.stock.stockDetails;
import stock.stock.StockInterface;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.net.URL;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;



/**
 * Implementation of Model interface. Model is responsible for all computation by the program.
 */
public class StockModel implements ModelInterface {

  private final String apiKey = "W5GNQVIAT6ZNULOG";

  private HashMap<String, Integer> stock = new HashMap<>();

  private List<StockInterface> stockList = new ArrayList<>();

  private final String currPath = System.getProperty("user.dir") + "/allPortfolios";
  private final String userFolder = "allPortfolios";
  private final Path userDirectory = Paths.get(currPath);

  @Override
  public ArrayList<String> getPortfolioNames() {
    Path currentPath = Paths.get(System.getProperty("user.dir"));
    Path fp = Paths.get(currentPath.toString(), "allPortfolios/flexPortfolios");
    File theDir = new File(fp.toString());
    if (!theDir.exists()) {
      theDir.mkdirs();
    }
    File directoryPath = new File(fp.toString());
    String[] stringListFiles = directoryPath.list();

    return new ArrayList<>(Arrays.asList(stringListFiles));
  }

  @Override
  public ArrayList<String> getDCAPortfolioNames() {
    Path currentPath = Paths.get(System.getProperty("user.dir"));
    Path fp = Paths.get(currentPath.toString(), "allPortfolios/DCAPortfolios");
    File theDir = new File(fp.toString());
    if (!theDir.exists()) {
      theDir.mkdirs();
    }
    File directoryPath = new File(fp.toString());
    String[] stringListFiles = directoryPath.list();

    return new ArrayList<>(Arrays.asList(stringListFiles));
  }

  @Override
  public List<DCAPortfolios> displayDCAPortfolio(String portfolioName) throws IOException {
    Path currPath = Paths.get(userDirectory.toString()+"/DCAPortfolios", portfolioName);
    File currFile = new File(currPath.toString());
    if (!validFile(currFile)) {
      throw new IOException("Invalid File");
    }
    String currLine;
    List<DCAPortfolios> stockList = new ArrayList<>();
    FileReader reader = new FileReader(currFile);
    BufferedReader bufferedReader = new BufferedReader(reader);
    while ((currLine = bufferedReader.readLine()) != null) {
      String[] data = currLine.split(",");
      if (data.length > 6) {
        throw new IOException("Invalid CSV file");
      } else {
        if (!validSymbol(data[0])) {
          throw new IOException("Invalid Symbol in CSV");
        } else if (!validFloatQuantity(data[1])) {
          throw new IOException("Invalid Quantity in CSV");
        }
        stockList.add(new DCAImpl(data[1],Float.parseFloat(data[1]), Float.parseFloat(data[5]), data[2],
                Float.parseFloat(data[3]), Float.parseFloat(data[4])));
      }
    }
    bufferedReader.close();
    return stockList;

  }

  private boolean validFloatQuantity(String quantity) {
    float quantity2 = Float.parseFloat(quantity);
    if(quantity2 <= 0) {
      return false;
    }
    return true;
  }
  @Override
  public void createStock(String ticker, int share, String date, float purchaseCost)
          throws IOException {
    /*
    stockDetails stockDetails = new stockDetails(ticker, share, date, purchaseCost);
    if (stockList.contains(ticker)) {
      share += stockDetails.getQuantity();
    }
    Path filePath = Paths.get(userDirectory.toString(),
            portfolioName + ".csv");
    File portfolioFile = new File(filePath.toString());
    if (portfolioFile.exists()) {
      throw new FileAlreadyExistsException("Portfolio already exists");
    } else {
      addStocks(filePath.toString());
    }

     */
  }

  private void addStocks(String fileName) throws IOException {
    FileWriter writer = new FileWriter(fileName, true);
    BufferedWriter bufferWriter = new BufferedWriter(writer);
    for (StockInterface stock : stockList) {
      int quantity = stock.getQuantity();
      String symbol = stock.getSymbol();
      String date = stock.getDate();
      float purchaseCost = stock.getPurchaseCost();
      bufferWriter.write(symbol + "," + quantity + "," + date + "," + purchaseCost +'\n');
    }
    bufferWriter.close();
    stockList = new ArrayList<StockInterface>();
  }


  @Override
  public HashMap<String, Integer> returnPortfolio() {
    return stock;
  }

  @Override
  public int checkTicker(HashMap loadedCsv, String date, String ticker) {
    String temp = ticker + "," + date;
    if (!loadedCsv.containsKey(temp)) {
      return -10;
    }
    return -1;
  }

  @Override
  public boolean validSymbol(String symbol) {
    String result = fetchUrlOverview(symbol);
    return result.length() > 2;
  }

  @Override
  public boolean validQuantity(String quantity) {
    try {
      int numQuantity = Integer.parseInt(quantity);
      if (numQuantity <= 0) {
        return false;
      }
    } catch (NumberFormatException e) {
      return false;
    }
    return true;
  }

  @Override
  public boolean validDate(String date) {
    DateTimeFormatter format = DateTimeFormatter.ofPattern("uuuu/MM/dd");
    try {
      LocalDate.parse(date, format);
      String[] value = new String[date.length()];
      value = date.split("/");
      if(value[1].equals("02")){
        if(Integer.valueOf(value[0]) % 4 == 0 || value[0].endsWith("00")) {
          if(Integer.valueOf(value[2] ) > 29) {
            return false;
          }
          else {
            return true;
          }
        }
        else {
          if(Integer.valueOf(value[2]) > 28){
            return false;
          }
          else {
            return true;
          }
        }
      }
      return true;
    } catch (DateTimeParseException e) {
      return false;
    }
  }

  @Override
  public boolean validDate2(String date) {
    DateTimeFormatter format = DateTimeFormatter.ofPattern("uuuu-MM-dd");
    try {
      LocalDate.parse(date, format);
      String[] value = new String[date.length()];
      value = date.split("/");
      if(value[1].equals("02")){
        if(Integer.valueOf(value[0]) % 4 == 0 || value[0].endsWith("00")) {
          if(Integer.valueOf(value[2] ) > 29) {
            return false;
          }
          else {
            return true;
          }
        }
        else {
          if(Integer.valueOf(value[2]) > 28){
            return false;
          }
          else {
            return true;
          }
        }
      }
      return true;
    } catch (DateTimeParseException e) {
      return false;
    }
  }

  @Override
  public List<StockInterface> loadFile(String currPath) throws IOException, InvalidPathException {
    if (validPath(currPath)) {
      File currFile = new File(currPath);
      stockList = readFile(currFile);
      return stockList;
    } else {
      throw new InvalidPathException(currPath, "Given Path is in invalid format");
    }
  }

  private boolean validPath(String currPath) {
    try {
      Paths.get(currPath);
    } catch (InvalidPathException | NullPointerException e) {
      return false;
    }
    return true;
  }

  /**
   * private helper function which helps verify if symbol is valid from the API.
   *
   * @param symbol ticker of the stock object
   * @return a string consisting of expected data.
   */
  public String fetchUrlOverview(String symbol) {
    URL url;
    InputStream inputData;
    StringBuilder result = new StringBuilder();
    try {
      url = new URL("https://www.alphavantage"
              + ".co/query?function=OVERVIEW"
              + "&symbol"
              + "=" + symbol + "&apikey=" + apiKey + "&datatype=csv");
      inputData = url.openStream();
      int reader;

      while ((reader = inputData.read()) != -1) {
        result.append((char) reader);
      }
    } catch (IOException e) {
      return "";
    }
    return result.toString();
  }

  /**
   * private helper function which helps fetch data for each symbol from the API.
   *
   * @param symbol ticker of the stock object
   * @return a string consisting of expected data.
   */
  public String fetchUrlPrices(String symbol) throws IOException {
    float purchaseCost = 0;
    URL url;
    InputStream inputData;
    StringBuilder result = new StringBuilder();
    url = new URL("https://www.alphavantage"
            + ".co/query?function=TIME_SERIES_DAILY"
            + "&outputsize=full"
            + "&symbol"
            + "=" + symbol + "&apikey=" + apiKey + "&datatype=csv");
    inputData = url.openStream();
    int reader;

    while ((reader = inputData.read()) != -1) {
      result.append((char) reader);
    }

    return result.toString();
  }

  @Override
  public void storeExPortfolio(String portfolioName, List<StockInterface> stockList) throws IOException {
    verifyName(portfolioName);
    for (StockInterface stock : stockList) {
      updateStockList(stock.getSymbol(), stock.getQuantity(), stock.getDate(), stock.getPurchaseCost());
    }
    createPortfolio(portfolioName);
  }

  private void createPortfolio(String portfolioName) throws IOException {
    Path filePath = Paths.get(userDirectory.toString()+"/flexPortfolios", portfolioName + ".csv");
    File portfolioFile = new File(filePath.toString());
    if (portfolioFile.exists()) {
      throw new FileAlreadyExistsException("Portfolio already exists");
    } else {
      addStocks(filePath.toString());
    }
  }

  private void verifyName(String name) throws IllegalArgumentException {
    if (!name.matches("^[\\w_]+$") || name.strip().length() == 0) {
      throw new IllegalArgumentException("Invalid name format");
    }
  }

  @Override
  public List<StockInterface> displayPortfolio(String portfolioName) throws IOException {
    Path currPath = Paths.get(userDirectory.toString()+"/flexPortfolios", portfolioName);
    File currFile = new File(currPath.toString());
    return readFile(currFile);
  }

  private List<StockInterface> readFile(File currFile) throws IOException {
    if (!validFile(currFile)) {
      throw new IOException("Invalid File");
    }
    String currLine;
    List<StockInterface> stockList = new ArrayList<>();
    FileReader reader = new FileReader(currFile);
    BufferedReader bufferedReader = new BufferedReader(reader);
    while ((currLine = bufferedReader.readLine()) != null) {
      String[] data = currLine.split(",");
      if (data.length > 4) {
        throw new IOException("Invalid CSV file");
      } else {
        if (!validSymbol(data[0])) {
          throw new IOException("Invalid Symbol in CSV");
        } else if (!validQuantity(data[1])) {
          throw new IOException("Invalid Quantity in CSV");
        }
        int quantity = Integer.parseInt(data[1]);
        stockList.add(new stockDetails(data[0],quantity, data[2],Float.parseFloat(data[3]),
                (quantity * Float.parseFloat(data[3]))));
      }
    }
    bufferedReader.close();
    return stockList;
  }

  private boolean validFile(File file) {
    String fileName = file.getName();
    return file.exists() && fileName.contains(".csv");
  }

  @Override
  public double valueOfPortfolio(HashMap stockOfUser, HashMap loadedCsv, String date) {
    double answer = 0;
    for (Object key : stockOfUser.keySet()) {
      String temp = key + "," + date;

      if (!loadedCsv.containsKey(temp)) {
        return -10;
      }

      double d1 = (double) loadedCsv.get(temp);
      int d2 = (int) stockOfUser.get(key);
      double d = d1 * d2;
      answer += d;
    }
    return answer;
  }

  private float determinePortfolioHelper(List<StockInterface> stocklist2, String date) throws IOException {
    float price = 0;
    List<Float> prices = determinePortfolio(stocklist2,date);
    for (int i = 0; i < prices.size(); i++) {
      price += prices.get(i);
    }
    return price;
  }

  @Override
  public List<Float> determinePortfolio(List<StockInterface> stockList, String date)
          throws IOException, RuntimeException {
    List<Float> prices = new ArrayList<>();
    if(date.contains("-")){
      date = date.replace("-","/");
    }
    for (StockInterface stock : stockList) {
      try {
        if (!validDate(date)) {
          throw new IllegalArgumentException("Invalid Date Format");
        }
        String priceResults = fetchUrlPrices(stock.getSymbol());
        float currPrice = getPrice(date, priceResults);
        if (currPrice == -1) {
          throw new IllegalArgumentException("No data found for given Date");
        } else {
          prices.add(currPrice);
        }
      } catch (IOException e) {
        throw new IOException("Error while handling URL data");
      } catch (DateTimeParseException e) {
        throw new RuntimeException("There are issues with the AlphaVantage API, " +
                "Please try again later");
      }
    }
    return prices;
  }

  @Override
  public float getPrices(String date, String priceResults) {
    return getPrice(date,priceResults);
  }

  /**
   * Gets the total amount invested in a portfolio.
   *
   * @param portfolioName selected portfolio's name.
   * @return
   */
  @Override
  public float getInvestedValue(String portfolioName, float sum) throws IOException {
    String currLine;
    Path currPath = Paths.get(userDirectory.toString()+"/flexPortfolios",portfolioName);
    File currFile = new File(currPath.toString());
    FileReader reader = new FileReader(currFile);
    BufferedReader bufferedReader = new BufferedReader(reader);
    while ((currLine = bufferedReader.readLine()) != null) {
      String[] data = currLine.split(",");
      int quantity = Integer.parseInt(data[1]);
      float price = Float.parseFloat(data[3]);
      sum = sum + (quantity * price);
    }
    bufferedReader.close();
    return sum;
  }

  @Override
  public void sellEntireStock(String portfolioName, List<StockInterface> stockList,String symbol, String date)
          throws IOException {
    int newQuantity = 0;
    List<StockInterface> temp = new ArrayList<>();
    for (StockInterface stock : stockList) {
      if (stock.getSymbol().equalsIgnoreCase(symbol) && stock.getDate().equalsIgnoreCase(date)) {
        stock.setQuantity(newQuantity);
        temp.add(stock);
      }
    }
    for (StockInterface stock : temp) {
      if (stock.getQuantity() == 0) {
        stockList.remove(stock);
      }
    }

    Path filePath = Paths.get(userDirectory.toString()+"/flexPortfolios", portfolioName);
    File portfolioFile = new File(filePath.toString());
    FileWriter writer = new FileWriter(portfolioFile);
    BufferedWriter bufferWriter = new BufferedWriter(writer);
    for (StockInterface stock : stockList) {
      int quantity = stock.getQuantity();
      if (quantity == 0) {
        continue;
      }
      String ticker = stock.getSymbol();
      String stockDate = stock.getDate();
      float purchaseCost = stock.getPurchaseCost();
      bufferWriter.write(ticker + "," + quantity + "," + stockDate + "," + purchaseCost + '\n');
    }
    bufferWriter.close();
  }

  @Override
  public void sellPartialStock(String portfolioName, List<StockInterface> stockList,String symbol,
                               int availableQuantity, int sellQuantity, String date) throws IOException {
    availableQuantity -= sellQuantity;
    for(StockInterface stock : stockList){
      if(stock.getSymbol().equalsIgnoreCase(symbol) && stock.getDate().equalsIgnoreCase(date)){
        stock.setQuantity(availableQuantity);
      }
    }
    Path filePath = Paths.get(userDirectory.toString()+"/flexPortfolios", portfolioName);
    File portfolioFile = new File(filePath.toString());
    FileWriter writer = new FileWriter(portfolioFile);
    BufferedWriter bufferWriter = new BufferedWriter(writer);
    for (StockInterface stock : stockList) {
      int quantity = stock.getQuantity();
      String ticker = stock.getSymbol();
      String stockDate = stock.getDate();
      float purchaseCost = stock.getPurchaseCost();
      bufferWriter.write(ticker + "," + quantity + "," + stockDate + "," + purchaseCost + '\n');
    }
    bufferWriter.close();
  }

  @Override
  public void purchaseStock(String portfolioName, List<StockInterface> stockList) throws IOException {
    Path currPath = Paths.get(userDirectory.toString()+"/flexPortfolios", portfolioName);
    File currFile = new File(currPath.toString());
    List<StockInterface> portfolioList = readFile(currFile);
    List<StockInterface> temp = new ArrayList<>();
    List<StockInterface> temp1 = new ArrayList<>();

    for (StockInterface stock1 : portfolioList){
      for(StockInterface stock2 : stockList){
        if(stock1.getSymbol().equalsIgnoreCase(stock2.getSymbol())) {
          if (stock1.getDate().equals(stock2.getDate())) {
            stock1.setQuantity(stock1.getQuantity() + stock2.getQuantity());
            temp1.add(stock2);
          } else {
            temp.add(stock2); //to store non-duplicate stock objects.
            //these are added to the Portfolio in the outermost "for."
          }
        }
      }
      for(StockInterface stock3 : temp1){
        stockList.remove(stock3);
      }
      temp1 = new ArrayList<>();
    }
    for (StockInterface stock : temp){ //adds the non-duplicate stock objects here.
      portfolioList.add(stock);
    }

    FileWriter writer = new FileWriter(currFile);
    BufferedWriter bufferWriter = new BufferedWriter(writer);
    for (StockInterface stock : portfolioList) {
      int quantity1 = stock.getQuantity();
      String ticker = stock.getSymbol();
      String date = stock.getDate();
      float purchaseCost = stock.getPurchaseCost();
      bufferWriter.write(ticker + "," + quantity1 + "," + date + "," + purchaseCost + '\n');
    }
    bufferWriter.close();
  }


  @Override
  public boolean verifyQuantity(int quantity, int shares){
    if(quantity > shares || quantity < 0 ){
      return false;
    }
    return true;
  }
  @Override
  public float totalSell(String symbol, int quantity, String date) throws IOException {
    String priceResults = fetchUrlPrices(symbol);
    float currPrice = getPrice(date, priceResults);

    return quantity * currPrice;
  }

  @Override
  public float getCommission(float totalSell, float percentCommission) {
    return (totalSell * (percentCommission/100));
  }

  @Override
  public float getEarnings(float totalSell, float commissionValue) {
    return totalSell - commissionValue;
  }

  /**
   * Checks for negative commission percentage.
   *
   * @param commission commission percentage should be positive and >=0.
   * @return returns true if commission percentage is negative.
   */

  @Override
  public boolean validCommission(float commission) {
    return commission < 0;
  }

  @Override
  public void storeDCAPortfolio(String portfolioName, List<DCAPortfolios> stockList) throws IOException {
    verifyName(portfolioName);
    Path filePath = Paths.get(userDirectory.toString()+"/DCAPortfolios", portfolioName + ".csv");
    File fileName = new File(filePath.toString());
    boolean check = createDCAPortfolio(portfolioName);
    if(check){
      FileWriter writer = new FileWriter(fileName, true);
      BufferedWriter bufferWriter = new BufferedWriter(writer);
      for (DCAPortfolios stock : stockList) {
        float quantity = stock.getQuantity();
        String symbol = stock.getSymbol();
        String date = stock.getDate();
        float percentage = stock.getPercentage();
        float purchaseCost = stock.getPurchaseCost();
        float investedAmount = stock.getInvestedAmount();
        bufferWriter.write(symbol + "," + quantity + "," + date + "," + purchaseCost + "," + investedAmount
                + "," + percentage + '\n');
      }
      bufferWriter.close();
    }
    else {
      throw new FileAlreadyExistsException("Portfolio already exists");
    }
  }

  private boolean createDCAPortfolio(String portfolioName) throws FileAlreadyExistsException {
    Path filePath = Paths.get(userDirectory.toString()+"/DCAPortfolios", portfolioName + ".csv");
    File portfolioFile = new File(filePath.toString());
    if (portfolioFile.exists()) {
      return false;
    }
    else {
      return true;
    }
  }

  @Override
  public float getInvestment(String symbol, int quantity, String date) throws IOException {
    String priceResults = fetchUrlPrices(symbol);
    float currPrice = getPrice(date, priceResults);
    return quantity * currPrice;
  }

  @Override
  public float getPurchaseCommission(float investment, float percentCommission) {
    return (investment * (percentCommission/100));
  }

  @Override
  public float totalInvestment(float investment, float commissionValue) {
    return investment + commissionValue;
  }


  private float getPrice(String date, String priceResults) throws DateTimeParseException {

    DateTimeFormatter format = DateTimeFormatter.ofPattern("uuuu/MM/dd");
    String[] dailyResults = priceResults.split("\n");
    String[] latestResult = dailyResults[1].split(",");
    String[] oldestResult = dailyResults[dailyResults.length - 1].split(",");
    LocalDate latestDate = LocalDate.parse(latestResult[0]);
    LocalDate oldestDate = LocalDate.parse(oldestResult[0]);
    LocalDate givenDate = LocalDate.parse(date, format);
    if (givenDate.isAfter(latestDate) || givenDate.isBefore(oldestDate)) {
      return -1;
    } else {
      for (int i = 1; i < dailyResults.length; i++) {
        String[] currResult = dailyResults[i].split(",");
        LocalDate currDate = LocalDate.parse(currResult[0]);
        if (currDate.equals(givenDate)) {
          double result = Double.parseDouble(currResult[1]);
          double roundedResult = Math.round(result * 100.0) / 100.0;
          return (float) roundedResult;
        }
      }
    }


    return -1;
  }


  @Override
  public boolean checkIfPortfolioFileExists(String portfolioName, ArrayList portfolioNames) {

    return portfolioNames.contains(portfolioName + ".csv");
  }

  @Override
  public HashMap loadDataFromCsv() {

    HashMap<String, Double> stock2 = new HashMap<>();
    Path currentPath = Paths.get(System.getProperty("user.dir"));

    Path filePath = Paths.get(currentPath.toString(), "/res/2017all.csv");
    String fileName = filePath.toString();

    ArrayList<String[]> content = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
      String line = "";
      while ((line = br.readLine()) != null) {
        content.add(line.split(","));
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    for (int i = 0; i < content.size(); i++) {

      String date = content.get(i)[0].replace("\"", "");
      double value = Double.parseDouble(content.get(i)[1].replace("\"", ""));
      String ticker = content.get(i)[2].replace("\"", "");
      String temp = ticker + "," + date;
      stock2.put(temp, value);
    }

    return stock2;
  }

  @Override
  public void storeCsv(HashMap<String, Integer> stock, String portfolioName)
          throws IOException {

    String[][] temp = new String[stock.size()][2];
    int count = 0;
    for (Map.Entry<String, Integer> entry : stock.entrySet()) {
      temp[count][0] = entry.getKey();
      temp[count][1] = String.valueOf(entry.getValue());
      count++;
    }
    Path currentPath = Paths.get(System.getProperty("user.dir"));
    Path fp = Paths.get(currentPath.toString(), "allPortfolios/flexPortfolios");
    File theDir = new File(fp.toString());
    if (!theDir.exists()) {
      theDir.mkdirs();
    }

    Path filePath = Paths.get(currentPath.toString(), "allPortfolios/flexPortfolios", portfolioName + ".csv");

    String fileName = filePath.toString();

    File csvFile = new File(fileName);
    FileWriter fileWriter = new FileWriter(csvFile);

    for (String[] data : temp) {
      StringBuilder line = new StringBuilder();
      for (int i = 0; i < data.length; i++) {
        line.append("\"");
        line.append(data[i].replaceAll("\"", "\"\""));
        line.append("\"");
        if (i != data.length - 1) {
          line.append(',');
        }
      }
      line.append("\n");
      fileWriter.write(line.toString());
    }
    fileWriter.close();

  }

  @Override
  public HashMap<String, Integer> readingCSV(String portfolioName, boolean b) { //upload
    HashMap<String, Integer> stock2 = new HashMap<>();
    String fileName = "";
    if (!b) {
      Path currentPath = Paths.get(System.getProperty("user.dir"));
      Path fp = Paths.get(currentPath.toString(), "allPortfolios/flexPortfolios");
      File theDir = new File(fp.toString());
      if (!theDir.exists()) {
        theDir.mkdirs();
      }
      Path filePath = Paths.get(currentPath.toString(), "allPortfolios/flexPortfolios", portfolioName + ".csv");

      fileName = filePath.toString();
    } else {
      fileName = portfolioName;
    }
    ArrayList<String[]> content = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
      String line = "";
      while ((line = br.readLine()) != null) {
        content.add(line.split(","));
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    for (int i = 0; i < content.size(); i++) {
      String key = content.get(i)[0].replace("\"", "");
      int value = Integer.parseInt(content.get(i)[1].replace("\"", ""));
      stock2.put(key, value);
    }

    return stock2;
  }

  @Override
  public void updateStockList(String symbol, int quantity, String date, float purchaseCost) {
    boolean exists = false;

    for (int i = 0; i < stockList.size(); i++) {
      StockInterface currStock = stockList.get(i);
      if (currStock.getSymbol().equals(symbol) && currStock.getDate()==date) {
        int currQuantity = currStock.getQuantity();
        stockList.set(i, new stockDetails(symbol, currQuantity + quantity, date,purchaseCost,
                (currQuantity + quantity)*purchaseCost));
        exists = true;
      }
    }
    if (!exists) {
      stockDetails stockDetails = new stockDetails(symbol, quantity, date, purchaseCost, (quantity*purchaseCost));
      stockList.add(stockDetails);
    }
  }

  @Override
  public long getNumDays(String fromDate, String toDate) {
    final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    final LocalDate d1 = LocalDate.parse(fromDate, fmt);
    final LocalDate d2 = LocalDate.parse(toDate, fmt);
    return ChronoUnit.DAYS.between(d1, d2);
  }

  @Override
  public LinkedHashMap<String, Float> splitDuration(String fromDate, String toDate, String portfolioName)
          throws IOException {
    fromDate = fromDate.replace("/","-");
    toDate = toDate.replace("/","-");
    LocalDate start = LocalDate.parse(fromDate);
    LocalDate end = LocalDate.parse(toDate);
    stockList = displayPortfolio(portfolioName);
    float years = ChronoUnit.YEARS.between(start, end);
    float months = ChronoUnit.MONTHS.between(start, end);
    float days = ChronoUnit.DAYS.between(start, end);

    LinkedHashMap<String, Float> timestamp = new LinkedHashMap<>();
    if (end.compareTo(start) > 0) {
      if (days > 4.0 && days <= 30.0) {
        for (int i = 0; i <= days; i++) {
          LocalDate valDate = start.plus(i, ChronoUnit.DAYS);
          float price = determinePortfolioHelper(stockList, String.valueOf(valDate));
          //add parameters for new value function
          timestamp.put(String.valueOf(valDate), price);
        }
      } else if (months > 4 && months <= 30) {
        for (int i = 0; i <= months; i++) {
          // String valDate = start.plus(i, ChronoUnit.MONTHS).toString();
          LocalDate valDate = start.plus(i,
                  ChronoUnit.MONTHS).with(TemporalAdjusters.lastDayOfMonth());
          float price = determinePortfolioHelper(stockList, String.valueOf(valDate));
          String disp = valDate.getMonth().toString().substring(0, 3) + " " + valDate.getYear();
          timestamp.put(disp, price);
        }
      } else if (years > 4 && years <= 30) {
        for (int i = 0; i <= years; i++) {
          String valDate = start.plus(i, ChronoUnit.YEARS).toString();
          float price = determinePortfolioHelper(stockList, valDate);
          String disp = String.valueOf(start.getYear());
          timestamp.put(disp, price);
        }
      }

    }
    return timestamp;
  }

}