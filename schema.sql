--Author: Ankush Vijay Israney, Jaidev Ramarkishna

--ALL DROP TABLE STATEMENTS
drop table Collaborates;
drop table Song;
drop table Album;
drop table Composer;
drop table Artist;
drop table Producer;
drop table Manager;
drop table Record_Label;

--ALL CREATE TABLE STATEMENTS

--Record Label Entity
create table Record_Label(
    company_id varchar(32) Primary key ,
            record_label_name varchar(32) not null,
            revenue integer,
            address varchar(64)        
);

--Manager Entity
create table Manager(
    ssn integer Primary key,
            manager_name varchar(32) not null,
            DOB date
);

--Producer Entity 
create table Producer(
    ssn integer Primary Key,
            producer_name varchar(32) not null,
            DOB date
);

--Composer Entity
create table Composer(
    ssn integer Primary key ,
            composer_name varchar(32) not null,
            DOB date
);

--Artist Entity
create table Artist(
    artist_id integer Primary Key,
            artist_name varchar(32) not null,
            DOB date,
            country varchar(32),
            percentage_cut_manager integer not null,
            manager_since date not null,
            manager_ssn integer not null,
            company_id varchar(32) not null,
            contract_amount integer not null,
            contract_date date not null,
            Foreign Key (manager_ssn) references Manager(ssn),
            Foreign Key (company_id) references Record_Label(company_id)
            );

--Album Entity
create table Album(
   album_id integer Primary Key,
            title varchar(64) not null,
            track_count integer,
            album_length integer,
            genre varchar(32),
            company_id varchar(32) not null,
            producer_ssn integer not null,
            studio varchar(32) not null,
            format varchar(32),
            price integer,
            release_date date,
            medium varchar(32),
            sales integer,
            production_cost integer not null,
            Foreign Key (producer_ssn) references Producer(ssn),
            Foreign Key (company_id) references Record_Label(company_id)            
);

--Song Entity
create table Song(
    track_id integer Primary Key,
            tempo integer,
            song_length integer,
            title varchar(64) not null,
            song_key varchar(2),
            song_language varchar(32),
            artist_role varchar(32) not null,
            artist_id integer not null,
            album_id integer not null,
            composer_ssn integer,
            Foreign Key (artist_id) references Artist(artist_id),
            Foreign Key (composer_ssn) references Composer(ssn),
            Foreign Key (album_id) references Album(album_id)
            
);

--Collaborates Relation
create table Collaborates(
            track_id integer,
            artist_id integer,
            artist_role varchar(32),
    Primary Key (track_id, artist_id), 
    Foreign Key (artist_id) references Artist(artist_id),
    Foreign Key (track_id) references Song(track_id) 
            );

--ALL INSERT STATEMENTS

INSERT INTO Record_Label (company_id,record_label_name,revenue,address) VALUES ('1009','Roadrunner','1000000','Netherlands');
INSERT INTO Record_Label (company_id,record_label_name,revenue,address) VALUES ('1010','Nuclear Blast','2000000','Germany');
INSERT INTO Record_Label (company_id,record_label_name,revenue,address) VALUES ('1011','Warner Music','3000000','USA');
INSERT INTO Record_Label (company_id,record_label_name,revenue,address) VALUES ('2014','Sony','4000000','Japan');
INSERT INTO Record_Label (company_id,record_label_name,revenue,address) VALUES ('2015','Universal','5000000','USA');
INSERT INTO Record_Label (company_id,record_label_name,revenue,address) VALUES ('2016','Island','1500000','UK');
INSERT INTO Record_Label (company_id,record_label_name,revenue,address) VALUES ('2017','Phonogram','2500000','UK');
INSERT INTO Record_Label (company_id,record_label_name,revenue,address) VALUES ('2018','Atlantic','3500000','USA');
INSERT INTO Record_Label (company_id,record_label_name,revenue,address) VALUES ('2019','A&M','4500000','USA');
INSERT INTO Record_Label (company_id,record_label_name,revenue,address) VALUES ('2020','Polygram','5500000','Netherlands');


