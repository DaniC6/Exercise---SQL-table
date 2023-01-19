import java.sql.*;

public class Start {
    static final String DB_NAME = "newdb";
    static final String URL = "jdbc:mysql://localhost:3306/"+DB_NAME;
    static final String USER = "root";
    static final String PASSWORD = "060891";

    public static void main(String[] args) {

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO students (last_name, first_name) VALUES (?, ?)")) {

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS students ("
                    + " student_id INT(10) NOT NULL AUTO_INCREMENT, "
                    + "last_name VARCHAR(30) NOT NULL, "
                    + "first_name VARCHAR(30) NOT NULL, "
                    + "PRIMARY KEY (student_id))");

            insertStudents(preparedStatement, "Daniele", "Cara");
            insertStudents(preparedStatement, "Donato", "Lege");
            insertStudents(preparedStatement, "Gerardo", "Pulie");
            insertStudents(preparedStatement, "Arrigo", "Fedelo");


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void insertStudents(PreparedStatement preparedStatement, String firstName, String lastName) throws SQLException {
        preparedStatement.setString(1, lastName);
        preparedStatement.setString(2, firstName);
        preparedStatement.executeUpdate();
    }
}
