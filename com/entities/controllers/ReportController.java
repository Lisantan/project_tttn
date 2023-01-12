package com.entities.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.entities.exceptions.ResourceNotFoundException;
import com.entities.fileHandle.FileStorageService;
import com.entities.fileHandle.ResponseFile;
import com.entities.fileHandle.ResponseMessage;
import com.entities.models.Report;
import com.entities.repositories.ReportRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ReportController {

	@Autowired
	private FileStorageService storageService;
	
	@Autowired
	private ReportRepository repository;
	
	@GetMapping("/reports/count")
	public int getReportCount(){
		return repository.reportCount();
	}

	@PostMapping("/reports")
	public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("reportName") String reportName,
			@RequestParam("file") MultipartFile file) {
		String message = "";
		try {
			storageService.store(reportName, file);

			message = "Uploaded the file successfully: " + file.getOriginalFilename();
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			message = "Could not upload the file: " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}
	}

	@GetMapping("/reports")
	public ResponseEntity<List<ResponseFile>> getListFiles() {
		List<ResponseFile> files = storageService.getAllFiles().map(dbFile -> {
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/reports/")
					.path(String.valueOf(dbFile.getId())).toUriString();

			return new ResponseFile(dbFile.getId(), dbFile.getReportName(), dbFile.getName(), fileDownloadUri, dbFile.getType(), dbFile.getData().length);
		}).collect(Collectors.toList());

		return ResponseEntity.status(HttpStatus.OK).body(files);
	}

	@GetMapping("/reports/{id}")
	public ResponseEntity<byte[]> getFile(@PathVariable Integer id) {
		Report report = storageService.getFile(id);

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + report.getName() + "\"")
				.body(report.getData());
	}

	@DeleteMapping("/reports/{id}") 
	public ResponseEntity<Map<String, Boolean>>delete(@PathVariable Integer id){ 
		Report e = repository.findById(id).orElseThrow(() -> 
						new ResourceNotFoundException("Report not exist with id" + id)); 
		repository.delete(e); Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE); return ResponseEntity.ok(response); 
	}

}