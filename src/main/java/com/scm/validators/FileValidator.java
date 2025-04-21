package com.scm.validators;

// import java.awt.image.BufferedImage;

// import javax.imageio.ImageIO;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FileValidator implements ConstraintValidator<ValidFile,MultipartFile>{

    private static final long MAX_FILE_SIZE = 1024 * 1024 * 1; // 1MB


    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        if (file==null || file.isEmpty()) {
            // context.disableDefaultConstraintViolation();
            // context.buildConstraintViolationWithTemplate("File cannot be empty.").addConstraintViolation();
            return true;
        }    

        if(file.getSize()>MAX_FILE_SIZE){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("File size should be less than 1MB.").addConstraintViolation();
            return false;
        }

        // check resolution
        // BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
        // if (bufferedImage.getWidth()>600) {
            
        // }

        return true;
    }

}
