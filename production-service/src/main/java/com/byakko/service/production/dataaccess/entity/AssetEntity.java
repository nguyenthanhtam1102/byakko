package com.byakko.service.production.dataaccess.entity;

import com.byakko.common.DomainConstants;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "assets")
public class AssetEntity {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    @Column(length = 60, nullable = false)
    private String filename;

    @Column(length = 255)
    private String filetype;

    @Column(nullable = false)
    private long size;

    @Column(name = "is_graphic")
    private String isGraphic;

    private int width;

    private int height;

    @Column(nullable = false)
    private String url;

    @Column(nullable = false)
    private String slug;

    @Column(name = "alt_text")
    private String altText;

    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @PrePersist
    private void createTimestamp() {
        if(createdAt == null)
            createdAt = ZonedDateTime.now(ZoneId.of(DomainConstants.ZONE_ID));
    }

}
