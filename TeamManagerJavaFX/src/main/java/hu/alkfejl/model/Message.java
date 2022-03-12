package hu.alkfejl.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Message {

    private IntegerProperty id = new SimpleIntegerProperty();
    private Player senderPlayer;
    private Player receiverPlayer;
    private StringProperty title = new SimpleStringProperty();
    private StringProperty messageContent = new SimpleStringProperty();

    public Message() {
    }

    public Message(int id, Player senderPlayer, Player receiverPlayer, String title, String messageContent) {
        this.id.set(id);
        this.senderPlayer = senderPlayer;
        this.receiverPlayer = receiverPlayer;
        this.title.set(title);
        this.messageContent.set(messageContent);
    }

    public Message(Player senderPlayer, Player receiverPlayer, String title, String messageContent) {
        this.senderPlayer = senderPlayer;
        this.receiverPlayer = receiverPlayer;
        this.title.set(title);
        this.messageContent.set(messageContent);
    }

    /**
     * Üzenet adatainak átmásolása egy másik üzenet objektumba.
     */
    public void copyTo(Message target) {
        target.setId(this.getId());
        target.setSenderPlayer(this.getSenderPlayer());
        target.setReceiverPlayer(this.getReceiverPlayer());
        target.setTitle(this.getTitle());
        target.setMessageContent(this.getMessageContent());
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public Player getSenderPlayer() {
        return senderPlayer;
    }

    public void setSenderPlayer(Player senderPlayer) {
        this.senderPlayer = senderPlayer;
    }

    public Player getReceiverPlayer() {
        return receiverPlayer;
    }

    public void setReceiverPlayer(Player receiverPlayer) {
        this.receiverPlayer = receiverPlayer;
    }

    public String getMessageContent() {
        return messageContent.get();
    }

    public StringProperty messageContentProperty() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent.set(messageContent);
    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }
}
