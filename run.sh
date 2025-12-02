./gradlew assembleDebug

./gradlew installDebug && adb shell am start -n com.example.rubiksalgo/.MainActivity

./gradlew clean assembleDebug

export ANDROID_SERIAL=emulator-5554 && ./gradlew installDebug && adb shell am start -n com.example.rubiksalgo/.MainActivity
