package com.congybk.service;

import com.congybk.entity.Event;
import com.congybk.entity.EventMember;
import com.congybk.repository.EvenMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author YNC on 05/04/2017.
 */
@Service
public class EventMemberServiceImpl implements EventMemberService {
    @Autowired
    EvenMemberRepository memberRepository;

    @Autowired
    public void setMemberRepository(EvenMemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public List<EventMember> getAll() {
        return (List<EventMember>) memberRepository.findAll();
    }

    @Override
    public void delete(int id) {
        memberRepository.delete(id);
    }

    @Override
    public EventMember findById(int id) {
        return memberRepository.findOne(id);
    }

    @Override
    public EventMember update(EventMember eventMember) {
        return memberRepository.save(eventMember);
    }

    @Override
    public EventMember create(EventMember eventMember) {
        return memberRepository.save(eventMember);
    }

    @Override
    public List<EventMember> getListMemberByEvent(Event event) {
        return memberRepository.getListMemberByEvent(event);
    }

    @Override
    public List<EventMember> getListMemberPost(int start, int end) {
        return null;
    }

    @Override
    public List<EventMember> findByEventAndUser(int event, int user) {
        return memberRepository.findByEventAndUser(event,user);
    }


}
