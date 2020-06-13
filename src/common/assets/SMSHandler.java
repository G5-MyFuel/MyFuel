package common.assets;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 *
 * @author Itay Ziv
 * This class is used to send SMS using Twilio Java SDK system.
 */
public class SMSHandler {
    // Find your Account Sid and Token at twilio.com/user/account
    public static final String ACCOUNT_SID = "AC95638a8a43812dc13a30ba282a0b7369";
    public static final String AUTH_TOKEN = "c29408f1623b245aec24126e935ff6b9";

    public static void main(String[] args) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(new PhoneNumber("+972527761348"),
                new PhoneNumber("+12055396641"),
                "This msg is from MyFuel Company.").create();
        System.out.println(message.getSid());
    }
}

