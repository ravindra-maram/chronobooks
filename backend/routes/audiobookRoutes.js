// path: chronobooks-backend/routes/audiobookRoutes.js
const express = require('express');
const axios = require('axios');
const router = express.Router();

router.get('/', async (req, res) => {
  const query = req.query.q || 'audiobook';
  try {
    const deezerRes = await axios.get(`https://api.deezer.com/search?q=${encodeURIComponent(query)}`);
    const books = deezerRes.data.data.map(track => ({
      title: track.title,
      author: track.artist.name,
      audioUrl: track.preview, // 30s preview
      coverUrl: track.album.cover_medium,
      description: track.album.title,
      chapters: [] // can be expanded if needed
    }));
    res.json(books);
  } catch (err) {
    console.error(err);
    res.status(500).json({ message: 'Failed to fetch from Deezer API' });
  }
});

module.exports = router;
