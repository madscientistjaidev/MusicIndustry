
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * An implementation of some basic database utilities.
 */
public class DBUtils {

    /**
     * Opens database connection.
     *
     * @param user
     * @param pass
     * @param dbSID
     * @param host
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static Connection openDBConnection(String user, String pass,
            String dbSID, String host, int port) throws SQLException,
            ClassNotFoundException {

        String driver = "org.postgresql.Driver";
        Class.forName(driver);

        String url = "jdbc:postgresql://" + host + "/" + dbSID;
        String username = user;
        String password = pass;

        return DriverManager.getConnection(url, username, password);
    }

    /**
     * Test the connection.
     *
     * @param conn
     * @return current date and time if the connection is open. Otherwise an
     * exception will be thrown.
     * @throws SQLException
     */
    public static String testConnection(Connection conn) throws SQLException {
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select now() as res");
        String res = "";
        while (rs.next()) {
            res = "Servus: " + rs.getString("res");
        }
        rs.close();
        st.close();
        return res;
    }

    /**
     * Close the database connection.
     *
     * @param conn
     * @throws SQLException if connection is already closed.
     */
    public static void closeDBConnection(Connection conn) throws SQLException {
        conn.close();
    }

    /**
     * Get an integer that is returned as a result of a query.
     *
     * @param conn
     * @param query
     * @return result
     * @throws SQLException
     */
    public static int getIntFromDB(Connection conn, String query)
            throws SQLException {

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        int ret = Integer.MIN_VALUE;
        if (rs.next()) {
            ret = rs.getInt(1);
        }
        rs.close();
        st.close();
        return ret;
    }

    /**
     * Execute an update or a delete query.
     *
     * @param conn
     * @param query
     * @throws SQLException
     */
    public static void executeUpdate(Connection conn, String query)
            throws SQLException {

        Statement st = conn.createStatement();
        st.executeUpdate(query);
        st.close();
    }

    /**
     * Execute an update or a delete query using a prepared statement.
     *
     * @param conn
     * @param query
     * @throws SQLException
     */
    public static void executeUpdate(Connection conn, String query, String[] bindValues)
            throws SQLException {

        PreparedStatement pst = conn.prepareStatement(query);

        for (String val : bindValues) {
            pst.setString(1, val);
            pst.executeUpdate();
        }
        pst.close();
    }

}
