
<br />
  <img src="img/Banner.png" align="center"/>
  <h1 align="center"></h1>
  

  ![MinAPI](https://badgen.net/badge/MinAPI/23/green/)
  [![Compose](https://img.shields.io/badge/compose-1.0.1-red.svg?)](https://developer.android.com/jetpack/compose)
  [![Kotlin](https://img.shields.io/badge/Kotlin-1.5.21-blue.svg?logo=kotlin)](http://kotlinlang.org)
  [![Gradle](https://img.shields.io/badge/gradle-7.0.2-blue.svg?)](https://lv.binarybabel.org/catalog/gradle/latest)

  
<p align="center">
 <img src="img/1.png" width="25%"/>
  <img src="img/2.png" width="25%"/>
  <img src="img/3.png" width="25%"/>

</p>
  
  <p align="center">
</p>

<!-- TABLE OF CONTENTS -->
## Table of Contents

* [About the Project](#about-the-project)
* [Features](#features)
* [Architecture](#architecture)
* [Future Improvements](#future-improvements)
* [Environment Setup](#requirements)
* [Contact](#contact)

  <!-- ABOUT THE PROJECT -->
## About The Project
This is sample mutual funds application developed following modern - clean architecture pattern and best practice such as SOLID principle, separation of concern, loose coupling, etc.


## Features
  - View list of Mutual Funds available in India fetched from api.
  - Search for Mutual funds from the list.
  - Get a overview information about the individual mutual fund.
      - Fund category.
      - Fund Type.
      - Fund House, etc
  - View the individual mutual fund 2 years performance(2006-2008 data) graph.
  

## Architecture


Clean architecture pattern has been used in the development of this application. 
The development language of the application is Kotlin.

Below is an illustration of clean architecture:

<img src="img/Architecture.png" />


* Architecture;
    * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
    * [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) 
    * [Navigation](https://developer.android.com/guide/navigation)
    * [Data layer](https://developer.android.com/topic/architecture/data-layer)
    * [Domain layer](https://developer.android.com/topic/architecture/domain-layer)


## Tech Stacks
This project uses many of the popular libraries, plugins and tools of the android ecosystem.
Some of Important one are listed below: 

- [Retrofit](https://square.github.io/retrofit/)
- [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) - Library Support for coroutines.
- [Flow](https://developer.android.com/kotlin/flow) - Flows are built on top of coroutines and can provide multiple values.
- [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) - Library Support for coroutines,provides runBlocking coroutine builder used in tests.
- [Madrapps](https://github.com/Madrapps) - Library for building line graph


- [Compose](https://developer.android.com/jetpack/compose)
  
    - [Material](https://developer.android.com/jetpack/androidx/releases/compose-material) - Build Jetpack Compose UIs with ready to use Material Design Components.
    - [UI](https://developer.android.com/jetpack/androidx/releases/compose-ui) - Fundamental components of compose UI needed to interact with the device, including layout, drawing, and input.
    - [Lifecycle-ViewModel](https://developer.android.com/jetpack/androidx/releases/lifecycle) - Perform actions in response to a change in the lifecycle status of another component, such as activities and fragments.
  
- [Jetpack](https://developer.android.com/jetpack)
  
    - [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) - Perform actions in response to a change in the lifecycle status of another component, such as activities and fragments.
    - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Designed to store and manage UI-related data in a lifecycle conscious way. The ViewModel class allows data to survive configuration changes such as screen rotations.


## Future Improvements

### Kotlin Multi-Module

The project can be improved further by adopting the Kotlin Multi-Module concept. This approach allows us to divide the project into multiple modules, each with its own set of responsibilities. This has several benefits:

- **Separation of Concerns**: Each module focuses on a single aspect of the application, making the codebase easier to understand and maintain.
- **Faster Build Times**: Changes in one module do not require other modules to be recompiled, resulting in faster build times.
- **Reusability**: Common functionality can be extracted into a shared module and reused across multiple modules.
- **Improved Testing**: Modules can be tested independently, making it easier to identify and fix bugs.

To implement this, we can start by identifying logical divisions in the application (such as `data`, `domain`, and `presentation` layers in Clean Architecture) and create a separate module for each.


## Environment Setup
  - Android Studio ```Bumblebee (2021.1.1)```  version.
  - Android Studio's Gradle JDK version should be Java 11.0.10.
  
    
<!-- CONTACT -->
## Contact

- [Linkedin](https://www.linkedin.com/in/rushikesh-pawar-239476167/)

```xml
Designed and developed by ruship125 (Rushikesh Pawar).
