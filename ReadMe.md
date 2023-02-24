# Room
1. LiveData: A data holder class that can be observed. Always holds/caches the latest version of data, and notifies its observers when data has changed. LiveData is lifecycle aware. UI components just observe relevant data and don't stop or resume observation. LiveData automatically manages all of this since it's aware of the relevant lifecycle status changes while observing.

2. ViewModel: Acts as a communication center between the Repository (data) and the UI. The UI no longer needs to worry about the origin of the data. ViewModel instances survive Activity/Fragment recreation.

3. Repository: A class that you create that is primarily used to manage multiple data sources.


## Observing database changes

When data changes, you usually want to take some action, such as displaying the updated data in the UI. 
This means you have to observe the data so when it changes, you can react.

To observe data changes you will use **Flow** from kotlinx-coroutines. Use a return value of type Flow in 
your method description, and Room generates all necessary code to update the Flow when the database is updated.

## Repository
A repository class abstracts access to multiple data sources. 

### Why use a Repository?
A Repository manages queries and allows you to use multiple backends. In the most common example, 
the Repository implements the logic for deciding whether to fetch data from a network or use results 
cached in a local database.

## ViewModel
The ViewModel's role is to provide data to the UI and survive configuration changes. 
A ViewModel acts as a communication center between the Repository and the UI. You can also use a 
ViewModel to share data between fragments. The ViewModel is part of the lifecycle library.

### Why use a ViewModel?
A ViewModel holds your app's UI data in a lifecycle-conscious way that survives configuration changes. 
Separating your app's UI data from your Activity and Fragment classes lets you better follow the single 
responsibility principle: Your activities and fragments are responsible for drawing data to the screen,
while your ViewModel can take care of holding and processing all the data needed for the UI.

### LiveData and ViewModel
LiveData is an observable data holder - you can get notified every time the data changes. Unlike Flow, 
LiveData is lifecycle aware, meaning that it will respect the lifecycle of other components like Activity
or Fragment. LiveData automatically stops or resumes observation depending on the lifecycle of the 
component that listens for changes. This makes LiveData the perfect component to be used for for changeable 
data that the UI will use or display.

The ViewModel will transform the data from the Repository, from Flow to LiveData and expose the list of 
words as LiveData to the UI. This ensures that every time the data changes in the database, your UI is automatically updated.

### viewModelScope
In Kotlin, all coroutines run inside a CoroutineScope. A scope controls the lifetime of coroutines through 
its job. When you cancel the job of a scope, it cancels all coroutines started in that scope.

The AndroidX lifecycle-viewmodel-ktx library adds a viewModelScope as an extension function of the ViewModel 
class, enabling you to work with scopes.

To find out more about working with coroutines in the ViewModel, check out Step 5 of the Using Kotlin 
Coroutines in your Android App codelab or the Easy Coroutines in Android: viewModelScope blogpost.