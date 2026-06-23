package com.atbs.utility;

import com.atbs.exception.FileInvalidExtensionException;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.util.List;

public class FileUtility {

    private FileUtility() {
        throw new IllegalStateException("Utility class");
    }

    public static void validFile(MultipartFile file) throws FileNotFoundException {
        if(file.isEmpty()){
            throw new FileNotFoundException("Not Found");
        }


        List<String> allowedExt=List.of("png", "jpeg", "jpg", "pdf", "docx", "pages");
        String fileName=file.getOriginalFilename();

        String ext=fileName.split("\\.")[1];

        if(!allowedExt.contains(ext)){
            throw new FileInvalidExtensionException(ext+" is not a Correct Extension");
        }
    }
}
