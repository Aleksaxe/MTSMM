package EmailWorker;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public class emailSender {

    private static void messegePreparation(String email, String password, String addressee, String numberWithOverrun, double overrun) {

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.yandex.ru");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(email, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(addressee));
            message.setSubject("Перерасход за услуги связи МТС");

            message.setText("Уважаемые коллеги!\n" +
                    "Информирую Вас о допущенном Вами перерасходе лимита сотовой связи по номеру "
                    + numberWithOverrun + " на сумму " +
                    +overrun + " р.\n" +
                    "\n" +
                    "До 5 числа текущего месяца" +
                    ". Вам необходимо:\n" +
                    " \n" +
                    "Сформировать Служебную записку, объясняющую данный перерасход " +
                    "(бланк можно найти по следующему пути : Shared\\Общая\\Документы IT)" +
                    " и согласовать её с Финансовым директором, Тверских Р.В.\n" +
                    "Направить, согласованную Служебную записку Руководителю расчетной группы Елене Мамичевой.\n" +
                    "\n" +
                    "В противном случае, допущенный Вами перерасход будет удержан из Вашей заработной платы.");


            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
   public static void send(String propMail, HashMap over) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileReader(new File(propMail)));
        /*
         * foreach по карте over для отправки мейлов
         * */
        over.forEach((key, value) -> {
            String email = properties.getProperty((String) key) + "@multi-menu.com";
            emailSender.messegePreparation("support@multi-menu.net", "Vfhcbfybyyfkeyt18", email
                    , (String) key, Double.parseDouble(value.toString()));
        });
    }

}

