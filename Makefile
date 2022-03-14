.PHONY: hello goodbye random

hello:
	java ./hello-world/HelloWorld.java

goodbye:
	java ./hello-world/HelloGoodbye.java John Mari

random:
	java -classpath "./lib/algs4.jar" ./hello-world/RandomWord.java

randomfromfile:
	java -classpath "./lib/algs4.jar" ./hello-world/RandomWord.java < animals8.txt

unionfindqf:
	java ./union-find/QuickFind.java

unionfindqu:
	java ./union-find/QuickUnion.java

unionfindqui:
	java ./union-find/QuickUnionImpv.java
