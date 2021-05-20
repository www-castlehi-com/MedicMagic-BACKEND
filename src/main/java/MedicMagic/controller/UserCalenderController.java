package MedicMagic.controller;

import MedicMagic.userCalender.domain.UserCalender;
import MedicMagic.userCalender.dto.UserCalenderDto;
import MedicMagic.userCalender.service.UserCalenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
public class UserCalenderController {
    private UserCalenderService userCalenderService;

    @RequestMapping(value = "/sendCalender_view", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView sendCalender(HttpServletRequest httpServletRequest) {
        try {
            String id = httpServletRequest.getParameter("id");
            String date = httpServletRequest.getParameter("date");
            Double weigh = Double.parseDouble(httpServletRequest.getParameter("weigh"));
            String sleepTime = httpServletRequest.getParameter("sleepTime");
            String exerciseTime = httpServletRequest.getParameter("exerciseTime");
            Double waterIntake = Double.parseDouble(httpServletRequest.getParameter("waterIntake"));
            String startDay = httpServletRequest.getParameter("startDay");
            String endDay = httpServletRequest.getParameter("endDay");
            String emotion = httpServletRequest.getParameter("emotion");
            String symptomString = httpServletRequest.getParameter("symptom");
            String mucusString = httpServletRequest.getParameter("mucus");
            Boolean symptom;
            Boolean mucus;

            if(symptomString == "true") {
                symptom = true;
            } else {
                symptom = false;
            }

            if(mucusString == "true") {
                mucus = true;
            }else {
                mucus = false;
            }

            if(sleepTime == "null") {sleepTime = null;}
            if(exerciseTime == "null") {exerciseTime=null;}
            if(startDay == "null") {startDay = null;}
            if(endDay == "null") {endDay = null;}
            if(emotion == "null") {emotion = null;}

            if(userCalenderService.getCountEachIdAndDate(id, date) == 0) {
                userCalenderService.add(new UserCalender(id, date, weigh, sleepTime, exerciseTime, waterIntake, startDay, endDay, emotion, symptom, mucus));
            }
            else {
                userCalenderService.update(new UserCalender(id, date, weigh, sleepTime, exerciseTime, waterIntake, startDay, endDay, emotion, symptom, mucus));
            }

            ModelAndView mv = new ModelAndView();
            mv.setViewName("userCalender/sendCalender_view");
            mv.addObject("id", id);
            mv.addObject("date", date);

            return mv;

        } catch(Exception e) {
            e.printStackTrace();

            ModelAndView mv = new ModelAndView();
            mv.setViewName("userCalender/sendCalenderError_view");
            mv.addObject("error", e.getMessage());

            return mv;
        }
    }

    @RequestMapping(value = "/getCalender_view", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView getCalender(HttpServletRequest httpServletRequest) {
        System.out.println("Server requested Android");
        try {
            String id = httpServletRequest.getParameter("id");
            String date = httpServletRequest.getParameter("date");
            System.out.println("ID from Android : "+id);
            System.out.println("DATE from Android : " + date);

            UserCalenderDto userCalenderDto = new UserCalenderDto(userCalenderService.get(id, date));

            ModelAndView mv = new ModelAndView();
            mv.setViewName("userCalender/getCalender_view");
            mv.addObject("weigh", userCalenderDto.weigh);

            String sleepTime;
            if(userCalenderDto.sleepTime == null) {
                sleepTime = "null";
            } else {
                sleepTime = userCalenderDto.sleepTime;
            }

            mv.addObject("sleepTime", sleepTime);

            String exerciseTime;
            if(userCalenderDto.exerciseTime == null) {
                exerciseTime = "null";
            } else {
                exerciseTime = userCalenderDto.exerciseTime;
            }

            mv.addObject("exerciseTime", exerciseTime);
            mv.addObject("waterIntake", userCalenderDto.waterIntake);

            String startDay;
            if(userCalenderDto.startDay == null) {
                startDay = "null";
            }else {
                startDay = userCalenderDto.startDay;
            }

            mv.addObject("startDay", userCalenderDto.startDay);

            String endDay;
            if(userCalenderDto.endDay == null) {
                endDay = "null";
            } else {
                endDay = userCalenderDto.endDay;
            }

            mv.addObject("endDay", userCalenderDto.endDay);

            String emotion;
            if(userCalenderDto.emotion == null) {
                emotion = "null";
            } else {
                emotion = userCalenderDto.emotion;
            }

            mv.addObject("emotion", userCalenderDto.emotion);

            String symptom;
            if(userCalenderDto.symptom == true) {
                symptom = "true";
            } else {
                symptom = "false";
            }

            mv.addObject("symptom", symptom);

            String mucus;
            if(userCalenderDto.mucus == true) {
                mucus = "true";
            } else {
                mucus = "false";
            }

            mv.addObject("mucus", mucus);

            return mv;

        } catch(Exception e) {
            e.printStackTrace();

            ModelAndView mv = new ModelAndView();
            mv.setViewName("userCalender/getCalenderError_view");
            mv.addObject("error", e.getMessage());

            return mv;
        }
    }
}
