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
import com.entities.fileHandle.ResponseFileContract;
import com.entities.fileHandle.ResponseMessage;
import com.entities.models.Contract;
import com.entities.models.Employee;
import com.entities.repositories.ContractRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ContractController {
	
	@Autowired
	private FileStorageService storageService;
	
	@Autowired
	private ContractRepository repository;

	@PostMapping("/contracts")
	public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("contractName") Employee employee, @RequestParam("contractName") String contractName,
			@RequestParam("file") MultipartFile file) {
		String message = "";
		try {
			storageService.storeContract(employee, contractName, file);

			message = "Uploaded the file successfully: " + file.getOriginalFilename();
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			message = "Could not upload the file: " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}
	}

	@GetMapping("/contracts")
	public ResponseEntity<List<ResponseFileContract>> getListFiles() {
		List<ResponseFileContract> files = storageService.getAllFilesContract().map(dbFile -> {
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/contracts/")
					.path(String.valueOf(dbFile.getId())).toUriString();

			return new ResponseFileContract(dbFile.getId(), dbFile.getEmployee(), dbFile.getContractName(), dbFile.getName(), 
					fileDownloadUri, dbFile.getType(), dbFile.getData().length);
		}).collect(Collectors.toList());

		return ResponseEntity.status(HttpStatus.OK).body(files);
	}

	@GetMapping("/contracts/{id}")
	public ResponseEntity<byte[]> getFile(@PathVariable Integer id) {
		Contract contract = storageService.getFileContract(id);

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + contract.getName() + "\"")
				.body(contract.getData());
	}

	@DeleteMapping("/contracts/{id}") 
	public ResponseEntity<Map<String, Boolean>>delete(@PathVariable Integer id){ 
		Contract e = repository.findById(id).orElseThrow(() -> 
						new ResourceNotFoundException("Contract not exist with id" + id)); 
		repository.delete(e); Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE); return ResponseEntity.ok(response); 
	}
}
