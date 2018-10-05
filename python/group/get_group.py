import requests
import configparser
import auth
import json

config = configparser.ConfigParser()
config.read('../config.ini')
apiKey = config['AUTH']['ApiKey']
apiSecret = config['AUTH']['ApiSecret']


if __name__ == '__main__':
    res = requests.get(config['SERVER']['URI'] + 'groups', headers=auth.get_headers(apiKey, apiSecret))
    print(json.loads(res.text))
