package com.omarkhaled.simple.webbased.email.program.interfaces;

import com.omarkhaled.simple.webbased.email.program.classes.Mail;

import java.util.Collection;
import java.util.List;

public interface SearchingStrategy {
    Collection<Mail> searching(String keyWord, Collection<Mail> mails);
}
