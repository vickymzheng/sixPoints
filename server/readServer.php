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

// Read Query
$sql1 = "SELECT * FROM transactions WHERE lot = '" . $lot . "';";
$result1 = mysqli_query($conn, $sql1);

$count = 0;
if(mysqli_num_rows($result1) > 0){
    while($row1 = mysqli_fetch_assoc($result1)){
        if($row1['rtime'] + 21600 > $rtime && $row1['type'] == $type) $count++;
    }
}

// Disconnect
mysqli_close($conn);

echo $count;                

?>