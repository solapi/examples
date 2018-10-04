import requests
import time
import datetime
import uuid
import hmac
import hashlib

apiKey = 'NCSCRL5NEDD4GXN0'
apiSecret = '2EKV5OEQESARHFHOQNOYLKMZVAZVTWW9'


def uniqid():
    return str(uuid.uuid1().hex)


def getISODateTime():
    # calculate the offset taking into account daylight saving time
    utc_offset_sec = time.altzone if time.localtime().tm_isdst else time.timezone
    utc_offset = datetime.timedelta(seconds=-utc_offset_sec)
    return datetime.datetime.now().replace(tzinfo=datetime.timezone(offset=utc_offset)).isoformat()
    # return str(int(time.time()))


def getSignature(key, msg):
    print(hmac.new(key.encode(), msg.encode(), hashlib.md5).hexdigest())
    return hmac.new(key.encode(), msg.encode(), hashlib.md5).hexdigest()


if __name__ == '__main__':
    date = getISODateTime()
    salt = uniqid()
    msg = date + salt
    res = requests.post('https://rest.coolsms.co.kr/messages/v4/send', headers={
        'Authorization':
    'HMAC-SHA256 ApiKey=' + apiKey + ', Date=' + date + ', salt=' + salt + ', signature=' + getSignature(apiSecret, msg)})
    print(res.text)
