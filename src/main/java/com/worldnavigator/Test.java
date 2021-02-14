package com.worldnavigator;

import java.util.ArrayDeque;
import java.util.Deque;

public class Test {
  public static void main(String[] args) {
      Deque<String> strings = new ArrayDeque<>(2);
      strings.add("hey");
      strings.add("yey");
      strings.add("yey");

    System.out.println(strings.size());
  }
}
