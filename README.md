Instructions to run the suite:

Pre-requisites:
Make sure you have 'IntelliJ Idea' installed on your machine
Have Chrome browser installed.

Checkout Code and configure project:
1. Open your command line or Terminal application and enter the directory where you would like to copy the repository. 
    For example: cd D:\test
2. Clone the repository
     git clone https://github.com/kedarrnath/deltax_selenium.git
3. Open intellij, create new java project with project source as the "delatx_selenium" folder in the content checked-out in step2. 
4. Right click on the project in intellij, goto BuildPath > Configure Buildpath. Click "Add External Libraries" and choose libraries from the "libs" folder in the project checked out in step2. Click "Apply and close"
5. Choose Java 8

Run the tests in intellij:
1. Click on testng.xml and Right click > Run
2. You should see all tests being run and their results in Run tab in intellij.
