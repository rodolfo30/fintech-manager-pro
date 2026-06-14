package org.example.proyectojenkinstest;

import org.example.proyectojenkinstest.CuentaFactory;
import org.example.proyectojenkinstest.Cuenta;
import org.example.proyectojenkinstest.CuentaRepository;
import org.example.proyectojenkinstest.TransferenciaService;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] sender) {
        System.out.println("=== BIENVENIDO A FINTECH MANAGER PRO ===");

        // 1. Creamos un repositorio simulado en memoria usando un HashMap
        CuentaRepository repositorioSimulado = new CuentaRepository() {
            private final Map<String, Cuenta> bd = new HashMap<>();

            @Override
            public void guardar(Cuenta cuenta) {
                bd.put(cuenta.getNumeroCuenta(), cuenta);
            }

            @Override
            public Cuenta buscarPorNumero(String numeroCuenta) {
                return bd.get(numeroCuenta);
            }
        };

        // 2. Usamos la Factoría (Patrón Factory) para crear dos cuentas
        Cuenta cuentaOrigen = CuentaFactory.crearCuenta("AHORRO", "111", "Rodolfo", 1000.0);
        Cuenta cuentaDestino = CuentaFactory.crearCuenta("CORRIENTE", "222", "Molina Tech", 500.0);

        // Guardamos las cuentas en nuestro repositorio simulado
        repositorioSimulado.guardar(cuentaOrigen);
        repositorioSimulado.guardar(cuentaDestino);

        System.out.println("Saldo inicial Rodolfo: " + cuentaOrigen.getSaldo() + "€");
        System.out.println("Saldo inicial Molina Tech: " + cuentaDestino.getSaldo() + "€");

        // 3. Inicializamos el servicio de transferencias
        TransferenciaService servicio = new TransferenciaService(repositorioSimulado);

        // 4. Ejecutamos una transferencia de 300€
        System.out.println("\nEfectuando transferencia de 300€ de Rodolfo a Molina Tech...");
        boolean exito = servicio.transferir("111", "222", 300.0);

        if (exito) {
            System.out.println("¡Transferencia realizada con ÉXITO!");
            System.out.println("Nuevo saldo Rodolfo: " + repositorioSimulado.buscarPorNumero("111").getSaldo() + "€");
            System.out.println("Nuevo saldo Molina Tech: " + repositorioSimulado.buscarPorNumero("222").getSaldo() + "€");
        } else {
            System.out.println("La transferencia ha FALLADO.");
        }
    }
}