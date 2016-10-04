import java.lang.reflect.Field;

/**
 * Contains methods for Producer entity.
 *
 * @author Ankush Israney
 * @author Jaidev RamaKrishna
 */
public class Producer {

    /**
     * Primary key for Producer entity.
     */
    private int ssn;

    private String producer_name;
    private String dob;

    public Producer() {
    }

    /**
     * Initializes object for a single tuple.
     *
     * @param ssn
     * @param name
     * @param date
     */
    public Producer(int ssn, String name, String date) {
        this.ssn = ssn;
        producer_name = name;
        this.dob = date;
    }

    /*----------------All GET Methods----------------*/
    public int getProducerSsn() {
        return ssn;
    }

    public String getProducerName() {
        return producer_name;
    }

    public String getProducerDob() {
        return dob;
    }

    /*----------------All SET Methods----------------*/
    public void setProducerSsn(int ssn) {
        this.ssn = ssn;
    }

    public void setProducerName(String name) {
        this.producer_name = name;
    }

    public void setProducerDob(String date) {
        this.dob = date;
    }

    /**
     * Outputs an HTML string for a single tuple.
     *
     * @param producer
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public String toHTML(Producer producer) throws IllegalArgumentException, IllegalAccessException {

        String a = "<tr>";
        Field[] fields = producer.getClass().getDeclaredFields();

        for (Field f : fields) {

            Object value = f.get(producer);
            a += "<td> " + value + " </td>";

        }

        return a + "</tr>";
    }

    /**
     * Generates table header row.
     *
     * @param producer
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public String toHTMLHeader(Producer producer) throws IllegalArgumentException, IllegalAccessException {

        String a = "<tr>";
        Field[] fields = producer.getClass().getDeclaredFields();

        for (Field f : fields) {

            Object name = f.getName();
            a += "<td> " + name + " </td>";

        }

        return a + "</tr>";
    }
}
