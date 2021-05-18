package MedicMagic.controller;

import MedicMagic.user.DuplicateUserIdException;
import MedicMagic.user.NullKeyException;
import MedicMagic.user.domain.User;
import MedicMagic.userGoal.dto.UserGoalDto;
import MedicMagic.userGoal.service.UserGoalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
public class UserGoalController {
    private final UserGoalService userGoalService;

    @RequestMapping(value = "/userGoal_view", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView userGoal(HttpServletRequest httpServletRequest) {
        System.out.println("Server requested Android");
        try {
            String id = httpServletRequest.getParameter("id");

            UserGoalDto userGoalDto = userGoalService.conveyUserGoal(id);

            ModelAndView mv = new ModelAndView();
            mv.setViewName("userGoal/userGoal_view");
            mv.addObject("weigh", userGoalDto.weigh);
            mv.addObject("sleepTime", userGoalDto.sleepTime);
            mv.addObject("exerciseTime", userGoalDto.exerciseTime);
            mv.addObject("waterIntake", userGoalDto.waterIntake);

            return mv;
        } catch (Exception e) {
            e.printStackTrace();

            ModelAndView mv = new ModelAndView();
            mv.setViewName("userGoal/userGoalError_view");
            mv.addObject("error", e.getMessage());

            return mv;
        }
    }
}
