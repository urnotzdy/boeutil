package com.example.myutil.minio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@RestController
public class OperateApiController {

    @Autowired
    private MinioUtil minioUtil;
    @Autowired
    private MinioConfig prop;

//    @GetMapping("/bucketExists")
//    public Result bucketExists(String bucketName) {
//        return Result.success(minioUtil.bucketExists(bucketName));
//    }
//
//    @GetMapping("/makeBucket")
//    public Result makeBucket(String bucketName) {
//        minioUtil.makeBucket(bucketName);
//        return Result.success();
//    }
//
//    @GetMapping("/removeBucket")
//    public Result removeBucket(String bucketName) {
//        minioUtil.removeBucket(bucketName);
//        return Result.success();
//    }
//
//    @GetMapping("/getAllBuckets")
//    public Result<List<Bucket>> getAllBuckets() {
//        List<Bucket> allBuckets = minioUtil.getAllBuckets();
//        return allBuckets;
//    }

    @PostMapping("/upload")
    public String upload(@RequestParam(value = "file") MultipartFile file) {
        String objectName = minioUtil.upload(file);
        if (null != objectName) {
            return prop.getEndpoint() + "/" + prop.getBucketName() + "/" + objectName;
        }
        return "";
    }

    @GetMapping("/preview")
    public String preview(@RequestParam("fileName") String fileName) {
        return minioUtil.preview(fileName);
    }

    @GetMapping("/download")
    public String download(@RequestParam("fileName") String fileName, HttpServletResponse res) {
        minioUtil.download(fileName,res);
        return "";
    }

    @PostMapping("/delete")
    public String remove(String url) {
        String objName = url.substring(url.lastIndexOf(prop.getBucketName()+"/") + prop.getBucketName().length()+1);
        minioUtil.remove(objName);
        return "";
    }

}
