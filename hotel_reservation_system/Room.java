package hotel_reservation_system;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Mahmoud
 */
public class Room {

    protected String roomNumber;
    protected double price;
    protected boolean underMaintenance;
    static int roomCounter = 1;
    protected static Connection conn = null;
    protected static PreparedStatement pst = null;
    protected static PreparedStatement pst1 = null;
    protected static ResultSet res = null;

    public Room() {
    }

    public Room(double price) {
        this.roomNumber = roomCounter + "";
        this.price = price;
        this.underMaintenance = false;
        roomCounter++;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean getUnderMaintenance() {
        return underMaintenance;
    }

    public void setUnderMaintenance(boolean underMaintenance) {
        this.underMaintenance = underMaintenance;
    }

    @Override
    public String toString() {
        return "roomNumber=" + roomNumber + ", price=" + price;
    }

    public static ArrayList findRoom(String type, int capacity) {
        ArrayList<Room> arr = new ArrayList();
        switch (type) {
            case "Standard" -> {
                String sql = "select * from ROOMES WHERE UNDER_MAINTENANCE=? AND ROOM_TYPE=? AND CAPACITY=?";
                conn = dbConn.DBConnection();
                try {
                    pst = conn.prepareStatement(sql);
                    pst.setInt(1, 0);
                    pst.setString(2, type);
                    pst.setInt(3, capacity);
                    res = pst.executeQuery();
                    while (res.next()) {
                        StandardRoom r = new StandardRoom();
                        r.roomNumber = res.getString(1);
                        r.underMaintenance = res.getInt(3) != 0;
                        r.capacity = res.getInt(5);
                        r.price = res.getDouble(4);
                        arr.add(r);
                    }
                    pst.close();
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println(ex.toString());
                }
                return arr;
            }
            case "Deluxe" -> {
                String sql = "select * from ROOMES WHERE UNDER_MAINTENANCE=? AND ROOM_TYPE=? AND CAPACITY=?";
                conn = dbConn.DBConnection();
                try {
                    pst = conn.prepareStatement(sql);
                    pst.setInt(1, 0);
                    pst.setString(2, type);
                    pst.setInt(3, capacity);
                    res = pst.executeQuery();
                    while (res.next()) {
                        DeluxeRoom r = new DeluxeRoom();
                        r.roomNumber = res.getString(1);
                        r.underMaintenance = res.getInt(3) != 0;
                        r.capacity = res.getInt(5);
                        r.price = res.getDouble(4);
                        r.hasJacuzzi = res.getInt(6) != 0;
                        arr.add(r);
                    }
                    pst.close();
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println(ex.toString());
                }
                return arr;
            }
            case "Suite" -> {
                String sql = "select * from ROOMES WHERE UNDER_MAINTENANCE=? AND ROOM_TYPE=? AND CAPACITY=?";
                conn = dbConn.DBConnection();
                try {
                    pst = conn.prepareStatement(sql);
                    pst.setInt(1, 0);
                    pst.setString(2, type);
                    pst.setInt(3, capacity);
                    res = pst.executeQuery();
                    while (res.next()) {
                        Suite r = new Suite();
                        r.roomNumber = res.getString(1);
                        r.underMaintenance = res.getInt(3) != 0;
                        r.capacity = res.getInt(5);
                        r.price = res.getDouble(4);
                        r.hasJacuzzi = res.getInt(6) != 0;
                        r.suiteType = res.getString(7);
                        r.bedConfiguration = res.getString(8);
                        arr.add(r);
                    }
                    pst.close();
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println(ex.toString());
                }
                return arr;
            }
            default -> {
            }
        }
        return null;
    }

    public static ArrayList findRoom(String type) {
        ArrayList<Room> arr = new ArrayList();
        switch (type) {
            case "Standard" -> {
                String sql = "select * from ROOMES WHERE UNDER_MAINTENANCE=? AND ROOM_TYPE=?";
                conn = dbConn.DBConnection();
                try {
                    pst = conn.prepareStatement(sql);
                    pst.setInt(1, 0);
                    pst.setString(2, type);
                    res = pst.executeQuery();
                    while (res.next()) {
                        StandardRoom r = new StandardRoom();
                        r.roomNumber = res.getString(1);
                        r.underMaintenance = res.getInt(3) != 0;
                        r.capacity = res.getInt(5);
                        r.price = res.getDouble(4);
                        arr.add(r);
                    }
                    pst.close();
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println(ex.toString());
                }
                return arr;
            }
            case "Deluxe" -> {
                String sql = "select * from ROOMES WHERE UNDER_MAINTENANCE=? AND ROOM_TYPE=?";
                conn = dbConn.DBConnection();
                try {
                    pst = conn.prepareStatement(sql);
                    pst.setInt(1, 0);
                    pst.setString(2, type);
                    res = pst.executeQuery();
                    while (res.next()) {
                        DeluxeRoom r = new DeluxeRoom();
                        r.roomNumber = res.getString(1);
                        r.underMaintenance = res.getInt(3) != 0;
                        r.capacity = res.getInt(5);
                        r.price = res.getDouble(4);
                        r.hasJacuzzi = res.getInt(6) != 0;
                        arr.add(r);
                    }
                    pst.close();
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println(ex.toString());
                }
                return arr;
            }
            case "Suite" -> {
                String sql = "select * from ROOMES WHERE UNDER_MAINTENANCE=? AND ROOM_TYPE=?";
                conn = dbConn.DBConnection();
                try {
                    pst = conn.prepareStatement(sql);
                    pst.setInt(1, 0);
                    pst.setString(2, type);
                    res = pst.executeQuery();
                    while (res.next()) {
                        Suite r = new Suite();
                        r.roomNumber = res.getString(1);
                        r.underMaintenance = res.getInt(3) != 0;
                        r.capacity = res.getInt(5);
                        r.price = res.getDouble(4);
                        r.hasJacuzzi = res.getInt(6) != 0;
                        r.suiteType = res.getString(7);
                        r.bedConfiguration = res.getString(8);
                        arr.add(r);
                    }
                    pst.close();
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println(ex.toString());
                }
                return arr;
            }
            default -> {
            }
        }
        return null;
    }

    public static ArrayList findRoom(int capacity) {
        ArrayList<Suite> arr = new ArrayList();
        String sql = "select * from ROOMES WHERE UNDER_MAINTENANCE=? AND CAPACITY=?";
        conn = dbConn.DBConnection();
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, capacity);
            res = pst.executeQuery();
            while (res.next()) {
                Suite r = new Suite();
                r.roomNumber = res.getString(1);
                r.underMaintenance = res.getInt(3) != 0;
                r.capacity = res.getInt(5);
                r.price = res.getDouble(4);
                r.hasJacuzzi = res.getInt(6) != 0;
                r.suiteType = res.getString(7);
                r.bedConfiguration = res.getString(8);
                arr.add(r);
            }
            pst.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        return arr;
    }

