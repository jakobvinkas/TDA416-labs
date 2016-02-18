all: build

clean:
	rm -rf bin/*

build:
	javac -d bin *.java

run: build
	java -cp bin

tests: build
	java -cp bin TestSetCorrectness 2 1000 100 20
