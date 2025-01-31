package org.jfm.domain.entities.enums;

import org.jfm.domain.exceptions.ErrosSistemaEnum;
import org.jfm.domain.exceptions.ParamException;

public enum Status {
  CARREGADO,
  PROCESSANDO,
  CONCLUIDO,
  FALHA;

  public static Status fromString(String statusString) {
    for (Status status : Status.values()) {
      if (status.name().equalsIgnoreCase(statusString)) {
        return status;
      }
    }

    throw new ParamException(ErrosSistemaEnum.PARAM_INVALID.getMessage());
  }
}
