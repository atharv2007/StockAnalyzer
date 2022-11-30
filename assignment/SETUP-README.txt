How to run Portfolio Maker:

1. Download the JAR file containing all supporting files onto local device.
Device now contains all required folders and supporting files in local JAR file.

2. Open command prompt and run program by using command [java -jar assignment_4.jar].
Menu is printed now and lets user use program functionalities.

-To open a user made (.csv ONLY) pre-existing file, option 5 in the menu lets the user input a path stream for file to be read. 
Provide the path correctly to load the file. The program autoatically stores all created/loaded portfolios into the 'allPortfolios' folder.
All existing portfolios can be found here. Finding total value of a portfolio needs a pre-existing portfolio. 
This is because a new portfolio created in current session can not have older values.




Specific Case:

1. Once downloaded, run the program by using command [java -jar assignment_4.jar].
2. The menu is printed. Enter '2' to create a new extended portfolio.
3. Enter the correct ticker symbol for current stock to be added to portfolio.
4. Enter the number of shares you want to buy for the respective ticker, when prompted. 
5. Carefully enter a valid date of purchase of stocks in said portfolio.
6. You are now asked if you want to continue adding stocks to the portfolio. Exits loop unless explicitly told 'y'.
7. Perform steps 4-6 three times to finally add 3 stocks into the current portfolio.
8. Say 'no' when asked if you want to add more stocks to current portfolio.
9. Enter a unique name to name portfolio.
10. Menu appears. Press 5 to determine the value of the portfolio.
11. Enter correct date to view value.
12. Menu appears. Press 6 to determine the total cost basis of the portfolio. 
13. Enter correct date to view value.



Valid dates: Any market-open date in the YYYY-MM-DD format.

Valid stocks: All stocks in the AlphaVantage API. 
