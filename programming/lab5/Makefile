compile:
	javac -version
	find -name "*.java" > sources.txt
	javac -g -cp *.jar @sources.txt
	java -version
	java -cp . Lab3
helios:
	javac18 -version
	find -name "*.java" > sources.txt
	javac18 -cp *.jar @sources.txt
	java18 -version
	java18 -cp . Lab3
clean:
	find . -type f -name '*.class' -exec rm {} +
	rm sources.txt