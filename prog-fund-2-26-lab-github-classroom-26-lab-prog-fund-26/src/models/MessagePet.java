package models;

import utils.Utilities;

public class MessagePet extends LikedPet {

    private String message = "";

    public MessagePet(String author, String message) {
        super(author);
        this.message = Utilities.truncateString(message, 40);
    }

    @Override
    public String displayCondensed() {
        return super.displayCondensed() + ": Message(" + message +  ")";
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        if (Utilities.validateStringLength(message, 40)) {
            this.message = message;
        }
    }

    public String display() {
        String str = super.display();

        if (!message.isEmpty()){
            str += "\t" + message + "\n";
        }
        return str;
    }
}
