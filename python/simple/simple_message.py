import requests
import configparser
import auth

config = configparser.ConfigParser()
config.read('./config.ini')
print(config['AUTH'])
apiKey = config['AUTH']['ApiKey']
apiSecret = config['AUTH']['ApiSecret']


if __name__ == '__main__':
    data = {
        'message': {
            'to': config['VALUE']['to'],
            'from': config['VALUE']['from'],
            'text': 'test'
        }
    }
    res = requests.post(config['SERVER']['URI'] + 'send', headers=auth.get_headers(apiKey, apiSecret), json=data)
    print(res.text)
