target: src/Game.java
	./gradlew jar

.PHONY: test fast
test:
	java -jar build/libs/tic-tac-toe.jar

fast: target test
