/* CtlParser.java */
/* Generated By:JavaCC: Do not edit this line. CtlParser.java */
package modelCheckCTL.controller.ctl.ctlParser;

import java.util.Set;
import java.util.HashSet;
import modelCheckCTL.controller.ctl.ctlParser.ParseException;
import modelCheckCTL.model.ModelCheckInputs;
import modelCheckCTL.model.formulaObj.FormulaObj;
import static modelCheckCTL.controller.ctl.CtlUtils.statesWithLabel;
import static modelCheckCTL.controller.ctl.CtlUtils.union;
import static modelCheckCTL.controller.ctl.CtlUtils.intersection;
import static modelCheckCTL.controller.ctl.CtlUtils.subtract;
import modelCheckCTL.model.kripke.Kripke;

/* clt parser rules approach from https://github.com/pedrogongora/antelope/blob/master/AntelopeCore/src/antelope/ctl/parser/CTLParser.jj, accessed 9/20 */
public class CtlParser implements CtlParserConstants {
    Kripke kripke;
    String model;

    public CtlParser(ModelCheckInputs modelCheckInputs) {
          this(modelCheckInputs.getStream(), null); // dunno what this call does exactly, but it's needed to avoid JavaCC throwing a null error. Compare to the autogenerated constructor in CtlValidator.java line 168
          this.kripke = modelCheckInputs.getKripke();
          this.model = modelCheckInputs.getCtlFormula();
    }

    public static Set or(Set a, Set b) {
        return union(a,b);
    }

    public static Set and(Set a, Set b) {
        return intersection(a,b);
    }

    public static Set not(Set states, Set a) {
        return subtract(states,a);
    }

/** Root production. */
  final public Set Parse() throws ParseException {Set f;
    f = formula(kripke.getStates());
    jj_consume_token(0);
{if ("" != null) return f;}
    throw new Error("Missing return statement in function");
}

