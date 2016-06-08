package libs;
import java.util.Enumeration;
import java.util.Vector;

public class Test1 {
	public static void main(String[] args) {
	}
}

class Movie {
	public static final int CHILDRENS = 2;
	public static final int REGULAR = 0;
	public static final int NEW_RELEASE = 1;
	private String _title;
	private Price _price;
	public Movie(String title, int priceCode) {
		_title = title;
		setPriceCode(priceCode);
	}
	public int getPriceCode() {
		return _price.getPriceCode();
	}
	public void setPriceCode(int arg) {
		switch (arg) {
		case REGULAR:
			break;
		case CHILDRENS:
			break;
		case NEW_RELEASE:
			break;
		default:
			throw new IllegalArgumentException("Incorrect Price Code");
		}
	}
	public String getTitle() {
		return _title;
	}
	public double getCharge(int daysRented) {
		return _price.getCharge(daysRented);
	}
	public int getFrequentRenterPoints(int daysRented) {
		return _price.getFrequentRenterPoints(daysRented);
	}
	//
	abstract class Price {
		public abstract int getPriceCode();
		public abstract double getCharge(int daysRented);
		public int getFrequentRenterPoints(int daysRented) {
			return 1;
		}
	}
	class ChildrensPrice extends Price {
		@Override
		public int getPriceCode() {
			return Movie.CHILDRENS;
		}
		@Override
		public double getCharge(int daysRented) {
			// TODO Auto-generated method stub
			double result = 1.5;
			if (daysRented > 3) {
				result += (daysRented - 3) * 1.5;
			}
			return result;
		}
	}
	class NewReleasePrice extends Price {
		@Override
		public int getPriceCode() {
			// TODO Auto-generated method stub
			return Movie.NEW_RELEASE;
		}
		@Override
		public double getCharge(int daysRented) {
			// TODO Auto-generated method stub
			return daysRented * 3;
		}
		@Override
		public int getFrequentRenterPoints(int daysRented) {
			// TODO Auto-generated method stub
			return daysRented > 1 ? 2 : 1;
		}
	}
	class RegularPrice extends Price {
		@Override
		public int getPriceCode() {
			// TODO Auto-generated method stub
			return Movie.REGULAR;
		}
		@Override
		public double getCharge(int daysRented) {
			// TODO Auto-generated method stub
			double result = 2;
			if (daysRented > 2) {
				result += (daysRented - 2) * 1.5;
			}
			return result;
		}
	}
}

class Rental {
	private Movie _movie;
	private int _daysRented;
	public Rental(Movie movie, int daysRented) {
		_movie = movie;
		_daysRented = daysRented;
	}
	public Movie getMovie() {
		return _movie;
	}
	public void setMovie(Movie _movie) {
		this._movie = _movie;
	}
	public int getDaysRented() {
		return _daysRented;
	}
	public void setDaysRented(int _daysRented) {
		this._daysRented = _daysRented;
	}
	public double getCharge() {
		return _movie.getCharge(_daysRented);
	}
	//
	public int getFrequentRenterPoints() {
		return _movie.getFrequentRenterPoints(_daysRented);
	}
}

class Customer {
	private String _name;
	private Vector _rentals = new Vector();
	public Customer(String name) {
		_name = name;
	}
	public void addRental(Rental arg) {
		_rentals.addElement(arg);
	}
	public String getName() {
		return _name;
	}
	public String statement() {
		double totalAmount = 0;
		// int /*frequentRenterPoints*/ = 0;
		Enumeration rentals = _rentals.elements();
		String result = "Rental Record for " + getName() + "\n";
		while (rentals.hasMoreElements()) {
			Rental each = (Rental) rentals.nextElement();
			result += "\t" + each.getMovie().getTitle() + "\t" + String.valueOf(each.getCharge()) + "\n";
			totalAmount += each.getCharge();
		}
		result += "Amount owed is " + String.valueOf(getTotalCharge()) + "\n";
		result += "You earned " + String.valueOf(getTotalIFrequentRenterPoints()) + " frequent renter points";
		return result;
	}
	private double getTotalCharge() {
		double result = 0;
		Enumeration rentals = _rentals.elements();
		while (rentals.hasMoreElements()) {
			Rental each = (Rental) rentals.nextElement();
			result += each.getCharge();
		}
		return result;
	}
	private int getTotalIFrequentRenterPoints() {
		int result = 0;
		Enumeration rentals = _rentals.elements();
		while (rentals.hasMoreElements()) {
			Rental each = (Rental) rentals.nextElement();
			result += each.getFrequentRenterPoints();
		}
		return result;
	}
}
