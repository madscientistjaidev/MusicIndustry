<%-- 
    Document   : entity_action
    Created on : Jul 30, 2016, 11:49:49 AM
    Author     : Ankush Israney & Jaidev Ramakrishna
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<HTML>
    <script type="text/javascript">
        function isValid()
        {
            if (document.getElementById('Single_Radio').checked) {
                if (document.getElementById('Single_Text').value == '') {
                    alert('Text Field Missing For Selection');
                    return false;
                }
            } else if (!document.getElementById('Single_Radio').checked && !document.getElementById('Multi_Radio').checked) {
                alert('Select  1 choice to proceed');
                return false;
            }

            return true;
        }

    </script>
        <TITLE>Analysis</TITLE>
    </HEAD>

    <BODY>
        
        <FORM action="Analyst" Method="POST" onsubmit ="return isValid()">
            <%
                try
                {
                    String entity_option = new String();

                    if (request.getParameter("Entity_Selection") != null) {
                        if (request.getParameter("Entity_Selection").equals("RecordLabel")) {
                            entity_option = "RecordLabel";
                            out.println("<H2>Record Label Analysis</H2>");
                        } else if (request.getParameter("Entity_Selection").equals("Composers")) {
                            out.println("<H2>Composer Analysis</H2>");
                            entity_option = "Composer";
                        } else if (request.getParameter("Entity_Selection").equals("Artists")) {
                            out.println("<H2>Artist Analysis</H2>");
                            entity_option = "Artist";
                        } else if (request.getParameter("Entity_Selection").equals("Producers")) {
                            out.println("<H2>Producer Analysis</H2>");
                            entity_option = "Producer";
                        } else if (request.getParameter("Entity_Selection").equals("Managers")) {
                            out.println("<H2>Manager Analysis</H2>");
                            entity_option = "Manager";
                        } else if (request.getParameter("Entity_Selection").equals("Albums")) {
                            out.println("<H2>Album Analysis</H2>");
                            entity_option = "Album";
                        } else if (request.getParameter("Entity_Selection").equals("Songs")) {
                            out.println("<H2>Song Analysis</H2>");
                            entity_option = "Song";
                        } else if (request.getParameter("Entity_Selection").equals("Collaborations")) {
                            out.println("<H2>Collaboration Analysis</H2>");
                            entity_option = "Collaboration";
                        }

                        session.setAttribute("option", entity_option);
                    }
                }
                catch (Exception err) {out.println("Exception");}
            %>

            <INPUT TYPE="radio" NAME="Value_Selection" ID="Single_Radio" VALUE="Single" CHECKED>
            Search by ID
            <INPUT TYPE="text" ID="Single_Text" NAME="Value_Single">
            <BR>
            <INPUT TYPE="radio" ID="Multi_Radio" NAME="Value_Selection" VALUE="List all">
            List all
            <BR>

            <INPUT TYPE="submit" VALUE="Submit">
        </FORM>
            
    <fieldset style="background-color: lightblue; width:100px"><A HREF="Analyst">Reset</A></fieldset>
    </BODY>
</HTML>
