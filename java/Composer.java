
import java.lang.reflect.Field;

/**
 * Contains methods for Composer entity.
 *
 * @author Ankush Israney
 * @author Jaidev RamaKrishna
 *
 */
public class Composer {

    /**
     * Primary key for Composer entity.
     */
    private int ssn;

    private String composer_name;
    private String dob;

    public Composer() {
    }

    /**
     * Initializes object for a single tuple.
     *
     * @param ssn
     * @param name
     * @param date
     */
    public Composer(int ssn, String name, String date) {
        this.ssn = ssn;
        composer_name = name;
        this.dob = date;
    }

    /*----------------All GET Methods----------------*/
    public int getComposerSsn() {
        return ssn;
    }

    public String getComposerName() {
        return composer_name;
    }

    public String getComposerDob() {
        return dob;
    }

    /*----------------All SET Methods----------------*/
    public void setComposerSsn(int ssn) {
        this.ssn = ssn;
    }

    public void setComposerName(String name) {
        this.composer_name = name;
    }

    public void setComposerDob(String date) {
        this.dob = date;
    }

    /**
     * Outputs an HTML string for a single tuple.
     *
     * @param song
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public String toHTML(Composer composer) throws IllegalArgumentException, IllegalAccessException {

        String a = "<tr>";
        Field[] fields = composer.getClass().getDeclaredFields();

        for (Field f : fields) {

            Object value = f.get(composer);
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
    public String toHTMLHeader(Composer composer) throws IllegalArgumentException, IllegalAccessException {

        String a = "<tr>";
        Field[] fields = composer.getClass().getDeclaredFields();

        for (Field f : fields) {

            Object name = f.getName();
            a += "<td> " + name + " </td>";

        }

        return a + "</tr>";
    }

}
