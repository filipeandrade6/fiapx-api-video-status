package org.jfm.domain.entities.enums;

import org.jfm.domain.exceptions.ErrosSistemaEnum;
import org.jfm.domain.exceptions.ParamException;

public enum Status {
  SOLICITADO, // primeiro
  CARREGADO, // upload finalizado
  PROCESSANDO, // processando pra fazer o zip
  CONCLUIDO, // ok
  FALHA; // deu ruim

  public static Status fromString(String statusString) {
    for (Status status : Status.values()) {
      if (status.name().equalsIgnoreCase(statusString)) {
        return status;
      }
    }

    // throw new ParamException(ErrosSistemaEnum.PARAM_INVALID.getMessage());
  }

  public static boolean statusEhSolicitado(String statusString) {
    return Status.SOLICITADO.name().equalsIgnoreCase(statusString);
  }

  public static boolean statusEhFalha(String statusString) {
    return Status.FALHA.name().equalsIgnoreCase(statusString);
  }

}
