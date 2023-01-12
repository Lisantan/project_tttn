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
import com.entities.models.Law;
import com.entities.repositories.LawRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class LawController {
	/* Old Controller
	@Autowired
	private LawRepository repository;
	
	@GetMapping("/laws")
	public List<Law> getAll(){
		return repository.findAll();
	}
	
	@PostMapping("/laws")
	public Law create(@RequestBody Law e) {
		return repository.save(e);
	}
	
	@GetMapping("/laws/{id}")
	public ResponseEntity<Law> getById(@PathVariable Integer id){
		Law e = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Law not exist with id" + id));
		return ResponseEntity.ok(e);
	}
	
	@PutMapping("/laws/{id}")
	public ResponseEntity<Law> update(@PathVariable Integer id,
			@RequestBody Law eEdit){
		Law e = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Law not exist with id" + id));
		
		e.setIdLaw(eEdit.getIdLaw());
		e.setName(eEdit.getName());
		e.setAttachFile(eEdit.getAttachFile());
	
		Law eEdited = repository.save(e);
		return ResponseEntity.ok(eEdited);	
	}
	
	@DeleteMapping("/laws/{id}")
	public ResponseEntity<Map<String, Boolean>> delete(@PathVariable Integer id){
		Law e = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Law not exist with id" + id));
		repository.delete(e);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	} */
	
	@Autowired
	private FileStorageService storageService;
	
	@Autowired
	private LawRepository repository;

	@PostMapping("/laws")
	public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("lawName") String lawName,
			@RequestParam("file") MultipartFile file) {
		String message = "";
		try {
			storageService.storeLaw(lawName, file);

			message = "Uploaded the file successfully: " + file.getOriginalFilename();
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			message = "Could not upload the file: " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}
	}

	@GetMapping("/laws")
	public ResponseEntity<List<ResponseFile>> getListFiles() {
		List<ResponseFile> files = storageService.getAllFilesLaw().map(dbFile -> {
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/laws/")
					.path(String.valueOf(dbFile.getId())).toUriString();

			return new ResponseFile(dbFile.getId(), dbFile.getLawName(), dbFile.getName(), fileDownloadUri, dbFile.getType(), dbFile.getData().length);
		}).collect(Collectors.toList());

		return ResponseEntity.status(HttpStatus.OK).body(files);
	}

	@GetMapping("/laws/{id}")
	public ResponseEntity<byte[]> getFile(@PathVariable Integer id) {
		Law law = storageService.getFileLaw(id);

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + law.getName() + "\"")
				.body(law.getData());
	}

	@DeleteMapping("/laws/{id}") 
	public ResponseEntity<Map<String, Boolean>>delete(@PathVariable Integer id){ 
		Law e = repository.findById(id).orElseThrow(() -> 
						new ResourceNotFoundException("Law not exist with id" + id)); 
		repository.delete(e); Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE); return ResponseEntity.ok(response); 
	}
}
