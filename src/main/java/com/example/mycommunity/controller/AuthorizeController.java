package com.example.mycommunity.controller;

import com.example.mycommunity.dto.AccessTokenDTO;
import com.example.mycommunity.dto.GitHubUser;
import com.example.mycommunity.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

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


    //name为接受的参数名字，后接数据
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request) {
        //将数据传入accessTokenDTO
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(Redirect_uri);
        accessTokenDTO.setState(state);
        accessTokenDTO.setClient_id(Client_id);
        accessTokenDTO.setClient_secret(Client_secret);


        String accessToken = gitHubProvider.getAccessToken(accessTokenDTO);
        System.out.println(accessToken + "ac40");
        GitHubUser user = gitHubProvider.getUser(accessToken);
        System.out.println(user.getName());
        System.out.println(user.getName() + "name");
        if (user != null) {
            //登陆成功，写cookie和session
            request.getSession().setAttribute("user", user);
            return "redirect:/";
            //跳转回首页
        } else {
            //重新登录
            return "redirect:/";
        }



    }
}
