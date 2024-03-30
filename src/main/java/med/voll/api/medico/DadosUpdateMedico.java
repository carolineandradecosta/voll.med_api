package med.voll.api.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import med.voll.api.endereco.Endereco;
import med.voll.api.model.DadosEndereco;

public record DadosUpdateMedico(
        @NotNull
        Long id,
        String nome,
        String telefone,
        @Valid
        DadosEndereco endereco) {
}
