package org.example.proyectojenkinsest;

import org.example.proyectojenkinstest.Cuenta;
import org.example.proyectojenkinstest.CuentaFactory;
import org.example.proyectojenkinstest.CuentaRepository;
import org.example.proyectojenkinstest.TransferenciaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransferenciaServiceTest {

    @Mock
    private CuentaRepository repository;

    @InjectMocks
    private TransferenciaService transferenciaService;

    private Cuenta cuentaOrigen;
    private Cuenta cuentaDestino;

    @BeforeEach
    void setUp() {
        cuentaOrigen = CuentaFactory.crearCuenta("AHORRO", "111", "Rodolfo", 1000.0);
        cuentaDestino = CuentaFactory.crearCuenta("CORRIENTE", "222", "Molina Tech", 500.0);
    }

    @Test
    void testTransferenciaExitosa() {
        when(repository.buscarPorNumero("111")).thenReturn(cuentaOrigen);
        when(repository.buscarPorNumero("222")).thenReturn(cuentaDestino);

        boolean resultado = transferenciaService.transferir("111", "222", 300.0);

        assertTrue(resultado);
        assertEquals(700.0, cuentaOrigen.getSaldo(), 0.001);
        assertEquals(800.0, cuentaDestino.getSaldo(), 0.001);

        verify(repository, times(1)).guardar(cuentaOrigen);
        verify(repository, times(1)).guardar(cuentaDestino);
    }

    @Test
    void testTransferenciaFallidaPorFondosInsuficientes() {
        when(repository.buscarPorNumero("111")).thenReturn(cuentaOrigen);
        when(repository.buscarPorNumero("222")).thenReturn(cuentaDestino);

        boolean resultado = transferenciaService.transferir("111", "222", 2000.0);

        assertFalse(resultado);
        assertEquals(1000.0, cuentaOrigen.getSaldo());
        assertEquals(500.0, cuentaDestino.getSaldo());
    }
}