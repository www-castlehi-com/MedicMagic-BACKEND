package MedicMagic.controller;

import MedicMagic.userGoal.domain.UserGoal;
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

    @RequestMapping(value = "/getGoal_view", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView getGoal(HttpServletRequest httpServletRequest) {
        try {
            String id = httpServletRequest.getParameter("id");

            UserGoalDto userGoalDto = userGoalService.conveyUserGoal(id);

            ModelAndView mv = new ModelAndView();
            mv.setViewName("userGoal/getGoal_view");
            mv.addObject("weigh", userGoalDto.weigh);
            if(userGoalDto.sleepTime == null) {
                mv.addObject("sleepTime", "null");
            } else {
                mv.addObject("sleepTime", userGoalDto.sleepTime);
            }
            if(userGoalDto.exerciseTime == null) {
                mv.addObject("exerciseTime", "null");
            } else {
                mv.addObject("exerciseTime", userGoalDto.exerciseTime);
            }
            mv.addObject("waterIntake", userGoalDto.waterIntake);

            return mv;
        } catch (Exception e) {
            e.printStackTrace();

            ModelAndView mv = new ModelAndView();
            mv.setViewName("userGoal/getGoalError_view");
            mv.addObject("error", e.getMessage());

            return mv;
        }
    }

    @RequestMapping(value = "/setGoal_view", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView setGoal(HttpServletRequest httpServletRequest) {
        try {
            String id = httpServletRequest.getParameter("id");
            Double weigh = Double.parseDouble(httpServletRequest.getParameter("weigh"));
            String sleepTime = httpServletRequest.getParameter("sleepTime");
            String exerciseTime = httpServletRequest.getParameter("exerciseTime");
            Double waterIntake = Double.parseDouble(httpServletRequest.getParameter("waterIntake"));

            if(sleepTime == "null") {sleepTime = null;}
            if(exerciseTime == "null") {exerciseTime = null;}
            userGoalService.update(new UserGoal(id, weigh, sleepTime, exerciseTime, waterIntake));

            ModelAndView mv = new ModelAndView();
            mv.setViewName("userGoal/setGoal_view");
            mv.addObject("id", id);

            return mv;

        } catch(Exception e) {
            e.printStackTrace();

            ModelAndView mv = new ModelAndView();
            mv.setViewName("userGoal/setGoalError_view");
            mv.addObject("error", e.getMessage());

            return mv;
        }
    }
}
