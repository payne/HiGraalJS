import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;
import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.Invocable;
import java.io.IOException;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.*;
import org.graalvm.polyglot.proxy.*;


class Main {

          public static String getSource() {
       return  "var N = 2000;\n"
            + "var EXPECTED = 17393;\n"
            + "\n"
            + "function Natural() {\n"
            + "    x = 2;\n"
            + "    return {\n"
            + "        'next' : function() { return x++; }\n"
            + "    };\n"
            + "}\n"
            + "\n"
            + "function Filter(number, filter) {\n"
            + "    var self = this;\n"
            + "    this.number = number;\n"
            + "    this.filter = filter;\n"
            + "    this.accept = function(n) {\n"
            + "      var filter = self;\n"
            + "      for (;;) {\n"
            + "          if (n % filter.number === 0) {\n"
            + "              return false;\n"
            + "          }\n"
            + "          filter = filter.filter;\n"
            + "          if (filter === null) {\n"
            + "              break;\n"
            + "          }\n"
            + "      }\n"
            + "      return true;\n"
            + "    };\n"
            + "    return this;\n"
            + "}\n"
            + "\n"
            + "function Primes(natural) {\n"
            + "    var self = this;\n"
            + "    this.natural = natural;\n"
            + "    this.filter = null;\n"
            + "\n"
            + "    this.next = function() {\n"
            + "        for (;;) {\n"
            + "            var n = self.natural.next();\n"
            + "            if (self.filter === null || self.filter.accept(n)) {\n"
            + "                self.filter = new Filter(n, self.filter);\n"
            + "                return n;\n"
            + "            }\n"
            + "        }\n"
            + "    };\n"
            + "}\n"
            + "\n"
            + "function primesMain() {\n"
            + "    var primes = new Primes(Natural());\n"
            + "    var primArray = [];\n"
            + "    for (var i=0;i<=N;i++) { primArray.push(primes.next()); }\n"
            + "    if (primArray[N] != EXPECTED) { throw new Error('wrong prime found: '+primArray[N]); }\n"
            + "}\n";
          }
  
  public static void main(String[] args) {
    System.out.println("Hello world!");
  Object result = demo2("eggs");
    System.out.format("result is a %s and has value:\n%s\n", 
                     result.getClass().getName(), result);
    try { 
    run();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
public static void run() throws Exception {
    ScriptEngine graaljsEngine = new ScriptEngineManager().getEngineByName("graal.js");
        if (graaljsEngine == null) {
            System.out.println("*** Graal.js not found ***");
          // return 0;
        } 
        else {
          System.out.println("Engine might be present.");
            // return benchScriptEngineIntl(graaljsEngine);
          
          graaljsEngine.eval(getSource());
            Invocable inv = (Invocable) graaljsEngine;
            System.out.println("warming up ...");
      final int WARMUP=10;
            for (int i = 0; i < WARMUP; i++) {
                inv.invokeFunction("primesMain");
            }
        }
}


  static String JS_CODE = "(function myFun(param){console.log('hello '+param); return 'fun ' + param; })";

// Based on https://docs.oracle.com/en/graalvm/enterprise/20/docs/reference-manual/js/
    public static Object demo2(String arg) {
        System.out.println("Hello Java!");
        try (Context context = Context.create()) {
            Value value = context.eval("js", JS_CODE);
            Object result = value.execute(arg);
          System.out.println(result);
          return result.toString();
        }
    }

}