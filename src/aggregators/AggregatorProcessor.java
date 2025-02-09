package aggregators;

import fileprocessors.StockFileReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AggregatorProcessor <T extends Aggregator> {

    T aggregator;
    String file;

    public StockFileReader sfr = new StockFileReader("table.csv");

    public AggregatorProcessor(T Aggregator, String file) {
        super();
        this.aggregator = Aggregator;
        this.file = file;
    }

    public double runAggregator(int index){
        index--;
        for(Double d : getColumn(index)) {
            aggregator.add(d);
        }
        return aggregator.calculate();
    }

    public List<List<Double>> getAllColumns(){
        List<List<Double>> columns = new ArrayList<>();
        List<String> lines = List.of();

        List<Double> open = new ArrayList<>();
        List<Double> high = new ArrayList<>();
        List<Double> low = new ArrayList<>();
        List<Double> close = new ArrayList<>();
        List<Double> volume = new ArrayList<>();
        List<Double> adj_Close = new ArrayList<>();

        // columns [0] = Open
        // columns [1] = High
        // etc...

        try {
            lines = sfr.readFileData();
        } catch (IOException e) {
            System.out.println("Error reading file");
        }

        for(String line : lines){
            List<String> s = List.of(line.split(","));
            for(int i = 0; i < 6; i++){
                double doubleItem = Double.parseDouble(s.get(i));
                switch(i){
                    case 0: open.add(doubleItem); break;
                    case 1: high.add(doubleItem); break;
                    case 2: low.add(doubleItem); break;
                    case 3: close.add(doubleItem); break;
                    case 4: volume.add(doubleItem); break;
                    case 5: adj_Close.add(doubleItem); break;
                    default: break;
                }
            }
        }

        columns = new ArrayList<>(List.of(open, high, low, close, volume, adj_Close));

//        for(List<Double> a : columns){
//            System.out.println(a);
//        }

        return columns;
    }

    public List<Double> getColumn(int i){
        List<List<Double>> temp = getAllColumns();
        return temp.get(i);
    }

}
