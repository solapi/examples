import requests
import configparser
import auth
import json

config = configparser.ConfigParser()
config.read('../config.ini')
apiKey = config['AUTH']['ApiKey']
apiSecret = config['AUTH']['ApiSecret']


if __name__ == '__main__':
    # [INPUT_GROUP_ID] 에 그룹 아이디를 넣어주세요
    # ex) G4V20181005122748TESTTESTTESTTES
    res = requests.get(config['SERVER']['URI'] + 'groups/[INPUT_GROUP_ID]', headers=auth.get_headers(apiKey, apiSecret))
    print(json.loads(res.text))
