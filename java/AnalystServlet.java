import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * Servlet for Analyst interface.
 * @author Ankush Israney
 * @author Jaidev RamaKrishna
 *
 */
public class AnalystServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private Analyst _analyst;
    private String _message;

    @Override
    public void init() throws ServletException {
        _analyst = new Analyst();
        _message = _analyst.openDBConnection("PgBundle");
    }

    /**
     * Prints all Artists.
     * @param out
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException 
     */
    public String printArtistList(PrintWriter out) throws IllegalArgumentException, IllegalAccessException {

        String result = "<h1>Artist List</h1>";
        result += "<table border=1 cellpadding= 3>";
        Artist artist = new Artist();

        result += artist.toHTMLHeader(artist) + "\n";

        try {
            ArrayList<Artist> artist_list = _analyst.getArtists();

            for (int i = 0; i < artist_list.size(); i++) {
                artist = (Artist) artist_list.get(i);
                result += artist.toHTML(artist) + "\n";
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace(out);
        }

        return result;
    }

    /**
     * Prints specified Artist.
     * @param out
     * @param id
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException 
     */
    public String printArtist(PrintWriter out, int id) throws IllegalArgumentException, IllegalAccessException {

        String result = "<h1>Artist Entity Card</h1>";
        result += "<table cellpadding= 3 border=1>";

        try {
            Artist artist = _analyst.getArtist(id);
            if(artist==null){
                result = "NO RECORD FOUND, PLEASE CHECK COMPLETE LIST";
                        }
            else
            {
            result += artist.toHTMLHeader(artist);
            result += artist.toHTML(artist);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace(out);
        }

        return result;

    }

    /**
     * Prints all Composers.
     * @param out
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException 
     */
    public String printComposerList(PrintWriter out) throws IllegalArgumentException, IllegalAccessException {

        String result = "<h1>Composer List</h1>";
        result += "<table cellpadding= 3 border=1>";
        Composer composer = new Composer();

        try {
            ArrayList<Composer> composer_list = _analyst.getComposers();
            result += composer.toHTMLHeader(composer);

            for (int i = 0; i < composer_list.size(); i++) {
                composer = (Composer) composer_list.get(i);
                result += composer.toHTML(composer) + "\n";
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace(out);
        }

        return result;

    }

    /**
     * Prints specified Composer.
     * @param out
     * @param id
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException 
     */
    public String printComposer(PrintWriter out, int id) throws IllegalArgumentException, IllegalAccessException {

        String result = "<h1>Composer Entity Card</h1>";
        result += "<table cellpadding= 3 border=1>";

        try {
            Composer composer = _analyst.getComposer(id);
             if(composer==null){
                result = "NO RECORD FOUND, PLEASE CHECK COMPLETE LIST";
                        }
             else
             {
            result += composer.toHTMLHeader(composer);
            result += composer.toHTML(composer);
             }
        } catch (SQLException sqle) {
            sqle.printStackTrace(out);
        }

        return result;
    }

    /**
     * Prints all Producers.
     * @param out
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException 
     */
    public String printProducerList(PrintWriter out) throws IllegalArgumentException, IllegalAccessException {

        String result = "<h1>Producer List</h1>";
        result += "<table cellpadding= 3 border=1>";
        Producer producer = new Producer();

        try {
            ArrayList<Producer> producer_list = _analyst.getProducers();
            result += producer.toHTMLHeader(producer);

            for (int i = 0; i < producer_list.size(); i++) {
                producer = (Producer) producer_list.get(i);
                result += producer.toHTML(producer) + "\n";
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace(out);
        }

        return result;

    }

    /**
     * Prints specified Producer.
     * @param out
     * @param id
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException 
     */
    public String printProducer(PrintWriter out, int id) throws IllegalArgumentException, IllegalAccessException {

        String result = "<h1>Producer Entity Card</h1>";
        result += "<table cellpadding= 3 border=1>";

        try {
            Producer producer = _analyst.getProducer(id);
             if(producer==null){
                result = "NO RECORD FOUND, PLEASE CHECK COMPLETE LIST";
                        }
             else
             {
            result += producer.toHTMLHeader(producer);
            result += producer.toHTML(producer);
             }
        } catch (SQLException sqle) {
            sqle.printStackTrace(out);
        }

        return result;

    }

    /**
     * Prints all Managers.
     * @param out
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException 
     */
    public String printManagerList(PrintWriter out) throws IllegalArgumentException, IllegalAccessException {

        String result = "<h1>Manager List</h1>";
        result += "<table cellpadding= 3 border=1>";
        Manager manager = new Manager();

        try {
            ArrayList<Manager> manager_list = _analyst.getManagers();
            result += manager.toHTMLHeader(manager);

            for (int i = 0; i < manager_list.size(); i++) {
                manager = (Manager) manager_list.get(i);
                result += manager.toHTML(manager) + "\n";
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace(out);
        }

        return result;
    }

    /**
     * Prints specified Manager.
     * @param out
     * @param id
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException 
     */
    public String printManager(PrintWriter out, int id) throws IllegalArgumentException, IllegalAccessException {

        String result = "<h1>Manager Entity Card</h1>";
        result += "<table cellpadding= 3 border=1>";

        try {
            
            Manager manager = _analyst.getManager(id);
             if(manager==null){
                result = "NO RECORD FOUND, PLEASE CHECK COMPLETE LIST";
                        }
             else{
            result += manager.toHTMLHeader(manager);
            result += manager.toHTML(manager);
             }
        } catch (SQLException sqle) {
            sqle.printStackTrace(out);
        }

        return result;
    }

    /**
     * Prints all Record Labels.
     * @param out
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException 
     */
    public String printRecordLabelList(PrintWriter out) throws IllegalArgumentException, IllegalAccessException {

        String result = "<h1>RecordLabel List</h1>";
        result += "<table cellpadding= 3 border=1>";
        RecordLabel record = new RecordLabel();

        try {
            ArrayList<RecordLabel> record_list = _analyst.getRecordLabels();
            result += record.toHTMLHeader(record);

            for (int i = 0; i < record_list.size(); i++) {
                record = (RecordLabel) record_list.get(i);
                result += record.toHTML(record) + "\n";
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace(out);
        }

        return result;
    }

    /**
     * Prints specified Record Label.
     * @param out
     * @param id
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException 
     */
    public String printRecordLabel(PrintWriter out, String id) throws IllegalArgumentException, IllegalAccessException {

        String result = "<h1>RecordLabel Entity Card</h1>";
        result += "<table cellpadding= 3 border=1>";

        try {
            RecordLabel record = _analyst.getRecordLabel(id);
             if(record==null){
                result = "NO RECORD FOUND, PLEASE CHECK COMPLETE LIST";
                        }
             else{
            result += record.toHTMLHeader(record);
            result += record.toHTML(record);
             }
        } catch (SQLException sqle) {
            sqle.printStackTrace(out);
        }
        return result;
    }

    /**
     * Prints all Albums.
     * @param out
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException 
     */
    public String printAlbumList(PrintWriter out) throws IllegalArgumentException, IllegalAccessException {

        String result = "<h1>Album List</h1>";
        result += "<table cellpadding= 3 border=1>";
        Album album = new Album();

        try {
            ArrayList<Album> album_list = _analyst.getAlbums();
            result += album.toHTMLHeader(album);

            for (int i = 0; i < album_list.size(); i++) {
                album = (Album) album_list.get(i);
                result += album.toHTML(album);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace(out);
        }

        return result;
    }

    /**
     * Prints specified Album.
     * @param out
     * @param id
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException 
     */
    public String printAlbum(PrintWriter out, int id) throws IllegalArgumentException, IllegalAccessException {

        String result = "<h1>Album Entity Card</h1>";
        result += "<table cellpadding= 3 border=1>";

        try {
            Album album = _analyst.getAlbum(id);
             if(album==null){
                result = "NO RECORD FOUND, PLEASE CHECK COMPLETE LIST";
                        }
             else{
            result += album.toHTMLHeader(album);
            result += album.toHTML(album);
             }
        } catch (SQLException sqle) {
            sqle.printStackTrace(out);
        }

        return result;
    }

    /**
     * Prints all Songs.
     * @param out
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException 
     */
    public String printSongList(PrintWriter out) throws IllegalArgumentException, IllegalAccessException {

        String result = "<h1>Song List</h1>";
        result += "<table cellpadding= 3 border=1>";
        Song song = new Song();

        try {
            ArrayList<Song> song_list = _analyst.getSongs();
            result += song.toHTMLHeader(song);

            for (int i = 0; i < song_list.size(); i++) {
                song = (Song) song_list.get(i);
                result += song.toHTML(song);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace(out);
        }

        return result;
    }

    /**
     * Prints specified Song.
     * @param out
     * @param id
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException 
     */
    public String printSong(PrintWriter out, int id) throws IllegalArgumentException, IllegalAccessException {

        String result = "<h1>Song Entity Card</h1>";
        result += "<table cellpadding= 3 border=1>";

        try {
            Song song = _analyst.getSong(id);
             if(song==null){
                result = "NO RECORD FOUND, PLEASE CHECK COMPLETE LIST";
                        }
             else{
            result += song.toHTMLHeader(song);
            result += song.toHTML(song);
             }
        } catch (SQLException sqle) {
            sqle.printStackTrace(out);
        }

        return result;
    }

    /**
     * Prints all Collaborations.
     * @param out
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException 
     */
    public String printCollaborationList(PrintWriter out) throws IllegalArgumentException, IllegalAccessException {

        String result = "<h1>Collaboration List</h1>";
        result += "<table cellpadding= 3 border=1>";
        Collaboration collaboration = new Collaboration();

        try {
            ArrayList<Collaboration> collaboration_list = _analyst.getCollaborations();
            result += collaboration.toHTMLHeader(collaboration);

            for (int i = 0; i < collaboration_list.size(); i++) {
                collaboration = (Collaboration) collaboration_list.get(i);
                result += collaboration.toHTML(collaboration) + "\n";
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace(out);
        }

        return result;
    }

    /**
     * Prints specified Collaboration.
     * @param out
     * @param id
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException 
     */
    public String printCollaboration(PrintWriter out, int id) throws IllegalArgumentException, IllegalAccessException {

        String result = "<h1>Collaboration Entity Card</h1>";
        result += "<table cellpadding= 3 border=1>";

        try {
            Collaboration collaboration = _analyst.getCollaboration(id);
             if(collaboration==null){
                result = "NO RECORD FOUND, PLEASE CHECK COMPLETE LIST";
                        }
             else{
            result += collaboration.toHTMLHeader(collaboration);
            result += collaboration.toHTML(collaboration);
             }
        } catch (SQLException sqle) {
            sqle.printStackTrace(out);
        }

        return result;
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
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/initial.jsp");
            dispatcher.forward(request, response);

        }

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

        PrintWriter out = outResponse.getWriter();
        String option = (String) inRequest.getSession(false).getAttribute("option");
        String selection = inRequest.getParameter("Value_Selection");
        String value = inRequest.getParameter("Value_Single");
        String result = null;
        String error = "NO RECORD FOUND, PLEASE CHECK COMPLETE LIST";
        

        if (option.matches("Artist")) {
            if (selection.matches("Single")) {
                try {
                    result = printArtist(out, Integer.parseInt(value));
                    if(result!=error){
                    RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/artist.jsp");
                    inRequest.setAttribute("result", result);
                    int artist_val = Integer.parseInt(value);
                    inRequest.setAttribute("artist_val", artist_val);
                    dispatcher.forward(inRequest, outResponse);
                    }
                    else
                        out.println(error);
                } catch (IllegalArgumentException | IllegalAccessException ex) {
                    Logger.getLogger(AnalystServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {

                    result = printArtistList(out);
                    RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/artist.jsp");
                    inRequest.setAttribute("result", result);
                    int artist_val = -1;
                    inRequest.setAttribute("artist_val", artist_val);
                    dispatcher.forward(inRequest, outResponse);

                } catch (IllegalArgumentException | IllegalAccessException ex) {
                    Logger.getLogger(AnalystServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else if (option.matches("Composer")) {
            if (selection.matches("Single")) {
                try {
                    
                    result = printComposer(out, Integer.parseInt(value));
                    if(result!=error){
                    RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/composer.jsp");
                    inRequest.setAttribute("result", result);
                    int composer_val = Integer.parseInt(value);
                    inRequest.setAttribute("composer_val", composer_val);
                    dispatcher.forward(inRequest, outResponse);
                    }
                    else
                        out.println(error);
                } catch (IllegalArgumentException | IllegalAccessException ex) {
                    Logger.getLogger(AnalystServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                  
                    result = printComposerList(out);
                    RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/composer.jsp");
                    inRequest.setAttribute("result", result);
                    int composer_val = -1;
                    inRequest.setAttribute("composer_val", composer_val);
                    dispatcher.forward(inRequest, outResponse);
                 
                   
                } catch (IllegalArgumentException | IllegalAccessException ex) {
                    Logger.getLogger(AnalystServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else if (option.matches("Producer")) {
            if (selection.matches("Single")) {
                try {
                    
                    result = printProducer(out, Integer.parseInt(value));
                    if(result!=error){
                    RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/producer.jsp");
                    inRequest.setAttribute("result", result);
                    int producer_val = Integer.parseInt(value);
                    inRequest.setAttribute("producer_val", producer_val);
                    dispatcher.forward(inRequest, outResponse);
                    }
                    else
                        out.println(error);
                } catch (IllegalArgumentException | IllegalAccessException ex) {
                    Logger.getLogger(AnalystServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    result = printProducerList(out);
                    RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/producer.jsp");
                    inRequest.setAttribute("result", result);
                    int producer_val = -1;
                    inRequest.setAttribute("producer_val", producer_val);
                    dispatcher.forward(inRequest, outResponse);
                } catch (IllegalArgumentException | IllegalAccessException ex) {
                    Logger.getLogger(AnalystServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else if (option.matches("Manager")) {
            if (selection.matches("Single")) {
                try {
                    
                    result = printManager(out, Integer.parseInt(value));
                     if(result!=error){
                    RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/manager.jsp");
                    inRequest.setAttribute("result", result);
                    int manager_val = Integer.parseInt(value);
                    inRequest.setAttribute("manager_val", manager_val);
                    dispatcher.forward(inRequest, outResponse);
                     }
                      else
                        out.println(error);
                } catch (IllegalArgumentException | IllegalAccessException ex) {
                    Logger.getLogger(AnalystServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    
                    result = printManagerList(out);
                    RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/manager.jsp");
                    inRequest.setAttribute("result", result);
                    int manager_val = -1;
                    inRequest.setAttribute("manager_val", manager_val);
                    dispatcher.forward(inRequest, outResponse);
                } catch (IllegalArgumentException | IllegalAccessException ex) {
                    Logger.getLogger(AnalystServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else if (option.matches("RecordLabel")) {
            if (selection.matches("Single")) {
                try {
                    
                    result = printRecordLabel(out, value);
                     if(result!=error){
                    RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/recordlabel.jsp");
                    inRequest.setAttribute("result", result);
                    int record_label_val = Integer.parseInt(value);
                    inRequest.setAttribute("record_label_val", record_label_val);
                    dispatcher.forward(inRequest, outResponse);
                     }
                      else
                        out.println(error);
                } catch (IllegalArgumentException | IllegalAccessException ex) {
                    Logger.getLogger(AnalystServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    result = printRecordLabelList(out);
                    RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/recordlabel.jsp");
                    inRequest.setAttribute("result", result);
                    int record_label_val = -1;
                    inRequest.setAttribute("record_label_val", record_label_val);
                    dispatcher.forward(inRequest, outResponse);
                } catch (IllegalArgumentException | IllegalAccessException ex) {
                    Logger.getLogger(AnalystServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else if (option.matches("Album")) {
            if (selection.matches("Single")) {
                try {
                    
                    result = printAlbum(out, Integer.parseInt(value));
                     if(result!=error){
                    RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/album.jsp");
                    inRequest.setAttribute("result", result);
                    int album_val = Integer.parseInt(value);
                    inRequest.setAttribute("album_val", album_val);
                    dispatcher.forward(inRequest, outResponse);
                     }
                     else
                        out.println(error);
                     
                } catch (IllegalArgumentException | IllegalAccessException ex) {
                    Logger.getLogger(AnalystServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    
                    result = printAlbumList(out);
                    RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/album.jsp");
                    inRequest.setAttribute("result", result);
                    int album_val = -1;
                    inRequest.setAttribute("album_val", album_val);
                    dispatcher.forward(inRequest, outResponse);
                    
                    
                } catch (IllegalArgumentException | IllegalAccessException ex) {
                    Logger.getLogger(AnalystServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else if (option.matches("Song")) {
            if (selection.matches("Single")) {
                try {
                    
                    result = printSong(out, Integer.parseInt(value));
                     if(result!=error){
                    RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/song.jsp");
                    inRequest.setAttribute("result", result);
                    int song_val = Integer.parseInt(value);
                    inRequest.setAttribute("song_val", song_val);
                    dispatcher.forward(inRequest, outResponse);
                     }
                     else
                         out.println(error);
                } catch (IllegalArgumentException | IllegalAccessException ex) {
                    Logger.getLogger(AnalystServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    result = printSongList(out);
                    RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/song.jsp");
                    inRequest.setAttribute("result", result);
                    int song_val = -1;
                    inRequest.setAttribute("song_val", song_val);
                    dispatcher.forward(inRequest, outResponse);
                } catch (IllegalArgumentException | IllegalAccessException ex) {
                    Logger.getLogger(AnalystServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else if (option.matches("Collaboration")) {
            if (selection.matches("Single")) {
                try {
                    
                    result = printCollaboration(out, Integer.parseInt(value));
                    if(result!=error){
                    RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/collaborations.jsp");
                    inRequest.setAttribute("result", result);
                    int collaboration_val = Integer.parseInt(value);
                    inRequest.setAttribute("collaboration_val", collaboration_val);
                    dispatcher.forward(inRequest, outResponse);
                    }
                    else
                         out.println(error+": Possible Mistake - Search by Artist ID here");
                    
                } catch (IllegalArgumentException | IllegalAccessException ex) {
                    Logger.getLogger(AnalystServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    result = printCollaborationList(out);
                    RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/collaborations.jsp");
                    inRequest.setAttribute("result", result);
                    int collaboration_val = -1;
                    inRequest.setAttribute("collaboration_val", collaboration_val);
                    dispatcher.forward(inRequest, outResponse);
                } catch (IllegalArgumentException | IllegalAccessException ex) {
                    Logger.getLogger(AnalystServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    /**
     * Kills servlet.
     */
    @Override
    public void destroy() {
        _analyst.closeDBConnection();
    }
}
