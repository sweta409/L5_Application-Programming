package component2;

import javax.swing.*;

public class CurrencyConverter {

    public static void main(String[] args) {

        // Creating the object of the JFrame class:
        JFrame myFrame = new JFrame("Currency Converter");

        // Creating the object of the MainPanel class:
        Converter mainPanel = new Converter();

        // Setting the JMenuBar for the frame:
        //myFrame.setJMenuBar(mainPanel.setUpMenu());

        // Adding the mainPanel object to the container contentPane object return by getContentPane() method:
        myFrame.getContentPane().add(mainPanel);

        // Making the JFrame visible:
        myFrame.setVisible(true);

        // Setting JFrame to be sized to fit the preferred size and layouts of its subcomponents:
        myFrame.pack();

        // Exit the application using the System exit method when we exit/close the frame:
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
