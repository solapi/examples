import requests
import configparser
import json
import sys
import os.path
import image

libdir = os.path.dirname(__file__)
sys.path.append(os.path.split(libdir)[0])

from auth import auth

config = configparser.ConfigParser()
config.read('config.ini')
apiKey = config['AUTH']['ApiKey']
apiSecret = config['AUTH']['ApiSecret']

if __name__ == '__main__':
    # 이미지를 바꾸시려면 testImage.jpg 대신
    # 사용하실 이미지가 있는 파일 경로를 넣어주세요
    imageInfo = json.loads(image.createImage('./image/testImage.jpg', config['SERVER']['IMGURI'], auth.get_headers(apiKey, apiSecret)).text)
    data = {
        'message': {
            'to': config['VALUE']['to'],
            'from': config['VALUE']['from'],
            'text': 'test',
            'imageId': imageInfo['imageId']
        }
    }
    res = requests.post(config['SERVER']['URI'] + 'send', headers=auth.get_headers(apiKey, apiSecret), json=data)
    print(json.dumps(json.loads(res.text), indent=2, ensure_ascii=False))
