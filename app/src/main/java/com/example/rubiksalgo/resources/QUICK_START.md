# Quick Start Guide - Rubik's Algo Wear OS App

## ✅ Migration Complete!

Your Android app has been successfully migrated to **Wear OS with bezel navigation support**.

---

## 🎯 What Changed?

- ✅ **Bezel Navigation**: Rotate the physical bezel on Galaxy Watch 4 Classic to navigate
- ✅ **Wear OS Optimized**: UI redesigned for 1.4" round AMOLED display
- ✅ **No Buttons**: Cleaner interface - bezel provides better UX than on-screen buttons
- ✅ **Same Functionality**: All 7 Rubik's algorithm steps preserved

---

## 🚀 Deploy to Galaxy Watch 4 Classic

### Prerequisites
- Galaxy Watch 4 Classic running Wear OS 5
- USB debugging enabled on watch
- Same Wi-Fi network for watch and computer

### Step 1: Enable Developer Mode on Watch

```
1. Open Settings on watch
2. Scroll to "About watch"
3. Tap "Software info"
4. Tap "Software version" 7 times rapidly
5. "Developer mode enabled" message appears
```

### Step 2: Enable ADB Debugging

```
1. Go back to main Settings
2. Scroll down to "Developer options" (new menu)
3. Enable "ADB debugging"
4. Enable "Debug over Wi-Fi"
5. Note the IP address shown (e.g., 192.168.1.100:5555)
```

### Step 3: Connect from Computer

```bash
# Connect to watch via Wi-Fi
adb connect 192.168.1.100:5555

# Verify connection
adb devices
# Should show: 192.168.1.100:5555    device
```

### Step 4: Build & Install

```bash
# Navigate to project directory
cd rubiks-algo

# Build and install in one command
./gradlew installDebug

# Wait for "BUILD SUCCESSFUL" and "INSTALL SUCCESS"
```

### Step 5: Launch App

```
Option 1: Find "RUBIK'S/LAYER-BY-LAYER" in app drawer on watch
Option 2: Run command: adb shell am start -n com.example.rubiksalgo/.MainActivity
```

---

## 🎮 How to Use

### Navigation:
- **Rotate bezel clockwise** → Next step
- **Rotate bezel counter-clockwise** → Previous step
- **Fast rotation** → Skip multiple steps
- **Swipe** → Also works as fallback

### UI Elements:
- **Top**: Current time (TimeText)
- **Center**: Step number counter (e.g., "3/7")
- **Main Area**: Rubik's algorithm card
- **Bottom**: Page indicator dots
- **Right Edge**: Vignette fade effect

---

## 🔧 Development Commands

### Build APK Only:
```bash
./gradlew assembleDebug
# APK location: app/build/outputs/apk/debug/app-debug.apk
```

### Clean Build:
```bash
./gradlew clean assembleDebug
```

### Install to Connected Watch:
```bash
./gradlew installDebug
```

### Uninstall from Watch:
```bash
adb uninstall com.example.rubiksalgo
```

### View Logs:
```bash
adb logcat | grep rubiksalgo
```

---

## 🐛 Troubleshooting

### "Device not found" when running adb connect
- ✅ Ensure watch and computer are on same Wi-Fi network
- ✅ Verify IP address is correct (check in Developer options)
- ✅ Try disabling and re-enabling "Debug over Wi-Fi"

### "INSTALL_FAILED_UPDATE_INCOMPATIBLE"
- ✅ Uninstall old version first: `adb uninstall com.example.rubiksalgo`
- ✅ Then reinstall: `./gradlew installDebug`

### App installs but doesn't appear
- ✅ Swipe up from watch face to see app drawer
- ✅ Look for "RUBIK'S/LAYER-BY-LAYER"
- ✅ Or launch via: `adb shell am start -n com.example.rubiksalgo/.MainActivity`

### Bezel navigation not working
- ✅ Ensure you're using Galaxy Watch 4 Classic (not regular Watch 4)
- ✅ Only Classic model has physical rotating bezel
- ✅ Try swiping as fallback - it still works

### Build errors
- ✅ Run: `./gradlew clean`
- ✅ Ensure you have JDK 11 installed
- ✅ Run: `./gradlew --version` to verify Gradle setup

---

## 📋 Technical Details

### Dependencies Added:
- `androidx.wear:wear:1.3.0` - Wear OS core
- `androidx.wear.compose:compose-material:1.3.1` - Wear UI components
- `androidx.wear.compose:compose-foundation:1.3.1` - Foundation layer
- `com.google.android.horologist:horologist-compose-layout:0.6.8` - Rotary input
- `com.google.android.horologist:horologist-composables:0.6.8` - Rotary helpers

### Key Implementation:
```kotlin
// This modifier connects bezel rotation to pager navigation
.rotaryWithScroll(
    scrollableState = pagerState,
    focusRequester = focusRequester
)
```

### Minimum Requirements:
- **Min SDK**: 30 (Wear OS 3.0)
- **Target SDK**: 36 (Android 14)
- **Compile SDK**: 36

---

## 🎯 Testing Checklist

After deployment, verify:

- [ ] App launches successfully
- [ ] All 7 steps visible
- [ ] Page counter shows "1/7", "2/7", etc.
- [ ] Bezel rotation clockwise moves to next step
- [ ] Bezel rotation counter-clockwise moves to previous step
- [ ] Swipe left/right still works
- [ ] All text is readable on watch screen
- [ ] No text cutoff on round edges
- [ ] Step badge visible
- [ ] Algorithm text (e.g., "F U' R U") clear
- [ ] Page indicator dots appear at bottom
- [ ] Time displays at top

---

## 📚 Learn More

- **Full Migration Details**: See `WEAR_OS_MIGRATION.md`
- **Horologist Docs**: https://github.com/google/horologist
- **Wear Compose Guide**: https://developer.android.com/training/wearables/compose
- **Rotary Input Guide**: https://developer.android.com/training/wearables/user-input/rotary-input

---

## ✨ Features

### Current:
- ✅ Physical bezel navigation
- ✅ 7 Rubik's algorithm steps
- ✅ Layer-by-layer solving method
- ✅ Clean, minimal design
- ✅ AMOLED-optimized dark theme

### Potential Enhancements:
- [ ] Haptic feedback on page change
- [ ] Ambient mode support (always-on display)
- [ ] Voice commands ("Next step")
- [ ] Watch face complication
- [ ] Quick access tile

---

## 🎉 Success!

Your Rubik's Algorithm app is now running on Wear OS with full bezel navigation support!

**Enjoy solving your Rubik's cube with your Galaxy Watch 4 Classic!** 🎲⌚

---

**Questions?** Check `WEAR_OS_MIGRATION.md` for detailed technical documentation.