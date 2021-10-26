package component2;

public class CurrencyConverter1 {




	    // NOTE: In the UML design, the data types used for the getter methods and the properties were Strings, double,
	    // and names of the properties and the getter methods were name, factor, symbol, getName(), getFactor() and
	    // getSymbol() but I have used String [] and double arrays as well as currencies, symbols, factors, getCurrencies,
	    // getSymbols and getFactors names for the properties and getters for my convenience.

	    // Starting currencies, symbols and factors that will be used when the GUI loads up.
	    private String [] currencies = {"Japanese yen (JPY)", "Euro (EUR)", "US Dollars (USD)", "Australian Dollars (AUD)",
	            "Canadian Dollars (CAD)", "South Korean Won (KRW)", "Thai Baht (THB)",
	            "United Arab Emirates Dirham (AED)"};
	    private String [] symbols = {"¥", "€", "$", "A$", "C$", "₩", "฿", "د.إ"};
	    private double [] factors = {137.52, 1.09, 1.29, 1.78, 1.70, 1537.75, 40.52, 4.75};

	    // Getters to return the currencies, symbols and factors array:
	    public String[] getCurrencies() {
	        return currencies;
	    }

	    public String[] getSymbols() {
	        return symbols;
	    }

	    public double[] getFactors() {
	        return factors;
	    }
	}
