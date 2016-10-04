<%-- 
    Document   : composer
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
        <form name="composer_query" id="query" action="Query" method="POST">
            <U>Composer Queries</U>   
            <BR>
            <input type="hidden" name="form_name" value="composer_query"/>

            <INPUT TYPE="radio" NAME="query" VALUE="first" CHECKED>
            List the Song(s) of the Composer(s)
            <BR>

            <INPUT TYPE="radio" NAME="query" VALUE="second">
            List the Artist(s) for which the Composer(s) have Composed Songs
            <BR>
            
            <INPUT TYPE="submit" VALUE="Submit">

            <%
                try
                {
                    String answer = (String) request.getAttribute("result");
                    session.setAttribute("answer_set", answer);
                    int val = (Integer) request.getAttribute("composer_val");
                    session.setAttribute("value", val);
                }
                catch (Exception e) {}

            %>

            ${result}
            
        </form>
    </body>
</html>