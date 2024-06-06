package hotel_reservation_system;

import java.sql.SQLException;

/**
 *
 * @author Mahmoud
 */
public class Suite extends DeluxeRoom {

    protected String suiteType;
    protected String bedConfiguration;

    public Suite() {
    }

    public Suite(double price, int capacity, boolean hasJacuzzi, String suiteType, String bedConfiguration) {
        super(price, capacity, hasJacuzzi);
        this.suiteType = suiteType;
        this.bedConfiguration = bedConfiguration;
    }

    public void setSuiteType(String suiteType) {
        this.suiteType = suiteType;
    }

    public String getSuiteType() {
        return suiteType;
    }

    public String getBedConfiguration() {
        return bedConfiguration;
    }

    public void setBedConfiguration(String bedConfiguration) {
        this.bedConfiguration = bedConfiguration;
    }

    @Override
    public String toString() {
        return super.toString() + ", " + "suiteType=" + suiteType + ", bedConfiguration=" + bedConfiguration;
    }

    public boolean storeRoom() {
        boolean stored = false;
        conn = dbConn.DBConnection();
        String sql = "Insert into ROOMES" + "(ROOM_NUMBER,ROOM_TYPE,REDESIGNING,PRICE,CAPACITY,HAS_JACUZZI,SUITETYPE,BEDCONFIGURATION) Values(?,?,?,?,?,?,?,?)";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, roomNumber);
            pst.setString(2, "Suite");
            pst.setInt(3, 0);
            pst.setDouble(4, price);
            pst.setInt(5, capacity);
            pst.setInt(6, hasJacuzzi ? 1 : 0);
            pst.setString(7, suiteType);
            pst.setString(8, bedConfiguration);
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
