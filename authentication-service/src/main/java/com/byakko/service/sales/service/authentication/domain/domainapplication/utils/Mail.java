package com.byakko.service.sales.service.authentication.domain.domainapplication.utils;

public class Mail {

    private String to;
    private String subject;
    private String body;


    public String getTo() {
        return to;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }

    public static final class Builder {
        private String to;
        private String subject;
        private String body;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder to(String to) {
            this.to = to;
            return this;
        }

        public Builder subject(String subject) {
            this.subject = subject;
            return this;
        }

        public Builder body(String body) {
            this.body = body;
            return this;
        }

        public Mail build() {
            Mail mail = new Mail();
            mail.body = this.body;
            mail.subject = this.subject;
            mail.to = this.to;
            return mail;
        }
    }
}
