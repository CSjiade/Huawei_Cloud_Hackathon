const mysql = require('mysql');

 const connectionPool = mysql.createPool({
     connectionLimit: 10,
     host: '',
     user: '',
     password: '',
     database: '',
 })


module.exports = connectionPool;