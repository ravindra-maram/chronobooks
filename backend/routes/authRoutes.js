const express = require('express');
const router = express.Router();
const { registerUser, login } = require('../controllers/authController');

// This MUST point to the file containing the hashing logic
router.post('/register', registerUser);
router.post('/login', login);

module.exports = router;
