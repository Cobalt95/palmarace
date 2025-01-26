package com.cobalt.palmarace.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "GPX_TRKPT")
@Getter @Setter
public class GpxTrackPoint {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer gpxTrkptId;
	private int position;
	@Column(precision = 8, scale = 6)
	private BigDecimal lat;
	@Column(precision = 8, scale = 6)
	private BigDecimal lon;
	@Column(precision = 8, scale = 6)
	private BigDecimal ele;
	
	@ManyToOne
	@JoinColumn(name = "gpx_trk_id")
	private GpxTrack gpxTrack;
}
