# Learn Python — Android App

Native Android wrapper (`com.learnpython.app`) for the existing Learn-Python Next.js UI, API backend, and Supabase database — generated without modifying any files in `web/` or `supabase/`.

## What is included

- **Full UI, Backend & DB preservation**: Loads the live Learn-Python app (`https://learnpythonprogram.vercel.app` by default, configurable via `LP_APP_URL`).
- **Dark monochrome shell**: Pure black status bar, navigation bar, and window theme matching `web/src/app/globals.css`.
- **Video & Interactive support**: JavaScript, DOM Storage (`localStorage`), IndexedDB, WebAssembly (for Pyodide), Cache Storage (for offline videos/tutorials), and fullscreen landscape video (`onShowCustomView`).
- **Native ergonomics**: Pull-to-refresh at scroll top, hardware back-button history navigation, file chooser support, offline retry screen, and Chrome Custom Tabs for external links.
- **Launcher icons**: Adaptive and legacy `mipmap-*` icons generated from `web/public/lp-logo.png`.

## Building the APK

Open the `android/` folder in **Android Studio** (JDK 17+, Android SDK 35) and run **Build > Build Bundle(s) / APK(s) > Build APK(s)**, or build from a machine with Gradle installed:

```bash
cd android
gradle wrapper          # one-time if gradlew is not generated yet
./gradlew assembleDebug # outputs app/build/outputs/apk/debug/app-debug.apk
```

### Testing against a local Next.js server

Start the web app in `web/` (`npm run dev`), then build/install the debug APK pointing at the Android emulator loopback host:

```bash
./gradlew installDebug -PLP_APP_URL=http://10.0.2.2:3000
```

> **Note on Google OAuth:** Embedded Android WebViews are blocked by Google OAuth (`403 disallowed_useragent`), so `accounts.google.com` opens in Chrome Custom Tabs while email/password + OTP sign-in (`/auth/email`) works directly inside the app.