INSERT INTO Manager (ssn,manager_name,DOB) VALUES ('1001','Peter Mensch','1/10/1950');
INSERT INTO Manager (ssn,manager_name,DOB) VALUES ('1002','Cliff Burnstein','2/20/1960');
INSERT INTO Manager (ssn,manager_name,DOB) VALUES ('1003','Andrew Farrow','3/30/1970');
INSERT INTO Manager (ssn,manager_name,DOB) VALUES ('2001','Lou Pearlman','4/4/1955');
INSERT INTO Manager (ssn,manager_name,DOB) VALUES ('2003','Allen Klein','5/5/1965');
INSERT INTO Manager (ssn,manager_name,DOB) VALUES ('2004','Brian Epstein','6/6/1975');
INSERT INTO Manager (ssn,manager_name,DOB) VALUES ('2005','Harvey Lisberg','7/7/1985');
INSERT INTO Manager (ssn,manager_name,DOB) VALUES ('2006','Bill Aucoin','8/8/1945');
INSERT INTO Manager (ssn,manager_name,DOB) VALUES ('2007','Rod Smallwood','9/9/1940');
INSERT INTO Manager (ssn,manager_name,DOB) VALUES ('2008','Brian Slagel','10/10/1960');


INSERT INTO Producer (ssn,producer_name,DOB) VALUES ('1004','Chris Tsangarides','4/5/1950');
INSERT INTO Producer (ssn,producer_name,DOB) VALUES ('1005','Bob Rock','5/6/1960');
INSERT INTO Producer (ssn,producer_name,DOB) VALUES ('1006','Desmond Child','6/7/1970');
INSERT INTO Producer (ssn,producer_name,DOB) VALUES ('1007','Rick Rubin','7/8/1940');
INSERT INTO Producer (ssn,producer_name,DOB) VALUES ('1008','Steven Wilson','8/9/1980');
INSERT INTO Producer (ssn,producer_name,DOB) VALUES ('2009','Greg Fidelman','1/1/1975');
INSERT INTO Producer (ssn,producer_name,DOB) VALUES ('2010','Andy Sneap','2/2/1965');
INSERT INTO Producer (ssn,producer_name,DOB) VALUES ('2011','Alan Parsons','3/3/1955');
INSERT INTO Producer (ssn,producer_name,DOB) VALUES ('2012','Misha Mansoor','4/4/1985');
INSERT INTO Producer (ssn,producer_name,DOB) VALUES ('2013','George Martin','5/5/1945');


INSERT INTO Composer (ssn,composer_name,DOB) VALUES ('1012','Glenn Tipton','5/6/1955');
INSERT INTO Composer (ssn,composer_name,DOB) VALUES ('1013','Lemmy','3/4/1945');
INSERT INTO Composer (ssn,composer_name,DOB) VALUES ('1014','Steve Harris','4/5/1950');
INSERT INTO Composer (ssn,composer_name,DOB) VALUES ('1015','Jimmy Page','1/2/1935');
INSERT INTO Composer (ssn,composer_name,DOB) VALUES ('1016','Ritchie Blackmore','2/3/1940');
INSERT INTO Composer (ssn,composer_name,DOB) VALUES ('2021','Max Martin','9/8/1980');
INSERT INTO Composer (ssn,composer_name,DOB) VALUES ('2022','Bernie Taupin','8/7/1975');
INSERT INTO Composer (ssn,composer_name,DOB) VALUES ('2023','Jake Lee','7/6/1970');
INSERT INTO Composer (ssn,composer_name,DOB) VALUES ('2024','Vivian Campbell','6/5/1965');
INSERT INTO Composer (ssn,composer_name,DOB) VALUES ('2025','Paul Simon','5/4/1960');

