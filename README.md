# qa-interview

Preconditions
	To execute the suite of automated tests, JAVA 8 and Maven must be installed and added to the environment variables

This is a maven project, made using Java and Selenium.

To execute tests open a console on "qa interview" folder and run the following commands:
	To run the complete suite of tests: mvn test -Dtest="*Test"
	To run a specific test: mvn test -Dtest="<name>Test", for example: mvn test -Dtest="productShouldBeAddedFromSearchToCartTest"

The list of tests can be found inside qainterview/src/test/java
