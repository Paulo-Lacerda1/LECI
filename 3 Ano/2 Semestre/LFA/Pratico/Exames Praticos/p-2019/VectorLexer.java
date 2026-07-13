// Generated from Vector.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class VectorLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, REAL=7, INT=8, ID=9, WS=10, 
		COMMENT=11;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "REAL", "INT", "ID", 
			"WS", "COMMENT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'show'", "';'", "'->'", "'['", "','", "']'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, "REAL", "INT", "ID", "WS", 
			"COMMENT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public VectorLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Vector.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\u000bN\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0004"+
		"\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0006\u0004\u0006)\b\u0006"+
		"\u000b\u0006\f\u0006*\u0001\u0006\u0001\u0006\u0004\u0006/\b\u0006\u000b"+
		"\u0006\f\u00060\u0001\u0007\u0004\u00074\b\u0007\u000b\u0007\f\u00075"+
		"\u0001\b\u0001\b\u0005\b:\b\b\n\b\f\b=\t\b\u0001\t\u0004\t@\b\t\u000b"+
		"\t\f\tA\u0001\t\u0001\t\u0001\n\u0001\n\u0005\nH\b\n\n\n\f\nK\t\n\u0001"+
		"\n\u0001\n\u0000\u0000\u000b\u0001\u0001\u0003\u0002\u0005\u0003\u0007"+
		"\u0004\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013\n\u0015\u000b"+
		"\u0001\u0000\u0005\u0001\u000009\u0001\u0000az\u0002\u000009az\u0003\u0000"+
		"\t\n\r\r  \u0002\u0000\n\n\r\rS\u0000\u0001\u0001\u0000\u0000\u0000\u0000"+
		"\u0003\u0001\u0000\u0000\u0000\u0000\u0005\u0001\u0000\u0000\u0000\u0000"+
		"\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b"+
		"\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001"+
		"\u0000\u0000\u0000\u0000\u0011\u0001\u0000\u0000\u0000\u0000\u0013\u0001"+
		"\u0000\u0000\u0000\u0000\u0015\u0001\u0000\u0000\u0000\u0001\u0017\u0001"+
		"\u0000\u0000\u0000\u0003\u001c\u0001\u0000\u0000\u0000\u0005\u001e\u0001"+
		"\u0000\u0000\u0000\u0007!\u0001\u0000\u0000\u0000\t#\u0001\u0000\u0000"+
		"\u0000\u000b%\u0001\u0000\u0000\u0000\r(\u0001\u0000\u0000\u0000\u000f"+
		"3\u0001\u0000\u0000\u0000\u00117\u0001\u0000\u0000\u0000\u0013?\u0001"+
		"\u0000\u0000\u0000\u0015E\u0001\u0000\u0000\u0000\u0017\u0018\u0005s\u0000"+
		"\u0000\u0018\u0019\u0005h\u0000\u0000\u0019\u001a\u0005o\u0000\u0000\u001a"+
		"\u001b\u0005w\u0000\u0000\u001b\u0002\u0001\u0000\u0000\u0000\u001c\u001d"+
		"\u0005;\u0000\u0000\u001d\u0004\u0001\u0000\u0000\u0000\u001e\u001f\u0005"+
		"-\u0000\u0000\u001f \u0005>\u0000\u0000 \u0006\u0001\u0000\u0000\u0000"+
		"!\"\u0005[\u0000\u0000\"\b\u0001\u0000\u0000\u0000#$\u0005,\u0000\u0000"+
		"$\n\u0001\u0000\u0000\u0000%&\u0005]\u0000\u0000&\f\u0001\u0000\u0000"+
		"\u0000\')\u0007\u0000\u0000\u0000(\'\u0001\u0000\u0000\u0000)*\u0001\u0000"+
		"\u0000\u0000*(\u0001\u0000\u0000\u0000*+\u0001\u0000\u0000\u0000+,\u0001"+
		"\u0000\u0000\u0000,.\u0005.\u0000\u0000-/\u0007\u0000\u0000\u0000.-\u0001"+
		"\u0000\u0000\u0000/0\u0001\u0000\u0000\u00000.\u0001\u0000\u0000\u0000"+
		"01\u0001\u0000\u0000\u00001\u000e\u0001\u0000\u0000\u000024\u0007\u0000"+
		"\u0000\u000032\u0001\u0000\u0000\u000045\u0001\u0000\u0000\u000053\u0001"+
		"\u0000\u0000\u000056\u0001\u0000\u0000\u00006\u0010\u0001\u0000\u0000"+
		"\u00007;\u0007\u0001\u0000\u00008:\u0007\u0002\u0000\u000098\u0001\u0000"+
		"\u0000\u0000:=\u0001\u0000\u0000\u0000;9\u0001\u0000\u0000\u0000;<\u0001"+
		"\u0000\u0000\u0000<\u0012\u0001\u0000\u0000\u0000=;\u0001\u0000\u0000"+
		"\u0000>@\u0007\u0003\u0000\u0000?>\u0001\u0000\u0000\u0000@A\u0001\u0000"+
		"\u0000\u0000A?\u0001\u0000\u0000\u0000AB\u0001\u0000\u0000\u0000BC\u0001"+
		"\u0000\u0000\u0000CD\u0006\t\u0000\u0000D\u0014\u0001\u0000\u0000\u0000"+
		"EI\u0005#\u0000\u0000FH\b\u0004\u0000\u0000GF\u0001\u0000\u0000\u0000"+
		"HK\u0001\u0000\u0000\u0000IG\u0001\u0000\u0000\u0000IJ\u0001\u0000\u0000"+
		"\u0000JL\u0001\u0000\u0000\u0000KI\u0001\u0000\u0000\u0000LM\u0006\n\u0000"+
		"\u0000M\u0016\u0001\u0000\u0000\u0000\u0007\u0000*05;AI\u0001\u0006\u0000"+
		"\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}