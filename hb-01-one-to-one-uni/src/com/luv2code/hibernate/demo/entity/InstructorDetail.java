package com.luv2code.hibernate.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="instructor_detail")
public class InstructorDetail {
	
	// annotate the class as an entity and map to db table
	
	// define the fields
	// annotate thefields with db column names
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="youtube_channel")
	private String youTubeChannel;
	@Column(name="hobby")
	private String hobby;
	
	
	// create the constructor
	public InstructorDetail() {
		
	}
	public InstructorDetail(String youTubeChannel, String hobby) {
		this.youTubeChannel = youTubeChannel;
		this.hobby = hobby;
	}
	
	// generate getters and setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getYouTubeChannel() {
		return youTubeChannel;
	}
	public void setYouTubeChannel(String youTubeChannel) {
		this.youTubeChannel = youTubeChannel;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	
	// generate toString() method
	@Override
	public String toString() {
		return "InstructorDetail [youTubeChannel=" + youTubeChannel + ", hobby=" + hobby + "]";
	}
	

}
