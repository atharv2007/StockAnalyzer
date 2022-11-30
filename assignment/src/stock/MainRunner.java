package stock;

import java.io.IOException;
import java.io.InputStreamReader;

import stock.controller.Controller;
import stock.model.ModelInterface;
import stock.model.StockModel;
import stock.view.View;
import stock.view.ViewInterface;

/**
 * Main runner class for the portfolio program. Driver function called here.
 */
public class MainRunner {

  /**
   * Create Model, Controller, View instances. Pass values through Controller object.
   *
   * @param args of type String[].
   * @throws IOException throws if illegal argument is passed.
   */
  public static void main(String[] args) throws Exception {
    ViewInterface view = new View();
    ModelInterface model = new StockModel();
    Controller conObj = new Controller(view, model, new InputStreamReader(System.in), System.out);
    conObj.goController();
  }

}
