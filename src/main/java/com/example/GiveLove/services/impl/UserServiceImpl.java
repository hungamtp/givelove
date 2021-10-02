package com.example.GiveLove.services.impl;

import com.example.GiveLove.converter.CampaignConverter;
import com.example.GiveLove.converter.UserConverter;
import com.example.GiveLove.dto.CampaignDTO;
import com.example.GiveLove.dto.PageDTO;
import com.example.GiveLove.dto.UpdateUserDTO;
import com.example.GiveLove.dto.UserDTO;
import com.example.GiveLove.entity.Campaign;
import com.example.GiveLove.entity.Role;
import com.example.GiveLove.entity.Users;
import com.example.GiveLove.repository.CampaignRepository;
import com.example.GiveLove.repository.UsersRepository;
import com.example.GiveLove.repository.specification.SearchCriteria;
import com.example.GiveLove.repository.specification.UserSpecification;
import com.example.GiveLove.responseCode.ErrorCode;
import com.example.GiveLove.services.UserService;
import lombok.AllArgsConstructor;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.zip.DataFormatException;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UsersRepository usersRepository;
    private UserConverter userConverter;
    private CampaignConverter campaignConverter;
    private CampaignRepository campaignRepository;




    @Override
    public Users save(Users users) {

        Optional<Users> user = Optional.ofNullable(usersRepository.findByUsername(users.getUsername()));

        if(user.isPresent()){
            throw new DuplicateKeyException(ErrorCode.USERNAME_NOT_AVAILABLE);
        }
        Users savedUser = usersRepository.save(users);
        savedUser.setPassword("");
        return savedUser;
    }

    @Override
    public Users findByUsername(String username) {

       Optional<Users>user =Optional.ofNullable( usersRepository.findByUsername(username));
        if(!user.isPresent()){
            throw new DuplicateKeyException(ErrorCode.USERNAME_NOT_AVAILABLE);
        }
        return  user.get();
    }

    @Override
    public PageDTO getAllUser(Specification specification, Pageable pageable) {
try{

}catch (Exception ex){
    throw new DataAccessResourceFailureException(ErrorCode.GET_USER_FAIL);
}
        return userConverter.convertPageToDTO(usersRepository.findAll(specification , pageable));
    }

    @Override
    public void updateRole(Long userId) {

        UserSpecification specification = new UserSpecification(new SearchCriteria("id" , ":" , userId));

        Optional<Users> usersOptional = usersRepository.findOne(specification);

        if(usersOptional.isPresent()){
            Users updatedUser = usersOptional.get();
            updatedUser.setRole(Role.builder().id(3L).build());
            usersRepository.save(updatedUser);
        }
        else{
            throw new IllegalIdentifierException(ErrorCode.USER_NOT_FOUND);
        }
    }

    public List<CampaignDTO> getCampaignByUser (Long userId){


        List<CampaignDTO> campaignDTOS = usersRepository.findById(userId).get()
                .getCampaign()
                .stream()
                .map(campaign -> campaignConverter.convertEntityToDTO(campaign))
                .collect(Collectors.toList());

        return campaignDTOS;

    }

    public List<CampaignDTO> getCampaignByManager (Long userId){


        List<CampaignDTO> campaignDTOS = usersRepository.findById(userId).get()
                .getCampaignList()
                .stream()
                .map(campaign -> campaignConverter.convertEntityToDTO(campaign))
                .collect(Collectors.toList());

        return campaignDTOS;

    }

    public void updateUserProfile(Long userid , UpdateUserDTO userDTO) throws DataFormatException {
        usersRepository.findById(userid).orElseThrow(() ->{
           throw new IllegalStateException(ErrorCode.USER_NOT_FOUND);
        });

        if(userDTO.getDob().isAfter(LocalDate.now())){
            throw new DataFormatException(ErrorCode.DOB_AFTER_NOW);
        }

        Users users = Users.builder()
                .id(userid)
                .fullName(userDTO.getFullName())
                .dateOfBirth(userDTO.getDob())
                .gender(userDTO.getGender())
                .phone(userDTO.getPhone())
                .email(userDTO.getEmail())
                .address(userDTO.getAddress())
                .build();

        usersRepository.updateProfile(userDTO.getAddress() , userDTO.getDob() , userDTO.getEmail()
                , userDTO.getFullName() , userDTO.getPhone(),  userid);
    }

    public List<UserDTO> getAllUserInCampaign (Long campaignId){

       Campaign campaign= campaignRepository.findById(campaignId).orElseThrow(()->{
                throw new IllegalStateException(ErrorCode.CAMPAIGN_NOT_FOUND);
        });

        return campaign.getMembers()
                .stream().map(users -> userConverter.convertEntityToDTO(users))
                .collect(Collectors.toList());
    }

    public UserDTO getUserById(Long userId){
        return userConverter.convertEntityToDTO(usersRepository.findById(userId).get());
    }


}
