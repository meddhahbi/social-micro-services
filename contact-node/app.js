const express = require('express');
const mongoose = require('mongoose');
const connection = require("./db");
const app = express();

const router = express.Router();

const Contact = require('./models/Contact');
const port = process.env.PORT || 3001;

app.use(express.json());

//database connection
connection();

const contactRouter = require('./routes/contactRoute'); // Adjust the path

app.use('/api', contactRouter); // Use a base path, e.g., '/api'


app.listen(port, () => {
  console.log(`Server is running on port ${port}`);
});
