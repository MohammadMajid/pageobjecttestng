# UI AUtomation Tests

### Following tools were used in this framework:

Maven, TestNG, Selenium WebDriver, WebDriver Extensions, Log4j, Maven Surefire Plugin(for parallel tests execution), Maven Compiler Plugin, Apache POI, Hamcrest library (for assertion), BrowserStack.
All automation is done in Java.

### Execute the following commands to run tests:

- Full test run:
  ```bash
  mvn clean test -DsuiteXmlFile=groups.xml
  ```

- Smoke tests (QA):
  ```bash
  mvn test -Dsuite=smoke -Denvironment=qa
  ```

- Regression tests (QA):
  ```bash
  mvn test -Dsuite=regression -Denvironment=qa
  ```

### GitHub Actions

A GitHub workflow is available in `.github/workflows/github-ci.yml`.
The workflow:
- builds code
- executes `mvn clean test`
- caches Maven dependencies
- runs on `push` and `pull_request` for `main` and `master`

### References:

[Selenium](https://www.selenium.dev/)

[TestNG](https://testng.org/doc/)

[Maven Repository](https://mvnrepository.com/)

[Maven Surefire Plugin Using TestNG](https://maven.apache.org/surefire-archives/surefire-2.21.0/maven-surefire-plugin/examples/testng.html)