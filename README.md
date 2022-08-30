# Getting Started

- install Java 8
- To run test locally: install browser. 
- You don't need to download the driver binary as I have used webdrivermanager
- To run test with real browser , comment out below line from core.CustomChromeDriver
   - options.addArguments("--headless");

- Framework can be used for:
    - WEB UI automation testing

### Git
- https://github.com/RahulVarma23/todomvc-test-automation-serenity-bdd.git

### Running the smoke or regresssion tests (using cucumber tags)
- mvn clean verify -Dtags="@regression"
- mvn clean verify -Dtags="@smoke"

### Running all tests locally with default options
- mvn clean verify

### Running the test with local IDE e.g eclipse
- clean verify 

### Running the scenarios with different environments (Environements can be configured in src/test/resources/serenity.conf)
- mvn clean verify -Dtags="@regression" -Denv=prod

### How to filter tests
- Features or scenarios should be tagged by different tags like featureName ,smoke ,regression or sanity

### How to view reports
- The Serenity reports will be generated in the target/site/serenity directory
