package stock.stock;

public class stockDetails implements StockInterface {


  String symbol;
  int quantity;
  String date;
  float purchaseCost;
  float investedAmount;


  public stockDetails(String symbol, int quantity, String date, float purchaseCost, float investedAmount) {
    this.quantity = quantity;
    this.symbol = symbol;
    this.date = date;
    this.purchaseCost = purchaseCost;
    this.investedAmount = investedAmount;
  }

  @Override
  public int getQuantity() {
    return this.quantity;
  }

  @Override
  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  @Override
  public String getSymbol() {
    return this.symbol;
  }

  @Override
  public String getDate() {
    return this.date;
  }

  @Override
  public float getPurchaseCost(){
    return this.purchaseCost;
  }

  @Override
  public float getInvestedAmount(){
    return this.investedAmount;
  }

}
