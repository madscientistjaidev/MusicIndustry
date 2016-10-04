
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * Servlet that runs SQL queries.
 * @author Ankush Israney
 * @author Jaidev RamaKrishna
 *
 */
public class QueryServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private Query _query;
    private String _message;

    /**
     * Initializes servlet and opens database connection.
     */
    @Override
    public void init() throws ServletException {
        _query = new Query();
        _message = _query.openDBConnection("PgBundle");
    }

    /**
     * Request/Response method.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        if (!_message.startsWith("Servus")) {
            PrintWriter out = response.getWriter();
            out.println("<html><head></head><body>");
            out.println("<h1>Database connection12 failed to open " + _message + "</h1>");

        } else {
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/jspviews/initial.jsp");
            dispatcher.forward(request, response);

        }

    }

    /**
     * Fetches artist tuples.
     * @param artist_id
     * @param option
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException 
     */
    private String printForArtist(int artist_id, String option) throws IllegalArgumentException, IllegalAccessException {
        String entity = "";

        try {
            switch (option) {

                case "first":
                    entity = (artist_id < 0) ? _query.getAllManagerForArtist() : _query.getManagerForArtist(artist_id);
                    break;

                case "second":
                    entity = (artist_id < 0) ? _query.getAllRecordLabelForArtist() : _query.getRecordLabelForArtist(artist_id);
                    break;

                case "third":
                    entity = (artist_id < 0) ? _query.getAllSongForArtist() : _query.getSongForArtist(artist_id);
                    break;

                case "fourth":
                    entity = (artist_id < 0) ? _query.getAllAlbumForArtist() : _query.getAlbumForArtist(artist_id);
                    break;

                case "fifth":
                    entity = (artist_id < 0) ? _query.getAllCollaborationForArtist() : _query.getCollaborationForArtist(artist_id);
                    break;

                case "sixth":
                    entity = (artist_id < 0) ? _query.getAllProducerForArtist() : _query.getProducerForArtist(artist_id);
                    break;

            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return entity;

    }

    /**
     * Fetches Album tuples.
     * @param album_id
     * @param option
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException 
     */
    private String printForAlbum(int album_id, String option) throws IllegalArgumentException, IllegalAccessException {
        String entity = "";

        try {
            switch (option) {

                case "first":
                    entity = (album_id < 0) ? _query.getAllProducerForAlbum() : _query.getProducerForAlbum(album_id);
                    break;

                case "second":
                    entity = (album_id < 0) ? _query.getAllSongForAlbum() : _query.getSongForAlbum(album_id);
                    break;

                case "third":
                    entity = (album_id < 0) ? _query.getAllRecordLabelForAlbum() : _query.getRecordLabelForAlbum(album_id);
                    break;

                case "fourth":
                    entity = (album_id < 0) ? _query.getAllArtistForAlbum() : _query.getArtistForAlbum(album_id);
                    break;

            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return entity;

    }

    /**
     * Fetches Song tuples.
     * @param song_id
     * @param option
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException 
     */
    private String printForSong(int song_id, String option) throws IllegalArgumentException, IllegalAccessException {
        String entity = "";

        try {
            switch (option) {

                case "first":
                    entity = (song_id < 0) ? _query.getAllArtistForSong() : _query.getArtistForSong(song_id);
                    break;

                case "second":
                    entity = (song_id < 0) ? _query.getAllCollaborationForSong() : _query.getCollaborationForSong(song_id);
                    break;

                case "third":
                    entity = (song_id < 0) ? _query.getAllAlbumForSong() : _query.getAlbumForSong(song_id);
                    break;

            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return entity;
    }

    /**
     * Fetches Producer tuples.
     * @param producer_id
     * @param option
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException 
     */
    private String printForProducer(int producer_id, String option) throws IllegalArgumentException, IllegalAccessException {
        String entity = "";

        try {
            switch (option) {

                case "first":
                    entity = (producer_id < 0) ? _query.getAllAlbumForProducer() : _query.getAlbumForProducer(producer_id);
                    break;

                case "second":
                    entity = (producer_id < 0) ? _query.getAllArtistForProducer() : _query.getArtistForProducer(producer_id);
                    break;

                case "third":
                    entity = (producer_id < 0) ? _query.getAllRecordLabelForProducer() : _query.getRecordLabelForProducer(producer_id);
                    break;

            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return entity;

    }

    /**
     * Fetches Composer tuples.
     * @param composer_id
     * @param option
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException 
     */
    private String printForComposer(int composer_id, String option) throws IllegalArgumentException, IllegalAccessException {
        String entity = "";

        try {
            switch (option) {

                case "first":
                    entity = (composer_id < 0) ? _query.getAllSongForComposer() : _query.getSongForComposer(composer_id);
                    break;

                case "second":
                    entity = (composer_id < 0) ? _query.getAllArtistForComposer() : _query.getArtistForComposer(composer_id);
                    break;

            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return entity;

    }

    /**
     * Fetches Manager tuples.
     * @param manager_id
     * @param option
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException 
     */
    private String printForManager(int manager_id, String option) throws IllegalArgumentException, IllegalAccessException {
        String entity = "";

        try {
            switch (option) {

                case "first":
                    entity = (manager_id < 0) ? _query.getAllArtistForManager() : _query.getArtistForManager(manager_id);
                    break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return entity;

    }

    /**
     * Fetches Record Label tuples.
     * @param company_id
     * @param option
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException 
     */
    private String printForRecordLabel(int company_id, String option) throws IllegalArgumentException, IllegalAccessException {
        String entity = "";

        try {
            switch (option) {

                case "first":
                    entity = (company_id < 0) ? _query.getAllArtistForRecordLabel() : _query.getArtistForRecordLabel(company_id);
                    break;

                case "second":
                    entity = (company_id < 0) ? _query.getAllAlbumForRecordLabel() : _query.getAlbumForRecordLabel(company_id);
                    break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return entity;

    }

    /**
     * Detects query type and invokes appropriate method.
     * @param inRequest
     * @param outResponse
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    public void doPost(HttpServletRequest inRequest, HttpServletResponse outResponse)
            throws ServletException, IOException {

        try {
            PrintWriter out = outResponse.getWriter();
            String result_query = "";
            String form_name = inRequest.getParameter("form_name");
            String option = inRequest.getParameter("query");
            String result = (String) inRequest.getSession().getAttribute("answer_set");
            int value = (Integer) inRequest.getSession().getAttribute("value");

            switch (form_name) {
                case "artist_query":
                    result_query = printForArtist(value, option);
                    break;

                case "album_query":
                    result_query = printForAlbum(value, option);
                    break;

                case "song_query":
                    result_query = printForSong(value, option);
                    break;

                case "producer_query":
                    result_query = printForProducer(value, option);
                    break;

                case "composer_query":
                    result_query = printForComposer(value, option);
                    break;

                case "manager_query":
                    result_query = printForManager(value, option);
                    break;

                case "recordlabel_query":
                    result_query = printForRecordLabel(value, option);
                    break;
            }

                    RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/resultquery.jsp");
                    inRequest.setAttribute("result_set", result_query);
                    dispatcher.forward(inRequest, outResponse);

        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(QueryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Kills servlet.
     */
    @Override
    public void destroy() {
        _query.closeDBConnection();
    }
}
