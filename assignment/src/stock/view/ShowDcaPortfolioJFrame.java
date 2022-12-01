package stock.view;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class ShowDcaPortfolioJFrame extends JFrame implements ItemListener {
    
    static JFrame showDcaPortFrame;

    static JLabel title, selectPortfolio, selectedPortfolio;

    static JComboBox listOfPortfolios;

    static JTable portfolioTable;

    static JButton btnBack;
    public static void main(String[] args) {
        showDcaPortFrame = new JFrame("List of DCA Portfolios");

        ShowDcaPortfolioJFrame sfp = new ShowDcaPortfolioJFrame();

        showDcaPortFrame.setLayout(new FlowLayout());

        String portfolios[] = {"--", "Gaurav's Portfolio", "Ganla's Portfolio"};

        listOfPortfolios = new JComboBox<>(portfolios);

        // not sure what is happening here
        listOfPortfolios.addItemListener(sfp);

        title = new JLabel("List of Portfolio's");

        selectPortfolio = new JLabel("Select a portfolio");
        selectedPortfolio = new JLabel("-- selected");

        selectPortfolio.setForeground(Color.red);
        selectedPortfolio.setForeground(Color.blue);

        String[][] data = {
            { "GOOG", "1234567", "30%", "2022/11/21", "1234512345", "123498765" },
            { "MSFT", "1234567", "50%", "2022/11/23", "1234512345", "123498765" },
        };

        String[] columnVals = {"Symbol", "Quantity", "Percentage", "Date of Purchase", "Purchase", "Investment Amount"};

        portfolioTable = new JTable(data, columnVals);
        portfolioTable.setBounds(30, 40, 200, 300);

        btnBack = new JButton("Back");

        JPanel p = new JPanel();
        JScrollPane sp = new JScrollPane(portfolioTable);
        // p.add(title);
        p.add(selectPortfolio);
        p.add(listOfPortfolios);
        p.add(selectedPortfolio);

        showDcaPortFrame.add(p);
        showDcaPortFrame.add(sp);

        showDcaPortFrame.add(btnBack);

        showDcaPortFrame.setSize(600, 600);
        showDcaPortFrame.setVisible(true);
    }
    public void itemStateChanged(ItemEvent e)
    {
        // if the state combobox is changed
        if (e.getSource() == listOfPortfolios) {
 
            selectedPortfolio.setText(listOfPortfolios.getSelectedItem() + " selected");
        }
    }
}
