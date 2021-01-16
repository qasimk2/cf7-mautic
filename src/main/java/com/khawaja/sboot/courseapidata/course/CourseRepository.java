package com.khawaja.sboot.courseapidata.course;

import java.util.List;

import com.khawaja.sboot.courseapidata.topic.Topic;

import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course, String> {
  public List<Course> findByTopic(Topic topic);
  public List<Course> findByName(String name);
  public List<Course> findByDescription(String description);
  public List<Course> findByTopicId(String id);
}
