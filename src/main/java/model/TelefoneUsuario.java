package model;

import lombok.Getter;
import lombok.Setter;
import model.enums.TipoTelefone;

@Getter
@Setter
public class TelefoneUsuario {
    private Long id;
    private String numero;
    private TipoTelefone tipoTelefone;
    private Usuario usuario;

    @Override
    public String toString() {
        return "TelefoneUsuario{" +
                "id=" + id +
                ", numero='" + numero + '\'' +
                ", tipoTelefone=" + tipoTelefone +
                ", usuario=" + usuario.getNome() +
                '}';
    }
}
