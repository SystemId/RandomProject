package com.Ani.AndroidGame.Common;


import java.util.Arrays;
import java.util.Comparator;

public class StandardSorter<T> extends Sorter<T> {


	@Override
	public void sort(T[] array, int count, Comparator<T> comparator) {
		 Arrays.sort(array, 0, count, comparator);
		
	}



}
