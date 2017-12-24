package LiveStock2;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Stock {

	private String symbol;
	private URL url;
	private String price_;
	private float price;

	public Stock(String symbol_) throws IOException{
		symbol = symbol_;
		System.out.println(symbol);
		url = checkURL(new URL("https://finance.yahoo.com/quote/%5E"+symbol+"?p=^"+symbol));

		if(price_!=null) price = Float.valueOf(price_);
	}

	public String getSymbol(){
		return symbol;
	}

	public URL getURL(){
		return url;
	}

	public float getPrice(){	
		return price;
	}

	private URL checkURL(URL url) throws IOException{	
		/*	if(urlHelper(url)) return url; //end here without having to call urlHelper twice
		else url = new URL("https://finance.yahoo.com/quote/"+symbol+"?p="+symbol);
		urlHelper(url); //Call helper again to set price
		return url;
		 */
		if(!urlHelper(url)){
			url = new URL("https://finance.yahoo.com/quote/"+symbol+"?p="+symbol);
			urlHelper(url); //run helper again with new url...
		}
		return url;
	}

	private boolean urlHelper(URL url) throws IOException{
		URLConnection urlConn = url.openConnection();
		InputStreamReader inStream = new InputStreamReader(urlConn.getInputStream());
		BufferedReader buff = new BufferedReader(inStream);
		//find a way to get buffer to skip about a third of the html, before reading... Takes too long...
		//Load lower half of view_source?
		//fix yahoo api
		//buff.skip(190000);
		String line = buff.readLine();
		boolean found = false;
		while(line!=null){


//https://www.msn.com/en-us/money/stockdetails/fi-126.1.FOX. NAS?symbol=FOX  &form=PRMFPS
//https://www.msn.com/en-us/money/stockdetails/fi-126.1.AMZN.NAS?symbol=AMZN &form=PRMFPS			
//https://www.msn.com/en-us/money/stockdetails/fi-126.1.AMD.NAS?symbol=AMD &form=PRMFPS			
//https://www.msn.com/en-us/money/stockdetails/fi-126.1.IXIC.NAS%3Fsymbol=IXIC%20&form=PRMFPS
			//if(line.contains(symbol+"\":{\"sourceInterval\":")){
			//\"currency\":\"USD\",\"regularMarketPrice\":	
			if(line.contains("\"currency\":\"USD\",\"regularMarketPrice\":")){
				found = true;
				int target = line.indexOf(symbol+"\":{\"sourceInterval\":");
				int dest = line.indexOf(".", target);
				int start = dest;
				while(line.charAt(start) != ':') start--;
				price_ = line.substring(start+1, dest+3);
			}
			line = buff.readLine();
		}
		return found;
	}

	public void stockNotFound(String stock){
		System.out.println(stock + " was not found.");
		System.out.println(stock + " Please recheck the symbol and try again.");
	}

}
