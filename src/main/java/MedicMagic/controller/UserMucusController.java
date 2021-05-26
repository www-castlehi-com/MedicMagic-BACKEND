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
    private final UserMucusService userMucusService;

    @RequestMapping(value = "/sendMucus_view", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView sendMucus(HttpServletRequest httpServletRequest) {
        try {
            String id = httpServletRequest.getParameter("id");
            String date = httpServletRequest.getParameter("date");
            String none = httpServletRequest.getParameter("none");
            String mottled = httpServletRequest.getParameter("mottled");
            String sticky = httpServletRequest.getParameter("sticky");
            String creamy = httpServletRequest.getParameter("creamy");
            String likeEggWhite = httpServletRequest.getParameter("likeEggWhite");
            String watery = httpServletRequest.getParameter("watery");
            String abnormal = httpServletRequest.getParameter("abnormal");

            UserMucusDto userMucusDto = new UserMucusDto(id, date, none, mottled, sticky, creamy, likeEggWhite, watery, abnormal);
            userMucusService.update(userMucusDto);

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

    @RequestMapping(value="/getMucus_view", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView getMucus(HttpServletRequest httpServletRequest) {
        try {
            String id = httpServletRequest.getParameter("id");
            String date = httpServletRequest.getParameter("date");

            UserMucusDto userMucusDto = userMucusService.get(id, date);

            ModelAndView mv = new ModelAndView();
            mv.setViewName("userMucus/getMucus_view");
            mv.addObject("none", userMucusDto.none);
            mv.addObject("mottled", userMucusDto.mottled);
            mv.addObject("sticky", userMucusDto.sticky);
            mv.addObject("creamy", userMucusDto.creamy);
            mv.addObject("likeEggWhite", userMucusDto.likeEggWhite);
            mv.addObject("watery", userMucusDto.watery);
            mv.addObject("abnormal", userMucusDto.abnormal);

            return mv;
        } catch(Exception e){
            e.printStackTrace();

            ModelAndView mv = new ModelAndView();
            mv.setViewName("userMucus/getMucusError_view");
            mv.addObject("error", e.getMessage());

            return mv;
        }
    }
}
