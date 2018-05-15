package com.example.reproducer.someotherpackage;

public class Baz<T> {

  /**
   * If this constructor appears <i>after</i> the second constructor, the compilation succeeds.
   */
  protected Baz() {
  }

  Baz(int baz) {
  }
}
