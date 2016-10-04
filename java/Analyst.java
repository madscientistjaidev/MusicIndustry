
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Analyst interface for Music Management system.
 *
 * @author Ankush Vijay Israney
 * @author Jaidev RamaKrishna
 *
 * @version - Final
 * @date of creation- 07/27/2016
 */
public class Analyst {

    private static Connection _conn = null;
    private static ResourceBundle _bundle;

    /**
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
     * Open the database connection.
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
     * Close the database connection.
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
     * Fetches all Artists.
     *
     * @return
     * @throws SQLException
     */
    public ArrayList<Artist> getArtists() throws SQLException {

        ArrayList<Artist> artist_list = new ArrayList<>();

        String query = "select * from Artist";

        Statement st = _conn.createStatement();
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {

            int artist_id = rs.getInt("artist_id");
            String artist_name = rs.getString("artist_name");
            String dob = rs.getString("dob");
            String country = rs.getString("country");
            double percentage_cut_manager = rs.getDouble("percentage_cut_manager");
            String manager_since = rs.getString("manager_since");
            int manager_ssn = rs.getInt("manager_ssn");
            String company_id = rs.getString("company_id");
            int contract_amount = rs.getInt("contract_amount");
            String contract_date = rs.getString("contract_date");

            Artist artist = new Artist(artist_id, artist_name, dob, country, percentage_cut_manager, manager_since, manager_ssn, company_id, contract_amount, contract_date);

            artist_list.add(artist);
        }

        rs.close();
        st.close();

        return artist_list;

    }

    /**
     * Fetches specified Artist.
     *
     * @param artistId
     * @return
     * @throws SQLException
     */
    public Artist getArtist(int artistId) throws SQLException {

        Artist artist = null;

        PreparedStatement st = _conn.prepareStatement("SELECT * from Artist WHERE Artist.artist_id = ?;");
        st.setInt(1, artistId);
        ResultSet rs = st.executeQuery();
        
        if(!rs.isBeforeFirst())
            artist = null;
        
        else{
        
        while (rs.next()) {

            int artist_id = rs.getInt("artist_id");
            String artist_name = rs.getString("artist_name");
            String dob = rs.getString("dob");
            String country = rs.getString("country");
            double percentage_cut_manager = rs.getDouble("percentage_cut_manager");
            String manager_since = rs.getString("manager_since");
            int manager_ssn = rs.getInt("manager_ssn");
            String company_id = rs.getString("company_id");
            int contract_amount = rs.getInt("contract_amount");
            String contract_date = rs.getString("contract_date");

            artist = new Artist(artist_id, artist_name, dob, country, percentage_cut_manager, manager_since, manager_ssn, company_id, contract_amount, contract_date);
        }

        rs.close();
        st.close();
        
        }

        return artist;

    }

    /**
     * Fetches all Composers.
     *
     * @return
     * @throws SQLException
     */
    public ArrayList<Composer> getComposers() throws SQLException {

        ArrayList<Composer> composer_list = new ArrayList<>();

        String query = "select * from Composer";

        Statement st = _conn.createStatement();
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {

            int composer_id = rs.getInt("ssn");
            String composer_name = rs.getString("composer_name");
            String dob = rs.getString("dob");

            Composer composer = new Composer(composer_id, composer_name, dob);

            composer_list.add(composer);
        }

        rs.close();
        st.close();

        return composer_list;

    }

    /**
     * Fetches specified Composer.
     *
     * @param composerId
     * @return
     * @throws SQLException
     */
    public Composer getComposer(int composerId) throws SQLException {

        Composer composer = null;

        PreparedStatement st = _conn.prepareStatement("SELECT * from Composer WHERE Composer.ssn = ?;");
        st.setInt(1, composerId);
        ResultSet rs = st.executeQuery();
        
         if(!rs.isBeforeFirst())
            composer = null;
        
         else{
            while (rs.next()) {

            int composer_id = rs.getInt("ssn");
            String composer_name = rs.getString("composer_name");
            String dob = rs.getString("dob");

            composer = new Composer(composer_id, composer_name, dob);

            }
         }

        rs.close();
        st.close();

        return composer;

    }

    /**
     * Fetches all Producers.
     *
     * @return
     * @throws SQLException
     */
    public ArrayList<Producer> getProducers() throws SQLException {

        ArrayList<Producer> producer_list = new ArrayList<>();

        String query = "select * from Producer";

        Statement st = _conn.createStatement();
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {

            int producer_id = rs.getInt("ssn");
            String producer_name = rs.getString("producer_name");
            String dob = rs.getString("dob");

            Producer producer = new Producer(producer_id, producer_name, dob);

            producer_list.add(producer);
        }

        rs.close();
        st.close();

        return producer_list;

    }

