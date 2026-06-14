package org.example.proyectojenkinstest;

import org.example.proyectojenkinstest.Cuenta;
import org.example.proyectojenkinstest.CuentaRepository;

public class TransferenciaService {
    private CuentaRepository repository;

    public TransferenciaService(CuentaRepository repository) {
        this.repository = repository;
    }

    public boolean transferir(String numeroOrigen, String numeroDestino, double monto) {
        Cuenta origen = repository.buscarPorNumero(numeroOrigen);
        Cuenta destino = repository.buscarPorNumero(numeroDestino);

        if (origen == null || destino == null || monto <= 0) {
            return false;
        }

        try {
            origen.retirar(monto);
            destino.depositar(monto);

            repository.guardar(origen);
            repository.guardar(destino);
            return true;
        } catch (IllegalArgumentException e) {
            return false; // Error en la lógica de negocio (ej. fondos insuficientes)
        }
    }
}