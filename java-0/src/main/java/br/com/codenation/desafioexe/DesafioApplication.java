package br.com.codenation.desafioexe;

import java.util.ArrayList;
import java.util.List;

public class DesafioApplication {

	public static final int VALOR_LIMITE = 350;

	public static List<Integer> fibonacci() {
		List<Integer> sequenciaFibonacci = new ArrayList<>();
		sequenciaFibonacci.add(0);
		sequenciaFibonacci.add(1);
		return calcularFibonacci(sequenciaFibonacci);
	}

	public static List<Integer> calcularFibonacci(List<Integer> sequenciaFibonacci){
		Integer valor = sequenciaFibonacci.get(sequenciaFibonacci.size() - 1);
		valor += sequenciaFibonacci.get(sequenciaFibonacci.size() - 2);
		sequenciaFibonacci.add(valor);
		if(valor <= VALOR_LIMITE) {
			calcularFibonacci(sequenciaFibonacci);
		}
		return sequenciaFibonacci;
	}

	public static Boolean isFibonacci(Integer a) {
		return fibonacci().contains(a);
	}

}