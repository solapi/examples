package model.request;

import java.util.ArrayList;

public class MessageIds {
    public MessageIds(ArrayList<String> messageIds) {
        this.messageIds = messageIds;
    }
    private ArrayList<String> messageIds;

    public ArrayList<String> getMessageIds() {
        return messageIds;
    }
}
