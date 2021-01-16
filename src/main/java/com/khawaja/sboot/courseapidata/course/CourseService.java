package com.khawaja.sboot.courseapidata.course;

import com.khawaja.sboot.courseapidata.exception.ApplicationException;
import com.khawaja.sboot.courseapidata.topic.Topic;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Spliterator;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

  private CourseRepository courserepository;

  @Autowired
  public CourseService(CourseRepository courserepository) {
    this.courserepository = courserepository;

    Course ac = new Course("spring-boot", "Spring Boot Fundamentals", "Spring Boot Fundamentals Description",
        new Topic("Spring", "Spring Framework", "Spring Framework Description"));
    // this.courserepository.addCourse(ac);
  }

  /**
   * @param topicid
   * @return List<Course>
   */
  public List<Course> getAllCourses(String topicid) {
    List<Course> courses = new ArrayList<>();

    StreamSupport.stream((Spliterator<Course>) courserepository.findAll(), false)
        .filter(course -> course.getTopic().getId().equals(topicid)).forEach(courses::add);
    return courses;
  }

  /**
   * @param getID
   * @return Course
   */
  public Course getCourse(String getID) {
    Course returnCourse = courserepository.findById(getID).orElse(new Course());
    return returnCourse;
  }

  /**
   * @param newCourse
   * @return Add a new course to a topic If an exception is caught then a new
   *         empty Course Object is returned JpaObjectRetrievalFailureException -
   *         catches an empty foreign key relationship with topic table @ TODO
   *         Should we use this function to add a new topic when one doesn't exist
   */
  public Course addCourse(Course newCourse) {
    try {
      return (Optional.ofNullable(courserepository.save(newCourse))).orElseThrow(NoSuchElementException::new);
    } catch (JpaObjectRetrievalFailureException e) {
      throw new ApplicationException("Error saving - topic not found " + newCourse.toString(), e);
    } catch (NoSuchElementException e) {
      throw new ApplicationException("Error saving course " + newCourse.toString(), e);
    } catch (Exception e) {
      throw new ApplicationException("Unknown error", e);
    }
  }

  /**
   *
   * @param id
   * @param newtopic
   */
  public void updateCourse(String id, Course newtopic) {
    try {
      courserepository.save(newtopic);
    } catch (JpaObjectRetrievalFailureException e) {
      throw new ApplicationException("Referential Inregrity Error", e);
    } catch (Exception e) {
      throw new ApplicationException("Unknown Error", e);
    }
  }

  public void deleteCourse(String topicid) {
    try {
      courserepository.delete(courserepository.findById(topicid).orElse(new Course()));
    } catch (IllegalArgumentException e) {
      throw new ApplicationException("Illegal ID", e);
    } catch (Exception e) {
      throw new ApplicationException("Unexpected Error", e);
    }
  }

  public List<Course> getCourseListbyTopic(String topicid) {
    try {
      return this.courserepository.findByTopic((new Topic(topicid, "", "")));
    } catch (IllegalArgumentException e) {
      throw new ApplicationException("Illegal Argument", e);
    }
  }

  public List<Course> getCoursesbyTopicId(String topicid) {
    try {
      return this.courserepository.findByTopicId(topicid);
    } catch (IllegalArgumentException e) {
      throw new ApplicationException("Illegal Argument", e);
    }
  }
/**
 * 
 * @param name
 * @return
 */
  public List<Course> getCoursesByName(String name) {
    List<Course> result = new ArrayList<>();
    try {
      result = this.courserepository.findByName(name);
      if (result.isEmpty()) {
        throw new ApplicationException("Empty Result - name");
      } else
        return result;
    } catch (IllegalArgumentException e) {
      throw new ApplicationException("Illegal Argument", e);
    } catch (Exception e) {
      throw new ApplicationException("Unexpected Error", e);
    }
  }
/**
 * 
 * @param description
 * @return
 */
  public List<Course> getCourseByDescription(String description) {
    List<Course> result = new ArrayList<>();
    try {
      result = courserepository.findByDescription(description);
      if (result.isEmpty()) {
        throw new ApplicationException("Empty Result");
      } else
        return result;
    } catch (IllegalArgumentException e) {
      throw new ApplicationException("Ilegal Argument", e);
    } catch (Exception e) {
      throw new ApplicationException("Unexpected Error", e);
    }

  }

}
