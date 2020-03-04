# key-conservation-mobile

Endangered Haven

by Albert Yakubov  
Android Developer  
March 3, 2020  

Endangered Haven opens an ability to bring conservation organizations and supporters together in time of need.   

Key Features:  
-Post Campaign  
-Delete Campaign  
-Update Profile  
-Reset Password  
-Maps  
-Connect campaign or any user  


Back end built in Springboot and Java  

Front End build in Kotlin via Android Studio  


Back End Features:  
-Basic Password grant O Auth2 Authentication   
-Password Encryption   

Front End libraries:  
-Dagger 2 and RxJava used to imlement search functionality in recycler view  
-Cloudinary for any image uploads  
	*Cloudinary is customizable to upload videos  

-Exo Player to play videos in the card view  
(improvement needed: display images in Exo player rather than a thumbnail)  

-Maps   
*displays currentUserLocation  
*displays userLocation  
*displays campaignLocation  

*Search functionality to search map activity by the city or country  

*notifies the user of nearby campaigns  

(maps area to improve: reduce the ammount of code)  


General Areas For Improvements:   
-Adding Comments  
-UI Fixes:  
*redesign registration and view profile to fit more info to display  

-Following Users  


Testing:  

-Front End  
*Robolectric for integration testing (views)   
**Robolectric allows testing to be done without the need for any machine  

*JUnit 4 for unit testing for both front and back end (works really well with robolectric)  

*Espresso for UI testing   

Curently at 20% percent coverage  
Google Play Deployed  

https://play.google.com/store/apps/details?id=com.stepasha.keyconservation

