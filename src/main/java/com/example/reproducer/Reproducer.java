package com.example.reproducer;

import com.example.reproducer.someotherpackage.Baz;

public class Reproducer {

  interface Foo {
    <T> T foo(Baz<T> key);
  }

  private Foo foo;

  Reproducer() {
    // this breaks ...
    Object baz = foo.foo(new Baz<>() {});

    // however, this works ...
    //Object baz = foo.foo(new Baz<Object>() {});
  }
}
