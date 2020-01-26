package com.stepasha.keyconservation.services;

import com.stepasha.keyconservation.models.Useremail;

import java.util.List;

public interface UseremailService
{
    List<Useremail> findAll();

    Useremail findUseremailById(long id);

    List<Useremail> findByUserName(String username);

    void delete(long id);

    Useremail update(long useremailid,
                     String emailaddress);

    // note emails are added through the user process
}
