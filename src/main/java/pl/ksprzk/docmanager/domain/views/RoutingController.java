package pl.ksprzk.docmanager.domain.views;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Przemysław Kasprzyk
 */
@Controller
public class RoutingController {

   @RequestMapping(path = {"/", "/index"})
   public String getMainPage() {
      return "/views/index.html";
   }

   @RequestMapping(path = "/login")
   public String getLoginPage() {
      return "/views/login.html";
   }

   @RequestMapping(path = "/controlpanel")
   public String getControlPanel() {
      return "/views/controlpanel.html";
   }

   @RequestMapping(path = "/publication")
   public String getPublicationView() {
      return "/views/publicationview.html";
   }
   
   @RequestMapping(path = "/mydocuments")
   public String getMyDocuments (){
      return "/views/mydocuments.html";
   }

   @RequestMapping(path = "/register")
   public String getRegistrationView() {
      return "/views/register.html";
   }
   
   @RequestMapping(path = {"/upload", "/docedit{id}"})
   public String getDocumentUploadView() {
      return "/views/docupload.html";
   }
}
