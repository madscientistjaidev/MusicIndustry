
import java.lang.reflect.Field;

/**
 * Contains methods for Album entity.
 *
 * @author Ankush Israney
 * @author Jaidev RamaKrishna
 *
 */
public class Album {

    /**
     * Primary key for Album entity.
     */
    private int album_id;

    private String title;
    private int track_count;
    private int album_length;
    private String genre;
    private String company_id;
    private int priducer_ssn;
    private String studio;
    private String format;
    private int price;
    private String release_date;
    private String medium;
    private int sales;
    private int production_cost;

    public Album() {
    }

    /**
     * Initializes object for a single tuple.
     *
     * @param album_id
     * @param title
     * @param track_count
     * @param album_length
     * @param genre
     * @param company_id
     * @param priducer_ssn
     * @param studio
     * @param format
     * @param price
     * @param release_date
     * @param medium
     * @param sales
     * @param production_cost
     */
    public Album(int album_id, String title, int track_count, int album_length, String genre, String company_id, int priducer_ssn, String studio, String format, int price, String release_date, String medium, int sales, int production_cost) {
        this.album_id = album_id;
        this.title = title;
        this.track_count = track_count;
        this.album_length = album_length;
        this.genre = genre;
        this.company_id = company_id;
        this.priducer_ssn = priducer_ssn;
        this.studio = studio;
        this.format = format;
        this.price = price;
        this.release_date = release_date;
        this.medium = medium;
        this.sales = sales;
        this.production_cost = production_cost;
    }

    /*----------------All GET Methods----------------*/
    public int getAlbum_id() {
        return album_id;
    }

    public String getTitle() {
        return title;
    }

    public int getTrack_count() {
        return track_count;
    }

    public int getAlbum_length() {
        return album_length;
    }

    public String getGenre() {
        return genre;
    }

    public String getCompany_id() {
        return company_id;
    }

    public int getPriducer_ssn() {
        return priducer_ssn;
    }

    public String getStudio() {
        return studio;
    }

    public String getFormat() {
        return format;
    }

    public int getPrice() {
        return price;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getMedium() {
        return medium;
    }

    public int getSales() {
        return sales;
    }

    public int getProduction_cost() {
        return production_cost;
    }

    /*----------------All SET Methods----------------*/
    public void setAlbum_id(int album_id) {
        this.album_id = album_id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTrack_count(int track_count) {
        this.track_count = track_count;
    }

    public void setAlbum_length(int album_length) {
        this.album_length = album_length;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

    public void setPriducer_ssn(int priducer_ssn) {
        this.priducer_ssn = priducer_ssn;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    public void setProduction_cost(int production_cost) {
        this.production_cost = production_cost;
    }

    /**
     * Outputs an HTML string for a single tuple.
     *
     * @param song
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public String toHTML(Album album) throws IllegalArgumentException, IllegalAccessException {
        String a = "<tr>";
        Field[] fields = album.getClass().getDeclaredFields();

        for (Field f : fields) {
            Object value = f.get(album);
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
    public String toHTMLHeader(Album album) throws IllegalArgumentException, IllegalAccessException {
        String a = "<tr>";
        Field[] fields = album.getClass().getDeclaredFields();

        for (Field f : fields) {
            Object name = f.getName();
            a += "<td> " + name + " </td>";
        }

        return a + "</tr>";
    }
}
