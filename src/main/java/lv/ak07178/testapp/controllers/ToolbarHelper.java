package lv.ak07178.testapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import lv.ak07178.testapp.session.CurrentUser;

@Component
public class ToolbarHelper {

    @Autowired
    private CurrentUser currentUser;

    public void fillDataForToolbar(Model model) {
        if (currentUser.getId() != null) {
            model.addAttribute("currentUser", currentUser.getName());
            model.addAttribute("currentUserId", currentUser.getId());
        }
    }
}
