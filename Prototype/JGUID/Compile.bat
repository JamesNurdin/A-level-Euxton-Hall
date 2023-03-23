del *.class
del UI\*.class
del Util\*.class
del Session\*.class

javac UI\*.java

javac Util\*.java

javac Session\*.java

javac JGuiD.java
pause
java  JGuiD
pause