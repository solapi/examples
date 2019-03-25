package model.request;

import com.google.gson.JsonObject;

public class Message {
    private JsonObject message = new JsonObject();
    // SMS일 경우는 발신,수신 번호와 내용만 넣으시면 됩니다.
    public Message(String to, String from, String text) {
        this.message.addProperty("to", to);
        this.message.addProperty("from", from);
        this.message.addProperty("text", text);
    }

    // LMS일 경우 subject에 제목을 넣어주세요
    public Message(String to, String from, String text, String subject) {
        this.message.addProperty("to", to);
        this.message.addProperty("from", from);
        this.message.addProperty("text", text);
        this.message.addProperty("subject", subject);
        this.message.addProperty("type", "LMS");
    }

    // MMS일 경우 imageId에 이미지 ID를 넣어주세요
    public Message(String to, String from, String text, String subject, String imageId) {
        this.message.addProperty("to", to);
        this.message.addProperty("from", from);
        this.message.addProperty("text", text);
        this.message.addProperty("subject", subject);
        this.message.addProperty("imageId", imageId);
        this.message.addProperty("type", "MMS");
    }
}
