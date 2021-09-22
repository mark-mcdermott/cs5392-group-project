package modelCheckCTL;

import modelCheckCTL.controller.Controller;
import modelCheckCTL.model.Model;
import modelCheckCTL.view.View;

import java.io.IOException;
import java.text.ParseException;

public class Main {

    public static void main(String[] args) throws IOException, ParseException, modelCheckCTL.controller.ctl.ctlParser.ParseException, modelCheckCTL.controller.ctl.ctlValidator.ParseException {

        // options
        Boolean useFileForModelInput = true; // 2nd command line argument - true: arg is filename with model in it, false: arg is hardcoded model string to check

        // setup main program (MVC structure based off https://www.edureka.co/blog/mvc-architecture-in-java/)
        Model model = new Model();
        View view = new View();
        Controller controller = new Controller(model, view);

        // run main program
        controller.runValidationTestsTests();
        controller.checkModel(args, useFileForModelInput);
        controller.updateView();
   }

}
