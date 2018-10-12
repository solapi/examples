const { config, Group } = require('coolsms-sdk-v4')
const conf = require('../config')

/*
 coolsms-message-v4 js example
 send group messages
*/

config.init({
  apiKey: conf.apiKey,
  apiSecret: conf.apiSecret
})
creaetGroup({
  text: `${conf.text} from Javascript`,
  type: conf.type,
  to: conf.to,
  from: conf.from
})
async function creaetGroup (message) {
  try {
    const group = new Group()
    await group.createGroup()
    const groupId = group.getGroupId()
    console.log('groupId: ', groupId)
  } catch (e) {
    console.log(e)
  }
}
