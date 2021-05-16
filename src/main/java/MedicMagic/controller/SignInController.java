package MedicMagic.controller;

import MedicMagic.user.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class SignInController {
    private final UserServiceImpl userService;

//    @RequestMapping(value = "/signIn", method = )
}
