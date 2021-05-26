package MedicMagic.controller;

import MedicMagic.userSymptom.domain.UserSymptom;
import MedicMagic.userSymptom.dto.UserSymptomDto;
import MedicMagic.userSymptom.service.UserSymptomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
public class UserSymptomController {
    private final UserSymptomService userSymptomService;

    @RequestMapping(value = "/sendSymptom_view", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView sendSymptom(HttpServletRequest httpServletRequest) {
        try {
            String id = httpServletRequest.getParameter("id");
            String date = httpServletRequest.getParameter("date");
            String none = httpServletRequest.getParameter("none");
            String cramps = httpServletRequest.getParameter("cramps");
            String breastTenderness = httpServletRequest.getParameter("breastTenderness");
            String headache = httpServletRequest.getParameter("headache");
            String acne = httpServletRequest.getParameter("acne");
            String lumbago = httpServletRequest.getParameter("lumbago");
            String nausea = httpServletRequest.getParameter("nausea");
            String fatigue = httpServletRequest.getParameter("fatigue");
            String abdominalBloating = httpServletRequest.getParameter("abdominalBloating");
            String desires = httpServletRequest.getParameter("desires");
            String insomnia = httpServletRequest.getParameter("insomnia");
            String constipation = httpServletRequest.getParameter("constipation");
            String diarrhea = httpServletRequest.getParameter("diarrhea");

            UserSymptomDto userSymptomDto = new UserSymptomDto(id, date, none, cramps, breastTenderness, headache, acne, lumbago, nausea, fatigue, abdominalBloating, desires, insomnia, constipation, diarrhea);
            userSymptomService.update(userSymptomDto);

            ModelAndView mv = new ModelAndView();
            mv.setViewName("userSymptom/sendSymptom_view");
            mv.addObject("id", id);
            mv.addObject("date", date);

            return mv;
        } catch(Exception e){
            e.printStackTrace();

            ModelAndView mv = new ModelAndView();
            mv.setViewName("userSymptom/sendSymptomError_view");
            mv.addObject("error", e.getMessage());

            return mv;
        }
    }

    @RequestMapping(value = "/getSymptom_view", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView getSymptom (HttpServletRequest httpServletRequest) {
        try {
            String id = httpServletRequest.getParameter("id");
            String date = httpServletRequest.getParameter("date");

            UserSymptomDto userSymptomDto = userSymptomService.get(id, date);
            ModelAndView mv = new ModelAndView();
            mv.setViewName("userSymptom/getSymptom_view");
            mv.addObject("none", userSymptomDto.none);
            mv.addObject("cramps", userSymptomDto.cramps);
            mv.addObject("breastTenderness", userSymptomDto.breastTenderness);
            mv.addObject("headache", userSymptomDto.headache);
            mv.addObject("acne", userSymptomDto.acne);
            mv.addObject("lumbago", userSymptomDto.lumbago);
            mv.addObject("nausea", userSymptomDto.nausea);
            mv.addObject("fatigue", userSymptomDto.fatigue);
            mv.addObject("abdominalBloating", userSymptomDto.abdominalBloating);
            mv.addObject("desires", userSymptomDto.desires);
            mv.addObject("insomnia", userSymptomDto.insomnia);
            mv.addObject("constipation", userSymptomDto.constipation);
            mv.addObject("diarrhea", userSymptomDto.diarrhea);

            return mv;
        } catch(Exception e) {
            e.printStackTrace();

            ModelAndView mv = new ModelAndView();
            mv.setViewName("userSymptom/getSymptomError_view");
            mv.addObject("error", e.getMessage());

            return mv;
        }
    }
}
