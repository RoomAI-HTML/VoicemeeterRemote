# Voicemeeter Remote Control

App Android per controllare Voicemeeter da remoto.

## Setup Voicemeeter

1. Abilita l'interfaccia web in Voicemeeter:
   - Menu → Options → Web Remote Interface
   - Porta: 8080 (default)

2. Configura il firewall per permettere connessioni sulla porta 8080

## Funzionalità

- **Strip (Input)**: Controllo volume e mute per 8 canali input
- **Bus (Output)**: Controllo volume e mute per 8 bus output  
- Connessione via IP locale o remoto
- Interface separata per input/output come Voicemeeter Potato

## Come usare

1. Avvia Voicemeeter sul PC
2. Abilita web interface
3. Nell'app inserisci l'IP del PC (127.0.0.1 se locale)
4. Premi "Connetti"
5. Usa i controlli per regolare volume e mute

## Note tecniche

L'app usa le API HTTP di Voicemeeter. Se non funziona, prova:
- Voicemeeter Potato (versione più completa)
- Plugin VB-Audio Virtual Cable
- API alternative via TCP socket