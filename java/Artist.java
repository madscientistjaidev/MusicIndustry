
import java.lang.reflect.Field;

/**
 * Contains methods for Artist entity.
 *
 * @author Ankush Israney
 * @author Jaidev RamaKrishna
 *
 */
public class Artist {

    /**
     * Primary key for Artist entity.
     */
    private int artist_id;
    private String artist_name;
    private String dob;
    private String country;
    private double percentage_cut_manager;
    private String manager_since;
    private int manager_ssn;
    private String company_id;
    private int contract_amount;
    private String contract_date;

    public Artist() {
    }

    /**
     * Initializes object for a single tuple.
     *
     * @param artist_id
     * @param artist_name
     * @param dob
     * @param country
     * @param percentage_cut_manager
     * @param manager_since
     * @param manager_ssn
     * @param company_id
     * @param contract_amount
     * @param contract_date
     */
    public Artist(int artist_id, String artist_name, String dob, String country,
            double percentage_cut_manager, String manager_since, int manager_ssn, String company_id,
            int contract_amount, String contract_date) {
        this.artist_id = artist_id;
        this.artist_name = artist_name;
        this.dob = dob;
        this.country = country;
        this.percentage_cut_manager = percentage_cut_manager;
        this.manager_since = manager_since;
        this.manager_ssn = manager_ssn;
        this.company_id = company_id;
        this.contract_amount = contract_amount;
        this.contract_date = contract_date;

    }

    /*----------------All GET Methods----------------*/
    public String getArtistName() {
        return artist_name;
    }

    public String getArtistDob() {
        return dob;
    }

    public int getArtistId() {
        return artist_id;
    }

    public String getArtistCountry() {
        return country;
    }

    public String getArtistCompanyId() {
        return company_id;
    }

    public int getArtistManager() {
        return manager_ssn;
    }

    public double getArtistAmount() {
        return contract_amount;
    }

    public String getArtistContractDate() {
        return contract_date;
    }

    public String getArtistManagerDate() {
        return manager_since;
    }

    public double getManagerCut() {
        return percentage_cut_manager;
    }

    public void getArtistName(String name) {
        artist_name = name;
    }

    /*----------------All SET Methods----------------*/
    public void setArtistDob(String dob) {
        this.dob = dob;
    }

    public void setArtistId(int id) {
        artist_id = id;
    }

    public void setArtistCountry(String Country) {
        country = Country;
    }

    public void setArtistCompanyId(String id) {
        company_id = id;
    }

    public void setArtistManager(int ssn) {
        manager_ssn = ssn;
    }

    public void setArtistAmount(int amount) {
        contract_amount = amount;
    }

    public void setArtistContractDate(String date) {
        contract_date = date;
    }

    public void setArtistManagerDate(String date) {
        manager_since = date;
    }

    public void setManagerCut(double percentage) {
        percentage_cut_manager = percentage;
    }

    /**
     * Outputs an HTML string for a single tuple.
     *
     * @param song
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public String toHTML(Artist artist) throws IllegalArgumentException, IllegalAccessException {

        String a = "<tr>";
        Field[] fields = artist.getClass().getDeclaredFields();

        for (Field f : fields) {

            Object value = f.get(artist);
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
    public String toHTMLHeader(Artist artist) throws IllegalArgumentException, IllegalAccessException {

        String a = "<tr>";
        Field[] fields = artist.getClass().getDeclaredFields();

        for (Field f : fields) {

            Object name = f.getName();
            a += "<td> " + name + " </td>";

        }

        return a + "</tr>";
    }

}
