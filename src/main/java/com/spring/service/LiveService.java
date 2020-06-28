package com.spring.service;

import com.spring.domain.LiveDomain;
import com.spring.repository.LiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class LiveService {

    @Autowired
    LiveRepository liveRepository;

    public Page<LiveDomain> findAll(Pageable pageable, String flag){
        if(flag != null && flag.equals("next")){
            return liveRepository.findByLiveDateAfterOrderByLiveDateAsc(new Date(), pageable);
        }else if(flag != null && flag.equals("previous")){
            return liveRepository.findByLiveDateBeforeOrderByLiveDateDesc(new Date(), pageable);
        }else{
            return liveRepository.findAll(pageable);
        }
    }

    public Optional<LiveDomain> findById(Integer id){
        return liveRepository.findById(id);
    }

    public LiveDomain save(LiveDomain liveDocument){
        return liveRepository.save(liveDocument);
    }

    public void delete(LiveDomain liveDocument){
        liveRepository.delete(liveDocument);
    }
}
