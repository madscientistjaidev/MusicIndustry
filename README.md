# MusicIndustry
Online portal and query system for business view of music industry

# Project Description
* This application provides a high level view of the music industry from the business side. It contains metadata about musical aspects.
* The entities and relationships, and the queries that the application is capable of running, have been formulated so that one may examine the business side of the music industry. 
* One can use this type of system to perform analytics, mine information and query facts pertaining to the functioning of the industry.

# System Design
* This application can be run in GlassFish or Apache Tomcat. We have provided java and jsp source code files in two folders.
* The front end is JSP files that dynamically generate HTML data to display. 
* The back end is written in Java. It consists of 

  1. An Analyst class and its associated servlet for generating the interface.
  2. A Query class and its associated servlet for building and running SQL queries.
  3. One class for each table in our database, each of which contains entity-specific methods.
  
# Requirements 
* This application can be run in GlassFish or Apache Tomcat. The code requires at least Java 7 to compile and run. It will fail to compile on previous versions.  
* The schema.sql contains all instructions needed to recreate our database. It must be run manually before running the application. You must also put database login credentials in the PgBundle file.

# User Interface
* We have tested our application on multiple web browsers on various operating systems. 
* The content and layout seem to be reproduced without any glitches even on phone browsers, but it is best viewed on Chrome in 1920x1080 resolution.
