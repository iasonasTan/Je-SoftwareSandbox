# Game

This is the readme for Game, try to keep it up to date with any information future-you will wish past-you
remembered to write down

## Project set up
This is a gradle project using JMonkey Engine and other java libraries

# Modules : 

Game module `:game` : holds `build.gradle` dependencies for the game code & should hold your code.
Desktop module `:desktop` : holds `build.gradle` for desktop dependencies & implements the `:game` module, this module can hold the desktop gui.

# Running Game : 

### Desktop : 

```gradle
./gradlew run
```

# Building Game :

### Desktop :

```bash
    $./gradlew :desktop:copyJars
```
