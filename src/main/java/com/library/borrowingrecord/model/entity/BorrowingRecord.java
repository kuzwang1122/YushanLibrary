package com.library.borrowingrecord.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Entity
@Table(name="user")
@EntityListeners(AuditingEntityListener.class)
@Data
public class BorrowingRecord implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userID;
	private Integer inventoryID;
	
	// 自動創建時間
    @CreatedDate()
	private LocalDateTime borrowingTime;
	
    // 歸還時間
    @LastModifiedDate
    private LocalDateTime returnTime;

}
