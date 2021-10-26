package component2;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.io.File;
import java.io.FileInputStream;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;




public class Converter extends JFrame {
	
     CurrencyConverter1 currencies = new CurrencyConverter1();
     
	private String [] startingCurrencies = currencies.getCurrencies();
    private String [] startingSymbols = currencies.getSymbols();
    private double [] startingFactors = currencies.getFactors();
    private double [] newFactors = new double [8];
    private String [] newSymbols = new String [8];
    private String symbolForResult;
    private boolean usingCurrencyFromFile = false;
    private int conversionCount = 0;
   
    String [] testSymbols = {"¥", "€", "$", "A$", "C$", "₩", "฿", "د.إ", "kr", "R"};

	private JPanel contentPane;
	private JTextField ValueTextField;
	private JTextField ResultField;
	String[] list = { "Japanese Yen[JPY]","Euro[EUR]\r\n","US Dollar[USD]\r\n","Australian Dollar(AUD)","Canadian Dollar(CAD)\r\n","SouthKorea Won(KRW)","Thaibath(TMB)","UnitedArabEmiratesDirham(AED)" };
	private JComboBox<String> ComboBox;
	private JLabel countField;
	private JCheckBox CheckBox;
	int count=0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Converter frame = new Converter();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Converter() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 645, 339);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu File = new JMenu("File");
		File.setIcon(new ImageIcon("C:/Users/Dell/Desktop/icon/edit.png"));
		menuBar.add(File);
		
		JMenuItem Load = new JMenuItem("Load");
		Load.setToolTipText("Press here to Load file");
		Load.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_DOWN_MASK));//shorcutkey for Loadfile
		Load.setIcon(new ImageIcon("C:/Users/Dell/Desktop/icon/file.png"));
		File.add(Load);
		Load.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File file;
				JFileChooser jfc = new JFileChooser();
				int status=jfc.showOpenDialog(null);
				if(status == JFileChooser.APPROVE_OPTION) {
					file= jfc.getSelectedFile();
					try {
						BufferedReader br= new BufferedReader(new FileReader(file));
						Object[] element=br.lines().toArray();
						for(int i=0; i<element.length; i++) {
							String s=element[i].toString();
							ComboBox.addItem(s);
						}
				}
					catch(FileNotFoundException ex) {
						ex.printStackTrace();
					}
				}
			}
		});
		
		JMenuItem Exit= new JMenuItem("Exit");
		Exit.setToolTipText("Press here to exit file");
		Exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK));//shorcutkey for exit
		Exit.setIcon(new ImageIcon("C:/Users/Dell/Desktop/icon/exit.png"));
		Exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		File.add(Exit);
		
		
		JMenu Help = new JMenu("Help");
		Help.setToolTipText("Press here to get some help");
		Help.setIcon(new ImageIcon("C:/Users/Dell/Desktop/icon/help.png"));
		menuBar.add(Help);
		
		JMenuItem About = new JMenuItem("About");
		Help.add(About);
		
		About.addActionListener(e ->{
			JOptionPane.showMessageDialog(this, "GUI currency converter application which calculate the values in different Currency in pound\n@SWETAYADAV 2020\n, JANAKPUR",
					"About",1);//a dialogue box describing the purpose of the application 
		});
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel value = new JLabel("Enter Value:");
		value.setBounds(36, 69, 77, 13);
		panel.add(value);
		
		ValueTextField = new JTextField();
		ValueTextField.setBounds(122, 66, 133, 19);
		panel.add(ValueTextField);
		ValueTextField.setColumns(10);
		
		JLabel currency = new JLabel("Select Currency:");
		currency.setBounds(285, 69, 96, 13);
		panel.add(currency);
		
		ActionListener listener= new ConvertListener();
        ComboBox = new JComboBox(list);
		ComboBox.setBounds(399, 61, 178, 29);
		panel.add(ComboBox);
		
		JLabel lblNewLabel_2 = new JLabel("Result:");
		lblNewLabel_2.setBounds(128, 206, 45, 13);
		panel.add(lblNewLabel_2);
		
		ResultField = new JTextField();
		ResultField.setBounds(183, 203, 96, 19);
		panel.add(ResultField);
		ResultField.setColumns(10);
		
		JButton convert = new JButton("Convert");
		convert.addActionListener(listener);
		convert.setBounds(90, 116, 85, 21);
		convert.addActionListener(listener); // convert values when pressed convert button
        
		countField= new JLabel(String.valueOf("Conversion: " +count));
		//label = new JLabel("---");
		//ValueTextField = new JTextField(5);
		ValueTextField.addKeyListener(new KeyAdapter() {
		  public void keyPressed(KeyEvent e) {
			  if (e.getKeyCode()==KeyEvent.VK_ENTER)
				  convert.doClick();
		  }
		}
		   );
		panel.add(convert);
		
		JButton reset = new JButton("Reset");
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ValueTextField.setText(null);
				ResultField.setText(null);
				count=0;
				countField.setText("0");
			}
		});
		reset.setBounds(248, 116, 85, 21);
		panel.add(reset);
		
		CheckBox = new JCheckBox("Reverse Conversion");
		CheckBox.setBounds(370, 116, 178, 21);
		panel.add(CheckBox);
		
		JLabel conversion = new JLabel("Conversion Count:");
		conversion.setBounds(307, 206, 108, 13);
		panel.add(conversion);
		
		countField = new JLabel("-----");
		countField.setBounds(413, 206, 77, 13);
		panel.add(countField);
	}
	
	/*private class ConvertListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {

			String text = ValueTextField.getText().trim();
		
			if(ValueTextField.getText().isEmpty()) {
				
				JOptionPane.showMessageDialog(ValueTextField, "you need to enter numbers//got it...");//
				
			}

			if (text.isEmpty() == false) {
				
				
				try {	
		
				double value = Double.parseDouble(text);

				// the factor applied during the conversion
				double factor = 0;

				// Setup the correct factor/offset values depending on required conversion
				switch (ComboBox.getSelectedIndex()) {

				case 0: // Japanese Yen(JPY)
					factor = 0.0072;
					break;
				case 1: // Euro[EUR] 
					factor = 0.92;
					break;
				case 2: // US Dollar[USD]
					factor = 0.75;
					break;
				case 3: // Australian Dollar(CAD)
					factor = 0.57;
					break;
				case 4: // Canadian Dollar(CAD)
					factor = 0.58;
					break;
				case 5: // SouthKorea Won(KRW)
					factor = 0.00068;
					break;
				case 6:// Thaibath(TMB)
					factor = 0.025 ;
					break;
				case 7:// UnitedArabEmiratesDirham(AED)
					factor = 0.20;
					break;
					
				}

				double result = factor * value;

				ResultField.setText(Double.toString(result));
				countField.setText("" + Integer.toString(++count));
				String s = new DecimalFormat("0.00").format(result);// limited to two decimal places.
				ResultField.setText(s);
				}
				catch(NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "The text you have entered is not valid");//value box empty then dialogmessage will be shown
						
					}
		
				if (CheckBox.isSelected()) {
					if(text.isEmpty() == false) {
						double value = Double.parseDouble(text);

						// the factor applied during the conversion
						double factor = 0;

						// Setup the correct factor/offset values depending on required conversion
						switch (ComboBox.getSelectedIndex()) {

						case 0: // Japanese Yen(JPY)
							factor = 1.14;
							break;
						
						case 1: // Euro[EUR]
							factor = 144.61;
							break;
							
						case 2: // US Dollar[USD]
							factor= 117.73;
							break;
							
						case 3: // Australian Dollar(CAD)
							factor = 89.47;
							break;
						
						case 4: // Canadian Dollar(CAD)
							factor = 91.44;
							break;
							
						case 5: // SouthKorea Won(KRW)
							factor = 0.11;
							break;
							
						case 6: // UnitedArabEmiratesDirham(AED)
							factor = 32.05;
						
							break;
							
					}

						double result = value*factor;
						String s = new DecimalFormat("0.00").format(result);// limited to two decimal places.
						ResultField.setText(s);
					}
				}
				
				}
			
				}*/
			


