package MedicMagic.controller;

import MedicMagic.user.DifferentPasswordException;
import MedicMagic.user.DuplicateUserIdException;
import MedicMagic.user.NoUserException;
import MedicMagic.user.NullKeyException;
import MedicMagic.user.domain.User;
import MedicMagic.user.dto.UserDto;
import MedicMagic.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    @RequestMapping(value = "/signIn_view", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView signIn(HttpServletRequest httpServletRequest) throws NullKeyException, NoUserException, DifferentPasswordException {
        System.out.println("Server requested Android");
        try {
            String id = httpServletRequest.getParameter("id");
            String pw = httpServletRequest.getParameter("pw");
            System.out.println("ID from Android : " + id);
            System.out.println("PW from Android : " + pw);

            UserDto userDto = userService.signIn(id, pw);
            ModelAndView mv = new ModelAndView();
            mv.setViewName("user/signIn_view");
            mv.addObject("id", userDto.id);
            mv.addObject("name", userDto.name);
            mv.addObject("pw", userDto.password);
            mv.addObject("birthday", userDto.birthday);
            mv.addObject("age", userDto.age);

            return mv;
        } catch(Exception e) {
            e.printStackTrace();

            ModelAndView mv = new ModelAndView();
            mv.setViewName("user/signInError_view");
            mv.addObject("error", e.getMessage());

            return mv;
        }
    }

    @RequestMapping(value = "/signUp_view", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView signUp(HttpServletRequest httpServletRequest) throws NullKeyException, DuplicateUserIdException {
        System.out.println("Server requested Android");
        try {
            String id = httpServletRequest.getParameter("id");
            String name = httpServletRequest.getParameter("name");
            String pw = httpServletRequest.getParameter("pw");
            String birthday = httpServletRequest.getParameter("birthday");
            int age = Integer.parseInt(httpServletRequest.getParameter("age"));

            System.out.println("ID from android : "+id);
            System.out.println("NAME from android : "+name);
            System.out.println("PW from android : " + pw);
            System.out.println("BIRTHDAY from android : "+birthday);
            System.out.println("AGE from android : "+ age);

            User user = new User(id, name, pw, birthday, age);
            userService.add(user);

            ModelAndView mv = new ModelAndView();
            mv.setViewName("user/signUp_view");
            mv.addObject("alert", "회원가입이 완료되었습니다");

            return mv;
        } catch (Exception e) {
            e.printStackTrace();

            ModelAndView mv = new ModelAndView();
            mv.setViewName("user/signUpError_view");
            mv.addObject("error", e.getMessage());

            return mv;
        }
    }
}
