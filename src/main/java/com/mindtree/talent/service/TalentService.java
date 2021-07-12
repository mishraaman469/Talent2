package com.mindtree.talent.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface TalentService {

	void addFile(MultipartFile file) throws IllegalStateException, IOException;

}
