const { config, Group } = require('solapi')
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
    const { resultList } = await group.addGroupMessage(message)
    console.log(await group.deleteGroupMessages(resultList[0].messageId))
  } catch (e) {
    console.log(e)
  }
}
