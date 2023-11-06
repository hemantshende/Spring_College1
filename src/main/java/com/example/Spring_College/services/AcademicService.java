package com.example.Spring_College.services;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.Spring_College.entities.Academic;
import com.example.Spring_College.entities.User;

public interface AcademicService {

//	Academic createAcademic(Academic academic);

	List<Academic> getAllAcademics();

	Academic getAacademicById(int id);

	boolean deleteAcademic(int id);

//	Academic createAcademic(Academic academic, MultipartFile cvPdfPath, MultipartFile govIdJpegPath);

//    public Academic createAcademic(Academic academic, List<MultipartFile> files) throws IOException;

//	Academic createAcademic(Academic academic);

	Academic createAcademic(int qualificationId, int interestsId, String previousCollegeName, String specialization,
			List<String> languagesKnown, List<byte[]> fileDataList, int userId);

	Academic updateAcademic(int academicId, int qualificationId, int interestsId, String previousCollegeName,
			String specialization, List<String> languagesKnown, List<byte[]> fileDataList, int userId);

}
