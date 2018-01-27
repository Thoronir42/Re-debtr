package cz.zcu.students.kiwi.redebtr.io;


import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class RedebtrFiles {

    private final String avatarsDirectory;

    public RedebtrFiles(ServletContext ctx) {
        this.avatarsDirectory = ctx.getRealPath("/img/profile/");
    }

    public String saveAvatar(Part part) {
        String ext = FilenameUtils.getExtension(part.getSubmittedFileName());

        String fileName = getNewFileName(avatarsDirectory, ext);
        File destination = new File(avatarsDirectory + fileName);

        try(InputStream inputStream = part.getInputStream()) {
            System.out.println(destination.getAbsolutePath());
            Files.copy(inputStream, destination.toPath());
            return fileName;
        } catch (IOException ex) {
            System.err.println(ex.toString());
            return null;
        }
    }

    private String getNewFileName(String dir, String extension) {
        String fileName;
        Date now = new Date();
        DateFormat format = new SimpleDateFormat("YYYY-MM-dd-HH-mm");
        do {
            fileName = format.format(now) + "-" + System.currentTimeMillis() % 10000 + "." + extension;
        } while (new File(dir + fileName).exists());

        return fileName;
    }


}
