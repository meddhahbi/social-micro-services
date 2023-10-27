// Create a 'contacts' route

const express = require('express');
const router = express.Router();

const {Contact} = require('../models/Contact'); // Import your model

// Define your routes and controller functions
// e.g., route to create a new contact
router.route('/contacts').post( async (req, res) => {
  try {
    const contact = new Contact(req.body);
    console.log(contact)
    await contact.save();
    res.status(200).send(contact);
  } catch (error) {
    res.status(400);
        throw new Error(error.message);
  }
});

router.get('/contacts', async (req, res) => {
    try {
      const contacts = await Contact.find();
      res.json(contacts);
    } catch (error) {
        res.status(400);
        throw new Error(error.message);
    }
  });

  

module.exports = router;
