package br.com.codenation;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StatisticUtil {

	public static int average(int[] elements) {
		return Arrays.stream(elements).reduce(0, Integer::sum)/elements.length;
	}

	public static int mode(int[] elements) {
		HashMap<Integer, Integer> mapa = new HashMap<>();
		int maximoOcorrencias = 0;
		int moda = 0;
		for(int num : elements) {
			int ocorrencias = mapa.get(num) != null ? mapa.get(num) + 1 : 1;
			mapa.put(num, ocorrencias);
			if (ocorrencias > maximoOcorrencias) {
				maximoOcorrencias = ocorrencias;
				moda = num;
			}
		}
		return moda;
	}

	public static int median(int[] elements) {
		Arrays.sort(elements);
		if(elements.length % 2 == 0) {
			return((elements[elements.length/2] + elements[elements.length/2 - 1])/2);
		}
		return elements[elements.length/2];
	}
}