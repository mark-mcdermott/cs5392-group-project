package modelCheckCTL.controller;


import modelCheckCTL.controller.ctl.ctlParser.CtlParser;
import modelCheckCTL.controller.ctl.ctlValidator.CtlValidator;
import modelCheckCTL.controller.ctl.ctlValidator.ParseException;
import modelCheckCTL.model.Model;
import modelCheckCTL.model.ModelCheckInputs;
import modelCheckCTL.model.kripke.Kripke;
import modelCheckCTL.model.kripke.State;
import modelCheckCTL.model.kripke.Transition;
import modelCheckCTL.view.View;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

import static java.lang.Integer.valueOf;
import static modelCheckCTL.util.Util.*;

public class Controller {

       private Model model;
       private View view;

       private String modelTestFilesDir = "modelTestFiles/";
       private List modelTestFiles = new ArrayList<String>(Arrays.asList("model1.txt","model2.txt","model3.txt","model4.txt","model5.txt","model6.txt","model7.txt"));

       public Controller(Model model, View view){
          this.model = model;
          this.view = view;
       }

       public Boolean doModelFileTestsPass(List filenames, Boolean testAllFiles) throws IOException, ParseException {
           // read resource file from class loader approach from https://mkyong.com/java/java-read-a-file-from-resources-folder/, accessed 9/18/21
           ClassLoader classLoader = getClass().getClassLoader();

           // use this to hard code a single model to check
           if (!testAllFiles) {
               String hardCodedModel = "EX(not p)";
               InputStream stringStream = new ByteArrayInputStream(hardCodedModel.getBytes("UTF-8"));
               CtlValidator ctlValidator = new CtlValidator(stringStream);
               ctlValidator.Validate();
               System.out.println("✅ model test passed: " + hardCodedModel);

               // use this to test every model in all the model files
           } else {

               int numFiles = 0;
               int numTests = 0;
               for (Object filenameObj : filenames) {
                   numFiles++;
                   String filename = (String) filenameObj;
                   try (InputStream inputStream = classLoader.getResourceAsStream(modelTestFilesDir + filename)) {
                       String ctlFormula = "";
                       if (inputStream == null) {
                           throw new IllegalArgumentException("file not found! " + filename);
                       } else {
                           // read input stream line by line approach from https://stackoverflow.com/a/55420102, accessed 9/18/21
                           try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                               String rawLine = reader.readLine();
                               while ((rawLine = reader.readLine()) != null) {
                                   numTests++;
                                   rawLine = rawLine.replaceAll("s\\d+;", "");
                                   rawLine = rawLine.replaceAll(";True", "");
                                   ctlFormula = rawLine.replaceAll(";False", "");
                                   ctlFormula = ctlFormula.replaceAll("\\ufeff", "");
                                   InputStream stringStream = new ByteArrayInputStream(ctlFormula.getBytes("UTF-8"));
                                   CtlValidator ctlValidator = new CtlValidator(stringStream);
                                   // System.out.println(filename + ": " + ctlFormula);
                                   ctlValidator.Validate();
                               }

                           } catch (ParseException e) {
                               e.printStackTrace();
                           }
                       }
                   }
               }
               System.out.println("✅ All model tests passed (" + numTests + " models, " + numFiles + " files)");
           }


