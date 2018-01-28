package com.poshist.maBit.entity;

import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

/**
 * Created by poshist on 18-1-28.
 */
@Entity
@Table(name = "MB_SITE")
@EntityListeners(AuditingEntityListener.class)
public class MbSite  extends AbstractPersistable<Long> implements Cloneable {
    private Long id;
    private String name;
    private String lastTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }
}
