const { config, Group } = require('coolsms-sdk-v4')
const conf = require('../config')

/*
 coolsms-message-v4 js example
 add group messages
*/

config.init({
  apiKey: conf.apiKey,
  apiSecret: conf.apiSecret
})
addGroupMessage({
  text: `${conf.text} from Javascript`,
  type: conf.type,
  to: conf.to,
  from: conf.from
})
async function addGroupMessage (message) {
  try {
    const group = new Group()
    await group.createGroup()
    await group.addGroupMessage(message)
  } catch (e) {
    console.log(e)
  }
}
