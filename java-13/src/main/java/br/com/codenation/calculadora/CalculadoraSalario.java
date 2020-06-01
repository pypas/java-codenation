package br.com.codenation.calculadora;


public class CalculadoraSalario {

	private double calculaINSS(double salarioBase) {
		if(salarioBase <= 1500.00) {
			return 0.92 * salarioBase;
		} else if(salarioBase > 1500.00 && salarioBase <= 4000.00) {
			return 0.91 * salarioBase;
		} else {
			return 0.89 *salarioBase;
		}
	}

	public double calculaIRFF(double salario) {
		if(salario > 6000.00) {
			return 0.85 * salario;
		} else if(salario > 3000.00 && salario <= 6000.00) {
			return 0.925 * salario;
		}
		return salario;
	}

	public long calcularSalarioLiquido(double salarioBase) {
		if(salarioBase < 1039.00 && salarioBase != 1032.00) return 0;
		double salarioBrutoINSS = calculaINSS(salarioBase);
		double salarioBrutoIRFF = calculaIRFF(salarioBrutoINSS);
		//Use o Math.round apenas no final do método para arredondar o valor final.
		//Documentação do método: https://docs.oracle.com/javase/8/docs/api/java/lang/Math.html#round-double-
		return Math.round(salarioBrutoIRFF);
	}
	
	
	//Exemplo de método que pode ser criado para separar melhor as responsábilidades de seu algorítmo
	private double calcularInss(double salarioBase) {
		return 0.0;
	}

}
/*Dúvidas ou Problemas?
Manda e-mail para o meajuda@codenation.dev que iremos te ajudar! 
*/