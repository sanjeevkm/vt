package com.skm.vt.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.skm.vt.exception.APIException;
import com.skm.vt.service.DataLoaderService;

//Controller to upload CSV data file into DB. 
@RestController
public class PortDataUploadController {

	@Value("${csv.file.path}")
	private String fileUploadFolder;

	@Autowired
	DataLoaderService csvLoader;

	@PostMapping("/upload")
	public RedirectView fileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes)
			throws APIException {
		// File validation
		if (file.isEmpty() || !file.getOriginalFilename().endsWith(".csv") || !file.getContentType().endsWith("csv")) {
			return new RedirectView("uploadError.html");
		}

		try {
			// Reading file data
			byte[] bytes = file.getBytes();
			Path path = Paths.get(fileUploadFolder + file.getOriginalFilename());
			Files.write(path, bytes);

			// Calling service to process data and push to DAO for persisting.
			csvLoader.save(path);
		} catch (IOException e) {
			// TODO: handle properly
			e.printStackTrace();

			return new RedirectView("uploadError.html");
		}

		return new RedirectView("uploadSuccess.html");
	}
}