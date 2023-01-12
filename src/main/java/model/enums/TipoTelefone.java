package model.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public enum TipoTelefone {

    FIXO(1, "Fixo", "+## (##) ####-####"),
    MOVEL(2, "Móvel", "+## (##) # ####-####")
    ;

    private Integer value;
    private String descricao;
    private String mascara;

    TipoTelefone(Integer value, String descricao, String mascara) {
        this.value = value;
        this.descricao = descricao;
        this.mascara = mascara;
    }

    public static TipoTelefone findById(Integer id) {
        List<TipoTelefone> list = Arrays.asList(FIXO, MOVEL);

        for (TipoTelefone tt : list) {
            if (tt.getValue().equals(id)) {
                return tt;
            }
        }
        return null;
    }
}
