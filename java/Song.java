import java.lang.reflect.Field;

/**
 * Contains methods for Song entity.
 *
 * @author Ankush Israney
 * @author Jaidev RamaKrishna
 */
public class Song {

    /**
     * Primary key for Song entity.
     */
    private int track_id;

    private int tempo;
    private int song_length;
    private String title;
    private String song_key;
    private String song_language;
    private String artist_role;
    private int artist_id;
    private int album_id;
    private int composer_ssn;

    public Song() {
    }

    /**
     * Initializes object for a single tuple.
     *
     * @param track_id
     * @param tempo
     * @param song_length
     * @param title
     * @param song_key
     * @param song_language
     * @param artist_role
     * @param artist_id
     * @param album_id
     * @param composer_ssn
     */
    public Song(int track_id, int tempo, int song_length, String title, String song_key, String song_language, String artist_role, int artist_id, int album_id, int composer_ssn) {
        this.track_id = track_id;
        this.tempo = tempo;
        this.song_length = song_length;
        this.title = title;
        this.song_key = song_key;
        this.song_language = song_language;
        this.artist_role = artist_role;
        this.artist_id = artist_id;
        this.album_id = album_id;
        this.composer_ssn = composer_ssn;
    }

    /*----------------All GET Methods----------------*/
    public int getTrack_id() {
        return track_id;
    }

    public int getTempo() {
        return tempo;
    }

    public int getSong_length() {
        return song_length;
    }

    public String getTitle() {
        return title;
    }

    public String getSong_key() {
        return song_key;
    }

    public String getSong_language() {
        return song_language;
    }

    public String getArtist_role() {
        return artist_role;
    }

    public int getArtist_id() {
        return artist_id;
    }

    public int getAlbum_id() {
        return album_id;
    }

    public int getComposer_ssn() {
        return composer_ssn;
    }

    /*----------------All SET Methods----------------*/
    public void setTrack_id(int track_id) {
        this.track_id = track_id;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    public void setSong_length(int song_length) {
        this.song_length = song_length;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSong_key(String song_key) {
        this.song_key = song_key;
    }

    public void setSong_language(String song_language) {
        this.song_language = song_language;
    }

    public void setArtist_role(String artist_role) {
        this.artist_role = artist_role;
    }

    public void setArtist_id(int artist_id) {
        this.artist_id = artist_id;
    }

    public void setAlbum_id(int album_id) {
        this.album_id = album_id;
    }

    public void setComposer_ssn(int composer_ssn) {
        this.composer_ssn = composer_ssn;
    }

    /**
     * Outputs an HTML string for a single tuple.
     *
     * @param song
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public String toHTML(Song song) throws IllegalArgumentException, IllegalAccessException {

        String a = "<tr>";
        Field[] fields = song.getClass().getDeclaredFields();

        for (Field f : fields) {

            Object value = f.get(song);
            a += "<td> " + value + " </td>";

        }

        return a + "</tr>";
    }

    /**
     * Generates table header row.
     *
     * @param song
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public String toHTMLHeader(Song song) throws IllegalArgumentException, IllegalAccessException {

        String a = "<tr>";
        Field[] fields = song.getClass().getDeclaredFields();

        for (Field f : fields) {

            Object name = f.getName();
            a += "<td> " + name + " </td>";

        }

        return a + "</tr>";
    }
}
