package hotel_reservation_system;

import java.sql.SQLException;

/**
 *
 * @author Mahmoud
 */
public class DeluxeRoom extends StandardRoom {

    protected boolean hasJacuzzi;

    public DeluxeRoom() {
    }

    public DeluxeRoom(double price, int capacity, boolean hasJacuzzi) {
        super(price, capacity);
        this.hasJacuzzi = hasJacuzzi;
    }

    public boolean isHasJacuzzi() {
        return hasJacuzzi;
    }

    public void setHasJacuzzi(boolean hasJacuzzi) {
        this.hasJacuzzi = hasJacuzzi;
    }

    @Override
    public String toString() {
        return super.toString() + ", " + "hasJacuzzi=" + hasJacuzzi;
    }

    public boolean storeRoom() {
        boolean stored = false;
        conn = dbConn.DBConnection();
        String sql = "Insert into ROOMES" + "(ROOM_NUMBER,ROOM_TYPE,REDESIGNING,PRICE,CAPACITY,HAS_JACUZZI) Values(?,?,?,?,?,?)";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, roomNumber);
            pst.setString(2, "Deluxe");
            pst.setInt(3, 0);
            pst.setDouble(4, price);
            pst.setInt(5, capacity);
            pst.setInt(6, hasJacuzzi ? 1 : 0);
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
