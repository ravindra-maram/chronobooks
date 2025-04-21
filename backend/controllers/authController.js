const User = require('../models/User');
const jwt = require('jsonwebtoken');
const bcrypt = require('bcryptjs');
require('dotenv').config();

const generateToken = (user) => {
    return jwt.sign({ id: user._id }, process.env.JWT_SECRET, { expiresIn: '7d' });
};

exports.registerUser = async (req, res) => {
    const { email, password } = req.body;
    console.log("📨 Register Request Body:", req.body);

    try {
        const existingUser = await User.findOne({ email });
        if (existingUser) {
            console.log("User already exists:", email);
            return res.status(409).json({ message: 'User already exists' });
        }

        const hashedPassword = await bcrypt.hash(password, 10); // Hash applied here
        const newUser = new User({ email, password: hashedPassword });

        await newUser.save();
        const token = generateToken(newUser);
        return res.status(201).json({ token, message: 'User registered successfully!' });

    } catch (error) {
        console.error("Registration Error:", error);
        return res.status(500).json({ message: 'Registration failed due to server error.' });
    }
};


exports.login = async (req, res) => {
    const { email, password } = req.body;

    try {
        const user = await User.findOne({ email });
        if (!user) {
            return res.status(401).json({ message: 'Invalid credentials' });
        }

        const isMatch = await bcrypt.compare(password, user.password);
        if (!isMatch) {
            return res.status(401).json({ message: 'Invalid credentials' });
        }

        const token = jwt.sign({ id: user._id }, process.env.JWT_SECRET, { expiresIn: '7d' });

        return res.status(200).json({
            token, // ✅ this is the key your app expects
            message: 'Login successful'
        });

    } catch (error) {
        return res.status(500).json({ message: 'Login failed' });
    }
};