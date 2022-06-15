import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;
import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.Invocable;
import java.io.IOException;
import org.graalvm.polyglot.Source;

class Main {
  public static void main(String[] args) {
    System.out.println("Hello world!");
    ScriptEngine graaljsEngine = new ScriptEngineManager().getEngineByName("graal.js");
        if (graaljsEngine == null) {
            System.out.println("*** Graal.js not found ***");
          // return 0;
        } 
        else {
          System.out.println("Engine might be present.");
            // return benchScriptEngineIntl(graaljsEngine);
        }
          
  }
}