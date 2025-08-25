@echo off
echo === Build Script per PC con poca RAM ===
echo.
echo Opzioni disponibili:
echo 1. Gradle command line (richiede JDK + Android SDK)
echo 2. Preparazione per GitHub Codespaces
echo 3. Conversione per Sketchware
echo.
set /p choice="Scegli opzione (1-3): "

if %choice%==1 goto gradle_build
if %choice%==2 goto github_prep
if %choice%==3 goto sketchware_prep

:gradle_build
echo Compilazione con Gradle...
gradlew assembleDebug
echo APK creato in: app\build\outputs\apk\debug\
pause
goto end

:github_prep
echo Preparando per GitHub Codespaces...
echo Crea repository GitHub e carica questi file
echo Poi usa Codespaces per compilare
pause
goto end

:sketchware_prep
echo Per Sketchware, usa la versione semplificata...
echo Vedi sketchware_version.txt per istruzioni
pause
goto end

:end