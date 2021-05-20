package MedicMagic.controller;

import MedicMagic.userMucus.domain.UserMucus;
import MedicMagic.userMucus.dto.UserMucusDto;
import MedicMagic.userMucus.service.UserMucusService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
public class UserMucusController {
    private UserMucusService userMucusService;

    @RequestMapping(value = "/sendMucus_view", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView sendMucus(HttpServletRequest httpServletRequest) {
        try {
            String id = httpServletRequest.getParameter("id");
            String date = httpServletRequest.getParameter("date");
            boolean none;
            if(httpServletRequest.getParameter("none") == "true") {
                none = true;
            } else {
                none = false;
            }
            boolean mottled;
            if(httpServletRequest.getParameter("mottled") == "true") {
                mottled = true;
            } else {
                mottled = false;
            }
            boolean sticky;
            if(httpServletRequest.getParameter("sticky") == "true") {
                sticky = true;
            } else {
                sticky = false;
            }
            boolean creamy;
            if(httpServletRequest.getParameter("creamy") == "true") {
                creamy = true;
            } else {
                creamy = false;
            }
            boolean likeEggWhite;
            if(httpServletRequest.getParameter("likeEggWhite") == "true") {
                likeEggWhite = true;
            } else {
                likeEggWhite = false;
            }
            boolean watery;
            if(httpServletRequest.getParameter("watery") == "true") {
                watery = true;
            } else {
                watery = false;
            }
            boolean abnormal;
            if(httpServletRequest.getParameter("abnormal") == "true") {
                abnormal = true;
            } else {
                abnormal = false;
            }

            if(userMucusService.getCountEachIdAndDate(id, date) == 0) {
                userMucusService.add(new UserMucus(id, date, none, mottled, sticky, creamy, likeEggWhite, watery, abnormal));
            } else {
                userMucusService.update(new UserMucus(id, date, none, mottled, sticky, creamy, likeEggWhite, watery, abnormal));
            }

            ModelAndView mv = new ModelAndView();
            mv.setViewName("userMucus/sendMucus_view");
            mv.addObject("id", id);
            mv.addObject("date", date);

            return mv;

        } catch(Exception e) {
            e.printStackTrace();

            ModelAndView mv = new ModelAndView();
            mv.setViewName("userMucus/sendMucusError_view");
            mv.addObject("error", e.getMessage());

            return mv;
        }
    }

    @RequestMapping(value = "/getMucus_view", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView getMucus(HttpServletRequest httpServletRequest) {
        try {
            String id = httpServletRequest.getParameter("id");
            String date = httpServletRequest.getParameter("date");

            UserMucusDto userMucusDto = new UserMucusDto(userMucusService.get(id, date));

            ModelAndView mv = new ModelAndView();
            mv.setViewName("userMucus/getMucus_view");

            if(userMucusDto.none == true) {
                mv.addObject("none", "true");
            } else {
                mv.addObject("none", "false");
            }

            if(userMucusDto.mottled == true) {
                mv.addObject("mottled", "true");
            } else {
                mv.addObject("mottled", "false");
            }

            if(userMucusDto.sticky == true) {
                mv.addObject("sticky", "true");
            } else {
                mv.addObject("sticky", "false");
            }

            if(userMucusDto.creamy == true) {
                mv.addObject("creamy", "true");
            } else {
                mv.addObject("creamy", "false");
            }

            if(userMucusDto.likeEggWhite == true) {
                mv.addObject("likeEggWhite", "true");
            } else {
                mv.addObject("likeEggWhite", "false");
            }

            if(userMucusDto.watery == true) {
                mv.addObject("watery", "true");
            } else {
                mv.addObject("watery", "false");
            }

            if(userMucusDto.abnormal == true) {
                mv.addObject("abnormal", "true");
            } else {
                mv.addObject("abnormal", "false");
            }

            return mv;

        } catch(Exception e) {
            e.printStackTrace();

            ModelAndView mv = new ModelAndView();
            mv.setViewName("userMucus/getMucusError_view");
            mv.addObject("error", e.getMessage());

            return mv;
        }
    }
}
