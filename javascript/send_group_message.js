const { config, Group } = require('solapi')
const conf = require('../config')

/*
 solapi js example
 send group messages
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
    console.log(await group.sendMessages())
  } catch (e) {
    console.log(e)
  }
}
