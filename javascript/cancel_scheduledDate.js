const moment = require('moment-timezone')
const { config, Group } = require('coolsms-sdk-v4')
const conf = require('../config')

/*
 coolsms-message-v4 js example
 cancel scheduledDate
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
    const scheduledDate = moment().tz('Asia/Seoul').add(1, 'days').format('YYYY-MM-DD H:m:s')
    await group.setScheduledDate(scheduledDate)
    await group.cancelScheduled()
  } catch (e) {
    console.log(e)
  }
}
