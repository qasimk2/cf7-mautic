package com.khawaja.sboot.courseapidata.topic;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Topic implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = -6615451992136413409L;

  @Id
  private String id;

  private String name;
  private String description;
}