    /**
     * Fetches specified Producer.
     *
     * @param producerId
     * @return
     * @throws SQLException
     */
    public Producer getProducer(int producerId) throws SQLException {

        Producer producer = null;

        PreparedStatement st = _conn.prepareStatement("SELECT * from Producer WHERE Producer.ssn = ?;");
        st.setInt(1, producerId);
        ResultSet rs = st.executeQuery();
        
        if(!rs.isBeforeFirst())
            producer = null;
        
        else{
            while (rs.next()) {

            int producer_id = rs.getInt("ssn");
            String producer_name = rs.getString("producer_name");
            String dob = rs.getString("dob");

            producer = new Producer(producer_id, producer_name, dob);

            }
        }

        rs.close();
        st.close();

        return producer;

    }

    /**
     * Fetches all Managers.
     *
     * @return
     * @throws SQLException
     */
    public ArrayList<Manager> getManagers() throws SQLException {

        ArrayList<Manager> manager_list = new ArrayList<>();

        String query = "select * from Manager";

        Statement st = _conn.createStatement();
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {

            int manager_id = rs.getInt("ssn");
            String manager_name = rs.getString("manager_name");
            String dob = rs.getString("dob");

            Manager manager = new Manager(manager_id, manager_name, dob);

            manager_list.add(manager);
        }

        rs.close();
        st.close();

        return manager_list;

    }

    /**
     * Fetches specified Manager.
     *
     * @param managerId
     * @return
     * @throws SQLException
     */
    public Manager getManager(int managerId) throws SQLException {

        Manager manager = null;

        PreparedStatement st = _conn.prepareStatement("SELECT * from Manager WHERE Manager.ssn = ?;");
        st.setInt(1, managerId);
        ResultSet rs = st.executeQuery();
        
        if(!rs.isBeforeFirst())
            manager = null;
        
        else{
           while (rs.next()) {

            int manager_id = rs.getInt("ssn");
            String manager_name = rs.getString("manager_name");
            String dob = rs.getString("dob");

            manager = new Manager(manager_id, manager_name, dob);

            }

        rs.close();
        st.close();
        }
        return manager;

    }

    /**
     * Fetches all Record Labels.
     *
     * @return
     * @throws SQLException
     */
    public ArrayList<RecordLabel> getRecordLabels() throws SQLException {

        ArrayList<RecordLabel> record_list = new ArrayList<>();

        String query = "select * from Record_Label";

        Statement st = _conn.createStatement();
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {

            String record_id = rs.getString("company_id");
            String record_name = rs.getString("record_label_name");
            int revenue = rs.getInt("revenue");
            String address = rs.getString("address");

            RecordLabel record = new RecordLabel(record_id, record_name, revenue, address);

            record_list.add(record);
        }
        rs.close();
        st.close();

        return record_list;

    }

    /**
     * Fetches specified Record Label.
     *
     * @param recordId
     * @return
     * @throws SQLException
     */
    public RecordLabel getRecordLabel(String recordId) throws SQLException {

        RecordLabel record = null;

        PreparedStatement st = _conn.prepareStatement("SELECT * from Record_Label WHERE company_id = '" + recordId + "';");
        ResultSet rs = st.executeQuery();
        
        if(!rs.isBeforeFirst())
            record = null;
        
        else{
            while (rs.next()) {

            String record_id = rs.getString("company_id");
            String record_name = rs.getString("record_label_name");
            int revenue = rs.getInt("revenue");
            String address = rs.getString("address");

            record = new RecordLabel(record_id, record_name, revenue, address);

            }
        }    

        rs.close();
        st.close();

        return record;

    }

    /**
     * Fetches all Collaborations.
     *
     * @return
     * @throws SQLException
     */
    public ArrayList<Collaboration> getCollaborations() throws SQLException {

        ArrayList<Collaboration> collaboration_list = new ArrayList<>();

        String query = "select * from Collaborates";

        Statement st = _conn.createStatement();
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {

            int track_id = rs.getInt("track_id");
            int artist_id = rs.getInt("artist_id");
            String artist_role = rs.getString("artist_role");

            Collaboration collaboration = new Collaboration(track_id, artist_id, artist_role);
            collaboration_list.add(collaboration);
        }
        rs.close();
        st.close();

        return collaboration_list;

    }

    /**
     * Fetches specified Collaboration.
     *
     * @param collaborationId
     * @return
     * @throws SQLException
     */
    public Collaboration getCollaboration(int collaborationId) throws SQLException {

        Collaboration collaboration = null;

        PreparedStatement st = _conn.prepareStatement("SELECT * from Collaborates WHERE Collaborates.artist_id = ?;");
        st.setInt(1, collaborationId);
        ResultSet rs = st.executeQuery();
        
        if(!rs.isBeforeFirst())
            collaboration = null;
        
        else{
            while (rs.next()) {

            int track_id = rs.getInt("track_id");
            int artist_id = rs.getInt("artist_id");
            String artist_role = rs.getString("artist_role");

            collaboration = new Collaboration(track_id, artist_id, artist_role);

            }
        }     

        rs.close();
        st.close();

        return collaboration;

    }

