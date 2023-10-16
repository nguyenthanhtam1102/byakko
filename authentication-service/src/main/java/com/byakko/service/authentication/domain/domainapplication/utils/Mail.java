package com.byakko.service.authentication.domain.domainapplication.utils;

public class Mail {

    private String from;
    private String to;
    private String subject;
    private String body;

    public String getFrom() {
        return from;
    }

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
        private String from;
        private String to;
        private String subject;
        private String body;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder from(String from) {
            this.from = from;
            return this;
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
            mail.from = this.from;
            mail.body = this.body;
            mail.subject = this.subject;
            mail.to = this.to;
            return mail;
        }
    }
}
