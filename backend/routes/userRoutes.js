const express = require('express');
const router = express.Router();
//const { registerUser, loginUser } = require('../controllers/userController');
const { registerUser, login } = require('../controllers/authController');

// Routes
//router.post('/register', registerUser);
//router.post('/login', loginUser);
router.post('/register', registerUser);
router.post('/login', login);

module.exports = router;
