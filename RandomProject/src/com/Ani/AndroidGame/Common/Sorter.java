package com.Ani.AndroidGame.Common;

import java.lang.reflect.Type;
import java.util.Comparator;

public abstract class Sorter<Type> {
	  public abstract void sort(Type[] array, int count, Comparator<Type> comparator);
}