//implementing  listener to the component
class ConvertListener implements ActionListener {

@Override
public void actionPerformed(ActionEvent event) {

        String text =ValueTextField.getText().trim();

        if (!text.isEmpty() && !CheckBox.isSelected()) {

            double value = 0;

            try{
                value = Double.parseDouble(text.trim());  
            }catch (Exception e){
                JOptionPane.showMessageDialog(null,
                        "Invalid number. Please enter a valid number.",
                        "WARNING",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            double factor = usingCurrencyFromFile ? getNewFactors(ComboBox.getSelectedIndex(), false)
                    : getConversionFactor(ComboBox.getSelectedIndex(), false);
            double result;
           
            if (factor == 0.0) {
                JOptionPane.showMessageDialog(null, "Conversions cannot be made with " +
                        "invalid data!", "Trying to convert an invalid data!", JOptionPane.WARNING_MESSAGE);
                return;
            }else {
               
                result = value * factor;
                String resultIn2dp = String.format("%.2f", result);
                ResultField.setText(symbolForResult + resultIn2dp);
            }
            conversionCount++;
            countField.setText("" + conversionCount);
        } else if (!text.isEmpty() && CheckBox.isSelected()){

         

//the factor applied during the conversion
            double value = 0;
           
            try{
                value = Double.parseDouble(text.trim());
               
            }catch (Exception e){
                JOptionPane.showMessageDialog(null,
                        "Invalid number. " +
                                "Please enter a valid number.",
                        "WARNING",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            double factor = usingCurrencyFromFile ? getNewFactors(ComboBox.getSelectedIndex(),
                    true)
                    : getConversionFactor(ComboBox.getSelectedIndex(), true);
            double result;
           
            if (factor == 0.0) {
                JOptionPane.showMessageDialog(null, "Conversions cannot be made with " +
                        "invalid data!", "Trying to convert an invalid data!", JOptionPane.WARNING_MESSAGE);
                return;
            }else {
                
                result = value / factor;
                String resultIn2dp = String.format("%.2f", result);
                ResultField.setText(symbolForResult + resultIn2dp);
            }
            conversionCount++;
            countField.setText(" " + conversionCount);

        }else {
           
            JOptionPane.showMessageDialog(null,
                    "The TextField is empty.Please enter a number.",
                    "WARNING",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

private double getConversionFactor(int indexPosition, boolean isChecked){

    double factor = 0;

    switch(indexPosition){
        case 0:
            factor = startingFactors[0];
            break;
        case 1:
            factor = startingFactors[1];
            break;
        case 2:
            factor = startingFactors[2];
            break;
        case 3:
            factor = startingFactors[3];
            break;
        case 4:
            factor = startingFactors[4];
            break;
        case 5:
            factor = startingFactors[5];
            break;
        case 6:
            factor = startingFactors[6];
            break;
        case 7:
            factor = startingFactors[7];
            break;
    }

    if (isChecked){
        symbolForResult = "£";
    }else {
        symbolForResult = startingSymbols[indexPosition];
    }

    return factor;
}

private double getNewFactors(int indexPosition, boolean isChecked){

    double factor = 0;

    if (isChecked){
        symbolForResult = "£";
    }else {
        symbolForResult = newSymbols[indexPosition];
    }

    switch(indexPosition){
        case 0:
            factor = newFactors[0];
            break;
        case 1:
            factor = newFactors[1];
            break;
        case 2:
            factor = newFactors[2];
            break;
        case 3:
            factor = newFactors[3];
            break;
        case 4:
            factor = newFactors[4];
            break;
        case 5:
            factor = newFactors[5];
            break;
        case 6:
            factor = newFactors[6];
            break;
        case 7:
            factor = newFactors[7];
            break;
    }
    return factor;
}
 //implementing actionlistener to LoadMenuItem
private class LoadMenuItemListener implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {

    JFileChooser fileChooser = new JFileChooser();

        int userOption = fileChooser.showDialog(null,"Select file");
        fileChooser.setVisible(true);

        if (userOption == JFileChooser.APPROVE_OPTION) {
            File fileName = fileChooser.getSelectedFile();
           CurrencyFile(fileName); 

            int counterForInvalidData = 0;

            for (int i = 0; i < ComboBox.getItemCount(); i++) {
                if (ComboBox.getItemAt(i).contains("Invalid data")) {
                    counterForInvalidData++;
                }
            }

            if (ComboBox.getItemCount() == counterForInvalidData) {

                String message = "Since none of the conversion " +
                        "data (conversion currencies, factors and symbols) " +
                        "from the text file are valid,\nDo you want to use the default conversion data " +
                        "(conversion currencies, factors and symbols)?";

                int userChoice = JOptionPane.showConfirmDialog(null, message,
                        "NO VALID DATA", JOptionPane.YES_NO_OPTION);

                if (userChoice == JOptionPane.YES_OPTION) {
                	ComboBox.removeAllItems();
                    for(String currency : startingCurrencies){
                    	ComboBox.addItem(currency);
                    }
                    usingCurrencyFromFile = false;
                }else{
                   
                    usingCurrencyFromFile = true;
                }
            }else {
                usingCurrencyFromFile = true;
            }
            ValueTextField.setText("");
            ResultField.setText("---");
        }
    }

//creating a method  to CurrencyFile
void CurrencyFile(File userSelectedFile) {

   try {

       BufferedReader inputFromFile =
               new BufferedReader(new InputStreamReader(new FileInputStream(userSelectedFile), "UTF8"));

       String line = inputFromFile.readLine();

       int counterForFactors = 0;
       int counterForSymbols = 0;

       ComboBox.removeAllItems();  //replace the loaded currency name to loaded file

       while ( line != null ) {

           String [] parts = line.split(","); 

           if (parts.length < 3) {
               JOptionPane.showMessageDialog(null, "Invalid number of data values!\n" +
                               "There should be 3 values(currency, currency conversion factory and currency " +
                               "symbol) in a line of the file!",
                       "ERROR!", JOptionPane.ERROR_MESSAGE);
               ComboBox.addItem("Invalid data ");
               newFactors[counterForFactors] = 0.0;
               newSymbols[counterForSymbols] = "Invalid";
               counterForFactors++;
               counterForSymbols++;
           }else {
            
               for(int i = 0; i < parts.length; i++){
                   if (i == 0) {
                      
                	   ComboBox.addItem(parts[i].trim());
                   }else if (i == 1){
                  
                       try{
                           newFactors[counterForFactors] = Double.parseDouble(parts[i].trim());
                           counterForFactors++;
                       }catch (Exception e){
                           JOptionPane.showMessageDialog(null,
                                   "There was invalid value(Non-numerical) for the conversion factor " +
                                           "in the file.\nThe invalid value caused the parsing from a String to " +
                                           "a Double to fail!\n"
                                           + e.getMessage() + ".", "ERROR!", JOptionPane.ERROR_MESSAGE);
                      
                           ComboBox.removeItemAt(ComboBox.getItemCount() - 1);
                           ComboBox.addItem("Invalid data");
                           newFactors[counterForFactors] = 0.0;
                           newSymbols[counterForSymbols] = "Invalid";
                           counterForFactors++;
                           counterForSymbols++;

                           break;
                       }
                   }else {

                       String fileSymbol = parts[i].trim();

                  
                       boolean symbolDoesExist = false;
                       for(String symbol : testSymbols){
                           if (fileSymbol.equals(symbol)){
                               newSymbols[counterForSymbols] = fileSymbol;
                               counterForSymbols++;
                               symbolDoesExist = true;
                           }
                       }
                       if (!symbolDoesExist){
                           JOptionPane.showMessageDialog(null, "Invalid currency " +
                                               "symbol from the file has been found! Invalid symbol: \""
                                           + fileSymbol + "\".",
                                       "ERROR!", JOptionPane.ERROR_MESSAGE);
                           ComboBox.removeItemAt(ComboBox.getItemCount() - 1);
                           ComboBox.addItem("Invalid data");
                          
                           newFactors[counterForFactors - 1] = 0.0;
                           newSymbols[counterForSymbols] = "Invalid";
                           counterForFactors++;
                           counterForSymbols++;
                       }
                   }
               }
           }
           line = inputFromFile.readLine();
       }
      
       inputFromFile.close();

   } catch (Exception e) {

       String errorMessage = e.getMessage();

       JOptionPane.showMessageDialog(null, errorMessage,
               "ERROR!", JOptionPane.ERROR_MESSAGE);
     }

    }
   }
  }
	}


