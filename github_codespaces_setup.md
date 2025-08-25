# Setup GitHub Codespaces (GRATIS)

## Passi:

1. **Crea account GitHub** (gratis)
2. **Carica il progetto**:
   ```
   git init
   git add .
   git commit -m "Voicemeeter Remote"
   git remote add origin https://github.com/TUO_USERNAME/voicemeeter-remote
   git push -u origin main
   ```

3. **Apri Codespaces**:
   - Vai su GitHub repository
   - Click "Code" → "Codespaces" → "Create codespace"

4. **Setup Android SDK** (nel terminale Codespaces):
   ```bash
   # Installa Java
   sudo apt update
   sudo apt install openjdk-11-jdk
   
   # Scarica Android SDK
   wget https://dl.google.com/android/repository/commandlinetools-linux-9477386_latest.zip
   unzip commandlinetools-linux-9477386_latest.zip
   
   # Setup SDK
   export ANDROID_HOME=$HOME/android-sdk
   mkdir -p $ANDROID_HOME/cmdline-tools
   mv cmdline-tools $ANDROID_HOME/cmdline-tools/latest
   
   # Installa build tools
   $ANDROID_HOME/cmdline-tools/latest/bin/sdkmanager "build-tools;34.0.0" "platforms;android-34"
   ```

5. **Compila APK**:
   ```bash
   ./gradlew assembleDebug
   ```

6. **Scarica APK**:
   - File sarà in `app/build/outputs/apk/debug/`
   - Download dal browser Codespaces

## Vantaggi:
- ✅ Gratis 60h/mese
- ✅ 4-core CPU, 8GB RAM
- ✅ Nessuna installazione locale
- ✅ Funziona da qualsiasi browser