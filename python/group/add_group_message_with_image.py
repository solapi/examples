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
    # [GROUP_ID] 에 그룹 아이디를 넣어주세요
    # ex) G4V20181005122748TESTTESTTESTTES
    # [IMAGE_ID] 에 이미지 아이디를 넣어주세요
    data = {
        'messages': json.dumps([
            {
                'to': config['VALUE']['to'],
                'from': config['VALUE']['from'],
                'text': 'test1',
                'imageId': '[IMAGE_ID]'
            },
            {
                'to': config['VALUE']['to2'],
                'from': config['VALUE']['from'],
                'text': 'test2',
                'imageId': '[IMAGE_ID]'
            }
        ])
    }
    res = requests.put(config['SERVER']['URI'] + 'groups/[GROUP_ID]/messages',
                       headers=auth.get_headers(apiKey, apiSecret),
                       json=data)
    print(json.dumps(json.loads(res.text), indent=2, ensure_ascii=False))
