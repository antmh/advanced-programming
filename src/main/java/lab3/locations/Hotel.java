package lab3.locations;

public class Hotel extends Location implements Payable, Classifiable {
    private double price;
    private Rank rank;

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public Rank getRank() {
        return rank;
    }

    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        this.price = price;
    }

    public void setRank(Rank rank) {
        if (rank == null) {
            throw new IllegalArgumentException("Rank cannot be null");
        }
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "Hotel [name=" + getName() + "]";
    }
}
