# 210028924 200220671
# silbery greenbb

compile:bin
	javac -d bin -cp biuoop-1.3.jar src/*/*.java src/*.java
bin:
	mkdir bin
run:jar
	java -jar ass6game.jar
check:
	java -jar checkstyle-5.7-all.jar -c biuoop.xml src/*/*.java src/*java
jar: 
	jar cfm ass6game.jar manifest.txt -C bin . -C resources .