           return true;
       }

       public String parseCtlFormulaFile(String filename) throws IOException {

           // read resource file from class loader approach from https://mkyong.com/java/java-read-a-file-from-resources-folder/, accessed 9/18/21
           ClassLoader classLoader = getClass().getClassLoader();
           InputStream inputStream = classLoader.getResourceAsStream(filename);
           String ctlFormula = "";
           if (inputStream == null) {
               throw new IllegalArgumentException("file not found! " + filename);
           } else {
               // read input stream line by line approach from https://stackoverflow.com/a/55420102, accessed 9/18/21
               try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                   ctlFormula = reader.readLine();
               }
           }
           return ctlFormula;
       }




       public Kripke parseKripkeFile(String filename) {
            // read resource file from class loader approach from https://mkyong.com/java/java-read-a-file-from-resources-folder/, accessed 9/18/21
            ClassLoader classLoader = getClass().getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream(filename);
            if (inputStream == null) {
                throw new IllegalArgumentException("file not found! " + filename);
            } else {
                // read input stream line by line approach from https://stackoverflow.com/a/55420102, accessed 9/18/21
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                    Kripke kripke = new Kripke();
                    Set states = new HashSet<State>();
                    Set transitions = new HashSet<Transition>();
                    int lineNum = 1;
                    while (reader.ready()) {
                        String line = reader.readLine();
                        if (lineNum == 1) {
                            // System.out.println(line);
                            states = parseKripkeStates(line);
                            // printStates(states);
                            kripke.setStates(states);
                        } else {
                            char firstChar = line.charAt(0);
                            char secondChar = line.charAt(1);
                            Integer elemNum = Character.getNumericValue(secondChar);
                            if (firstChar == 't') {           // transition line
                                Transition transition = parseKripkeTransitionLine(line);
                                transitions.add(transition);
                            } else if (firstChar == 's') {    // labels line

                                Set labels = new HashSet<Character>();
                                line = line.substring(5); // remove everything in the front (ie, "s1 : ") so only the labels are left (ie, "q t r,")
                                line = line.substring(0,line.length() - 1); // remove trailing comma or semicolon

                                while (line.length() > 0) {
                                    Character label = line.charAt(0);
                                    labels.add(label);
                                    if (line.length() > 2) {
                                        line = line.substring(2); // remove first char and the space after it
                                    } else {
                                        line = "";
                                    }
                                }
                                getState(elemNum,kripke.getStates()).setLabels(labels);
                            }
                        }
                        lineNum++;
                    }
                    kripke.setTransitions(transitions);
                    // System.out.println(kripke);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return new Kripke();
       }

       private static Set parseKripkeStates(String line) {
           Set states = new HashSet<State>();
           while (line.length() > 0) {
               String firstTwoChars = line.substring(0,2);
                if (isStateName(firstTwoChars)) {
                    Integer stateNum = valueOf(firstTwoChars.substring(1,2));
                    State state = new State(stateNum);
                    states.add(state);
                    if (line.length() > 3) {
                        line = line.substring(4);
                    } else {
                        line = "";
                    }
                }
            }
           return states;
        }

        private static Transition parseKripkeTransitionLine(String line) {
            char secondChar = line.charAt(1);
            Integer elemNum = Character.getNumericValue(secondChar);
            Integer fromNum = Integer.parseInt(line.substring(6,7));
            Integer toNum = Integer.parseInt(line.substring(11,12));
            State fromState = new State(fromNum);
            State toState = new State(toNum);
            return new Transition(elemNum, fromState, toState);
        }

        public void runCtlParser (ModelCheckInputs modelCheckInputs) {
           // TODO
        }

        public Set SAT(List Φ) {
            // TODO
            return new HashSet<String>();
        }

        public void runTests() throws IOException, ParseException {
           doModelFileTestsPass(modelTestFiles, true);
        }

        public void validateModel(String modelStr, Boolean useFileForModelInput) throws ParseException, IOException {

            // use this to use 2nd argument as filename where model to test is located
            if (useFileForModelInput) {
                String ctlFormula = parseCtlFormulaFile(modelStr);
                InputStream inputStream = new ByteArrayInputStream(ctlFormula.getBytes("UTF-8"));
                CtlValidator ctlValidator = new CtlValidator(inputStream);
                ctlValidator.Validate();
                System.out.println("✅ Model " + ctlFormula + " is well formed (" + modelStr + ")");

            // use this to use 2nd argument as model string to test
            } else {
                String ctlFormula = modelStr;
                InputStream stringStream = new ByteArrayInputStream(ctlFormula.getBytes("UTF-8"));
                CtlValidator ctlValidator = new CtlValidator(stringStream);
                ctlValidator.Validate();
                System.out.println("✅ Model " + ctlFormula + " is well formed (command line argument)");
            }
        }

        public void checkModel(String args[], Boolean useFileForModelInput) throws IOException, ParseException {
            checkArgs(args);
            setArgsToModel(args);
            Kripke kripke = parseKripkeFile(getKripkeFile());
            model.setKripke(kripke);
            validateModel(args[1], useFileForModelInput);

            runCtlParser(new ModelCheckInputs()); // TODO replace param stub with actual model check inputs

        }





    /**
     * Check if command line arguments have been supplied correctly
     * First argument should be kripke file, second should be ctl formula, third (optional) should be state to check.");
     * @param args
     * @throws IOException
     */
    public void checkArgs(String[] args) throws IOException {
            if (args == null) {
                throw new FileNotFoundException("No file arguments supplied. First argument should be kripke file, second should be ctl formula, third (optional) should be state to check.");
            } else if (args.length < 2 || args[1] == null) {
                throw new FileNotFoundException("No ctl formula argument supplied. First argument should be kripke file, second should be ctl formula, third (optional) should be state to check.");
            }
            if (args.length > 2 && args[2] != null) {
               if (!isStateName(args[2])) {
                   throw new IOException("Invalid state name in third argument - must be the letter \"s\" followed by an integer");
               }
           }
        }

    /**
     * Sets the kripke filename, the ctl formula and (if supplied) the state to check in the model
     * @param args
     * @throws IOException
     */
    public void setArgsToModel(String[] args) throws IOException {
           model.setKripkeFile(args[0]);
           model.setCtlFormula(args[1]);
           if (args.length > 2 && args[2] != null) {
               if (isStateName(args[2])) {
                   model.setStateToCheck(args[2]);
               }
           }
        }

        public String getKripkeFile() {
           return model.getKripkeFile();
       }

        public String getCtlFormula() {
           return model.getCtlFormula();
       }


        public void updateView(){
          view.printCourseDetails(model.getKripkeFile(), model.getCtlFormula());
       }

}
