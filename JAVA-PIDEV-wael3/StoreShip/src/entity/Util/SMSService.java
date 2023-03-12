/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity.Util;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
/**
 *
 * @author Bejaoui
 */
public class SMSService {
   // Twilio account SID and auth token, obtained from your Twilio account dashboard
    private final String ACCOUNT_SID = "ACa141e95e70a02a529768fb9df90ebcea";
    private final String AUTH_TOKEN = "e2267938830f700a0fa17739915be438";
    private final String FROM_PHONE_NUMBER = "+15076288954";

    public void sendSMS(String recipientPhoneNumber, String messageContent) {
        System.setProperty("org.slf4j.simpleLogger.defaultLogLevel", "warn");
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
            new PhoneNumber(recipientPhoneNumber),
            new PhoneNumber(FROM_PHONE_NUMBER),
            messageContent
        ).create();
        System.out.println("Message sent: " + message.getSid());
    }
}
