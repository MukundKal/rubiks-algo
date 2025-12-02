~/Library/Android/sdk/emulator/emulator -list-avds

~/Library/Android/sdk/cmdline-tools/latest/bin/avdmanager list device | grep "wear"

echo "no" | ~/Library/Android/sdk/cmdline-tools/latest/bin/avdmanager create avd \
  --name "WearOS_Watch" \
  --package "system-images;android-33;android-wear;arm64-v8a" \
  --device "wearos_large_round" \
  --tag "android-wear"


~/Library/Android/sdk/emulator/emulator -avd WearOS_Watch

./gradlew clean assembleDebug


./gradlew installDebug && adb -s emulator-5554 shell am start -n com.example.rubiksalgo/.MainActivity
