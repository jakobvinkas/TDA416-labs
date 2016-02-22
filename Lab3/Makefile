all: build

clean:
	rm -rf bin/*

build:
	javac -d bin *.java

tests: build
	java -cp bin TestSetCorrectness ${type} 1000 100 20
