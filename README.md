# LambdaTest Selenium Advanced Assignment

This project demonstrates automated testing using Selenium WebDriver with LambdaTest's cloud platform. It includes advanced Selenium interactions like handling multiple windows, scrolling, and explicit waits.

## Prerequisites

1. Java JDK 17 or higher
2. Maven
3. LambdaTest Account (for username and access key)
4. Git

## Setup Instructions

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd LambdaTest-Selenium
   ```

2. Set up LambdaTest credentials as environment variables:
   ```bash
   export LT_USERNAME="your_username"
   export LT_ACCESS_KEY="your_access_key"
   ```
   For Windows, use:
   ```bash
   set LT_USERNAME=your_username
   set LT_ACCESS_KEY=your_access_key
   ```

3. Install dependencies:
   ```bash
   mvn clean install
   ```

## Running Tests

1. Run tests using Maven:
   ```bash
   mvn test
   ```

The test suite will execute the following scenarios:
- Navigate to LambdaTest website
- Explore integrations in a new tab
- Verify TestingWhiz integration
- Navigate to LambdaTest blog and community
- Handle multiple windows and tabs
- Perform scrolling and explicit waits

## Project Structure

- `src/test/java/org/lambdatest/selenium/task/` - Contains test classes
- `testng.xml` - TestNG configuration file
- `pom.xml` - Maven dependencies and build configuration

## Dependencies

- Selenium WebDriver 4.9.0
- TestNG 7.7.0
- Maven Surefire Plugin 3.0.0

## Configuration

The test configuration is managed through `testng.xml` which includes:
- Browser configuration
- Platform selection
- Browser version

## Running in Gitpod

1. Click the button below to open in Gitpod:

   [![Open in Gitpod](https://gitpod.io/button/open-in-gitpod.svg)](https://gitpod.io/#https://github.com/YOUR_REPO_URL)

2. Set up LambdaTest credentials in Gitpod environment variables:
   ```bash
   gp env LT_USERNAME="your_username"
   gp env LT_ACCESS_KEY="your_access_key"
   ```

3. The project will automatically:
   - Install dependencies (mvn install)
   - Set up Java 17 environment
   - Configure necessary VS Code extensions

4. Run tests:
   ```bash
   mvn test
   ```

Note: After setting environment variables, you may need to restart your Gitpod workspace for the changes to take effect.

## Notes

- Tests run on LambdaTest's cloud platform
- Requires active internet connection
- Make sure LambdaTest credentials are properly set before running tests
