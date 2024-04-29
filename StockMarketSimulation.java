package css217_8.ex1;
import java.util.ArrayList;
import java.util.List;

interface Observer {
    void update(float price);
}

interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}

class Stock implements Subject {
    private String symbol;
    private float price;
    private List<Observer> observers;

    public Stock(String symbol, float price) {
        this.symbol = symbol;
        this.price = price;
        this.observers = new ArrayList<>();
    }

    public String getSymbol() {
        return symbol;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
        notifyObservers();
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(price);
        }
    }
}

class Investor implements Observer {
    private String name;

    public Investor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void update(float price) {
        System.out.println("Investor " + name + " received update: Price is now " + price);
    }
}

public class StockMarketSimulation {
    public static void main(String[] args) {
        Stock appleStock = new Stock("AAPL", 150.0f);
        Stock googleStock = new Stock("GOOGL", 2500.0f);

        Investor investor1 = new Investor("Alice");
        Investor investor2 = new Investor("Bob");

        appleStock.registerObserver(investor1);
        appleStock.registerObserver(investor2);
        googleStock.registerObserver(investor2);

        appleStock.setPrice(155.0f);
        googleStock.setPrice(2600.0f);
    }
}
