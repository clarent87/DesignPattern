package com.design.composite.menu;

public abstract class MenuComponent {

  // MenuComponent 를 추가, 제거, 가져오기 위한 method들. ( tree 관리.)

  public void add(MenuComponent menuComponent) {
    throw new UnsupportedOperationException();
  }

  public void remove(MenuComponent menuComponent) {
    throw new UnsupportedOperationException();
  }

  public MenuComponent getChild(int i) {
    throw new UnsupportedOperationException();
  }

  // MenuItem 에서 작업을 처리하기 위해 사용하는 메소드들 (일부는 Menu에서도 씀 )

  public String getName() {
    throw new UnsupportedOperationException();
  }

  public String getDescription() {
    throw new UnsupportedOperationException();
  }

  public double getPrice() {
    throw new UnsupportedOperationException();
  }

  public boolean isVegetarian() {
    throw new UnsupportedOperationException();
  }

  public void print() {
    throw new UnsupportedOperationException();
  }
}
