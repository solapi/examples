import requests
import configparser
import auth

config = configparser.ConfigParser()
config.read('./config.ini')
apiKey = config['AUTH']['ApiKey']
apiSecret = config['AUTH']['ApiSecret']


if __name__ == '__main__':
    date = auth.get_iso_datetime()
    salt = auth.unique_id()
    msg = date + salt
    headers = {'Authorization': 'HMAC-SHA256 ApiKey=' + apiKey + ', Date=' + date + ', salt=' + salt + ', signature=' +
                                auth.get_signature(apiSecret, msg)}
    data = {
        'message': {
            'to': config['VALUE']['to'],
            'from': config['VALUE']['from'],
            'text': 'test'
        }
    }
    res = requests.post(config['SERVER']['URI'], headers=headers, json=data)
    print(res.text)
