package br.com.casadocodigo.loja.infra;

import java.io.IOException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;

import br.com.casadocodigo.loja.utils.LogConstants;

@RequestScoped
public class FileSaver {
	
	@Inject
	private HttpServletRequest request;
	
	@Inject
	private AmazonS3Client s3;
	
	private static final String CONTENT_DISPOSITION = 
			"content-disposition";
	
	private static final String FILENAME_KEY = 
			"filename=";
	
	
	public String write(String baseFolder, Part multipartFile) {
		
//		String serverPath = request.getServletContext()
//				.getRealPath("/" + baseFolder);
		
		String fileName = extractFileName(multipartFile.getHeader(CONTENT_DISPOSITION));
		
//		String path = serverPath + "/" + fileName;
		
		try {
//			multipartFile.write(path);
			s3.putObject("casadocodigo", fileName,
					multipartFile.getInputStream(), new ObjectMetadata());
			return "http://localhost:9444/s3/casadocodigo/"+fileName+"?noAuth=true";
		}catch (IOException e) {
			LogConstants.getLogError(e.getMessage());
			throw new RuntimeException(e);
		}
	}
	
	private String extractFileName(String contentDisposition) {
		
		if (contentDisposition == null)
			return null;
		
		int startIndex = contentDisposition.indexOf(FILENAME_KEY);
		if (startIndex == -1)
			return null;
		String fileName = contentDisposition
				.substring(startIndex + FILENAME_KEY.length());
		
		if (fileName.startsWith("\"")) {
			int endIndex = fileName.indexOf("\"", 1);
			if (endIndex != -1) {
				return fileName.substring(1, endIndex);
			}
		}else {
			int endIndex = fileName.indexOf(";");
			if (endIndex != -1) {
				return fileName.substring(0, endIndex);
			}
		}
		
		return fileName;
	}
	
}
