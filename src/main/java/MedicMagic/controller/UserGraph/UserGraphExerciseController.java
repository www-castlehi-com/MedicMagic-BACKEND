package MedicMagic.controller.UserGraph;

import MedicMagic.userCalender.dto.UserCalenderDto;
import MedicMagic.userCalender.service.UserCalenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserGraphExerciseController {
    private final UserCalenderService userCalenderService;

    @RequestMapping(value="/getExercise_view", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView getExercise(HttpServletRequest httpServletRequest) {
        try {
            String id = httpServletRequest.getParameter("id");

            List<UserCalenderDto> userCalenderDtoList = userCalenderService.getEachId(id);
            int count;
            if(userCalenderDtoList.size() > 30) {
                count = userCalenderDtoList.size() - 30;
            } else {
                for(int i = userCalenderDtoList.size(); i <= 30; i++) {
                    userCalenderDtoList.add(new UserCalenderDto());
                }
                count = 0;
            }

            ModelAndView mv = new ModelAndView();
            mv.setViewName("userGraph/userExercise/getExercise_view");

            mv.addObject("exercise1", userCalenderDtoList.get(count++).exerciseTime);
            mv.addObject("exercise2", userCalenderDtoList.get(count++).exerciseTime);
            mv.addObject("exercise3", userCalenderDtoList.get(count++).exerciseTime);
            mv.addObject("exercise4", userCalenderDtoList.get(count++).exerciseTime);
            mv.addObject("exercise5", userCalenderDtoList.get(count++).exerciseTime);
            mv.addObject("exercise6", userCalenderDtoList.get(count++).exerciseTime);
            mv.addObject("exercise7", userCalenderDtoList.get(count++).exerciseTime);
            mv.addObject("exercise8", userCalenderDtoList.get(count++).exerciseTime);
            mv.addObject("exercise9", userCalenderDtoList.get(count++).exerciseTime);
            mv.addObject("exercise10", userCalenderDtoList.get(count++).exerciseTime);
            mv.addObject("exercise11", userCalenderDtoList.get(count++).exerciseTime);
            mv.addObject("exercise12", userCalenderDtoList.get(count++).exerciseTime);
            mv.addObject("exercise13", userCalenderDtoList.get(count++).exerciseTime);
            mv.addObject("exercise14", userCalenderDtoList.get(count++).exerciseTime);
            mv.addObject("exercise15", userCalenderDtoList.get(count++).exerciseTime);
            mv.addObject("exercise16", userCalenderDtoList.get(count++).exerciseTime);
            mv.addObject("exercise17", userCalenderDtoList.get(count++).exerciseTime);
            mv.addObject("exercise18", userCalenderDtoList.get(count++).exerciseTime);
            mv.addObject("exercise19", userCalenderDtoList.get(count++).exerciseTime);
            mv.addObject("exercise20", userCalenderDtoList.get(count++).exerciseTime);
            mv.addObject("exercise21", userCalenderDtoList.get(count++).exerciseTime);
            mv.addObject("exercise22", userCalenderDtoList.get(count++).exerciseTime);
            mv.addObject("exercise23", userCalenderDtoList.get(count++).exerciseTime);
            mv.addObject("exercise24", userCalenderDtoList.get(count++).exerciseTime);
            mv.addObject("exercise25", userCalenderDtoList.get(count++).exerciseTime);
            mv.addObject("exercise26", userCalenderDtoList.get(count++).exerciseTime);
            mv.addObject("exercise27", userCalenderDtoList.get(count++).exerciseTime);
            mv.addObject("exercise28", userCalenderDtoList.get(count++).exerciseTime);
            mv.addObject("exercise29", userCalenderDtoList.get(count++).exerciseTime);
            mv.addObject("exercise30", userCalenderDtoList.get(count++).exerciseTime);

            return mv;

        } catch(Exception e){
            e.printStackTrace();

            ModelAndView mv = new ModelAndView();
            mv.setViewName("userGraph/userExercise/getExerciseError_view");
            mv.addObject("error", e.getMessage());

            return mv;
        }
    }
}
