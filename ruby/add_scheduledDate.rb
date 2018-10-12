require 'openssl'
require 'Base64'
require 'net/http'
require 'json'
require 'securerandom'
require 'date'

#
# coolsms-message-v4 ruby 
# add scheduledDate
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

def create_group
    header = get_header
    # puts 'header : ' + header
    uri = URI('https://rest.coolsms.co.kr/messages/v4/groups')
    http = Net::HTTP.new(uri.host, uri.port)
    http.use_ssl = true
    req = Net::HTTP::Post.new(uri.path, 'Content-Type' => 'application/json')
    req.add_field('Authorization', header)
    res = http.request(req)
    # puts res.body
    group_id = JSON.parse(res.body)["groupId"]
    puts 'groupId: ' + group_id
    return group_id
rescue => e
    puts 'failed'
    puts e
end

def add_message(groupId)
    header = get_header
    uri = URI("https://rest.coolsms.co.kr/messages/v4/groups/#{groupId}/messages")
    http = Net::HTTP.new(uri.host, uri.port)
    http.use_ssl = true
    req = Net::HTTP::Put.new(uri.path, 'Content-Type' => 'application/json')
    req.add_field('Authorization', header)
    req.body = {
      messages: [{
        text: $config["text"] + " from Ruby",
        type: $config["type"],
        to: $config["to"],
        from: $config["from"]
      }].to_json
    }.to_json
    res = http.request(req)
    puts "Group msg added : #{res.body}"

rescue => e
    puts e
end

def add_scheduledDate(groupId)
    header = get_header
    uri = URI("https://rest.coolsms.co.kr/messages/v4/groups/#{groupId}/schedule")
    http = Net::HTTP.new(uri.host, uri.port)
    http.use_ssl = true
    req = Net::HTTP::Post.new(uri.path, 'Content-Type' => 'application/json')
    req.add_field('Authorization', header)
    date = (Date.today + 1).to_s
    puts date
    req.body = {
      scheduledDate: date
    }.to_json
    res = http.request(req)
    puts "Get Group Info : #{res.body}"

rescue => e
    puts e
end

groupId = create_group()
add_message(groupId)
add_scheduledDate(groupId)
