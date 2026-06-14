package org.example.proyectojenkinstest;

public class CuentaCorriente extends Cuenta {
    private double limiteSobregiro = 500.0; // Permite quedar en negativo hasta -500

    public CuentaCorriente(String numeroCuenta, String titular, double saldoInicial) {
        super(numeroCuenta, titular, saldoInicial);
    }

    @Override
    public void retirar(double monto) {
        if (monto > 0 && (getSaldo() + limiteSobregiro) >= monto) {
            setSaldo(getSaldo() - monto);
        } else {
            throw new IllegalArgumentException("Límite de sobregiro excedido.");
        }
    }

    @Override
    public void calcularInteres() {
        // Las cuentas corrientes corporativas no generan intereses acumulativos básicos
    }
}