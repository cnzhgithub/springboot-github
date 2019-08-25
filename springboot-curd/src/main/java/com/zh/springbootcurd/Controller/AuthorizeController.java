package com.zh.springbootcurd.Controller;

import com.zh.springbootcurd.dto.AccessTokenDto;
import com.zh.springbootcurd.dto.GithubUser;
import com.zh.springbootcurd.mapper.UserMapper;
import com.zh.springbootcurd.model.User;
import com.zh.springbootcurd.provider.GithubProvider;
import com.zh.springbootcurd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider provider;
    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSer;
    @Value("${github.redirect.uri}")
    private String clientUri;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request,
                           HttpServletResponse response) {
        AccessTokenDto accessTokenDto = new AccessTokenDto();
        accessTokenDto.setClient_id(clientId);
        accessTokenDto.setClient_secret(clientSer);
        accessTokenDto.setCode(code);
        accessTokenDto.setRedirect_uri(clientUri);
        accessTokenDto.setState(state);
        String s = provider.getAccessToken(accessTokenDto);
        try {
            GithubUser user = provider.githubUser(s);
            if (user != null) {
                User user1 = new User();
                user1.setName(user.getName());
                String token = UUID.randomUUID().toString();
                user1.setToken(token);
                user1.setAccount_id(String.valueOf(user.getId()));
                user1.setAvatar_url(user.getAvatar_url());
                user1.setGmt_create(System.currentTimeMillis());
                user1.setGmt_modified(user1.getGmt_create());
                userService.createOrUpdate(user1);
                request.getSession().setAttribute("user",user);
                System.out.println(token);
                response.addCookie(new Cookie("token",token));
                return "redirect:/";
            } else {
                return "redirect:/";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index";
    }
   @GetMapping("/logout")
  public String logout(HttpServletRequest request,HttpServletResponse response){
        request.getSession().removeAttribute("user");
        Cookie cookie = new Cookie("token",null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
  }
}
