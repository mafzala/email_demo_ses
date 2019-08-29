package com.demo;


import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
/**
 * @author afzal2
 * @since 2019-03-20
 */
public class AmazonSES {
    // This address must be verified with Amazon SES.
    final String FROM = "example@xyz.com";

    final String TO = "example@xyz.xom";

    // The subject line for the email.
    final String SUBJECT = "Demo - Amazon AWS -SES ";

    // The HTML body for the email.
    final String HTMLBODY = "<h1>Just Demo</h1>";
    // The email body for recipients with non-HTML email clients.
    final String TEXTBODY = "Just Demo";

    public void verifyEmail()
    {
        try {
            AmazonSimpleEmailService client =
                    AmazonSimpleEmailServiceClientBuilder.standard()
                            .withRegion(Regions.EU_WEST_1).build();

            SendEmailRequest request = new SendEmailRequest()
                    .withDestination(
                            new Destination().withToAddresses( TO ) )

                    .withMessage(new Message()
                            .withBody(new Body()
                                    .withHtml(new Content()
                                            .withCharset("UTF-8").withData(HTMLBODY))
                                    .withText(new Content()
                                            .withCharset("UTF-8").withData(TEXTBODY)))
                            .withSubject(new Content()
                                    .withCharset("UTF-8").withData(SUBJECT)))
                    .withSource(FROM);

            client.sendEmail(request);

            System.out.println("Email sent!");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public static void main(String[] args)
    {
        new AmazonSES().verifyEmail();
    }

}
