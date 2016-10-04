
import java.lang.reflect.Field;

/**
 * Contains methods for Record Label entity.
 *
 * @author Ankush Israney
 * @author Jaidev RamaKrishna
 */
public class RecordLabel {

    /**
     * Primary key for Record Label entity.
     */
    private String company_id;

    private String recordlabel_name;
    private int revenue;
    private String address;

    public RecordLabel() {
    }

    /**
     * Initializes object for a single tuple.
     *
     * @param company_id
     * @param recordlabel_name
     * @param revenue
     * @param address
     */
    public RecordLabel(String company_id, String recordlabel_name, int revenue, String address) {
        this.company_id = company_id;
        this.recordlabel_name = recordlabel_name;
        this.revenue = revenue;
        this.address = address;
    }

    /*----------------All GET Methods----------------*/
    public String getCompany_id() {
        return company_id;
    }

    public String getRecordlabel_name() {
        return recordlabel_name;
    }

    public int getRevenue() {
        return revenue;
    }

    public String getAddress() {
        return address;
    }

    /*----------------All SET Methods----------------*/
    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

    public void setRecordlabel_name(String recordlabel_name) {
        this.recordlabel_name = recordlabel_name;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Outputs an HTML string for a single tuple.
     *
     * @param song
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public String toHTML(RecordLabel record) throws IllegalArgumentException, IllegalAccessException {

        String a = "<tr>";
        Field[] fields = record.getClass().getDeclaredFields();

        for (Field f : fields) {

            Object value = f.get(record);
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
    public String toHTMLHeader(RecordLabel record) throws IllegalArgumentException, IllegalAccessException {

        String a = "<tr>";
        Field[] fields = record.getClass().getDeclaredFields();

        for (Field f : fields) {

            Object name = f.getName();
            a += "<td> " + name + " </td>";

        }

        return a + "</tr>";
    }

}
