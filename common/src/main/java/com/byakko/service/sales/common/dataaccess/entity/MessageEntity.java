package com.byakko.service.sales.common.dataaccess.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "messages")
public class MessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "msg_key", length = 60, unique = true, nullable = false)
    private String key;

    @Column(name = "msg_value", length = 255, nullable = false)
    private String value;

}
