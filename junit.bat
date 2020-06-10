javac -cp junit/junit-4.13.jar;hamcreast-core-1.3.jar ./src/*.java ./src/calculator/*.java ./src/testingtool/tests/*.java ./src/testingtool/*.java ./src/unittests/*.java -d classes
java -cp classes;junit/junit-4.13.jar;junit/hamcrest-core-1.3.jar org.junit.runner.JUnitCore UnitTests
