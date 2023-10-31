## Coordinate Plotter App
This is a simple Android app built using Jetpack Compose. 
It allows the user to move a cursor in X and Y directions using X-Axis and Y-Axis sliders and 
displays the coordinates of the cursor in real time.

## CoordPlotFeatures:
* App supports both Landscape and Portrait orientations.
* App supports both Light and Dark themes.
* Slider values and cursor position state is retained during configuration changes(screen rotation)
* Custom Splash Screen for devices running Android 11 and below.
* Custom App Icon

## CountryInfoFeatures:
* MVVM Architecture
* Repository Pattern
* Compose NavHost for Navigation
* Animations

## Animations:
* CountryInfoScreen: 
    * CrossFade animation between CountryInfoState.Loading,CountryInfoState.Success and CountryInfoState.Error states
    * using Crossfade composable
  
    * Animated `favorite` star icon:
    * * When the user taps on the star icon, it rotates 360 degrees and change its drawable resources.
  
    * Animated `app version` text size on AboutScreen using `updateTransition` and `animateFloat`
    * that changes the text size when the user taps on the text and goes back to its original size after 1 second.
    