# WeatherApp

WeatherApp is a simple Android application that displays weather information using the WeatherAPI.

## Features

- Displays current weather information for a specified location.
- Shows forecast data for the upcoming days.
- Uses the Room database for local data persistence.
- Utilizes RecyclerView for efficient and flexible list display.
- Implements URLConnection for making API requests to the WeatherAPI.
- Follows the MVVM (Model-View-ViewModel) architectural pattern.
- Uses Fragments for modular and reusable UI components.
- Implements OOP (Object-Oriented Programming) principles for code organization and maintainability.
- Incorporates design patterns such as Observer (LiveData) and Adapter (RecyclerView Adapter).


## Libraries Used

- Retrofit: Used for making API requests to the WeatherAPI.
- Gson: Used for JSON serialization/deserialization.
- Picasso: Used for loading and displaying weather icons.

## Setup

1. Clone the repository:git clone https://github.com/Timur682/weatherApp-master22

2. Open the project in Android Studio.

3. Build and run the app on an emulator or a physical device.

## API Key

The app requires an API key from the WeatherAPI. You can obtain an API key by signing up on their website: https://www.weatherapi.com/. 
Once you have the API key, add it to the `API_KEY` constant in the `WeatherService` interface.

```kotlin
interface WeatherService {
    companion object {
        const val API_KEY = "YOUR_API_KEY"
    }

}
