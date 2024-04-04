package com.omarkhaled.simple.webbased.email.program.classes;

import com.omarkhaled.simple.webbased.email.program.interfaces.AttachmentBuilder;

public class Attachment {
    private String id;

    private String attachmentName;

    private String contentType;

    private byte[] data;

    public String getId() {
        return id;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public String getContentType() {
        return contentType;
    }

    public byte[] getData() {
        return data;
    }

    public static class Builder implements AttachmentBuilder{

        Attachment attachment = new Attachment();

        @Override
        public Attachment build() {
            return attachment;
        }

        @Override
        public AttachmentBuilder setId(String id) {
            attachment.id = id;
            return this;
        }

        @Override
        public AttachmentBuilder setAttachmentName(String attachmentName) {
            attachment.attachmentName = attachmentName;
            return this;
        }

        @Override
        public AttachmentBuilder setContentType(String contentType) {
            attachment.contentType = contentType;
            return this;
        }

        @Override
        public AttachmentBuilder setData(byte[] data) {
            attachment.data = data;
            return this;
        }
    }
}
