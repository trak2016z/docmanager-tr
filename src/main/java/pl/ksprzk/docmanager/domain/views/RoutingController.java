package pl.ksprzk.docmanager.domain.views;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Przemysław Kasprzyk
 */
@Controller
public class RoutingController {

   @RequestMapping(path = {"/", "/index"})
   public ModelAndView getMainPage (){
      return new ModelAndView("/views/index.html");
   }
}
