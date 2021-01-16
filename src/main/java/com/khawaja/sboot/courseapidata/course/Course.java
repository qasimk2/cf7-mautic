package com.khawaja.sboot.courseapidata.course;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.khawaja.sboot.courseapidata.topic.Topic;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Course implements Serializable{

  /**
   *
   */
  private static final long serialVersionUID = 4925062164148623095L;

  @Id
  private String id;

  private String name;
  private String description;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  private Topic topic;
}
