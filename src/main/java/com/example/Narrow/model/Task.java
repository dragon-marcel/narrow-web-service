package com.example.Narrow.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "TASKS")
public class Task implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    @Column(name = "CREATED_DATE")
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date createdDate;
    @Column(name = "USER_ID")
    private Long idUser;
    @Column(name = "STATUS_ID")
    private int idStatus;
    private boolean email;
    
    public Task() {
	this.email = false;
	this.idStatus = 1;
    }
}