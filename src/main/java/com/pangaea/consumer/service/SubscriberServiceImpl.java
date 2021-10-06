package com.pangaea.consumer.service;

import com.pangaea.consumer.model.Subscriber;
import com.pangaea.consumer.repository.SubscriberRepository;
import com.pangaea.consumer.service.interfaces.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriberServiceImpl implements SubscriberService{

    private SubscriberRepository subscriberRepository;

    @Override
    public Subscriber saveSubscriber(Subscriber subscriber) {
        return subscriberRepository.save(subscriber);
    }

    @Override
    public List<Subscriber> findByUrl(String url) {
        return subscriberRepository.findAllByUrl(url);
    }

    @Autowired
    public void setSubscriberRepository(SubscriberRepository subscriberRepository) {
        this.subscriberRepository = subscriberRepository;
    }
}
