package org.example.proyectojenkinstest;


import org.example.proyectojenkinstest.Cuenta;

public interface CuentaRepository {
    void guardar(Cuenta cuenta);
    Cuenta buscarPorNumero(String numeroCuenta);
}
