K = 8
FIG = fig2

all: build

clean:
	rm -rf bin/*

build:
	javac -d bin Lab2.java

run: build
	java -cp bin Lab2 -k$(K) -w12 -h12 < $(FIG).txt
