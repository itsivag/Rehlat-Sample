Welcome to the README file for the "Rehlat Sample" Android app. This document will provide an overview of the app's features, product requirements, and technical requirements.

Product Requirements
Fetching Data from Backend Endpoint:
The app should make a GET request to the following back-end endpoint:

Endpoint URL: https://ey3f2y0nre.execute-api.us-east-1.amazonaws.com/default/dynamodb-writer
Authentication: No authentication is required.
Response: The endpoint will return a JSON response representing a list of listings, which will be used as a data source for the app.
List of Listings:
The app should display a homepage that shows a list of listings. Each listing should include relevant details such as title, description, price, and date.

Sorting Listings:
The app should provide options to sort the list of listings based on the following criteria:

Price Sort: The user should be able to sort the list in ascending or descending order based on the price.
Date Sort: The user should be able to sort the list in the latest or oldest order based on the date.
Listing Details:
Clicking on a listing in the list should open a new screen that displays the details of that listing. The details should include all available information about the listing, such as title, description, price, and date.

Technical Requirements
Retrofit for HTTP Requests:
The app should use the Retrofit library to handle HTTP requests. Retrofit simplifies the process of making HTTP calls and handling responses.

Intent-Revealing Class and Method Names:
Class and method names should clearly indicate their intent and responsibility. This ensures that the codebase is easy to understand and maintain.

Accommodating Future Requirement Changes:
The app's software architecture should be designed in a way that easily accommodates possible future requirement changes. This means that the code should be modular, well-organized, and follow best practices to facilitate scalability and extensibility.

Getting Started
To run the "Rehlat Sample" Android app on your local machine, follow these steps:

Clone the repository from GitHub: https://github.com/itsivag/Rehlat-Sample.git
Open the project in Android Studio.
Build and run the app on an emulator or a connected Android device.
Make sure to have an active internet connection to fetch the data from the back-end endpoint.

Dependencies
The "Rehlat Sample" app utilizes the following dependencies:

Retrofit: https://square.github.io/retrofit/
Glide Image Loading : https://github.com/bumptech/glide
Make sure to include these dependencies in your project's build.gradle file.

License
The "Rehlat Sample" app is open-source software licensed under the GNU License.

Contact
If you have any questions, feedback, or suggestions, please contact the project maintainers:
Siva G: sivacbrf2@gmail.com
