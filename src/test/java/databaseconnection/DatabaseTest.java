package databaseconnection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;

import static org.junit.Assert.assertEquals;

public class DatabaseTest {

    static final String DB_URL = "jdbc:postgresql://localhost:5432/Cetvrtagrupa";
    static final String USER = "postgres";
    static final String PASS = "Soda.Voda123";

    private Connection conn;

    @Before
    public void setUp() throws SQLException {
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
    }

    @Test
    public void checkUserTest() throws SQLException {
        String sql = "SELECT * FROM Users WHERE id=2";

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        rs.next();

        assertEquals("user2", rs.getString("username"));
        assertEquals("password2", rs.getString("password"));

    }
    //.............................................................................
    // HOMEWORK -> from table Payment

    @Test
    public void paymentUserTest() throws SQLException {
        String sql = "SELECT * FROM Payment WHERE id=2";

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        rs.next();

        assertEquals("Marko", rs.getString("name"));
        assertEquals("Markovski", rs.getString("surname"));
        assertEquals("2200", rs.getString("amount"));

    }

    @Test
    public void insertUserTest() throws SQLException {
        String insertUser = "INSERT INTO Payment (name, surname, amount) VALUES ('Krste', 'Krstevski', '6200');";

        Statement stmt = conn.createStatement();
        stmt.executeUpdate(insertUser);

        String sql = "SELECT * FROM Payment WHERE id=6";
        ResultSet rs = stmt.executeQuery(sql);

        rs.next();

        assertEquals("Krste", rs.getString("name"));
        assertEquals("Krstevski", rs.getString("surname"));
        assertEquals("6200", rs.getString("amount"));

    }

    @Test
    public void updateUserTest() throws SQLException {
        String updateUser = "UPDATE Payment SET name = 'Ivica' WHERE id=12";

        Statement stmt = conn.createStatement();
        stmt.executeUpdate(updateUser);

        String sql = "SELECT * FROM Payment WHERE id=12";
        ResultSet rs = stmt.executeQuery(sql);

        rs.next();

        assertEquals("Ivica", rs.getString("name"));
        assertEquals("Ivanovski", rs.getString("surname"));
        assertEquals("1200", rs.getString("amount"));
    }

        @After
        public void tearDown() throws SQLException {
            conn.close();
        }
    }

