package org.example.proyectojenkinstest;
public class CuentaAhorro extends Cuenta {
    private double tasaInteres = 0.02; // 2% de interés fijo

    public CuentaAhorro(String numeroCuenta, String titular, double saldoInicial) {
        super(numeroCuenta, titular, saldoInicial);
    }

    @Override
    public void retirar(double monto) {
        if (monto > 0 && getSaldo() >= monto) {
            setSaldo(getSaldo() - monto);
        } else {
            throw new IllegalArgumentException("Fondos insuficientes o monto inválido en cuenta de ahorro.");
        }
    }

    @Override
    public void calcularInteres() {
        double interes = getSaldo() * tasaInteres;
        depositar(interes);
    }
}