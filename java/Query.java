
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

/**
 *
 * Query Implementations class.
 *
 * @author Ankush Vijay Israney
 * @author Jaidev RamaKrishna
 *
 * @version - Final
 * @date of creation- 07/27/2016
 *
 */
public class Query {

    private static Connection _conn = null;
    private static ResourceBundle _bundle;

    /**
     * Opens database connection using bundled parameters.
     *
     * @param bundle - resource bundle that contains database connection
     * information
     * @return
     */
    public String openDBConnection(String bundle) {
        _bundle = ResourceBundle.getBundle(bundle);
        return openDBConnection(
                _bundle.getString("dbUser"),
                _bundle.getString("dbPass"),
                _bundle.getString("dbSID"),
                _bundle.getString("dbHost"),
                Integer.parseInt(_bundle.getString("dbPort"))
        );
    }

    /**
     * Opens database connection using passed parameters.
     *
     * @param dbUser
     * @param dbPass
     * @param dbSID
     * @param dbHost
     * @return
     */
    public String openDBConnection(String dbUser, String dbPass, String dbSID, String dbHost, int port) {

        String res = "";
        if (_conn != null) {
            closeDBConnection();
        }

        try {
            _conn = DBUtils.openDBConnection(dbUser, dbPass, dbSID, dbHost, port);
            res = DBUtils.testConnection(_conn);
        } catch (SQLException | ClassNotFoundException sqle) {
            sqle.printStackTrace(System.err);
        }
        return res;
    }

    /**
     * Closes the database connection.
     */
    public void closeDBConnection() {
        try {
            DBUtils.closeDBConnection(_conn);
            System.out.println("Closed a connection");
        } catch (SQLException sqle) {
            sqle.printStackTrace(System.err);
        }
    }

    /**
     * Serially inserts terms into table.
     *
     * @param terms
     */
    public void addTermsDynamicSQL(String[] terms) {

        for (String term : terms) {
            try {
                String query = "insert into Terms values ('" + term + "')";
                DBUtils.executeUpdate(_conn, query);
            } catch (SQLException sqle) {
                System.out.println("Insert into Terms failed for " + term);
            }
        }
    }

    /**
     * Inserts array of terms into table.
     *
     * @param terms
     */
    public void addTermsPreparedStatement(String[] terms) {
        try {
            String query = "insert into Terms values ( ? )";
            DBUtils.executeUpdate(_conn, query, terms);
        } catch (SQLException sqle) {
            System.out.println(sqle.toString());
        }
    }

    /**
     * Gets Manager for specified Artist.
     *
     * @param artist_id
     * @return
     * @throws SQLException
     */
    String getManagerForArtist(int artist_id) throws SQLException {
        String result = "<h1>Manager Entity Cards for Artists: " + artist_id + "</h1>";
        result += "<table cellpadding= 3 border=1>";
        result += "<tr>"
                + "<td> " + "artist_id" + " </td>"
                + "<td> " + "artist_name" + " </td>"
                + "<td> " + "manager_ssn" + " </td>"
                + "<td> " + "manager_name" + " </td>"
                + "<td> " + "dob" + " </td>" + "</tr>";

        PreparedStatement st = _conn.prepareStatement("SELECT * from Manager, Artist "
                + "WHERE Manager.ssn = " + " Artist.manager_ssn "
                + "and Artist.artist_id = ?;");
        st.setInt(1, artist_id);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            result
                    += "<tr>"
                    + "<td> " + rs.getInt("artist_id") + " </td>"
                    + "<td> " + rs.getString("artist_name") + " </td>"
                    + "<td> " + rs.getInt("manager_ssn") + " </td>"
                    + "<td> " + rs.getString("manager_name") + " </td>"
                    + "<td> " + rs.getString("dob") + " </td>"
                    + "</tr>";

        }

        rs.close();
        st.close();

