package MedicMagic.controller;

import MedicMagic.userGoal.dto.UserGoalDto;
import MedicMagic.userReminder.domain.UserReminder;
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

    @RequestMapping(value="/getReminder_view", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView userReminder(HttpServletRequest httpServletRequest) {
        try {
            String id = httpServletRequest.getParameter("id");

            UserReminderDto userReminderDto = userReminderService.conveyUserReminder(id);

            ModelAndView mv = new ModelAndView();
            mv.setViewName("userReminder/getReminder_view");

            if(userReminderDto.birthControlPills == true) {
                mv.addObject("birthControlPills", "true");
            } else {
                mv.addObject("birthControlPills", "false");
            }
            mv.addObject("beforeBirthControlPills", userReminderDto.beforeBirthControlPills.toString());
            mv.addObject("birthControlPillsTime", userReminderDto.birthControlPillsTime);
            if(userReminderDto.physiology == true) {
                mv.addObject("physiology", "true");
            } else {
                mv.addObject("physiology", "false");
            }
            mv.addObject("beforePhysiology", userReminderDto.beforePhysiology.toString());
            if(userReminderDto.sleepTimeGoal == true) {
                mv.addObject("sleepTimeGoal", "true");
            } else {
                mv.addObject("sleepTimeGoal",false);
            }
            if(userReminderDto.exerciseTimeGoal == true) {
                mv.addObject("exerciseTimeGoal", "true");
            } else {
                mv.addObject("exerciseTimeGoal", false);
            }
            if(userReminderDto.hospital == true) {
                mv.addObject("hospital", "true");
            } else {
                mv.addObject("hospital", "false");
            }
            mv.addObject("hospitalDate", userReminderDto.hospitalDate.toString());

            return mv;
        } catch (Exception e) {
            e.printStackTrace();

            ModelAndView mv = new ModelAndView();
            mv.setViewName("userReminder/getReminderError_view");
            mv.addObject("error", e.getMessage());

            return mv;
        }
    }

    @RequestMapping(value = "/setReminder_view", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView setReminder(HttpServletRequest httpServletRequest) {
        try {
            String id = httpServletRequest.getParameter("id");
            boolean birthControlPills;
            if(httpServletRequest.getParameter("birthControlPills") == "true") {
                birthControlPills = true;
            } else {
                birthControlPills = false;
            }
            Integer beforeBirthControlPills = Integer.parseInt(httpServletRequest.getParameter("beforeBirthControlPills"));
            String birthControlPillsTime = httpServletRequest.getParameter("birthControlPillsTime");
            boolean physiology;
            if(httpServletRequest.getParameter("physiology") == "true") {
                physiology = true;
            } else {
                physiology = false;
            }
            Integer beforePhysiology = Integer.parseInt(httpServletRequest.getParameter("beforePhysiology"));
            boolean sleepTimeGoal;
            if(httpServletRequest.getParameter("sleepTimeGoal") == "true") {
                sleepTimeGoal = true;
            } else {
                sleepTimeGoal = false;
            }
            boolean exerciseTimeGoal;
            if(httpServletRequest.getParameter("exerciseTimeGoal") == "true") {
                exerciseTimeGoal = true;
            } else {
                exerciseTimeGoal = false;
            }
            boolean hospital;
            if(httpServletRequest.getParameter("hospital") == "true") {
                hospital = true;
            } else {
                hospital = false;
            }
            Integer hospitalDate = Integer.parseInt(httpServletRequest.getParameter("hospitalDate"));

            userReminderService.update(new UserReminder(id, birthControlPills, beforeBirthControlPills, birthControlPillsTime, physiology, beforePhysiology, sleepTimeGoal, exerciseTimeGoal, hospital, hospitalDate));

            ModelAndView mv = new ModelAndView();
            mv.setViewName("userReminder/setReminder_view");
            mv.addObject("id", id);

            return mv;

        } catch(Exception e) {
            e.printStackTrace();

            ModelAndView mv = new ModelAndView();
            mv.setViewName("userReminder/setReminderError_view");
            mv.addObject("error", e.getMessage());

            return mv;
        }
    }
}
