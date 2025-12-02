# Wear OS Emulator Testing Guide

## 🎯 Overview

This guide helps you test the Rubik's Algorithm app on the Wear OS emulator. The app has been optimized to ensure **all content is visible**, including the algorithm text which is the most important element.

---

## ⚠️ Fixed Layout Issues

### What Was Wrong?
- **Problem**: Only "Step 1, Flip edge" visible, algorithm text cut off
- **Cause**: Card content too tall for emulator screen
- **Solution**: Made layout ultra-compact with scrollable content

### What's Now Fixed:
- ✅ Algorithm text always visible and prominent
- ✅ Scrollable card content (swipe up/down on card)
- ✅ Reduced spacing throughout
- ✅ Optimized for emulator screen size
- ✅ Algorithm displayed in large, bold monospace font

---

## 🚀 Quick Start

### 1. Create Wear OS Emulator (One-time setup)

```bash
# List available Wear OS system images
~/Library/Android/sdk/cmdline-tools/latest/bin/sdkmanager --list | grep wear

# Create large round watch emulator
echo "no" | ~/Library/Android/sdk/cmdline-tools/latest/bin/avdmanager create avd \
  --name "WearOS_Watch" \
  --package "system-images;android-33;android-wear;arm64-v8a" \
  --device "wearos_large_round" \
  --tag "android-wear"
```

### 2. Start Emulator

```bash
# Start the emulator (in a separate terminal)
~/Library/Android/sdk/emulator/emulator -avd WearOS_Watch
```

