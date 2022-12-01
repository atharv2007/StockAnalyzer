package stock.view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class CreateDcaPortfolio extends JFrame implements ActionListener {

    private Container c;

    private JLabel lblTitle;
    private JLabel lblInvestedAmount;
    private JLabel lblSymbol;
    private JLabel lblDop;
    private JLabel lblCommission;

    private JTextField tfInvestedAmount;
    private JTextField tfSymbol;
    private JTextField tfDop;
    private JTextField tfCommission;

    private JButton btnSubmit;

    // private JLabel lblPurchaseCost;
    // private JLabel lblInvestedAmount;

    // private JLabel displayPurchaseCost;
    // private JLabel displayInvestedAmount;

    // private JButton btnAddMoreYes;
    // private JButton btnAddMoreNo;

    public CreateDcaPortfolio() {
        setTitle("Create DCA Portfolio");
        setBounds(300, 90, 900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        c = getContentPane();
        c.setLayout(null);

        lblTitle = new JLabel("Create DCA Portfolio");
        lblTitle.setFont(new Font("Arial", Font.PLAIN, 30));
        lblTitle.setSize(500, 30);
        lblTitle.setLocation(300, 30);
        c.add(lblTitle);

        // Invested Amount
        lblInvestedAmount = new JLabel("Invested Amount");
        lblInvestedAmount.setFont(new Font("Arial", Font.PLAIN, 20));
        lblInvestedAmount.setSize(200, 20);
        lblInvestedAmount.setLocation(100, 150);
        c.add(lblInvestedAmount);

        tfInvestedAmount = new JTextField();
        tfInvestedAmount.setFont(new Font("Arial", Font.PLAIN, 15));
        tfInvestedAmount.setSize(190, 30);
        tfInvestedAmount.setLocation(300, 150);
        c.add(tfInvestedAmount);

        // Symbol
        lblSymbol = new JLabel("Symbol");
        lblSymbol.setFont(new Font("Arial", Font.PLAIN, 20));
        lblSymbol.setSize(100, 20);
        lblSymbol.setLocation(100, 100);
        c.add(lblSymbol);

        tfSymbol = new JTextField();
        tfSymbol.setFont(new Font("Arial", Font.PLAIN, 15));
        tfSymbol.setSize(190, 30);
        tfSymbol.setLocation(300, 100);
        c.add(tfSymbol);

        // Date of Purchase
        lblDop = new JLabel("Date Of Purchase");
        lblDop.setFont(new Font("Arial", Font.PLAIN, 20));
        lblDop.setSize(200, 20);
        lblDop.setLocation(100, 200);
        c.add(lblDop);

        tfDop = new JTextField();
        tfDop.setFont(new Font("Arial", Font.PLAIN, 15));
        tfDop.setSize(190, 30);
        tfDop.setLocation(300, 200);
        c.add(tfDop);

        // Commission
        lblCommission = new JLabel("Commission");
        lblCommission.setFont(new Font("Arial", Font.PLAIN, 20));
        lblCommission.setSize(200, 20);
        lblCommission.setLocation(100, 250);
        c.add(lblCommission);

        tfCommission = new JTextField();
        tfCommission.setFont(new Font("Arial", Font.PLAIN, 15));
        tfCommission.setSize(190, 30);
        tfCommission.setLocation(300, 250);
        c.add(tfCommission);

        btnSubmit = new JButton("Submit");
        btnSubmit.setFont(new Font("Arial", Font.PLAIN, 15));
        btnSubmit.setSize(200, 40);
        btnSubmit.setLocation(300, 300);
        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(c, "Add more stocks?", "Options",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if (result == JOptionPane.YES_OPTION) {
                    //
                } else if (result == JOptionPane.NO_OPTION) {
                    //
                } else {
                    //
                }
            }
        });
        c.add(btnSubmit);

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        
    }

}

class CDFrame {
    public static void main(String[] args) throws Exception {
        CreateDcaPortfolio cdp = new CreateDcaPortfolio();
    }
}