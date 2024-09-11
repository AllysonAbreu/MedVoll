package med.voll.api.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.agendamentos.DadosAgendamentoConsulta;
import med.voll.api.service.AgendamentosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("agendamento")
@SecurityRequirement(name = "bearer-key")
public class AgendamentosController {

    @Autowired
    private AgendamentosService service;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsulta dados) {
        var dto = service.agendar(dados);
        return ResponseEntity.ok(dto);
    }

}
