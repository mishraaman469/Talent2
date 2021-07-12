package com.mindtree.talent.controller;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;
import java.io.OutputStreamWriter;
import java.nio.file.Path;
import java.util.concurrent.Callable;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.mindtree.talent.service.TalentService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class TalentController {

	@Autowired
	private TalentService talentService;

	@PostMapping("/saveFile")
	public ResponseEntity<String> addFile(@RequestParam("file") MultipartFile file)
			throws IllegalStateException, IOException {
		talentService.addFile(file);
		Path cwd = Path.of("").toAbsolutePath();
		String cwd1 = Path.of("").toAbsolutePath().toString();
		System.out.println(cwd);
		System.out.println(cwd1);
		return new ResponseEntity<String>("working", HttpStatus.OK);
	}

	@GetMapping("/getFile/{name}")
	public String File(@PathVariable String name) throws IOException, InterruptedException {
		System.out.println("");
		/*
		 * String dir =
		 * "C:\\Users\\Aman\\Documents\\workspace-spring-tool-suite-4-4.9.0.RELEASE\\CodingChallenge\\ColudStreamPractice\\Talent2\\temp\\"
		 * + name + "\\";
		 */
		 String dir = "/temp/" + name + "/";
		
		deleteExcelfile();
		callCompileCheckStyle(dir);
		callCompileBat(dir);

		return "working";
	}

	private void callCompileCheckStyle(String dir) throws IOException {
		System.out.println("CheckStylein proccess");
		/* String[] command = { "CMD", "/C", "mvn checkstyle:checkstyle" }; */
		String[] command = { "/bin/bash", "-c", "mvn checkstyle:checkstyle" };
		ProcessBuilder builder = new ProcessBuilder(command);
		builder = builder.directory(new File(dir));
		builder.redirectErrorStream(true);
		Process p = builder.start();
	}

	private void deleteExcelfile() {
		/*
		 * File file = new
		 * File("C:\\\\Users\\\\Aman\\\\Desktop\\\\Project\\\\test.xls");
		 */
		File file = new File("/mydata/test.xls");
		if (file.delete()) {
			log.info("Previous Test Case Report deleted");
		}

	}

	private void callCompileBat(String dir) throws IOException, InterruptedException {
		/* String[] command = { "CMD", "/C", "mvn test" }; */
		String[] command = { "bash", "-c", "mvn test" };
		ProcessBuilder builder = new ProcessBuilder(command);
		builder = builder.directory(new File(dir));
		builder.redirectErrorStream(true);
		Process p = builder.start();
		log.info("Reached in compiling test cases");
		callTestCases(dir);

	}

	private void callTestCases(String dir) throws IOException, InterruptedException {
		boolean valid = true;
		/*
		 * File file = new
		 * File("C:\\\\Users\\\\Aman\\\\Desktop\\\\Project\\\\test.xls");
		 */
		 File file = new File("/mydata/test.xls");
		while (valid) {
			if (file.exists()) {
				FileInputStream fis = new FileInputStream(file);
				HSSFWorkbook workBook = new HSSFWorkbook(fis);
				HSSFSheet sheet = workBook.getSheetAt(0);
				FormulaEvaluator formulaEvaluator = workBook.getCreationHelper().createFormulaEvaluator();
				for (Row row : sheet) {
					for (Cell cell : row) {
						switch (formulaEvaluator.evaluateInCell(cell).getCellType()) {
						case Cell.CELL_TYPE_NUMERIC:
							System.out.print(cell.getNumericCellValue() + "\t\t");
							break;
						case Cell.CELL_TYPE_STRING:
							System.out.print(cell.getStringCellValue() + "\t\t");
							break;
						}
					}
					System.out.println();
				}
				valid = false;
			} else {
				log.info("System is in Processing");
				Thread.sleep(5000);
			}
		}
		
		reportPublishService(dir);
	}

	private void reportPublishService(String dir) {
		String directory=dir+"target/site/checkstyle.html";
		System.out.println(directory);
		LinkedMultiValueMap<String, Object>  map=new LinkedMultiValueMap<>();
		FileSystemResource value=new FileSystemResource(new File("/mydata/test.xls"));
		FileSystemResource value1=new FileSystemResource(new File(directory));
		map.add("file", value);
		map.add("file", value1);
		HttpHeaders header=new HttpHeaders();
		header.setContentType(MediaType.MULTIPART_FORM_DATA);
		HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity=new HttpEntity<>(map,header);
		RestTemplate template=new RestTemplate();
		System.out.println("reached");
		template.exchange("http://file:8082/save", HttpMethod.POST, requestEntity, String.class);
		
		
	}

}
