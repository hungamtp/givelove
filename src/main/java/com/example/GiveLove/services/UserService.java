package com.example.GiveLove.services;

import com.example.GiveLove.dto.CampaignDTO;
import com.example.GiveLove.dto.PageDTO;
import com.example.GiveLove.dto.UpdateUserDTO;
import com.example.GiveLove.dto.UserDTO;
import com.example.GiveLove.entity.Campaign;
import com.example.GiveLove.entity.Users;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.zip.DataFormatException;

public interface UserService {

    Users save(Users users);

    Users findByUsername(String username);

    PageDTO getAllUser(Specification specification , Pageable pageable);

    void updateRole(Long userId);

    List<CampaignDTO> getCampaignByUser (Long userId);
    void updateUserProfile(Long userid , UpdateUserDTO userDTO) throws DataFormatException;
    List<UserDTO> getAllUserInCampaign (Long campaignId);
    List<CampaignDTO> getCampaignByManager (Long userId);
    UserDTO getUserById(Long userId);
    List<CampaignDTO> getCampaignByDonator (Long userId);
    List<UserDTO> getAllDonatorInCampaign (Long campaignId);
}
