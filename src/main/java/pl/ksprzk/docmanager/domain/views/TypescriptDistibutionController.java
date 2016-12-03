package pl.ksprzk.docmanager.domain.views;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Przemysław Kasprzyk
 */
@Controller
public class TypescriptDistibutionController {

   @RequestMapping(path = "/*/{sourcesLocalization}.ts")
   public String getSources(@PathVariable(name = "sourcesLocalization") String typescriptSources) {
      return "/scripts/" + typescriptSources + ".ts";
   }
}
