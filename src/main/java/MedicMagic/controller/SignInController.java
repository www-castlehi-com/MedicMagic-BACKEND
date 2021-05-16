package MedicMagic.controller;

import MedicMagic.user.NullKeyException;
import MedicMagic.user.dto.UserDto;
import MedicMagic.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
public class SignInController {
    private final UserService userService;

    @RequestMapping(value = "/signIn_view", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView signIn(HttpServletRequest httpServletRequest) {
        System.out.println("Server requested Android");
        try {
            String id = httpServletRequest.getParameter("id");
            String pw = httpServletRequest.getParameter("pw");
            System.out.println("ID from Android : " + id);
            System.out.println("PW from Android : " + pw);

            if(id != null && pw != null) {
                UserDto userDto = userService.signIn(id, pw);
                ModelAndView mv = new ModelAndView();
                mv.setViewName("signIn_view");
                mv.addObject("id", userDto.id);
                mv.addObject("name", userDto.name);
                mv.addObject("pw", userDto.password);
                mv.addObject("birthday", userDto.birthday);
                mv.addObject("age", userDto.age);

                return mv;
            }
            else {
                throw new NullKeyException("아이디와 비밀번호를 입력해주세요");
            }
        } catch(Exception e) {
            e.printStackTrace();

            return null;
        }
    }
}
