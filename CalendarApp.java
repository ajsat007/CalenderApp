import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CalendarApp {
    private static final Map<Date, String> events = new HashMap<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("1. View Calendar");
            System.out.println("2. Add Event");
            System.out.println("3. Update Event");
            System.out.println("4. Delete Event");
            System.out.println("5. Exit");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    viewCalendar();
                    break;
                case 2:
                    addEvent();
                    break;
                case 3:
                    updateEvent();
                    break;
                case 4:
                    deleteEvent();
                    break;
                case 5:
                    System.out.println("Exiting Calendar App. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void viewCalendar() {
        if (events.isEmpty()) {
            System.out.println("No events in the calendar.");
        } else {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            for (Map.Entry<Date, String> entry : events.entrySet()) {
                System.out.println(dateFormat.format(entry.getKey()) + ": " + entry.getValue());
            }
        }
    }

    private static void addEvent() {
        System.out.print("Enter date (yyyy-MM-dd): ");
        String dateString = scanner.nextLine();

        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
            System.out.print("Enter event description: ");
            String description = scanner.nextLine();
            events.put(date, description);
            System.out.println("Event added successfully!");
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd.");
        }
    }

    private static void updateEvent() {
        viewCalendar();

        if (events.isEmpty()) {
            System.out.println("No events to update.");
            return;
        }

        System.out.print("Enter date of the event to update (yyyy-MM-dd): ");
        String dateString = scanner.nextLine();

        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
            if (events.containsKey(date)) {
                System.out.print("Enter new event description: ");
                String newDescription = scanner.nextLine();
                events.put(date, newDescription);
                System.out.println("Event updated successfully!");
            } else {
                System.out.println("No event found for the given date.");
            }
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd.");
        }
    }

    private static void deleteEvent() {
        viewCalendar();

        if (events.isEmpty()) {
            System.out.println("No events to delete.");
            return;
        }

        System.out.print("Enter date of the event to delete (yyyy-MM-dd): ");
        String dateString = scanner.nextLine();

        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
            if (events.containsKey(date)) {
                events.remove(date);
                System.out.println("Event deleted successfully!");
            } else {
                System.out.println("No event found for the given date.");
            }
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd.");
        }
    }
}
