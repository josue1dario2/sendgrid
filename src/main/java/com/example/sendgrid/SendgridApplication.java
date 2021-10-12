package com.example.sendgrid;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class SendgridApplication {

	public static void main(String[] args) {
		SpringApplication.run(SendgridApplication.class, args);

		String apiKey = "${API_KEY}";
		String envio = "${MAIL}";
		String to = "thomas1amador2@gmail.com";
		Email fromEmail = new Email(envio);
		Email toEmail = new Email(to);
		Content content = new Content(
				"text/plain",
				"Bienvenido@ a Alkemy "
		);
		String subject = "Alkemy Icons";

		Mail mail = new Mail(fromEmail, subject, toEmail, content);
		SendGrid sg = new SendGrid(apiKey);
		Request request = new Request();
		try {
			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(mail.build());
			Response response = sg.api(request);

			System.out.println(response.getStatusCode());
			System.out.println(response.getBody());
			System.out.println(response.getHeaders());
		} catch (IOException ex) {
			System.out.println("Error trying to send the email");
		}


	}

}
