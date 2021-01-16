package com.khawaja.sboot.courseapidata.topic;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicService {

  private TopicRepository topicrepository;

  // private List<Topic> topics = new ArrayList<>();

  @Autowired
  public TopicService(TopicRepository topicrepository) {
    this.topicrepository = topicrepository;
    this.addTopic(
        new Topic("Spring", "Spring Framework", "Spring Framework Description")
      );
    addTopic(
      new Topic(
        "Javascript",
        "Javascript Framework",
        "Javascript Framework Description"
      )
    );
    addTopic(
      new Topic("Python", "Python Framework", "Python Framework Description")
    );
  }

  public List<Topic> getAllTopics() {
    List<Topic> topics = new ArrayList<>();
    topicrepository.findAll().forEach(topics::add);
    return topics;
  }

  public Topic getTopic(String getID) {
    Topic returnTopic = topicrepository.findById(getID).orElse(new Topic());
    return returnTopic;
  }

  public Topic addTopic(Topic newTopic) {
    return topicrepository.save(newTopic);
  }

  public void updateTopic(String id, Topic newtopic) {
    topicrepository.save(newtopic);
  }

  public void deleteTopic(String topicid) {
    topicrepository.delete(
      topicrepository.findById(topicid).orElse(new Topic())
    );
  }
}
