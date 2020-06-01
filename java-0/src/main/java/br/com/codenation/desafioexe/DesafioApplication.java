package br.com.codenation.desafioexe;

import java.util.ArrayList;
import java.util.List;

public class DesafioApplication {

	public static List<Integer> fibonacci() {
		List<Integer> lista = new ArrayList<>();
		lista.add(0,0);
		lista.add(1,1);
		int i = 2;
		int elemento = lista.get(i-1) + lista.get(i-2);
		while(elemento <= 350) {
			lista.add(i, elemento);
			i++;
			elemento = lista.get(i-1) + lista.get(i-2);
		}
		lista.add(i, elemento);
		return lista;
	}

	public static Boolean isFibonacci(Integer a) {
		List<Integer> fib = fibonacci();
		return fib.contains(a);
	}

}