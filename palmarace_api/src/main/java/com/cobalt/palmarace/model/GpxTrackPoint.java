package com.cobalt.palmarace.model;

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
	private int gpxTrkptId;
	private int position;
	private float lat;
	private float lon;
	private float ele;
	
	@ManyToOne
	@JoinColumn(name = "gpx_trk_id")
	private GpxTrack gpxTrack;
}
