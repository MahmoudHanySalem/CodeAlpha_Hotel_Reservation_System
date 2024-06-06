package hotel_reservation_system;

import java.sql.SQLException;

/**
 *
 * @author Mahmoud
 */
public class StandardRoom extends Room {

    protected int capacity;

    public StandardRoom() {
    }

    public StandardRoom(double price, int capacity) {
        super(price);
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return super.toString() + ", " + "capacity=" + capacity;
    }

    public boolean storeRoom() {
        boolean stored = false;
        conn = dbConn.DBConnection();
        String sql = "Insert into ROOMES" + "(ROOM_NUMBER,ROOM_TYPE,REDESIGNING,PRICE,CAPACITY) Values(?,?,?,?,?)";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, roomNumber);
            pst.setString(2, "Standard");
            pst.setInt(3, 0);
            pst.setDouble(4, price);
            pst.setInt(5, capacity);
            int i = pst.executeUpdate();
            if (i == 1) {
                stored = true;
            }
            pst.close();
            conn.close();
        } catch (NumberFormatException | SQLException ex) {
            System.out.println(ex.toString());
        }
        return stored;
    }

}
