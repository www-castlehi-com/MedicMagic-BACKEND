package MedicMagic.controller;

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
public class UserPhysiologyController {
    private final UserPhysiologyService userPhysiologyService;

    @RequestMapping(value = "/getLast_view", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView getLast(HttpServletRequest httpServletRequest) {
        try {
            String id = httpServletRequest.getParameter("id");

            UserPhysiologyDto userPhysiologyDto = userPhysiologyService.getLastEachId(id);

            ModelAndView mv = new ModelAndView();
            mv.setViewName("userPhysiology/getLast_view");
            mv.addObject("startPhysiology", userPhysiologyDto.startPhysiology);
            mv.addObject("endPhysiology", userPhysiologyDto.endPhysiology);
            mv.addObject("expectedOvulationDate", userPhysiologyDto.expectedOvulationDate);
            mv.addObject("expectedPhysiologyDate", userPhysiologyDto.expectedPhysiologyDate);

            return mv;
        } catch(Exception e){
            e.printStackTrace();

            ModelAndView mv = new ModelAndView();
            mv.setViewName("userPhysiology/getLastError_view");
            mv.addObject("error", e.getMessage());

            return mv;
        }
    }

    @RequestMapping(value = "/getLimit3_view", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView getLimit3(HttpServletRequest httpServletRequest) {
        try {
            String id = httpServletRequest.getParameter("id");

            List<UserPhysiologyDto> userPhysiologyDtoList = userPhysiologyService.getEachIdLimit3(id);

            ModelAndView mv = new ModelAndView();
            mv.setViewName("userPhysiology/getLimit3_view");
            if(userPhysiologyDtoList.size() >= 1) {
                mv.addObject("startPhysiology1", userPhysiologyDtoList.get(0).startPhysiology);
                mv.addObject("endPhysiology1", userPhysiologyDtoList.get(0).endPhysiology);
                mv.addObject("expectedOvulationDate1", userPhysiologyDtoList.get(0).expectedOvulationDate);
                mv.addObject("expectedPhysiologyDate1", userPhysiologyDtoList.get(0).expectedPhysiologyDate);
            }
            if(userPhysiologyDtoList.size() >= 2) {
                mv.addObject("startPhysiology2", userPhysiologyDtoList.get(1).startPhysiology);
                mv.addObject("endPhysiology2", userPhysiologyDtoList.get(1).endPhysiology);
                mv.addObject("expectedOvulationDate2", userPhysiologyDtoList.get(1).expectedOvulationDate);
                mv.addObject("expectedPhysiologyDate2", userPhysiologyDtoList.get(1).expectedPhysiologyDate);
            }
            if(userPhysiologyDtoList.size() >= 3) {
                mv.addObject("startPhysiology3", userPhysiologyDtoList.get(2).startPhysiology);
                mv.addObject("endPhysiology3", userPhysiologyDtoList.get(2).endPhysiology);
                mv.addObject("expectedOvulationDate3", userPhysiologyDtoList.get(2).expectedOvulationDate);
                mv.addObject("expectedPhysiologyDate3", userPhysiologyDtoList.get(2).expectedPhysiologyDate);
            }

            return mv;
        } catch(Exception e){
            e.printStackTrace();

            ModelAndView mv = new ModelAndView();
            mv.setViewName("userPhysiology/getLimit3Error_view");
            mv.addObject("error", e.getMessage());

            return mv;
        }
    }
}
