import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BookingService {

    private List<Train> trainList = new ArrayList<>();
    private List<Ticket> ticketList = new ArrayList<>();

    public BookingService() {
        trainList.add(new Train(101,"Rajdhani Express","Delhi","Nagpur",100));
        trainList.add(new Train(102,"Shatabdi Express","Delhi","Mumbai",60));
        trainList.add(new Train(103,"Duronto Express","Agra","Delhi",70));
    }

    public List<Train> searchTrain(String source, String destination) {
        List<Train> result = new ArrayList<>();

        for (Train train : trainList) {
            if (train.getSource().equalsIgnoreCase(source)
                    && train.getDestination().equalsIgnoreCase(destination)) {
                result.add(train);
            }
        }
        return result;
    }

    public Ticket bookTicket(User user, int trainId, int seats) {

        for (Train train : trainList) {
            if (train.getTrainId() == trainId) {

                if (train.bookSeats(seats)) {
                    Ticket ticket = new Ticket(user, train, seats);
                    ticketList.add(ticket);
                    return ticket;
                } else {
                    System.out.println("Not enough seats");
                    return null;
                }
            }
        }

        System.out.println("Train not found");
        return null;
    }

    public List<Ticket> getTicketByUser(User user) {

        List<Ticket> result = new ArrayList<>();

        for (Ticket ticket : ticketList) {
            if (ticket.getUser().getUsername().equalsIgnoreCase(user.getUsername())) {
                result.add(ticket);
            }
        }

        return result;
    }

    public boolean cancelTicket(int ticketId, User user) {

        Iterator<Ticket> it = ticketList.iterator();

        while (it.hasNext()) {
            Ticket t = it.next();

            if (t.getTicketId() == ticketId &&
                    t.getUser().getUsername().equalsIgnoreCase(user.getUsername())) {

                t.getTrain().cancelSeats(t.getSeatBooked());
                it.remove();
                System.out.println("Ticket cancelled");
                return true;
            }
        }

        System.out.println("Ticket not found");
        return false;
    }

    public void listAllTrains() {
        for (Train t : trainList) {
            System.out.println(t);
        }
    }
}