import requests
import configparser
import json
import sys
import os.path

libdir = os.path.dirname(__file__)
sys.path.append(os.path.split(libdir)[0])

from auth import auth

config = configparser.ConfigParser()
config.read('config.ini')
apiKey = config['AUTH']['ApiKey']
apiSecret = config['AUTH']['ApiSecret']


if __name__ == '__main__':
    res = requests.get(config['SERVER']['URI'] + 'groups',
                       headers=auth.get_headers(apiKey, apiSecret))
    print(json.dumps(json.loads(res.text), indent=2, ensure_ascii=False))
