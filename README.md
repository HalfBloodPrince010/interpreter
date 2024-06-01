# Interpreter
Tree-Walk Interpreter written in Java.

## Running from cmd line

### Compile the Java Classes

```dtd
cd ~/interpreter/java/com/craftinginterpreters/
mkdir build && cd lox
javac ./*.java -d ../build
```

### Then go to build directory containing class files

```dtd
cd ../build
```

### Invoke the Interpreter

- On terminal
```dtd
java com.craftinginterpreters.lox.Lox
```

- Using File Input Argument
```dtd
java com.craftinginterpreters.lox.Lox ../test/func.txt
```