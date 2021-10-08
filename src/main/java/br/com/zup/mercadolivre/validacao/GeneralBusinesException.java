package br.com.zup.mercadolivre.validacao;

public class GeneralBusinesException extends CommonException {

    public GeneralBusinesException(String field, String msg) {
        super(field, msg);
    }
}
