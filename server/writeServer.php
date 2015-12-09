<?php

// Michael Wehar assisted group sixPoints with writing this code.

$lot = $_REQUEST["lot"];
$type = $_REQUEST["type"];

$rtime = time();
date_default_timezone_set('America/New_York');
$dateArray = getdate();
$rdate = $dateArray['mon'] . "/" . $dateArray['mday'] . "/" . $dateArray['year'];

// Connect
$conn = mysqli_connect("localhost","sharpagr_CSE442","Bq^RJ!AU.8w7","sharpagr_sixpoints");

// Write Query
$sql1 = "INSERT INTO `transactions`(`lot`, `type`, `rdate`, `rtime`) VALUES ('" . $lot . "','" . $type . "','" . $rdate . "','" . $rtime . "');";
$result1 = mysqli_query($conn, $sql1);

// Disconnect
mysqli_close($conn);

echo $result1;                

?>