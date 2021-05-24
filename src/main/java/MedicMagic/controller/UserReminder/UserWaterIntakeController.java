package MedicMagic.controller.UserReminder;

import MedicMagic.userReminder.userWaterIntake.dto.UserWaterIntakeDto;
import MedicMagic.userReminder.userWaterIntake.service.UserWaterIntakeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
public class UserWaterIntakeController {
    private final UserWaterIntakeService userWaterIntakeService;

    @RequestMapping(value = "/setUserWaterIntake_view", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView setUserWaterIntake(HttpServletRequest httpServletRequest) {
        try {
            String id = httpServletRequest.getParameter("id");
            String cups = httpServletRequest.getParameter("cups");
            String waterTime = httpServletRequest.getParameter("water_time");

            UserWaterIntakeDto userWaterIntakeDto = new UserWaterIntakeDto(id, cups, waterTime);
            userWaterIntakeService.update(userWaterIntakeDto);

            ModelAndView mv= new ModelAndView();
            mv.setViewName("userReminder/userWaterIntake/setUserWaterIntake_view");
            mv.addObject("id", id);

            return mv;
        } catch(Exception e) {
            e.printStackTrace();

            ModelAndView mv = new ModelAndView();
            mv.setViewName("userReminder/userWaterIntake/setUserWaterIntakeError_view");
            mv.addObject("error", e.getMessage());

            return mv;
        }
    }

    @RequestMapping(value = "/getUserWaterIntake_view", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView getUserWaterIntake(HttpServletRequest httpServletRequest) {
        try {
            String id = httpServletRequest.getParameter("id");

            UserWaterIntakeDto userWaterIntakeDto = userWaterIntakeService.get(id);

            ModelAndView mv= new ModelAndView();
            mv.setViewName("userReminder/userWaterIntake/getUserWaterIntake_view");
            mv.addObject("id", userWaterIntakeDto.id);
            mv.addObject("cups", userWaterIntakeDto.cups);
            mv.addObject("water_time", userWaterIntakeDto.waterTime);

            return mv;
        } catch(Exception e) {
            e.printStackTrace();

            ModelAndView mv = new ModelAndView();
            mv.setViewName("userReminder/userWaterIntake/getUserWaterIntakeError_view");
            mv.addObject("error", e.getMessage());

            return mv;
        }
    }
}
