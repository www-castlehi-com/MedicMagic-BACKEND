package MedicMagic.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
public class HomeController {
    @RequestMapping(value = "/android", method={RequestMethod.POST, RequestMethod.GET})
    public ModelAndView androidPage(HttpServletRequest request) {
        System.out.println("Server requested Android");
        try {
            String androidID = request.getParameter("id");
            String androidPW = request.getParameter("pw");
            System.out.println("ID from Android : " + androidID);
            System.out.println("PW from Android : " + androidPW);
            ModelAndView mv = new ModelAndView();

            mv.addObject("android", androidID);

            mv.setViewName("android_view");

            return mv;
        } catch(Exception e) {
            e.printStackTrace();

            return null;
        }
    }
}
