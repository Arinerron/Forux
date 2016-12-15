# Forux
[![Build Status](https://travis-ci.org/Arinerron/Forux.svg?branch=master)](https://travis-ci.org/Arinerron/Forux)

An in-development, simple, lightweight game library.

# Documentation
## Game.java
```
Game game = new Game(String name, String version); // init

game.start(); // start the game
game.stop(); // stop the game
game.setPaused(boolean paused); // pause or unpause the game

String name = game.getName(); // get game name
String version = game.getVersion(); // get game version
boolean running = game.isRunning() // is game running
boolean paused = game.isPaused(); // is game paused

Window window = game.getWindow(); // get Window instance
Clock clock = game.getClock(); // get Clock instance
EventHandler handler = game.getEventHandler(); // get EventHandler instance
Settings settings = game.getSettings(); // get Settings instance
Logger logger = game.getLogger(); // get Logger instance
ResourceManager resourcemanager = game.getResourceManager(); // get ResourceManager instance
SoundManager soundmanager game.getSoundManager(); // get SoundManager instance
```

## Window.java
```
Window window = game.getWindow(); // get Window instance

window.setVisible(boolean visible); // show or hide window : Only use this after game.start()!
window.setSize(int width, int height); // set window size
window.setFullscreen(boolean fullscreen); // make window fullscreen or not
window.setLocation(int x, int y); // set window location
window.setFrameSize(int width, int height); // set size of screen

window.addScreen(Screen screen); // add screen
window.removeScreen(Screen screen) // remove screen by screen
window.removeScreen(int id) // remove screen by id
window.setCurrentScreen(Screen screen); // set current screen by screen
window.setCurrentScreen(int id); // set current screen by id

Game game = window.getGame(); // get Game instance
String filelocation = window.screenshot(); // saves a screenshot of the window in the screenshots folder
int screenCount = window.getScreenCount(); // get number of screens
boolean visible = window.isVisible(); // is window visible
boolean fullscreen = window.isFullscreen(); // is window fullscreen
Dimension size = window.getSize(); // get window size
Dimension frameSize = window.getFrameSize(); // get window-screen size
Point location = window.getLocation(); // get window location
List<Screen> screen = window.getScreens(); // get all screens
BufferedImage image = window.getImage(); // get current image
Screen screen = window.getCurrentScreen(); // get current Screen instance
Screen screen = window.getScreen(int id); // get screen by id
```

## Clock.java
```
Clock clock = game.getClock(); // get Clock instance

Game game = clock.getGame(); // get Game instance
int ticks = clock.getTicks(); // get total ticks
int index = clock.getIndex(); // get tick count when screen changed
int tickssinceindex = clock.getTicksSinceIndex(); // get ticks since last index
String timestamp = clock.getTimestamp(); // get the file-friendly timestamp
Window window = clock.getWindow(); // get Window instance
```

## EventHandler.java
```
EventHandler handler = game.getEventHandler(); // get EventHandler instance

handler.registerListener(EventListener listener); // register EventListener
handler.unregisterListener(EventListener listener); // unregister EventListener
handler.unregisterAll(); // unregister all registered EventListeners

Game game = handler.getGame(); // get Game instance
List<EventListener> listeners = handler.getEventListeners(); // get a List of EventListeners
boolean registered = handler.isRegistered(EventListener listener); // is EventListener registered
```

## Settings.java
```
/*
 * Since this class isn't done and is going to
 * undergo huge changes, I am not going to 
 * document it right now. If for some reason
 * you need to use this class right now,
 * feel free to make an Issue in this
 * repository.
 */
```

## Logger.java
```
Logger logger = game.getLogger(); // get Logger instance

logger.setType(int type); // valid values are Logger.PRINT_TO_CONSOLE, Logger.PRINT_TO_FILE, and PRINT_TO_CONSOLE_AND_FILE
logger.log(String type, Object message); // log any type of message
logger.info(Object message); // log an info message
logger.warn(Object message); // log a warning message
logger.error(Object message); // log an error message
logger.error(Exception exception); // log an exception

Game game = logger.getGame(); // get Game instance
int type = logger.getType(); // get logging type
```

## ResourceManager.java
```
ResourceManager resourcemanager = game.getResourceManager(); // get ResourceManager instance

resourcemanager.writeFile(File file, Object contents); // write Object to file
resourcemanager.appendFile(File file, Object contents); // append Object to file

Game game = resourcemanager.getGame(); // get Game instance
String contents = resourcemanager.readFile(File file); // read file into String
File home = resourcemanager.getHomeFolder(); // get Game's home folder
File config = resourcemanager.getConfigurationFile(); // get Game's config file
File logs = resourcemanager.getLogsFolder(); // get logs folder
File log = resourcemanager.getLogFile(); // get current log file
File resources = resourcemanager.getResourcesFolder(); // get resources folder
File images = resourcemanager.getImageResourcesFolder(); // get image resources folder
File audio = resourcemanager.getAudioResourcesFolder(); // get audio resources folder
BufferedImage image = resourcemanager.getImageResource(String filename); // get image resource
File sound = resourcemanager.getAudioResource(String filename); // get audio resource
```

## SoundManager.java
```
SoundManager soundmanager = game.getSoundManager(); // get SoundManager instance

soundmanager.register(String name, String filename); // register sound
soundmanager.unregister(String name); // unregister sound
soundmanager.unregisterAll(); // unregister all sounds

Game game = soundManager.getGame(); // get Game instance
HashMap<String, String> sounds = soundmanager.getSounds(); // get list of all registered sounds
SoundPlayer player = soundmanager.getSound(String name); // get SoundPlayer instance for a registered sound by name
```

## SoundPlayer.java
```
SoundPlayer player = soundmanager.getSound(String name); // get SoundPlayer instance for a registered sound by name

player.play(); // start sound
player.stop(); // stop sound
player.pause(); // pause sound (player.play() to continue)
player.restart(); // restart sound
player.dispose(); // dispose of player (run this when done!)
player.setVolume(float volume); // set volume of player

Game game = player.getGame(); // get Game instance
float volume = player.getVolume(); // get volume as float
boolean playing = player.isPlaying(); // is sound playing
SoundManager soundmanager = player.getSoundManager(); // get SoundManager instance
File file = player.getFile(); // get source file
```
