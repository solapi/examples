const { config, Group } = require('solapi-sdk-v4')
const conf = require('../config')

/*
 solapi js example
 delete group messages
*/

config.init({
  apiKey: conf.apiKey,
  apiSecret: conf.apiSecret
})
send({
  text: `${conf.text} from Javascript`,
  type: conf.type,
  to: conf.to,
  from: conf.from
})
async function send (message) {
  try {
    const group = new Group()
    await group.createGroup()
    await group.addGroupMessage(message)
    await group.deleteGroupMessages()
  } catch (e) {
    console.log(e)
  }
}
