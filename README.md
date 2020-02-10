# RevolutCurrency
Android App with MVVM, Architecture Components (ViewModel, LiveData etc.), Dependency Injection (Dagger2), Kotlin Coroutines

#### Architecture
* It uses MVVM architecture
* It based on one activity-multiple fragment approach (MainActivity is container activity for this situation)
* It uses Dagger2 for dependency injection
* It has repository and datasources
  * The datasource classes are responsible for fetching data from remote(rest api) or local(database) datasource (local datasource is not implemented for this app)
  * The repository classes are responsible for getting data from datasource and feeding viewmodel

#### Libraries

##### Dependency Injection with Dagger2
    implementation "com.google.dagger: dagger:2.24"
    implementation "com.google.dagger:dagger-android:2.24"
    implementation 'com.google.dagger:dagger-android-support:2.24'
    kapt "com.google.dagger:dagger-compiler:2.24"
    kapt "com.google.dagger:dagger-android-processor:2.24"

##### Lifecycle
    implementation "androidx.lifecycle:lifecycle-extensions:2.2.0"
    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0"
    implementation "androidx.lifecycle:lifecycle-livedata-ktx:2.2.0"
    kapt "androidx.lifecycle:lifecycle-common-java8:2.2.0"

##### Kotlin Android Coroutines
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.3'
    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.3"
    
##### Retrofit
    implementation "com.squareup.retrofit2:retrofit:2.7.1"
    implementation "com.squareup.retrofit2:converter-gson:2.7.1"

##### Glide
    implementation "com.github.bumptech.glide:glide:4.9.0"
    kapt "com.github.bumptech.glide:compiler:4.9.0"
    
#### Features
* It fetches currency data every second
* It calculates of currencies by main currency 
* It can switch between currencies
* It stops the network processes when app is in the background
* It creates alert dialog when the network is unavailable

![](revolut-gif.gif)

![revolut](revolut-gif.gif)
