import requests
import configparser
import json
import sys
import os.path

libdir = os.path.dirname(__file__)
sys.path.append(os.path.split(libdir)[0])

from auth import auth

config = configparser.ConfigParser()
config.read('../config.ini')
apiKey = config['AUTH']['ApiKey']
apiSecret = config['AUTH']['ApiSecret']

if __name__ == '__main__':
    # [INPUT_GROUP_ID] 에 그룹 아이디를 넣어주세요
    # ex) G4V20181005122748TESTTESTTESTTES
    # [INPUT_DATE] 에 날짜를 넣어주세요 (format : YY-mm-dd)
    data = {'scheduledDate': '[INPUT_DATE]'}
    res = requests.post(config['SERVER']['URI'] + 'groups/[INPUT_GROUP_ID]/schedule',
                        headers=auth.get_headers(apiKey, apiSecret),
                        json=data)
    print(json.dumps(json.loads(res.text), indent=2, ensure_ascii=False))
