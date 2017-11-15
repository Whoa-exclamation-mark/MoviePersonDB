package com.zenoton.MoviePersonDB;

import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.zenoton.MoviePersonDB.db.data.DefaultData;
import org.springframework.security.core.context.SecurityContextHolder;

@SpringUI
public class MainUI extends UI {
    @Override
    protected void init(VaadinRequest request) {

        setContent(new Label(SecurityContextHolder.getContext().getAuthentication().getName()));
    }
}