    public static boolean isRoomAvailable(String roomNumber, LocalDate date) {
        String sql = "SELECT * FROM RESERVATIONS WHERE ROOM_NUMBER = ? AND DATE_OCCUPIED = ?";
        conn = dbConn.DBConnection();
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, roomNumber);
            pst.setDate(2, Date.valueOf(date));
            res = pst.executeQuery();
            return !res.next(); // If no result, room is available
        } catch (SQLException e) {
            return false;
        }
    }

    public static void bookRoom(String roomNumber, LocalDate date, String email, double price) {
        String sql = "INSERT INTO RESERVATIONS (ROOM_NUMBER, DATE_OCCUPIED,GUEST_EMAIL_ADDRESS,PRICE,PAID) VALUES (?,?,?,?,?)";
        conn = dbConn.DBConnection();
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, roomNumber);
            pst.setDate(2, Date.valueOf(date));
            pst.setString(3, email);
            pst.setDouble(4, price);
            pst.setInt(5, 0);
            pst.executeUpdate();
            System.out.println("Room " + roomNumber + " booked for " + date);
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    public boolean deleteRoom(String roomNumber) {
        boolean deleted = false;
        String sql = "Delete from ROOMES where ROOM_NUMBER = ?";
        conn = dbConn.DBConnection();
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, roomNumber);
            pst.executeUpdate();
            pst.close();
            conn.close();
            deleted = true;
        } catch (NumberFormatException | SQLException ex) {
            System.out.println(ex.toString());
        }
        return deleted;
    }

    public static boolean deleteReservation(String email, LocalDate date) {
        boolean deleted = false;
        String sql = "Delete from RESERVATIONS where DATE_OCCUPIED = ? AND GUEST_EMAIL_ADDRESS=?";
        conn = dbConn.DBConnection();
        try {
            pst = conn.prepareStatement(sql);
            pst.setDate(1, Date.valueOf(date));
            pst.setString(2, email);
            int rowsAffected = pst.executeUpdate();
            pst.close();
            conn.close();
            if (rowsAffected > 0) {
                deleted = true;
            }
        } catch (NumberFormatException | SQLException ex) {
            System.out.println(ex.toString());
        }
        return deleted;
    }

    public static void findReservation(String email) {
        String sql = "SELECT * FROM RESERVATIONS WHERE GUEST_EMAIL_ADDRESS = ?";
        conn = dbConn.DBConnection();
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, email);
            res = pst.executeQuery();
            while (res.next()) {
                System.out.print("Reservation details: Room Number : " + res.getString(1) + ", Date Occupied : " + res.getDate(2) + ", Guest email address : " + res.getString(3) + ", Price : " + res.getString(4) + ", Paid : " + (res.getInt(5) != 0));
                System.out.println("");
            }
        } catch (SQLException e) {

        }
    }
}
