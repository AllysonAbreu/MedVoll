package med.voll.api.domain.agendamentos.validadores;

import med.voll.api.domain.agendamentos.DadosAgendamentoConsulta;
import med.voll.api.domain.exception.ValidacaoException;
import med.voll.api.repositories.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoAtivo implements ValidadorAgendamentoConsulta {

    @Autowired
    private MedicoRepository repository;

    public void validar(DadosAgendamentoConsulta dados) {

        if(dados.idMedico() == null) {
            return;
        }

        var medicoEstaAtivo = repository.findAtivoById(dados.idMedico());
        if(!medicoEstaAtivo) {
            throw new ValidacaoException(String.format("Consulta não pode ser agendada com o médico de id: %d", dados.idMedico()));
        }
    }
}
