package demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RunWith(JUnit4.class)
public class StringTest {

  @Test public void testSplit1() {
    final String input = "水产、蔬菜";
    Arrays.asList(input.split("、")).forEach(System.out::println);
  }
  @Test public void testSplit2() {
    final String input = "水产、蔬菜";
    Arrays.asList(input.split(" ")).forEach(System.out::println);
    String output = Arrays.stream(input.split("、")).collect(Collectors.joining(","));
    System.out.println(output);
  }

}
