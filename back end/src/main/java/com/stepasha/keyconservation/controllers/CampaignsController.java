package com.stepasha.keyconservation.controllers;

import com.stepasha.keyconservation.models.Campaigns;
import com.stepasha.keyconservation.models.ErrorDetail;
import com.stepasha.keyconservation.services.CampaignsService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/campaigns")
public class CampaignsController {

    @Autowired
    private CampaignsService campaignsService;

    private static final Logger logger = LoggerFactory.getLogger(CampaignsController.class);
    @ApiOperation(value = "returns all Campaigns",
            response = Campaigns.class,
            responseContainer = "List")
    // http://localhost:0122/campaigns/campaigns
    @GetMapping(value = "/campaigns", produces = "application/json")
    ResponseEntity<?> getCampaigns(){
        logger.info("campaigns/campaigns Accessed");
        List<Campaigns> campaigns = campaignsService.findAll();
        return new ResponseEntity<>(campaigns, HttpStatus.OK);
    }

    //CREATE
    //http://localhost:0122/campaigns/campaign
    @PostMapping(value = "/campaign",
            consumes = {"application/json"})
    public ResponseEntity<?> addNewCampaign(
            @Valid
            @RequestBody Campaigns newCampaign){
        newCampaign = campaignsService.save(newCampaign);
        HttpHeaders responseHeader = new HttpHeaders();
        URI newCustomerUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{eventid}")
                .buildAndExpand(newCampaign.getEventid())
                .toUri();
        responseHeader.setLocation(newCustomerUri);

        return new ResponseEntity<>(null, responseHeader, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Retrieve a user based of off user id",
            response = Campaigns.class)
    @ApiResponses(value = {@ApiResponse(code = 200,
            message = "Map Found",
            response = Campaigns.class), @ApiResponse(code = 404,
            message = "Map Not Found",
            response = ErrorDetail.class)})
    // http://localhost:0122/campaigns/campaign/1
    @PutMapping(value = "/campaign/{eventid}",
            consumes = {"application/json"})
    public ResponseEntity<?> updateCampaign(@RequestBody Campaigns updateCampaign,
                                       @ApiParam(value = "Event id",
                                               required = true,
                                               example = "4")
                                       @PathVariable long eventid){
        campaignsService.update(updateCampaign, eventid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    // http://localhost:0122/campaigns/campaign/1
    @DeleteMapping(value = "/campaign/{eventid}")
    public ResponseEntity<?> deleteCampaign(@PathVariable long eventid){
        campaignsService.delete(eventid);
        return new ResponseEntity<>(HttpStatus.OK);
    }




}

