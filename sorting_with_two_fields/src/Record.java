public class Record {

    private int id;
    private String movie;
    private int week;
    private char rowID;
    private int seat;
    private double price;
    private char customerType;

    public Record(int id, String movie, int week, char rowID, int seat, double price, char customerType) {
        this.id = id;
        this.movie = movie;
        this.week = week;
        this.rowID = rowID;
        this.seat = seat;
        this.price = price;
        this.customerType = customerType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public char getRowID() {
        return rowID;
    }

    public void setRowID(char rowID) {
        this.rowID = rowID;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public char getCustomerType() {
        return customerType;
    }

    public void setCustomerType(char customerType) {
        this.customerType = customerType;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ID: ").append(id).append(", ");
        stringBuilder.append("Movie: ").append(movie).append(", ");
        stringBuilder.append("Week: ").append(week).append(", ");
        stringBuilder.append("Row: ").append(rowID).append(", ");
        stringBuilder.append("Seat: ").append(seat).append(", ");
        stringBuilder.append("Price: ").append(price).append(", ");
        stringBuilder.append("Customer Type: ").append(customerType);
        return stringBuilder.toString();
    }
}