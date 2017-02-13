package zip;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Controller
@RequestMapping("/")
public class MyController {

    ArrayList<String> zipNames = new ArrayList<>();
    public static final String DIR_PATH = "C:\\Users\\Ootka\\Zip-zip\\src\\main\\resources\\files\\";

    @RequestMapping("/")
    public String onIndex() {
        return "index";
    }

    @RequestMapping(value = "/uploadMultipleFile", method = RequestMethod.POST)
    String uploadMultipleFileHandler(Model model, @RequestParam("file") MultipartFile[] files) {

        int counter = 0;
        for (MultipartFile file : files) {
            if (file.isEmpty()) {
                counter++;
            }
        }

        if (files != null && counter < files.length) {

            FileOutputStream fos = null;
            try {
                String name = System.currentTimeMillis() + "";
                fos = new FileOutputStream(DIR_PATH + name + ".zip");
                ZipOutputStream zos = new ZipOutputStream(fos);

                for (MultipartFile file : files) {

                    if (file.isEmpty()) {
                        continue;
                    }

                    File convFile = new File(file.getOriginalFilename());
                    file.transferTo(convFile);
                    addToZipFile(convFile, zos);
                }
                model.addAttribute("name", name);
                zos.flush();
                zos.close();
                fos.flush();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "result";
    }

    public static void addToZipFile(File file, ZipOutputStream zos) throws IOException {

        FileInputStream fis = new FileInputStream(file);
        ZipEntry zipEntry = new ZipEntry(file.getName());
        zos.putNextEntry(zipEntry);

        byte[] bytes = new byte[1024];
        int length;
        while ((length = fis.read(bytes)) >= 0) {
            zos.write(bytes, 0, length);
        }

        zos.closeEntry();
        fis.close();
    }

    @RequestMapping(value = "/download/{file_name}", method = RequestMethod.GET)
    public void getFile(
            @PathVariable("file_name") String fileName,
            HttpServletResponse response) {
        try {
            InputStream is = new FileInputStream(DIR_PATH + fileName + ".zip");
            response.setHeader("content-Disposition", "attachment; filename=" + fileName);
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + ".zip\"");
            response.setHeader("Content-Type", "application/zip");
            IOUtils.copyLarge(is, response.getOutputStream());
        } catch (IOException ex) {
            throw new RuntimeException("IOError writing file to output stream");
        }

    }

    @RequestMapping(value = "/all_files", method = RequestMethod.POST)
    public String allFiles(Model model){
        getAllFiles();
        model.addAttribute("zipNames", zipNames);
        return "all_files";
    }

    public void getAllFiles(){
        File folder = new File(DIR_PATH);
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile() && !zipNames.contains(listOfFiles[i].getName())) {
                zipNames.add(listOfFiles[i].getName());
            }
        }
    }
}
