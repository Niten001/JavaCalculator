javac ./src/*.java ./src/calculator/*.java ./src/testingtool/tests/*.java ./src/testingtool/*.java -d classes
javac -cp junit/junit-4.10.jar junit/tests/*.java -d junit/classes
java -cp classes javacalculator.JavaCalculator
java -cp junit/classes:junit/junit-4.10.jar org.junit.runner.JUnitCore TestBasic