<?php
/*
 coolsms-message-v4 php example
 send group messages
*/
$configFile = file_get_contents("./config.json");
$config = json_decode($configFile, true);

function get_header() {
  global $config;
  # apiKey && apiSecret are acquired from coolsms.co.kr/credentials
  $apiKey = $config["apiKey"];
  $apiSecret = $config["apiSecret"];
  date_default_timezone_set('Asia/Seoul');
  $date = date('Y-m-d\TH:i:s.Z\Z', time());
  $salt = uniqid();
  $signature = hash_hmac('sha256', $date.$salt, $apiSecret);
  return "Authorization: HMAC-SHA256 apiKey={$apiKey}, date={$date}, salt={$salt}, signature={$signature}";
}

function create_group() {
  $url = "https://rest.coolsms.co.kr/messages/v4/groups";
  $result = request("POST", $url);
  $groupId = json_decode($result)->groupId;
  print_r("GroupID : {$groupId}\n");
  return $groupId;
}

// 이미지 생성
function createImage () {
  $path = 'php/testImage.jpg';
  $type = pathinfo($path, PATHINFO_EXTENSION);
  echo $type;
  $data = file_get_contents($path);
  $imageData = base64_encode($data);

  // image API 에서 이미지 생성
  $url = "https://rest.coolsms.co.kr/images/v4/images";
  $fields = new stdClass();
  $fields->image = $imageData;
  $result = request("POST", $url, $fields);
  $imageId = json_decode($result)->imageId;
  print_r("imageId : {$imageId}\n");
  return $imageId;
}

// 메시지 추가
function add_message($groupId) {
  global $config;

  $fields = new stdClass();
  $message = new stdClass();
  $message->text = $config["text"] . " from PHP";
  // 타입 변경 
  $message->type = 'MMS';
  $message->to = $config["to"];
  $message->from = $config["from"];
  // 제목 추가
  $message->subject = 'MMS 에서 제목은 필수입니다.';
  $message->imageId = createImage();
  print_r($message);
  $fields->messages = json_encode(array($message));
  $url = "https://rest.coolsms.co.kr/messages/v4/groups/{$groupId}/messages";
  $result = request("PUT", $url, $fields);
  print_r("{$result}\n");
}

// 문자 발송
function send_message($groupId) {
  $url = "https://rest.coolsms.co.kr/messages/v4/groups/{$groupId}/send";
  $result = request("POST", $url);
  print_r("Group msg send : {$result}\n");
}

// http request
function request($method, $url, $data = false) {
  // echo "{$method}  {$url}\n";
  print_r($data);
  try {
    $curl = curl_init();
    switch ($method) {
      case "POST":
        curl_setopt($curl, CURLOPT_CUSTOMREQUEST, "POST");
        if ($data) curl_setopt($curl, CURLOPT_POSTFIELDS, json_encode($data));
        break;
      case "PUT":
        curl_setopt($curl, CURLOPT_CUSTOMREQUEST, "PUT");
        if ($data) curl_setopt($curl, CURLOPT_POSTFIELDS, json_encode($data));
        break;
      default:
        if ($data) $url = sprintf("%s?%s", $url, http_build_query($data));
    }
    curl_setopt($curl, CURLOPT_HTTPHEADER, array(get_header(), "Content-Type: application/json"));
    curl_setopt($curl, CURLOPT_URL, $url);
    curl_setopt($curl, CURLOPT_RETURNTRANSFER, true);
    if (curl_error($curl)) {
      print curl_error($curl);
    }
    $result = curl_exec($curl);
    curl_close($curl);
    return $result;
  } catch (Exception $err) {
    echo $err;
  }
}

$groupId = create_group();
add_message($groupId);
send_message($groupId);

?>
