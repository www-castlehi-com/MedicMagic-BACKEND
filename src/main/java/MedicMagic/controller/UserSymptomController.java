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
    private UserSymptomService userSymptomService;

    @RequestMapping(value = "/sendSymptom_view", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView sendSymptom(HttpServletRequest httpServletRequest) {
        try {
            String id = httpServletRequest.getParameter("id");
            String date = httpServletRequest.getParameter("date");
            String noneString = httpServletRequest.getParameter("none");
            String crampsString = httpServletRequest.getParameter("cramps");
            String breathTendernessString = httpServletRequest.getParameter("breastTenderness");
            String headacheString = httpServletRequest.getParameter("headache");
            String acneString = httpServletRequest.getParameter("acne");
            String lumbagoString = httpServletRequest.getParameter("lumbago");
            String nauseaString = httpServletRequest.getParameter("nausea");
            String fatigueString = httpServletRequest.getParameter("fatigue");
            String abdominalBloatingString = httpServletRequest.getParameter("abdominalBloating");
            String desiresString = httpServletRequest.getParameter("desires");
            String insomniaString = httpServletRequest.getParameter("insomnia");
            String constipationString = httpServletRequest.getParameter("constipation");
            String diarrheaString = httpServletRequest.getParameter("diarrhea");

            boolean none;
            if(noneString == "true") {none = true;}
            else {none = false;}

            boolean cramps;
            if(crampsString == "true") {cramps = true; }
            else {cramps = false;}

            boolean breastTenderness;
            if(breathTendernessString == "true") {breastTenderness = true;}
            else {breastTenderness = false;}

            boolean headache;
            if(headacheString == "true") {headache = true;}
            else {headache = false;}

            boolean acne;
            if(acneString == "true") {acne = true;}
            else {acne = false;}

            boolean lumbago;
            if(lumbagoString == "true") {lumbago = true;}
            else {lumbago = false;}

            boolean nausea;
            if(nauseaString == "true") {nausea = true;}
            else {nausea = false;}

            boolean fatigue;
            if(fatigueString == "true") {fatigue = true;}
            else {fatigue = false;}

            boolean abdominalBloating;
            if(abdominalBloatingString == "true") {abdominalBloating = true;}
            else{abdominalBloating=false;}

            boolean desires;
            if(desiresString == "true") {desires = true;}
            else{desires = false;}

            boolean insomina;
            if(insomniaString == "true") {insomina = true;}
            else{insomina = false;}

            boolean constipation;
            if(constipationString == "true") {constipation = true;}
            else {constipation = false;}

            boolean diarrhea;
            if(diarrheaString == "true") {diarrhea = true;}
            else{diarrhea = false;}

            if(userSymptomService.getCountEachIdAndDate(id, date) == 0) {
                userSymptomService.add(new UserSymptom(id, date, none, cramps, breastTenderness, headache, acne, lumbago, nausea, fatigue, abdominalBloating, desires,insomina, constipation, diarrhea));
            }
            else {
                userSymptomService.update(new UserSymptom(id, date, none, cramps, breastTenderness, headache, acne, lumbago, nausea, fatigue, abdominalBloating, desires, insomina, constipation, diarrhea));
            }

            ModelAndView mv = new ModelAndView();
            mv.setViewName("userSymptom/sendSymptom_view");
            mv.addObject("id", id);
            mv.addObject("date", date);

            return mv;

        } catch(Exception e) {
            e.printStackTrace();

            ModelAndView mv = new ModelAndView();
            mv.setViewName("userSymptom/sendSymptomError_view");
            mv.addObject("error", e.getMessage());

            return mv;
        }
    }

    @RequestMapping(value = "/getSymptom_view", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView getSymptom(HttpServletRequest httpServletRequest) {
        try {
            String id = httpServletRequest.getParameter("id");
            String date = httpServletRequest.getParameter("date");

            UserSymptomDto userSymptomDto = new UserSymptomDto(userSymptomService.get(id, date));

            ModelAndView mv = new ModelAndView();
            mv.setViewName("userSymptom/getSymptom_view");

            if(userSymptomDto.none == true) {
                mv.addObject("none", "true");
            } else {
                mv.addObject("none", "false");
            }

            if(userSymptomDto.cramps == true) {
                mv.addObject("cramps", "true");
            } else {
                mv.addObject("cramps", "false");
            }

            if(userSymptomDto.breastTenderness == true) {
                mv.addObject("breastTenderness", "true");
            } else {
                mv.addObject("breastTenderness", "false");
            }

            if(userSymptomDto.headache == true) {
                mv.addObject("headache", "true");
            } else {
                mv.addObject("headache", "false");
            }

            if(userSymptomDto.acne == true) {
                mv.addObject("acne", "true");
            } else {
                mv.addObject("acne", "false");
            }

            if(userSymptomDto.lumbago == true) {
                mv.addObject("lumbago", "true");
            } else {
                mv.addObject("lumbago", "false");
            }

            if(userSymptomDto.nausea == true) {
                mv.addObject("nausea", "true");
            } else {
                mv.addObject("nausea", "false");
            }

            if(userSymptomDto.fatigue == true) {
                mv.addObject("fatigue", "true");
            } else {
                mv.addObject("fatigue", "false");
            }

            if(userSymptomDto.abdominalBloating == true) {
                mv.addObject("abdominalBloating", "true");
            } else {
                mv.addObject("abdominalBloating", "false");
            }

            if(userSymptomDto.desires == true) {
                mv.addObject("desires", "true");
            } else {
                mv.addObject("desires", "false");
            }

            if(userSymptomDto.insomnia == true) {
                mv.addObject("insomnia", "true");
            } else {
                mv.addObject("insomnia", "false");
            }

            if(userSymptomDto.constipation == true) {
                mv.addObject("constipation", "true");
            } else {
                mv.addObject("constipation", "false");
            }

            if(userSymptomDto.diarrhea == true) {
                mv.addObject("diarrhea", "true");
            } else {
                mv.addObject("diarrhea", "false");
            }

            return mv;

        } catch (Exception e) {
            e.printStackTrace();

            ModelAndView mv = new ModelAndView();
            mv.setViewName("userSymptom/getSymptomError_view");
            mv.addObject("error", e.getMessage());

            return mv;
        }
    }
}
