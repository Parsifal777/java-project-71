.PHONY: build run test clean run-dist

build:
	gradlew.bat clean installDist

run:
	gradlew.bat run

run-dist:
	build\install\app\bin\app.bat

test:
	gradlew.bat test

clean:
	gradlew.bat clean