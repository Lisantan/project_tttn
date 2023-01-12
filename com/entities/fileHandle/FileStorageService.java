package com.entities.fileHandle;

import java.io.IOException;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.entities.models.Contract;
import com.entities.models.Employee;
import com.entities.models.Law;
import com.entities.models.Report;
import com.entities.repositories.ContractRepository;
import com.entities.repositories.LawRepository;
import com.entities.repositories.ReportRepository;

@Service
public class FileStorageService {

	@Autowired
	private ReportRepository reportRepository;
	
	@Autowired
	private LawRepository lawRepository;
	
	@Autowired
	private ContractRepository contractRepository;

	public Report store(String reportName, MultipartFile file) throws IOException {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		Report FileDB = new Report(reportName, fileName, file.getContentType(), file.getBytes());

		return reportRepository.save(FileDB);
	}

	public Report getFile(Integer id) {
		return reportRepository.findById(id).get();
	}

	public Stream<Report> getAllFiles() {
		return reportRepository.findAll().stream();
	}
	
	public Law storeLaw(String lawName, MultipartFile file) throws IOException {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		Law FileDB = new Law(lawName, fileName, file.getContentType(), file.getBytes());

		return lawRepository.save(FileDB);
	}

	public Law getFileLaw(Integer id) {
		return lawRepository.findById(id).get();
	}

	public Stream<Law> getAllFilesLaw() {
		return lawRepository.findAll().stream();
	}
	
	public Contract storeContract(Employee employee, String lawName, MultipartFile file) throws IOException {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		Contract FileDB = new Contract(employee, lawName, fileName, file.getContentType(), file.getBytes());

		return contractRepository.save(FileDB);
	}

	public Contract getFileContract(Integer id) {
		return contractRepository.findById(id).get();
	}

	public Stream<Contract> getAllFilesContract() {
		return contractRepository.findAll().stream();
	}
}
