// Generated from CalComplex.g4 by ANTLR 4.12.0
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class CalComplexLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.12.0", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, STRING=11, COMPLEX=12, NUMBER=13, ID=14, WS=15, COMMENT=16, ERROR=17;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "STRING", "COMPLEX", "NUMBER", "ID", "WS", "COMMENT", "ERROR"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "';'", "'output'", "'=>'", "'+'", "'-'", "'*'", "':'", "'('", "')'", 
			"'input'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, "STRING", 
			"COMPLEX", "NUMBER", "ID", "WS", "COMMENT", "ERROR"
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


	public CalComplexLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "CalComplex.g4"; }

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
		"\u0004\u0000\u0011x\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002"+
		"\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0001\u0000\u0001\u0000\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001"+
		"\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001"+
		"\u0007\u0001\u0007\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\n\u0001\n\u0005\nD\b\n\n\n\f\nG\t\n\u0001\n\u0001\n\u0001"+
		"\u000b\u0003\u000bL\b\u000b\u0001\u000b\u0003\u000bO\b\u000b\u0001\u000b"+
		"\u0003\u000bR\b\u000b\u0001\u000b\u0001\u000b\u0001\f\u0004\fW\b\f\u000b"+
		"\f\f\fX\u0001\r\u0004\r\\\b\r\u000b\r\f\r]\u0001\r\u0005\ra\b\r\n\r\f"+
		"\rd\t\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000f\u0001"+
		"\u000f\u0001\u000f\u0001\u000f\u0005\u000fn\b\u000f\n\u000f\f\u000fq\t"+
		"\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u0010\u0001"+
		"\u0010\u0002Eo\u0000\u0011\u0001\u0001\u0003\u0002\u0005\u0003\u0007\u0004"+
		"\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013\n\u0015\u000b\u0017"+
		"\f\u0019\r\u001b\u000e\u001d\u000f\u001f\u0010!\u0011\u0001\u0000\u0004"+
		"\u0002\u0000++--\u0001\u000009\u0002\u0000AZaz\u0003\u0000\t\n\r\r  \u007f"+
		"\u0000\u0001\u0001\u0000\u0000\u0000\u0000\u0003\u0001\u0000\u0000\u0000"+
		"\u0000\u0005\u0001\u0000\u0000\u0000\u0000\u0007\u0001\u0000\u0000\u0000"+
		"\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001\u0000\u0000\u0000\u0000"+
		"\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000\u0000\u0000\u0000\u0011"+
		"\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000\u0000\u0000\u0000\u0015"+
		"\u0001\u0000\u0000\u0000\u0000\u0017\u0001\u0000\u0000\u0000\u0000\u0019"+
		"\u0001\u0000\u0000\u0000\u0000\u001b\u0001\u0000\u0000\u0000\u0000\u001d"+
		"\u0001\u0000\u0000\u0000\u0000\u001f\u0001\u0000\u0000\u0000\u0000!\u0001"+
		"\u0000\u0000\u0000\u0001#\u0001\u0000\u0000\u0000\u0003%\u0001\u0000\u0000"+
		"\u0000\u0005,\u0001\u0000\u0000\u0000\u0007/\u0001\u0000\u0000\u0000\t"+
		"1\u0001\u0000\u0000\u0000\u000b3\u0001\u0000\u0000\u0000\r5\u0001\u0000"+
		"\u0000\u0000\u000f7\u0001\u0000\u0000\u0000\u00119\u0001\u0000\u0000\u0000"+
		"\u0013;\u0001\u0000\u0000\u0000\u0015A\u0001\u0000\u0000\u0000\u0017K"+
		"\u0001\u0000\u0000\u0000\u0019V\u0001\u0000\u0000\u0000\u001b[\u0001\u0000"+
		"\u0000\u0000\u001de\u0001\u0000\u0000\u0000\u001fi\u0001\u0000\u0000\u0000"+
		"!v\u0001\u0000\u0000\u0000#$\u0005;\u0000\u0000$\u0002\u0001\u0000\u0000"+
		"\u0000%&\u0005o\u0000\u0000&\'\u0005u\u0000\u0000\'(\u0005t\u0000\u0000"+
		"()\u0005p\u0000\u0000)*\u0005u\u0000\u0000*+\u0005t\u0000\u0000+\u0004"+
		"\u0001\u0000\u0000\u0000,-\u0005=\u0000\u0000-.\u0005>\u0000\u0000.\u0006"+
		"\u0001\u0000\u0000\u0000/0\u0005+\u0000\u00000\b\u0001\u0000\u0000\u0000"+
		"12\u0005-\u0000\u00002\n\u0001\u0000\u0000\u000034\u0005*\u0000\u0000"+
		"4\f\u0001\u0000\u0000\u000056\u0005:\u0000\u00006\u000e\u0001\u0000\u0000"+
		"\u000078\u0005(\u0000\u00008\u0010\u0001\u0000\u0000\u00009:\u0005)\u0000"+
		"\u0000:\u0012\u0001\u0000\u0000\u0000;<\u0005i\u0000\u0000<=\u0005n\u0000"+
		"\u0000=>\u0005p\u0000\u0000>?\u0005u\u0000\u0000?@\u0005t\u0000\u0000"+
		"@\u0014\u0001\u0000\u0000\u0000AE\u0005\"\u0000\u0000BD\t\u0000\u0000"+
		"\u0000CB\u0001\u0000\u0000\u0000DG\u0001\u0000\u0000\u0000EF\u0001\u0000"+
		"\u0000\u0000EC\u0001\u0000\u0000\u0000FH\u0001\u0000\u0000\u0000GE\u0001"+
		"\u0000\u0000\u0000HI\u0005\"\u0000\u0000I\u0016\u0001\u0000\u0000\u0000"+
		"JL\u0003\u0019\f\u0000KJ\u0001\u0000\u0000\u0000KL\u0001\u0000\u0000\u0000"+
		"LN\u0001\u0000\u0000\u0000MO\u0007\u0000\u0000\u0000NM\u0001\u0000\u0000"+
		"\u0000NO\u0001\u0000\u0000\u0000OQ\u0001\u0000\u0000\u0000PR\u0003\u0019"+
		"\f\u0000QP\u0001\u0000\u0000\u0000QR\u0001\u0000\u0000\u0000RS\u0001\u0000"+
		"\u0000\u0000ST\u0005i\u0000\u0000T\u0018\u0001\u0000\u0000\u0000UW\u0007"+
		"\u0001\u0000\u0000VU\u0001\u0000\u0000\u0000WX\u0001\u0000\u0000\u0000"+
		"XV\u0001\u0000\u0000\u0000XY\u0001\u0000\u0000\u0000Y\u001a\u0001\u0000"+
		"\u0000\u0000Z\\\u0007\u0002\u0000\u0000[Z\u0001\u0000\u0000\u0000\\]\u0001"+
		"\u0000\u0000\u0000][\u0001\u0000\u0000\u0000]^\u0001\u0000\u0000\u0000"+
		"^b\u0001\u0000\u0000\u0000_a\u0007\u0001\u0000\u0000`_\u0001\u0000\u0000"+
		"\u0000ad\u0001\u0000\u0000\u0000b`\u0001\u0000\u0000\u0000bc\u0001\u0000"+
		"\u0000\u0000c\u001c\u0001\u0000\u0000\u0000db\u0001\u0000\u0000\u0000"+
		"ef\u0007\u0003\u0000\u0000fg\u0001\u0000\u0000\u0000gh\u0006\u000e\u0000"+
		"\u0000h\u001e\u0001\u0000\u0000\u0000ij\u0005#\u0000\u0000jk\u0005#\u0000"+
		"\u0000ko\u0001\u0000\u0000\u0000ln\t\u0000\u0000\u0000ml\u0001\u0000\u0000"+
		"\u0000nq\u0001\u0000\u0000\u0000op\u0001\u0000\u0000\u0000om\u0001\u0000"+
		"\u0000\u0000pr\u0001\u0000\u0000\u0000qo\u0001\u0000\u0000\u0000rs\u0005"+
		"\n\u0000\u0000st\u0001\u0000\u0000\u0000tu\u0006\u000f\u0000\u0000u \u0001"+
		"\u0000\u0000\u0000vw\t\u0000\u0000\u0000w\"\u0001\u0000\u0000\u0000\t"+
		"\u0000EKNQX]bo\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}