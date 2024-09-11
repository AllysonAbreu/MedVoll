package med.voll.api.service;

import jakarta.validation.Valid;
import med.voll.api.domain.agendamentos.Consulta;
import med.voll.api.domain.agendamentos.DadosAgendamentoConsulta;
import med.voll.api.domain.agendamentos.DadosDetalhamentoConsulta;
import med.voll.api.domain.agendamentos.validadores.ValidadorAgendamentoConsulta;
import med.voll.api.domain.exception.ValidacaoException;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.paciente.Paciente;
import med.voll.api.repositories.ConsultaRepository;
import med.voll.api.repositories.MedicoRepository;
import med.voll.api.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendamentosService {

    @Autowired
    private ConsultaRepository repository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidadorAgendamentoConsulta> validadores;


    public DadosDetalhamentoConsulta agendar(@Valid DadosAgendamentoConsulta dados) {

        if(!pacienteRepository.existsById(dados.idPaciente())) {
            throw new ValidacaoException(String.format("Paciente não encontrado para id: %s", dados.idPaciente()));
        }

        if(dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())) {
            throw new ValidacaoException(String.format("Médico não encontrado para id: %s", dados.idMedico()));
        }

        validadores.forEach(v -> v.validar(dados));

        Medico medico = escolherMedico(dados);
        if (medico == null) {
            throw new ValidacaoException("Não existe médico disponível para essa data");
        }
        Paciente paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        Consulta consulta = new Consulta(null, medico, paciente, dados.data());
        repository.save(consulta);

        return new DadosDetalhamentoConsulta(consulta);
    }

    private Medico escolherMedico(DadosAgendamentoConsulta dados) {
        if(dados.idMedico() != null) {
            return medicoRepository.getReferenceById(dados.idMedico());
        }

        if(dados.especialidade() != null) {
            throw new ValidacaoException("Especialidade é obrigatória quando médico não for escolhido!");
        }

        return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(), dados.data());
    }
}
