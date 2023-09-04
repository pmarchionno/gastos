package intefaces;

import exceptions.InvalidGastoException;

import java.util.Objects;

public class GastosMontoValidatorImpl implements GastosMontoValidator{
  @Override
  public boolean validarMonto(double monto) throws InvalidGastoException {
    if (monto < 0 || !Objects.nonNull(monto) ) {
      throw new InvalidGastoException("El monto debe ser mayor o igual a cero.");
    }
    return true;
  }
}
