package com.byakko.service.sales.service.authentication.domain.domaincore.entity;

import com.byakko.service.sales.common.DomainConstants;
import com.byakko.service.sales.common.domain.entity.BaseEntity;
import com.byakko.service.sales.common.domain.exception.ValidationException;
import com.byakko.service.sales.service.authentication.domain.domaincore.valueobject.ConditionId;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Map;

public class Condition extends BaseEntity<ConditionId> {
    private Object content;
    private String typeQuery;
    private boolean executed;
    public void initialize(){
        setId(new ConditionId(Integer.parseInt(String.valueOf(ZonedDateTime.now(ZoneId.of(DomainConstants.ZONE_ID)).toEpochSecond()))));
        setExecuted(false);
    }
    public boolean isExecuted() {
        return executed;
    }
    public void setExecuted(boolean executed) {
        this.executed = executed;
    }

    public String getTypeQuery() {
        return typeQuery;
    }

    public void setTypeQuery(String typeQuery) {
        this.typeQuery = typeQuery;
    }

    public Object getContent() {
        return content;
    }
    public void setContent(Object content) {
        this.content = content;
    }
    public void validate(){
        validateContent();
    }
    private void validateContent(){
        if(this.content == null)
            throw new ValidationException(Map.of("content", "content must be not blank"));
    }
    public static final class ConditionBuilder{
        private Object content;
        private ConditionId conditionId;
        private boolean executed;
        private String typeQuery;
        private ConditionBuilder() {
        }
        public static ConditionBuilder builder(){
            return new ConditionBuilder();
        }
        public ConditionBuilder content(Object content){
            this.content = content;
            return this;
        }
        public ConditionBuilder executed(boolean executed){
            this.executed = executed;
            return this;
        }
        public ConditionBuilder typeQuery(String typeQuery){
            this.typeQuery = typeQuery;
            return this;
        }
        public Condition build(){
            Condition condition = new Condition();
            condition.setExecuted(executed);
            condition.setTypeQuery(typeQuery);
            condition.setContent(content);
            condition.setId(conditionId);
            return condition;
        }
    }
}
