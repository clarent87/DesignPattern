package com.design.composite.menuiterator;

import java.util.Iterator;
import java.util.Stack;

public class CompositeIterator implements Iterator {

  Stack stack = new Stack(); // 기존 Iterator에서 Position을 관리했듯.. Stack에 composite를 만날때 마다 iterator를 저장

  public CompositeIterator(Iterator iterator) {
    stack.push(iterator);
  } // [*] case1. Arraylist의 iterator 가 들어옴. (composite의 경우, 내부에 MenuComponent arraylist가 있음)
    // [*] case2. CompositeIterator가 들어옴

  public Object next() {
    if (hasNext()) { // 이게 true란것은 stack에서 가져온 iterator에 next가 있다는것
      Iterator iterator = (Iterator) stack.peek(); // stack에서 iterator 가져옴
      MenuComponent component = (MenuComponent) iterator.next();
      if (component instanceof Menu) { // 받아온게 Composite이면
        stack.push(component.createIterator()); // stack에 저장
      }
      return component; // component는 그냥 반환.
    } else {
      return null;
    }
  }

  public boolean hasNext() {
    if (stack.empty()) {
      return false;
    } else {
      Iterator iterator = (Iterator) stack.peek();
      if (!iterator.hasNext()) {
        stack.pop();
        return hasNext();
      } else {
        return true;
      }
    }
  }

  public void remove() {
    throw new UnsupportedOperationException();
  }
}


