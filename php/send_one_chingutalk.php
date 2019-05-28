<?php
/*
 * solapi php example
 * send simple messages
 */
$configFile = file_get_contents("../config.json");
$config = json_decode($configFile, true);

// apiKey && apiSecret are acquired from solapi.com/credentials
$apiKey = $config["apiKey"];
$apiSecret = $config["apiSecret"];

date_default_timezone_set('Asia/Seoul');
$date = date('Y-m-d\TH:i:s.Z\Z', time());  // date must be ISO 8361 format
$salt = uniqid(); // Any random strings with [0-9a-zA-Z]
$signature = hash_hmac('sha256', $date.$salt, $apiSecret);

$url = "https://api.solapi.com/messages/v4/send";
$kakaoOptions = new stdClass();
$kakaoOptions->pfId = "### ENTER PFID ###";
$fields = new stdClass();
$message = new stdClass();
$message->text = "친구톡 메시지 테스트 입니다.";
$message->type = "CTA";
$message->to = $config["to"];
$message->from = $config["from"];
$message->kakaoOptions = $kakaoOptions;
$fields->message = $message;
$fields_string = json_encode($fields);
$header = "Authorization: HMAC-SHA256 apiKey={$apiKey}, date={$date}, salt={$salt}, signature={$signature}";

//open connection
$ch = curl_init();
curl_setopt($ch, CURLOPT_URL, $url);
curl_setopt($ch, CURLOPT_HTTPHEADER, array($header, "Content-Type: application/json"));
curl_setopt($ch, CURLOPT_POST, count($fields));
curl_setopt($ch, CURLOPT_POSTFIELDS, $fields_string);
curl_setopt($ch,CURLOPT_RETURNTRANSFER, true);

$result = curl_exec($ch);
echo $result;

?>
