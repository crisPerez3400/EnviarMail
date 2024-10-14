package org.calc2.enviarmail;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class EmailSender {
    private static boolean connection;

    public static void emailSender(String hostName, String smtpPort, String from, String subject, String msg, String sendTo , String pass) throws EmailException {
        // Crea una instancia de SimpleEmail
        Email email = new SimpleEmail();

        try {
            // Configuración del servidor SMTP
            email.setHostName(hostName); // Cambia por tu servidor SMTP
            email.setSmtpPort(Integer.parseInt(smtpPort)); // Puerto SMTP (generalmente 465 o 587)
            email.setAuthentication(from, pass);
            email.setSSLOnConnect(connection); // Si usas SSL

            // Configuración del correo
            email.setFrom("dad.iesdpm@gmail.com"); // Remitente
            email.setSubject(subject);
            email.setMsg(msg);
            email.addTo(sendTo); // Destinatario

            // Envía el correo
            email.send();

            System.out.println("Correo enviado con éxito!");

        } catch (EmailException e) {
            //e.printStackTrace(); //imprime consola

            throw new EmailException("Error" + e.getMessage(), e);

        }
    }

    public static void setConnection(boolean connection) {
        EmailSender.connection = connection;
    }
}
