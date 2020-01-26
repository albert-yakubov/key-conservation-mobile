package com.stepasha.keyconservation.repos;

import com.stepasha.keyconservation.models.Campaigns;
import org.springframework.data.repository.CrudRepository;

public interface CampaignsRepoasitory extends CrudRepository<Campaigns, Long> {

   Campaigns findCampaignByid(long eventid);
}
