package com.examplenewstack.newstack.domain.loan.service;

import com.examplenewstack.newstack.config.rabbitMQ.RabbitMQConfig;
import com.examplenewstack.newstack.config.rabbitMQ.service.EmailService;
import com.examplenewstack.newstack.domain.loan.dto.LoanNotificationDTO;
import com.examplenewstack.newstack.domain.loan.enums.StatusLoan;
import jakarta.mail.MessagingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

@Service
public class NotificationConsumer {

    private static final Logger logger = LoggerFactory.getLogger(NotificationConsumer.class);


    private final EmailService emailService;

    public NotificationConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = RabbitMQConfig.NOTIFICATION_QUEUE_NAME)
    public void receiveLoanNotification(LoanNotificationDTO notification) {
        logger.info("MENSAGEM RECEBIDA PARA GERAR EMAIL: {}", notification);


        String subject = null;
        String htmlContent = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");


        if (notification.statusLoan() == StatusLoan.EMPRESTADO) {
            subject = "Empréstimo Confirmado! | Biblioteca NewStack";
            htmlContent = String.format("""
        <!DOCTYPE html>
        <html lang="pt-BR">
        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <style>
                body { margin: 0; padding: 0; font-family: Arial, sans-serif; }
                .container { width: 100%%; max-width: 600px; }
                @media screen and (max-width: 600px) {
                    .container { width: 100%% !important; }
                    .content { padding: 20px !important; }
                }
            </style>
        </head>
        <body style="margin: 0; padding: 0; background-color: #f4f7f6;">
            <table align="center" border="0" cellpadding="0" cellspacing="0" class="container" style="width: 100%%; max-width: 600px; border-collapse: collapse;">
                <tr>
                    <td align="center" style="background-color: #2c3e50; padding: 20px 0;">
                        <h1 style="color: #ffffff; margin: 0; font-family: Arial, sans-serif;">Biblioteca NewStack</h1>
                    </td>
                </tr>
                <tr>
                    <td bgcolor="#ffffff" class="content" style="padding: 40px 30px;">
                        <h2 style="color: #3498db; font-family: Arial, sans-serif;">Empréstimo Confirmado!</h2>
                        <p style="color: #555555; font-family: Arial, sans-serif; font-size: 16px; line-height: 1.5;">
                            Olá, %s!
                        </p>
                        <p style="color: #555555; font-family: Arial, sans-serif; font-size: 16px; line-height: 1.5;">
                            Ótima notícia! O seu empréstimo foi processado com sucesso. Confira os detalhes abaixo:
                        </p>
                        <table border="0" cellpadding="10" cellspacing="0" style="width: 100%%; background-color: #ecf0f1; border-left: 4px solid #3498db; margin-top: 20px; margin-bottom: 20px;">
                            <tr>
                                <td style="color: #333; font-family: Arial, sans-serif;">
                                    <strong>Livro:</strong><br>
                                    <span style="font-size: 18px;">%s</span>
                                </td>
                            </tr>
                            <tr>
                                <td style="color: #333; font-family: Arial, sans-serif;">
                                    <strong>Status:</strong><br>
                                    <span style="font-size: 18px; color: #27ae60; font-weight: bold;">%s</span>
                                </td>
                            </tr>
                            <tr>
                                <td style="color: #333; font-family: Arial, sans-serif;">
                                    <strong>Data de Devolução Esperada:</strong><br>
                                    <span style="font-size: 18px;">%s</span>
                                </td>
                            </tr>
                        </table>
                        <p style="color: #555555; font-family: Arial, sans-serif; font-size: 16px; line-height: 1.5;">
                            Aproveite sua leitura e não se esqueça de devolver na data combinada!
                        </p>
                    </td>
                </tr>
                <tr>
                    <td align="center" style="background-color: #34495e; padding: 20px; color: #bdc3c7; font-family: Arial, sans-serif; font-size: 12px;">
                        © %d Biblioteca NewStack. Todos os direitos reservados.
                    </td>
                </tr>
            </table>
        </body>
        </html>
        """,
                    notification.clientName(),
                    notification.bookTitle(),
                    notification.statusLoan().name(),
                    notification.expectedReturnDate().format(formatter),
                    java.time.Year.now().getValue());
        } else if (notification.statusLoan() == StatusLoan.RESERVADO) {
            subject = "Reserva Confirmada | Biblioteca NewStack";
            htmlContent = String.format("""
                            <!DOCTYPE html>
                            <html lang="pt-BR">
                            <head>
                                <meta charset="UTF-8">
                                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                                <style>
                                    body { margin: 0; padding: 0; font-family: Arial, sans-serif; }
                                    .container { width: 100%%; max-width: 600px; }
                                    @media screen and (max-width: 600px) {
                                        .container { width: 100%% !important; }
                                        .content { padding: 20px !important; }
                                    }
                                </style>
                            </head>
                            <body style="margin: 0; padding: 0; background-color: #f4f7f6;">
                                <table align="center" border="0" cellpadding="0" cellspacing="0" class="container" style="width: 100%%; max-width: 600px; border-collapse: collapse;">
                                    <tr>
                                        <td align="center" style="background-color: #2c3e50; padding: 20px 0;">
                                            <h1 style="color: #ffffff; margin: 0; font-family: Arial, sans-serif;">Biblioteca NewStack</h1>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td bgcolor="#ffffff" class="content" style="padding: 40px 30px;">
                                            <h2 style="color: #e67e22; font-family: Arial, sans-serif;">Reserva Confirmada!</h2>
                                            <p style="color: #555555; font-family: Arial, sans-serif; font-size: 16px; line-height: 1.5;">
                                                Olá, %s!
                                            </p>
                                            <p style="color: #555555; font-family: Arial, sans-serif; font-size: 16px; line-height: 1.5;">
                                                O livro que você solicitou não está disponível no momento. Mas não se preocupe, sua reserva foi feita e você está na fila de espera.
                                            </p>
                                            <table border="0" cellpadding="10" cellspacing="0" style="width: 100%%; background-color: #ecf0f1; border-left: 4px solid #e67e22; margin-top: 20px; margin-bottom: 20px;">
                                                <tr>
                                                    <td style="color: #333; font-family: Arial, sans-serif;">
                                                        <strong>Livro:</strong><br>
                                                        <span style="font-size: 18px;">%s</span>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td style="color: #333; font-family: Arial, sans-serif;">
                                                        <strong>Status:</strong><br>
                                                        <span style="font-size: 18px; color: #e67e22; font-weight: bold;">%s</span>
                                                    </td>
                                                </tr>
                                            </table>
                                            <p style="color: #555555; font-family: Arial, sans-serif; font-size: 16px; line-height: 1.5;">
                                                Nós enviaremos um novo e-mail assim que o livro estiver disponível para retirada.
                                            </p>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="center" style="background-color: #34495e; padding: 20px; color: #bdc3c7; font-family: Arial, sans-serif; font-size: 12px;">
                                            © %d Biblioteca NewStack. Todos os direitos reservados.
                                        </td>
                                    </tr>
                                </table>
                            </body>
                            </html>
                            """,
                    notification.clientName(),
                    notification.bookTitle(),
                    notification.statusLoan().name(),
                    java.time.Year.now().getValue());

        } else if (notification.statusLoan() == StatusLoan.DEVOLVIDO) {
            subject = "Devolução Confirmada! | Biblioteca NewStack";
            htmlContent = String.format("""
        <!DOCTYPE html>
        <html lang="pt-BR">
        <head>
            <meta charset="UTF-8">
        </head>
        <body style="margin: 0; padding: 0; background-color: #f4f7f6; font-family: Arial, sans-serif;">
            <table align="center" border="0" cellpadding="0" cellspacing="0" style="width: 100%%; max-width: 600px; border-collapse: collapse;">
                <tr>
                    <td align="center" style="background-color: #2c3e50; padding: 20px 0;">
                        <h1 style="color: #ffffff; margin: 0;">Biblioteca NewStack</h1>
                    </td>
                </tr>
                <tr>
                    <td bgcolor="#ffffff" style="padding: 40px 30px;">
                        <h2 style="color: #27ae60;">Devolução Confirmada!</h2>
                        <p style="color: #555555; font-size: 16px; line-height: 1.5;">
                            Olá, %s!
                        </p>
                        <p style="color: #555555; font-size: 16px; line-height: 1.5;">
                            Confirmamos o recebimento do livro e a finalização do seu empréstimo. Obrigado!
                        </p>
                        <table border="0" cellpadding="10" cellspacing="0" style="width: 100%%; background-color: #ecf0f1; border-left: 4px solid #27ae60; margin-top: 20px; margin-bottom: 20px;">
                            <tr>
                                <td style="color: #333;">
                                    <strong>Livro:</strong><br>
                                    <span style="font-size: 18px;">%s</span>
                                </td>
                            </tr>
                            <tr>
                                <td style="color: #333;">
                                    <strong>Status:</strong><br>
                                    <span style="font-size: 18px; color: #27ae60; font-weight: bold;">%s</span>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td align="center" style="background-color: #34495e; padding: 20px; color: #bdc3c7; font-size: 12px;">
                        © %d Biblioteca NewStack. Todos os direitos reservados.
                    </td>
                </tr>
            </table>
        </body>
        </html>
        """,
                    notification.clientName(),
                    notification.bookTitle(),
                    notification.statusLoan().name(),
                    java.time.Year.now().getValue());
        }

        if (htmlContent != null) {

            try {
                emailService.sendHtmlMessage(notification.clientEmail(), subject, htmlContent);
                logger.info("E-mail HTML de notificação enviado com sucesso para: {}", notification.clientEmail());
            } catch (MessagingException e) {
                logger.error("Falha ao enviar e-mail HTML para: {}. Erro: {}", notification.clientEmail(), e.getMessage());
            }
        } else {

            logger.warn("Nenhum template de e-mail encontrado para o status: {}. E-mail não enviado.", notification.statusLoan());
        }
    }
}