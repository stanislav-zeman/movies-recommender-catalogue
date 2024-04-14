package dev.cere.reviews.messaging;

public class ReviewMessage {
    private String jsonDataString;
    private MessageActionType actionType;

    public ReviewMessage(String jsonDataString, MessageActionType actionType) {
        this.jsonDataString = jsonDataString;
        this.actionType = actionType;
    }

    public String getJsonDataString() {
        return jsonDataString;
    }

    public void setJsonDataString(String jsonDataString) {
        this.jsonDataString = jsonDataString;
    }

    public MessageActionType getActionType() {
        return actionType;
    }

    public void setActionType(MessageActionType actionType) {
        this.actionType = actionType;
    }

    @Override
    public String toString() {
        return "Message{"
                + "jsonDataString='"
                + jsonDataString
                + '\''
                + ", actionType="
                + actionType
                + '}';
    }
}
