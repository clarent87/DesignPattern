package com.design.adapter.iterenum;

import java.util.Enumeration;
import java.util.Iterator;

// 아래 예제에서는 generic을 쓰고 있지 않는데, 일반적으로 generic을 써주는게 좋다.
public class EnumerationIterator implements Iterator {

  Enumeration enumeration;

  public EnumerationIterator(Enumeration enumeration) {
    this.enumeration = enumeration;
  }

  public boolean hasNext() {
    return enumeration.hasMoreElements();
  }

  public Object next() {
    return enumeration.nextElement();
  }

  public void remove() {
    throw new UnsupportedOperationException();
  }
}
