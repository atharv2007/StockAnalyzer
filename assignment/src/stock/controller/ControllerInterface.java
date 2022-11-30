package stock.controller;

import java.io.IOException;


/**
 * This interface represents all the operations the Controller takes care of.
 */
public interface ControllerInterface {

  /**
   * Main controller function for portfolio maker.
   *
   * @throws IOException exception if illegal argument is passed.
   */
  void goController() throws Exception;

}
