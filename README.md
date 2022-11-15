# CountriesApp
## About

The CountriesApp was created as part of a stage3 task during th HNGI9 internship to learn more about developing an app from a design

## Appetize Link

You can click [Appetize.io](https://appetize.io/app/bhw6ha3tbff6eyyxcrnz4unzha?device=pixel4&osVersion=11.0&scale=75) to view the app. 

(LinkStatus: Updated)

## Libraries

- Jetpack Compose : used for the ui and state management
- Dagger-Hilt : a library for  dependency injection in Android apps that works seamlessly with Compose.
- Accompanist: Used as a paging library for horizontal paging of composable
- Retrofitt: 

## Design & Features

The app has two screens namely the SearchCountryScreen which is the first screen that offers a list of grouped countries, clicking on a country item navigates to the  CountryDetailsScreen that shows details concerning selected country. The translations of the country names can be accessed by clicking the "world" button and choosing a language from the bottom sheet.Countries can be searched using the search bar and get a filtered list by continents or timezones. 

The design is from [Design](https://www.figma.com/file/v9AXj4VZNnx26fTthrPbhX/Explore)

## Features to be added

- Map: Add a map of the country in the detail screen

## What did I learn?

- Calling complex Restful APIs: This took too much time to crack since a number of the objects are nullable and/or contained simillar structured nested objects which generated a lot of files on conversion. Lesson learnt to always analyse the code beforehand and also HashMaps are the key to adding value in the DTO :wink:
- Theming in Jetpack Compose: Gained more knowledge about automatic theme change. Also learnt how to change status bar colors in compose(Just do it the old way)
