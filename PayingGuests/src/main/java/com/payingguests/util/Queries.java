package com.payingguests.util;

public class Queries {
	public static final String CREATEPGQUERY = "CREATE TABLE PayingGuest(PGId Integer PRIMARY KEY, PGName Varchar(50), Location Varchar(20),Category Varchar(20));";
	public static final String CREATEROOMQUERY = "CREATE TABLE Room(RoomId Integer PRIMARY KEY, Type Varchar(10), Share Varchar(10), Availability boolean, Price double, PGId Integer, FOREIGN KEY (PGId) REFERENCES PayingGuest(PGId));";
	public static final String INSERTPGQUERY = "INSERT INTO PayingGuest VALUES(?,?,?,?);";
	public static final String INSERTROOMQUERY = "INSERT INTO Room VALUES(?,?,?,?,?,?);";

	public static final String SELECTROOMQUERY = "SELECT * FROM Room;";
	public static final String QUERYBYROOMTYPE = "SELECT * FROM Room WHERE Type=?;";
	public static final String QUERYBYPGTYPE = "SELECT Room.* FROM PayingGuest INNER JOIN Room ON (PayingGuest.PGId=Room.PGId AND PayingGuest.Category = ?);";
	public static final String SELECTAVAILABILITYQUERY = "SELECT Availability FROM Room WHERE RoomId = ?";
	public static final String QUERYBYSHARE = "SELECT * FROM Room WHERE Share=?;";
	public static final String SELECTPGQUERY = "SELECT * FROM PayingGuest;";
	public static final String QUERYBYPGID = "SELECT * FROM PayingGuest WHERE PGId=?;";
	public static final String QUERYBYCATEGORY = "SELECT * FROM PayingGuest WHERE Category=?;";
	public static final String QUERYBYLOCATION = "SELECT * FROM PayingGuest WHERE Location=?;";
	public static final String APPENDROOMQUERY = "SELECT DISTINCT Room.RoomId,Room.Type,Room.Share,Room.Availability,Room.Price FROM PayingGuest INNER JOIN Room where (Room.PGId=?) ORDER BY RoomId ASC;";
	public static final String UPDATEROOMQUERY = "UPDATE Room SET Share=? WHERE RoomId=?;";
	public static final String UPDATEAVAILABILITYQUERY = "UPDATE Room SET Availability=? WHERE RoomId=?;";
	public static final String UPDATEPGCATEGORY = "UPDATE PayingGuest SET Category=? WHERE PGId=?;";

	public static final String DELETEROOMQUERY = "DELETE FROM Room WHERE RoomID=?;";
	public static final String DELETEPGQUERY = "DELETE FROM PayingGuest WHERE PGId=?;";

	public static final String CREATEUSERQUERY = "CREATE TABLE UserDetail(Username VARCHAR(25) UNIQUE,Name VARCHAR(25), Mobile VARCHAR(25), Email VARCHAR(50), City VARCHAR(25), CONSTRAINT chk_mobile CHECK (Mobile not like '%[^0-9]%'));";
	public static final String INSERTUSERQUERY = "INSERT INTO UserDetail VALUES(?,?,?,?,?,?);";
	public static final String LOGINUSERQUERY="SELECT * FROM UserDetail WHERE Username=? AND Password=?";
	public static final String PASSWORDUPDATEQUERY="UPDATE UserDetail SET Password = ? WHERE Username = ?";
}
