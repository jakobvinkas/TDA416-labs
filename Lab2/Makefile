all: build

clean:
	rm -rf bin/*

build:
	javac -d bin Lab2.java

fig1: build
	java -cp bin Lab2 -k8 -w12 -h12 < fig1.txt

fig2: build
	java -cp bin Lab2 -k8 -w12 -h12 < fig2.txt
