const mongoose = require('mongoose');

const chapterSchema = new mongoose.Schema({
    title: String,
    duration: String,
});

const audiobookSchema = new mongoose.Schema({
    title: { type: String, required: true },
    author: { type: String, required: true },
    coverUrl: String,
    audioUrl: String,
    description: String,
    chapters: [chapterSchema],
}, { timestamps: true });

module.exports = mongoose.model('Audiobook', audiobookSchema);
