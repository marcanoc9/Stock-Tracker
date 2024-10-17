// this is a program which reads  files containing stock market information sorted into 7 columns
//this program then searches and sorts through that data and organizes it in several ways
//such as finding the top five lowest and highest opening stocks

//Christopher Marcano
import java.io.File;
import java.util.*;
// sets up how to read the .txt file with the stock information
public class StockTracker {
    static final int DATE = 1;
    static ArrayList<Stock> parseStocks()
    {
        ArrayList<Stock> stocks = new ArrayList<>();
        String line;
        String[] tokens;
        File file = new File("stocks.txt");
        try
        { Scanner sc = new Scanner(file);
            while (sc.hasNextLine())
            { line = sc.nextLine();
                stocks.add(Factory(line.split(",")));
            }
            return stocks;
        } catch (Exception e)
        {
            //throw exception and print error message if scanner can't read properly
            System.out.println("error with scanner");
        }
        //returns the stock information to be used by the program
        return stocks;
    }
    static Stock Factory(String[] line)
    {
        // sets the variables for the 5 groups of information we want to find in the data
        double open = 0;
        double high = 0;
        double low = 0;
        double close = 0;
        double volume = 0;
        double[] params = { open, high, low, close, volume };
        for (int i = 2; i < line.length; i++)
        {
            try
            {
                params[i - 2] = Double.parseDouble(line[i]);
            } catch (Exception e)
            {
                //throws exception and prints error if the data is not a double
                System.out.println("error with double");
                System.exit(1);
            }
        }
        Stock stock = new Stock(line[0], line[1], params[0], params[1], params[2], params[3], params[4]);
        return stock; }
//this method finds the highest and lowest opens among the data
    static void HighestAndLowest(ArrayList<Stock> stocks)
    {
        Comparator<Stock> compareByOpen = (Stock s1, Stock s2) -> s1.Open().compareTo(s2.Open());
        stocks.sort(compareByOpen);
        Stock highestOpen = stocks.get(stocks.size() - 1);
        Stock lowestOpen = stocks.get(0);
        System.out.println("The highest open is: " + highestOpen.Symbol() + ": "
                + String.format("%.2f", highestOpen.Open()));
        System.out.println("The lowest open is: " + lowestOpen.Symbol() + ": "
                + String.format("%.2f", lowestOpen.Open()));
    }
    // this method finds the top five highest and lowest performing stocks
    static void highsAndLows(ArrayList<Stock> stocks)
    {
        stocks.sort((Stock s1, Stock s2) -> s1.High().compareTo(s2.High()));
        System.out.println("The top five highs are: ");
        for (int i = 1; i < 6; i++)
        {
            System.out.println("\t" + stocks.get(stocks.size() - i).Symbol() + ": " + stocks.get(stocks.size() - i).High());
        }
        System.out.println("The top 5 lows are: ");
        for (int i = 0; i < 6; i++)
        {
            System.out.println("\t" + stocks.get(i).Symbol() + ": " + stocks.get(i).Low());
        }
    }
    // this method shows the performance of a group of specific stocks
    static void Performance(ArrayList<Stock> stocks, String ticker)
    {
        ArrayList<String> symbols = new ArrayList<>();
        final String symbol = ticker.toString();
        Iterator it = stocks.iterator();
        Stock next = stocks.get(0);
        while (it.hasNext())
        {
            if (next.Symbol().compareTo(ticker) == 0) { break; }
            next = (Stock) it.next();
        }
        System.out.println(ticker + "'s Performance: " + "\n\tOpen: " + next.Open() + "\n\tHigh: " + next.High() + "\n\tLow: " + next.Low() + "\n\tClose: " + next.Close() + "\n\tVolume: " + next.Volume());
    }
    // this is the main method which prints highest volumes as well as specifies the specific stocks for the
    //above method.
    public static void main(String[] args)
    {
        ArrayList<Stock> stocks = parseStocks();
        Comparator<Stock> compareByVolume = (Stock s1, Stock s2) -> s1.Volume().compareTo(s2.Volume());
        stocks.sort(compareByVolume);
        Stock highestVol = stocks.get(stocks.size() - 1);
        System.out.println("The highest volume is: " + highestVol.Symbol() + ": "
                + String.format("%.2f", highestVol.Volume()));
        HighestAndLowest(stocks);
        highsAndLows(stocks);
        Performance(stocks, "WMT");
        Performance(stocks, "GE");
        Performance(stocks, "TWX");
        Performance(stocks, "KO");
        Performance(stocks, "DIS");
    }
}

