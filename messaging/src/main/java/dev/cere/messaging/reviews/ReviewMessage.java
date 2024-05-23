package dev.cere.messaging.reviews;

public class ReviewMessage {
    private String jsonDataString;
    private ReviewMessageActionType actionType;

    public ReviewMessage(String jsonDataString, ReviewMessageActionType actionType) {
        this.jsonDataString = jsonDataString;
        this.actionType = actionType;
    }

    public String getJsonDataString() {
        return jsonDataString;
    }

    public void setJsonDataString(String jsonDataString) {
        this.jsonDataString = jsonDataString;
    }

    public ReviewMessageActionType getActionType() {
        return actionType;
    }

    public void setActionType(ReviewMessageActionType actionType) {
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
