require 'openssl'
require 'Base64'
require 'net/http'
require 'json'
require 'securerandom'

#
# coolsms-message-v4 ruby 
# send simple message
# 

file = File.read './config.json'
$config = JSON.parse(file)

def get_header
    apiKey = $config["apiKey"]
    apiSecret = $config["apiSecret"]
    date = Time.now.strftime('%Y-%m-%dT%H:%M:%S.%L%z')
    salt = SecureRandom.hex
    signature = OpenSSL::HMAC.hexdigest('SHA256', apiSecret, date + salt)
    return 'HMAC-SHA256 apiKey=' + apiKey + ', date=' + date + ', salt=' + salt + ', signature=' + signature
end

def send_sms
    header = get_header
    puts 'header : ' + header
    uri = URI('https://rest.coolsms.co.kr/messages/v4/send')
    http = Net::HTTP.new(uri.host, uri.port)
    http.use_ssl = true
    req = Net::HTTP::Post.new(uri.path, 'Content-Type' => 'application/json')
    req.add_field('Authorization', header)
    req.body = {
      message: {
        text: $config["text"] + " from Ruby",
        type: $config["type"],
        to: $config["to"],
        from: $config["from"]
      }
    }.to_json
    res = http.request(req)
    puts res.body
rescue => e
    puts 'failed'
    puts e
end

send_sms()
