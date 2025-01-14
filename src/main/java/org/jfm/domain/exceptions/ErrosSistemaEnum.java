package org.jfm.domain.exceptions;

public enum ErrosSistemaEnum {
  DATABASE_ERROR("Erro de persistência"),
  PARAM_INVALID("Parâmetro(s) invalido(s)"),
  ITEM_NOT_FOUND("Item não encontrado"),
  FALHA_COMUNICACAO("Houve uma falha de comunicação");

  private final String message;

  ErrosSistemaEnum(String message) {
      this.message = message;
  }

  public String getMessage() {
      return message;
  }
}
