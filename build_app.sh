#!/bin/bash
export JAVA_HOME=/nix/store/65qpdkc33j5wqzxvz8c23zhgms8hl35y-openjdk-21.0.9+10
export ANDROID_HOME=/home/user/android-sdk
export ANDROID_SDK_ROOT=/home/user/android-sdk
cd /home/user/android-sdk/cmdline-tools/latest/bin
yes | ./sdkmanager --licenses
./sdkmanager "platforms;android-34" "build-tools;34.0.0"
cd /home/user/VectorVision
./gradlew build