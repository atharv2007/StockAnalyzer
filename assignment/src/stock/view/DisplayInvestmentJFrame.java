package stock.view;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class DisplayInvestmentJFrame extends JFrame implements ItemListener {

    static JFrame displayInvestmentFrame;

    static JLabel title, selectPortfolio, selectedPortfolio, lblInvestment, lblInvestmentValue;

    static JComboBox listOfPortfolios;

    static JButton btnBack;

    public static void main(String[] args) {
        displayInvestmentFrame = new JFrame("Investments");

        DisplayInvestmentJFrame di = new DisplayInvestmentJFrame();

        displayInvestmentFrame.setLayout(new FlowLayout());

        String portfolios[] = {"--", "Gaurav's Portfolio", "Ganla's Portfolio"};

        listOfPortfolios = new JComboBox<>(portfolios);

        // not sure what is happening here
        listOfPortfolios.addItemListener(di);

        title = new JLabel("List of Portfolio's");

        selectPortfolio = new JLabel("Select a portfolio");
        selectedPortfolio = new JLabel("-- selected");

        selectPortfolio.setForeground(Color.red);
        selectedPortfolio.setForeground(Color.blue);

        lblInvestment = new JLabel("Investment : ");
        lblInvestmentValue = new JLabel("Investment Value");
        // lblInvestment.setText(value);

        btnBack = new JButton("Back");

        JPanel p = new JPanel();

        p.add(selectPortfolio);
        p.add(listOfPortfolios);
        p.add(selectedPortfolio);
        p.add(lblInvestment);
        p.add(lblInvestmentValue);

        displayInvestmentFrame.add(p);

        displayInvestmentFrame.add(btnBack);

        displayInvestmentFrame.setSize(600, 600);
        displayInvestmentFrame.setVisible(true);

    }
    public void itemStateChanged(ItemEvent e)
    {
        // if the state combobox is changed
        if (e.getSource() == listOfPortfolios) {
 
            selectedPortfolio.setText(listOfPortfolios.getSelectedItem() + " selected");
        }
    }
}
