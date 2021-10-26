import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

/**
 * The main graphical panel used to display conversion components.
 * 
 * This is the starting point for the assignment.
 * 
 * The variable names have been deliberately made vague and generic, and most comments have been removed.
 * 
 * You may want to start by improving the variable names and commenting what the existing code does.
 * 
 * @author mdixon
 */
@SuppressWarnings("serial")
public class MainPanel extends JPanel {

	private final static String[] list = { "inches/cm","Pounds/Kilograms\r\n","Degrees/Radians\r\n","Acres/Hectares","Miles/Kilometres\r\n","Yards/Metres\r\n","Celsius/Fahrenheit" };
	private JTextField textField;
	private JLabel label;
	private JLabel Counter;
	private JCheckBox reverseCheckBox;
	private JComboBox<String> combo;
    int count=0;

	JMenuBar setupMenu() {

		JMenuBar menuBar = new JMenuBar();

		JMenu File = new JMenu("File");//creating the menu bar with file option.
		File.setToolTipText("Press here for opening file");
		File.setIcon(new ImageIcon("C:/Users/Dell/Desktop/Converter/icon/file.png"));
		
		JMenu Edit = new JMenu("Edit");//creating the menu bar with edit option.
		Edit.setToolTipText("Press here to edit option");
		Edit.setIcon(new ImageIcon("C:/Users/Dell/Desktop/Converter/icon/edit.png"));
		
		JMenu Source = new JMenu("Source");//creating the menu bar with source option.
		Source.setToolTipText("Press here for sources");
		Source.setIcon(new ImageIcon("C:/Users/Dell/Desktop/Converter/icon/source.png"));
		
		JMenu Help = new JMenu("Help");//creating the menu bar with help option.
		Help.setToolTipText("Press here to get some help");
		Help.setIcon(new ImageIcon("C:/Users/Dell/Desktop/Converter/icon/help.png"));

		menuBar.add(File);
		menuBar.add(Edit);
		menuBar.add(Source);
		menuBar.add(Help);

		JMenuItem Open = new JMenuItem("Open");
		Open.setToolTipText("Press here to create new file");//hover message
		Open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));//shorcutkey for open
		Open.setIcon(new ImageIcon("C:/Users/Dell/Desktop/Converter/icon/open.png"));
		JMenuItem Save = new JMenuItem("Save");
		Save.setToolTipText("Press here to save new file");
		Save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));//shorcutkey for Save
		Save.setIcon(new ImageIcon("C:/Users/Dell/Desktop/Converter/icon/save.png"));
		JMenuItem Exit = new JMenuItem("Exit");
		Exit.setToolTipText("Press here to exit file");
		Exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK));//shorcutkey for exit
		Exit.setIcon(new ImageIcon("C:/Users/Dell/Desktop/Converter/icon/exit.png"));
		Exit.addActionListener(e ->
		{
			System.exit(0);
		});
		File.add(Open);
		File.add(Save);
		File.add(Exit);
		
		JMenuItem Copy = new JMenuItem("Copy");
		Copy.setIcon(new ImageIcon("C:/Users/Dell/Desktop/Converter/icon/copy.png"));
		JMenuItem Paste = new JMenuItem("Paste");
		Paste.setIcon(new ImageIcon("C:/Users/Dell/Desktop/Converter/icon/paste.png"));
		JMenuItem Delete = new JMenuItem("Delete");
		Delete.setIcon(new ImageIcon("C:/Users/Dell/Desktop/Converter/icon/delet.png"));
		Edit.add(Copy);
		Edit.add(Paste);
		Edit.add(Delete);
		
		JMenuItem Generate = new JMenuItem("Generate");//creating sub type option for source
		JMenuItem Remove = new JMenuItem("Remove");
		JMenuItem Formate = new JMenuItem("Formate");
		Source.add(Generate);
		Source.add(Remove);
		Source.add(Formate);
        
		
		JMenuItem About = new JMenuItem("About");
		Help.add(About);
		
		
		About.addActionListener(e ->{
			JOptionPane.showMessageDialog(this, "GUI calculator converter application which calculate the values in different unit\n@SWETAYADAV 2020\n, JANAKPUR",
					"About",1);//a dialogue box describing the purpose of the application 
		});
		
		return menuBar;
	}


	MainPanel() {

		ActionListener listener = new ConvertListener();
		

		combo = new JComboBox<String>(list);
		combo.addActionListener(listener); //convert values when option changed

		JLabel inputLabel = new JLabel("Enter value:");

		JButton convertButton = new JButton("Convert");
		convertButton.addActionListener(listener); // convert values when pressed convert button
        
		Counter= new JLabel(String.valueOf("Conversion: " +count));
		label = new JLabel("---");
		textField = new JTextField(5);
		textField.addKeyListener(new KeyAdapter() {
		  public void keyPressed(KeyEvent e) {
			  if (e.getKeyCode()==KeyEvent.VK_ENTER)
				  convertButton.doClick();
		  }
		}
		   ); 
		
		JButton clear= new JButton("Clear");
		clear.addActionListener(e ->{
			textField.setText(null);
			label.setText(null);
			count=0;
			Counter.setText("conversion count=0");//displays a count of how many time conversions have been done.
		});//clear the value when clear button pressed
		
		//reverse checkbox
		reverseCheckBox = new JCheckBox("Reverse Conversion");
		reverseCheckBox.addItemListener(null);
		
		add(combo);
		add(inputLabel);
		add(textField);
		add(convertButton);
		add(label);
		add(reverseCheckBox);
		add(clear);
		add(Counter);

		setPreferredSize(new Dimension(800, 80));
		setBackground(Color.LIGHT_GRAY);
		
		JButton exitButton= new JButton("Exit");
		exitButton.addActionListener(listener);
		exitButton.setToolTipText("PRESS HERE TO EXIT");
	}

	private class ConvertListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {

			String text = textField.getText().trim();
		
			if(textField.getText().isEmpty()) {
				
				JOptionPane.showMessageDialog(textField, "you need to enter numbers//got it...");//
				
			}

			if (text.isEmpty() == false) {
				
				
				try {	
		
				double value = Double.parseDouble(text);

				// the factor applied during the conversion
				double factor = 0;

				// the offset applied during the conversion.
				double offset = 0;

				// Setup the correct factor/offset values depending on required conversion
				switch (combo.getSelectedIndex()) {

				case 0: // inches / cm
					factor = 2.54;
					break;
				case 1: // Pounds / Kilograms
					factor = 0.453592;
					break;
				case 2: // Degrees / Radians
					factor = 0.0174533;
					break;
				case 3: // Acres / Hectares
					factor = 0.404686;
					break;
				case 4: // Miles / Kilometres
					factor = 1.61;
					break;
				case 5: // Yards / Metres
					factor = 0.9144;
					break;
				case 6:// Celsius / Fahrenheit
					factor = 32;
					break;
					
				}

				double result = factor * value + offset;

				label.setText(Double.toString(result));
				Counter.setText("Conversion count: " + Integer.toString(++count));
				}
				catch(NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "The text you have entered is not valid");//value box empty then dialogmessage will be shown
						
					}
		
				if (reverseCheckBox.isSelected()) {
					if(text.isEmpty() == false) {
						double value = Double.parseDouble(text);

						// the factor applied during the conversion
						double factor = 0;

						// the offset applied during the conversion.
						double offset = 0;

						// Setup the correct factor/offset values depending on required conversion
						switch (combo.getSelectedIndex()) {

						case 0: // cm/inches
							factor = 0.393701;
							break;
						
						case 1: // kilogram/pound
							factor = 2.20462;
							break;
							
						case 2: //Radians/Degrees
							factor= 57.2958;
							break;
							
						case 3: //Hectares/Acres
							factor = 2.47105;
							break;
						
						case 4: //Kilometers/Miles
							factor = 0.621371;
							break;
							
						case 5: //Meters/Yards
							factor = 1.09361;
							break;
							
						case 6: //Fahreinheit/Celsius
							factor = (value-32)*0.556;
							break;
							
					}

						double result = factor * value + offset;
						String s = new DecimalFormat("0.00").format(result);// limited to two decimal places.
						label.setText(s);
					}
				}
				
				}
			
				}
			}
			
			}
		
		
	


