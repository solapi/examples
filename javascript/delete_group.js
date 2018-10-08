const { config, Group } = require('coolsms-sdk-v4')
const conf = require('../config')

/*
 coolsms-message-v4 js example
 delete group
*/

config.init({
  apiKey: conf.apiKey,
  apiSecret: conf.apiSecret
})
deleteGroup()
async function deleteGroup () {
  try {
    const group = new Group()
    await group.createGroup()
    const groupId = group.getGroupId()
    await Group.deleteGroup(groupId)
  } catch (e) {
    console.log(e)
  }
}
