package com.omarkhaled.simple.webbased.email.program.classes;

import com.omarkhaled.simple.webbased.email.program.interfaces.SortingStrategy;

import java.util.Collection;
import java.util.List;

public class AttachmentSort implements SortingStrategy {

    @Override
    public Collection<Mail> sorting(List<Mail> mails) {
        mails.sort((o1,o2) -> {
            int sizeO1 = o1.getAttachments().size();
            int sizeO2 = o2.getAttachments().size();
            return Integer.compare(sizeO2, sizeO1);
        });
        return mails;
    }
}
