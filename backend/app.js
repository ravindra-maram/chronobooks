require('dotenv').config();
const express = require('express');
const cors = require('cors');
const connectDB = require('./config/db'); // use helper
const userRoutes = require('./routes/userRoutes');
const audiobookRoutes = require('./routes/audiobookRoutes');

const app = express();
const PORT = 8080;

// Middleware
app.use(cors());
app.use(express.json());

// MongoDB Connection
connectDB(); // cleaner

// Routes
app.use('/api/users', userRoutes);      ///api/users/register, /login
app.use('/api/audiobooks', audiobookRoutes);

app.get('/api/ping', (req, res) => {
  res.send('API is reachable!');
});

// Start Server
app.listen(PORT, () => {
    console.log(`Server running on http://localhost:${PORT}`);
});
