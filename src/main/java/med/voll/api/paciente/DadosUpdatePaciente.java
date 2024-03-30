package med.voll.api.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import med.voll.api.model.DadosEndereco;

public record DadosUpdatePaciente(
        @NotNull
        Long id,
        String nome,
        String telefone,
        @Valid
        DadosEndereco endereco) {



}
