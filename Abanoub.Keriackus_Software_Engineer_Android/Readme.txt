CONTENTS OF THIS FILE
---------------------
* Introduction
* INSTALLATION
* UNIT TESTS RUNNING
* DATABASE INSTALLATION
* ASSUMPTIONS AND VALIDATIONS
* ISSUES
* FEEDBACK

INTRODUCTION
------------
An android auction system application , using sqlite database. Developed by Abanoub Keriackus.

INSTALLATION
------------
* Install android studio by following the steps in the below link:
http://developer.android.com/sdk/installing/index.html?pkg=studio

*Use the sdk manager to download the latest android support library (from extras section)

*Minimum SDK is android (4.03 API 15) so make sure it's installed as well. Use the below link for more details about how to use the SDK Manager.
http://developer.android.com/sdk/installing/adding-packages.html

* If everything is installed proberly, then the gradle build system will be able to continue the installations of the used extra libraries/modules.

UNIT TESTS
------------------
*After importing  the Project in Android studio, right click on the unit test package (app\src\ Test), and choose run. 
An html report with successful/failed test cases will be generated under the following path  "~\app\build\reports\tests\debug"

DATABASE INSTALLATION
---------------------------
Tha database will be automatically installed and initialized with test data on the first launch of the app. (Depending on ORMLite)

ASSUMPTIONS AND VALIDATIONS MADE:
------------------------------------------
* User will use email to register/sign in.

* email must be valid.

*User password must be at least 4 characters.

*Auction Type is Absolute Auction (The item is sold to the highest bidder, regardless of the price.)	

*Each item has a title, a description, and a category.

*Each item in  auction has an estimated price.

*The Bot use the estimate price of the item in the random bidding. (Random bidding is at most double the estimate, and at least half the estimate)

*Each item has a timestamp in which the auction will start.

*While creating new item the auction start time is validated to be in the future (at least after half a minute from now)

*User can't place Bid on an expired item.

*Bidding price can't be negative.

*Bidding price can't be canceled, nor edited.

ISSUES 
-------------
*I didn't face any issues, the task description is clear, and the time is enough.

FEEDBACK
---------------
*I believe using a version control system to submit the task will be better than uploading a zip file. 
It will also give you a good hint about the candidate version control skills.