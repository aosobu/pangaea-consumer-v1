package com.pangaea.consumer.repository;

import com.pangaea.consumer.model.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {
    List<Subscriber> findAllByUrl(String url);
    List<Subscriber> findAllByUrlAndTopicId(String url, Long topicId);
    List<Subscriber> findAllByTopicId(Long topicId);
}
