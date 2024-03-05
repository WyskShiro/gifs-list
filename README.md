# Gifs list

# Technologies
 - Kotlin
 - Retrofit
 - Hilt
 - Coroutines
 - Flow
 - MockK
 - Jetpack Stuff (Fragments, ViewModel, Navigation etc)

# Commentaries

- This simple app was developed to apply some different concepts that I've seen throughout my career
- I've used the Clean Architecture with MVVM in the presentation layer, since it's the recommended architecture by Google

# TODO
- ~~In the future, I want to also have the same UI, but with Compose, while also improving the components and UI testing~~
- Replaced the XML UI with compose. The XML version is still available in the commit history and here: [xml_version](https://github.com/WyskShiro/gifs-list/tree/feature/layout-version)

## Overview

Simple app to show gifs from Giphy API
[new version.webm](https://github.com/WyskShiro/gifs-list/assets/26095521/ea198fbb-cf0b-4584-b43a-336d7dfa84c8)


### Screen 1:

Screen 1 has the following two functionalities:

1. Displaying a random GIF:
    - Upon opening the app, it connects to the Giphy random API and display a random GIF
    - Every 10 seconds a new random GIF replaces the previous loaded one. 
    - It also displays the GIF title, link and an age restriction badge.
2. Search Bar:
    - Live search with a debounce time of 500ms.
    - On press of the back button, the screen goes back to displaying the random GIF.

### Screen 2:

Screen 2 only has the following functionality:

1. Displaying the GIF that was tapped:
    - It displays the GIF title, link and an age restriction badge.
