#! /bin/bash

ANDROID_APIS=(14 16 19 21 22)

chmod +x gradlew

# mostrar las api's instaladas para propósitos de debugging
android list targets

function main {
  for api in ${ANDROID_APIS[@]}; do
    echo "Launching emulator for android-$api..."
    launch_emulator_for "android-$api"
    echo "Running tests on android-$api..."
    ./gradlew build connectedCheck
    echo "Killing emulator for android-$api..."
    kill_emulator
  done
}

function launch_emulator_for {
  echo no | android create avd --force -n test -t $1 --abi armeabi-v7a
  emulator -avd test -no-skin -no-audio -no-window &
  android-wait-for-emulator
  adb shell input keyevent 82 &
}

function kill_emulator {
  killall emulator
  killall adb
  android delete avd -n test
}

main $@