    /**
     * Fetches all Albums.
     *
     * @return
     * @throws SQLException
     */
    public ArrayList<Album> getAlbums() throws SQLException {

        ArrayList<Album> album_list = new ArrayList<>();

        String query = "select * from Album";

        Statement st = _conn.createStatement();
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {

            int album_id = rs.getInt("album_id");
            String title = rs.getString("title");
            int track_count = rs.getInt("track_count");
            int album_length = rs.getInt("album_length");
            String genre = rs.getString("genre");
            String company_id = rs.getString("company_id");
            int producer_ssn = rs.getInt("producer_ssn");
            String studio = rs.getString("studio");
            String format = rs.getString("format");
            int price = rs.getInt("price");
            String release_date = rs.getString("release_date");
            String medium = rs.getString("medium");
            int sales = rs.getInt("sales");
            int production_cost = rs.getInt("production_cost");

            Album album = new Album(album_id, title, track_count, album_length, genre, company_id, producer_ssn, studio, format, price, release_date, medium, sales, production_cost);
            album_list.add(album);
        }
        rs.close();
        st.close();

        return album_list;

    }

    /**
     * Fetches specified Album.
     *
     * @param albumId
     * @return
     * @throws SQLException
     */
    public Album getAlbum(int albumId) throws SQLException {

        Album album = null;

        PreparedStatement st = _conn.prepareStatement("SELECT * from Album WHERE Album.album_id = ?;");
        st.setInt(1, albumId);
        ResultSet rs = st.executeQuery();
        
        if(!rs.isBeforeFirst())
            album = null;
        
        else{
        while (rs.next()) {

            int album_id = rs.getInt("album_id");
            String title = rs.getString("title");
            int track_count = rs.getInt("track_count");
            int album_length = rs.getInt("album_length");
            String genre = rs.getString("genre");
            String company_id = rs.getString("company_id");
            int producer_ssn = rs.getInt("producer_ssn");
            String studio = rs.getString("studio");
            String format = rs.getString("format");
            int price = rs.getInt("price");
            String release_date = rs.getString("release_date");
            String medium = rs.getString("medium");
            int sales = rs.getInt("sales");
            int production_cost = rs.getInt("production_cost");

            album = new Album(album_id, title, track_count, album_length, genre, company_id, producer_ssn, studio, format, price, release_date, medium, sales, production_cost);

            }
        }

        rs.close();
        st.close();

        return album;

    }

    /**
     * Fetches all Songs.
     *
     * @return
     * @throws SQLException
     */
    public ArrayList<Song> getSongs() throws SQLException {

        ArrayList<Song> song_list = new ArrayList<>();

        String query = "select * from Song;";

        Statement st = _conn.createStatement();
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {

            int track_id = rs.getInt("track_id");
            int tempo = rs.getInt("tempo");
            int song_length = rs.getInt("song_length");
            String title = rs.getString("title");
            String song_key = rs.getString("song_key");
            String language = rs.getString("song_language");
            String artist_role = rs.getString("artist_role");
            int artist_id = rs.getInt("artist_id");
            int album_id = rs.getInt("album_id");
            int composer_ssn = rs.getInt("composer_ssn");

            Song song = new Song(track_id, tempo, song_length, title, song_key, language, artist_role, artist_id, album_id, composer_ssn);
            song_list.add(song);
        }
        rs.close();
        st.close();

        return song_list;

    }

    /**
     * Fetches specified Song.
     *
     * @param songId
     * @return
     * @throws SQLException
     */
    public Song getSong(int songId) throws SQLException {

        Song song = null;

        PreparedStatement st = _conn.prepareStatement("SELECT * from Song WHERE Song.track_id = ?;");
        st.setInt(1, songId);
        ResultSet rs = st.executeQuery();
        
        if(!rs.isBeforeFirst())
            song = null;
        
        else{
            while (rs.next()) {

            int track_id = rs.getInt("track_id");
            int tempo = rs.getInt("tempo");
            int song_length = rs.getInt("song_length");
            String title = rs.getString("title");
            String song_key = rs.getString("song_key");
            String language = rs.getString("song_language");
            String artist_role = rs.getString("artist_role");
            int artist_id = rs.getInt("artist_id");
            int album_id = rs.getInt("album_id");
            int composer_ssn = rs.getInt("composer_ssn");

            song = new Song(track_id, tempo, song_length, title, song_key, language, artist_role, artist_id, album_id, composer_ssn);

            }
        }

        rs.close();
        st.close();

        return song;

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
}
