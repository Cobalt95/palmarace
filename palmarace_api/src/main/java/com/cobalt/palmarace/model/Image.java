package com.cobalt.palmarace.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Image {

	@Id
	// No @GeneratedValues as imageIds will be provided by MinIO, or S3
	// (Object storage strategy will be decided later)
	private Integer imageId;
	
	@ManyToOne
	@JoinColumn(name = "image_type_code")
	private ImageType imageType;
	
}
