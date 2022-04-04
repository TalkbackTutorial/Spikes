# Accessibility Service Spike
This spike showcases how we can use our own Accessibility Service to detect and handle gestures

## Advantages
Overrides talkback, this means during lessons and practice talkback won't interfere, for example, if talkback is enabled and we ask the user to perform a swipe up then right 
gesture the talkback menu will open.

## Disadvantages
The user has to enable our service in settings (we cannot programmatically change this from what I've read), because our service overrides talkback the user will have to
turn our service off after exiting the app, the user then has no way to navigate to settings to turn off our service as talkback is disabled.
