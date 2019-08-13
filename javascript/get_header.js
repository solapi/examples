'use strict'
const conf = require('../config.js')

const moment = require('moment-timezone')
const crypto = require('crypto')
const cryptoRandomString = require('crypto-random-string')
const now = moment(new Date()).format('YYYY-MM-DD HH:mm:ss')
const salt = cryptoRandomString({ length: 64 })
const apiKey = conf.apiKey
const apiSecret = conf.apiSecret
const signatureKey = crypto.createHmac('sha256', apiSecret)
const signature = signatureKey.update(now + salt).digest('hex')

console.log('signature: ', signature)
console.log('header: ', `Authorization: HMAC-SHA256 apiKey=${apiKey}, date=${now}, salt=${salt}, signature=${signature}`)
