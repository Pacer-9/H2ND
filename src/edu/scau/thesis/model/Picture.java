package edu.scau.thesis.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import edu.scau.thesis.model.base.BasePOJO;

@Entity
@Table(name="PICTURE")
public class Picture extends BasePOJO{
	private static final long serialVersionUID = 1L;
	@Id@Column(name="ID")
	@GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;
	@Column(name="SEQ_NUM")
	private int sequenceNumber;
	@Column(name="PIC_URL")
	private String pictureUrl;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getSequenceNumber() {
		return sequenceNumber;
	}
	public void setSequenceNumber(int sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	public String getPictureUrl() {
		return pictureUrl;
	}
	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}
	
}
