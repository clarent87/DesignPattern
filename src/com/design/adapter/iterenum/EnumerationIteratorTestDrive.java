package com.design.adapter.iterenum;

import java.util.*;

// 그냥 vector에 있는 enumeration 을 받아서 어댑터로 Iterator 변수에 받아서 쓰는 예제
public class EnumerationIteratorTestDrive {

  public static void main(String args[]) {
    Vector v = new Vector(Arrays.asList(args));
    Iterator iterator = new EnumerationIterator(v.elements());
    while (iterator.hasNext()) {
      System.out.println(iterator.next());
    }
  }
}
