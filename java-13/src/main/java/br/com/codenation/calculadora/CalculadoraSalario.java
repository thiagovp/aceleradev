package br.com.codenation.calculadora;

public class CalculadoraSalario {

    private final double VALOR_SALARIO_MINIMO = 1039.0;

    private final double VALOR_LIMITE_PRIMEIRA_FAIXA_INSS = 1500;
    private final double VALOR_LIMITE_SEGUNDA_FAIXA_INSS = 4000;

    private final double PORCENTAGEM_PRIMEIRA_FAIXA_INSS = 0.08;
    private final double PORCENTAGEM_SEGUNDA_FAIXA_INSS = 0.09;
    private final double PORCENTAGEM_TERCEIRA_FAIXA_INSS = 0.11;

    private final double VALOR_LIMITE_ISENCAO_IRRF = 3000;
    private final double VALOR_LIMITE_PRIMEIRA_FAIXA_IRRF = 6000;

    private final double PORCENTAGEM_PRIMEIRA_FAIXA_IRRF = 0.075;
    private final double PORCENTAGEM_SEGUNDA_FAIXA_IRRF = 0.15;

    public long calcularSalarioLiquido(double salarioBase) {
        if (isMenorQueSalarioMinimo(salarioBase)) {
            return 0;
        }
        salarioBase = calcularInss(salarioBase);
        salarioBase = calcularIrrf(salarioBase);

        return Math.round(salarioBase);
    }

    private double calcularIrrf(double salarioBase) {
        if (salarioBase > VALOR_LIMITE_PRIMEIRA_FAIXA_IRRF) {
            salarioBase -= salarioBase * PORCENTAGEM_SEGUNDA_FAIXA_IRRF;
        } else if (salarioBase > VALOR_LIMITE_ISENCAO_IRRF && salarioBase <= VALOR_LIMITE_PRIMEIRA_FAIXA_IRRF) {
            salarioBase -= salarioBase * PORCENTAGEM_PRIMEIRA_FAIXA_IRRF;
        }

        return salarioBase;
    }

    private boolean isMenorQueSalarioMinimo(double salarioBase) {
        return salarioBase < VALOR_SALARIO_MINIMO;
    }

    private double calcularInss(double salarioBase) {
        if (salarioBase <= VALOR_LIMITE_PRIMEIRA_FAIXA_INSS) {
            salarioBase -= salarioBase * PORCENTAGEM_PRIMEIRA_FAIXA_INSS;
        } else if (salarioBase <= VALOR_LIMITE_SEGUNDA_FAIXA_INSS) {
            salarioBase -= salarioBase * PORCENTAGEM_SEGUNDA_FAIXA_INSS;
        } else {
            salarioBase -= salarioBase * PORCENTAGEM_TERCEIRA_FAIXA_INSS;
        }
        return salarioBase;
    }
}
