package MedicMagic.controller;

import MedicMagic.userCalender.domain.UserCalender;
import MedicMagic.userCalender.dto.UserCalenderDto;
import MedicMagic.userCalender.service.UserCalenderService;
import MedicMagic.userPhysiology.dto.UserPhysiologyDto;
import MedicMagic.userPhysiology.service.UserPhysiologyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserCalenderController {
    private final UserCalenderService userCalenderService;
    private final UserPhysiologyService userPhysiologyService;

    @RequestMapping(value = "/setUserCalender_view", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView setUserCalender(HttpServletRequest httpServletRequest) {
        try {
            String id = httpServletRequest.getParameter("id");
            String today_date = httpServletRequest.getParameter("today_date");

            UserCalenderDto userCalenderDto = userCalenderService.get(id,today_date);

            ModelAndView mv = new ModelAndView();
            mv.setViewName("userCalender/setUserCalender_view");
            mv.addObject("sleepTime", userCalenderDto.sleepTime);
            mv.addObject("exerciseTime", userCalenderDto.exerciseTime);
            mv.addObject("waterIntake", userCalenderDto.waterIntake);
            mv.addObject("startDay", userCalenderDto.startDay);
            mv.addObject("endDay", userCalenderDto.endDay);

            String month = today_date.split("-")[1];

            List<UserPhysiologyDto> userPhysiologyDto = userPhysiologyService.getEachIdAndMonth(id, month);
            if(userPhysiologyDto.size() >= 1) {
                mv.addObject("startPhysiology1", userPhysiologyDto.get(0).startPhysiology);
                mv.addObject("endPhysiology1", userPhysiologyDto.get(0).endPhysiology);
            }
            if(userPhysiologyDto.size() >= 2) {
                mv.addObject("startPhysiology2", userPhysiologyDto.get(1).startPhysiology);
                mv.addObject("endPhysiology2", userPhysiologyDto.get(1).endPhysiology);
            }
            if(userPhysiologyDto.size() >= 3) {
                mv.addObject("startPhysiology3", userPhysiologyDto.get(2).startPhysiology);
                mv.addObject("endPhysiology3", userPhysiologyDto.get(2).endPhysiology);
            }

            return mv;

        } catch(Exception e) {
            e.printStackTrace();

            ModelAndView mv = new ModelAndView();
            mv.setViewName("userCalender/setUserCalenderError_view");
            mv.addObject("error", e.getMessage());

            return mv;
        }
    }

    @RequestMapping(value="/getUserCalender_view", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView getUserCalender(HttpServletRequest httpServletRequest) {
        try {
            String id = httpServletRequest.getParameter("id");
            String today_date = httpServletRequest.getParameter("today_date");
            String sleepTime = httpServletRequest.getParameter("sleepTime");
            String exerciseTime = httpServletRequest.getParameter("exerciseTime");
            String waterIntake = httpServletRequest.getParameter("waterIntake");
            String startDay = httpServletRequest.getParameter("startDay");
            String endDay = httpServletRequest.getParameter("endDay");

            UserCalenderDto userCalenderDto = new UserCalenderDto(id, today_date, sleepTime, exerciseTime, waterIntake, startDay, endDay);
            userCalenderService.update(userCalenderDto);

            ModelAndView mv = new ModelAndView();
            mv.setViewName("userCalender/getUserCalender_view");
            mv.addObject("id", id);
            mv.addObject("today_date", today_date);

            return mv;
        } catch(Exception e){
            e.printStackTrace();

            ModelAndView mv = new ModelAndView();
            mv.setViewName("userCalender/getUserCalenderError_view");
            mv.addObject("error", e.getMessage());

            return mv;
        }
    }

    @RequestMapping(value = "/getDate_view", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView getDate(HttpServletRequest httpServletRequest) {
        try {
            String id = httpServletRequest.getParameter("id");
            String selected_date = httpServletRequest.getParameter("selected_date");

            UserCalenderDto userCalenderDto = userCalenderService.get(id, selected_date);

            ModelAndView mv = new ModelAndView();
            mv.setViewName("userCalender/getDate_view");
            mv.addObject("sleepTime", userCalenderDto.sleepTime);
            mv.addObject("exerciseTime", userCalenderDto.exerciseTime);
            mv.addObject("waterIntake", userCalenderDto.waterIntake);
            mv.addObject("startDay", userCalenderDto.startDay);
            mv.addObject("endDay", userCalenderDto.endDay);

            return mv;
        } catch(Exception e) {
            e.printStackTrace();

            ModelAndView mv = new ModelAndView();
            mv.setViewName("userCalender/getDateError_view");
            mv.addObject("error", e.getMessage());

            return mv;
        }
    }

    @RequestMapping(value = "/getMonth_view", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView getMonth(HttpServletRequest httpServletRequest) {
        try {
            String id = httpServletRequest.getParameter("id");
            String month = httpServletRequest.getParameter("month");

            List<UserPhysiologyDto> userPhysiologyDto = userPhysiologyService.getEachIdAndMonth(id, month);
            ModelAndView mv = new ModelAndView();
            mv.setViewName("userCalender/getMonth_view");
            if(userPhysiologyDto.size() >= 1) {
                mv.addObject("startPhysiology1", userPhysiologyDto.get(0).startPhysiology);
                mv.addObject("endPhysiology1", userPhysiologyDto.get(0).endPhysiology);
            }
            if(userPhysiologyDto.size() >= 2) {
                mv.addObject("startPhysiology2", userPhysiologyDto.get(1).startPhysiology);
                mv.addObject("endPhysiology2", userPhysiologyDto.get(1).endPhysiology);
            }
            if(userPhysiologyDto.size() >= 3) {
                mv.addObject("startPhysiology3", userPhysiologyDto.get(2).startPhysiology);
                mv.addObject("endPhysiology3", userPhysiologyDto.get(2).endPhysiology);
            }

            return mv;

        } catch(Exception e){
            e.printStackTrace();

            ModelAndView mv = new ModelAndView();
            mv.setViewName("userCalender/getMonthError_view");
            mv.addObject("error", e.getMessage());

            return mv;
        }
    }
}
