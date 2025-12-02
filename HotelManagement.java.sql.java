package dbms;

import java.sql.*;
import java.util.Scanner;

public class HotelManagement {

    private Connection connection;
    private Scanner scanner;

    
    public HotelManagement() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "anirudh");
        scanner = new Scanner(System.in);
    }

    public void createTables() throws SQLException {
        try {
            Statement stmt = connection.createStatement();

            stmt.executeUpdate("CREATE TABLE staff (staff_id INT PRIMARY KEY, staff_name VARCHAR(20))");
            System.out.println("Staff table successfully created");

            stmt.executeUpdate("CREATE TABLE guests (guest_id INT PRIMARY KEY, guest_name VARCHAR(20))");
            System.out.println("Guest table successfully created");

            stmt.executeUpdate("CREATE TABLE rooms (room_id INT PRIMARY KEY, room_type VARCHAR(20), price INT)");
            System.out.println("Room table successfully created");

            stmt.executeUpdate("CREATE TABLE bookings (booking_id INT PRIMARY KEY, staff_id INT, guest_id INT, room_id INT, " +
                    "FOREIGN KEY (staff_id) REFERENCES staff(staff_id) ON DELETE CASCADE, " +
                    "FOREIGN KEY (guest_id) REFERENCES guests(guest_id) ON DELETE CASCADE, " +
                    "FOREIGN KEY (room_id) REFERENCES rooms(room_id) ON DELETE CASCADE)");
            System.out.println("Booking table successfully created");
        } catch (SQLException e) {
            System.out.println("Tables already exist  " );
        }
    }

    public void insertRecords() throws SQLException {
        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Insert into staff");
            System.out.println("2. Insert into guest");
            System.out.println("3. Insert into room");
            System.out.println("4. Insert booking");
            System.out.println("5. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 5) {
                break;
            }

            switch (choice) {
                case 1:
                    try {
                        System.out.print("Enter staff id: ");
                        int staffId = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter staff name: ");
                        String staffName = scanner.nextLine();

                        String insertStaff = "INSERT INTO staff (staff_id, staff_name) VALUES (?, ?)";
                        PreparedStatement pstmtStaff = connection.prepareStatement(insertStaff);
                        pstmtStaff.setInt(1, staffId);
                        pstmtStaff.setString(2, staffName);
                        pstmtStaff.executeUpdate();
                        System.out.println("Inserted values into staff table");
                    } catch (SQLException e) {
                        System.out.println("Cannot insert into the table: " + e.getMessage());
                    }
                    break;

                case 2:
                    try {
                        System.out.print("Enter guest id: ");
                        int guestId = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter guest name: ");
                        String guestName = scanner.nextLine();

                        String insertGuest = "INSERT INTO guests (guest_id, guest_name) VALUES (?, ?)";
                        PreparedStatement pstmtGuest = connection.prepareStatement(insertGuest);
                        pstmtGuest.setInt(1, guestId);
                        pstmtGuest.setString(2, guestName);
                        pstmtGuest.executeUpdate();
                        System.out.println("Inserted values into guest table");
                    } catch (SQLException e) {
                        System.out.println("Cannot insert into the table: " );
                    }
                    break;

                case 3:
                    try {
                        System.out.print("Enter room id: ");
                        int roomId = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter room type: ");
                        String roomType = scanner.nextLine();
                        System.out.print("Enter room price: ");
                        int price = scanner.nextInt();

                        String insertRoom = "INSERT INTO rooms (room_id, room_type, price) VALUES (?, ?, ?)";
                        PreparedStatement pstmtRoom = connection.prepareStatement(insertRoom);
                        pstmtRoom.setInt(1, roomId);
                        pstmtRoom.setString(2, roomType);
                        pstmtRoom.setInt(3, price);
                        pstmtRoom.executeUpdate();
                        System.out.println("Inserted values into room table");
                    } catch (SQLException e) {
                    	System.out.println("Cannot insert into the table: " );
                    }
                    break;

                case 4:
                    try {
                        System.out.print("Enter booking id: ");
                        int bookingId = scanner.nextInt();
                        System.out.print("Enter staff id: ");
                        int staffId = scanner.nextInt();
                        System.out.print("Enter guest id: ");
                        int guestId = scanner.nextInt();
                        System.out.print("Enter room id: ");
                        int roomId = scanner.nextInt();

                        String insertBooking = "INSERT INTO bookings (booking_id, staff_id, guest_id, room_id) VALUES (?, ?, ?, ?)";
                        PreparedStatement pstmtBooking = connection.prepareStatement(insertBooking);
                        pstmtBooking.setInt(1, bookingId);
                        pstmtBooking.setInt(2, staffId);
                        pstmtBooking.setInt(3, guestId);
                        pstmtBooking.setInt(4, roomId);
                        pstmtBooking.executeUpdate();
                        System.out.println("Inserted values into booking table");
                    } catch (SQLException e) {
                    	System.out.println("Cannot insert into the table: " );
                    }
                    break;

                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }

    public void updateRecords() throws SQLException {
        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Update staff");
            System.out.println("2. Update guest");
            System.out.println("3. Update room");
            System.out.println("4. Update booking");
            System.out.println("5. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 5) {
                break;
            }

            switch (choice) {
                case 1:
                    try {
                        System.out.print("Enter staff id to update: ");
                        int staffId = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter new staff name: ");
                        String staffName = scanner.nextLine();

                        String updateStaff = "UPDATE staff SET staff_name = ? WHERE staff_id = ?";
                        PreparedStatement pstmtUpdateStaff = connection.prepareStatement(updateStaff);
                        pstmtUpdateStaff.setString(1, staffName);
                        pstmtUpdateStaff.setInt(2, staffId);
                        pstmtUpdateStaff.executeUpdate();
                        System.out.println("Updated staff table");
                    } catch (SQLException e) {
                        System.out.println("Cannot update the table: " + e.getMessage());
                    }
                    break;

                case 2:
                    try {
                        System.out.print("Enter guest id to update: ");
                        int guestId = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter new guest name: ");
                        String guestName = scanner.nextLine();

                        String updateGuest = "UPDATE guests SET guest_name = ? WHERE guest_id = ?";
                        PreparedStatement pstmtUpdateGuest = connection.prepareStatement(updateGuest);
                        pstmtUpdateGuest.setString(1, guestName);
                        pstmtUpdateGuest.setInt(2, guestId);
                        pstmtUpdateGuest.executeUpdate();
                        System.out.println("Updated guest table");
                    } catch (SQLException e) {
                        System.out.println("Cannot update the table: " + e.getMessage());
                    }
                    break;

                case 3:
                    try {
                        System.out.print("Enter room id to update: ");
                        int roomId = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter new room type: ");
                        String roomType = scanner.nextLine();
                        System.out.print("Enter new room price: ");
                        int price = scanner.nextInt();

                        String updateRoom = "UPDATE rooms SET room_type = ?, price = ? WHERE room_id = ?";
                        PreparedStatement pstmtUpdateRoom = connection.prepareStatement(updateRoom);
                        pstmtUpdateRoom.setString(1, roomType);
                        pstmtUpdateRoom.setInt(2, price);
                        pstmtUpdateRoom.setInt(3, roomId);
                        pstmtUpdateRoom.executeUpdate();
                        System.out.println("Updated room table");
                    } catch (SQLException e) {
                        System.out.println("Cannot update the table: " + e.getMessage());
                    }
                    break;

                case 4:
                    try {
                        System.out.print("Enter booking id to update: ");
                        int bookingId = scanner.nextInt();
                        System.out.print("Enter new staff id: ");
                        int staffId = scanner.nextInt();
                        System.out.print("Enter new guest id: ");
                        int guestId = scanner.nextInt();
                        System.out.print("Enter new room id: ");
                        int roomId = scanner.nextInt();

                        String updateBooking = "UPDATE bookings SET staff_id = ?, guest_id = ?, room_id = ? WHERE booking_id = ?";
                        PreparedStatement pstmtUpdateBooking = connection.prepareStatement(updateBooking);
                        pstmtUpdateBooking.setInt(1, staffId);
                        pstmtUpdateBooking.setInt(2, guestId);
                        pstmtUpdateBooking.setInt(3, roomId);
                        pstmtUpdateBooking.setInt(4, bookingId);
                        pstmtUpdateBooking.executeUpdate();
                        System.out.println("Updated booking table");
                    } catch (SQLException e) {
                        System.out.println("Cannot update the table: " + e.getMessage());
                    }
                    break;

                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }

    public void deleteRecord() throws SQLException {
        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Delete staff");
            System.out.println("2. Delete room");
            System.out.println("3. Delete booking");
            System.out.println("4. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 4) {
                break;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter staff id to delete: ");
                    int staffId = scanner.nextInt();

                    String deleteStaffSQL = "DELETE FROM staff WHERE staff_id = ?";
                    PreparedStatement pstmtDeleteStaff = connection.prepareStatement(deleteStaffSQL);
                    pstmtDeleteStaff.setInt(1, staffId);

                    int rowsAffectedStaff = pstmtDeleteStaff.executeUpdate();

                    if (rowsAffectedStaff > 0) {
                        System.out.println("Staff with id " + staffId + " has been deleted.");
                    } else {
                        System.out.println("No staff found with id " + staffId);
                    }
                    break;

                case 2:
                    System.out.print("Enter room id to delete: ");
                    int roomId = scanner.nextInt();

                    String deleteRoomSQL = "DELETE FROM rooms WHERE room_id = ?";
                    PreparedStatement pstmtDeleteRoom = connection.prepareStatement(deleteRoomSQL);
                    pstmtDeleteRoom.setInt(1, roomId);

                    int rowsAffectedRoom = pstmtDeleteRoom.executeUpdate();

                    if (rowsAffectedRoom > 0) {
                        System.out.println("Room with id " + roomId +"has been deleted.");
                    } else {
                        System.out.println("No room found with id " + roomId);
                    }
                    break;

                case 3:
                    System.out.print("Enter booking id to delete: ");
                    int bookingId = scanner.nextInt();

                    String deleteBookingSQL = "DELETE FROM bookings WHERE booking_id = ?";
                    PreparedStatement pstmtDeleteBooking = connection.prepareStatement(deleteBookingSQL);
                    pstmtDeleteBooking.setInt(1, bookingId);

                    int rowsAffectedBooking = pstmtDeleteBooking.executeUpdate();

                    if (rowsAffectedBooking > 0) {
                        System.out.println("Booking with id " + bookingId + " has been deleted.");
                    } else {
                        System.out.println("No booking found with id " + bookingId);
                    }
                    break;

                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }

    private void displayTable() throws SQLException {
        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Display staff table");
            System.out.println("2. Display guest table");
            System.out.println("3. Display room table");
            System.out.println("4. Display bookings table");
            System.out.println("5. Back to Main Menu");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 5) {
                break;
            }

            switch (choice) {
                case 1:
                    displayTable("staff");
                    break;
                case 2:
                    displayTable("guests");
                    break;
                case 3:
                    displayTable("rooms");
                    break;
                case 4:
                    displayTable("bookings");
                    break;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }

    private void displayTable(String tableName) throws SQLException {
        String query = "SELECT * FROM " + tableName;
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();

            while (rs.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) System.out.print(", ");
                    String columnValue = rs.getString(i);
                    System.out.print(rsmd.getColumnName(i) + ": " + columnValue);
                }
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println("Error accessing the table: " + e.getMessage());
        }
    }

    public void displayMenu() throws SQLException {
        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Create tables");
            System.out.println("2. Insert records");
            System.out.println("3. Update records");
            System.out.println("4. Delete records");
            System.out.println("5. Display tables");
            System.out.println("6. Exit");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    createTables();
                    break;
                case 2:
                    insertRecords();
                    break;
                case 3:
                    updateRecords();
                    break;
                case 4:
                    deleteRecord();
                    break;
                case 5:
                    displayTable();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }

    public static void main(String[] args) {
        try {
            HotelManagement system = new HotelManagement();
            system.displayMenu();
        } catch (Exception e) {
            e.printStackTrace();
        } }
}