require 'openssl'
require 'Base64'
require 'net/http'
require 'json'
require 'securerandom'

#
# coolsms-message-v4 ruby 
# get group list
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

def get_group_list()
    header = get_header
    uri = URI("https://rest.coolsms.co.kr/messages/v4/groups")
    http = Net::HTTP.new(uri.host, uri.port)
    http.use_ssl = true
    req = Net::HTTP::Get.new(uri.path, 'Content-Type' => 'application/json')
    req.add_field('Authorization', header)
    res = http.request(req)
    puts "Group List : #{res.body}"

rescue => e
    puts e
end

get_group_list()
