const { config, Group, Image } = require('coolsms-sdk-v4')
const conf = require('../config')
const axios = require('axios')
const fs = require('fs')

/*
 coolsms-message-v4 js example
 send simple messages
*/

config.init({
  apiKey: conf.apiKey,
  apiSecret: conf.apiSecret
})
send({
  text: `${conf.text} from JavaScript`,
  type: conf.type,
  to: conf.to,
  from: conf.from
})

// 이미지 생성
async function createImage() {
  // 이미지 파일 읽어오기
  const rawImage = fs.readFileSync('testImage.jpg')
  // 파일을 base64 로 변환
  const base64Image = rawImage.toString('base64')
  const ImageCreator = new Image(base64Image)
  return await ImageCreator.createImage()
}

// 문자 발송
async function send(message, agent = {}) {
  try {
    const imageId = await createImage()
    const body = {
      ...message,
      imageId,
      type: 'MMS',
      subject: 'javascript image test'
    }
    console.log(body)
    console.log(await Group.sendSimpleMessage(body, agent))
  } catch (e) {
    console.log(e)
  }
}
