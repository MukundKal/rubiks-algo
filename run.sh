./gradlew assembleDebug

./gradlew installDebug && adb shell am start -n com.example.rubiksalgo/.MainActivity
