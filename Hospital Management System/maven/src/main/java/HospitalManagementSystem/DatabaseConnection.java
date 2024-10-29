package HospitalManagementSystem;


	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.SQLException;

	public class DatabaseConnection {
	    public static void main(String[] args) {
	        String jdbcUrl = "jdbc:mysql://localhost/hospital";  // Update if using a different port
	        String username = "root";
	        String password = "@Srija5471";  // Your MySQL password

	        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
	            System.out.println("Connection successful");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
	


