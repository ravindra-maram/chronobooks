const Audiobook = require('../models/Audiobook');

// GET all audiobooks
exports.getAllAudiobooks = async (req, res) => {
    try {
        const books = await Audiobook.find();
        res.json(books);
    } catch (error) {
        console.error('Error fetching audiobooks:', error);
        res.status(500).json({ message: 'Failed to fetch audiobooks' });
    }
};

// POST: Add a new audiobook
exports.addAudiobook = async (req, res) => {
    try {
        const newBook = new Audiobook(req.body);
        await newBook.save();
        res.status(201).json(newBook);
    } catch (error) {
        console.error('Error saving audiobook:', error);
        res.status(500).json({ message: 'Failed to save audiobook' });
    }
};
