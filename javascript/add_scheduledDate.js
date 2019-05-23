const moment = require('moment-timezone')
const { config, Group } = require('solapi')
const conf = require('../config')

/*
 solapi js example
 set scheduledDate to group
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
    const scheduledDate = moment().tz('Asia/Seoul').add(1, 'days').toISOString()
    console.log(await group.setScheduledDate(scheduledDate))
  } catch (e) {
    console.log(e)
  }
}
