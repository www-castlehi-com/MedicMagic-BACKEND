package MedicMagic.controller.UserReminder;

import MedicMagic.userReminder.userReminderPhysiology.dto.UserReminderPhysiologyDto;
import MedicMagic.userReminder.userReminderPhysiology.service.UserReminderPhysiologyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
public class UserReminderPhysiologyController {
    private final UserReminderPhysiologyService userReminderPhysiologyService;

    @RequestMapping(value = "/setUserReminderPhysiology_view", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView setUserReminderPhysiology(HttpServletRequest httpServletRequest) {
        try {
            String id = httpServletRequest.getParameter("id");
            String physiologyTime = httpServletRequest.getParameter("physiologyTime");

            UserReminderPhysiologyDto userReminderPhysiologyDto = new UserReminderPhysiologyDto(id, physiologyTime);
            userReminderPhysiologyService.update(userReminderPhysiologyDto);

            ModelAndView mv = new ModelAndView();
            mv.setViewName("userReminder/userReminderPhysiology/setUserReminderPhysiology_view");
            mv.addObject("id", id);

            return mv;
        } catch(Exception e) {
            e.printStackTrace();

            ModelAndView mv = new ModelAndView();
            mv.setViewName("userReminder/userReminderPhysiology/setUserReminderPhysiologyError_view");
            mv.addObject("error", e.getMessage());

            return mv;
        }
    }

    @RequestMapping(value = "/getUserReminderPhysiology_view", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView getUserReminderPhysiology(HttpServletRequest httpServletRequest) {
        try {
            String id = httpServletRequest.getParameter("id");

            UserReminderPhysiologyDto userReminderPhysiologyDto = userReminderPhysiologyService.get(id);

            ModelAndView mv = new ModelAndView();
            mv.setViewName("userReminder/userReminderPhysiology/getUserReminderPhysiology_view");
            mv.addObject("id", id);
            mv.addObject("physiologyTime", userReminderPhysiologyDto.physiologyTime);

            return mv;
        } catch(Exception e ){
            e.printStackTrace();

            ModelAndView mv = new ModelAndView();
            mv.setViewName("userReminder/userReminderPhysiology/getUserReminderPhysiologyError_view");
            mv.addObject("error", e.getMessage());

            return mv;
        }
    }
}
