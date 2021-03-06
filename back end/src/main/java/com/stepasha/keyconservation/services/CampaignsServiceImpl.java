package com.stepasha.keyconservation.services;

import com.stepasha.keyconservation.models.Campaigns;
import com.stepasha.keyconservation.repos.CampaignsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "campaignsService")
public class CampaignsServiceImpl implements CampaignsService {

    @Autowired
    private CampaignsService campaignsService;
    @Autowired
    private CampaignsRepository campaignsRepository;

    @Override
    public Campaigns getCampaignsById(long id) {
        return campaignsRepository.findCampaignByid(id);
    }

    @Override
    public List<Campaigns> findAll() {
        List<Campaigns> list = new ArrayList<>();
        campaignsRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Transactional
    @Override
    public Campaigns save(Campaigns campaigns) {

        Campaigns newCampaigns = new Campaigns();
        newCampaigns.setTitle(campaigns.getTitle());
        newCampaigns.setBanner_image(campaigns.getBanner_image());
        newCampaigns.setLocation(campaigns.getLocation());
        newCampaigns.setLatitude(campaigns.getLatitude());
        newCampaigns.setLongitude(campaigns.getLongitude());
        newCampaigns.setCreated_at(campaigns.getCreated_at());
        newCampaigns.setEvent_image(campaigns.getEvent_image());
        newCampaigns.setEvent_name(campaigns.getEvent_name());
        newCampaigns.setEvent_description(campaigns.getEvent_description());
        newCampaigns.setUser(campaigns.getUser());


        return campaignsRepository.save(newCampaigns);
    }
    @Transactional
    @Override
    public Campaigns update(Campaigns campaigns, long id) {
        Campaigns currentCampaign = getCampaignsById(id);
        if (currentCampaign.getTitle() != null) {
            currentCampaign.setTitle(campaigns.getTitle());
        }
        if (currentCampaign.getBanner_image() != null) {
            currentCampaign.setBanner_image(campaigns.getBanner_image());
        }
        if (currentCampaign.getLocation()!= null) {
            currentCampaign.setLocation(campaigns.getLocation());
        }
        if (currentCampaign.getLatitude()!= null) {
            currentCampaign.setLatitude(campaigns.getLatitude());
        }
        if (currentCampaign.getLongitude()!= null) {
            currentCampaign.setLongitude(campaigns.getLongitude());
        }
        if (currentCampaign.getCreated_at()!= null) {
            currentCampaign.setCreated_at(campaigns.getCreated_at());
        }
        if (currentCampaign.getEvent_image() != null) {
            currentCampaign.setEvent_image(campaigns.getEvent_image());
        }
        if (currentCampaign.getEvent_name()!= null) {
            currentCampaign.setEvent_name(campaigns.getEvent_name());
        }
        if (currentCampaign.getEvent_description()!= null) {
            currentCampaign.setEvent_description(campaigns.getEvent_description());
        }
        if(currentCampaign.getUser() != null){
            currentCampaign.setUser(campaigns.getUser());
        }


        return campaignsRepository.save(currentCampaign);
    }

    @Override
    public void delete(long id) {
        if (getCampaignsById(id) != null){
            campaignsRepository.deleteById(id);
        }

    }

    @Override
    public List<Campaigns> findByTitleContaining(String title) {
        return campaignsRepository.findByTitleContainingIgnoreCase(title);
    }

    @Override
    public List<Campaigns> findByEventnameContaining(String eventname) {
        return campaignsRepository.findByEventnameContainingIgnoreCase(eventname);
    }


}
