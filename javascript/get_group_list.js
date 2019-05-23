const { config, Group } = require('solapi-sdk-v4')
const conf = require('../config')

/*
 solapi js example
 get group list
*/

config.init({
  apiKey: conf.apiKey,
  apiSecret: conf.apiSecret
})
getGroupList()
async function getGroupList () {
  try {
    const group = new Group()
    await group.createGroup()
    console.log(await Group.getMyGroupList())
  } catch (e) {
    console.log(e)
  }
}
