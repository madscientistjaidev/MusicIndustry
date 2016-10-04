<%-- 
    Document   : initial
    Created on : Jul 29, 2016, 3:22:06 PM
    Author     : Ankush Israney & Jaidev Ramakrishna
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Main Page</title>
    </head>
    <body style="width:720px;margin:auto;padding-top:50px">
        <fieldset><legend><h2>Choose Query Point</h2></legend>
        <fieldset style="background-color: lightblue"><FORM ACTION="entity_action.jsp" METHOD="post">
            <INPUT TYPE="radio" NAME="Entity_Selection" VALUE="RecordLabel" CHECKED>
            Record Labels
            <BR>
            <INPUT TYPE="radio" NAME="Entity_Selection" VALUE="Composers">
            Composers
            <BR>
            <INPUT TYPE="radio" NAME="Entity_Selection" VALUE="Artists">
            Artists
            <BR>
            <INPUT TYPE="radio" NAME="Entity_Selection" VALUE="Producers">
            Producers
            <BR>
            <INPUT TYPE="radio" NAME="Entity_Selection" VALUE="Managers">
            Managers
            <BR>
            <INPUT TYPE="radio" NAME="Entity_Selection" VALUE="Albums">
            Albums
            <BR>
            <INPUT TYPE="radio" NAME="Entity_Selection" VALUE="Songs">
            Songs
            <BR>
            <INPUT TYPE="radio" NAME="Entity_Selection" VALUE="Collaborations">
            Collaborations
            <BR>
            <INPUT TYPE="submit" VALUE="Submit">
        </FORM></fieldset></fieldset>
    </body>
</html>
