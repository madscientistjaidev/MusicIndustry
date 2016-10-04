<%-- 
    Document   : recordlabel
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
        <form name="song_query" id="query" action="Query" method="POST">
            <U>Song Queries</U>   
            <BR>
            <input type="hidden" name="form_name" value="song_query"/>

            <INPUT TYPE="radio" NAME="query" VALUE="first" CHECKED>
            List the Artist(s) for the Song(s)
            <BR>

            <INPUT TYPE="radio" NAME="query" VALUE="second">
            List the Collaboration(s) for the Song(s)
            <BR>

            <INPUT TYPE="radio" NAME="query" VALUE="third">
            Display the Album(s) for the Song(s)
            <BR>
            
            <INPUT TYPE="submit" VALUE="Submit">

            <%
                try
                {
                    String answer = (String) request.getAttribute("result");
                    session.setAttribute("answer_set", answer);
                    int val = (Integer) request.getAttribute("song_val");
                    session.setAttribute("value", val);
                }
                catch (Exception e) {}

            %>

            ${result}
            
        </form>
    </body>
</html>