  final public Set formula(Set states) throws ParseException {Set e;
    Set b = null;
    e = expression(states);
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case AND:
    case OR:
    case IMPLIES:{
      b = binaryPredicate(e,states);
      break;
      }
    default:
      jj_la1[0] = jj_gen;
      ;
    }
if (b != null) { {if ("" != null) return b;} }
        else { {if ("" != null) return e;} }
    throw new Error("Missing return statement in function");
}

  final public Set expression(Set states) throws ParseException {Token t;
 Set f;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case ATOM:{
      t = jj_consume_token(ATOM);
Set statesWithLabels = statesWithLabel(states, t);
            // System.out.println(statesWithLabels);
            {if ("" != null) return statesWithLabels;}
      break;
      }
    case NOT:{
      jj_consume_token(NOT);
      f = formula(states);
{if ("" != null) return not(states,f);}
      break;
      }
    case LPAREN:{
      jj_consume_token(LPAREN);
      f = formula(states);
      jj_consume_token(RPAREN);
{if ("" != null) return f;}
      break;
      }
    case AX:
    case AF:
    case AG:
    case EX:
    case EF:
    case EG:
    case A:
    case E:{
      f = temporalExpression(states);
{if ("" != null) return f;}
      break;
      }
    default:
      jj_la1[1] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
}

  final public Set binaryPredicate(Set subject, Set states) throws ParseException {Set predicate;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case AND:{
      jj_consume_token(AND);
      predicate = formula(states);
{if ("" != null) return and(subject,predicate);}
      break;
      }
    case OR:{
      jj_consume_token(OR);
      predicate = formula(states);
{if ("" != null) return or(subject,predicate);}
      break;
      }
    case IMPLIES:{
      jj_consume_token(IMPLIES);
      predicate = formula(subject);
{if ("" != null) return or(not(states,subject),predicate);}  /* (not subject or predicate) */
      break;
      }
    default:
      jj_la1[2] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
}

  final public Set temporalExpression(Set s) throws ParseException {Set f;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case AX:{
      jj_consume_token(AX);
      f = formula(s);
{if ("" != null) return f;} /* TODO: finish this stub */
      break;
      }
    case AF:{
      jj_consume_token(AF);
      f = formula(s);
{if ("" != null) return f;} /* TODO: finish this stub */
      break;
      }
    case AG:{
      jj_consume_token(AG);
      f = formula(s);
{if ("" != null) return f;} /* TODO: finish this stub */
      break;
      }
    case EX:{
      jj_consume_token(EX);
      f = formula(s);
{if ("" != null) return f;} /* TODO: finish this stub */
      break;
      }
    case EF:{
      jj_consume_token(EF);
      f = formula(s);
{if ("" != null) return f;} /* TODO: finish this stub */
      break;
      }
    case EG:{
      jj_consume_token(EG);
      f = formula(s);
{if ("" != null) return f;} /* TODO: finish this stub */
      break;
      }
    case A:{
      jj_consume_token(A);
      jj_consume_token(LPAREN);
      f = formula(s);
      jj_consume_token(U);
      f = formula(s);
      jj_consume_token(RPAREN);
{if ("" != null) return f;} /* TODO: finish this stub */
      break;
      }
    case E:{
      jj_consume_token(E);
      jj_consume_token(LPAREN);
      f = formula(s);
      jj_consume_token(U);
      f = formula(s);
      jj_consume_token(RPAREN);
{if ("" != null) return f;} /* TODO: finish this stub */
      break;
      }
    default:
      jj_la1[3] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
}

  /** Generated Token Manager. */
  public CtlParserTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private int jj_gen;
  final private int[] jj_la1 = new int[4];
  static private int[] jj_la1_0;
  static {
	   jj_la1_init_0();
	}
	private static void jj_la1_init_0() {
	   jj_la1_0 = new int[] {0x1c0,0x15fe20,0x1c0,0x1fe00,};
	}

  /** Constructor with InputStream. */
  public CtlParser(java.io.InputStream stream) {
	  this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public CtlParser(java.io.InputStream stream, String encoding) {
	 try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
	 token_source = new CtlParserTokenManager(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 4; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
	  ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
	 try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
	 token_source.ReInit(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 4; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public CtlParser(java.io.Reader stream) {
	 jj_input_stream = new SimpleCharStream(stream, 1, 1);
	 token_source = new CtlParserTokenManager(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 4; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
	if (jj_input_stream == null) {
	   jj_input_stream = new SimpleCharStream(stream, 1, 1);
	} else {
	   jj_input_stream.ReInit(stream, 1, 1);
	}
	if (token_source == null) {
 token_source = new CtlParserTokenManager(jj_input_stream);
	}

	 token_source.ReInit(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 4; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public CtlParser(CtlParserTokenManager tm) {
	 token_source = tm;
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 4; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(CtlParserTokenManager tm) {
	 token_source = tm;
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 4; i++) jj_la1[i] = -1;
  }

  private Token jj_consume_token(int kind) throws ParseException {
	 Token oldToken;
	 if ((oldToken = token).next != null) token = token.next;
	 else token = token.next = token_source.getNextToken();
	 jj_ntk = -1;
	 if (token.kind == kind) {
	   jj_gen++;
	   return token;
	 }
	 token = oldToken;
	 jj_kind = kind;
	 throw generateParseException();
  }


/** Get the next Token. */
  final public Token getNextToken() {
	 if (token.next != null) token = token.next;
	 else token = token.next = token_source.getNextToken();
	 jj_ntk = -1;
	 jj_gen++;
	 return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
	 Token t = token;
	 for (int i = 0; i < index; i++) {
	   if (t.next != null) t = t.next;
	   else t = t.next = token_source.getNextToken();
	 }
	 return t;
  }

  private int jj_ntk_f() {
	 if ((jj_nt=token.next) == null)
	   return (jj_ntk = (token.next=token_source.getNextToken()).kind);
	 else
	   return (jj_ntk = jj_nt.kind);
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;

  /** Generate ParseException. */
  public ParseException generateParseException() {
	 jj_expentries.clear();
	 boolean[] la1tokens = new boolean[21];
	 if (jj_kind >= 0) {
	   la1tokens[jj_kind] = true;
	   jj_kind = -1;
	 }
	 for (int i = 0; i < 4; i++) {
	   if (jj_la1[i] == jj_gen) {
		 for (int j = 0; j < 32; j++) {
		   if ((jj_la1_0[i] & (1<<j)) != 0) {
			 la1tokens[j] = true;
		   }
		 }
	   }
	 }
	 for (int i = 0; i < 21; i++) {
	   if (la1tokens[i]) {
		 jj_expentry = new int[1];
		 jj_expentry[0] = i;
		 jj_expentries.add(jj_expentry);
	   }
	 }
	 int[][] exptokseq = new int[jj_expentries.size()][];
	 for (int i = 0; i < jj_expentries.size(); i++) {
	   exptokseq[i] = jj_expentries.get(i);
	 }
	 return new ParseException(token, exptokseq, tokenImage);
  }

  private boolean trace_enabled;

/** Trace enabled. */
  final public boolean trace_enabled() {
	 return trace_enabled;
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

}
