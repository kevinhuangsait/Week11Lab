package services;

import dataaccess.UserDB;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.User;

public class AccountService {
    
    public User login(String email, String password, String path) {
        UserDB userDB = new UserDB();
        
        try {
            User user = userDB.get(email);
            if (password.equals(user.getPassword())) {
                Logger.getLogger(AccountService.class.getName()).log(Level.INFO, "Successful login by {0}", email);
                
                String to = user.getEmail();
                String subject = "Notes App Login";
                String template = path + "/emailtemplates/login.html";
                
                HashMap<String, String> tags = new HashMap<>();
                tags.put("firstname", user.getFirstName());
                tags.put("lastname", user.getLastName());
                tags.put("date", (new java.util.Date()).toString());
                
                GmailService.sendMail(to, subject, template, tags);
                return user;
            }
        } catch (Exception e) {
        }
        
        return null;
    }
    
    public void resetPassword(String email, String path, String url) throws Exception {
        UserDB ub = new UserDB();
        
        User user = ub.get(email);
        String id = UUID.randomUUID().toString();
        user.setResetPasswordUuid(id);
        ub.update(user);
        
        String subject = "Password Reset";
        String template = path + "/emailtemplates/resetpassword.html";
        String link = url + "?uuid=" + id;
        
        HashMap<String, String> tags = new HashMap<>();
        tags.put("firstname", user.getFirstName());
        tags.put("lastname", user.getLastName());
        tags.put("link", link);
        
        GmailService.sendMail(email, subject, template, tags);
    }
    
    public boolean changePassword(String id, String pass) {
        UserDB ub = new UserDB();
        
        User user = ub.getByUUID(id);
        user.setPassword(pass);
        user.setResetPasswordUuid(null);
        ub.update(user);
        return true;
        
    }
}
