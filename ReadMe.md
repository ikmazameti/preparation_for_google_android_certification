#SharedPreferences Methods

Let’s look at some important methods for SharedPreferences.

** getSharedPreferences(String, int) method is used to retrieve an instance of the SharedPreferences. Here String is the name of the SharedPreferences file and int is the Context passed.
** The SharedPreferences.Editor() is used to edit values in the SharedPreferences.
**We can call commit() or apply() to save the values in the SharedPreferences file. 
The commit() saves the values immediately whereas apply() saves the values asynchronously.

1. create the container - getSharedPreferences(String, int)
2. open the container - SharedPreferences.Editor()
3. put/remove items in/from the container eg. editor.putString("username","Anupam") sharedPreference.getString("username","defaultName")
4. cover the container - commit() or apply()
5. empty container/remove just one record - editor.clear() editor.remove("username")

Good Practices

1. Save preferences in onPause()
2. Name your shared preferences file the same name as the package name of your app.
3. The commit() method is discouraged as it can block other operations.

SharedPreferences vs onSaveInstanceState
1. If you want to fill once and keep the data even if the app gets killed, use SharedPreferences.
2. If it's volatile data that will have to be reentered differently some other time (i.e., days later), then use onSavedInstanceState.
3. SharedPreferences
Use for things that should always be remembered, no matter if the phone is turned off (eg for settings chosen in the settings screen of your app
4. onSavedInstanceState
Use this for remembering things about the current state of your activity such as the currently selected tab on the screen. This allows you to recreate the same state after a rotation or if the app was killed due to low memory.
The things saved in onSaveInstanceState will be forgotten after reboot, and when starting a new instance of an activity they will not be passed, so they are only for remembering the state of the activity
5. onRetainNonConfigurationInstance
Use this for storing objects which take a long time to load so that you don't have to load them again when the phone is rotated.
6. Shared preferences	Saved instance state
   Persists across user sessions, even if your app is stopped and restarted, or if the device is rebooted.	Preserves state data across activity instances in the same user session.
   Used for data that should be remembered across user sessions, such as a user's preferred settings or their game score.	Used for data that should not be remembered across sessions, such as the currently selected tab, or any current state of an activity.
   Represented by a small number of key/value pairs.	Represented by a small number of key/value pairs.
   Data is private to the app.	Data is private to the app.
   Common use is to store user preferences.	Common use is to recreate state after the device has been rotated.
