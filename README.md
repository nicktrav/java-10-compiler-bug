# Minimal reproducer for compilation error with JDK 10+

Running the following results in a NPE being thrown from the compiler:

```bash
$ docker build -t nickt:test .

# Version
$ docker run --rm -it nickt:test /usr/lib/jvm/jdk-10/bin/java -version
openjdk version "10" 2018-03-20
OpenJDK Runtime Environment 18.3 (build 10+46)
OpenJDK 64-Bit Server VM 18.3 (build 10+46, mixed mode)

# Compile
$ docker run --rm -it nickt:test /usr/lib/jvm/jdk-10/bin/javac \
  /tmp/src/main/java/com/example/reproducer/Reproducer.java \
  /tmp/src/main/java/com/example/reproducer/someotherpackage/Baz.java
An exception has occurred in the compiler (10). Please file a bug against the Java compiler via the Java bug reporting page (http://bugreport.java.com) after checking the Bug Database (http://bugs.java.com) for duplicates. Include your program and the following diagnostic in your report. Thank you.
java.lang.NullPointerException
        at jdk.compiler/com.sun.tools.javac.comp.Flow$FlowAnalyzer.visitApply(Flow.java:1233)
        at jdk.compiler/com.sun.tools.javac.tree.JCTree$JCMethodInvocation.accept(JCTree.java:1634)
        at jdk.compiler/com.sun.tools.javac.tree.TreeScanner.scan(TreeScanner.java:49)
        at jdk.compiler/com.sun.tools.javac.comp.Flow$BaseAnalyzer.scan(Flow.java:396)
        at jdk.compiler/com.sun.tools.javac.comp.Flow$FlowAnalyzer.visitVarDef(Flow.java:987)
        at jdk.compiler/com.sun.tools.javac.tree.JCTree$JCVariableDecl.accept(JCTree.java:956)
        at jdk.compiler/com.sun.tools.javac.tree.TreeScanner.scan(TreeScanner.java:49)
        at jdk.compiler/com.sun.tools.javac.comp.Flow$BaseAnalyzer.scan(Flow.java:396)
        at jdk.compiler/com.sun.tools.javac.tree.TreeScanner.scan(TreeScanner.java:57)
        at jdk.compiler/com.sun.tools.javac.comp.Flow$FlowAnalyzer.visitBlock(Flow.java:995)
        at jdk.compiler/com.sun.tools.javac.tree.JCTree$JCBlock.accept(JCTree.java:1020)
        at jdk.compiler/com.sun.tools.javac.tree.TreeScanner.scan(TreeScanner.java:49)
        at jdk.compiler/com.sun.tools.javac.comp.Flow$BaseAnalyzer.scan(Flow.java:396)
        at jdk.compiler/com.sun.tools.javac.comp.Flow$FlowAnalyzer.visitMethodDef(Flow.java:962)
        at jdk.compiler/com.sun.tools.javac.tree.JCTree$JCMethodDecl.accept(JCTree.java:866)
        at jdk.compiler/com.sun.tools.javac.tree.TreeScanner.scan(TreeScanner.java:49)
        at jdk.compiler/com.sun.tools.javac.comp.Flow$BaseAnalyzer.scan(Flow.java:396)
        at jdk.compiler/com.sun.tools.javac.comp.Flow$FlowAnalyzer.visitClassDef(Flow.java:925)
        at jdk.compiler/com.sun.tools.javac.tree.JCTree$JCClassDecl.accept(JCTree.java:774)
        at jdk.compiler/com.sun.tools.javac.tree.TreeScanner.scan(TreeScanner.java:49)
        at jdk.compiler/com.sun.tools.javac.comp.Flow$BaseAnalyzer.scan(Flow.java:396)
        at jdk.compiler/com.sun.tools.javac.comp.Flow$FlowAnalyzer.analyzeTree(Flow.java:1325)
        at jdk.compiler/com.sun.tools.javac.comp.Flow$FlowAnalyzer.analyzeTree(Flow.java:1315)
        at jdk.compiler/com.sun.tools.javac.comp.Flow.analyzeTree(Flow.java:216)
        at jdk.compiler/com.sun.tools.javac.main.JavaCompiler.flow(JavaCompiler.java:1393)
        at jdk.compiler/com.sun.tools.javac.main.JavaCompiler.flow(JavaCompiler.java:1367)
        at jdk.compiler/com.sun.tools.javac.main.JavaCompiler.compile(JavaCompiler.java:965)
        at jdk.compiler/com.sun.tools.javac.main.Main.compile(Main.java:306)
        at jdk.compiler/com.sun.tools.javac.main.Main.compile(Main.java:165)
        at jdk.compiler/com.sun.tools.javac.Main.compile(Main.java:57)
        at jdk.compiler/com.sun.tools.javac.Main.main(Main.java:43)
```

The following two changes both result in the compilation succeeding:

```java
  Reproducer() {
    Object baz = foo.foo(new Baz<Object>() {});
  }
```

and

```java
public class Baz<T> {

  Baz(int baz) {
  }

  protected Baz() {
  }
}
```
