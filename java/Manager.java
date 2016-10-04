
import java.lang.reflect.Field;

/**
 * Contains methods for Manager entity.
 *
 * @author Ankush Israney
 * @author Jaidev RamaKrishna
 *
 */
public class Manager {

    /**
     * Primary key for Manager entity.
     */
    private int ssn;

    private String manager_name;
    private String dob;

    public Manager() {
    }

    /**
     * Initializes object for a single tuple.
     *
     * @param ssn
     * @param name
     * @param date
     */
    public Manager(int ssn, String name, String date) {
        this.ssn = ssn;
        manager_name = name;
        this.dob = date;
    }

    /*----------------All GET Methods----------------*/
    public int getManagerSsn() {
        return ssn;
    }

    public String getManagerName() {
        return manager_name;
    }

    public String getManagerDob() {
        return dob;
    }

    /*----------------All GET Methods----------------*/
    public void setManagerSsn(int ssn) {
        this.ssn = ssn;
    }

    public void setManagerName(String name) {
        this.manager_name = name;
    }

    public void setManagerDob(String date) {
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
    public String toHTML(Manager manager) throws IllegalArgumentException, IllegalAccessException {

        String a = "<tr>";
        Field[] fields = manager.getClass().getDeclaredFields();

        for (Field f : fields) {

            Object value = f.get(manager);
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
    public String toHTMLHeader(Manager manager) throws IllegalArgumentException, IllegalAccessException {

        String a = "<tr>";
        Field[] fields = manager.getClass().getDeclaredFields();

        for (Field f : fields) {

            Object name = f.getName();
            a += "<td> " + name + " </td>";

        }

        return a + "</tr>";
    }

}