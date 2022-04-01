const express = require('express')
const router = express.Router()
// import file
const database = require("../../config")

// Get User Info
router.get("/", (request, response) => {
    console.log(request)
    var name = request.query.name;
    const query = "SELECT name, email, details, date, document, tokens, if(isAdmin=1,  'true', 'false') as isAdmin FROM user WHERE name = ?";
    const args = [name]
    database.query(query, args, (err, res) => {
            if (err) throw err 
            console.log(res);
            response.status(200).json({
                "name" : res[0]["name"],
                "email" : res[0]["email"],
                "isAdmin" : res[0]["isAdmin"],
                "details": res[0]["details"],
                "tokens": res[0]["tokens"],
                "date":res[0]["date"].toString(),
                "document": res[0]["document"]
            })
        });
    })
module.exports = router