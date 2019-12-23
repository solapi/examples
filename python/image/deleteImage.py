import requests
import json
import configparser
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
    # [IMAGE_ID] 에 이미지 아이디를 넣어주세요
    res = requests.delete(config['SERVER']['IMGURI'] + 'files/[IMAGE_ID]',
                        headers=auth.get_headers(apiKey, apiSecret))
    print(json.dumps(json.loads(res.text), indent=2, ensure_ascii=False))
