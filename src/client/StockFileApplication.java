package client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import fileprocessors.StockFileData;
import fileprocessors.StockFileReader;

public class StockFileApplication {
	
	public static void main(String args[]) throws IOException{
		StockFileReader fr = new StockFileReader("table.csv");
		
		List<HashMap<String, Double>> dataResult = populateStockFileData(fr.getHeaders(), fr.readFileData());
		StockFileData fileData = new StockFileData();
		fileData.addData(dataResult);
		fileData.printData();
		System.out.println(dataResult.size());
	}
	/**
	// * Complete the method body so that it returns the given structure needed to
	// * populate the data field in the StockFileData class.
	// * @param headers
	// * @param lines
	// * @return List
	// */
	public static List<HashMap<String, Double>> populateStockFileData(List<String> headers, List<String> lines){
		List<HashMap<String, Double>> dataResult = new ArrayList<>();

		// Insert your code here..
		for(String line : lines){
			HashMap<String, Double> data = new HashMap<>();
			List<String> lineData = Arrays.asList(line.split(","));
			for(int i = 0; i < headers.size(); i++){
				double value = Double.parseDouble(lineData.get(i));
				data.put(headers.get(i), value);
			}
			dataResult.add(data);
		}
		for(HashMap<String, Double> data : dataResult){
			System.out.println(data);
		}
		return dataResult;
	}
	
	
}
