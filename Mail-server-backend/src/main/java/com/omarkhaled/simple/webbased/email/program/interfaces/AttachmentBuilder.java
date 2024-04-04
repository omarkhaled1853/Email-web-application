package com.omarkhaled.simple.webbased.email.program.interfaces;

import com.omarkhaled.simple.webbased.email.program.classes.Attachment;

public interface AttachmentBuilder {
    Attachment build();
    AttachmentBuilder setId(String id);
    AttachmentBuilder setAttachmentName(String attachmentName);
    AttachmentBuilder setContentType(String contentType);
    AttachmentBuilder setData(byte[] data);
}
