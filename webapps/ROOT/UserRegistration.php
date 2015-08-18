<html>
<head>
	<title>Registration Form</title>
</head>
<?php
	error_reporting(E_ALL ^ E_NOTICE ^ E_DEPRECATED);
	session_start();
	$_SESSION['host']="10.0.0.11";
	$_SESSION['user']="root";
	$_SESSION['password']="tecnics";
	$_SESSION['db']="teclab";
	$conn=mysql_connect($_SESSION['host'],$_SESSION['user'], $_SESSION['password']);
	mysql_select_db($_SESSION['db']);
	if (!$conn) 
	{
	    die("Connection failed: " . mysqli_connect_error());
	}
	//echo $_GET['serviceType'];
	//echo document.getElementById("serviceType").value;
	if($_GET['serviceType']=="BLOOD"){
		$sqlGetFields="select * from tblserviceconfig  where ServiceID='1'";
		//$sqlGetFields="SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'tbl_serviceconfig' and ORDINAL_POSITION in (2,4,5,6,7,8,9) ";
	}
	else if($_GET['serviceType']=="MARRIAGE")
		{
			$sqlGetFields="select * from tblserviceconfig where ServiceID='2'";
		//$sqlGetFields="SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'tbl_serviceconfig' and ORDINAL_POSITION in (3,4,6) ";
	}
	else if($_GET['serviceType']=="TUITION")
		{
			$sqlGetFields="select * from tblserviceconfig where ServiceID='3'";
		//$sqlGetFields="select * from tblRegistrationFields where SlNum IN (2,3,7,9,11,13)";
		//	$sqlGetFields="SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'tbl_serviceconfig' and ORDINAL_POSITION in (2,5,7,8,9,2,3,4,5) ";
	}
	$sqlColumnCount="select count(*) from Information_Schema.Columns c where table_name = 'tblserviceconfig'";
	$resultColumnCount=mysql_query($sqlColumnCount);
	$ColumnCount = mysql_fetch_array($resultColumnCount);
	$Columns= $ColumnCount["count(*)"];
	$result=mysql_query($sqlGetFields);
	$check=mysql_affected_rows();
	//echo $check;
	?>
	<center>
	<h1>Registration</h1>
	<form method="get">
		<table border="3">
		<tr><td>
			Name:
			<input type="text" name="name" id="name">
		</td></tr>
		<tr><td>
		Phone:
		<input type="tel" pattern='\d{10}' title='Phone Number (Format: 9999999999)' name="PhoneNum" id="PhoneNum" required>
		</td></tr>
		<tr><td>
		EmailId:
		<input type="email" name="EmailId" id="EmailId">
		</td></tr>
		<tr><td>
		Password:
		<input type="password" name="password" id="password">
		</td></tr>
		<tr><td>
		Confirm Password:
		<input type="password" name="cpassword" id="cpassword">
		</td></tr>
				<?php
				while($row = mysql_fetch_array($result)) 
				{
					//echo ;
					for($i=1;$i<$Columns;$i++){
					?>
					<tr>
					<td>
					<?php
						echo $row["FieldName$i"];
					?>
					<input type="text" name="fieldName" >
					</td>
					</tr>
					<?php
					}
				}
				?>
		</table>
		<br><input type="submit" value="Register">
	</form>
	</center>
	<?php
?>