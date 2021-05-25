package MedicMagic.controller.UserReminder;

import MedicMagic.userReminder.userSleep.dto.UserSleepDto;
import MedicMagic.userReminder.userSleep.service.UserSleepService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
public class UserSleepController {
    private final UserSleepService userSleepService;

    @RequestMapping(value="/setUserSleep_view", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView setUserSleep(HttpServletRequest httpServletRequest) {
        try {
            String id = httpServletRequest.getParameter("id");
            String sleepGoal = httpServletRequest.getParameter("sleep_goal");

            UserSleepDto userSleepDto = new UserSleepDto(id, sleepGoal);
            userSleepService.update(userSleepDto);

            ModelAndView mv = new ModelAndView();
            mv.setViewName("userReminder/userSleep/setUserSleep_view");
            mv.addObject("id", id);

            return mv;
        } catch(Exception e){
            e.printStackTrace();

            ModelAndView mv = new ModelAndView();
            mv.setViewName("userReminder/userSleep/setUserSleepError_view");
            mv.addObject("error", e.getMessage());

            return mv;
        }
    }

    @RequestMapping(value="/getUserSleep_view", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView getUserSleep(HttpServletRequest httpServletRequest) {
        try {
            String id = httpServletRequest.getParameter("id");

            UserSleepDto userSleepDto = userSleepService.get(id);

            ModelAndView mv = new ModelAndView();
            mv.setViewName("userReminder/userSleep/getUserSleep_view");
            mv.addObject("id", id);
            mv.addObject("sleep_goal", userSleepDto.sleepGoal);

            return mv;
        } catch(Exception e){
            e.printStackTrace();

            ModelAndView mv = new ModelAndView();
            mv.setViewName("userReminder/userSleep/getUserSleepError_view");
            mv.addObject("error", e.getMessage());

            return mv;
        }
    }
}
