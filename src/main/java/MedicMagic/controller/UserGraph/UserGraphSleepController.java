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
public class UserGraphSleepController {
    private final UserCalenderService userCalenderService;

    @RequestMapping(value="/getSleep_view", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView getSleep(HttpServletRequest httpServletRequest){
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
            mv.setViewName("userGraph/userSleep/getSleep_view");

            mv.addObject("sleep1", userCalenderDtoList.get(count++).sleepTime);
            mv.addObject("sleep2", userCalenderDtoList.get(count++).sleepTime);
            mv.addObject("sleep3", userCalenderDtoList.get(count++).sleepTime);
            mv.addObject("sleep4", userCalenderDtoList.get(count++).sleepTime);
            mv.addObject("sleep5", userCalenderDtoList.get(count++).sleepTime);
            mv.addObject("sleep6", userCalenderDtoList.get(count++).sleepTime);
            mv.addObject("sleep7", userCalenderDtoList.get(count++).sleepTime);
            mv.addObject("sleep8", userCalenderDtoList.get(count++).sleepTime);
            mv.addObject("sleep9", userCalenderDtoList.get(count++).sleepTime);
            mv.addObject("sleep10", userCalenderDtoList.get(count++).sleepTime);
            mv.addObject("sleep11", userCalenderDtoList.get(count++).sleepTime);
            mv.addObject("sleep12", userCalenderDtoList.get(count++).sleepTime);
            mv.addObject("sleep13", userCalenderDtoList.get(count++).sleepTime);
            mv.addObject("sleep14", userCalenderDtoList.get(count++).sleepTime);
            mv.addObject("sleep15", userCalenderDtoList.get(count++).sleepTime);
            mv.addObject("sleep16", userCalenderDtoList.get(count++).sleepTime);
            mv.addObject("sleep17", userCalenderDtoList.get(count++).sleepTime);
            mv.addObject("sleep18", userCalenderDtoList.get(count++).sleepTime);
            mv.addObject("sleep19", userCalenderDtoList.get(count++).sleepTime);
            mv.addObject("sleep20", userCalenderDtoList.get(count++).sleepTime);
            mv.addObject("sleep21", userCalenderDtoList.get(count++).sleepTime);
            mv.addObject("sleep22", userCalenderDtoList.get(count++).sleepTime);
            mv.addObject("sleep23", userCalenderDtoList.get(count++).sleepTime);
            mv.addObject("sleep24", userCalenderDtoList.get(count++).sleepTime);
            mv.addObject("sleep25", userCalenderDtoList.get(count++).sleepTime);
            mv.addObject("sleep26", userCalenderDtoList.get(count++).sleepTime);
            mv.addObject("sleep27", userCalenderDtoList.get(count++).sleepTime);
            mv.addObject("sleep28", userCalenderDtoList.get(count++).sleepTime);
            mv.addObject("sleep29", userCalenderDtoList.get(count++).sleepTime);
            mv.addObject("sleep30", userCalenderDtoList.get(count++).sleepTime);

            return mv;

        } catch(Exception e){
            e.printStackTrace();

            ModelAndView mv = new ModelAndView();
            mv.setViewName("userGraph/userSleep/getSleepError_view");
            mv.addObject("error", e.getMessage());

            return mv;
        }
    }
}
