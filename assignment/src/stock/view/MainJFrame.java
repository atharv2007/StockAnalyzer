package stock.view;

import java.awt.*;
import javax.swing.*;

public class MainJFrame extends JFrame {
    public static void main(String[] args) {
        JFrame homePage = new JFrame("Stock Analyzer");

        JPanel mainMenu = new JPanel(new GridLayout(1, 0));

        JLabel projectTitle = new JLabel("Welcome", JLabel.CENTER);
        projectTitle.setFont(new Font("Verdana", Font.PLAIN, 24));
        projectTitle.setVerticalAlignment(JLabel.TOP);
        projectTitle.setHorizontalAlignment(JLabel.CENTER);

        JButton btnCreateFlexiblePortfolio = new JButton("Create Flexible Portfolio");
        JButton btnCreateDcaPortfolio = new JButton("Create DCA Portfolio");
        JButton btnShowFlexiblePortfolio = new JButton("Show Flexible Portfolio");
        JButton btnShowDcaPortfolio = new JButton("Show DCA Portfolio");
        JButton btnPurchaseToFlexiblePortfolio = new JButton("Purchase to Flexible Portfolio");
        JButton btnSellFromFlexiblePortfolio = new JButton("Sell From Flexible Portfolio");
        JButton btnDisplayInvestment = new JButton("Display Investment");
        JButton btnLoadFromFiles = new JButton("Load From Files");


        projectTitle.setBounds(150, 20, 300, 40);

        btnCreateFlexiblePortfolio.setBounds(150,100,300, 40);
        btnCreateDcaPortfolio.setBounds(150,150,300, 40);
        btnShowFlexiblePortfolio.setBounds(150,200,300, 40);
        btnShowDcaPortfolio.setBounds(150,250,300, 40);
        btnPurchaseToFlexiblePortfolio.setBounds(150,300,300, 40);
        btnSellFromFlexiblePortfolio.setBounds(150,350,300, 40);
        btnDisplayInvestment.setBounds(150,400,300, 40);
        btnLoadFromFiles.setBounds(150,450,300, 40);

        mainMenu.add(projectTitle);

        mainMenu.add(btnCreateFlexiblePortfolio);
        mainMenu.add(btnCreateDcaPortfolio);
        mainMenu.add(btnShowFlexiblePortfolio);
        mainMenu.add(btnShowDcaPortfolio);
        mainMenu.add(btnPurchaseToFlexiblePortfolio);
        mainMenu.add(btnSellFromFlexiblePortfolio);
        mainMenu.add(btnDisplayInvestment);
        mainMenu.add(btnLoadFromFiles);

        homePage.setSize(600, 600);
        mainMenu.setLayout(null);
        homePage.setVisible(true);

        homePage.add(mainMenu);

        // hom
    }
}
