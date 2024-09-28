import java.util.ArrayList;
import java.util.List;

public class MovieTheater {
    private final String theaterName;
    private List<Seat> seats = new ArrayList<>(); // Corrected initialization

    public MovieTheater(String theaterName, int numRows, int seatsPerRow) {
        this.theaterName = theaterName;
        int lastRow = 'A' + (numRows - 1);
        for (char row = 'A'; row <= lastRow; row++) {
            for (int seatNum = 1; seatNum <= seatsPerRow; seatNum++) {
                String seatNumber = row + String.format("%02d", seatNum);
                seats.add(new Seat(seatNumber));
            }
        }
        printSeatingChart();
    }

    // Method to get the theater name
    public String getTheaterName() {
        return theaterName;
    }

    // Method to reserve a seat
    public boolean reserveSeat(String seatNumber) {
        Seat requestedSeat = findSeat(seatNumber);
        if (requestedSeat != null && !requestedSeat.isReserved()) {
            requestedSeat.reserve();
            System.out.println("Seat " + seatNumber + " reserved successfully.");
            return true;
        } else {
            System.out.println("Seat " + seatNumber + " is either already reserved or not available.");
            return false;
        }
    }

    // Method to cancel a seat reservation
    public boolean cancelSeat(String seatNumber) {
        Seat requestedSeat = findSeat(seatNumber);
        if (requestedSeat != null && requestedSeat.isReserved()) {
            requestedSeat.cancel();
            System.out.println("Reservation for seat " + seatNumber + " canceled.");
            return true;
        } else {
            System.out.println("Seat " + seatNumber + " is not reserved.");
            return false;
        }
    }

    // Method to retrieve the seating chart
    public void printSeatingChart() {
        System.out.println("Seating chart for " + theaterName + ":");
        for (Seat seat : seats) {
            System.out.print(seat.getSeatNumber() + (seat.isReserved() ? " [R] " : " [A] "));
            if (seat.getSeatNumber().endsWith("10")) {
                System.out.println(); // Newline for every 10 seats
            }
        }
        System.out.println();
    }

    // Helper method to find a seat by seat number
    private Seat findSeat(String seatNumber) {
        for (Seat seat : seats) {
            if (seat.getSeatNumber().equals(seatNumber)) {
                return seat;
            }
        }
        return null;
    }

    // Inner class Seat
    private class Seat {
        private final String seatNumber;
        private boolean reserved = false;

        public Seat(String seatNumber) {
            this.seatNumber = seatNumber;
        }

        public boolean isReserved() {
            return reserved;
        }

        public String getSeatNumber() {
            return seatNumber;
        }

        public void reserve() {
            this.reserved = true;
        }

        public void cancel() {
            this.reserved = false;
        }
    }

    public static void main(String[] args) {
        MovieTheater theater = new MovieTheater("CinemaX", 5, 10);

        // Example usage
        theater.reserveSeat("A05");
        theater.reserveSeat("B10");
        theater.printSeatingChart();

        theater.cancelSeat("A05");
        theater.printSeatingChart();
    }
}
