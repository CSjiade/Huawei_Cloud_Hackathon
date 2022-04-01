const express = require('express')
const router = express.Router()

// import file
const database = require("../../config")

// Get All recently_viewed products
router.get("/", (request, response) => {
    var userId = request.query.userId;
    var page = request.query.page;
    var page_size = request.query.page_size;

    if(page == null || page < 1){
        page = 1;
    }
 
    if(page_size == null){
        page_size = 20;
    }

    // OFFSET starts from zero
    const offset = page - 1;
    // OFFSET * LIMIT
    page = offset * page_size;

    const args = [
        userId,
        userId,
        userId,
        parseInt(page_size),
        parseInt(page)
    ];
    
    const query = `SELECT product.id,
                          history.name,
                          product.product_name,
                          product.price,
                          product.image,
                          product.category,
                          product.quantity,
                          product.supplier,
                          product.age,
                          product.description,
                          (SELECT IF(COUNT(*) >= 1, TRUE, FALSE) FROM favorite WHERE favorite.user_id = ? AND favorite.product_id = product.id) as isFavourite,
                          (SELECT IF(COUNT(*) >= 1, TRUE, FALSE) FROM cart WHERE cart.user_id = ? AND cart.product_id = product.id) as isInCart
                          FROM history JOIN product JOIN user 
                          ON history.product_id = product.id AND history.user_id = user.id 
                          WHERE history.user_id = ? 
                          LIMIT ? OFFSET ?`;

    database.query(query, args, (error, result) => {
        if(error) throw error;
        response.status(200).json({
            "page": offset + 1,
            "error" : false,
            "history" : result
        })

    })
});

// Add to History
router.post("/add", (request, response) => {
    const userId = request.body.userId
    const productId = request.body.productId
    const name = request.body.name;
  
    const query = "INSERT INTO history(user_Id, product_Id,name) VALUES(?, ?,?)"

    
    const args = [userId, productId,name]

    database.query(query, args, (error, result) => {
        if (error) {
            if (error.code === 'ER_DUP_ENTRY') {
                response.status(500).send("Deplicate Entry")
            } else {
                throw error;
            }
        } else {
            response.status(200).send("Added to History")
        }
    });
});


// Delete History
router.delete("/remove", (request, response) => {

    const query = "DELETE FROM history"

    database.query(query, (error, result) => {
        if(error) throw error
        response.status(200).send("Removed All From History")
    });
});
     

module.exports = router