**Wait for emulator to fully boot** (you'll see the watch face)

### 3. Deploy App

```bash
# Navigate to project
cd rubiks-algo

# Use the deployment script (easiest)
./deploy.sh

# OR manually:
./gradlew clean assembleDebug
./gradlew installDebug
adb -s emulator-5554 shell am start -n com.example.rubiksalgo/.MainActivity
```

---

## 📱 Using the App on Emulator

### Navigation Methods:

#### Method 1: Rotary Input (Bezel Simulation)
The emulator simulates the physical bezel:

1. **Mouse Wheel**: Scroll up/down to navigate pages
2. **Circular Gesture**: Click and drag in circular motion on edge of watch face
3. **Keyboard**: 
   - Page Up / Page Down keys
   - Ctrl + Up/Down arrows

#### Method 2: Swipe Gestures
- **Swipe Left**: Next step
- **Swipe Right**: Previous step

#### Method 3: Scroll Card Content
- **Swipe Up/Down on Card**: If content is too tall, you can scroll within the card

### Expected Layout:

```
┌─────────────────┐
│    🕐 12:45     │  ← TimeText (clock)
├─────────────────┤
│      3/7        │  ← Page counter
├─────────────────┤
│  ┌───────────┐  │
│  │  Step 3   │  │  ← Step badge
│  ├───────────┤  │
│  │Move edge  │  │  ← Instruction
│  │  to down  │  │
│  ├───────────┤  │
│  │┌─────────┐│  │
│  ││U' L' U L││  │  ← ALGORITHM (prominent!)
│  ││U F U' F'││  │
│  │└─────────┘│  │
│  ├───────────┤  │
│  │💡 note... │  │  ← Optional note
│  └───────────┘  │
├─────────────────┤
│   • • ● • • •   │  ← Page indicator dots
└─────────────────┘
```

---

## 🔍 Verifying the Fix

### What You Should See Now:

#### ✅ Step 1: "Flip edge"
- Badge: "Step 1"
- Instruction: "Flip edge"
- **Algorithm: `F U' R U`** ← Should be clearly visible
- No note

#### ✅ Step 2: "Move edge to down (right)"
- Badge: "Step 2"
- Instruction: "Move edge to down (right)"
- **Algorithm: `U R U' R' U' F' U F`** ← Should be clearly visible
- No note

#### ✅ Step 3: "Move edge to down (left)"
- Badge: "Step 3"
- Instruction: "Move edge to down (left)"
- **Algorithm: `U' L' U L U F U' F'`** ← Should be clearly visible
- No note

If you still can't see the algorithm text:
1. Try scrolling down on the card (swipe up on the card area)
2. Check emulator screen size (should be "wearos_large_round")
3. See troubleshooting section below

---

## 🎮 Testing Checklist

Use this checklist to verify everything works:

### Layout Tests:
- [ ] App launches without errors
- [ ] Page counter shows "1/7" at top
- [ ] Step badge visible (e.g., "Step 1")
- [ ] Instruction text visible (e.g., "Flip edge")
- [ ] **Algorithm clearly visible in large font** (e.g., "F U' R U")
- [ ] Note visible when present (Steps 5, 6, 7)
- [ ] Page indicator dots at bottom
- [ ] No text cutoff on round edges
- [ ] Card can scroll if content is tall

### Navigation Tests:
- [ ] Mouse wheel scrolls between pages
- [ ] Page counter updates (1/7 → 2/7 → 3/7...)
- [ ] Page indicator dots update
- [ ] Swipe left goes to next page
- [ ] Swipe right goes to previous page
- [ ] Can't go before page 1
- [ ] Can't go past page 7

### Content Tests:
Verify each step shows correctly:
- [ ] Step 1: "F U' R U"
- [ ] Step 2: "U R U' R' U' F' U F"
- [ ] Step 3: "U' L' U L U F U' F'"
- [ ] Step 4: "F R U R' U' F'"
- [ ] Step 5: "R U R' U R U2 R' U" + note
- [ ] Step 6: "U R U' L' U R' U' L" + note
- [ ] Step 7: "R' D' R D" + note

---

## 🐛 Troubleshooting

### Issue: Can't see algorithm text

**Solution 1: Scroll the card**
```
The card content is now scrollable. Swipe up/down on the card itself
(not the screen edges) to scroll within the card.
```

**Solution 2: Check emulator size**
```bash
# Recreate with correct size
~/Library/Android/sdk/cmdline-tools/latest/bin/avdmanager delete avd --name WearOS_Watch

echo "no" | ~/Library/Android/sdk/cmdline-tools/latest/bin/avdmanager create avd \
  --name "WearOS_Watch" \
  --package "system-images;android-33;android-wear;arm64-v8a" \
  --device "wearos_large_round" \
  --tag "android-wear"
```

**Solution 3: Increase emulator window size**
```
Drag the emulator window corners to make it larger.
This gives you a better view of the watch screen.
```

### Issue: Emulator not starting

**Check if emulator exists:**
```bash
~/Library/Android/sdk/emulator/emulator -list-avds
# Should show: WearOS_Watch
```

**If not listed, create it:**
```bash
# See "Create Wear OS Emulator" section above
```

**Kill stuck emulator process:**
```bash
pkill -9 qemu-system
```

### Issue: Installation fails

**Check emulator is running:**
```bash
adb devices
# Should show: emulator-5554    device
```

**If shows "offline":**
```bash
adb kill-server
adb start-server
adb devices
```

**Uninstall old version:**
```bash
adb -s emulator-5554 uninstall com.example.rubiksalgo
./gradlew installDebug
```

### Issue: Rotary input not working

**On emulator, rotary input methods:**
1. **Mouse wheel** - Most reliable
2. **Circular drag** - Click and drag in circle on watch edge
3. **Keyboard shortcuts** - Page Up/Down

**Alternative:**
```
Use swipe gestures instead:
- Swipe left = Next
- Swipe right = Previous
```

### Issue: App crashes on launch

**View crash logs:**
```bash
adb -s emulator-5554 logcat | grep -E "(AndroidRuntime|rubiksalgo)"
```

**Common fixes:**
```bash
# Clear app data
adb -s emulator-5554 shell pm clear com.example.rubiksalgo

# Reinstall
./gradlew clean assembleDebug installDebug
```

---

## 🎨 Layout Optimizations Made

### Changes for Better Visibility:

| Element | Old Value | New Value | Reason |
|---------|-----------|-----------|--------|
| Card Height | 80% | 88% | More room for content |
| Top Spacing | 32dp | 20dp | Less wasted space |
| Counter Spacing | 8dp | 4dp | Compact layout |
| Card Padding | 16dp | 12dp H, 16dp V | Maximize content area |
| Badge Padding | 12dp x 4dp | 10dp x 3dp | Smaller, compact |
| Algorithm Font | title2 (24sp) | title1 (22sp) | Large & readable |
| Algorithm BG | 60% alpha | 15% alpha | Subtle highlight |
| Instruction | body1 | caption1 | Smaller to prioritize algo |
| Content Scroll | None | Vertical | Ensures all visible |

### Typography Hierarchy (by importance):
1. **Algorithm** (22sp, ExtraBold, Monospace) - THE MOST IMPORTANT
2. **Instruction** (caption1, SemiBold) - Secondary
3. **Step Badge** (caption2, Bold) - Tertiary
4. **Note** (caption3) - Optional info

---

## 📊 Performance Tips

### Faster Emulator:
```bash
# Use hardware acceleration (Intel Mac)
~/Library/Android/sdk/emulator/emulator -avd WearOS_Watch -gpu host

# Use more RAM
~/Library/Android/sdk/emulator/emulator -avd WearOS_Watch -memory 1024

# Enable snapshot for quick boot
~/Library/Android/sdk/emulator/emulator -avd WearOS_Watch -no-boot-anim
```

### Faster Builds:
```bash
# Skip unnecessary tasks
./gradlew installDebug --offline

# Use daemon
./gradlew --daemon installDebug

# Parallel execution
./gradlew installDebug --parallel
```

### Quick Reinstall (skip build if no code changes):
```bash
adb -s emulator-5554 install -r app/build/outputs/apk/debug/app-debug.apk
adb -s emulator-5554 shell am start -n com.example.rubiksalgo/.MainActivity
```

---

## 📸 Screenshots & Validation

### Take Screenshots:
```bash
# Capture current screen
adb -s emulator-5554 exec-out screencap -p > screenshot.png

# Record video
adb -s emulator-5554 shell screenrecord /sdcard/demo.mp4
# Press Ctrl+C to stop
adb -s emulator-5554 pull /sdcard/demo.mp4
```

### Validate Each Step:
```bash
# Script to navigate through all steps and capture screenshots
for i in {1..7}; do
  adb -s emulator-5554 exec-out screencap -p > "step_$i.png"
  # Swipe to next page
  adb -s emulator-5554 shell input swipe 300 200 100 200 100
  sleep 1
done
```

---

## 🔧 Advanced Debugging

### Enable Compose Layout Inspector:
```
1. Open Android Studio
2. Tools → Layout Inspector
3. Select emulator process
4. Navigate to app
5. Inspect composable hierarchy
```

### View Memory Usage:
```bash
adb -s emulator-5554 shell dumpsys meminfo com.example.rubiksalgo
```

### Monitor Performance:
```bash
# FPS monitoring
adb -s emulator-5554 shell dumpsys gfxinfo com.example.rubiksalgo
```

### Logcat Filtering:
```bash
# Only show app logs
adb -s emulator-5554 logcat --pid=$(adb -s emulator-5554 shell pidof -s com.example.rubiksalgo)

# Filter by tag
adb -s emulator-5554 logcat RubiksAlgo:D *:S
```

---

## ✅ Success Criteria

Your emulator test is successful when:

1. ✅ App launches without crashes
2. ✅ **All 7 algorithms are clearly visible** (not cut off)
3. ✅ Mouse wheel navigates between pages smoothly
4. ✅ Page counter updates correctly (1/7 through 7/7)
5. ✅ Swipe gestures work as fallback
6. ✅ No text cutoff on round edges
7. ✅ Card content scrollable if needed
8. ✅ All steps display correct data from repository

---

## 🎯 Next Steps

Once emulator testing is successful:

1. **Test on Real Device**: Galaxy Watch 4 Classic with physical bezel
2. **Performance Test**: Check battery usage and animation smoothness
3. **Usability Test**: Verify algorithm text readable in real-world use
4. **Edge Cases**: Test with screen always-on mode
5. **Publish**: Prepare for Play Store deployment

---

## 📚 Additional Resources

- [Wear OS Emulator Docs](https://developer.android.com/training/wearables/get-started/creating)
- [Rotary Input Testing](https://developer.android.com/training/wearables/user-input/rotary-input)
- [Compose Layout Inspector](https://developer.android.com/jetpack/compose/tooling#layout-inspector)
- [Horologist GitHub](https://github.com/google/horologist)

---

## 🆘 Still Having Issues?

### Quick Diagnostic:

```bash
# Run this diagnostic script
cat > diagnostic.sh << 'EOF'
#!/bin/bash
echo "=== Wear OS Emulator Diagnostic ==="
echo ""
echo "1. Emulator Status:"
~/Library/Android/sdk/emulator/emulator -list-avds
echo ""
echo "2. Connected Devices:"
adb devices
echo ""
echo "3. App Installation Status:"
adb -s emulator-5554 shell pm list packages | grep rubiksalgo
echo ""
echo "4. Last Build Time:"
ls -lh app/build/outputs/apk/debug/app-debug.apk
echo ""
echo "=== End Diagnostic ==="
EOF

chmod +x diagnostic.sh
./diagnostic.sh
```

### Common Issues Summary:

| Symptom | Likely Cause | Fix |
|---------|--------------|-----|
| Algorithm not visible | Content too tall | Scroll card content (swipe up) |
| Emulator won't start | Stuck process | `pkill -9 qemu-system` |
| Install fails | Old version | `adb uninstall com.example.rubiksalgo` |
| No rotary input | Emulator limitation | Use mouse wheel or swipe gestures |
| Crashes on launch | Corrupted cache | `./gradlew clean` |

---

**Happy Testing! 🎉**

If you can now see all the algorithms clearly, the migration is complete and successful! 🚀