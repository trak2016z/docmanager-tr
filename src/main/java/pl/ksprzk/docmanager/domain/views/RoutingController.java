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
   public ModelAndView getMainPage() {
      return new ModelAndView("/views/index.html");
   }

   @RequestMapping(path = "/login")
   public ModelAndView getLoginPage() {
      return new ModelAndView("/views/login.html");
   }

   @RequestMapping(path = "/controlpanel")
   public ModelAndView getControlPanel() {
      return new ModelAndView("/views/controlpanel.html");
   }

   @RequestMapping(path = "/publication")
   public ModelAndView getPublicationView() {
      return new ModelAndView("/views/publicationview.html");
   }

   @RequestMapping(path = "/register")
   public ModelAndView getRegistrationView() {
      return new ModelAndView("/views/register.html");
   }
}
