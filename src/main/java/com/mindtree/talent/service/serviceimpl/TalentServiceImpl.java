package com.mindtree.talent.service.serviceimpl;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mindtree.talent.service.TalentService;

@Service
public class TalentServiceImpl implements TalentService {

	@Override
	public void addFile(MultipartFile file) throws IllegalStateException, IOException {
		
		file.transferTo(new File("C:\\Users\\Aman\\Documents\\workspace-spring-tool-suite-4-4.9.0.RELEASE\\CodingChallenge\\ColudStreamPractice\\Talent\\src\\main\\resources\\static\\file\\"));
		
	}
	
		
		
		
	
}
