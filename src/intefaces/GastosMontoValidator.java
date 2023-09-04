package intefaces;

import exceptions.InvalidGastoException;

@FunctionalInterface
public interface GastosMontoValidator {
    boolean validarMonto(double monto) throws InvalidGastoException;
}
