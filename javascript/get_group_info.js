const { config, Group } = require('coolsms-sdk-v4')
const conf = require('../config')

/*
 coolsms-message-v4 js example
 get group info
*/

config.init({
  apiKey: conf.apiKey,
  apiSecret: conf.apiSecret
})
getGroupInfo()
async function getGroupInfo () {
  try {
    const group = new Group()
    await group.createGroup()
    const groupId = group.getGroupId()
    console.log(await Group.getInfo(groupId))
  } catch (e) {
    console.log(e)
  }
}
