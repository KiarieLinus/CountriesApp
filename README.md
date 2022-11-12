# CountriesApp
## About

The CountriesApp was created as part of a stage3 task during th HNGI9 internship to learn more about developing an app from a design

## Appetize Link

You can click [Appetize.io](https://appetize.io/app/bhdpkf4gr23eqd6vau3bp6ffzm?device=pixel4&osVersion=10.0&scale=75) to view the app. (To be updated on 13/11)

## Libraries

- Navigation Compose : library to add navigation elements to your compose projects
- Dagger-Hilt : a library for  dependency injection in Android apps that works seamlessly with Compose.
-Accompanist: Used as a paging library for horizontal paging of composable

## Design & Features

The app has two screens namely the SearchCountryScreen which is the first screen that offers a list of grouped countries, clicking on a country item navigates to the  CountryDetailsScreen that shows details concerning selected country

The design is from [Design](https://www.figma.com/file/v9AXj4VZNnx26fTthrPbhX/Explore)

## Features to be added

- Theming: Improve on the theming to make the mode change cleaner
- List Filtering: Add the functions to filter the list by continent and time zone
- Localisation: Add the function to get translation of the country name
- Search Feature: Add function for the country list search feature

## Challenges

- Designing the DTO model: This took too much time since a number of the elements are nullable and the JSON is quite large. A bit of debugging did the trick
