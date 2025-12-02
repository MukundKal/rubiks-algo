./gradlew assembleDebug

./gradlew clean assembleDebug

./gradlew installDebug && adb shell am start -n com.example.rubiksalgo/.MainActivity



adb pair 192.168.1.16:45993
adb connect 192.168.1.16:45735
adb disconnect 192.168.1.16:45993
adb devices

export ANDROID_SERIAL=192.168.1.16:45735 && ./gradlew installDebug && adb shell am start -n com.example.rubiksalgo/.MainActivity
