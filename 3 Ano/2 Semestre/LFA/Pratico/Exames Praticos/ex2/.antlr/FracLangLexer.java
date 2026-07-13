// Generated from /Users/andreoliveira/Library/CloudStorage/OneDrive-UniversidadedeAveiro/LEI - André Oliveira/2º Ano/2º semestre/Compiladores/Aulas Práticas/guiao-p/ex2/FracLang.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class FracLangLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, ID=12, FRACTION=13, STRING=14, WS=15, COMMENT=16, ERROR=17;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "ID", "FRACTION", "STRING", "WS", "COMMENT", "ERROR"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "';'", "'display'", "'<='", "'+'", "'-'", "'*'", "':'", "'reduce'", 
			"'('", "')'", "'read'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			"ID", "FRACTION", "STRING", "WS", "COMMENT", "ERROR"
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


	public FracLangLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "FracLang.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\23{\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\5\3\5\3\6\3\6\3"+
		"\7\3\7\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3"+
		"\f\3\f\3\f\3\r\6\rL\n\r\r\r\16\rM\3\16\6\16Q\n\16\r\16\16\16R\3\16\3\16"+
		"\6\16W\n\16\r\16\16\16X\5\16[\n\16\3\17\3\17\7\17_\n\17\f\17\16\17b\13"+
		"\17\3\17\3\17\3\20\6\20g\n\20\r\20\16\20h\3\20\3\20\3\21\3\21\3\21\3\21"+
		"\7\21q\n\21\f\21\16\21t\13\21\3\21\3\21\3\21\3\21\3\22\3\22\4`r\2\23\3"+
		"\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37"+
		"\21!\22#\23\3\2\5\3\2c|\3\2\62;\5\2\13\f\17\17\"\"\2\u0081\2\3\3\2\2\2"+
		"\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2"+
		"\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2"+
		"\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\3%\3\2\2"+
		"\2\5\'\3\2\2\2\7/\3\2\2\2\t\62\3\2\2\2\13\64\3\2\2\2\r\66\3\2\2\2\178"+
		"\3\2\2\2\21:\3\2\2\2\23A\3\2\2\2\25C\3\2\2\2\27E\3\2\2\2\31K\3\2\2\2\33"+
		"P\3\2\2\2\35\\\3\2\2\2\37f\3\2\2\2!l\3\2\2\2#y\3\2\2\2%&\7=\2\2&\4\3\2"+
		"\2\2\'(\7f\2\2()\7k\2\2)*\7u\2\2*+\7r\2\2+,\7n\2\2,-\7c\2\2-.\7{\2\2."+
		"\6\3\2\2\2/\60\7>\2\2\60\61\7?\2\2\61\b\3\2\2\2\62\63\7-\2\2\63\n\3\2"+
		"\2\2\64\65\7/\2\2\65\f\3\2\2\2\66\67\7,\2\2\67\16\3\2\2\289\7<\2\29\20"+
		"\3\2\2\2:;\7t\2\2;<\7g\2\2<=\7f\2\2=>\7w\2\2>?\7e\2\2?@\7g\2\2@\22\3\2"+
		"\2\2AB\7*\2\2B\24\3\2\2\2CD\7+\2\2D\26\3\2\2\2EF\7t\2\2FG\7g\2\2GH\7c"+
		"\2\2HI\7f\2\2I\30\3\2\2\2JL\t\2\2\2KJ\3\2\2\2LM\3\2\2\2MK\3\2\2\2MN\3"+
		"\2\2\2N\32\3\2\2\2OQ\t\3\2\2PO\3\2\2\2QR\3\2\2\2RP\3\2\2\2RS\3\2\2\2S"+
		"Z\3\2\2\2TV\7\61\2\2UW\t\3\2\2VU\3\2\2\2WX\3\2\2\2XV\3\2\2\2XY\3\2\2\2"+
		"Y[\3\2\2\2ZT\3\2\2\2Z[\3\2\2\2[\34\3\2\2\2\\`\7$\2\2]_\13\2\2\2^]\3\2"+
		"\2\2_b\3\2\2\2`a\3\2\2\2`^\3\2\2\2ac\3\2\2\2b`\3\2\2\2cd\7$\2\2d\36\3"+
		"\2\2\2eg\t\4\2\2fe\3\2\2\2gh\3\2\2\2hf\3\2\2\2hi\3\2\2\2ij\3\2\2\2jk\b"+
		"\20\2\2k \3\2\2\2lm\7/\2\2mn\7/\2\2nr\3\2\2\2oq\13\2\2\2po\3\2\2\2qt\3"+
		"\2\2\2rs\3\2\2\2rp\3\2\2\2su\3\2\2\2tr\3\2\2\2uv\7\f\2\2vw\3\2\2\2wx\b"+
		"\21\2\2x\"\3\2\2\2yz\13\2\2\2z$\3\2\2\2\n\2MRXZ`hr\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}