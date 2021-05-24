package MedicMagic.controller.UserReminder;

import MedicMagic.userReminder.userBirthControlPills.dto.UserBirthControlPillsDto;
import MedicMagic.userReminder.userBirthControlPills.service.UserBirthControlPillsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
public class UserBirthControlPillsController {
    private final UserBirthControlPillsService userBirthControlPillsService;

    @RequestMapping(value = "/setUserBirthControlPills_view", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView setUserBirthControlPills(HttpServletRequest httpServletRequest) {
        try {
            String id = httpServletRequest.getParameter("id");
            String pillsTime = httpServletRequest.getParameter("pills_time");
            String pillsDate = httpServletRequest.getParameter("pills_date");
            String days = httpServletRequest.getParameter("days");

            UserBirthControlPillsDto userBirthControlPillsDto = new UserBirthControlPillsDto(id, pillsTime, pillsDate, days);
            userBirthControlPillsService.update(userBirthControlPillsDto);

            ModelAndView mv = new ModelAndView();
            mv.setViewName("userReminder/userBirthControlPills/setUserBirthControlPills_view");
            mv.addObject("id", id);

            return mv;

        } catch(Exception e) {
            e.printStackTrace();

            ModelAndView mv = new ModelAndView();
            mv.setViewName("userReminder/userBirthControlPills/setUserBirthControlPillsError_view");
            mv.addObject("error", e.getMessage());

            return mv;
        }
    }

    @RequestMapping(value = "/getUserBirthControlPills_view", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView getUserBirthControlPills(HttpServletRequest httpServletRequest) {
        try {
            String id = httpServletRequest.getParameter("id");

            UserBirthControlPillsDto userBirthControlPillsDto = userBirthControlPillsService.get(id);

            ModelAndView mv= new ModelAndView();
            mv.setViewName("userReminder/userBirthControlPills/getUserBirthControlPills_view");
            mv.addObject("id", id);
            mv.addObject("pills_time", userBirthControlPillsDto.pillsTime);
            mv.addObject("pills_date", userBirthControlPillsDto.pillsDate);
            mv.addObject("days", userBirthControlPillsDto.days);

            return mv;
        } catch(Exception e ){
            e.printStackTrace();

            ModelAndView mv = new ModelAndView();
            mv.setViewName("userReminder/userBirthControlPills/getUserBirthControlPillsError_view");
            mv.addObject("error", e.getMessage());

            return mv;
        }
    }
}
