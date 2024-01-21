package com.library.user.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Entity
@Table(name="user")
@EntityListeners(AuditingEntityListener.class)
@Data
public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userID; 
	private String phoneNumber;
	private String password;
	private String userName;

	// 自動創建時間
    @CreatedDate()
	private LocalDateTime registrationTime;
    
    // 最後登入時間
    private LocalDateTime lastLoginTime;
    
    @PreUpdate
    public void setLastLoginTime() {
        this.lastLoginTime = LocalDateTime.now();
    }
}
