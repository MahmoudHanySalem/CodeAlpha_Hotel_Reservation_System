package hotel_reservation_system;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Mahmoud
 */
public class Hotel_Reservation_System {

    public static void main(String[] args) {
// adding roomes to the data base
//        StandardRoom standardRoom1 = new StandardRoom(100, 2);
//        StandardRoom standardRoom2 = new StandardRoom(200, 4);
//        StandardRoom standardRoom3 = new StandardRoom(200, 4);
//        StandardRoom standardRoom4 = new StandardRoom(300, 6);
//        StandardRoom standardRoom5 = new StandardRoom(100, 2);
//        DeluxeRoom deluxeRoom1 = new DeluxeRoom(250, 4, true);
//        DeluxeRoom deluxeRoom2 = new DeluxeRoom(250, 4, true);
//        DeluxeRoom deluxeRoom3 = new DeluxeRoom(400, 6, true);
//        Suite suite1 = new Suite(1000, 8, true, "Presidential Suite","king-size");
//        standardRoom1.storeRoom();standardRoom2.storeRoom();standardRoom3.storeRoom();standardRoom4.storeRoom();standardRoom5.storeRoom();
//        deluxeRoom1.storeRoom();deluxeRoom2.storeRoom();deluxeRoom3.storeRoom();
//        suite1.storeRoom();
        Scanner input = new Scanner(System.in);
        boolean loged = false;
        Guest g = null;
        while (loged == false) {
            System.out.println("Welcome to Mahmoud's hotel\n(0)Exit\n(1)sign in\n(2)sign up");//<<<
            boolean validChoice = true;
            int logOrSighn = -1;
            while (validChoice) {
                System.out.println("Enter choice");
                try {
                    logOrSighn = input.nextInt();
                    validChoice = false;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid value<<must be an integer>>");
                    // Clear the invalid input from the scanner buffer
                    input.nextLine();
                }
            }
            switch (logOrSighn) {
                case 0 -> {
                    return;
                }
                case 1 -> {
                    boolean loggedIn = false;
                    // Authentication loop
                    while (loggedIn == false) {
                        System.out.print("Enter email:");// email=mahmoud.bj795@gmail.com<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
                        String name = input.next();
                        System.out.print("Enter password:");//password=123<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
                        String password = input.next();
                        g = Guest.findGuest(name, password);
                        if (g != null) {
                            loggedIn = true;
                            System.out.println("signed in Successfully");
                            loged = true;
                        } else {
                            System.out.println("Authentication failed. Please try again.");
                        }
                    }
                }
                case 2 -> {
                    g = new Guest();
                    boolean valid = false;
                    System.out.println("Enter Name:");
                    input.nextLine();
                    g.setName(input.nextLine());
                    while (valid == false) {
                        System.out.println("Enter Email Address:");
                        valid = g.setEmailAddress(input.next());
                        if (valid == false) {
                            System.out.println("Invalid email");
                        }
                    }
                    valid = false;
                    while (valid == false) {
                        System.out.println("Enter password(5 or more characters or digits,at least one character and one litter):");
                        valid = g.setPassword(input.next());
                        if (valid == false) {
                            System.out.println("Invalid password");
                        }
                    }
                    System.out.println("Enter Passport Number:");
                    g.setPassportNumber(input.next());
                    boolean c = false;
                    while (c == false) {
                        System.out.println("Want to add aditional data(yes/no)? ex.Phone Number,Date Of Birth");
                        String Continue = input.next();
                        if (Continue.equalsIgnoreCase("No")) {
                            Guest.storeGuest(g);
                            System.out.println("signed up Successfully");
                            loged = true;
                            c = true;
                        } else if (Continue.equalsIgnoreCase("yes")) {
                            valid = false;
                            while (valid == false) {
                                System.out.println("Enter Phone Number(+country code then 10 digits):");
                                valid = g.setPhoneNumber(input.next());
                                if (valid == false) {
                                    System.out.println("Invalid phone number");
                                }
                            }
                            valid = false;
                            while (valid == false) {
                                System.out.println("Enter Date Of Birth(in this format YYYY-MM-DD <only 16 and older allowed>):");
                                valid = g.setDateOfBirth(input.next());
                                if (valid == false) {
                                    System.out.println("Invalid date");
                                }
                            }
                            System.out.println("Enter Nationality:");
                            g.setNationality(input.next());
                            System.out.println("Enter gender:");
                            g.setGender(input.next());
                            System.out.println(g);
                            boolean confirm = false;
                            while (confirm == false) {
                                System.out.println("Confirm?(Yes/No)");
                                String confirmation = input.next();
                                if (confirmation.equalsIgnoreCase("YES")) {
                                    Guest.storeGuest(g);
                                    System.out.println("signed up Successfully");
                                    loged = true;
                                    confirm = true;
                                } else if (confirmation.equalsIgnoreCase("No")) {
                                    System.out.println("Canceled");
                                    confirm = true;
                                } else {
                                    System.out.println("invalid choice");
                                }
                            }
                            c = true;
                        } else {
                            System.out.println("invalid choice");
                        }
                    }
                }
            }
        }
        boolean loop = true;
        while (loop) {
            System.out.println("(1)search for available roomes\n(2)book a room\n(3)cancel a book\n(4)view book details\n(0)Exit");
            boolean validChoice = true;
            int choice = -1;
            while (validChoice) {
                System.out.println("Enter choice");
                try {
                    choice = input.nextInt();
                    validChoice = false;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid value<<must be an integer>>");
                    // Clear the invalid input from the scanner buffer
                    input.nextLine();
                }
            }
            switch (choice) {
                case 1 -> {
                    boolean sloop = true;
                    while (sloop) {
                        boolean validType = false;
                        String type = "";
                        while (validType == false) {
                            System.out.println("Enter room type(Standard,Deluxe or Suite)");
                            type = input.next();
                            if (type.equals("Standard") || type.equals("Deluxe") || type.equals("Suite")) {
                                validType = true;
                            } else {
                                System.out.println("Invalid room type");
                            }
                        }
                        boolean validnum = true;
                        int capacity = -1;
                        while (validnum) {
                            System.out.println("Enter number of guestes");
                            try {
                                capacity = input.nextInt();
                                validnum = false;
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid value<<must be an integer>>");
                                // Clear the invalid input from the scanner buffer
                                input.nextLine();
                            }
                        }
                        ArrayList<Room> ruselt = Room.findRoom(type, capacity);
                        if (ruselt.isEmpty()) {
                            System.out.println("No room with this type and capacity");
                            System.out.println("List of roomes with the type " + type + " :");
                            ArrayList<Room> r = Room.findRoom(type);
                            if (r.isEmpty()) {
                                System.out.println("No roomes with this type");
                            } else {
                                for (Room room : r) {
                                    System.out.println(room);
                                }
                            }
                            System.out.println("List of roomes with the capacity " + capacity + " :");
                            r = Room.findRoom(capacity);
                            if (r.isEmpty()) {
                                System.out.println("No roomes with this capacity");
                            } else {
                                for (Room room : r) {
                                    System.out.println(room);
                                }
                            }
                            System.out.println("(0)Back\n(1)Search with a different type or/and capacity");
                            boolean validChoice2 = true;
                            int choice2 = -1;
                            while (validChoice2) {
                                System.out.println("Enter choice");
                                try {
                                    choice2 = input.nextInt();
                                    validChoice2 = false;
                                } catch (InputMismatchException e) {
                                    System.out.println("Invalid value<<must be an integer>>");
                                    // Clear the invalid input from the scanner buffer
                                    input.nextLine();
                                }
                            }
                            if (choice2 == 0) {
                                sloop = false;
                            }
                        } else {
                            for (Room room : ruselt) {
                                System.out.println(room);
                            }
                            sloop = false;
                        }
                    }
                }

                case 2 -> {
                    boolean sloop = true;
                    ArrayList<Room> ruselt = null;
                    Room croom = null;
                    while (sloop) {
                        boolean validType = false;
                        String type = "";
                        while (validType == false) {
                            System.out.println("Enter room type(Standard,Deluxe or Suite)");
                            type = input.next();
                            if (type.equals("Standard") || type.equals("Deluxe") || type.equals("Suite")) {
                                validType = true;
                            } else {
                                System.out.println("Invalid room type");
                            }
                        }
                        boolean validnum = true;
                        int capacity = -1;
                        while (validnum) {
                            System.out.println("Enter number of guestes");
                            try {
                                capacity = input.nextInt();
                                validnum = false;
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid value<<must be an integer>>");
                                // Clear the invalid input from the scanner buffer
                                input.nextLine();
                            }
                        }
                        ruselt = Room.findRoom(type, capacity);
                        if (ruselt.isEmpty()) {
                            System.out.println("No room with this type and capacity");
                            System.out.println("List of roomes with the type " + type + " :");
                            ArrayList<Room> r = Room.findRoom(type);
                            if (r.isEmpty()) {
                                System.out.println("No roomes with this type");
                            } else {
                                for (Room room : r) {
                                    System.out.println(room);
                                }
                            }
                            System.out.println("List of roomes with the capacity " + capacity + " :");
                            r = Room.findRoom(capacity);
                            if (r.isEmpty()) {
                                System.out.println("No roomes with this capacity");
                            } else {
                                for (Room room : r) {
                                    System.out.println(room);
                                }
                            }
                            System.out.println("(0)Back\n(1)Search with a different type or/and capacity");
                            boolean validChoice2 = true;
                            int choice2 = -1;
                            while (validChoice2) {
                                System.out.println("Enter choice");
                                try {
                                    choice2 = input.nextInt();
                                    validChoice2 = false;
                                } catch (InputMismatchException e) {
                                    System.out.println("Invalid value<<must be an integer>>");
                                    // Clear the invalid input from the scanner buffer
                                    input.nextLine();
                                }
                            }
                            if (choice2 == 0) {
                                sloop = false;
                            }
                        } else {
                            for (Room room : ruselt) {
                                System.out.println(room);
                            }
                            sloop = false;
                        }
                    }/////<<<<<<<<<<<<<<<<<<<
                    System.out.println("Enter the number of the room you wont");
                    String roomNuber = input.next();
                    for (Room current : ruselt) {
                        if (current.roomNumber.equals(roomNuber)) {
                            croom = current;
                            break;
                        }
                    }
                    System.out.println("Enter Check-in and Check-out date to see if the room will be available or not");
                    boolean dateLoop = false;
                    while (dateLoop == false) {
                        boolean validin = false;
                        LocalDate CheckInDate = LocalDate.parse(("0000-01-01"));
                        while (validin == false) {
                            System.out.println("Enter Check-in date in this format(YYYY-MM-DD)");
                            try {
                                CheckInDate = LocalDate.parse(input.next());
                                validin = true;
                                // Not accepted
                            } catch (DateTimeParseException e) {
                                System.out.println("Invalid date enter in this format(YYYY-MM-DD)");
                            }
                        }
                        boolean validout = false;
                        LocalDate CheckOutDate = LocalDate.parse(("0000-01-01"));
                        while (validout == false) {
                            System.out.println("Enter Check-out date enter in this format(YYYY-MM-DD)");
                            try {
                                CheckOutDate = LocalDate.parse(input.next());
                                validout = true;
                                // Not accepted
                            } catch (DateTimeParseException e) {
                                System.out.println("Invalid date");
                            }
                        }
                        ArrayList<LocalDate> bookedDates = new ArrayList<>();
                        LocalDate currentDate = CheckInDate;
                        while (!currentDate.isAfter(CheckOutDate)) {
                            bookedDates.add(currentDate);
                            currentDate = currentDate.plusDays(1);
                        }
                        boolean notcomplite = false;
                        for (LocalDate date : bookedDates) {
                            if (!Room.isRoomAvailable(roomNuber, date)) {
                                System.out.println("Room not empty at " + date);
                                notcomplite = true;
                            }
                        }
                        if (notcomplite) {
                            System.out.println("(1)Try a different date\n(2)Try a different room\n(0)Exit ");
                            boolean validChoice3 = true;
                            int choice3 = -1;
                            while (validChoice3) {
                                System.out.println("Enter choice");
                                try {
                                    choice3 = input.nextInt();
                                    validChoice3 = false;
                                } catch (InputMismatchException e) {
                                    System.out.println("Invalid value<<must be an integer>>");
                                    input.nextLine();
                                }
                            }
                            boolean valid = false;
                            while (valid == false) {
                                switch (choice3) {
                                    case 1 -> {
                                        valid = true;
                                        break;
                                    }
                                    case 2 -> {
                                        dateLoop = true;
                                        valid = true;
                                        break;
                                    }
                                    case 0 -> {
                                        return;
                                    }
                                }
                            }
                        } else {
                            for (LocalDate date : bookedDates) {
                                Room.bookRoom(roomNuber, date, g.getEmailAddress(), croom.price);
                            }
                            dateLoop = true;
                            loop = false;
                        }
                    }
                }
                case 3 -> {
                    System.out.println("Enter Check-in and Check-out date fot the Reservation");
                    boolean validin = false;
                    LocalDate CheckInDate = LocalDate.parse(("0000-01-01"));
                    while (validin == false) {
                        System.out.println("Enter Check-in date in this format(YYYY-MM-DD)");
                        try {
                            CheckInDate = LocalDate.parse(input.next());
                            validin = true;
                            // Not accepted
                        } catch (DateTimeParseException e) {
                            System.out.println("Invalid date enter in this format(YYYY-MM-DD)");
                        }
                    }
                    boolean validout = false;
                    LocalDate CheckOutDate = LocalDate.parse(("0000-01-01"));
                    while (validout == false) {
                        System.out.println("Enter Check-out date enter in this format(YYYY-MM-DD)");
                        try {
                            CheckOutDate = LocalDate.parse(input.next());
                            validout = true;
                            // Not accepted
                        } catch (DateTimeParseException e) {
                            System.out.println("Invalid date");
                        }
                    }
                    ArrayList<LocalDate> bookedDates = new ArrayList<>();
                    LocalDate currentDate = CheckInDate;
                    while (!currentDate.isAfter(CheckOutDate)) {
                        bookedDates.add(currentDate);
                        currentDate = currentDate.plusDays(1);
                    }
                    for (LocalDate date : bookedDates) {
                        if (Room.deleteReservation(g.getEmailAddress(), date)) {
                            System.out.println("Reservation at " + date + " deleted");
                        } else {
                            System.out.println("No Reservation found");
                        }
                    }
                    loop = false;
                }
                case 4 -> {
                    Room.findReservation(g.getEmailAddress());
                    loop = false;
                }
                case 0 -> {
                    return;
                }
                default ->
                    System.out.println("Invalid choice");
            }
        }
    }
}
