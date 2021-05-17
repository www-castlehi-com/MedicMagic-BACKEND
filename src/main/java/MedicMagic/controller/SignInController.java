package MedicMagic.controller;

import MedicMagic.user.DifferentPasswordException;
import MedicMagic.user.NoUserException;
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
    public ModelAndView signIn(HttpServletRequest httpServletRequest) throws NullKeyException, NoUserException, DifferentPasswordException {
        System.out.println("Server requested Android");
        try {
            String id = httpServletRequest.getParameter("id");
            String pw = httpServletRequest.getParameter("pw");
            System.out.println("ID from Android : " + id);
            System.out.println("PW from Android : " + pw);

            UserDto userDto = userService.signIn(id, pw);
            ModelAndView mv = new ModelAndView();
            mv.setViewName("signIn_view");
            mv.addObject("id", userDto.id);
            mv.addObject("name", userDto.name);
            mv.addObject("pw", userDto.password);
            mv.addObject("birthday", userDto.birthday);
            mv.addObject("age", userDto.age);

            return mv;
        } catch(Exception e) {
            e.printStackTrace();

            ModelAndView mv = new ModelAndView();
            mv.setViewName("signInError_view");
            mv.addObject("error", e.getMessage());

            return mv;
        }
    }
}
