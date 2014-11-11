ese2014-team1 - Build instructions
----------------------------------
To build our project, please follow these steps:

1. You need to have Java JDK8 installed. JDK7 and JRE8 are *not* enough. Also check that the JDK8 is properly added to the workspace by checking that it is added under *Eclipse preferences -> Java -> Installed JREs*. It should be visible and checked as the default there. Furthermore, we recommend using a current version of Eclipse (e.g. Eclipse Luna). This is due to some Java 8 code in the project.
2. When building the project, have Apache and MySQL running. MySQL should be accessible as user *root* with no password.
3. Build the project using `jetty:stop jetty:run`. The build adds test users and test data to the database, so no additional steps should be required.
4. Visit the site at [http://localhost:8080](http://localhost:8080). You can find the test users listed on the login page.
