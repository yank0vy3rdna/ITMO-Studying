compile:
	javac -version
	find -name "*.java" > sources.txt
	javac -g -cp *.jar @sources.txt
	java -version
clean:
	find . -type f -name '*.class' -exec rm {} +
	rm sources.txt
