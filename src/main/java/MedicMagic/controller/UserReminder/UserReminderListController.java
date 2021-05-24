package MedicMagic.controller.UserReminder;

import MedicMagic.userReminder.userReminderList.dto.UserReminderListDto;
import MedicMagic.userReminder.userReminderList.service.UserReminderListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
public class UserReminderListController {
    private final UserReminderListService userReminderListService;

    @RequestMapping(value = "/setUserReminderList_view", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView setUserReminderList(HttpServletRequest httpServletRequest) {
        try {
            String id = httpServletRequest.getParameter("id");
            String birthControlPills = httpServletRequest.getParameter("birthControlPills");
            String physiology = httpServletRequest.getParameter("physiology");
            String hospital = httpServletRequest.getParameter("hospital");
            String water = httpServletRequest.getParameter("water");
            String exerciseTimeGoal = httpServletRequest.getParameter("exerciseTimeGoal");
            String sleepTimeGoal = httpServletRequest.getParameter("sleepTimeGoal");

            UserReminderListDto userReminderListDto = new UserReminderListDto(id, birthControlPills, physiology, hospital, water, exerciseTimeGoal, sleepTimeGoal);
            userReminderListService.update(userReminderListDto);

            ModelAndView mv = new ModelAndView();
            mv.setViewName("userReminder/userReminderList/setUserReminderList_view");
            mv.addObject("id", id);

            return mv;
        } catch(Exception e) {
            e.printStackTrace();

            ModelAndView mv = new ModelAndView();
            mv.setViewName("userReminder/userReminderList/setUserReminderListError_view");
            mv.addObject("error", e.getMessage());

            return mv;
        }
    }

    @RequestMapping(value = "/getUserReminderList_view", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView getUserReminderList (HttpServletRequest httpServletRequest) {
        try {
            String id = httpServletRequest.getParameter("id");

            UserReminderListDto userReminderListDto = userReminderListService.get(id);

            ModelAndView mv = new ModelAndView();
            mv.setViewName("userReminder/userReminderList/getUserReminderList_view");
            mv.addObject("id", id);
            mv.addObject("birthControlPills", userReminderListDto.birthControlPills);
            mv.addObject("physiology", userReminderListDto.physiology);
            mv.addObject("hospital", userReminderListDto.hospital);
            mv.addObject("water", userReminderListDto.waterIntake);
            mv.addObject("exerciseTimeGoal", userReminderListDto.exerciseTimeGoal);
            mv.addObject("sleepTimeGoal", userReminderListDto.sleepTimeGoal);

            return mv;
        } catch(Exception e) {
            e.printStackTrace();

            ModelAndView mv = new ModelAndView();
            mv.setViewName("userReminder/userReminderList/getUserReminderListError_view");
            mv.addObject("error", e.getMessage());

            return mv;
        }
    }
}
