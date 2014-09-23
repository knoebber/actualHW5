package edu.grinnell.csc207.ajunaNfriends.utils;

import static org.junit.Assert.*;

import org.junit.Test;

public class UtilsTest
{

  @Test
  public void test()
  {
    assertEquals(177147,Utils.expt(3,11),0.005);
  }
  
  @Test
  public void test2()
  {
    assertEquals(177147,Utils.recurExpt(3,11),.01);
  }

}
