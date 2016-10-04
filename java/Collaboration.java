
import java.lang.reflect.Field;

/**
 * Contains methods for Collaboration relation.
 *
 * @author Ankush Israney
 * @author Jaidev RamaKrishna
 *
 */
public class Collaboration {

    /**
     * Primary key for Collaboration relation.
     */
    private int track_id;

    private int artist_id;
    private String artist_role;

    public Collaboration() {
    }

    /**
     * Initializes object for a single tuple.
     *
     * @param track_id
     * @param artist_id
     * @param artist_role
     */
    public Collaboration(int track_id, int artist_id, String artist_role) {
        this.track_id = track_id;
        this.artist_id = artist_id;
        this.artist_role = artist_role;
    }

    /*----------------All GET Methods----------------*/
    public int getTrack_id() {
        return track_id;
    }

    public int getArtist_id() {
        return artist_id;
    }

    public String getArtist_role() {
        return artist_role;
    }

    /*----------------All SET Methods----------------*/
    public void setTrack_id(int track_id) {
        this.track_id = track_id;
    }

    public void setArtist_id(int artist_id) {
        this.artist_id = artist_id;
    }

    public void setArtist_role(String artist_role) {
        this.artist_role = artist_role;
    }

    /**
     * Outputs an HTML string for a single tuple.
     *
     * @param song
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public String toHTML(Collaboration collaboration) throws IllegalArgumentException, IllegalAccessException {

        String a = "<tr>";
        Field[] fields = collaboration.getClass().getDeclaredFields();

        for (Field f : fields) {

            Object value = f.get(collaboration);
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
    public String toHTMLHeader(Collaboration collaboration) throws IllegalArgumentException, IllegalAccessException {

        String a = "<tr>";
        Field[] fields = collaboration.getClass().getDeclaredFields();

        for (Field f : fields) {

            Object name = f.getName();
            a += "<td> " + name + " </td>";

        }

        return a + "</tr>";
    }

}
