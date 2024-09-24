package com.hit.base_service_api.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hit.base_service_api.domain.object.tracking.TrackingId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@AllArgsConstructor
public class Tracking {

  private TrackingId trackingId;

  public Tracking() {
    this.trackingId = new TrackingId();
  }

}
