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
        try {
            String id = httpServletRequest.getParameter("id");
            String pw = httpServletRequest.getParameter("pw");

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
            String age = httpServletRequest.getParameter("age");

            UserDto userDto = new UserDto(id, name, pw, birthday, age);
            userService.signUp(userDto);

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
