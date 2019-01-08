<?php
/*
 * coolsms-message-v4 php example
 * send simple messages
 */
$configFile = file_get_contents("./config.json");
$config = json_decode($configFile, true);

// 인증 생성
function getAuthHeader () {
  global $config;
  // apiKey && apiSecret are acquired from coolsms.co.kr/credentials
  $apiKey = $config["apiKey"];
  $apiSecret = $config["apiSecret"];

  date_default_timezone_set('Asia/Seoul');
  $date = date('Y-m-d\TH:i:s.Z\Z', time());  // date must be ISO 8361 format
  $salt = uniqid(); // Any random strings with [0-9a-zA-Z]
  $signature = hash_hmac('sha256', $date.$salt, $apiSecret);
  return "Authorization: HMAC-SHA256 apiKey={$apiKey}, date={$date}, salt={$salt}, signature={$signature}";
}

// 이미지 생성
function createImage () {
  global $config;

  $path = 'php/testImage.jpg';
  $type = pathinfo($path, PATHINFO_EXTENSION);
  echo $type;
  $data = file_get_contents($path);
  $imageData = base64_encode($data);

  $url = "https://rest.coolsms.co.kr/images/v4/images";
  $fields = new stdClass();
  $fields->image = $imageData;
  $fields_string = json_encode($fields);
  $header = getAuthHeader();

  //open connection
  $ch = curl_init();
  curl_setopt($ch, CURLOPT_URL, $url);
  curl_setopt($ch, CURLOPT_HTTPHEADER, array($header, "Content-Type: application/json"));
  curl_setopt($ch, CURLOPT_POST, count($fields));
  curl_setopt($ch, CURLOPT_POSTFIELDS, $fields_string);
  curl_setopt($ch,CURLOPT_RETURNTRANSFER, true); 

  $result = curl_exec($ch);
  $parseResult = json_decode($result, true);
  return $parseResult["imageId"];
}


// 문자 발송
function send ($imageId) {
  global $config;

  $url = "https://rest.coolsms.co.kr/messages/v4/send";
  $fields = new stdClass();
  $message = new stdClass();
  $message->text = $config["text"] . " from PHP";
  // 타입 변경 
  $message->type = 'MMS';
  $message->to = $config["to"];
  $message->from = $config["from"];
  $message->imageId = $imageId;
  // 제목 추가
  $message->subject = 'MMS 에서 제목은 필수입니다.';
  $fields->message = $message;
  $fields_string = json_encode($fields);
  $header = getAuthHeader();

  //open connection
  $ch = curl_init();
  curl_setopt($ch, CURLOPT_URL, $url);
  curl_setopt($ch, CURLOPT_HTTPHEADER, array($header, "Content-Type: application/json"));
  curl_setopt($ch, CURLOPT_POST, count($fields));
  curl_setopt($ch, CURLOPT_POSTFIELDS, $fields_string);
  curl_setopt($ch,CURLOPT_RETURNTRANSFER, true); 

  $result = curl_exec($ch);
  echo $result;
}

$imageId = createImage();
send($imageId);

?>