INSERT INTO Artist (artist_id,artist_name,DOB,country,percentage_cut_manager,manager_since,manager_ssn,company_id,contract_amount,contract_date) VALUES ('1017','Metallica','1/1/1979','USA','5','9/27/1981','1001','2020','100000000','6/23/1984');
INSERT INTO Artist (artist_id,artist_name,DOB,country,percentage_cut_manager,manager_since,manager_ssn,company_id,contract_amount,contract_date) VALUES ('1018','RHCP','2/2/1982','USA','10','6/17/1983','1002','2019','95000000','3/13/1986');
INSERT INTO Artist (artist_id,artist_name,DOB,country,percentage_cut_manager,manager_since,manager_ssn,company_id,contract_amount,contract_date) VALUES ('1019','Dream Theater','3/3/1990','USA','15','7/16/1991','1003','2018','90000000','4/11/1994');
INSERT INTO Artist (artist_id,artist_name,DOB,country,percentage_cut_manager,manager_since,manager_ssn,company_id,contract_amount,contract_date) VALUES ('1020','Scorpions','4/4/1973','Germany','20','8/17/1974','2001','2017','85000000','5/13/1977');
INSERT INTO Artist (artist_id,artist_name,DOB,country,percentage_cut_manager,manager_since,manager_ssn,company_id,contract_amount,contract_date) VALUES ('1021','Bon Jovi','6/5/1978','USA','25','3/1/1981','2003','2016','80000000','7/14/1982');
INSERT INTO Artist (artist_id,artist_name,DOB,country,percentage_cut_manager,manager_since,manager_ssn,company_id,contract_amount,contract_date) VALUES ('1022','Judas Priest','6/6/1968','UK','30','3/3/1971','2004','2015','75000000','7/15/1972');
INSERT INTO Artist (artist_id,artist_name,DOB,country,percentage_cut_manager,manager_since,manager_ssn,company_id,contract_amount,contract_date) VALUES ('1023','Opeth','7/7/1992','Sweden','35','4/3/1995','2005','2014','70000000','8/15/1996');
INSERT INTO Artist (artist_id,artist_name,DOB,country,percentage_cut_manager,manager_since,manager_ssn,company_id,contract_amount,contract_date) VALUES ('1024','Meshuggah','8/8/1995','Sweden','40','5/4/1998','2006','1011','65000000','1/28/2001');
INSERT INTO Artist (artist_id,artist_name,DOB,country,percentage_cut_manager,manager_since,manager_ssn,company_id,contract_amount,contract_date) VALUES ('1025','Slayer','9/9/1983','USA','45','6/5/1986','2007','1010','60000000','3/1/1989');
INSERT INTO Artist (artist_id,artist_name,DOB,country,percentage_cut_manager,manager_since,manager_ssn,company_id,contract_amount,contract_date) VALUES ('1026','Slipknot','10/10/2000','USA','50','7/7/2003','2008','1009','55000000','4/2/2006');
INSERT INTO Artist (artist_id,artist_name,DOB,country,percentage_cut_manager,manager_since,manager_ssn,company_id,contract_amount,contract_date) VALUES ('1027','Porcupine Tree','11/11/1994','UK','47','8/7/1997','2007','2020','50000000','5/3/2000');
INSERT INTO Artist (artist_id,artist_name,DOB,country,percentage_cut_manager,manager_since,manager_ssn,company_id,contract_amount,contract_date) VALUES ('1028','Queensryche','12/12/1984','USA','42','9/8/1987','2006','2019','45000000','6/4/1990');
INSERT INTO Artist (artist_id,artist_name,DOB,country,percentage_cut_manager,manager_since,manager_ssn,company_id,contract_amount,contract_date) VALUES ('1029','Fates Warning','12/12/1976','USA','37','12/12/1967','2005','2018','40000000','12/12/1976');
INSERT INTO Artist (artist_id,artist_name,DOB,country,percentage_cut_manager,manager_since,manager_ssn,company_id,contract_amount,contract_date) VALUES ('1030','Linkin Park','8/2/2002','USA','32','4/28/2005','2004','2017','35000000','1/23/2008');
INSERT INTO Artist (artist_id,artist_name,DOB,country,percentage_cut_manager,manager_since,manager_ssn,company_id,contract_amount,contract_date) VALUES ('1031','Megadeth','9/8/1980','USA','27','6/5/1983','2003','2016','30000000','3/1/1986');


