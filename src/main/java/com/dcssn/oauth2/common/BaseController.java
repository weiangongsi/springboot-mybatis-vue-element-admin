package com.dcssn.oauth2.common;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.beans.PropertyEditorSupport;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * 建议controller 继承此类，实现分页的参数的封装，以及日期格式化绑定
 *
 * @author lihy
 * @date 2019-5-27
 */
public class BaseController {

    /**
     * 日期绑定，将页面中的日期字符串转换为日期类型
     *
     * @param binder binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(LocalDateTime.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime dateTime = LocalDateTime.parse(text, formatter);
                setValue(dateTime);
            }
        });
    }


    /**
     * 返回分页信息
     *
     * @return page
     */
    protected <T> Page<T> startPage(Class<T> clz) {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        // 从0开始，前台从1开始，所以减1
        int page = Integer.parseInt(request.getParameter("page"));
        int size = Integer.parseInt(request.getParameter("size"));
        String sort = request.getParameter("sort");
        Page<T> pageInfo = new Page<>(page, size);
        if (!StringUtils.isEmpty(sort)) {
            String[] sortArr = sort.split(" ");
            if (sortArr.length == 1 || "asc".equalsIgnoreCase(sortArr[1])) {
                pageInfo.setAsc(sortArr[0].toUpperCase());
            } else {
                pageInfo.setDesc(sortArr[0]);
            }
        }
        return pageInfo;
    }
}
