package com.nowcoder.community.controller;

import com.nowcoder.community.annotation.LoginRequired;
import com.nowcoder.community.entity.User;
import com.nowcoder.community.service.UserService;
import com.nowcoder.community.util.CommunityUtil;
import com.nowcoder.community.util.HostHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@Slf4j
@RequestMapping("/user")
@Controller
public class UserController {

    @Value("${community.path.upload}")
    private String uploadPath;
    @Value("${community.path.domain}")
    private String domain;
    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Autowired
    private UserService userService;
    @Autowired
private HostHolder hostHolder;
    @LoginRequired
    @GetMapping(path = "/setting")
    public String getSettingPage() {

        return "/site/setting";
    }
    @LoginRequired
@PostMapping(path = "/upload")
public String uploadHeader(MultipartFile headerImage, Model model){
        if(headerImage == null) {
            model.addAttribute("error","您还没有选择图片");
            return "/site/setting";
        }

    String fileName = headerImage.getOriginalFilename();
    String suffix = fileName.substring(fileName.lastIndexOf("."));
if(StringUtils.isBlank(suffix)){
    model.addAttribute("error","文件的格式不正确");
    return "/site/setting";
}
fileName =  CommunityUtil.generateUUID()+suffix;
    File dest = new File(uploadPath+"/"+fileName);
    try {
        headerImage.transferTo(dest);
    } catch (IOException e) {
log.error("上传文件失败");
throw new RuntimeException("上传文件失败",e);
    }
    //更新当前用户头像的路径
    User user = hostHolder.getUser();
    String headerUrl = domain + contextPath + "/user/header/"+ fileName;
    userService.updateHeader(user.getId(),headerUrl);
    return "redirect:/index";
}
    @RequestMapping(path = "/header/{fileName}", method = RequestMethod.GET)
    public void getHeader(@PathVariable("fileName") String fileName, HttpServletResponse response) {
        // 服务器存放路径
        fileName = uploadPath + "/" + fileName;
        // 文件后缀
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        // 响应图片
        response.setContentType("image/" + suffix);
        try (
                FileInputStream fis = new FileInputStream(fileName);
                OutputStream os = response.getOutputStream();
        ) {
            byte[] buffer = new byte[1024];
            int b = 0;
            while ((b = fis.read(buffer)) != -1) {
                os.write(buffer, 0, b);
            }
        } catch (IOException e) {
            log.error("读取头像失败: " + e.getMessage());
        }
    }

}
