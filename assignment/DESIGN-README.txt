Portfolio Maker

Design Changes:
Menu flow was changed, as asked to incorporate new features.
New stock package is introduced for the extended portfolio. This includes all data pertaining to the portfolio.
New cases are introduced into the Controller, including their supporting functions in the Controller.
Functionalities are added into the model for the required new computations. 
AlphaVantage API is now incorporated into the program. 
New View functions are introduced for message displays.

Design:
The program is based on the MVC(Model-View-Controller) design paradigm.
All code exists in the stock package.
We divide the functionality based on the computation, input/output handling and the controlling of data.
The model, controller and view aspects each have an interface and an implementation of the interface.

Model interface and implementation: This interface represents all the functions the model takes care of. 
The implementation defines methods that need to be performed by the Model.
It contains methods to
	
	-Create stock [void createStock(String ticker, int share)]

	-View portfolio [HashMap returnPortfolio()]

	-Determine value of portfolio [double valueOfPortfolio(HashMap portfolioName, HashMap stock2, String date)]

	-Get all portfolio names [ArrayList getPortfolioNames()]

	-Format date into required format [String dateFormatting(String date)]

	-Store data into a .csv file [void storeCsv(HashMap<String, Integer> stock, String portfolioName)]

	-Load and read data from a .csv file [HashMap readingCSV(String portfolioName, boolean b)]

	-Check if portfolio with current name already exists [boolean checkIfPortfolioFileExists(String portfolioName, ArrayList portfolio_names)]

	-To check if ticker symbol is valid [int checkTicker(HashMap loadedCsv, String date, String ticker)]


Controller interface and implementation: This interface represents all the functions carried out by the controller. 
The controller is responsible for calling methods from the View and Model aspects to perform computation and I/O activities. 
It contains methods to

	-To check for empty string [private String checkEmpty(String s,Scanner sc)]

	-To create a constructor of the Controller type [public Controller(ViewInterface view, ModelInterface model, Readable in, Appendable out)]

	-Main go() function responsible for controlling the functionalities the program provides, in a switch case format.

	-To print the respective stocks into the portfolio. Implicit exiting loop until 'yes' is entered [private void takeStocksToPortfolio(Scanner sc)]

	
View interface and implementation: This interface repesents all the functions carried out by the View aspect of thee program. 
The view is responsible for the Input and Output streams of the program. 
It contains methods to

	-Print menu of the program [public void printMenu(Appendable out)]

	-Other public void methods containing messages as output streams that throw exceptions in the case of errors in arguments.
	
	
Main Runner Function: Main driver function of the program. Passes System.in and System.out as Readable and Appendable types for I/O activities. 
This is so the user can enter data in any easily readable format. 
These are simple abstracted forms of the PrintStream and InputStream.
		Runs the go() function by first creating View and Model instances and passing them to the Controller implementation with the help of a Controller object. 
		Throws exceptions if invalid arguments are passed.









