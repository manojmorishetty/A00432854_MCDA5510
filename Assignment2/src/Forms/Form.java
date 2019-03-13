package Forms;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.mcds5510.Entity.Transaction;
 
/**
 * This program demonstrates how to use JPanel in Swing.
 * @author www.codejava.net
 */
public class Form extends JFrame implements ActionListener{
     
    private JLabel ID = new JLabel("Enter ID: ");
    private static JLabel nameOnCard = new JLabel("Enter Name on Card: ");
    private static JLabel cardNumber = new JLabel("Enter Card Number: ");
    private static JLabel Quantity = new JLabel("Enter Quantity: ");
    private JLabel UnitPrice = new JLabel("Enter Unit Price: ");
    private JLabel UnitPriceValidation;
    private JLabel TotalPrice = new JLabel("Enter Total Price: ");
    private JLabel TotalPriceValidation;
    private static JLabel ExpDate = new JLabel("Enter Expiry Date: ");
    private static JLabel CreatedBy = new JLabel("Enter Created By: ");
     
    private JTextField IDField = new JTextField(30);
    private JTextField nameOnCardField = new JTextField(30);
    private JTextField cardNumberField = new JTextField(30);
    private JTextField QuantityField = new JTextField(30);
    private JTextField UnitPriceField = new JTextField(30);
    private JTextField TotalPriceField = new JTextField(30);
    private JTextField ExpDateField = new JTextField(30);
    private JTextField CreatedByField = new JTextField(30);
    
    
    private static JButton Submit = new JButton("Submit");
     
    public Form() {
        super("TRANSACTION");
         
        // create a new panel with GridBagLayout manager
        JPanel newPanel = new JPanel(new GridBagLayout());
         
        GridBagConstraints constraints = new GridBagConstraints();
        GridBagConstraints constraints2 = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);
        //constraints2.insets=new Insets(2,2,2,2);
         
        // add components to the panel
        constraints.gridx = 0;
        constraints.gridy = 0;     
        newPanel.add(ID, constraints);
 
        constraints.gridx = 1;
        constraints.gridy = 0;
        newPanel.add(IDField, constraints);
         
        constraints.gridx = 0;
        constraints.gridy = 1;     
        newPanel.add(nameOnCard, constraints);
         
        constraints.gridx = 1;
        constraints.gridy = 1;
        newPanel.add(nameOnCardField, constraints);
        
        constraints.gridx = 0;
        constraints.gridy = 2;     
        newPanel.add(cardNumber, constraints);
 
        constraints.gridx = 1;
        constraints.gridy = 2;
        newPanel.add(cardNumberField, constraints);
         
        constraints.gridx = 0;
        constraints.gridy = 3;     
        newPanel.add(Quantity, constraints);
         
        constraints.gridx = 1;
        constraints.gridy = 3;  
        newPanel.add(QuantityField, constraints);
        
        constraints.gridx = 0;
        constraints.gridy = 4;     
        newPanel.add(UnitPrice, constraints);

 
        constraints.gridx = 1;
        constraints.gridy = 4;  
        newPanel.add(UnitPriceField, constraints);
         
        constraints.gridx = 0;
        constraints.gridy = 5;     
        newPanel.add(TotalPrice, constraints);
        
         
        constraints.gridx = 1;
        constraints.gridy = 5;  
        newPanel.add(TotalPriceField, constraints);
        
        constraints.gridx = 0;
        constraints.gridy = 6;     
        newPanel.add(ExpDate, constraints);
         
        constraints.gridx = 1;
        constraints.gridy = 6;  
        newPanel.add(ExpDateField, constraints);
        
        constraints.gridx = 0;
        constraints.gridy = 7;     
        newPanel.add(CreatedBy, constraints);
         
        constraints.gridx = 1;
        constraints.gridy = 7;  
        newPanel.add(CreatedByField, constraints);
         
        constraints.gridx = 0;
        constraints.gridy = 8;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        Submit.addActionListener(this);
        newPanel.add(Submit, constraints);
         
        // set border for the panel
        newPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Enter Transaction Details"));
         
        // add the panel to this frame
        add(newPanel);        
        pack();
        setLocationRelativeTo(null);
    }
     
	@Override
	public void actionPerformed(ActionEvent e) {
		SubmitNewTransaction(e);
		
	}
	
	public int GetInt(String s) {
		int i = 0;
		try {
			i=Integer.parseInt(s);
		} catch(NumberFormatException n){
			
		}
		return i;

	}
	
	
    public Transaction SubmitNewTransaction(ActionEvent e) {
		Transaction trxn=new Transaction();
    	if(e.getSource()==Submit) {
    		//if()
        	trxn.setNameOnCard(nameOnCardField.getText());
        	trxn.setCardNumber(cardNumberField.getText());
        	trxn.setQuantity(QuantityField.getText());
        	trxn.setUnitPrice(Integer.parseInt(UnitPriceField.getText()));
        	trxn.setTotalPrice(Integer.parseInt(TotalPriceField.getText()));
        	trxn.setExpDate(ExpDateField.getText());
        	trxn.setCreatedBy(CreatedByField.getText());
        	nameOnCardField.setText("");
        	cardNumberField.setText("");
        	QuantityField.setText("");
        	UnitPriceField.setText("");
        	TotalPriceField.setText("");
        	ExpDateField.setText("");
        	CreatedByField.setText("");
        	JOptionPane.showMessageDialog(null, trxn.getExpDate());
    	}
    	return trxn;
    }  
    
    public static void main(String[] args) {
        // set look and feel to the system look and feel
//        try {
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }         
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new Form().setVisible(true);
//            }
//        });
    }
}
