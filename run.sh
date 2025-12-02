./gradlew assembleDebug

./gradlew installDebug && adb shell am start -n com.example.rubiksalgo/.MainActivity


export ANDROID_SERIAL=emulator-5554 && ./gradlew installDebug && adb shell am start -n com.example.rubiksalgo/.MainActivity
