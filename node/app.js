var crypto = require('crypto');
var iv = new Buffer('0000000000000000');
var key = 'hi';

var encrypt = function(data, key) {
  var decodeKey = crypto.createHash('sha256').update(key, 'utf-8').digest();
  var cipher = crypto.createCipheriv('aes-256-cbc', decodeKey, iv);
  return cipher.update(data, 'utf8', 'hex') + cipher.final('hex');
};

var decrypt = function(data, key) {
  var encodeKey = crypto.createHash('sha256').update(key, 'utf-8').digest();
  var cipher = crypto.createDecipheriv('aes-256-cbc', encodeKey, iv);
  return cipher.update(data, 'hex', 'utf8') + cipher.final('utf8');
};


var jsonObj = {
  expiry: new Date().getTime(),
  customer: "Acme Inc",
  winkle: 'cha cha cha',
  plopsy: 'winkle indeed'
}

var data = JSON.stringify(jsonObj);

var enc = encrypt(data, key);
var dec = decrypt(enc, key);

console.log('ENCRYPTED: ', enc);
console.log('DECRYPTED: ', dec);
