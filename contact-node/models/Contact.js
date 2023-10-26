const mongoose = require('mongoose');

const contactSchema = new mongoose.Schema({
  name: String,
  email: String,
  description: String
  // Add more fields as needed
});

const Contact = mongoose.model('Contact', contactSchema);
module.exports={
    Contact
}