package lab3;

public class Hotel extends Location implements Payable, Classifiable {
	private double price;
	private int rank;

	@Override
	public double getPrice() {
		return price;
	}

	@Override
	public int getRank() {
		return rank;
	}

	public void setPrice(double price) {
		if (price < 0) {
			throw new IllegalArgumentException("Price cannot be negative");
		}
		this.price = price;
	}

	public void setRank(int rank) {
		if (rank < 0) {
			throw new IllegalArgumentException("Rank cannot be negative");
		}
		this.rank = rank;
	}

	@Override
	public String toString() {
		return "Hotel [name=" + getName() + "]";
	}
}
