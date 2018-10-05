const { config, Group } = require('coolsms-sdk-v4')
const conf = require('../config')

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
async function send (message, agent = {}) {
  try {
    console.log(await Group.sendSimpleMessage(message, agent))
  } catch (e) {
    console.log(e)
  }
}
