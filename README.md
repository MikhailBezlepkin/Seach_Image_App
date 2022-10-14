# Search Image App
This app using the [Unsplash Image API](https://unsplash.com/developers) and written in Kotlin with Android Studio.
***
## Search image app staсk:
* Using Retrofit for fetching data from Unsplash Image API;
* MVVM pattern;
* Coroutines;
* Navigation-fragment;
* Paging.
***
## Description:
* When you open the app, you see the following screen

![](https://github.com/MikhailBezlepkin/Screenshots/blob/main/SI.png)

* You can run a query and see images based on your criteria:

![](https://github.com/MikhailBezlepkin/Screenshots/blob/main/SICat.png)

Here, as a list, images are displayed in incomplete format. If you scroll down, then through
every 20 elements new images will be loaded.

* By clicking on the selected list item, you will open a fragment where the image will be shown in full format:

![](https://github.com/MikhailBezlepkin/Screenshots/blob/main/SIView.png)

* If nothing was found according to your request or there is no Internet connection, then the corresponding notifications will appear:

![](https://github.com/MikhailBezlepkin/Screenshots/blob/main/SINI.png)
![](https://github.com/MikhailBezlepkin/Screenshots/blob/main/SINInt.png)
