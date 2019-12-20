
/* Create a table called NAMES */
CREATE TABLE NAMES(Id integer PRIMARY KEY, Name text);

/* Create few records in this table */
INSERT INTO NAMES VALUES(1,'Tom');
INSERT INTO NAMES VALUES(6,'Tom');
INSERT INTO NAMES VALUES(7,'Tom');
INSERT INTO NAMES VALUES(8,'Tom');
INSERT INTO NAMES VALUES(2,'Lucy');
INSERT INTO NAMES VALUES(3,'Frank');
INSERT INTO NAMES VALUES(4,'Jane');
INSERT INTO NAMES VALUES(89,'Jane');
INSERT INTO NAMES VALUES(90,'Jane');
INSERT INTO NAMES VALUES(23,'Jane');
INSERT INTO NAMES VALUES(5,'Robert');
COMMIT;

//listing all duplicates
SELECT Name, COUNT(Name) FROM NAMES
GROUP BY Name
HAVING COUNT(Name) > 1;
	
//Delete all duplicates
delete From NAMES where Name in(select Name from NAMES group by Name having count(*) >1);

//Delete Duplicates except one with Max ID
DELETE t1 FROM NAMES t1 INNER JOIN NAMES t2 WHERE t1.Id < t2.Id AND t1.Name = t2.Name;

//Pagination
SELECT * FROM 
(SELECT result.*, 
ROWNUM RNUM FROM 
(SELECT obj.obj_name,obj.obj_key,ost.ost_status,oiu.ACCOUNT_TYPE,oiu.OIU_PROV_START_DATE,usr.USR_LOGIN,obi.OBI_CREATE,UD_GENERICA.* 
FROM ost,obj,obi,oiu,usr,UD_GENERICA 
WHERE oiu.orc_key = UD_GENERICA.orc_key AND oiu.usr_key = usr.usr_key 
AND obi.obi_key = oiu.obi_key AND obj.OBJ_KEY = obi.OBJ_KEY 
AND oiu.OST_KEY = ost.OST_KEY AND OBJ.obj_key = 246 
order by USR_LOGIN DESC) 
result WHERE ROWNUM <= 50 ) WHERE RNUM >= 1;
