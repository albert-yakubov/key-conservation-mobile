package com.stepasha.keyconservation.repos;

import com.stepasha.keyconservation.models.Campaigns;
import com.stepasha.keyconservation.models.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CampaignsRepository extends PagingAndSortingRepository<Campaigns, Long> {

   Campaigns findCampaignByid(long id);

   List<Campaigns> findByTitleContainingIgnoreCase(String title);
   List<Campaigns> findByEventnameContainingIgnoreCase(String eventname);
}
