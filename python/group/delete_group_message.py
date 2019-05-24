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
    # [INPUT_GROUP_ID] 에 그룹 아이디를 넣어주세요
    # [INPUT_MESSAGE_ID] 에 메세지 아이디를 넣어주세요
    # ex) G4V20181005122748TESTTESTTESTTES
    data = {'messageIds':
        [
            # 배열로 id를 입력하여 한 요청에 여러개의 메세지를 제거할 수 있습니다.
            '[INPUT_MESSAGE_ID]',
            '[INPUT_MESSAGE_ID]'
        ]
    }
    res = requests.delete(config['SERVER']['URI'] + 'groups/[INPUT_GROUP_ID]/messages',
                          headers=auth.get_headers(apiKey, apiSecret),
                          json=data)
    print(json.dumps(json.loads(res.text), indent=2, ensure_ascii=False))
