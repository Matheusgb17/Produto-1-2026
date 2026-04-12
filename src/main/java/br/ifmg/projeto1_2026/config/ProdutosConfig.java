package br.ifmg.projeto1_2026.config;

import br.ifmg.projeto1_2026.anotations.TipoDoNotificador;
import br.ifmg.projeto1_2026.constants.TipoDeNotificacao;
import br.ifmg.projeto1_2026.util.NotificacaoEmail;
import br.ifmg.projeto1_2026.util.NotificacaoSMS;
import br.ifmg.projeto1_2026.util.Notificador;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class ProdutosConfig {

    @Value("${notificador.email.host}")
    private String servidorSMTP;

    @Profile("prod")
    @TipoDoNotificador(value = TipoDeNotificacao.EMAIL)
    @Bean
    public Notificador notificacaoEmail(){
        NotificacaoEmail notificacaoEmail = new NotificacaoEmail(servidorSMTP);
        notificacaoEmail.setCaixaAlta(true);
        return notificacaoEmail;
    }

    @Profile({"dev", "test"})
    @TipoDoNotificador(value = TipoDeNotificacao.SMS)
    @Bean
    public Notificador notificacaoSMS(){
        NotificacaoSMS notificacaoSMS = new NotificacaoSMS();
        notificacaoSMS.setCaixaAlta(true);

        return notificacaoSMS;
    }
}