        return result;
    }

    /**
     * Gets Record Label for specified Artist.
     *
     * @param artist_id
     * @return
     * @throws SQLException
     */
    String getRecordLabelForArtist(int artist_id) throws SQLException {
        String result = "<h1>Record Label Entity Cards for Artist: " + artist_id + "</h1>";
        result += "<table cellpadding= 3 border=1>";
        result += "<tr>"
                + "<td> " + "artist_id" + " </td>"
                + "<td> " + "artist_name" + " </td>"
                + "<td> " + "company_id" + " </td>"
                + "<td> " + "record_label_name" + " </td>"
                + "<td> " + "revenue" + " </td>"
                + "<td> " + "address" + " </td>" + "</tr>";

        PreparedStatement st = _conn.prepareStatement("SELECT Record_Label.*, Artist.artist_id, Artist.artist_name from Record_Label, Artist "
                + "WHERE Record_Label.company_id = " + " Artist.company_id "
                + "and Artist.artist_id = ?;");
        st.setInt(1, artist_id);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            result
                    += "<tr>"
                    + "<td> " + rs.getInt("artist_id") + " </td>"
                    + "<td> " + rs.getString("artist_name") + " </td>"
                    + "<td> " + rs.getString("company_id") + " </td>"
                    + "<td> " + rs.getString("record_label_name") + " </td>"
                    + "<td> " + rs.getInt("revenue") + " </td>"
                    + "<td> " + rs.getString("address") + " </td>"
                    + "</tr>";

        }

        rs.close();
        st.close();

        return result;
    }

    /**
     * Gets Songs for specified Artist.
     *
     * @param artist_id
     * @return
     * @throws SQLException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    String getSongForArtist(int artist_id) throws SQLException, IllegalArgumentException, IllegalAccessException {
        String result = "<h1>Song Cards for Main Artist (Excluding Collaboration): " + artist_id + "</h1>";
        result += "<table cellpadding= 3 border=1>";

        PreparedStatement st = _conn.prepareStatement("SELECT A.artist_name, S.* from Song S, Artist A"
                + " WHERE S.artist_id = A.artist_id"
                + " AND A.artist_id = ?;"
        );

        st.setInt(1, artist_id);
        ResultSet rs = st.executeQuery();
        ResultSetMetaData rm = rs.getMetaData();

        result += getResultString(rs, rm, "");

        rs.close();
        st.close();

        return result;
    }

    /**
     * List Managers for Artists.
     *
     * @return
     * @throws SQLException
     * @throws IllegalArgumentException
     */
    public String getAllManagerForArtist() throws SQLException, IllegalArgumentException {

        String result = "<h1>Manager Cards for Artists</h1>";
        result += "<table cellpadding= 3 border=1>";
        result += "<tr>"
                + "<td> " + "artist_id" + " </td>"
                + "<td> " + "artist_name" + " </td>"
                + "<td> " + "manager_ssn" + " </td>"
                + "<td> " + "manager_name" + " </td>"
                + "<td> " + "dob" + " </td>" + "</tr>";

        String query = "select Artist.artist_id, Artist.artist_name, Manager.* from Artist, Manager where Artist.manager_ssn = Manager.ssn";

        Statement st = _conn.createStatement();
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {

            result
                    += "<tr>"
                    + "<td> " + rs.getInt("artist_id") + " </td>"
                    + "<td> " + rs.getString("artist_name") + " </td>"
                    + "<td> " + rs.getInt("ssn") + " </td>"
                    + "<td> " + rs.getString("manager_name") + " </td>"
                    + "<td> " + rs.getString("dob") + " </td>"
                    + "</tr>";
        }

        rs.close();
        st.close();

        return result;

    }

    /**
     * Lists Record Labels for Artists.
     *
     * @return
     * @throws SQLException
     */
    String getAllRecordLabelForArtist() throws SQLException {
        String result = "<h1>Record Label Entity Cards for Artists</h1>";
        result += "<table cellpadding= 3 border=1>";
        result += "<tr>"
                + "<td> " + "artist_id" + " </td>"
                + "<td> " + "artist_name" + " </td>"
                + "<td> " + "company_id" + " </td>"
                + "<td> " + "record_label_name" + " </td>"
                + "<td> " + "revenue" + " </td>"
                + "<td> " + "address" + " </td>" + "</tr>";

        PreparedStatement st = _conn.prepareStatement("SELECT Record_Label.*, Artist.artist_id, Artist.artist_name from Record_Label, Artist "
                + "WHERE Record_Label.company_id = " + " Artist.company_id");

        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            result
                    += "<tr>"
                    + "<td> " + rs.getInt("artist_id") + " </td>"
                    + "<td> " + rs.getString("artist_name") + " </td>"
                    + "<td> " + rs.getString("company_id") + " </td>"
                    + "<td> " + rs.getString("record_label_name") + " </td>"
                    + "<td> " + rs.getInt("revenue") + " </td>"
                    + "<td> " + rs.getString("address") + " </td>"
                    + "</tr>";

        }

        rs.close();
        st.close();

        return result;
    }

    /**
     * Lists Songs for Artists.
     *
     * @return
     * @throws SQLException
     */
    String getAllSongForArtist() throws SQLException {
        String result = "<h1>Song Cards for Main Artists (Excluding Collaborations) : " + "</h1>";
        result += "<table cellpadding= 3 border=1>";

        PreparedStatement st = _conn.prepareStatement("SELECT A.artist_name, S.* from Song S, Artist A"
                + " WHERE S.artist_id = A.artist_id "
                + " order by S.artist_id;"
        );

        ResultSet rs = st.executeQuery();
        ResultSetMetaData rm = rs.getMetaData();
        String group = "artist_id";

        result += getResultString(rs, rm, group);

        rs.close();
        st.close();

        return result;
    }

    /**
     * Lists Albums for Artists.
     *
     * @return
     * @throws SQLException
     */
    String getAllAlbumForArtist() throws SQLException {
        String result = "<h1>Album Cards for Main Artists (Excluding Collaborations) : " + "</h1>";
        result += "<table cellpadding= 3 border=1>";

        PreparedStatement st = _conn.prepareStatement("SELECT distinct A.artist_name, A.artist_id, Al.* from Song S, Artist A, Album Al"
                + " WHERE S.artist_id = A.artist_id "
                + " AND Al.album_id = S.album_id "
                + " order by A.artist_id;");

        ResultSet rs = st.executeQuery();
        ResultSetMetaData rm = rs.getMetaData();
        String group = "artist_id";

        result += getResultString(rs, rm, group);

        rs.close();
        st.close();

        return result;
    }

    /**
     * Gets Albums for specified Artist.
     *
     * @param artist_id
     * @return
     * @throws SQLException
     */
    String getAlbumForArtist(int artist_id) throws SQLException {
        String result = "<h1>Album Cards for Main Artist (Excluding Collaboration): " + artist_id + "</h1>";
        result += "<table cellpadding= 3 border=1>";

        PreparedStatement st = _conn.prepareStatement("SELECT distinct A.artist_name, Al.* from Album Al, Artist A, Song S"
                + " WHERE S.artist_id = A.artist_id"
                + " AND S.album_id = Al.album_id"
                + " AND A.artist_id = ?;"
                + "                      "
        );

        st.setInt(1, artist_id);
        ResultSet rs = st.executeQuery();
        ResultSetMetaData rm = rs.getMetaData();

        result += getResultString(rs, rm, "");

        rs.close();
        st.close();

        return result;
    }

    /**
     * Lists Collaborations for Artists.
     *
     * @return
     * @throws SQLException
     */
    String getAllCollaborationForArtist() throws SQLException {
        String result = "<h1>Collaborations of Artists (Exlcuding Main Artists) : " + "</h1>";
        result += "<table cellpadding= 3 border=1>";

        PreparedStatement st = _conn.prepareStatement("SELECT A.*, C.track_id, C.artist_role"
                + " FROM Collaborates C, Artist A "
                + " WHERE C.artist_id = A.artist_id"
                + " order by A.artist_id;");

        ResultSet rs = st.executeQuery();
        ResultSetMetaData rm = rs.getMetaData();
        String group = "artist_id";

        result += getSimpleResult(rs, rm);

        rs.close();
        st.close();

        return result;
    }

    /**
     * Gets Collaborations for specified Artist.
     *
     * @param artist_id
     * @return
     * @throws SQLException
     */
    String getCollaborationForArtist(int artist_id) throws SQLException {
        String result = "<h1>Collaborations of Artists (Excluding Main Artists): " + artist_id + "</h1>";
        result += "<table cellpadding= 3 border=1>";

        PreparedStatement st = _conn.prepareStatement("SELECT A.*, C.track_id, C.artist_role"
                + " FROM Collaborates C, Artist A "
                + " WHERE C.artist_id = A.artist_id"
                + " AND A.artist_id = ?;");

        st.setInt(1, artist_id);
        ResultSet rs = st.executeQuery();
        ResultSetMetaData rm = rs.getMetaData();
        String group = "artist_id";

        result += getSimpleResult(rs, rm);

        rs.close();
        st.close();

        return result;
    }

    /**
     * Lists Producers for Artists.
     *
     * @return
     * @throws SQLException
     */
    String getAllProducerForArtist() throws SQLException {
        String result = "<h1>Producers who have produced an album for Artists : " + "</h1>";
        result += "<table cellpadding= 3 border=1>";

        PreparedStatement st = _conn.prepareStatement("SELECT distinct A.artist_name, A.artist_id, P.*"
                + " FROM Producer P, Artist A, Song S, Album Al "
                + " WHERE A.artist_id = S.artist_id"
                + " AND S.album_id = Al.album_id"
                + " AND P.ssn = Al.producer_ssn"
                + " order by A.artist_id");

        ResultSet rs = st.executeQuery();
        ResultSetMetaData rm = rs.getMetaData();
        String group = "artist_id";

        result += getResultString(rs, rm, group);

        rs.close();
        st.close();

        return result;
    }

    /**
     * Gets Producers for specified Artist.
     *
     * @param artist_id
     * @return
     * @throws SQLException
     */
    String getProducerForArtist(int artist_id) throws SQLException {
        String result = "<h1>Producers who have produced an Album for an Artist: " + artist_id + "</h1>";
        result += "<table cellpadding= 3 border=1>";

        PreparedStatement st = _conn.prepareStatement("SELECT distinct A.artist_name, P.*"
                + " FROM Producer P, Artist A, Song S, Album Al "
                + " WHERE A.artist_id = S.artist_id"
                + " AND S.album_id = Al.album_id"
                + " AND P.ssn = Al.producer_ssn"
                + " AND A.artist_id = ?;");

        st.setInt(1, artist_id);
        ResultSet rs = st.executeQuery();
        ResultSetMetaData rm = rs.getMetaData();
        String group = "artist_id";

        result += getResultString(rs, rm, "");

        rs.close();
        st.close();

        return result;
    }

    /**
     * Handles list of results.
     *
     * @param rs
     * @param rm
     * @param group
     * @return
     * @throws SQLException
     */
    String getMultiResult(ResultSet rs, ResultSetMetaData rm, String group) throws SQLException {
        String previous = "";
        int flag = 0;
        String s = "";
        String name = "";
        String head = "<TR>";

        int column = rm.getColumnCount();

        for (int i = 2; i <= column; i++) {
            head += "<TD> " + rm.getColumnLabel(i) + " </TD>";
        }

        head += "</TR>";

        String sub = "";
        while (rs.next()) {

            String content = "<TR>";
            for (int i = 2; i <= column; i++) {

                if (rm.getColumnLabel(i).matches(group)) {
                    if (previous.matches(rs.getObject((String) rm.getColumnLabel(i)).toString())) {
                        flag = 0;
                    } else {
                        flag = 1;
                    }

                    previous = rs.getObject((String) rm.getColumnLabel(i)).toString();
                    name = "-" + rs.getObject((String) rm.getColumnLabel(1));
                }

                content += "<TD> " + rs.getObject((String) rm.getColumnLabel(i)) + " </TD>";

            }

            if (flag == 1) {
                sub += "<TR><TD><U>" + previous + name + "</U></TD></TR>" + head + "" + content + "</TR>";
            } else {
                sub += content + "</TR>";
            }

        }

        s += sub;

        return s;

    }

    /**
     * Handles single results.
     *
     * @param rs
     * @param rm
     * @return
     * @throws SQLException
     */
    String getSingleResult(ResultSet rs, ResultSetMetaData rm) throws SQLException {
        String sub = "<TR>";
        String name = "";
        String s = "";

        int column = rm.getColumnCount();

        for (int i = 2; i <= column; i++) {
            sub += "<TD> " + rm.getColumnLabel(i) + " </TD>";
        }

        sub += "</TR>";

        while (rs.next()) {

            sub += "<TR>";
            name = rs.getObject((String) rm.getColumnLabel(1)).toString();
            for (int i = 2; i <= column; i++) {
                sub += "<TD> " + rs.getObject((String) rm.getColumnLabel(i)) + " </TD>";

            }

            sub += "</TR>";

        }
        s = "<TR><TD><U>Name: " + name + "</U></TD></TR>" + sub;
        return s;
    }

    /**
     * Gets simplified form of results.
     *
     * @param rs
     * @param rm
     * @return
     * @throws SQLException
     */
    String getSimpleResult(ResultSet rs, ResultSetMetaData rm) throws SQLException {
        String sub = "<TR>";
        String s = "";

        int column = rm.getColumnCount();

        for (int i = 1; i <= column; i++) {
            sub += "<TD> " + rm.getColumnLabel(i) + " </TD>";
        }

        sub += "</TR>";

        while (rs.next()) {

            sub += "<TR>";

            for (int i = 1; i <= column; i++) {
                sub += "<TD> " + rs.getObject((String) rm.getColumnLabel(i)) + " </TD>";

            }

            sub += "</TR>";

        }
        s += sub;
        return s;
    }

    /**
     * Gets results as a string.
     *
     * @param rs
     * @param rm
     * @param group
     * @return
     * @throws SQLException
     */
    String getResultString(ResultSet rs, ResultSetMetaData rm, String group) throws SQLException {
        String s = "";

        if (group.matches("")) {
            s = getSingleResult(rs, rm);
        } else {
            s = getMultiResult(rs, rm, group);
        }

        return s;
    }

    /**
     * Lists Producers for Albums.
     *
     * @return
     * @throws SQLException
     */
    String getAllProducerForAlbum() throws SQLException {
        String result = "<h1>Album Cards for Main Artist (Excluding Collaboration): </h1>";
        result += "<table cellpadding= 3 border=1>";

        PreparedStatement st = _conn.prepareStatement("SELECT Al.title, Al.album_id, P.* "
                + " from Album Al, Producer P "
                + " where Al.producer_ssn = P.ssn order by Al.album_id;"
        );

        ResultSet rs = st.executeQuery();
        ResultSetMetaData rm = rs.getMetaData();

        result += getResultString(rs, rm, "album_id");

        rs.close();
        st.close();

        return result;
    }

    /**
     * Gets Producer for Specified Album.
     *
     * @param album_id
     * @return
     * @throws SQLException
     */
    String getProducerForAlbum(int album_id) throws SQLException {
        String result = "<h1>Album Cards for Main Artist (Excluding Collaboration): " + album_id + "</h1>";
        result += "<table cellpadding= 3 border=1>";

        PreparedStatement st = _conn.prepareStatement("SELECT Al.title, P.* "
                + " from Album Al, Producer P "
                + " where Al.producer_ssn = P.ssn and Al.album_id=?;"
        );

        st.setInt(1, album_id);
        ResultSet rs = st.executeQuery();
        ResultSetMetaData rm = rs.getMetaData();

        result += getResultString(rs, rm, "");

        rs.close();
        st.close();

        return result;
    }

    /**
     * Lists Songs for Albums.
     *
     * @return
     * @throws SQLException
     */
    String getAllSongForAlbum() throws SQLException {
        String result = "<h1>Songs for Albums: </h1>";
        result += "<table cellpadding= 3 border=1>";

        PreparedStatement st = _conn.prepareStatement("SELECT distinct Al.title album_title, S.* "
                + " from Album Al, Song S"
                + " where Al.album_id = S.album_id order by album_id;"
        );

        ResultSet rs = st.executeQuery();
        ResultSetMetaData rm = rs.getMetaData();

        result += getResultString(rs, rm, "album_id");

        rs.close();
        st.close();
        return result;
    }

    /**
     * Gets Songs for specified Album.
     *
     * @param album_id
     * @return
     * @throws SQLException
     */
    String getSongForAlbum(int album_id) throws SQLException {
        String result = "<h1>Songs for the Album: " + album_id + "</h1>";
        result += "<table cellpadding= 3 border=1>";

        PreparedStatement st = _conn.prepareStatement("SELECT Al.title album_title, S.* "
                + " from Album Al, Song S"
                + " where Al.album_id = S.album_id and Al.album_id=?;"
        );

        st.setInt(1, album_id);
        ResultSet rs = st.executeQuery();
        ResultSetMetaData rm = rs.getMetaData();

        result += getResultString(rs, rm, "");

        rs.close();
        st.close();
        return result;
    }

    /**
     * Lists Record Labels for Albums.
     *
     * @return
     * @throws SQLException
     */
    String getAllRecordLabelForAlbum() throws SQLException {
        String result = "<h1>Record Labels For Albums: " + "</h1>";
        result += "<table cellpadding= 3 border=1>";

        PreparedStatement st = _conn.prepareStatement("SELECT distinct Al.title, Al.album_id, R.* "
                + " from Album Al, record_label R"
                + " where Al.company_id = R.company_id "
                + "order by Al.album_id ;"
        );

        ResultSet rs = st.executeQuery();
        ResultSetMetaData rm = rs.getMetaData();

        result += getResultString(rs, rm, "album_id");

        rs.close();
        st.close();

        return result;
    }

    /**
     * Gets Record Label for specified Album.
     *
     * @param album_id
     * @return
     * @throws SQLException
     */
    String getRecordLabelForAlbum(int album_id) throws SQLException {
        String result = "<h1>Record Label For Album: " + album_id + "</h1>";
        result += "<table cellpadding= 3 border=1>";

        PreparedStatement st = _conn.prepareStatement("SELECT Al.title, R.* "
                + " from Album Al, record_label R"
                + " where Al.company_id = R.company_id and Al.album_id=?;"
        );

        st.setInt(1, album_id);
        ResultSet rs = st.executeQuery();
        ResultSetMetaData rm = rs.getMetaData();

        result += getResultString(rs, rm, "");

        rs.close();
        st.close();

        return result;

    }

    /**
     * Gets Artist for specified Album.
     *
     * @param album_id
     * @return
     * @throws SQLException
     */
    String getArtistForAlbum(int album_id) throws SQLException {
        String result = "<h1>Main Artist for Album: " + album_id + "</h1>";
        result += "<table cellpadding= 3 border=1>";

        PreparedStatement st = _conn.prepareStatement("SELECT distinct Al.title, A.*  "
                + " from Album Al, Artist A, Song S"
                + " where Al.album_id = S.album_id"
                + " and S.artist_id = A.artist_id"
                + " and Al.album_id=?;"
        );

        st.setInt(1, album_id);
        ResultSet rs = st.executeQuery();
        ResultSetMetaData rm = rs.getMetaData();

        result += getResultString(rs, rm, "");

        rs.close();
        st.close();

        return result;

    }

    /**
     * Lists Artists for Albums.
     *
     * @return
     * @throws SQLException
     */
    String getAllArtistForAlbum() throws SQLException {
        String result = "<h1>Main Artists for Albums: </h1>";
        result += "<table cellpadding= 3 border=1>";

        PreparedStatement st = _conn.prepareStatement("SELECT distinct Al.title, Al.album_id, A.*"
                + " from Album Al, Artist A, Song S"
                + " where Al.album_id = S.album_id"
                + " and S.artist_id = A.artist_id"
                + " order by Al.album_id;"
        );

        ResultSet rs = st.executeQuery();
        ResultSetMetaData rm = rs.getMetaData();

        result += getResultString(rs, rm, "album_id");

        rs.close();
        st.close();

        return result;
    }

    /**
     * Lists Artists for Songs.
     *
     * @return
     * @throws SQLException
     */
    String getAllArtistForSong() throws SQLException {
        String result = "<h1>Main Artists for Tracks: </h1>";
        result += "<table cellpadding= 3 border=1>";

        PreparedStatement st = _conn.prepareStatement("SELECT S.title, S.track_id, A.*"
                + " from Artist A, Song S"
                + " where S.artist_id = A.artist_id"
                + " order by S.track_id;"
        );

        ResultSet rs = st.executeQuery();
        ResultSetMetaData rm = rs.getMetaData();

        result += getResultString(rs, rm, "track_id");

        rs.close();
        st.close();

        return result;
    }

    /**
     * Gets Artist for specified Song.
     *
     * @param track_id
     * @return
     * @throws SQLException
     */
    String getArtistForSong(int track_id) throws SQLException {
        String result = "<h1>Main Artists for Tracks: " + track_id + "</h1>";
        result += "<table cellpadding= 3 border=1>";

        PreparedStatement st = _conn.prepareStatement("SELECT S.title, A.*"
                + " from Artist A, Song S"
                + " where S.artist_id = A.artist_id"
                + " and S.track_id = ?;"
        );

        st.setInt(1, track_id);
        ResultSet rs = st.executeQuery();
        ResultSetMetaData rm = rs.getMetaData();

        result += getResultString(rs, rm, "");

        rs.close();
        st.close();

        return result;
    }

    /**
     * Lists Collaborators for Songs.
     *
     * @return
     * @throws SQLException
     */
    String getAllCollaborationForSong() throws SQLException {
        String result = "<h1>Collaboraters for Tracks (Excluding Main Artist): </h1>";
        result += "<table cellpadding= 3 border=1>";

        PreparedStatement st = _conn.prepareStatement("SELECT S.title, S.track_id, C.artist_role, A.* "
                + " from Artist A, Song S, Collaborates C"
                + " where A.artist_id = C.artist_id"
                + " and S.track_id = C.track_id"
                + " order by S.track_id;"
        );

        ResultSet rs = st.executeQuery();
        ResultSetMetaData rm = rs.getMetaData();

        result += getResultString(rs, rm, "track_id");

        rs.close();
        st.close();

        return result;
    }

    /**
     * Gets Collaborator for specified Song.
     *
     * @param track_id
     * @return
     * @throws SQLException
     */
    String getCollaborationForSong(int track_id) throws SQLException {
        String result = "<h1>Collaboraters for Track (Excluding Main Artist): " + track_id + "</h1>";
        result += "<table cellpadding= 3 border=1>";

        PreparedStatement st = _conn.prepareStatement("SELECT S.title, C.artist_role, A.* "
                + " from Artist A, Song S, Collaborates C"
                + " where A.artist_id = C.artist_id"
                + " and S.track_id = C.track_id"
                + " and S.track_id = ?;"
        );

        st.setInt(1, track_id);
        ResultSet rs = st.executeQuery();
        ResultSetMetaData rm = rs.getMetaData();

        result += getResultString(rs, rm, "");

        rs.close();
        st.close();

        return result;
    }

    /**
     * Lists Albums for Songs.
     *
     * @return
     * @throws SQLException
     */
    String getAllAlbumForSong() throws SQLException {
        String result = "<h1>Albums for Tracks: </h1>";
        result += "<table cellpadding= 3 border=1>";

        PreparedStatement st = _conn.prepareStatement("SELECT S.title song_title, S.track_id, Al.* from Album Al, Song S where Al.album_id = S.album_id order by S.track_id;"
        );

        ResultSet rs = st.executeQuery();
        ResultSetMetaData rm = rs.getMetaData();

        result += getResultString(rs, rm, "track_id");

        rs.close();
        st.close();

        return result;
    }

    /**
     * Gets Album for specified Song.
     *
     * @param track_id
     * @return
     * @throws SQLException
     */
    String getAlbumForSong(int track_id) throws SQLException {
        String result = "<h1>Album for Track: " + track_id + "</h1>";
        result += "<table cellpadding= 3 border=1>";

        PreparedStatement st = _conn.prepareStatement("SELECT S.title song_title, Al.* from Album Al, Song S where Al.album_id = S.album_id AND S.track_id = ?;"
        );

        st.setInt(1, track_id);

        ResultSet rs = st.executeQuery();
        ResultSetMetaData rm = rs.getMetaData();

        result += getResultString(rs, rm, "");

        rs.close();
        st.close();

        return result;
    }

    /**
     * Lists Albums for Producers.
     *
     * @return
     * @throws SQLException
     */
    String getAllAlbumForProducer() throws SQLException {
        String result = "<h1>Albums for Producers: </h1>";
        result += "<table cellpadding= 3 border=1>";

        PreparedStatement st = _conn.prepareStatement("SELECT distinct P.producer_name, P.ssn producer, Al.* "
                + "from Album Al, Producer P where Al.producer_ssn = P.ssn order by P.ssn;"
        );

        ResultSet rs = st.executeQuery();
        ResultSetMetaData rm = rs.getMetaData();

        result += getResultString(rs, rm, "producer");

        rs.close();
        st.close();

        return result;
    }

    /**
     * Gets Albums for specified Producer.
     *
     * @param producer_id
     * @return
     * @throws SQLException
     */
    String getAlbumForProducer(int producer_id) throws SQLException {
        String result = "<h1>Albums for Producer: " + producer_id + "</h1>";
        result += "<table cellpadding= 3 border=1>";

        PreparedStatement st = _conn.prepareStatement("SELECT distinct P.producer_name, Al.* "
                + "from Album Al, Producer P where Al.producer_ssn = P.ssn and P.ssn = ?;"
        );

        st.setInt(1, producer_id);
        ResultSet rs = st.executeQuery();
        ResultSetMetaData rm = rs.getMetaData();

        result += getResultString(rs, rm, "");

        rs.close();
        st.close();

        return result;
    }

    /**
     * Lists Artists for Producers.
     *
     * @return
     * @throws SQLException
     */
    String getAllArtistForProducer() throws SQLException {
        String result = "<h1>Main Artists for which Albums were produced by Producers: </h1>";
        result += "<table cellpadding= 3 border=1>";

        PreparedStatement st = _conn.prepareStatement("SELECT distinct P.producer_name, P.ssn producer, A.* "
                + "from Album Al, Artist A, Song S, Producer P "
                + " where Al.producer_ssn = P.ssn "
                + " and Al.album_id = S.album_id "
                + " and S.artist_id = A.artist_id"
                + " order by P.ssn;"
        );

        ResultSet rs = st.executeQuery();
        ResultSetMetaData rm = rs.getMetaData();

        result += getResultString(rs, rm, "producer");

        rs.close();
        st.close();

        return result;
    }

    /**
     * Gets Artists for specified Producer.
     *
     * @param producer_id
     * @return
     * @throws SQLException
     */
    String getArtistForProducer(int producer_id) throws SQLException {
        String result = "<h1>Main Artists for which Albums were produced by Producer: " + producer_id + "</h1>";
        result += "<table cellpadding= 3 border=1>";

        PreparedStatement st = _conn.prepareStatement("SELECT distinct P.producer_name, P.ssn producer, A.* "
                + "from Album Al, Artist A, Song S, Producer P "
                + "where Al.producer_ssn = P.ssn "
                + " and Al.album_id = S.album_id "
                + " and S.artist_id = A.artist_id"
                + " and P.ssn = ? ;"
        );

        st.setInt(1, producer_id);
        ResultSet rs = st.executeQuery();
        ResultSetMetaData rm = rs.getMetaData();

        result += getResultString(rs, rm, "");

        rs.close();
        st.close();

        return result;
    }

    /**
     * Lists Record Labels for Producer.
     *
     * @return
     * @throws SQLException
     */
    String getAllRecordLabelForProducer() throws SQLException {
        String result = "<h1>All Record Labels for which Albums were produced by Producers: </h1>";
        result += "<table cellpadding= 3 border=1>";

        PreparedStatement st = _conn.prepareStatement("SELECT distinct P.producer_name, P.ssn producer, R.* "
                + " from Album Al, Record_Label R, Producer P "
                + " where Al.producer_ssn = P.ssn "
                + " and Al.company_id = R.company_id "
                + " order by P.ssn;"
        );

        ResultSet rs = st.executeQuery();
        ResultSetMetaData rm = rs.getMetaData();

        result += getResultString(rs, rm, "producer");

        rs.close();
        st.close();

        return result;
    }

    /**
     * Gets Record Labels for specified Producer.
     *
     * @param producer_id
     * @return
     * @throws SQLException
     */
    String getRecordLabelForProducer(int producer_id) throws SQLException {
        String result = "<h1>All Record Labels for which Albums were produced by Producer: " + producer_id + "</h1>";
        result += "<table cellpadding= 3 border=1>";

        PreparedStatement st = _conn.prepareStatement("SELECT distinct P.producer_name, P.ssn producer, R.* "
                + " from Album Al, Record_Label R, Producer P "
                + " where Al.producer_ssn = P.ssn "
                + " and Al.company_id = R.company_id "
                + " and P.ssn = ?;"
        );

        st.setInt(1, producer_id);

        ResultSet rs = st.executeQuery();
        ResultSetMetaData rm = rs.getMetaData();

        result += getResultString(rs, rm, "");

        rs.close();
        st.close();

        return result;
    }

    /**
     * Lists Songs for Composers.
     *
     * @return
     * @throws SQLException
     */
    String getAllSongForComposer() throws SQLException {
        String result = "<h1>All Songs Composed by Composers: </h1>";
        result += "<table cellpadding= 3 border=1>";

        PreparedStatement st = _conn.prepareStatement("SELECT distinct C.composer_name, C.ssn composer, S.* "
                + " from Song S, Composer C"
                + " where C.ssn = S.composer_ssn "
                + " order by C.ssn;"
        );

        ResultSet rs = st.executeQuery();
        ResultSetMetaData rm = rs.getMetaData();

        result += getResultString(rs, rm, "composer");

        rs.close();
        st.close();

        return result;
    }

    /**
     * Gets Songs for specified Composer.
     *
     * @param composer_id
     * @return
     * @throws SQLException
     */
    String getSongForComposer(int composer_id) throws SQLException {
        String result = "<h1>All Songs Composed by Composer: " + composer_id + " </h1>";
        result += "<table cellpadding= 3 border=1>";

        PreparedStatement st = _conn.prepareStatement("SELECT distinct C.composer_name, C.ssn composer, S.* "
                + " from Song S, Composer C"
                + " where C.ssn = S.composer_ssn "
                + " and C.ssn = ?;"
        );

        st.setInt(1, composer_id);

        ResultSet rs = st.executeQuery();
        ResultSetMetaData rm = rs.getMetaData();

        result += getResultString(rs, rm, "");

        rs.close();
        st.close();

        return result;
    }

    /**
     * Lists Artists for Composers.
     *
     * @return
     * @throws SQLException
     */
    String getAllArtistForComposer() throws SQLException {
        String result = "<h1>All Artists for which Composers have Composed Songs: </h1>";
        result += "<table cellpadding= 3 border=1>";

        PreparedStatement st = _conn.prepareStatement("SELECT distinct C.composer_name, C.ssn composer, A.* "
                + " from Composer C, Artist A, Song S "
                + " where A.artist_id = S.artist_id "
                + " and C.ssn = S.composer_ssn "
                + " order by C.ssn;"
        );

        ResultSet rs = st.executeQuery();
        ResultSetMetaData rm = rs.getMetaData();

        result += getResultString(rs, rm, "composer");

        rs.close();
        st.close();

        return result;
    }

    /**
     * Gets Artists for specified Composer.
     *
     * @param composer_id
     * @return
     * @throws SQLException
     */
    String getArtistForComposer(int composer_id) throws SQLException {
        String result = "<h1>All Artists for which Composer: " + composer_id + " has Composed Songs: </h1>";
        result += "<table cellpadding= 3 border=1>";

        PreparedStatement st = _conn.prepareStatement("SELECT distinct C.composer_name, C.ssn composer, A.* "
                + " from Composer C, Artist A, Song S "
                + " where A.artist_id = S.artist_id "
                + " and C.ssn = S.composer_ssn "
                + " and C.ssn = ?;"
        );

        st.setInt(1, composer_id);
        ResultSet rs = st.executeQuery();
        ResultSetMetaData rm = rs.getMetaData();

        result += getResultString(rs, rm, "");

        rs.close();
        st.close();

        return result;
    }

    /**
     * Lists Artists for Managers.
     *
     * @return
     * @throws SQLException
     */
    String getAllArtistForManager() throws SQLException {
        String result = "<h1>All Artists Managed by Managers: </h1>";
        result += "<table cellpadding= 3 border=1>";

        PreparedStatement st = _conn.prepareStatement("SELECT distinct M.manager_name, M.ssn manager, A.* "
                + " from Manager M, Artist A"
                + " where A.manager_ssn = M.ssn "
                + " order by M.ssn;"
        );

        ResultSet rs = st.executeQuery();
        ResultSetMetaData rm = rs.getMetaData();

        result += getResultString(rs, rm, "manager");

        rs.close();
        st.close();

        return result;
    }

    /**
     * Gets all Artists for specified Manager.
     *
     * @param manager_id
     * @return
     * @throws SQLException
     */
    String getArtistForManager(int manager_id) throws SQLException {
        String result = "<h1>All Artists Managed by Manager : " + manager_id + "</h1>";
        result += "<table cellpadding= 3 border=1>";

        PreparedStatement st = _conn.prepareStatement("SELECT distinct M.manager_name, M.ssn manager, A.* "
                + " from Manager M, Artist A"
                + " where A.manager_ssn = M.ssn "
                + " and M.ssn = ?;"
        );

        st.setInt(1, manager_id);
        ResultSet rs = st.executeQuery();
        ResultSetMetaData rm = rs.getMetaData();

        result += getResultString(rs, rm, "");

        rs.close();
        st.close();

        return result;
    }

    /**
     * Lists Artists for Record Labels.
     *
     * @return
     * @throws SQLException
     */
    String getAllArtistForRecordLabel() throws SQLException {
        String result = "<h1>All Artists who signed a Contract with Record Labels: </h1>";
        result += "<table cellpadding= 3 border=1>";

        PreparedStatement st = _conn.prepareStatement("SELECT distinct R.record_label_name, A.*"
                + " from Record_Label R, Artist A"
                + " where A.company_id = R.company_id "
                + " order by A.company_id;"
        );

        ResultSet rs = st.executeQuery();
        ResultSetMetaData rm = rs.getMetaData();

        result += getResultString(rs, rm, "company_id");

        rs.close();
        st.close();

        return result;
    }

    /**
     * Gets Artists for specified Record Label.
     *
     * @param company_id
     * @return
     * @throws SQLException
     */
    String getArtistForRecordLabel(int company_id) throws SQLException {
        String result = "<h1>All Artists who signed a Contract with Record Labels: </h1>";
        result += "<table cellpadding= 3 border=1>";

        PreparedStatement st = _conn.prepareStatement("SELECT distinct R.record_label_name, A.* "
                + " from Record_Label R, Artist A"
                + " where A.company_id = R.company_id "
                + " and A.company_id = ?;"
        );

        st.setString(1, Integer.toString(company_id));
        ResultSet rs = st.executeQuery();
        ResultSetMetaData rm = rs.getMetaData();

        result += getResultString(rs, rm, "company_id");

        rs.close();
        st.close();

        return result;
    }

    /**
     * Lists Albums for Record Labels.
     *
     * @return
     * @throws SQLException
     */
    String getAllAlbumForRecordLabel() throws SQLException {
        String result = "<h1>All Albums under Record Labels: </h1>";
        result += "<table cellpadding= 3 border=1>";

        PreparedStatement st = _conn.prepareStatement("SELECT distinct R.record_label_name, Al.*"
                + " from Record_Label R, Album Al"
                + " where Al.company_id = R.company_id "
                + " order by Al.company_id;"
        );

        ResultSet rs = st.executeQuery();
        ResultSetMetaData rm = rs.getMetaData();

        result += getResultString(rs, rm, "company_id");

        rs.close();
        st.close();

        return result;
    }

    /**
     * Gets Artists for specified Record Label.
     *
     * @param company_id
     * @return
     * @throws SQLException
     */
    String getAlbumForRecordLabel(int company_id) throws SQLException {
        String result = "<h1>All Albums under Record Label: " + company_id + "</h1>";
        result += "<table cellpadding= 3 border=1>";

        PreparedStatement st = _conn.prepareStatement("SELECT distinct R.record_label_name, Al.* "
                + " from Record_Label R, Album Al"
                + " where Al.company_id = R.company_id "
                + " and Al.company_id = ?;"
        );

        st.setString(1, Integer.toString(company_id));
        ResultSet rs = st.executeQuery();
        ResultSetMetaData rm = rs.getMetaData();

        result += getResultString(rs, rm, "company_id");

        rs.close();
        st.close();

        return result;
    }
}
