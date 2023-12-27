package com.omarkhaled.simple.webbased.email.program.interfaces;

import com.omarkhaled.simple.webbased.email.program.classes.Mail;

import java.util.Collection;
import java.util.List;

public interface Criteria {
    public Collection<Mail> filter(Collection<Mail> mails);
}
