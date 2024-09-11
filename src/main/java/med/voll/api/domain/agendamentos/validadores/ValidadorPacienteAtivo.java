package med.voll.api.domain.agendamentos.validadores;

import med.voll.api.domain.agendamentos.DadosAgendamentoConsulta;
import med.voll.api.domain.exception.ValidacaoException;
import med.voll.api.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ValidadorPacienteAtivo implements ValidadorAgendamentoConsulta{

    @Autowired
    private PacienteRepository repository;

    public void validar(DadosAgendamentoConsulta dados) {
        var pacienteEstaAtivo = repository.findAtivoById(dados.idPaciente());
        if(!pacienteEstaAtivo) {
            throw new ValidacaoException(String.format("Consulta não pode ser agendada para paciente com id: %d", dados.idPaciente()));
        }
    }
}
