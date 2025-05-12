# IrisQualityCapture

An Android app for **biometric iris image capture**, featuring:
- Real-time **MediaPipe face mesh detection**
- Accurate **Camera2 autofocus and region-of-interest targeting**
- **OpenCV quality evaluation** for dataset-grade image filtering
- **Stable flashlight (torch) control** for uniform eye illumination
- Automated file naming and dataset generation
- Designed for **biometric dataset collection and research**

---

## 📱 Features

- 📸 Camera2 + OpenCV + MediaPipe pipeline
- 👁️ Detects left and right eye landmarks in real-time
- 🎯 Locks focus on eye region before capture
- 💡 Constant flashlight (torch) mode to avoid flicker
- ✅ Image quality evaluation (ISO/IEC 29794-6 inspired)
- 📝 Dataset-friendly file naming:
```
subjectID_sessionID_trial_left.png
subjectID_sessionID_trial_left_1.png
subjectID_sessionID_trial_left_2.png
subjectID_sessionID_trial_left_3.png
```
- 💾 All images saved in `Downloads` folder
- 🚀 Ready for large-scale research dataset creation

---

## 🛠️ Dependencies

### Required:
- **Android Studio Hedgehog (or newer)**
- **Android device running Android 8.0+ (API 26+)**
- OpenCV Android SDK (`System.loadLibrary("opencv_java4")`)
- MediaPipe Tasks SDK (Face Landmarker / Face Mesh Model)
- Android Camera2 API

👉 We recommend real devices. **Emulators will NOT work** due to lack of Camera2 + OpenCV hardware support.

---

## 🚀 Installation & Setup

### 1️⃣ Clone the repo
```bash
git clone https://github.com/YOUR_USERNAME/IrisQualityCapture.git
cd IrisQualityCapture
```

### 2️⃣ Open in Android Studio
- Open `IrisQualityCapture` folder
- Let Gradle sync complete

### 3️⃣ Download & integrate OpenCV Android SDK
- Download OpenCV Android SDK from: https://opencv.org/releases/
- Import `sdk/native/libs` into your project OR copy `.so` files into your app's `jniLibs` folder
- Add OpenCV initialization:  
```java
static {
    System.loadLibrary("opencv_java4");
}
```

### 4️⃣ Integrate MediaPipe Tasks SDK
- Follow [MediaPipe Android Tasks setup](https://developers.google.com/mediapipe/solutions/vision/face_landmarker/android)
- Add the dependencies in `build.gradle`:
```gradle
implementation 'com.google.mediapipe:tasks-vision:latest-version'
```
👉 replace `latest-version` with the current stable version.

### 5️⃣ Replace quality server URL
In `MainActivity3.java`, set your real server:
```java
String serverUrl = "https://your-server-url/analyze_iris";
```

### 6️⃣ Build + Run
- Connect Android device via USB
- Run app from Android Studio

👉 First launch will ask for Camera and Storage permissions.

---

## 🎮 Using the App

1. Enter:
    - Subject ID
    - Session ID
    - Trial Number
2. Position subject’s face in frame
3. App detects eyes → locks focus → evaluates image quality
4. If quality passes, image is saved automatically
5. Torch stays ON for stable lighting
6. Repeat for left and right eyes
7. Images stored in `/Downloads/`

👉 You can customize thresholds for quality scoring in `MainActivity3.java → thresholds map`

---

## 🎯 Quality Evaluation (default settings)

| Metric | Threshold |
|-------|-----------|
| Overall Quality | ≥ 50 |
| Sharpness | ≥ 70 |
| Others | optional (disabled by default) |

👉 Only images meeting all thresholds will be saved.

---

## 📝 Project Structure

```
/app/src/main/java/com/example/irisqualitycapture/
├── medium/
│   ├── CameraConnectionFragment.java   → Camera2 + focus + torch control
│   ├── MainActivity3.java              → Main capture workflow
│   ├── OverlayView.java                → Eye rectangle overlays
│   └── ImageUtils.java                 → Image conversion helpers
```

---

## ⚠️ Notes & Limitations

- App designed for **internal research dataset capture**
- Not for biometric matching in production systems (yet)
- Requires real Android device with Camera2 support

---

## 📋 License

This project is licensed under the MIT License.

---

## 🙏 Acknowledgements

- Google MediaPipe Tasks SDK
- OpenCV Android SDK
- Android Camera2 API
- ISO/IEC 29794-6 Biometrics Quality Guidelines

---

## 💡 Future Improvements (Roadmap)

- Add support for front camera
- In-app live quality feedback overlay
- Local fallback quality scoring (no server dependency)
- Dataset export + dataset labeling automation
