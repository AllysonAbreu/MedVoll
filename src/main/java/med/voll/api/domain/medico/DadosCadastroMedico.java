package med.voll.api.domain.medico;

import med.voll.api.domain.endereco.DadosEndereco;
import med.voll.api.domain.medico.enumeration.Especialidade;

public record DadosCadastroMedico(String nome, String email, String crm, Especialidade especialidade, DadosEndereco endereco) {
}
