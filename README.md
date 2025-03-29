# ⌚ ChronoBooks

![ChronoBooks Banner](assets/banner.png) <!-- Optional: add your custom banner here -->

> **Standalone Audiobook Player for Android Wear OS**  
> Designed for seamless, on-the-go listening — no phone required.

---

## 📱 About

**ChronoBooks** is a standalone audiobook application developed for **Android Wear OS smartwatches**, reimagined from the original ShelfTime concept. It enables users to **stream, download, and control audiobooks directly from their wrist**, all within an elegant and minimal interface optimized for small screens.

Originally built because most audiobook platforms lack smartwatch support, ChronoBooks fills the gap with a clean, user-friendly experience that's perfect for workouts, commutes, and hands-free moments.

---

## 🚀 Features

- 🔐 **User Authentication** – Log in securely to your audiobook account.
- 📚 **Audiobook Library** – Browse all audiobooks available from the connected server.
- 🔍 **Chapter Info Retrieval** – View metadata and structure for each book.
- 🎧 **Stream & Listen** – Stream audiobooks with real-time playback.
- ⏮️⏭️ **Playback Controls** – Pause, play, rewind, and fast-forward easily.
- ⬇️ **Full Audiobook Download** – Download entire books for offline access.
- 🔄 **Progress Sync** – Sync playback progress with your account/server.
- 📴 **Offline Mode** – Continue listening without internet, sync later automatically.

---

## 🛠️ In Progress / TODO

- 🔊 Volume Control
- 🐢 Speed Control
- 🖼️ Offline Cover Support
- 📖 Fetching Unfinished Audiobooks
- 🔎 Audiobook Search

---

## 🎨 Theme & Visual Design

ChronoBooks uses a cohesive color theme throughout the app for readability and aesthetic balance on wearable displays:

| Element             | Color             | HEX Code   |
|---------------------|-------------------|------------|
| Primary Color       | Dark Teal         | `#254D4D`  |
| Accent              | Golden Yellow     | `#D6A843`  |
| Background          | Soft Beige        | `#F5F0E6`  |
| Primary Text        | Charcoal Black    | `#2A2A2A`  |
| Secondary Text      | Muted Gray        | `#8A8A8A`  |

---

## 📦 Built With

- **Java** for Android app logic
- **Android Wear OS SDK**
- **ExoPlayer** for robust audio playback
- **Retrofit** for HTTP networking
- **ViewBinding** for safe, efficient UI interaction
- **Material Design for Wear OS**

---

## 📁 Project Structure

```plaintext
ChronoBooks/
├── ui/               # Wear OS optimized UIs
├── playback/         # ExoPlayer service & media session
├── data/             # Models, DTOs, and adapters
├── network/          # Retrofit API handlers
├── utils/            # Helper classes
└── MainActivity.java # App entry point

## 📄 License

MIT License – see the [LICENSE](LICENSE) file for details.

---

### 👋 Questions?

Feel free to [open an issue](https://github.com/your-repo/ChronoBooks/issues) or connect with us for feedback, contributions, or suggestions.

