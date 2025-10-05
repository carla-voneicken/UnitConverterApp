## 📏 Unit Converter App

An Android app that converts between various units of length, weight, volume, and temperature — in real time.
Developed in Android Studio using Kotlin and XML.

### 🧠 Overview

This app allows users to enter a value in any field, instantly showing all equivalent units without selecting “from” or “to” units.
It supports:

- Real-time, simultaneous conversion in all fields
- Optional toggle between UK Imperial and US Customary volume systems
- Swiping or tapping via BottomNavigationView to switch between fragments (e.g. volume, distance, weight, temperature)

### 🏗️ App Structure

#### Architecture
- 4 Fragments (Length, Weight, Volume, Temperature)
- Each fragment uses its own layout and Kotlin logic
- ViewPager2 manages fragments, synced with BottomNavigationView
- Enum classes define unit types and conversion logic

#### Core Features
- Real-time conversions using TextWatcher
- Custom logic for temperature conversions (Celsius, Fahrenheit, Kelvin)
- Keyboard behavior handled dynamically (hide/show, dismiss outside taps)
- Dual layouts for portrait and landscape modes

### 🧩 Technologies Used

- Kotlin
- XML
- Android Studio

### 🚧 Challenges & Learnings

This project was inspired by a simple beginner’s converter app, but expanded into a fully dynamic version.
Key learnings include:
- Managing real-time input and preventing infinite loops with TextWatcher
- Structuring clean, modular fragment code
- Testing and debugging complex UI behaviors
- Collaborating with AI tools (ChatGPT, Gemini) while learning to verify and refine code

**Known issue:**
🔹 Minor focus bug when selecting EditTexts after switching certain fragments — still under investigation.

### 🚀 Future Improvements

- Fix focus bug
- Add compound unit conversions (e.g. feet + inches → meters)
- Add settings for:
    - Decimal format
    - Language
    - Decimal separator (. / ,)
    - Custom visible units

### 🎥 Preview

[Video Demo on Youtube](https://youtube.com/shorts/eX_xU0tqfas?feature=share)

### 👩‍💻 Author

Created with 💙 by Carla von Eicken
[LinkedIn](https://www.linkedin.com/in/carla-von-eicken/) | [GitHub](https://github.com/carla-voneicken)
