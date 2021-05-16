package MedicMagic.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
public class HomeController {
    @RequestMapping(value = "/android", method={RequestMethod.POST, RequestMethod.GET})
    public String androidPage(HttpServletRequest request, Model model) {
        System.out.println("Server requested Android");
        try {
            String androidID = request.getParameter("id");
            String androidPW = request.getParameter("pw");
            System.out.println("ID from Android : " + androidID);
            System.out.println("PW from Android : " + androidPW);

            model.addAttribute("android", androidID);

            return "android_view";
        } catch(Exception e) {
            e.printStackTrace();

            return null;
        }
    }
}
