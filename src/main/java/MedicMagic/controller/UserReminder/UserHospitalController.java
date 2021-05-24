package MedicMagic.controller.UserReminder;

import MedicMagic.userReminder.userHospital.dto.UserHospitalDto;
import MedicMagic.userReminder.userHospital.service.UserHospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
public class UserHospitalController {
    private UserHospitalService userHospitalService;

    @RequestMapping(value = "/setUserHospital_view", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView setUserHospital(HttpServletRequest httpServletRequest) {
        try {
            String id = httpServletRequest.getParameter("id");
            String hospitalDate = httpServletRequest.getParameter("hospital_date");
            String hospitalTime = httpServletRequest.getParameter("hospital_time");

            UserHospitalDto userHospitalDto = new UserHospitalDto(id, hospitalDate, hospitalTime);
            userHospitalService.update(userHospitalDto);

            ModelAndView mv = new ModelAndView();
            mv.setViewName("userReminder/userHospital/setUserHospital_view");
            mv.addObject("id", id);

            return mv;
        } catch(Exception e) {
            e.printStackTrace();

            ModelAndView mv = new ModelAndView();
            mv.setViewName("userReminder/userHospital/setUserHospitalError_view");
            mv.addObject("error", e.getMessage());

            return mv;
        }
    }

    @RequestMapping(value = "/getUserHospital_view", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView getUserHospital(HttpServletRequest httpServletRequest) {
        try {
            String id = httpServletRequest.getParameter("id");

            UserHospitalDto userHospitalDto = userHospitalService.get(id);

            ModelAndView mv = new ModelAndView();
            mv.setViewName("userReminder/userHospital/getUserHospital_view");
            mv.addObject("id", id);
            mv.addObject("hospital_date", userHospitalDto.hospitalDate);
            mv.addObject("hospital_time", userHospitalDto.hospitalTime);

            return mv;
        } catch(Exception e ){
            e.printStackTrace();

            ModelAndView mv = new ModelAndView();
            mv.setViewName("userReminder/userHospital/getUserHospitalError_view");
            mv.addObject("error", e.getMessage());

            return mv;
        }
    }
}
