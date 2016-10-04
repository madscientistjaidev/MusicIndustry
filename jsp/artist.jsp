<%-- 
    Document   : artist
    Created on : Jul 30, 2016, 11:48:49 AM
    Author     : Ankush Israney & Jaidev Ramakrishna
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
    </head>
    <body>
        <form name="artist_query" id="query" action="Query" method="POST">
            <U>Artist Queries</U>   
            <BR>
            <input type="hidden" name="form_name" value="artist_query"/>

            <INPUT TYPE="radio" NAME="query" VALUE="first" CHECKED>
            List the Manager(s) of the Artist(s)
            <BR>

            <INPUT TYPE="radio" NAME="query" VALUE="second">
            List the Record Label(s) of the Artist(s)
            <BR>

            <INPUT TYPE="radio" NAME="query" VALUE="third">
            List the Song(s) of Artist(s)
            <BR>

            <INPUT TYPE="radio" NAME="query" VALUE="fourth">
            List the Album(s) of the Artist(s)
            <BR>

            <INPUT TYPE="radio" NAME="query" VALUE="fifth">
            List the Collaboration(s) of Artist(s) in various songs
            <BR>

            <INPUT TYPE="radio" NAME="query" VALUE="sixth">
            List the Producer(s) of the Artist(s)
            <BR>
            
            <INPUT TYPE="submit" VALUE="Submit">

            <%
                try
                {
                    String answer = (String) request.getAttribute("result");
                    session.setAttribute("answer_set", answer);
                    int val = (Integer) request.getAttribute("artist_val");
                    session.setAttribute("value", val);
                }
                catch (Exception e) {}

            %>

            ${result}
            
        </form>
    </body>
</html>
