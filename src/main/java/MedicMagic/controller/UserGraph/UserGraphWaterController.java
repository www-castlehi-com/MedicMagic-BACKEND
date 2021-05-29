package MedicMagic.controller.UserGraph;

import MedicMagic.userCalender.dto.UserCalenderDto;
import MedicMagic.userCalender.service.UserCalenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserGraphWaterController {
    private final UserCalenderService userCalenderService;

    @RequestMapping(value="/getWater_view", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView getWater(HttpServletRequest httpServletRequest) {
        try {
            String id = httpServletRequest.getParameter("id");

            List<UserCalenderDto> userCalenderDtoList = userCalenderService.getEachId(id);
            int count;
            if(userCalenderDtoList.size() > 30) {
                count = userCalenderDtoList.size() - 30;
            } else {
                for(int i = userCalenderDtoList.size(); i <= 30; i++) {
                    userCalenderDtoList.add(new UserCalenderDto());
                }
                count = 0;
            }

            ModelAndView mv = new ModelAndView();
            mv.setViewName("userGraph/userWater/getWater_view");

            mv.addObject("water1", userCalenderDtoList.get(count++).waterIntake);
            mv.addObject("water2", userCalenderDtoList.get(count++).waterIntake);
            mv.addObject("water3", userCalenderDtoList.get(count++).waterIntake);
            mv.addObject("water4", userCalenderDtoList.get(count++).waterIntake);
            mv.addObject("water5", userCalenderDtoList.get(count++).waterIntake);
            mv.addObject("water6", userCalenderDtoList.get(count++).waterIntake);
            mv.addObject("water7", userCalenderDtoList.get(count++).waterIntake);
            mv.addObject("water8", userCalenderDtoList.get(count++).waterIntake);
            mv.addObject("water9", userCalenderDtoList.get(count++).waterIntake);
            mv.addObject("water10", userCalenderDtoList.get(count++).waterIntake);
            mv.addObject("water11", userCalenderDtoList.get(count++).waterIntake);
            mv.addObject("water12", userCalenderDtoList.get(count++).waterIntake);
            mv.addObject("water13", userCalenderDtoList.get(count++).waterIntake);
            mv.addObject("water14", userCalenderDtoList.get(count++).waterIntake);
            mv.addObject("water15", userCalenderDtoList.get(count++).waterIntake);
            mv.addObject("water16", userCalenderDtoList.get(count++).waterIntake);
            mv.addObject("water17", userCalenderDtoList.get(count++).waterIntake);
            mv.addObject("water18", userCalenderDtoList.get(count++).waterIntake);
            mv.addObject("water19", userCalenderDtoList.get(count++).waterIntake);
            mv.addObject("water20", userCalenderDtoList.get(count++).waterIntake);
            mv.addObject("water21", userCalenderDtoList.get(count++).waterIntake);
            mv.addObject("water22", userCalenderDtoList.get(count++).waterIntake);
            mv.addObject("water23", userCalenderDtoList.get(count++).waterIntake);
            mv.addObject("water24", userCalenderDtoList.get(count++).waterIntake);
            mv.addObject("water25", userCalenderDtoList.get(count++).waterIntake);
            mv.addObject("water26", userCalenderDtoList.get(count++).waterIntake);
            mv.addObject("water27", userCalenderDtoList.get(count++).waterIntake);
            mv.addObject("water28", userCalenderDtoList.get(count++).waterIntake);
            mv.addObject("water29", userCalenderDtoList.get(count++).waterIntake);
            mv.addObject("water30", userCalenderDtoList.get(count++).waterIntake);

            return mv;

        } catch(Exception e){
            e.printStackTrace();

            ModelAndView mv = new ModelAndView();
            mv.setViewName("userGraph/userWater/getWaterError_view");
            mv.addObject("error", e.getMessage());

            return mv;
        }
    }

}
