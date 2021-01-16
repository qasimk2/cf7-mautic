package com.khawaja.sboot.courseapidata.course;

import com.khawaja.sboot.courseapidata.topic.Topic;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseController {

  @Autowired
  private CourseService courseService;

  @GetMapping("/topics/{id}/courses")
  public List<Course> getTopicCourses(@PathVariable String id) {
    return this.courseService.getCoursesbyTopicId(id); // ;

  }

  @GetMapping("topics/{id}/courses/{courseid}")
  public Course getCourse(@PathVariable String id, @PathVariable String courseid) {
    return courseService.getCourse(courseid);
  }

  @PostMapping(value = "courses")
  public Course addCourse(@RequestBody Course course) {
    courseService.addCourse(course);
    return courseService.getCourse(course.getId());
  }

  @PutMapping(value = "courses/{id}")
  public void UpdateCourse(
    @PathVariable String id,
    @RequestBody Course course
  ) {
    courseService.updateCourse(id, course);
  }

  @DeleteMapping("/courses/{id}")
  public void deleteTopic(@PathVariable String id) {
    courseService.deleteCourse(id);
  }

}
