package org.example.proyectojenkinstest;


import org.example.proyectojenkinstest.Cuenta;
import org.example.proyectojenkinstest.CuentaAhorro;
import org.example.proyectojenkinstest.CuentaCorriente;

public class CuentaFactory {
    public static Cuenta crearCuenta(String tipo, String numero, String titular, double saldoInicial) {
        if ("AHORRO".equalsIgnoreCase(tipo)) {
            return new CuentaAhorro(numero, titular, saldoInicial);
        } else if ("CORRIENTE".equalsIgnoreCase(tipo)) {
            return new CuentaCorriente(numero, titular, saldoInicial);
        }
        throw new IllegalArgumentException("Tipo de cuenta no soportado: " + tipo);
    }
}