<?php
/*
 coolsms php example
 create group
*/
$configFile = file_get_contents("../config.json");
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

function balance() {
  $url = "https://api.solapi.com/cash/v1/balance";
  $result = request("GET", $url);
  return $result;
}

function request($method, $url, $data = false) {
  echo "{$method}  {$url}\n";
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


$balance = balance();
print_r($balance);
