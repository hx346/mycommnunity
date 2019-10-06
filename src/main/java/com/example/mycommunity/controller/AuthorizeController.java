package com.example.mycommunity.controller;

import com.example.mycommunity.dto.AccessTokenDTO;
import com.example.mycommunity.dto.GitHubUser;
import com.example.mycommunity.mapper.UserMapper;
import com.example.mycommunity.modle.User;
import com.example.mycommunity.provider.GitHubProvider;
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
    //加载实例
    @Autowired
    private GitHubProvider gitHubProvider;

    @Value("${github.client.id}")
    private String Client_id;
    @Value("${github.client.secret}")
    private String Client_secret;
    @Value("${github.redirect.uri}")
    private String Redirect_uri;

    @Autowired
    private UserMapper userMapper;


    //name为接受的参数名字，后接数据
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           //HttpServletRequest request,
                           HttpServletResponse response) {
        //将数据传入accessTokenDTO
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(Redirect_uri);
        accessTokenDTO.setState(state);
        accessTokenDTO.setClient_id(Client_id);
        accessTokenDTO.setClient_secret(Client_secret);


        String accessToken = gitHubProvider.getAccessToken(accessTokenDTO);
        System.out.println(accessToken + "ac40");
        GitHubUser gitHubUser = gitHubProvider.getUser(accessToken);
        System.out.println(gitHubUser.getName());
        System.out.println(gitHubUser.getName() + "=name");
        if (gitHubUser != null) {
            //将githunuser内容写入user
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(gitHubUser.getName());
            user.setAccoutid(String.valueOf(gitHubUser.getId()));
            user.setGmtcreat(System.currentTimeMillis());
            user.setGetmodifide(user.getGmtcreat());
            System.out.println(user);
            userMapper.insert(user);
            //登陆成功，写cookie和session
            response.addCookie(new Cookie("token", token));
            //request.getSession().setAttribute("user", gitHubUser);
            return "redirect:/";
            //跳转回首页
        } else {
            //重新登录
            return "redirect:/";
        }


    }
}
