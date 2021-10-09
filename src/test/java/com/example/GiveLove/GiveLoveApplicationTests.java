package com.example.GiveLove;

import com.example.GiveLove.entity.Campaign;
import com.example.GiveLove.entity.Users;
import com.example.GiveLove.repository.CampaignRepository;
import com.example.GiveLove.repository.TaskRepository;
import com.example.GiveLove.repository.UsersRepository;
import com.example.GiveLove.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.when;

@SpringBootTest
class GiveLoveApplicationTests {

	@Autowired
	TaskRepository repository;
	@Autowired
	CampaignRepository campaignRepository;

	@Autowired
	UsersRepository usersRepository;



	@Test
	void contextLoads() {
	}

@Test
	void getTask(){
		List<Users> users = usersRepository.search("hung" , 3L);
		assertEquals(users.size() , 2);
}


}
