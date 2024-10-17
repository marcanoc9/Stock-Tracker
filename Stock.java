// this class takes the variables from the main class and returns their values to be used and printed
public class Stock {
    private String date;
    private Double volume;
    private Double open;
    private Double close;
    private Double high;
    private Double low;
    private String symbol;
//************************************************************************************
    public Double High()
    {
        return high;
    }
    public Double Open()
    {
        return open;
    }
    public Double Close()
    {
        return close;
    }
    public Double Volume()
    {
        return volume;
    }
    public Double Low()
    {
        return low;
    }
    public String Symbol()
    {
        return symbol;
    }
    public String getDate()
    {
        return date;
    }
    public Stock(String symbol, String date, double open, double high, double low, double close, double volume)
    {
        this.symbol = symbol; this.date = date; this.open = open; this.close = close;
        this.high = high; this.low = low; this.volume = volume;
    }
}

