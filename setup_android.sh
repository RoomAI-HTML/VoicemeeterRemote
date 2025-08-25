#!/bin/bash
echo "=== Setup Android SDK ==="

# Installa Java
sudo apt update
sudo apt install -y openjdk-17-jdk wget unzip

# Scarica Android SDK
cd ~
wget https://dl.google.com/android/repository/commandlinetools-linux-9477386_latest.zip
unzip commandlinetools-linux-9477386_latest.zip

# Setup SDK
export ANDROID_HOME=$HOME/android-sdk
mkdir -p $ANDROID_HOME/cmdline-tools
mv cmdline-tools $ANDROID_HOME/cmdline-tools/latest

# Aggiungi al PATH
echo 'export ANDROID_HOME=$HOME/android-sdk' >> ~/.bashrc
echo 'export PATH=$PATH:$ANDROID_HOME/cmdline-tools/latest/bin:$ANDROID_HOME/platform-tools' >> ~/.bashrc
source ~/.bashrc

# Installa componenti Android
yes | $ANDROID_HOME/cmdline-tools/latest/bin/sdkmanager "build-tools;34.0.0" "platforms;android-34" "platform-tools"

echo "✅ Android SDK installato!"
echo "Ora esegui: cd /workspaces/VoicemeeterRemote && ./gradlew assembleDebug"