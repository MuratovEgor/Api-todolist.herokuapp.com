# Api autotests for https://api-nodejs-todolist.herokuapp.com/

___

## Technologies used:

| Java | Gradle | Junit5 | IntelliJ IDEA | GitHub |
|:------:|:----:|:----:|:------:|:------:|
| <img src="images/JAVA.svg" width="40" height="40"> | <img src="images/Gradle.svg" width="40" height="40"> | <img src="images/Junit5.svg" width="40" height="40"> | <img src="images/IDEA.svg" width="40" height="40"> |  <img src="images/GitHub.svg" width="40" height="40">

| Jenkins  | Allure Report | Allure TestOps | Telegram |
|:--------:|:-------------:|:---------:|:-------:|
| <img src="images/Jenkins.svg" width="40" height="40"> | <img src="images/Allure Report.svg" width="40" height="40"> | <img src="images/Allure TestOps.svg" width="40" height="40"> | <img src="images/Telegram.svg" width="40" height="40">

___

## Default settings used [for Jenkins startup](https://jenkins.autotests.cloud/job/c06-egormuratov-salo/) <a href="https://www.jenkins.io/"><img src="./images/Jenkins.svg" width="40" height="40"  alt="Jenkins"/></a>

* REPOSITORY
* BROWSER (default chrome)
* BROWSER_VERSION (default 91.0)
* REMOTE_DRIVER_URL (url address from selenoid or grid. default selenoid.autotests.cloud)
* THREADS (number of threads to run. default 1)
* ALLURE_NOTIFICATIONS_VERSION (default 3.0.2)

![alt "Launch via Jenkins"](./images/jenkinsRun.png)

### Run tests with filled local.properties:

```bash
gradle clean test
```

### Run tests with not filled local.properties:

```bash
gradle clean test -Dbrowser=chrome -DbrowserVersion=91.0 -DremoteDriverUrl=https://user1:1234@selenoid.autotests.cloud/wd/hub/ -Dthreads=1
```

### Serve allure report:

```bash
allure serve build/allure-results
```

## Notification of test results via a bot on Telegram <a href="https://telegram.org/"> <img src="images/Telegram.svg" width="40" height="40"></a>

![alt "tellegram bot"](./images/bot.png "telegram bot")

## Analysis of results in Jenkins via Allure Reports<a href="https://qameta.io/"><img src="images/jenkins_allure.svg" width="40" height="40"></a>

![alt "Allure Reports"](./images/jenkinsResult.png "Allure Reports")

## Analysis of results in Allure TestOps <a href="https://qameta.io/"><img src="images/Allure_EE.svg" width="40" height="40"></a>

![alt "Allure TestOps"](./images/allure1.png "Allure TestOps")
