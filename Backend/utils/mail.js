const nodemailer = require('nodemailer');

const serverSupportMail = 'mardroid.apps@gmail.com'
const serverSupportPassword = process.env.EMAIL_PASSWORD

const transporter = nodemailer.createTransport({
  service: 'gmail',
  auth: {
    user: serverSupportMail,
    pass: serverSupportPassword
  }
});

function sendOptMail(email, otpCode){
  
  var mailOptions = {
    from: 'jdlian505@gmail.com',
    to: email,
    subject: 'Souq password assistance',
    text: 'To authenticate, please use the following One Time Password (OTP): \n' + otpCode + '\nDo not share this OTP with anyone.\n Misa takes your account security very seriously.'+
    '\n Misa will never ask you to disclose or varify your Souq password, OTP, credit card, or banking account number'
  };
  
  
  transporter.sendMail(mailOptions, function(error, info){
    if (error) {
      console.log(error);
    } else {
      console.log('Email sent: ' + info.response);
    }
  });    
  
}

function getRandomInt(min, max) {
  min = Math.ceil(min);
  max = Math.floor(max);
  return Math.floor(Math.random() * (max - min)) + min; 
}


module.exports.sendOptMail = sendOptMail;
module.exports.getRandomInt = getRandomInt;