INSERT INTO Album (album_id,title,track_count,album_length,genre,company_id,producer_ssn,studio,format,price,release_date,medium,sales,production_cost) VALUES ('1032','Ride The Lightening','5','4400','Thrash','1009','1004','Sound City','Analog','50','6/23/1984','Vinyl','25000000','2250000');
INSERT INTO Album (album_id,title,track_count,album_length,genre,company_id,producer_ssn,studio,format,price,release_date,medium,sales,production_cost) VALUES ('1033','Master Of Puppets','4','2340','Thrash','1010','1005','Sweet Silence','Digital','45','3/20/1987','Tape','22500000','2000000');
INSERT INTO Album (album_id,title,track_count,album_length,genre,company_id,producer_ssn,studio,format,price,release_date,medium,sales,production_cost) VALUES ('1034','And Justice For All','3','4360','Thrash','1011','1006','HQ','Analog','40','12/14/1989','CD','20000000','1750000');
INSERT INTO Album (album_id,title,track_count,album_length,genre,company_id,producer_ssn,studio,format,price,release_date,medium,sales,production_cost) VALUES ('1035','Californication','5','4340','Rock','2014','1007','Spectre Sound','Digital','35','3/13/1986','Internet','17500000','1500000');
INSERT INTO Album (album_id,title,track_count,album_length,genre,company_id,producer_ssn,studio,format,price,release_date,medium,sales,production_cost) VALUES ('1036','Stadium Arcadium','4','2280','Rock','2015','1008','Columbia','Analog','30','12/7/1988','Vinyl','15000000','1250000');
INSERT INTO Album (album_id,title,track_count,album_length,genre,company_id,producer_ssn,studio,format,price,release_date,medium,sales,production_cost) VALUES ('1037','Images And Words','3','2280','Progressive','2016','1004','Abbey Road','Digital','25','4/11/1994','Tape','12500000','1000000');
INSERT INTO Album (album_id,title,track_count,album_length,genre,company_id,producer_ssn,studio,format,price,release_date,medium,sales,production_cost) VALUES ('1038','Scenes From A Memory','5','4300','Progressive','2017','1005','Gold Star','Analog','20','1/5/1997','CD','10000000','750000');
INSERT INTO Album (album_id,title,track_count,album_length,genre,company_id,producer_ssn,studio,format,price,release_date,medium,sales,production_cost) VALUES ('1039','A Change Of Seasons','4','4280','Progressive','2018','1006','Sound City','Digital','15','10/2/1999','Internet','7500000','500000');
INSERT INTO Album (album_id,title,track_count,album_length,genre,company_id,producer_ssn,studio,format,price,release_date,medium,sales,production_cost) VALUES ('1040','Love At First Sting','3','2220','Rock','2019','1007','Sweet Silence','Analog','10','5/13/1977','Vinyl','5000000','250000');
INSERT INTO Album (album_id,title,track_count,album_length,genre,company_id,producer_ssn,studio,format,price,release_date,medium,sales,production_cost) VALUES ('1041','Crazy World','5','2220','Rock','2020','1008','HQ','Digital','15','2/7/1980','Tape','2500000','260000');
INSERT INTO Album (album_id,title,track_count,album_length,genre,company_id,producer_ssn,studio,format,price,release_date,medium,sales,production_cost) VALUES ('1042','Crush','4','4240','Pop','1009','1004','Spectre Sound','Analog','20','7/14/1982','CD','2600000','270000');
INSERT INTO Album (album_id,title,track_count,album_length,genre,company_id,producer_ssn,studio,format,price,release_date,medium,sales,production_cost) VALUES ('1043','Slippery When Wet','3','4220','Pop','1010','1005','Columbia','Digital','25','4/9/1985','Internet','2700000','280000');
INSERT INTO Album (album_id,title,track_count,album_length,genre,company_id,producer_ssn,studio,format,price,release_date,medium,sales,production_cost) VALUES ('1044','Painkiller','5','2160','Metal','1011','1006','Abbey Road','Analog','30','7/15/1972','Vinyl','2800000','290000');
INSERT INTO Album (album_id,title,track_count,album_length,genre,company_id,producer_ssn,studio,format,price,release_date,medium,sales,production_cost) VALUES ('1045','Defenders Of The Faith','4','2160','Metal','2014','1007','Gold Star','Digital','35','4/11/1975','Tape','2900000','300000');
INSERT INTO Album (album_id,title,track_count,album_length,genre,company_id,producer_ssn,studio,format,price,release_date,medium,sales,production_cost) VALUES ('1046','Screaming For Vengeance','3','4180','Metal','2015','1008','Sound City','Analog','40','1/5/1978','CD','3000000','310000');
INSERT INTO Album (album_id,title,track_count,album_length,genre,company_id,producer_ssn,studio,format,price,release_date,medium,sales,production_cost) VALUES ('1047','Damnation','5','4160','Death','2016','1004','Sweet Silence','Digital','45','8/15/1996','Internet','3100000','320000');
INSERT INTO Album (album_id,title,track_count,album_length,genre,company_id,producer_ssn,studio,format,price,release_date,medium,sales,production_cost) VALUES ('1048','Deliverence','4','2100','Death','2017','1005','HQ','Analog','50','5/12/1999','Vinyl','3200000','330000');
INSERT INTO Album (album_id,title,track_count,album_length,genre,company_id,producer_ssn,studio,format,price,release_date,medium,sales,production_cost) VALUES ('1049','Blackwater Park','3','2100','Death','2018','1006','Spectre Sound','Digital','45','2/5/2002','Tape','3300000','340000');
INSERT INTO Album (album_id,title,track_count,album_length,genre,company_id,producer_ssn,studio,format,price,release_date,medium,sales,production_cost) VALUES ('1050','Nothing','5','4120','Death','2019','1007','Columbia','Analog','40','1/28/2001','CD','3400000','350000');
INSERT INTO Album (album_id,title,track_count,album_length,genre,company_id,producer_ssn,studio,format,price,release_date,medium,sales,production_cost) VALUES ('1051','Catch 33','4','4100','Death','2020','1008','Abbey Road','Digital','35','10/25/2003','Internet','3500000','360000');
INSERT INTO Album (album_id,title,track_count,album_length,genre,company_id,producer_ssn,studio,format,price,release_date,medium,sales,production_cost) VALUES ('1052','Seasons In The Abyss','3','2040','Thrash','1009','1004','Gold Star','Analog','30','3/1/1989','Vinyl','3600000','370000');
INSERT INTO Album (album_id,title,track_count,album_length,genre,company_id,producer_ssn,studio,format,price,release_date,medium,sales,production_cost) VALUES ('1053','Reign In Blood','5','2040','Thrash','1010','1005','Sound City','Digital','25','11/26/1991','Tape','3700000','380000');
INSERT INTO Album (album_id,title,track_count,album_length,genre,company_id,producer_ssn,studio,format,price,release_date,medium,sales,production_cost) VALUES ('1054','Iowa','4','4060','Metal','1011','1006','Sweet Silence','Analog','20','4/2/2006','CD','3800000','390000');
INSERT INTO Album (album_id,title,track_count,album_length,genre,company_id,producer_ssn,studio,format,price,release_date,medium,sales,production_cost) VALUES ('1055','The Subliminal Verses','3','4040','Metal','2014','1007','HQ','Digital','15','12/27/2008','Internet','3900000','400000');
INSERT INTO Album (album_id,title,track_count,album_length,genre,company_id,producer_ssn,studio,format,price,release_date,medium,sales,production_cost) VALUES ('1056','Deadwing','5','1980','Progressive','2015','1008','Spectre Sound','Analog','10','5/3/2000','Vinyl','4000000','410000');
INSERT INTO Album (album_id,title,track_count,album_length,genre,company_id,producer_ssn,studio,format,price,release_date,medium,sales,production_cost) VALUES ('1057','In Absentia','4','1980','Progressive','2016','1004','Columbia','Digital','15','1/28/2003','Tape','4100000','420000');
INSERT INTO Album (album_id,title,track_count,album_length,genre,company_id,producer_ssn,studio,format,price,release_date,medium,sales,production_cost) VALUES ('1058','Fear Of A Blank Planet','3','4000','Progressive','2017','1005','Abbey Road','Analog','20','10/24/2005','CD','4200000','430000');
INSERT INTO Album (album_id,title,track_count,album_length,genre,company_id,producer_ssn,studio,format,price,release_date,medium,sales,production_cost) VALUES ('1059','Operation Mindcrime','5','3980','Progressive','2018','1006','Gold Star','Digital','25','6/4/1990','Internet','4300000','440000');
INSERT INTO Album (album_id,title,track_count,album_length,genre,company_id,producer_ssn,studio,format,price,release_date,medium,sales,production_cost) VALUES ('1060','Empire','4','1920','Progressive','2019','1007','Sound City','Analog','30','2/28/1993','Vinyl','4400000','450000');
INSERT INTO Album (album_id,title,track_count,album_length,genre,company_id,producer_ssn,studio,format,price,release_date,medium,sales,production_cost) VALUES ('1061','Disconnected','3','1920','Progressive','2020','1008','Sweet Silence','Digital','35','12/12/1976','Tape','4500000','460000');
INSERT INTO Album (album_id,title,track_count,album_length,genre,company_id,producer_ssn,studio,format,price,release_date,medium,sales,production_cost) VALUES ('1062','Parallels','5','3940','Progressive','1009','1004','HQ','Analog','40','9/8/1979','CD','4600000','470000');
INSERT INTO Album (album_id,title,track_count,album_length,genre,company_id,producer_ssn,studio,format,price,release_date,medium,sales,production_cost) VALUES ('1063','Hybrid Theory','4','3920','Rock','1010','1005','Spectre Sound','Digital','45','1/23/2008','Internet','4700000','480000');
INSERT INTO Album (album_id,title,track_count,album_length,genre,company_id,producer_ssn,studio,format,price,release_date,medium,sales,production_cost) VALUES ('1064','Meteora','3','1860','Rock','1011','1006','Columbia','Analog','50','10/19/2010','Vinyl','4800000','490000');
INSERT INTO Album (album_id,title,track_count,album_length,genre,company_id,producer_ssn,studio,format,price,release_date,medium,sales,production_cost) VALUES ('1065','Countdown To Extinction','5','1860','Thrash','2014','1007','Abbey Road','Digital','45','3/1/1986','Tape','4900000','500000');
INSERT INTO Album (album_id,title,track_count,album_length,genre,company_id,producer_ssn,studio,format,price,release_date,medium,sales,production_cost) VALUES ('1066','Rust In Peace','4','3880','Thrash','2015','1008','Gold Star','Analog','40','11/25/1988','CD','5000000','450000');


INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1067','60','600','Ride The Lightening','A','English','Guitars','1017','1032','1012');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1068','72','570','Fade To Black','A#','English','Vocals','1017','1032','1013');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1069','96','540','For Whom The Bells Toll','B','English','Percussion','1017','1032','1014');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1070','108','510','Creeping Death','C','English','Keyboards','1017','1032','1015');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1071','120','480','Escape','C#','Instrumental','Instruments','1017','1032','1016');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1072','128','450','Master Of Puppets','D','English','Guitars','1017','1033','2021');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1073','144','420','Battery','D#','English','Vocals','1017','1033','2022');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1074','168','390','Orion','E','English','Percussion','1017','1033','2023');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1075','180','360','Welcome Home','F','English','Keyboards','1017','1033','2024');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1076','192','330','And Justice For All','F#','Instrumental','Instruments','1017','1034','2025');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1077','200','300','Blackened','G','English','Guitars','1017','1034','2024');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1078','216','270','Harvester Of Sorrow','G#','English','Vocals','1017','1034','2023');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1079','200','240','Californication','A','English','Percussion','1018','1035','2022');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1080','192','210','Porcelain','A#','English','Keyboards','1018','1035','2021');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1081','180','180','Scar Tissue','B','Instrumental','Instruments','1018','1035','1016');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1082','168','150','Around The World','C','English','Guitars','1018','1035','1015');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1083','144','120','Savior','C#','English','Vocals','1018','1035','1014');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1084','128','600','Stadium Arcadium','D','English','Percussion','1018','1036','1013');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1085','120','570','Snow','D#','English','Keyboards','1018','1036','1012');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1086','108','540','Desecration Smile','E','Instrumental','Instruments','1018','1036','1013');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1087','96','510','Dani California','F','English','Guitars','1018','1036','1014');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1088','72','480','Images And Words','F#','English','Vocals','1019','1037','1015');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1089','60','450','Learning To Live','G','English','Percussion','1019','1037','1016');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1090','72','420','Metropolis','G#','English','Keyboards','1019','1037','2021');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1091','96','390','Scenes From A Memory','A','Instrumental','Instruments','1019','1038','2022');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1092','108','360','Overture','A#','English','Guitars','1019','1038','2023');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1093','120','330','Finally Free','B','English','Vocals','1019','1038','2024');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1094','128','300','Dance Of Eternity','C','English','Percussion','1019','1038','2025');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1095','144','270','Fatal Tragedy','C#','English','Keyboards','1019','1038','2024');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1096','168','240','A Change Of Seasons','D','Instrumental','Instruments','1019','1039','2023');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1097','180','210','The Big Medley','D#','English','Guitars','1019','1039','2022');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1098','192','180','Perfect Strangers','E','English','Vocals','1019','1039','2021');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1099','200','150','Funeral For A Friend','F','English','Percussion','1019','1039','1016');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1100','216','120','Love At First Sting','F#','German','Keyboards','1020','1040','1015');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1101','200','600','Rock You Like A Hurricane','G','Instrumental','Instruments','1020','1040','1014');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1102','192','570','Coming Home','G#','German','Guitars','1020','1040','1013');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1103','180','540','Crazy World','A','German','Vocals','1020','1041','1012');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1104','168','510','Winds Of Change','A#','German','Percussion','1020','1041','1013');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1105','144','480','Restless Nights','B','German','Keyboards','1020','1041','1014');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1106','128','450','Money And Fame','C','Instrumental','Instruments','1020','1041','1015');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1107','120','420','Send Me An Angel','C#','German','Guitars','1020','1041','1016');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1108','108','390','Crush','D','English','Vocals','1021','1042','2021');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1109','96','360','Its My Life','D#','English','Percussion','1021','1042','2022');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1110','72','330','Save The World','E','English','Keyboards','1021','1042','2023');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1111','60','300','Mystery Train','F','Instrumental','Instruments','1021','1042','2024');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1112','72','270','Slippery When Wet','F#','English','Guitars','1021','1043','2025');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1113','96','240','Livin On A Prayer','G','English','Vocals','1021','1043','2024');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1114','108','210','You Give Love A Bad Name','G#','English','Percussion','1021','1043','2023');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1115','120','180','Painkiller','A','English','Keyboards','1022','1044','2022');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1116','128','150','Touch Of Evil','A#','Instrumental','Instruments','1022','1044','2021');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1117','144','120','Battle Hymn','B','English','Guitars','1022','1044','1016');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1118','168','600','Metal Meltdown','C','English','Vocals','1022','1044','1015');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1119','180','570','One Shot At Glory','C#','English','Percussion','1022','1044','1014');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1120','192','540','Defenders Of The Faith','D','English','Keyboards','1022','1045','1013');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1121','200','510','The Sentinel','D#','Instrumental','Instruments','1022','1045','1012');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1122','216','480','Heavy Duty','E','English','Guitars','1022','1045','1013');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1123','200','450','Jawbreaker','F','English','Vocals','1022','1045','1014');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1124','192','420','Screaming For Vengeance','F#','English','Percussion','1022','1046','1015');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1125','180','390','The Hellion','G','English','Keyboards','1022','1046','1016');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1126','168','360','Electric Eye','G#','Instrumental','Instruments','1022','1046','2021');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1127','144','330','Damnation','A','Swedish','Guitars','1023','1047','2022');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1128','128','300','Windowpane','A#','Swedish','Vocals','1023','1047','2023');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1129','120','270','Closure','B','Swedish','Percussion','1023','1047','2024');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1130','108','240','Weakness','C','Swedish','Keyboards','1023','1047','2025');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1131','96','210','Hope Leaves','C#','Instrumental','Instruments','1023','1047','2024');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1132','72','180','Deliverence','D','Swedish','Guitars','1023','1048','2023');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1133','60','150','Wreath','D#','Swedish','Vocals','1023','1048','2022');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1134','72','120','Masters Apprentice','E','Swedish','Percussion','1023','1048','2021');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1135','96','600','A Fair Judgement','F','Swedish','Keyboards','1023','1048','1016');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1136','108','570','Blackwater Park','F#','Instrumental','Instruments','1023','1049','1015');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1137','120','540','Bleak','G','Swedish','Guitars','1023','1049','1014');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1138','128','510','Patterns In The Ivy','G#','Swedish','Vocals','1023','1049','1013');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1139','144','480','Nothing','A','Swedish','Percussion','1024','1050','1012');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1140','168','450','Rational Gaze','A#','Swedish','Keyboards','1024','1050','1013');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1141','180','420','Perpetual Black Second','B','Instrumental','Instruments','1024','1050','1014');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1142','192','390','Spasm','C','Swedish','Guitars','1024','1050','1015');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1143','200','360','Obsidian','C#','Swedish','Vocals','1024','1050','1016');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1144','216','330','Catch 33','D','Swedish','Percussion','1024','1051','2021');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1145','200','300','Entrapment','D#','Swedish','Keyboards','1024','1051','2022');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1146','192','270','Disenchantment','E','Instrumental','Instruments','1024','1051','2023');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1147','180','240','Sum','F','Swedish','Guitars','1024','1051','2024');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1148','168','210','Seasons In The Abyss','F#','English','Vocals','1025','1052','2025');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1149','144','180','War Ensemble','G','English','Percussion','1025','1052','2024');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1150','128','150','Temptation','G#','English','Keyboards','1025','1052','2023');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1151','120','120','Reign In Blood','A','Instrumental','Instruments','1025','1053','2022');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1152','108','600','Angel Of Death','A#','English','Guitars','1025','1053','2021');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1153','96','570','Reborn','B','English','Vocals','1025','1053','1016');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1154','72','540','Epidemic','C','English','Percussion','1025','1053','1015');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1155','60','510','Piece By Piece','C#','English','Keyboards','1025','1053','1014');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1156','72','480','Iowa','D','Instrumental','Instruments','1026','1054','1013');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1157','96','450','My Plague','D#','English','Guitars','1026','1054','1012');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1158','108','420','Gently','E','English','Vocals','1026','1054','1013');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1159','120','390','The Shape','F','English','Percussion','1026','1054','1014');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1160','128','360','The Subliminal Verses','F#','English','Keyboards','1026','1055','1015');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1161','144','330','Vermillion','G','Instrumental','Instruments','1026','1055','1016');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1162','168','300','Circle','G#','English','Guitars','1026','1055','2021');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1163','180','270','Deadwing','A','English','Vocals','1027','1056','2022');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1164','192','240','Lazarus','A#','English','Percussion','1027','1056','2023');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1165','200','210','Arriving Somewhere But Not Here','B','English','Keyboards','1027','1056','2024');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1166','216','180','Shallow','C','Instrumental','Instruments','1027','1056','2025');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1167','200','150','Halo','C#','English','Guitars','1027','1056','2024');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1168','192','120','In Absentia','D','English','Vocals','1027','1057','2023');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1169','180','600','The Sound Of Muzak','D#','English','Percussion','1027','1057','2022');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1170','168','570','Trains','E','English','Keyboards','1027','1057','2021');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1171','144','540','Heartattack In A Lay by','F','Instrumental','Instruments','1027','1057','1016');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1172','128','510','Fear Of A Blank Planet','F#','English','Guitars','1027','1058','1015');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1173','120','480','Anesthesize','G','English','Vocals','1027','1058','1014');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1174','108','450','My Ashes','G#','English','Percussion','1027','1058','1013');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1175','96','420','Operation Mindcrime','A','English','Keyboards','1028','1059','1012');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1176','72','390','Eyes Of A Stranger','A#','Instrumental','Instruments','1028','1059','1013');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1177','60','360','I Dont Believe In Love','B','English','Guitars','1028','1059','1014');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1178','72','330','Breaking The Silence','C','English','Vocals','1028','1059','1015');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1179','96','300','The Mission','C#','English','Percussion','1028','1059','1016');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1180','108','270','Empire','D','English','Keyboards','1028','1060','2021');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1181','120','240','Anybody Listening','D#','Instrumental','Instruments','1028','1060','2022');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1182','128','210','Jet City Woman','E','English','Guitars','1028','1060','2023');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1183','144','180','Best I Can','F','English','Vocals','1028','1060','2024');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1184','168','150','Disconnected','F#','English','Percussion','1029','1061','2025');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1185','180','120','Still Remains','G','English','Keyboards','1029','1061','2024');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1186','192','600','One','G#','Instrumental','Instruments','1029','1061','2023');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1187','200','570','Parallels','A','English','Guitars','1029','1062','2022');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1188','216','540','The Eleventh Hour','A#','English','Vocals','1029','1062','2021');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1189','200','510','Eye To Eye','B','English','Percussion','1029','1062','1016');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1190','192','480','We Only Say Goodbye','C','English','Keyboards','1029','1062','1015');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1191','180','450','Life In Still Water','C#','Instrumental','Instruments','1029','1062','1014');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1192','168','420','Hybrid Theory','D','English','Guitars','1030','1063','1013');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1193','144','390','In The End','D#','English','Vocals','1030','1063','1012');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1194','128','360','Crawling','E','English','Percussion','1030','1063','1013');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1195','120','330','Runaway','F','English','Keyboards','1030','1063','1014');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1196','108','300','Meteora','F#','Instrumental','Instruments','1030','1064','1015');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1197','96','270','Somewhere I Belong','G','English','Guitars','1030','1064','1016');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1198','72','240','Faint','G#','English','Vocals','1030','1064','2021');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1199','60','210','Countdown To Extinction','A','English','Percussion','1031','1065','2022');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1200','72','180','Symphony Of Destruction','A#','English','Keyboards','1031','1065','2023');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1201','96','150','Architecture Of Aggression','B','Instrumental','Instruments','1031','1065','2024');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1202','108','120','Sweating Bullets','C','English','Guitars','1031','1065','2025');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1203','120','600','Forclosure Of A Dream','C#','English','Vocals','1031','1065','2024');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1204','128','570','Rust In Peace','D','English','Percussion','1031','1066','2023');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1205','144','540','Holy Wars','D#','English','Keyboards','1031','1066','2022');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1206','168','510','Tornado Of Souls','E','Instrumental','Instruments','1031','1066','2021');
INSERT INTO Song (track_id,tempo,song_length,title,song_key,song_language,artist_role,artist_id,album_id,composer_ssn) VALUES ('1207','180','480','Hangar 18','F','English','Guitars','1031','1066','1016');

INSERT INTO Collaborates (track_id,artist_id,artist_role) VALUES ('1068','1031','Guitars');
INSERT INTO Collaborates (track_id,artist_id,artist_role) VALUES ('1078','1030','Vocals');
INSERT INTO Collaborates (track_id,artist_id,artist_role) VALUES ('1088','1029','Percussion');
INSERT INTO Collaborates (track_id,artist_id,artist_role) VALUES ('1098','1028','Keyboards');
INSERT INTO Collaborates (track_id,artist_id,artist_role) VALUES ('1108','1027','Instruments');
INSERT INTO Collaborates (track_id,artist_id,artist_role) VALUES ('1118','1026','Guitars');
INSERT INTO Collaborates (track_id,artist_id,artist_role) VALUES ('1128','1025','Vocals');
INSERT INTO Collaborates (track_id,artist_id,artist_role) VALUES ('1138','1024','Percussion');
INSERT INTO Collaborates (track_id,artist_id,artist_role) VALUES ('1148','1023','Keyboards');
INSERT INTO Collaborates (track_id,artist_id,artist_role) VALUES ('1158','1022','Instruments');