package LiveStock2;

import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;

public class LiveStock {

	public static void main(String[] args) throws IOException{
	//	/*
		Scanner sc = new Scanner(System.in);
		System.out.print("Input stock symbol (IN BLOCK): ");
		final String symbol = sc.next();
		Stock stock = new Stock(symbol);
		if(stock.getPrice()==0) stock.stockNotFound(symbol);
		System.out.println(stock.getPrice());
	//	*/
		
		
		/*
		ArrayList<Stock> stock_list = new ArrayList<Stock>();
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Input desired stock symbols (IN BLOCK):");
		System.out.println("Input \"DONE\" when finished.");
		String symbol = "";
		while(sc.hasNext()){
			symbol = sc.next();
			if(symbol.equals("DONE"))
				break;
			stock_list.add(new Stock(symbol));
		}
		
		for(Stock stock: stock_list){
			System.out.println(stock.getSymbol() + ": " + stock.getPrice());
		}
		*/		
	}	

}
