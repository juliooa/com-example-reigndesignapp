
Reigndesign Sample app
===================================

This sample app gets the last stories from Hacker News(https://news.ycombinator.com/)
using their API. For this app the call http://hn.algolia.com/api/v1/search_by_date?query=android
is used.
In the retrieved JSON objects, I notice the only unique identifier is the field "objectID",
so I'm using this field as main id for the stories.
The main listview has the feature of pull-to-refresh and swipe-to-delete items.
In this project the following libraries are used:

* Square Retrofit to handle the REST communication (http://square.github.io/retrofit/)
* SwipeMenuListView for the swipe-to-delete feature (https://github.com/baoyongzhang/SwipeMenuListView)
* Android-PullRefreshLayout to the pull-to-refresh feature (https://github.com/baoyongzhang/android-PullRefreshLayout)



Pre-requisites
--------------

- Android SDK v14+

Build
---------------

This sample uses the Gradle build system. To build this project, use the
"gradlew build" command or use "Import Project" in Android Studio.



.Julio Andrés Olivares Alarcón
.julio.olivares.a@gmail.com