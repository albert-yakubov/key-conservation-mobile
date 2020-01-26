package com.stepasha.keyconservation.services;

import com.stepasha.keyconservation.models.Campaigns;

import java.util.List;

public interface CampaignsService {

    Campaigns getCampaignsById(long eventid);

    List<Campaigns> findAll();

    Campaigns save(Campaigns campaigns);

    Campaigns update(Campaigns campaigns, long eventid);

    void delete(long eventid);


}

