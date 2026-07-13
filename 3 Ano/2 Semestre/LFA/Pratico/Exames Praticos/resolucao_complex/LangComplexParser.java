// Generated from LangComplex.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class LangComplexParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, NUMBER=6, IMAG=7, ID=8, COMMENT=9, 
		WS=10;
	public static final int
		RULE_program = 0, RULE_stat = 1, RULE_expr = 2, RULE_atom = 3;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "stat", "expr", "atom"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'display'", "';'", "'<='", "'+'", "'-'", null, "'i'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, "NUMBER", "IMAG", "ID", "COMMENT", 
			"WS"
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

	@Override
	public String getGrammarFileName() { return "LangComplex.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public LangComplexParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(LangComplexParser.EOF, 0); }
		public List<StatContext> stat() {
			return getRuleContexts(StatContext.class);
		}
		public StatContext stat(int i) {
			return getRuleContext(StatContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangComplexListener ) ((LangComplexListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangComplexListener ) ((LangComplexListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangComplexVisitor ) return ((LangComplexVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(11);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0 || _la==ID) {
				{
				{
				setState(8);
				stat();
				}
				}
				setState(13);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(14);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatContext extends ParserRuleContext {
		public StatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stat; }
	 
		public StatContext() { }
		public void copyFrom(StatContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StatDisplayContext extends StatContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public StatDisplayContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangComplexListener ) ((LangComplexListener)listener).enterStatDisplay(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangComplexListener ) ((LangComplexListener)listener).exitStatDisplay(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangComplexVisitor ) return ((LangComplexVisitor<? extends T>)visitor).visitStatDisplay(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StatAssignContext extends StatContext {
		public TerminalNode ID() { return getToken(LangComplexParser.ID, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public StatAssignContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangComplexListener ) ((LangComplexListener)listener).enterStatAssign(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangComplexListener ) ((LangComplexListener)listener).exitStatAssign(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangComplexVisitor ) return ((LangComplexVisitor<? extends T>)visitor).visitStatAssign(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatContext stat() throws RecognitionException {
		StatContext _localctx = new StatContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_stat);
		try {
			setState(25);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
				_localctx = new StatDisplayContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(16);
				match(T__0);
				setState(17);
				expr(0);
				setState(18);
				match(T__1);
				}
				break;
			case ID:
				_localctx = new StatAssignContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(20);
				match(ID);
				setState(21);
				match(T__2);
				setState(22);
				expr(0);
				setState(23);
				match(T__1);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprContext extends ParserRuleContext {
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	 
		public ExprContext() { }
		public void copyFrom(ExprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprAddSubContext extends ExprContext {
		public Token op;
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public ExprAddSubContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangComplexListener ) ((LangComplexListener)listener).enterExprAddSub(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangComplexListener ) ((LangComplexListener)listener).exitExprAddSub(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangComplexVisitor ) return ((LangComplexVisitor<? extends T>)visitor).visitExprAddSub(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprAtomContext extends ExprContext {
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public ExprAtomContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangComplexListener ) ((LangComplexListener)listener).enterExprAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangComplexListener ) ((LangComplexListener)listener).exitExprAtom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangComplexVisitor ) return ((LangComplexVisitor<? extends T>)visitor).visitExprAtom(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 4;
		enterRecursionRule(_localctx, 4, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			_localctx = new ExprAtomContext(_localctx);
			_ctx = _localctx;
			_prevctx = _localctx;

			setState(28);
			atom();
			}
			_ctx.stop = _input.LT(-1);
			setState(35);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ExprAddSubContext(new ExprContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_expr);
					setState(30);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(31);
					((ExprAddSubContext)_localctx).op = _input.LT(1);
					_la = _input.LA(1);
					if ( !(_la==T__3 || _la==T__4) ) {
						((ExprAddSubContext)_localctx).op = (Token)_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(32);
					atom();
					}
					} 
				}
				setState(37);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AtomContext extends ParserRuleContext {
		public AtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom; }
	 
		public AtomContext() { }
		public void copyFrom(AtomContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AtomRealContext extends AtomContext {
		public TerminalNode NUMBER() { return getToken(LangComplexParser.NUMBER, 0); }
		public AtomRealContext(AtomContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangComplexListener ) ((LangComplexListener)listener).enterAtomReal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangComplexListener ) ((LangComplexListener)listener).exitAtomReal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangComplexVisitor ) return ((LangComplexVisitor<? extends T>)visitor).visitAtomReal(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AtomImagUnitContext extends AtomContext {
		public TerminalNode IMAG() { return getToken(LangComplexParser.IMAG, 0); }
		public AtomImagUnitContext(AtomContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangComplexListener ) ((LangComplexListener)listener).enterAtomImagUnit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangComplexListener ) ((LangComplexListener)listener).exitAtomImagUnit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangComplexVisitor ) return ((LangComplexVisitor<? extends T>)visitor).visitAtomImagUnit(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AtomImagNumberContext extends AtomContext {
		public TerminalNode NUMBER() { return getToken(LangComplexParser.NUMBER, 0); }
		public TerminalNode IMAG() { return getToken(LangComplexParser.IMAG, 0); }
		public AtomImagNumberContext(AtomContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangComplexListener ) ((LangComplexListener)listener).enterAtomImagNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangComplexListener ) ((LangComplexListener)listener).exitAtomImagNumber(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangComplexVisitor ) return ((LangComplexVisitor<? extends T>)visitor).visitAtomImagNumber(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AtomIDContext extends AtomContext {
		public TerminalNode ID() { return getToken(LangComplexParser.ID, 0); }
		public AtomIDContext(AtomContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangComplexListener ) ((LangComplexListener)listener).enterAtomID(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangComplexListener ) ((LangComplexListener)listener).exitAtomID(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangComplexVisitor ) return ((LangComplexVisitor<? extends T>)visitor).visitAtomID(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_atom);
		try {
			setState(43);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				_localctx = new AtomImagNumberContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(38);
				match(NUMBER);
				setState(39);
				match(IMAG);
				}
				break;
			case 2:
				_localctx = new AtomImagUnitContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(40);
				match(IMAG);
				}
				break;
			case 3:
				_localctx = new AtomRealContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(41);
				match(NUMBER);
				}
				break;
			case 4:
				_localctx = new AtomIDContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(42);
				match(ID);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 2:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001\n.\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0001\u0000\u0005\u0000\n\b"+
		"\u0000\n\u0000\f\u0000\r\t\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0003\u0001\u001a\b\u0001\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0005\u0002\"\b\u0002\n\u0002"+
		"\f\u0002%\t\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0003\u0003,\b\u0003\u0001\u0003\u0000\u0001\u0004\u0004\u0000"+
		"\u0002\u0004\u0006\u0000\u0001\u0001\u0000\u0004\u0005/\u0000\u000b\u0001"+
		"\u0000\u0000\u0000\u0002\u0019\u0001\u0000\u0000\u0000\u0004\u001b\u0001"+
		"\u0000\u0000\u0000\u0006+\u0001\u0000\u0000\u0000\b\n\u0003\u0002\u0001"+
		"\u0000\t\b\u0001\u0000\u0000\u0000\n\r\u0001\u0000\u0000\u0000\u000b\t"+
		"\u0001\u0000\u0000\u0000\u000b\f\u0001\u0000\u0000\u0000\f\u000e\u0001"+
		"\u0000\u0000\u0000\r\u000b\u0001\u0000\u0000\u0000\u000e\u000f\u0005\u0000"+
		"\u0000\u0001\u000f\u0001\u0001\u0000\u0000\u0000\u0010\u0011\u0005\u0001"+
		"\u0000\u0000\u0011\u0012\u0003\u0004\u0002\u0000\u0012\u0013\u0005\u0002"+
		"\u0000\u0000\u0013\u001a\u0001\u0000\u0000\u0000\u0014\u0015\u0005\b\u0000"+
		"\u0000\u0015\u0016\u0005\u0003\u0000\u0000\u0016\u0017\u0003\u0004\u0002"+
		"\u0000\u0017\u0018\u0005\u0002\u0000\u0000\u0018\u001a\u0001\u0000\u0000"+
		"\u0000\u0019\u0010\u0001\u0000\u0000\u0000\u0019\u0014\u0001\u0000\u0000"+
		"\u0000\u001a\u0003\u0001\u0000\u0000\u0000\u001b\u001c\u0006\u0002\uffff"+
		"\uffff\u0000\u001c\u001d\u0003\u0006\u0003\u0000\u001d#\u0001\u0000\u0000"+
		"\u0000\u001e\u001f\n\u0002\u0000\u0000\u001f \u0007\u0000\u0000\u0000"+
		" \"\u0003\u0006\u0003\u0000!\u001e\u0001\u0000\u0000\u0000\"%\u0001\u0000"+
		"\u0000\u0000#!\u0001\u0000\u0000\u0000#$\u0001\u0000\u0000\u0000$\u0005"+
		"\u0001\u0000\u0000\u0000%#\u0001\u0000\u0000\u0000&\'\u0005\u0006\u0000"+
		"\u0000\',\u0005\u0007\u0000\u0000(,\u0005\u0007\u0000\u0000),\u0005\u0006"+
		"\u0000\u0000*,\u0005\b\u0000\u0000+&\u0001\u0000\u0000\u0000+(\u0001\u0000"+
		"\u0000\u0000+)\u0001\u0000\u0000\u0000+*\u0001\u0000\u0000\u0000,\u0007"+
		"\u0001\u0000\u0000\u0000\u0004\u000b\u0019#+";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}