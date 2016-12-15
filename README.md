# Forux
[![Build Status](https://travis-ci.org/Arinerron/Forux.svg?branch=master)](https://travis-ci.org/Arinerron/Forux)

An in-development, simple, lightweight 2D game library.

# Error Checking
Before pushing to this repository, please check for errors in your code. You can do that by executing `sh script.sh` in the root directory in the repository. This will trigger a fake compile that will report errors (if any) to the console.

# Documentation
## Game.java
```
Game game = new Game(String name, String version); // init

game.start(); // start the game
game.stop(); // stop the game
game.setPaused(boolean paused); // pause or unpause the game

String name = game.getName(); // returns game name
String version = game.getVersion(); // returns game version
boolean running = game.isRunning() // is game running
boolean paused = game.isPaused(); // is game paused
boolean firstrun = game.isFirstRun(); // is this the first time the game has run

Window window = game.getWindow(); // returns Window instance
Clock clock = game.getClock(); // returns Clock instance
EventHandler handler = game.getEventHandler(); // returns EventHandler instance
Settings settings = game.getSettings(); // returns Settings instance
Logger logger = game.getLogger(); // returns Logger instance
ResourceManager resourcemanager = game.getResourceManager(); // returns ResourceManager instance
SoundManager soundmanager game.getSoundManager(); // returns SoundManager instance
```

## Window.java
```
Window window = game.getWindow(); // returns Window instance

window.setVisible(boolean visible); // show or hide window : Only use this after game.start()!
window.setSize(int width, int height); // set window size
window.setFullscreen(boolean fullscreen); // make window fullscreen or not
window.setLocation(int x, int y); // set window location
window.setFrameSize(int width, int height); // set size of screen
window.setResizable(boolean resizable); // set resizable or not
window.setUndecorated(boolean undecorated); // set whether or not window is undecorated

window.addScreen(Screen screen); // add screen
window.removeScreen(Screen screen) // remove screen by screen
window.removeScreen(int id) // remove screen by id
window.setCurrentScreen(Screen screen); // set current screen by screen
window.setCurrentScreen(int id); // set current screen by id

Game game = window.getGame(); // returns Game instance
String filelocation = window.screenshot(); // saves a screenshot of the window in the screenshots folder
int screenCount = window.getScreenCount(); // returns number of screens
boolean visible = window.isVisible(); // is window visible
boolean fullscreen = window.isFullscreen(); // is window fullscreen
Dimension size = window.getSize(); // returns window size
Dimension frameSize = window.getFrameSize(); // returns window-screen size
Point location = window.getLocation(); // returns window location
boolean resizable = window.isResizable(); // is window resizable
boolean undecorated = window.isUndecorated(); // is window undecorated
List<Screen> screen = window.getScreens(); // returns all screens
BufferedImage image = window.getImage(); // returns current image
Screen screen = window.getCurrentScreen(); // returns current Screen instance
Screen screen = window.getScreen(int id); // returns screen by id
```

## Clock.java
```
Clock clock = game.getClock(); // returns Clock instance

clock.setRenderSpeed(int speed); // set the speed at which frames are updated
clock.setTickSpeed(int speed); // set the speed that ticks happen

Game game = clock.getGame(); // returns Game instance
int ticks = clock.getTicks(); // returns total ticks
int index = clock.getIndex(); // returns tick count when screen changed
int tickssinceindex = clock.getTicksSinceIndex(); // returns ticks since last index
int renderspeed = clock.getRenderSpeed(); // returns render speed
int tickspeed = clock.getTickSpeed(); // returns tick speed
String timestamp = clock.getTimestamp(); // returns the file-friendly timestamp
Window window = clock.getWindow(); // returns Window instance
```

## EventHandler.java
```
EventHandler handler = game.getEventHandler(); // returns EventHandler instance

handler.registerListener(EventListener listener); // register EventListener
handler.unregisterListener(EventListener listener); // unregister EventListener
handler.unregisterAll(); // unregister all registered EventListeners

Game game = handler.getGame(); // returns Game instance
List<EventListener> listeners = handler.getEventListeners(); // returns a List of EventListeners
boolean registered = handler.isRegistered(EventListener listener); // is EventListener registered
```

## Settings.java
```
Settings settings = game.getSettings(); // returns Settings instance

settings.loadDefaults(); // replace current settings with ones in settings file
settings.saveDefaults(); // save current settings to settings file
settings.put(String key, Object val); // store a setting by key and val

Game game = settings.getGame(); // returns Game instance
String json = settings.getSettings(); // returns all settings in JSON format
String stringval = settings.getString(String key); // returns a String setting
int intval = settings.getInt(String key); // returns an int setting
double doubleval = settings.getDouble(String key); returns a double setting
float floatval = settings.getFloat(String key); // returns a float setting
boolean booleanval = settings.getBoolean(String key); // returns a boolean setting
```

## Logger.java
```
Logger logger = game.getLogger(); // returns Logger instance

logger.setType(int type); // valid values are Logger.PRINT_TO_CONSOLE, Logger.PRINT_TO_FILE, and PRINT_TO_CONSOLE_AND_FILE
logger.log(String type, Object message); // log any type of message
logger.info(Object message); // log an info message
logger.warn(Object message); // log a warning message
logger.error(Object message); // log an error message
logger.error(Exception exception); // log an exception

Game game = logger.getGame(); // returns Game instance
int type = logger.getType(); // returns logging type
```

## ResourceManager.java
```
ResourceManager resourcemanager = game.getResourceManager(); // returns ResourceManager instance

resourcemanager.writeFile(File file, Object contents); // write Object to file
resourcemanager.appendFile(File file, Object contents); // append Object to file

Game game = resourcemanager.getGame(); // returns Game instance
String contents = resourcemanager.readFile(File file); // read file into String
File home = resourcemanager.getHomeFolder(); // returns Game's home folder
File config = resourcemanager.getConfigurationFile(); // returns Game's config file
File logs = resourcemanager.getLogsFolder(); // returns logs folder
File log = resourcemanager.getLogFile(); // returns current log file
File resources = resourcemanager.getResourcesFolder(); // returns resources folder
File images = resourcemanager.getImageResourcesFolder(); // returns image resources folder
File audio = resourcemanager.getAudioResourcesFolder(); // returns audio resources folder
BufferedImage image = resourcemanager.getImageResource(String filename); // returns image resource
File sound = resourcemanager.getAudioResource(String filename); // returns audio resource
```

## SoundManager.java
```
SoundManager soundmanager = game.getSoundManager(); // returns SoundManager instance

soundmanager.register(String name, String filename); // register sound
soundmanager.unregister(String name); // unregister sound
soundmanager.unregisterAll(); // unregister all sounds

Game game = soundManager.getGame(); // returns Game instance
HashMap<String, String> sounds = soundmanager.getSounds(); // returns list of all registered sounds
SoundPlayer player = soundmanager.getSound(String name); // returns SoundPlayer instance for a registered sound by name
```

## SoundPlayer.java
```
SoundPlayer player = soundmanager.getSound(String name); // returns SoundPlayer instance for a registered sound by name

player.play(); // start sound
player.stop(); // stop sound
player.pause(); // pause sound (player.play() to continue)
player.restart(); // restart sound
player.dispose(); // dispose of player (run this when done!)
player.setVolume(float volume); // set volume of player

Game game = player.getGame(); // returns Game instance
float volume = player.getVolume(); // returns volume as float
boolean playing = player.isPlaying(); // is sound playing
SoundManager soundmanager = player.getSoundManager(); // returns SoundManager instance
File file = player.getFile(); // returns source file
```

## KeyManager.java
```
KeyManager keymanager = game.getKeyManager(); // returns KeyManager instance

Game game = keymanager.getGame(); // returns Game instance
List<Character> keys = keymanager.getPressedKeys(); // returns List of chars that are pressed
boolean pressed = keymanager.isKeyPressed(char key); // is key pressed (by char)?
boolean pressed2 = keymanager.isKeyPressed(int key); // is key pressed (by int)?
List<Integer> buttons = keymanager.getPressedMouseButtons(); // returns List of mouse buttons that are pressed
boolean pressed3 = keymanager.isMouseButtonPressed(int button); // is mouse button pressed? (1=left 2=middle 3=right mouse button)
```
