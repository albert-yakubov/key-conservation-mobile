package com.stepasha.keyconservation.services;

import com.stepasha.keyconservation.models.Campaigns;
import com.stepasha.keyconservation.models.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CampaignsService {

    Campaigns getCampaignsById(long id);

    List<Campaigns> findAll();

    Campaigns save(Campaigns campaigns);

    Campaigns update(Campaigns campaigns, long id);

    void delete(long id);

    List<Campaigns> findByTitleContaining(String title);
    List<Campaigns> findByEventnameContaining(String eventname);

}

