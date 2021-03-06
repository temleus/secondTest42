**How to**

Building SDK must be 4.0 or later (it doesn't mean that app won't be compatible with 2.x devices)

Project is dependent on 3 libraries:
 1. **ActionBar Sherlock** 
https://github.com/JakeWharton/ActionBarSherlock.git
 2. **ViewPagerIndicator**
https://github.com/JakeWharton/Android-ViewPagerIndicator.git
 3. **Facebook SDK** [Download][1] and extract the SDK ZIP file. 

The resulting folder, facebook-android-sdk-x.x.x, contains the SDK itself, samples and others.

All three libraries must be added as library projects.

+ add the last version android support package dependency (libs\android-support-v4.jar or your local jar)

+ ! `applicationId = Utility.getMetadataApplicationId(context);` at line 221 com.facebook.Session.java must be replaced with 
  `applicationId = "387110658074885";` (because Manifest metadata parsing isn't implemented in Robolectric)

**Test module**

Project contains inner module named 'test' that is dependent on outer module (scope - test) 

and on test libraries:
robolectric-x.x.jar and junit-x.x.jar located at test/libs directory

! dependencies must be added in that order: http://i.imgur.com/7ozkEkM.png

test/src directory must be marked as 'test sources'

[1]: https://developers.facebook.com/resources/facebook-android-sdk-3.0.1.zip

