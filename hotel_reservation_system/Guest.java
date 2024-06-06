package hotel_reservation_system;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 *
 * @author Mahmoud
 */
public class Guest {

    private String name, emailAddress, password, PhoneNumber, nationality, gender, passportNumber;
    private Date dateOfBirth;
    private static Connection conn = null;
    private static PreparedStatement pst = null;
    private static ResultSet res = null;

    public Guest() {
    }

    public Guest(String name, String emailAddress, String password, String passportNumber) {
        this.name = name;
        this.emailAddress = emailAddress;
        this.password = password;
        this.passportNumber = passportNumber;
    }

    public Guest(String name, String emailAddress, String password, String PhoneNumber, Date dateOfBirth, String nationality, String gender, String passportNumber) {
        this(name, emailAddress, password, passportNumber);
        this.PhoneNumber = PhoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.nationality = nationality;
        this.gender = gender;
    }

    public static boolean storeGuest(Guest g) {
        boolean stored = false;
        conn = dbConn.DBConnection();
        String sql = "Insert into GUESTS" + "(NAME,EMAIL_ADDRESS,PASSWORD,PHONE_NUMBER,DATE_OF_BIRTH,NATIONALITY,GENDER,PASSPORT_NUMBER) Values(?,?,?,?,?,?,?,?)";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, g.name);
            pst.setString(2, g.emailAddress);
            pst.setString(3, g.password);
            pst.setString(4, g.PhoneNumber);
            pst.setDate(5, g.dateOfBirth);
            pst.setString(6, g.nationality);
            pst.setString(7, g.gender);
            pst.setString(8, g.passportNumber);
            int i = pst.executeUpdate();
            if (i == 1) {
                stored = true;
                //System.out.println("Data is inserted");
            }
            pst.close();
            conn.close();
        } catch (NumberFormatException | SQLException ex) {
            System.out.println(ex.toString());
        }
        return stored;
    }

    public static Guest findGuest(String email, String password) {
        Guest g = new Guest();
        String sql = "select * from GUESTS WHERE EMAIL_ADDRESS=? AND PASSWORD=?";
        conn = dbConn.DBConnection();
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, email);
            pst.setString(2, password);
            res = pst.executeQuery();
            if (res.next()) {
                g.name = res.getString(1);
                g.emailAddress = res.getString(2);
                g.password = res.getString(3);
                g.PhoneNumber = res.getString(4);
                g.dateOfBirth = res.getDate(5);
                g.nationality = res.getString(6);
                g.gender = res.getString(7);
                g.passportNumber = res.getString(8);

            } else {
                return null;
            }
            pst.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        return g;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public boolean setEmailAddress(String emailAddress) {
        if (ValidEmailAddress(emailAddress)) {
            this.emailAddress = emailAddress;
            return true;
        }
        return false;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public boolean setPhoneNumber(String PhoneNumber) {
        if (ValidPhoneNumber(PhoneNumber)) {
            this.PhoneNumber = PhoneNumber;
            return true;
        }
        return false;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public boolean setDateOfBirth(String date) {
        try {
            this.dateOfBirth = java.sql.Date.valueOf(date);
            // Calculate age based on the date of birth
            LocalDate dob = this.dateOfBirth.toLocalDate();
            LocalDate today = LocalDate.now();
            Period age = Period.between(dob, today);

            // Check if age is greater than or equal to 16
            return age.getYears() >= 16; // Accepted
            // Not accepted
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public boolean setPassword(String password) {
        if (validPassword(password)) {
            this.password = password;
            return true;
        }
        return false;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Name=" + name + ", Email Address=" + emailAddress + ", Phone Number=" + PhoneNumber + ", Date Of Birth=" + dateOfBirth + ", Nationality=" + nationality + ", Gender=" + gender + ", Passport Number=" + passportNumber;
    }

    public static boolean validPassword(String password) {
        boolean hasLetter = false;
        boolean hasDigit = false;
        for (char c : password.toCharArray()) {
            if (Character.isLetter(c)) {
                hasLetter = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            }
            if (password.length() >= 5 && hasLetter && hasDigit) {
                return true;
            }
        }
        return false;
    }

    public static boolean ValidPhoneNumber(String number) {
        // Regular expression for a phone number with country code and ten digits
        String regex = "^(\\+\\d{1,3}( )?)?\\d{10}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(number);

        return matcher.matches();
    }

    public static boolean ValidEmailAddress(String email) {
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
            return true;
        } catch (AddressException ex) {
            return false;
        }
    }

}
