@echo off
echo === Upload su GitHub ===
echo.
echo 1. Vai su github.com e crea un nuovo repository
echo 2. Chiamalo "voicemeeter-remote" 
echo 3. NON aggiungere README, .gitignore o license
echo 4. Copia l'URL del repository
echo.
pause

echo Inizializzo Git...
git init

echo Aggiungo tutti i file...
git add .

echo Primo commit...
git commit -m "Initial commit - Voicemeeter Remote Android App"

echo.
set /p repo_url="Incolla l'URL del tuo repository GitHub: "

echo Collegamento al repository...
git remote add origin %repo_url%

echo Upload...
git branch -M main
git push -u origin main

echo.
echo ✅ Fatto! Ora vai su GitHub e apri Codespaces
pause