# CountriesApp
## About

The CountriesApp was created as part of a stage3 task during th HNGI9 internship to learn more about developing an app from a design

## Appetize Link

You can click [Appetize.io](https://appetize.io/app/bhw6ha3tbff6eyyxcrnz4unzha?device=pixel4&osVersion=11.0&scale=75) to view the app. (To be updated)

## Libraries

- Jetpack Compose : used for the ui and state management
- Dagger-Hilt : a library for  dependency injection in Android apps that works seamlessly with Compose.
- Accompanist: Used as a paging library for horizontal paging of composable

## Design & Features

The app has two screens namely the SearchCountryScreen which is the first screen that offers a list of grouped countries, clicking on a country item navigates to the  CountryDetailsScreen that shows details concerning selected country. The translations of the country names can be accessed by clicking the "world" button and choosing a language from the bottom sheet.

The design is from [Design](https://www.figma.com/file/v9AXj4VZNnx26fTthrPbhX/Explore)

## Features to be added

- Theming: Improve on the theming to make the mode change cleaner
- List Filtering: Add the functions to filter the list by continent and time zone
- Search Feature: Add function for the country list search feature
- Map: Add a map of the country in the detail screen

## Challenges

- Designing the DTO model: This took too much time since a number of the elements are nullable and the JSON is quite large. A bit of debugging and analysing the JSON file did the trick
