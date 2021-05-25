package MedicMagic.controller.UserReminder;

import MedicMagic.userReminder.userExercise.dto.UserExerciseDto;
import MedicMagic.userReminder.userExercise.service.UserExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
public class UserExerciseController {
    private final UserExerciseService userExerciseService;

    @RequestMapping(value = "/setUserExercise_view", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView setUserExercise (HttpServletRequest httpServletRequest) {
        try {
            String id = httpServletRequest.getParameter("id");
            String exerciseHour = httpServletRequest.getParameter("exercise_hour");

            UserExerciseDto userExerciseDto = new UserExerciseDto(id, exerciseHour);
            userExerciseService.update(userExerciseDto);

            ModelAndView mv = new ModelAndView();
            mv.setViewName("userReminder/userExercise/setUserExercise_view");
            mv.addObject("id", id);

            return mv;
        } catch(Exception e ){
            e.printStackTrace();

            ModelAndView mv = new ModelAndView();
            mv.setViewName("userReminder/userExercise/setUserExerciseError_view");
            mv.addObject("error", e.getMessage());

            return mv;
        }
    }

    @RequestMapping(value = "/getUserExercise_view", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView getUserExercise(HttpServletRequest httpServletRequest) {
        try {
            String id = httpServletRequest.getParameter("id");

            UserExerciseDto userExerciseDto = userExerciseService.get(id);

            ModelAndView mv = new ModelAndView();
            mv.setViewName("userReminder/userExercise/getUserExercise_view");
            mv.addObject("id", id);
            mv.addObject("exercise_hour", userExerciseDto.exerciseHour);

            return mv;
        } catch(Exception e ){
            e.printStackTrace();

            ModelAndView mv = new ModelAndView();
            mv.setViewName("userReminder/userExercise/getUserExerciseError_view");
            mv.addObject("error", e.getMessage());

            return mv;
        }
    }
}
