package org.example.proyectojenkinstest;

public abstract class Cuenta {
    private String numeroCuenta;
    private double saldo;
    private String titular;

    public Cuenta(String numeroCuenta, String titular, double saldoInicial) {
        this.numeroCuenta = numeroCuenta;
        this.titular = titular;
        this.saldo = saldoInicial;
    }

    public String getNumeroCuenta() { return numeroCuenta; }
    public double getSaldo() { return saldo; }
    public String getTitular() { return titular; }

    public void depositar(double monto) {
        if (monto > 0) {
            this.saldo += monto;
        }
    }

    protected void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public abstract void retirar(double monto);
    public abstract void calcularInteres();
}