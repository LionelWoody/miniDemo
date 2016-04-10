package demo.controllers;

import demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

/**
 * Created by <a href="mailto:javaworld@qq.com">Woody</a>
 */
@Controller
public class WelcomeController {

    @Value("${application.message:Hello World}")
    private String message = "Hello World";

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/")
    public String welcome(Map<String, Object> model, HttpServletRequest request) {
        model.put("time", new Date());
        model.put("message", userRepository.findOne(1L));
        request.setAttribute("message.request", userRepository.findOne(1L));
        request.getSession().setAttribute("message.session", userRepository.findOne(1L));
        model.put("tests", Arrays.asList("abc,def,ghi,jkl,mno,stu,vwx,yz".split(",")));
        return "welcome";
    }

    @RequestMapping("/users")
    public String getUsers(Model model, HttpServletRequest request) {

        int page;
        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch (Exception e) {
            page = 0;
        }

        int pageSize;
        try {
            pageSize = Integer.parseInt(request.getParameter("pageSize"));
        } catch (Exception e) {
            pageSize = 25;
        }

        Pageable pageable = new PageRequest(page, pageSize);

        model.addAttribute("users", userRepository.findAll(pageable));

        return "users";

    }

}
