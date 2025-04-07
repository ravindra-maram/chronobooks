# ⌚ ChronoBooks

![ChronoBooks Banner](assets/banner.png) <!-- Optional: replace with actual banner if available -->

> **Standalone Audiobook Player for Android Wear OS**  
> Designed for seamless, on-the-go listening — no phone required.

---

## 📱 About

**ChronoBooks** is a standalone audiobook application developed for **Android Wear OS smartwatches**, inspired by Audible and reimagined from the original ShelfTime concept. It enables users to **stream, download, and control audiobooks directly from their wrist**, offering a sleek and minimal interface tailored for small screens.

Created to bridge the gap left by major audiobook platforms on smartwatches, ChronoBooks is optimized for hands-free listening during workouts, commutes, or downtime.

---

## 🚀 Features

- 🔐 **User Authentication** – Secure login for personalized libraries.
- 📚 **Audiobook Library** – Browse available audiobooks from your server.
- 🔍 **Chapter Info** – View detailed chapter metadata.
- 🎧 **Streaming & Downloading** – Listen online or offline.
- ⏯️ **Playback Controls** – Play, pause, rewind, and fast-forward.
- 🔄 **Progress Sync** – Continuously sync playback across devices.
- 📴 **Offline Mode** – Full access to downloaded content without internet.

---

## 🛠️ In Progress / TODO

- 🔊 Volume Control
- 🐢 Playback Speed Adjustment
- 🖼️ Offline Cover Caching
- 📖 Fetch Unfinished Audiobooks
- 🔎 Search Functionality

---

## 🎨 Theme & Visual Design

ChronoBooks uses a **cohesive, elegant theme** based on your logo colors:

| Element             | Description         | HEX Code   |
|---------------------|---------------------|------------|
| Primary Color       | Dark Teal           | `#254D4D`  |
| Accent              | Golden Yellow       | `#D6A843`  |
| Background          | Soft Beige          | `#F5F0E6`  |
| Primary Text        | Charcoal Black      | `#2A2A2A`  |
| Secondary Text      | Muted Gray          | `#8A8A8A`  |

> 🖋️ Font: [Montserrat Medium](https://fonts.google.com/specimen/Montserrat)

---

## 📦 Tech Stack

- **Java** – Application logic
- **Android SDK for Wear OS**
- **ExoPlayer** – Audio playback
- **Retrofit** – Networking and API integration
- **ViewBinding** – Safe, efficient UI handling
- **Material Design for Wear OS** – UI components

---

## 📁 Project Structure

```plaintext
ChronoBooks/
├── ui/               # Wear OS optimized UIs
│   ├── MainActivity.java
│   ├── PlayerActivity.java
│   └── AudiobookListFragment.java
│
├── playback/         # ExoPlayer service & media session
├── data/             # Models, DTOs
├── network/          # Retrofit API services
├── utils/            # Helpers (time, theming)
├── auth/             # Login screen and session handling
├── repository/       # Data access abstraction
├── viewmodel/        # MVVM ViewModels (if used)
└── MainActivity.java # App entry point
```
---

## 📄 License

MIT License – see the [LICENSE](LICENSE) file for details.

---

### 👋 Questions?

Feel free to [open an issue](https://github.com/your-repo/ChronoBooks/issues) or connect with us for feedback, contributions, or suggestions.

