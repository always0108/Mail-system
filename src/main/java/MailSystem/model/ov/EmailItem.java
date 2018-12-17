package MailSystem.model.ov;

import MailSystem.model.pv.Files;

import java.util.List;

public class EmailItem {
    private Integer id;

    private String sender;

    private String receiver;

    private String email_address;

    private String rece_email;

    private String subject;

    private String content;

    private Boolean is_read;

    private Boolean star;

    private String time;

    private List<Files> enclosures;

    public EmailItem() {
    }

    public String getRece_email() {
        return rece_email;
    }

    public void setRece_email(String rece_email) {
        this.rece_email = rece_email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getEmail_address() {
        return email_address;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getIs_read() {
        return is_read;
    }

    public void setIs_read(Boolean is_read) {
        this.is_read = is_read;
    }

    public Boolean getStar() {
        return star;
    }

    public void setStar(Boolean star) {
        this.star = star;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<Files> getEnclosures() {
        return enclosures;
    }

    public void setEnclosures(List<Files> enclosures) {
        this.enclosures = enclosures;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
}
