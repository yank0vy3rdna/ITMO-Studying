compile:
	javac -version
	find -name "*.java" > sources.txt
	javac -g -cp *.jar @sources.txt
	java -version
	java -cp .:Pokemon.jar:Pokemons:Moves Lab2 
helios:
	javac18 -version
	find -name "*.java" > sources.txt
	javac18 -cp *.jar @sources.txt
	java18 -version
	java18 -cp .:Pokemon.jar:Pokemons:Moves Lab2
test:
	java -cp .:Pokemon.jar Lab2Test
heliostest:
	java18 -cp .:Pokemon.jar Lab2Test
clean:
	find . -type f -name '*.class' -exec rm {} +
	rm sources.txt