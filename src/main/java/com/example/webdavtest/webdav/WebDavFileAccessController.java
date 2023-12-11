package com.example.webdavtest.webdav;

import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@Validated
@Api
@RequestMapping("/api/webdav")
public interface WebDavFileAccessController {

    @GetMapping("/{fileId}")
    RedirectView getFile(@PathVariable Long fileId, HttpServletRequest request);
}
