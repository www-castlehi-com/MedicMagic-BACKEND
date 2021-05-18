package MedicMagic.controller;

import MedicMagic.userGoal.dto.UserGoalDto;
import MedicMagic.userReminder.dto.UserReminderDto;
import MedicMagic.userReminder.service.UserReminderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
public class UserReminderController {
    private final UserReminderService userReminderService;

    @RequestMapping(value="userReminder_view", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView userReminder(HttpServletRequest httpServletRequest) {
        System.out.println("Server requested Android");
        try {
            String id = httpServletRequest.getParameter("id");

            UserReminderDto userReminderDto = userReminderService.conveyUserReminder(id);

            ModelAndView mv = new ModelAndView();
            mv.setViewName("userReminder/userReminder_view");

            mv.addObject("birthControlPills", userReminderDto.birthControlPills);
            mv.addObject("beforeBirthControlPills", userReminderDto.beforeBirthControlPills);
            mv.addObject("birthControlPillsTime", userReminderDto.birthControlPillsTime);
            mv.addObject("physiology", userReminderDto.physiology);
            mv.addObject("beforePhysiology", userReminderDto.beforePhysiology);
            mv.addObject("sleepTimeGoal", userReminderDto.sleepTimeGoal);
            mv.addObject("exerciseTimeGoal", userReminderDto.exerciseTimeGoal);
            mv.addObject("hospital", userReminderDto.hospital);
            mv.addObject("hospitalDate", userReminderDto.hospitalDate);

            return mv;
        } catch (Exception e) {
            e.printStackTrace();

            ModelAndView mv = new ModelAndView();
            mv.setViewName("userReminder/userReminderError_view");
            mv.addObject("error", e.getMessage());

            return mv;
        }
    }
}
