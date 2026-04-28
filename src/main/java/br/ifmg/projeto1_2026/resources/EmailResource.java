package br.ifmg.projeto1_2026.resources;

import br.ifmg.projeto1_2026.dto.EmailDTO;
import br.ifmg.projeto1_2026.service.EmailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailResource
{
    @Autowired
    private EmailService emailService;

    @PostMapping
    public ResponseEntity<Void> sendEmail(@Valid @RequestBody EmailDTO emailDTO){
        emailService.sendMail(emailDTO);
        return ResponseEntity.noContent().build();
    }
}
