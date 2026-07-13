// Generated from /home/paulo/Desktop/LFA_projeto/safelang-lfa-p4g1/src/Safelang.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class SafelangParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, T__37=38, 
		T__38=39, T__39=40, T__40=41, T__41=42, T__42=43, T__43=44, MULTLINECOMMENT=45, 
		COMMENT=46, NEWLINE=47, StringLiteral=48, READ=49, WRITE=50, WRITELN=51, 
		TYPECMD=52, UNITCMD=53, FORMATCMD=54, LENGTHCMD=55, ASSERTCMD=56, FAILCMD=57, 
		WS=58, TYPESTR=59, TYPEINT=60, TYPEREAL=61, TYPEBOOL=62, TRUEKEY=63, FALSEKEY=64, 
		ID=65, NumberDecimal=66, NumberScientific=67, IntegerLiteral=68;
	public static final int
		RULE_program = 0, RULE_stat = 1, RULE_commonStat = 2, RULE_fail = 3, RULE_write = 4, 
		RULE_assign = 5, RULE_type = 6, RULE_if = 7, RULE_else = 8, RULE_try = 9, 
		RULE_rescue = 10, RULE_for = 11, RULE_while = 12, RULE_assert = 13, RULE_listadd = 14, 
		RULE_type_def_expr = 15, RULE_expr = 16, RULE_string = 17, RULE_number = 18, 
		RULE_booleans = 19;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "stat", "commonStat", "fail", "write", "assign", "type", "if", 
			"else", "try", "rescue", "for", "while", "assert", "listadd", "type_def_expr", 
			"expr", "string", "number", "booleans"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "';'", "':='", "':'", "':=?'", "'new'", "'list'", "'['", "']'", 
			"','", "']:='", "'if'", "'then'", "'end'", "'elseif'", "'else'", "'try'", 
			"'rescue'", "'retry'", "'for'", "'to'", "'do'", "'while'", "'until'", 
			"'>>'", "'*'", "'/'", "'('", "'\"left\"'", "'\"center\"'", "'\"right\"'", 
			"')'", "'//'", "'\\\\'", "'+'", "'-'", "'not'", "'='", "'<'", "'>'", 
			"'<='", "'>='", "'<>'", "'and'", "'or'", null, null, null, null, "'read'", 
			"'write'", "'writeln'", "'type'", "'unit'", "'format'", "'length'", "'assert'", 
			"'fail'", null, "'string'", "'integer'", "'real'", "'boolean'", "'true'", 
			"'false'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, "MULTLINECOMMENT", 
			"COMMENT", "NEWLINE", "StringLiteral", "READ", "WRITE", "WRITELN", "TYPECMD", 
			"UNITCMD", "FORMATCMD", "LENGTHCMD", "ASSERTCMD", "FAILCMD", "WS", "TYPESTR", 
			"TYPEINT", "TYPEREAL", "TYPEBOOL", "TRUEKEY", "FALSEKEY", "ID", "NumberDecimal", 
			"NumberScientific", "IntegerLiteral"
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
	public String getGrammarFileName() { return "Safelang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public SafelangParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(SafelangParser.EOF, 0); }
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
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitProgram(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(45);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(40);
					stat();
					setState(41);
					match(T__0);
					}
					} 
				}
				setState(47);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			}
			setState(52);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 11)) & ~0x3f) == 0 && ((1L << (_la - 11)) & 285837701469509921L) != 0)) {
				{
				setState(48);
				stat();
				setState(50);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__0) {
					{
					setState(49);
					match(T__0);
					}
				}

				}
			}

			setState(54);
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
	public static class StatForContext extends StatContext {
		public ForContext for_() {
			return getRuleContext(ForContext.class,0);
		}
		public StatForContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterStatFor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitStatFor(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StatIfContext extends StatContext {
		public IfContext if_() {
			return getRuleContext(IfContext.class,0);
		}
		public StatIfContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterStatIf(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitStatIf(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StatTryContext extends StatContext {
		public TryContext try_() {
			return getRuleContext(TryContext.class,0);
		}
		public StatTryContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterStatTry(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitStatTry(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StatAssertContext extends StatContext {
		public AssertContext assert_() {
			return getRuleContext(AssertContext.class,0);
		}
		public StatAssertContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterStatAssert(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitStatAssert(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StatExprContext extends StatContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public StatExprContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterStatExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitStatExpr(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StatTypeContext extends StatContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public StatTypeContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterStatType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitStatType(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StatListAddContext extends StatContext {
		public ListaddContext listadd() {
			return getRuleContext(ListaddContext.class,0);
		}
		public StatListAddContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterStatListAdd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitStatListAdd(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StatWhileContext extends StatContext {
		public WhileContext while_() {
			return getRuleContext(WhileContext.class,0);
		}
		public StatWhileContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterStatWhile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitStatWhile(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StatFailContext extends StatContext {
		public FailContext fail() {
			return getRuleContext(FailContext.class,0);
		}
		public StatFailContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterStatFail(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitStatFail(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StatWriteContext extends StatContext {
		public WriteContext write() {
			return getRuleContext(WriteContext.class,0);
		}
		public StatWriteContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterStatWrite(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitStatWrite(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StatAssignContext extends StatContext {
		public AssignContext assign() {
			return getRuleContext(AssignContext.class,0);
		}
		public StatAssignContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterStatAssign(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitStatAssign(this);
		}
	}

	public final StatContext stat() throws RecognitionException {
		StatContext _localctx = new StatContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_stat);
		try {
			setState(67);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				_localctx = new StatAssignContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(56);
				assign();
				}
				break;
			case 2:
				_localctx = new StatWriteContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(57);
				write();
				}
				break;
			case 3:
				_localctx = new StatExprContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(58);
				expr(0);
				}
				break;
			case 4:
				_localctx = new StatTypeContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(59);
				type();
				}
				break;
			case 5:
				_localctx = new StatIfContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(60);
				if_();
				}
				break;
			case 6:
				_localctx = new StatTryContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(61);
				try_();
				}
				break;
			case 7:
				_localctx = new StatForContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(62);
				for_();
				}
				break;
			case 8:
				_localctx = new StatWhileContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(63);
				while_();
				}
				break;
			case 9:
				_localctx = new StatAssertContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(64);
				assert_();
				}
				break;
			case 10:
				_localctx = new StatListAddContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(65);
				listadd();
				}
				break;
			case 11:
				_localctx = new StatFailContext(_localctx);
				enterOuterAlt(_localctx, 11);
				{
				setState(66);
				fail();
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

	@SuppressWarnings("CheckReturnValue")
	public static class CommonStatContext extends ParserRuleContext {
		public CommonStatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_commonStat; }
	 
		public CommonStatContext() { }
		public void copyFrom(CommonStatContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class CommonStatFailContext extends CommonStatContext {
		public FailContext fail() {
			return getRuleContext(FailContext.class,0);
		}
		public CommonStatFailContext(CommonStatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterCommonStatFail(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitCommonStatFail(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class CommonStatWriteContext extends CommonStatContext {
		public WriteContext write() {
			return getRuleContext(WriteContext.class,0);
		}
		public CommonStatWriteContext(CommonStatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterCommonStatWrite(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitCommonStatWrite(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class CommonStatForContext extends CommonStatContext {
		public ForContext for_() {
			return getRuleContext(ForContext.class,0);
		}
		public CommonStatForContext(CommonStatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterCommonStatFor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitCommonStatFor(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class CommonStatTryContext extends CommonStatContext {
		public TryContext try_() {
			return getRuleContext(TryContext.class,0);
		}
		public CommonStatTryContext(CommonStatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterCommonStatTry(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitCommonStatTry(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class CommonStatAssertContext extends CommonStatContext {
		public AssertContext assert_() {
			return getRuleContext(AssertContext.class,0);
		}
		public CommonStatAssertContext(CommonStatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterCommonStatAssert(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitCommonStatAssert(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class CommonStatExprContext extends CommonStatContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public CommonStatExprContext(CommonStatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterCommonStatExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitCommonStatExpr(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class CommonStatWhileContext extends CommonStatContext {
		public WhileContext while_() {
			return getRuleContext(WhileContext.class,0);
		}
		public CommonStatWhileContext(CommonStatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterCommonStatWhile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitCommonStatWhile(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class CommonStatListAddContext extends CommonStatContext {
		public ListaddContext listadd() {
			return getRuleContext(ListaddContext.class,0);
		}
		public CommonStatListAddContext(CommonStatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterCommonStatListAdd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitCommonStatListAdd(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class CommonStatAssignContext extends CommonStatContext {
		public AssignContext assign() {
			return getRuleContext(AssignContext.class,0);
		}
		public CommonStatAssignContext(CommonStatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterCommonStatAssign(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitCommonStatAssign(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class CommonStatIfContext extends CommonStatContext {
		public IfContext if_() {
			return getRuleContext(IfContext.class,0);
		}
		public CommonStatIfContext(CommonStatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterCommonStatIf(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitCommonStatIf(this);
		}
	}

	public final CommonStatContext commonStat() throws RecognitionException {
		CommonStatContext _localctx = new CommonStatContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_commonStat);
		try {
			setState(79);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				_localctx = new CommonStatAssignContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(69);
				assign();
				}
				break;
			case 2:
				_localctx = new CommonStatWriteContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(70);
				write();
				}
				break;
			case 3:
				_localctx = new CommonStatExprContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(71);
				expr(0);
				}
				break;
			case 4:
				_localctx = new CommonStatIfContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(72);
				if_();
				}
				break;
			case 5:
				_localctx = new CommonStatTryContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(73);
				try_();
				}
				break;
			case 6:
				_localctx = new CommonStatForContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(74);
				for_();
				}
				break;
			case 7:
				_localctx = new CommonStatWhileContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(75);
				while_();
				}
				break;
			case 8:
				_localctx = new CommonStatAssertContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(76);
				assert_();
				}
				break;
			case 9:
				_localctx = new CommonStatListAddContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(77);
				listadd();
				}
				break;
			case 10:
				_localctx = new CommonStatFailContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(78);
				fail();
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

	@SuppressWarnings("CheckReturnValue")
	public static class FailContext extends ParserRuleContext {
		public TerminalNode FAILCMD() { return getToken(SafelangParser.FAILCMD, 0); }
		public FailContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fail; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterFail(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitFail(this);
		}
	}

	public final FailContext fail() throws RecognitionException {
		FailContext _localctx = new FailContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_fail);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(81);
			match(FAILCMD);
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
	public static class WriteContext extends ParserRuleContext {
		public WriteContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_write; }
	 
		public WriteContext() { }
		public void copyFrom(WriteContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class WriteExprContext extends WriteContext {
		public TerminalNode WRITE() { return getToken(SafelangParser.WRITE, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public WriteExprContext(WriteContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterWriteExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitWriteExpr(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class WriteLnExprContext extends WriteContext {
		public TerminalNode WRITELN() { return getToken(SafelangParser.WRITELN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public WriteLnExprContext(WriteContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterWriteLnExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitWriteLnExpr(this);
		}
	}

	public final WriteContext write() throws RecognitionException {
		WriteContext _localctx = new WriteContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_write);
		int _la;
		try {
			setState(89);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case WRITE:
				_localctx = new WriteExprContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(83);
				match(WRITE);
				setState(84);
				expr(0);
				}
				break;
			case WRITELN:
				_localctx = new WriteLnExprContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(85);
				match(WRITELN);
				setState(87);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((((_la - 27)) & ~0x3f) == 0 && ((1L << (_la - 27)) & 4359800750977L) != 0)) {
					{
					setState(86);
					expr(0);
					}
				}

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
	public static class AssignContext extends ParserRuleContext {
		public AssignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assign; }
	 
		public AssignContext() { }
		public void copyFrom(AssignContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AssignListValTypeContext extends AssignContext {
		public List<TerminalNode> ID() { return getTokens(SafelangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(SafelangParser.ID, i);
		}
		public List<TerminalNode> TYPEINT() { return getTokens(SafelangParser.TYPEINT); }
		public TerminalNode TYPEINT(int i) {
			return getToken(SafelangParser.TYPEINT, i);
		}
		public List<TerminalNode> TYPESTR() { return getTokens(SafelangParser.TYPESTR); }
		public TerminalNode TYPESTR(int i) {
			return getToken(SafelangParser.TYPESTR, i);
		}
		public List<TerminalNode> TYPEREAL() { return getTokens(SafelangParser.TYPEREAL); }
		public TerminalNode TYPEREAL(int i) {
			return getToken(SafelangParser.TYPEREAL, i);
		}
		public List<TerminalNode> TYPEBOOL() { return getTokens(SafelangParser.TYPEBOOL); }
		public TerminalNode TYPEBOOL(int i) {
			return getToken(SafelangParser.TYPEBOOL, i);
		}
		public AssignListValTypeContext(AssignContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterAssignListValType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitAssignListValType(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AssignValTypeContext extends AssignContext {
		public List<TerminalNode> ID() { return getTokens(SafelangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(SafelangParser.ID, i);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode TYPEINT() { return getToken(SafelangParser.TYPEINT, 0); }
		public TerminalNode TYPESTR() { return getToken(SafelangParser.TYPESTR, 0); }
		public TerminalNode TYPEREAL() { return getToken(SafelangParser.TYPEREAL, 0); }
		public TerminalNode TYPEBOOL() { return getToken(SafelangParser.TYPEBOOL, 0); }
		public AssignValTypeContext(AssignContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterAssignValType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitAssignValType(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AssignTryValContext extends AssignContext {
		public TerminalNode ID() { return getToken(SafelangParser.ID, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public AssignTryValContext(AssignContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterAssignTryVal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitAssignTryVal(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AssignTypeContext extends AssignContext {
		public List<TerminalNode> ID() { return getTokens(SafelangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(SafelangParser.ID, i);
		}
		public TerminalNode TYPEINT() { return getToken(SafelangParser.TYPEINT, 0); }
		public TerminalNode TYPESTR() { return getToken(SafelangParser.TYPESTR, 0); }
		public TerminalNode TYPEREAL() { return getToken(SafelangParser.TYPEREAL, 0); }
		public TerminalNode TYPEBOOL() { return getToken(SafelangParser.TYPEBOOL, 0); }
		public AssignTypeContext(AssignContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterAssignType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitAssignType(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AssignListValContext extends AssignContext {
		public List<TerminalNode> ID() { return getTokens(SafelangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(SafelangParser.ID, i);
		}
		public TerminalNode TYPEINT() { return getToken(SafelangParser.TYPEINT, 0); }
		public TerminalNode TYPESTR() { return getToken(SafelangParser.TYPESTR, 0); }
		public TerminalNode TYPEREAL() { return getToken(SafelangParser.TYPEREAL, 0); }
		public TerminalNode TYPEBOOL() { return getToken(SafelangParser.TYPEBOOL, 0); }
		public AssignListValContext(AssignContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterAssignListVal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitAssignListVal(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AssignValContext extends AssignContext {
		public TerminalNode ID() { return getToken(SafelangParser.ID, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public AssignValContext(AssignContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterAssignVal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitAssignVal(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AssignListTypeContext extends AssignContext {
		public List<TerminalNode> ID() { return getTokens(SafelangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(SafelangParser.ID, i);
		}
		public TerminalNode TYPEINT() { return getToken(SafelangParser.TYPEINT, 0); }
		public TerminalNode TYPESTR() { return getToken(SafelangParser.TYPESTR, 0); }
		public TerminalNode TYPEREAL() { return getToken(SafelangParser.TYPEREAL, 0); }
		public TerminalNode TYPEBOOL() { return getToken(SafelangParser.TYPEBOOL, 0); }
		public AssignListTypeContext(AssignContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterAssignListType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitAssignListType(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AssignTryValTypeContext extends AssignContext {
		public List<TerminalNode> ID() { return getTokens(SafelangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(SafelangParser.ID, i);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode TYPEINT() { return getToken(SafelangParser.TYPEINT, 0); }
		public TerminalNode TYPESTR() { return getToken(SafelangParser.TYPESTR, 0); }
		public TerminalNode TYPEREAL() { return getToken(SafelangParser.TYPEREAL, 0); }
		public TerminalNode TYPEBOOL() { return getToken(SafelangParser.TYPEBOOL, 0); }
		public AssignTryValTypeContext(AssignContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterAssignTryValType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitAssignTryValType(this);
		}
	}

	public final AssignContext assign() throws RecognitionException {
		AssignContext _localctx = new AssignContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_assign);
		int _la;
		try {
			setState(143);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				_localctx = new AssignValTypeContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(91);
				match(ID);
				setState(92);
				match(T__1);
				setState(93);
				expr(0);
				setState(94);
				match(T__2);
				setState(95);
				_la = _input.LA(1);
				if ( !(((((_la - 59)) & ~0x3f) == 0 && ((1L << (_la - 59)) & 79L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 2:
				_localctx = new AssignValContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(97);
				match(ID);
				setState(98);
				match(T__1);
				setState(99);
				expr(0);
				}
				break;
			case 3:
				_localctx = new AssignTryValContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(100);
				match(ID);
				setState(101);
				match(T__3);
				setState(102);
				expr(0);
				}
				break;
			case 4:
				_localctx = new AssignTryValTypeContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(103);
				match(ID);
				setState(104);
				match(T__3);
				setState(105);
				expr(0);
				setState(106);
				match(T__2);
				setState(107);
				_la = _input.LA(1);
				if ( !(((((_la - 59)) & ~0x3f) == 0 && ((1L << (_la - 59)) & 79L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 5:
				_localctx = new AssignTypeContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(109);
				match(ID);
				setState(110);
				match(T__2);
				setState(111);
				_la = _input.LA(1);
				if ( !(((((_la - 59)) & ~0x3f) == 0 && ((1L << (_la - 59)) & 79L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 6:
				_localctx = new AssignListValTypeContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(112);
				match(ID);
				setState(113);
				match(T__1);
				setState(120);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__4:
					{
					setState(114);
					match(T__4);
					setState(115);
					match(T__5);
					setState(116);
					match(T__6);
					setState(117);
					_la = _input.LA(1);
					if ( !(((((_la - 59)) & ~0x3f) == 0 && ((1L << (_la - 59)) & 79L) != 0)) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(118);
					match(T__7);
					}
					break;
				case ID:
					{
					setState(119);
					match(ID);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(122);
				match(T__2);
				setState(123);
				match(T__5);
				setState(124);
				match(T__6);
				setState(125);
				_la = _input.LA(1);
				if ( !(((((_la - 59)) & ~0x3f) == 0 && ((1L << (_la - 59)) & 79L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(126);
				match(T__7);
				}
				break;
			case 7:
				_localctx = new AssignListTypeContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(127);
				match(ID);
				setState(128);
				match(T__2);
				setState(129);
				match(T__5);
				setState(130);
				match(T__6);
				setState(131);
				_la = _input.LA(1);
				if ( !(((((_la - 59)) & ~0x3f) == 0 && ((1L << (_la - 59)) & 79L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(132);
				match(T__7);
				}
				break;
			case 8:
				_localctx = new AssignListValContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(133);
				match(ID);
				setState(134);
				match(T__1);
				setState(141);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__4:
					{
					setState(135);
					match(T__4);
					setState(136);
					match(T__5);
					setState(137);
					match(T__6);
					setState(138);
					_la = _input.LA(1);
					if ( !(((((_la - 59)) & ~0x3f) == 0 && ((1L << (_la - 59)) & 79L) != 0)) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(139);
					match(T__7);
					}
					break;
				case ID:
					{
					setState(140);
					match(ID);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
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

	@SuppressWarnings("CheckReturnValue")
	public static class TypeContext extends ParserRuleContext {
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
	 
		public TypeContext() { }
		public void copyFrom(TypeContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class TypeDependentUnitSuffixContext extends TypeContext {
		public TerminalNode TYPECMD() { return getToken(SafelangParser.TYPECMD, 0); }
		public List<TerminalNode> ID() { return getTokens(SafelangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(SafelangParser.ID, i);
		}
		public Type_def_exprContext type_def_expr() {
			return getRuleContext(Type_def_exprContext.class,0);
		}
		public TerminalNode TYPEINT() { return getToken(SafelangParser.TYPEINT, 0); }
		public TerminalNode TYPEREAL() { return getToken(SafelangParser.TYPEREAL, 0); }
		public TypeDependentUnitSuffixContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterTypeDependentUnitSuffix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitTypeDependentUnitSuffix(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class TypeDependentContext extends TypeContext {
		public TerminalNode TYPECMD() { return getToken(SafelangParser.TYPECMD, 0); }
		public TerminalNode ID() { return getToken(SafelangParser.ID, 0); }
		public Type_def_exprContext type_def_expr() {
			return getRuleContext(Type_def_exprContext.class,0);
		}
		public TerminalNode TYPEINT() { return getToken(SafelangParser.TYPEINT, 0); }
		public TerminalNode TYPEREAL() { return getToken(SafelangParser.TYPEREAL, 0); }
		public TypeDependentContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterTypeDependent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitTypeDependent(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class TypeUnitContext extends TypeContext {
		public TerminalNode TYPECMD() { return getToken(SafelangParser.TYPECMD, 0); }
		public List<TerminalNode> ID() { return getTokens(SafelangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(SafelangParser.ID, i);
		}
		public TerminalNode TYPEINT() { return getToken(SafelangParser.TYPEINT, 0); }
		public TerminalNode TYPESTR() { return getToken(SafelangParser.TYPESTR, 0); }
		public TerminalNode TYPEREAL() { return getToken(SafelangParser.TYPEREAL, 0); }
		public TypeUnitContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterTypeUnit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitTypeUnit(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class TypeDependentUnitContext extends TypeContext {
		public TerminalNode TYPECMD() { return getToken(SafelangParser.TYPECMD, 0); }
		public List<TerminalNode> ID() { return getTokens(SafelangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(SafelangParser.ID, i);
		}
		public Type_def_exprContext type_def_expr() {
			return getRuleContext(Type_def_exprContext.class,0);
		}
		public TerminalNode TYPEINT() { return getToken(SafelangParser.TYPEINT, 0); }
		public TerminalNode TYPEREAL() { return getToken(SafelangParser.TYPEREAL, 0); }
		public TypeDependentUnitContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterTypeDependentUnit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitTypeDependentUnit(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class TypeByteRangeContext extends TypeContext {
		public TerminalNode TYPECMD() { return getToken(SafelangParser.TYPECMD, 0); }
		public TerminalNode ID() { return getToken(SafelangParser.ID, 0); }
		public TerminalNode IntegerLiteral() { return getToken(SafelangParser.IntegerLiteral, 0); }
		public TerminalNode TYPEINT() { return getToken(SafelangParser.TYPEINT, 0); }
		public TerminalNode TYPEREAL() { return getToken(SafelangParser.TYPEREAL, 0); }
		public TypeByteRangeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterTypeByteRange(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitTypeByteRange(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DimensionUnitContext extends TypeContext {
		public TerminalNode UNITCMD() { return getToken(SafelangParser.UNITCMD, 0); }
		public List<TerminalNode> ID() { return getTokens(SafelangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(SafelangParser.ID, i);
		}
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public DimensionUnitContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterDimensionUnit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitDimensionUnit(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class TypeUnitSuffixContext extends TypeContext {
		public TerminalNode TYPECMD() { return getToken(SafelangParser.TYPECMD, 0); }
		public List<TerminalNode> ID() { return getTokens(SafelangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(SafelangParser.ID, i);
		}
		public TerminalNode TYPEINT() { return getToken(SafelangParser.TYPEINT, 0); }
		public TerminalNode TYPESTR() { return getToken(SafelangParser.TYPESTR, 0); }
		public TerminalNode TYPEREAL() { return getToken(SafelangParser.TYPEREAL, 0); }
		public TypeUnitSuffixContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterTypeUnitSuffix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitTypeUnitSuffix(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DimensionUnitSuffixContext extends TypeContext {
		public TerminalNode UNITCMD() { return getToken(SafelangParser.UNITCMD, 0); }
		public List<TerminalNode> ID() { return getTokens(SafelangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(SafelangParser.ID, i);
		}
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public DimensionUnitSuffixContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterDimensionUnitSuffix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitDimensionUnitSuffix(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_type);
		int _la;
		try {
			setState(212);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				_localctx = new TypeUnitContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(145);
				match(TYPECMD);
				setState(146);
				match(ID);
				setState(147);
				match(T__6);
				setState(148);
				match(ID);
				setState(149);
				match(T__7);
				setState(150);
				match(T__2);
				setState(151);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 4035225266123964416L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 2:
				_localctx = new TypeUnitSuffixContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(152);
				match(TYPECMD);
				setState(153);
				match(ID);
				setState(154);
				match(T__6);
				setState(155);
				match(ID);
				setState(156);
				match(T__8);
				setState(157);
				match(ID);
				setState(158);
				match(T__7);
				setState(159);
				match(T__2);
				setState(160);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 4035225266123964416L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 3:
				_localctx = new TypeDependentContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(161);
				match(TYPECMD);
				setState(162);
				match(ID);
				setState(163);
				match(T__1);
				setState(164);
				type_def_expr(0);
				setState(165);
				match(T__2);
				setState(166);
				_la = _input.LA(1);
				if ( !(_la==TYPEINT || _la==TYPEREAL) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 4:
				_localctx = new TypeDependentUnitContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(168);
				match(TYPECMD);
				setState(169);
				match(ID);
				setState(170);
				match(T__6);
				setState(171);
				match(ID);
				setState(172);
				match(T__7);
				setState(173);
				match(T__1);
				setState(174);
				type_def_expr(0);
				setState(175);
				match(T__2);
				setState(176);
				_la = _input.LA(1);
				if ( !(_la==TYPEINT || _la==TYPEREAL) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 5:
				_localctx = new TypeDependentUnitSuffixContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(178);
				match(TYPECMD);
				setState(179);
				match(ID);
				setState(180);
				match(T__6);
				setState(181);
				match(ID);
				setState(182);
				match(T__8);
				setState(183);
				match(ID);
				setState(184);
				match(T__7);
				setState(185);
				match(T__1);
				setState(186);
				type_def_expr(0);
				setState(187);
				match(T__2);
				setState(188);
				_la = _input.LA(1);
				if ( !(_la==TYPEINT || _la==TYPEREAL) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 6:
				_localctx = new TypeByteRangeContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(190);
				match(TYPECMD);
				setState(191);
				match(ID);
				setState(192);
				match(T__2);
				setState(193);
				_la = _input.LA(1);
				if ( !(_la==TYPEINT || _la==TYPEREAL) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(194);
				match(T__6);
				setState(195);
				match(IntegerLiteral);
				setState(196);
				match(T__7);
				}
				break;
			case 7:
				_localctx = new DimensionUnitContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(197);
				match(UNITCMD);
				setState(198);
				match(ID);
				setState(199);
				match(T__6);
				setState(200);
				match(ID);
				setState(201);
				match(T__9);
				setState(202);
				number(0);
				}
				break;
			case 8:
				_localctx = new DimensionUnitSuffixContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(203);
				match(UNITCMD);
				setState(204);
				match(ID);
				setState(205);
				match(T__6);
				setState(206);
				match(ID);
				setState(207);
				match(T__8);
				setState(208);
				match(ID);
				setState(209);
				match(T__7);
				setState(210);
				match(T__1);
				setState(211);
				number(0);
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

	@SuppressWarnings("CheckReturnValue")
	public static class IfContext extends ParserRuleContext {
		public IfContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_if; }
	 
		public IfContext() { }
		public void copyFrom(IfContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IfElseContext extends IfContext {
		public BooleansContext booleans() {
			return getRuleContext(BooleansContext.class,0);
		}
		public ElseContext else_() {
			return getRuleContext(ElseContext.class,0);
		}
		public List<CommonStatContext> commonStat() {
			return getRuleContexts(CommonStatContext.class);
		}
		public CommonStatContext commonStat(int i) {
			return getRuleContext(CommonStatContext.class,i);
		}
		public IfElseContext(IfContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterIfElse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitIfElse(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IfEndContext extends IfContext {
		public BooleansContext booleans() {
			return getRuleContext(BooleansContext.class,0);
		}
		public List<CommonStatContext> commonStat() {
			return getRuleContexts(CommonStatContext.class);
		}
		public CommonStatContext commonStat(int i) {
			return getRuleContext(CommonStatContext.class,i);
		}
		public IfEndContext(IfContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterIfEnd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitIfEnd(this);
		}
	}

	public final IfContext if_() throws RecognitionException {
		IfContext _localctx = new IfContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_if);
		int _la;
		try {
			int _alt;
			setState(253);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				_localctx = new IfElseContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(214);
				match(T__10);
				setState(215);
				booleans(0);
				setState(216);
				match(T__11);
				{
				setState(222);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(217);
						commonStat();
						setState(218);
						match(T__0);
						}
						} 
					}
					setState(224);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
				}
				setState(229);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((((_la - 11)) & ~0x3f) == 0 && ((1L << (_la - 11)) & 285831104399743265L) != 0)) {
					{
					setState(225);
					commonStat();
					setState(227);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==T__0) {
						{
						setState(226);
						match(T__0);
						}
					}

					}
				}

				}
				setState(231);
				else_();
				setState(232);
				match(T__12);
				}
				break;
			case 2:
				_localctx = new IfEndContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(234);
				match(T__10);
				setState(235);
				booleans(0);
				setState(236);
				match(T__11);
				{
				setState(242);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(237);
						commonStat();
						setState(238);
						match(T__0);
						}
						} 
					}
					setState(244);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
				}
				setState(249);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((((_la - 11)) & ~0x3f) == 0 && ((1L << (_la - 11)) & 285831104399743265L) != 0)) {
					{
					setState(245);
					commonStat();
					setState(247);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==T__0) {
						{
						setState(246);
						match(T__0);
						}
					}

					}
				}

				}
				setState(251);
				match(T__12);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ElseContext extends ParserRuleContext {
		public ElseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_else; }
	 
		public ElseContext() { }
		public void copyFrom(ElseContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ElseIfContext extends ElseContext {
		public BooleansContext booleans() {
			return getRuleContext(BooleansContext.class,0);
		}
		public ElseContext else_() {
			return getRuleContext(ElseContext.class,0);
		}
		public List<CommonStatContext> commonStat() {
			return getRuleContexts(CommonStatContext.class);
		}
		public CommonStatContext commonStat(int i) {
			return getRuleContext(CommonStatContext.class,i);
		}
		public ElseIfContext(ElseContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterElseIf(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitElseIf(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ElseNormContext extends ElseContext {
		public List<CommonStatContext> commonStat() {
			return getRuleContexts(CommonStatContext.class);
		}
		public CommonStatContext commonStat(int i) {
			return getRuleContext(CommonStatContext.class,i);
		}
		public ElseNormContext(ElseContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterElseNorm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitElseNorm(this);
		}
	}

	public final ElseContext else_() throws RecognitionException {
		ElseContext _localctx = new ElseContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_else);
		int _la;
		try {
			int _alt;
			setState(290);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__13:
				_localctx = new ElseIfContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(255);
				match(T__13);
				setState(256);
				booleans(0);
				setState(257);
				match(T__11);
				{
				setState(263);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(258);
						commonStat();
						setState(259);
						match(T__0);
						}
						} 
					}
					setState(265);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
				}
				setState(270);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((((_la - 11)) & ~0x3f) == 0 && ((1L << (_la - 11)) & 285831104399743265L) != 0)) {
					{
					setState(266);
					commonStat();
					setState(268);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==T__0) {
						{
						setState(267);
						match(T__0);
						}
					}

					}
				}

				}
				setState(273);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__13 || _la==T__14) {
					{
					setState(272);
					else_();
					}
				}

				}
				break;
			case T__14:
				_localctx = new ElseNormContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(275);
				match(T__14);
				{
				setState(281);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(276);
						commonStat();
						setState(277);
						match(T__0);
						}
						} 
					}
					setState(283);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
				}
				setState(288);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((((_la - 11)) & ~0x3f) == 0 && ((1L << (_la - 11)) & 285831104399743265L) != 0)) {
					{
					setState(284);
					commonStat();
					setState(286);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==T__0) {
						{
						setState(285);
						match(T__0);
						}
					}

					}
				}

				}
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
	public static class TryContext extends ParserRuleContext {
		public TryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_try; }
	 
		public TryContext() { }
		public void copyFrom(TryContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class TryRescueContext extends TryContext {
		public RescueContext rescue() {
			return getRuleContext(RescueContext.class,0);
		}
		public List<CommonStatContext> commonStat() {
			return getRuleContexts(CommonStatContext.class);
		}
		public CommonStatContext commonStat(int i) {
			return getRuleContext(CommonStatContext.class,i);
		}
		public TryRescueContext(TryContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterTryRescue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitTryRescue(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class TryNormContext extends TryContext {
		public List<CommonStatContext> commonStat() {
			return getRuleContexts(CommonStatContext.class);
		}
		public CommonStatContext commonStat(int i) {
			return getRuleContext(CommonStatContext.class,i);
		}
		public TryNormContext(TryContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterTryNorm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitTryNorm(this);
		}
	}

	public final TryContext try_() throws RecognitionException {
		TryContext _localctx = new TryContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_try);
		int _la;
		try {
			int _alt;
			setState(326);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,32,_ctx) ) {
			case 1:
				_localctx = new TryNormContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(292);
				match(T__15);
				{
				setState(298);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(293);
						commonStat();
						setState(294);
						match(T__0);
						}
						} 
					}
					setState(300);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
				}
				setState(305);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((((_la - 11)) & ~0x3f) == 0 && ((1L << (_la - 11)) & 285831104399743265L) != 0)) {
					{
					setState(301);
					commonStat();
					setState(303);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==T__0) {
						{
						setState(302);
						match(T__0);
						}
					}

					}
				}

				}
				setState(307);
				match(T__12);
				}
				break;
			case 2:
				_localctx = new TryRescueContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(308);
				match(T__15);
				{
				setState(314);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(309);
						commonStat();
						setState(310);
						match(T__0);
						}
						} 
					}
					setState(316);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
				}
				setState(321);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((((_la - 11)) & ~0x3f) == 0 && ((1L << (_la - 11)) & 285831104399743265L) != 0)) {
					{
					setState(317);
					commonStat();
					setState(319);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==T__0) {
						{
						setState(318);
						match(T__0);
						}
					}

					}
				}

				}
				setState(323);
				rescue();
				setState(324);
				match(T__12);
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

	@SuppressWarnings("CheckReturnValue")
	public static class RescueContext extends ParserRuleContext {
		public RescueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rescue; }
	 
		public RescueContext() { }
		public void copyFrom(RescueContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class RescueRetryContext extends RescueContext {
		public List<CommonStatContext> commonStat() {
			return getRuleContexts(CommonStatContext.class);
		}
		public CommonStatContext commonStat(int i) {
			return getRuleContext(CommonStatContext.class,i);
		}
		public RescueRetryContext(RescueContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterRescueRetry(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitRescueRetry(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class RescueNormContext extends RescueContext {
		public List<CommonStatContext> commonStat() {
			return getRuleContexts(CommonStatContext.class);
		}
		public CommonStatContext commonStat(int i) {
			return getRuleContext(CommonStatContext.class,i);
		}
		public RescueNormContext(RescueContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterRescueNorm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitRescueNorm(this);
		}
	}

	public final RescueContext rescue() throws RecognitionException {
		RescueContext _localctx = new RescueContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_rescue);
		int _la;
		try {
			int _alt;
			setState(356);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,38,_ctx) ) {
			case 1:
				_localctx = new RescueNormContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(328);
				match(T__16);
				{
				setState(334);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,33,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(329);
						commonStat();
						setState(330);
						match(T__0);
						}
						} 
					}
					setState(336);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,33,_ctx);
				}
				setState(341);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((((_la - 11)) & ~0x3f) == 0 && ((1L << (_la - 11)) & 285831104399743265L) != 0)) {
					{
					setState(337);
					commonStat();
					setState(339);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==T__0) {
						{
						setState(338);
						match(T__0);
						}
					}

					}
				}

				}
				}
				break;
			case 2:
				_localctx = new RescueRetryContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(343);
				match(T__16);
				setState(349);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (((((_la - 11)) & ~0x3f) == 0 && ((1L << (_la - 11)) & 285831104399743265L) != 0)) {
					{
					{
					setState(344);
					commonStat();
					setState(345);
					match(T__0);
					}
					}
					setState(351);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(352);
				match(T__17);
				setState(354);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__0) {
					{
					setState(353);
					match(T__0);
					}
				}

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

	@SuppressWarnings("CheckReturnValue")
	public static class ForContext extends ParserRuleContext {
		public ForContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_for; }
	 
		public ForContext() { }
		public void copyFrom(ForContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ForAssignContext extends ForContext {
		public TerminalNode ID() { return getToken(SafelangParser.ID, 0); }
		public List<NumberContext> number() {
			return getRuleContexts(NumberContext.class);
		}
		public NumberContext number(int i) {
			return getRuleContext(NumberContext.class,i);
		}
		public List<CommonStatContext> commonStat() {
			return getRuleContexts(CommonStatContext.class);
		}
		public CommonStatContext commonStat(int i) {
			return getRuleContext(CommonStatContext.class,i);
		}
		public ForAssignContext(ForContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterForAssign(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitForAssign(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ForNormContext extends ForContext {
		public List<NumberContext> number() {
			return getRuleContexts(NumberContext.class);
		}
		public NumberContext number(int i) {
			return getRuleContext(NumberContext.class,i);
		}
		public List<CommonStatContext> commonStat() {
			return getRuleContexts(CommonStatContext.class);
		}
		public CommonStatContext commonStat(int i) {
			return getRuleContext(CommonStatContext.class,i);
		}
		public ForNormContext(ForContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterForNorm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitForNorm(this);
		}
	}

	public final ForContext for_() throws RecognitionException {
		ForContext _localctx = new ForContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_for);
		int _la;
		try {
			int _alt;
			setState(402);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,45,_ctx) ) {
			case 1:
				_localctx = new ForAssignContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(358);
				match(T__18);
				setState(359);
				match(ID);
				setState(360);
				match(T__1);
				setState(361);
				number(0);
				setState(362);
				match(T__19);
				setState(363);
				number(0);
				setState(364);
				match(T__20);
				{
				setState(370);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,39,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(365);
						commonStat();
						setState(366);
						match(T__0);
						}
						} 
					}
					setState(372);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,39,_ctx);
				}
				setState(377);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((((_la - 11)) & ~0x3f) == 0 && ((1L << (_la - 11)) & 285831104399743265L) != 0)) {
					{
					setState(373);
					commonStat();
					setState(375);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==T__0) {
						{
						setState(374);
						match(T__0);
						}
					}

					}
				}

				}
				setState(379);
				match(T__12);
				}
				break;
			case 2:
				_localctx = new ForNormContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(381);
				match(T__18);
				setState(382);
				number(0);
				setState(383);
				match(T__19);
				setState(384);
				number(0);
				setState(385);
				match(T__20);
				{
				setState(391);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,42,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(386);
						commonStat();
						setState(387);
						match(T__0);
						}
						} 
					}
					setState(393);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,42,_ctx);
				}
				setState(398);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((((_la - 11)) & ~0x3f) == 0 && ((1L << (_la - 11)) & 285831104399743265L) != 0)) {
					{
					setState(394);
					commonStat();
					setState(396);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==T__0) {
						{
						setState(395);
						match(T__0);
						}
					}

					}
				}

				}
				setState(400);
				match(T__12);
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

	@SuppressWarnings("CheckReturnValue")
	public static class WhileContext extends ParserRuleContext {
		public WhileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_while; }
	 
		public WhileContext() { }
		public void copyFrom(WhileContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class WhileUntilContext extends WhileContext {
		public BooleansContext booleans() {
			return getRuleContext(BooleansContext.class,0);
		}
		public List<CommonStatContext> commonStat() {
			return getRuleContexts(CommonStatContext.class);
		}
		public CommonStatContext commonStat(int i) {
			return getRuleContext(CommonStatContext.class,i);
		}
		public WhileUntilContext(WhileContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterWhileUntil(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitWhileUntil(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class WhileNormContext extends WhileContext {
		public BooleansContext booleans() {
			return getRuleContext(BooleansContext.class,0);
		}
		public List<CommonStatContext> commonStat() {
			return getRuleContexts(CommonStatContext.class);
		}
		public CommonStatContext commonStat(int i) {
			return getRuleContext(CommonStatContext.class,i);
		}
		public WhileNormContext(WhileContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterWhileNorm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitWhileNorm(this);
		}
	}

	public final WhileContext while_() throws RecognitionException {
		WhileContext _localctx = new WhileContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_while);
		int _la;
		try {
			int _alt;
			setState(442);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__21:
				_localctx = new WhileNormContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(404);
				match(T__21);
				setState(405);
				booleans(0);
				setState(406);
				match(T__20);
				{
				setState(412);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,46,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(407);
						commonStat();
						setState(408);
						match(T__0);
						}
						} 
					}
					setState(414);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,46,_ctx);
				}
				setState(419);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((((_la - 11)) & ~0x3f) == 0 && ((1L << (_la - 11)) & 285831104399743265L) != 0)) {
					{
					setState(415);
					commonStat();
					setState(417);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==T__0) {
						{
						setState(416);
						match(T__0);
						}
					}

					}
				}

				}
				setState(421);
				match(T__12);
				}
				break;
			case T__22:
				_localctx = new WhileUntilContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(423);
				match(T__22);
				setState(424);
				booleans(0);
				setState(425);
				match(T__20);
				{
				setState(431);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,49,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(426);
						commonStat();
						setState(427);
						match(T__0);
						}
						} 
					}
					setState(433);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,49,_ctx);
				}
				setState(438);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((((_la - 11)) & ~0x3f) == 0 && ((1L << (_la - 11)) & 285831104399743265L) != 0)) {
					{
					setState(434);
					commonStat();
					setState(436);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==T__0) {
						{
						setState(435);
						match(T__0);
						}
					}

					}
				}

				}
				setState(440);
				match(T__12);
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
	public static class AssertContext extends ParserRuleContext {
		public TerminalNode ASSERTCMD() { return getToken(SafelangParser.ASSERTCMD, 0); }
		public BooleansContext booleans() {
			return getRuleContext(BooleansContext.class,0);
		}
		public AssertContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assert; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterAssert(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitAssert(this);
		}
	}

	public final AssertContext assert_() throws RecognitionException {
		AssertContext _localctx = new AssertContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_assert);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(444);
			match(ASSERTCMD);
			setState(445);
			booleans(0);
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
	public static class ListaddContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode ID() { return getToken(SafelangParser.ID, 0); }
		public ListaddContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_listadd; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterListadd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitListadd(this);
		}
	}

	public final ListaddContext listadd() throws RecognitionException {
		ListaddContext _localctx = new ListaddContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_listadd);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(447);
			expr(0);
			setState(448);
			match(T__23);
			setState(449);
			match(ID);
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
	public static class Type_def_exprContext extends ParserRuleContext {
		public Type_def_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type_def_expr; }
	 
		public Type_def_exprContext() { }
		public void copyFrom(Type_def_exprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class TypeDefIDContext extends Type_def_exprContext {
		public TerminalNode ID() { return getToken(SafelangParser.ID, 0); }
		public TypeDefIDContext(Type_def_exprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterTypeDefID(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitTypeDefID(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class TypeDefExprContext extends Type_def_exprContext {
		public Token op;
		public List<Type_def_exprContext> type_def_expr() {
			return getRuleContexts(Type_def_exprContext.class);
		}
		public Type_def_exprContext type_def_expr(int i) {
			return getRuleContext(Type_def_exprContext.class,i);
		}
		public TypeDefExprContext(Type_def_exprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterTypeDefExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitTypeDefExpr(this);
		}
	}

	public final Type_def_exprContext type_def_expr() throws RecognitionException {
		return type_def_expr(0);
	}

	private Type_def_exprContext type_def_expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Type_def_exprContext _localctx = new Type_def_exprContext(_ctx, _parentState);
		Type_def_exprContext _prevctx = _localctx;
		int _startState = 30;
		enterRecursionRule(_localctx, 30, RULE_type_def_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			_localctx = new TypeDefIDContext(_localctx);
			_ctx = _localctx;
			_prevctx = _localctx;

			setState(452);
			match(ID);
			}
			_ctx.stop = _input.LT(-1);
			setState(459);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,53,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new TypeDefExprContext(new Type_def_exprContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_type_def_expr);
					setState(454);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(455);
					((TypeDefExprContext)_localctx).op = _input.LT(1);
					_la = _input.LA(1);
					if ( !(_la==T__24 || _la==T__25) ) {
						((TypeDefExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(456);
					type_def_expr(3);
					}
					} 
				}
				setState(461);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,53,_ctx);
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
	public static class StringConcatContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public StringConcatContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterStringConcat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitStringConcat(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprStringContext extends ExprContext {
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public ExprStringContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterExprString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitExprString(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprFormatCommandContext extends ExprContext {
		public TerminalNode FORMATCMD() { return getToken(SafelangParser.FORMATCMD, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public ExprFormatCommandContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterExprFormatCommand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitExprFormatCommand(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprBooleanContext extends ExprContext {
		public BooleansContext booleans() {
			return getRuleContext(BooleansContext.class,0);
		}
		public ExprBooleanContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterExprBoolean(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitExprBoolean(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprListRetrieveElementContext extends ExprContext {
		public TerminalNode ID() { return getToken(SafelangParser.ID, 0); }
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public ExprListRetrieveElementContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterExprListRetrieveElement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitExprListRetrieveElement(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprNumberContext extends ExprContext {
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public ExprNumberContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterExprNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitExprNumber(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprIDContext extends ExprContext {
		public TerminalNode ID() { return getToken(SafelangParser.ID, 0); }
		public ExprIDContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterExprID(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitExprID(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprFormatCommandPlacementContext extends ExprContext {
		public Token op;
		public TerminalNode FORMATCMD() { return getToken(SafelangParser.FORMATCMD, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public ExprFormatCommandPlacementContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterExprFormatCommandPlacement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitExprFormatCommandPlacement(this);
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
		int _startState = 32;
		enterRecursionRule(_localctx, 32, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(488);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,54,_ctx) ) {
			case 1:
				{
				_localctx = new ExprFormatCommandPlacementContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(463);
				match(FORMATCMD);
				setState(464);
				match(T__26);
				setState(465);
				expr(0);
				setState(466);
				match(T__8);
				setState(467);
				number(0);
				setState(468);
				match(T__8);
				setState(469);
				((ExprFormatCommandPlacementContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 1879048192L) != 0)) ) {
					((ExprFormatCommandPlacementContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(470);
				match(T__30);
				}
				break;
			case 2:
				{
				_localctx = new ExprFormatCommandContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(472);
				match(FORMATCMD);
				setState(473);
				match(T__26);
				setState(474);
				expr(0);
				setState(475);
				match(T__8);
				setState(476);
				number(0);
				setState(477);
				match(T__30);
				}
				break;
			case 3:
				{
				_localctx = new ExprListRetrieveElementContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(479);
				match(ID);
				setState(480);
				match(T__6);
				setState(481);
				number(0);
				setState(482);
				match(T__7);
				}
				break;
			case 4:
				{
				_localctx = new ExprIDContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(484);
				match(ID);
				}
				break;
			case 5:
				{
				_localctx = new ExprStringContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(485);
				string();
				}
				break;
			case 6:
				{
				_localctx = new ExprNumberContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(486);
				number(0);
				}
				break;
			case 7:
				{
				_localctx = new ExprBooleanContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(487);
				booleans(0);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(495);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,55,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new StringConcatContext(new ExprContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_expr);
					setState(490);
					if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
					setState(491);
					match(T__8);
					setState(492);
					expr(9);
					}
					} 
				}
				setState(497);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,55,_ctx);
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
	public static class StringContext extends ParserRuleContext {
		public StringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_string; }
	 
		public StringContext() { }
		public void copyFrom(StringContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StringLiteralContext extends StringContext {
		public TerminalNode StringLiteral() { return getToken(SafelangParser.StringLiteral, 0); }
		public StringLiteralContext(StringContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterStringLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitStringLiteral(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StringIDContext extends StringContext {
		public TerminalNode ID() { return getToken(SafelangParser.ID, 0); }
		public StringIDContext(StringContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterStringID(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitStringID(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ConvertToStringContext extends StringContext {
		public TerminalNode TYPESTR() { return getToken(SafelangParser.TYPESTR, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ConvertToStringContext(StringContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterConvertToString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitConvertToString(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ReadCmdContext extends StringContext {
		public TerminalNode READ() { return getToken(SafelangParser.READ, 0); }
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public ReadCmdContext(StringContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterReadCmd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitReadCmd(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StringListRetrieveElementContext extends StringContext {
		public TerminalNode ID() { return getToken(SafelangParser.ID, 0); }
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public StringListRetrieveElementContext(StringContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterStringListRetrieveElement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitStringListRetrieveElement(this);
		}
	}

	public final StringContext string() throws RecognitionException {
		StringContext _localctx = new StringContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_string);
		try {
			setState(512);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,56,_ctx) ) {
			case 1:
				_localctx = new StringLiteralContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(498);
				match(StringLiteral);
				}
				break;
			case 2:
				_localctx = new ConvertToStringContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(499);
				match(TYPESTR);
				setState(500);
				match(T__26);
				setState(501);
				expr(0);
				setState(502);
				match(T__30);
				}
				break;
			case 3:
				_localctx = new ReadCmdContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(504);
				match(READ);
				setState(505);
				string();
				}
				break;
			case 4:
				_localctx = new StringListRetrieveElementContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(506);
				match(ID);
				setState(507);
				match(T__6);
				setState(508);
				number(0);
				setState(509);
				match(T__7);
				}
				break;
			case 5:
				_localctx = new StringIDContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(511);
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

	@SuppressWarnings("CheckReturnValue")
	public static class NumberContext extends ParserRuleContext {
		public NumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_number; }
	 
		public NumberContext() { }
		public void copyFrom(NumberContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ConvertToIntContext extends NumberContext {
		public TerminalNode TYPEINT() { return getToken(SafelangParser.TYPEINT, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ConvertToIntContext(NumberContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterConvertToInt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitConvertToInt(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NumberListLengthContext extends NumberContext {
		public TerminalNode LENGTHCMD() { return getToken(SafelangParser.LENGTHCMD, 0); }
		public TerminalNode ID() { return getToken(SafelangParser.ID, 0); }
		public NumberListLengthContext(NumberContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterNumberListLength(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitNumberListLength(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NumberSuffixContext extends NumberContext {
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public TerminalNode ID() { return getToken(SafelangParser.ID, 0); }
		public NumberSuffixContext(NumberContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterNumberSuffix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitNumberSuffix(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ConvertToTypeContext extends NumberContext {
		public TerminalNode ID() { return getToken(SafelangParser.ID, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ConvertToTypeContext(NumberContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterConvertToType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitConvertToType(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NumberScientificContext extends NumberContext {
		public TerminalNode NumberScientific() { return getToken(SafelangParser.NumberScientific, 0); }
		public NumberScientificContext(NumberContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterNumberScientific(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitNumberScientific(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NumberDecimalContext extends NumberContext {
		public TerminalNode NumberDecimal() { return getToken(SafelangParser.NumberDecimal, 0); }
		public NumberDecimalContext(NumberContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterNumberDecimal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitNumberDecimal(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NumberIDContext extends NumberContext {
		public TerminalNode ID() { return getToken(SafelangParser.ID, 0); }
		public NumberIDContext(NumberContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterNumberID(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitNumberID(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ConvertToRealContext extends NumberContext {
		public TerminalNode TYPEREAL() { return getToken(SafelangParser.TYPEREAL, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ConvertToRealContext(NumberContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterConvertToReal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitConvertToReal(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NumberIntLiteralContext extends NumberContext {
		public TerminalNode IntegerLiteral() { return getToken(SafelangParser.IntegerLiteral, 0); }
		public NumberIntLiteralContext(NumberContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterNumberIntLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitNumberIntLiteral(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NumberUnaryContext extends NumberContext {
		public Token op;
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public NumberUnaryContext(NumberContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterNumberUnary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitNumberUnary(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NumberMultContext extends NumberContext {
		public List<NumberContext> number() {
			return getRuleContexts(NumberContext.class);
		}
		public NumberContext number(int i) {
			return getRuleContext(NumberContext.class,i);
		}
		public NumberMultContext(NumberContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterNumberMult(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitNumberMult(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NumberListRetrieveElementContext extends NumberContext {
		public TerminalNode ID() { return getToken(SafelangParser.ID, 0); }
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public NumberListRetrieveElementContext(NumberContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterNumberListRetrieveElement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitNumberListRetrieveElement(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NumberQuotModIntContext extends NumberContext {
		public Token op;
		public List<NumberContext> number() {
			return getRuleContexts(NumberContext.class);
		}
		public NumberContext number(int i) {
			return getRuleContext(NumberContext.class,i);
		}
		public NumberQuotModIntContext(NumberContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterNumberQuotModInt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitNumberQuotModInt(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NumberParentContext extends NumberContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public NumberParentContext(NumberContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterNumberParent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitNumberParent(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NumberAddSubContext extends NumberContext {
		public Token op;
		public List<NumberContext> number() {
			return getRuleContexts(NumberContext.class);
		}
		public NumberContext number(int i) {
			return getRuleContext(NumberContext.class,i);
		}
		public NumberAddSubContext(NumberContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterNumberAddSub(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitNumberAddSub(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NumberDivRealContext extends NumberContext {
		public List<NumberContext> number() {
			return getRuleContexts(NumberContext.class);
		}
		public NumberContext number(int i) {
			return getRuleContext(NumberContext.class,i);
		}
		public NumberDivRealContext(NumberContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterNumberDivReal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitNumberDivReal(this);
		}
	}

	public final NumberContext number() throws RecognitionException {
		return number(0);
	}

	private NumberContext number(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		NumberContext _localctx = new NumberContext(_ctx, _parentState);
		NumberContext _prevctx = _localctx;
		int _startState = 36;
		enterRecursionRule(_localctx, 36, RULE_number, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(549);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,57,_ctx) ) {
			case 1:
				{
				_localctx = new NumberParentContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(515);
				match(T__26);
				setState(516);
				expr(0);
				setState(517);
				match(T__30);
				}
				break;
			case 2:
				{
				_localctx = new NumberUnaryContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(519);
				((NumberUnaryContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==T__33 || _la==T__34) ) {
					((NumberUnaryContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(520);
				number(10);
				}
				break;
			case 3:
				{
				_localctx = new NumberListRetrieveElementContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(521);
				match(ID);
				setState(522);
				match(T__6);
				setState(523);
				number(0);
				setState(524);
				match(T__7);
				}
				break;
			case 4:
				{
				_localctx = new NumberListLengthContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(526);
				match(LENGTHCMD);
				setState(527);
				match(T__26);
				setState(528);
				match(ID);
				setState(529);
				match(T__30);
				}
				break;
			case 5:
				{
				_localctx = new ConvertToIntContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(530);
				match(TYPEINT);
				setState(531);
				match(T__26);
				setState(532);
				expr(0);
				setState(533);
				match(T__30);
				}
				break;
			case 6:
				{
				_localctx = new ConvertToRealContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(535);
				match(TYPEREAL);
				setState(536);
				match(T__26);
				setState(537);
				expr(0);
				setState(538);
				match(T__30);
				}
				break;
			case 7:
				{
				_localctx = new ConvertToTypeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(540);
				match(ID);
				setState(541);
				match(T__26);
				setState(542);
				expr(0);
				setState(543);
				match(T__30);
				}
				break;
			case 8:
				{
				_localctx = new NumberIntLiteralContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(545);
				match(IntegerLiteral);
				}
				break;
			case 9:
				{
				_localctx = new NumberDecimalContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(546);
				match(NumberDecimal);
				}
				break;
			case 10:
				{
				_localctx = new NumberScientificContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(547);
				match(NumberScientific);
				}
				break;
			case 11:
				{
				_localctx = new NumberIDContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(548);
				match(ID);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(567);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,59,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(565);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,58,_ctx) ) {
					case 1:
						{
						_localctx = new NumberMultContext(new NumberContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_number);
						setState(551);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(552);
						match(T__24);
						setState(553);
						number(15);
						}
						break;
					case 2:
						{
						_localctx = new NumberDivRealContext(new NumberContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_number);
						setState(554);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(555);
						match(T__25);
						setState(556);
						number(14);
						}
						break;
					case 3:
						{
						_localctx = new NumberQuotModIntContext(new NumberContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_number);
						setState(557);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(558);
						((NumberQuotModIntContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__31 || _la==T__32) ) {
							((NumberQuotModIntContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(559);
						number(13);
						}
						break;
					case 4:
						{
						_localctx = new NumberAddSubContext(new NumberContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_number);
						setState(560);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(561);
						((NumberAddSubContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__33 || _la==T__34) ) {
							((NumberAddSubContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(562);
						number(12);
						}
						break;
					case 5:
						{
						_localctx = new NumberSuffixContext(new NumberContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_number);
						setState(563);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(564);
						match(ID);
						}
						break;
					}
					} 
				}
				setState(569);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,59,_ctx);
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
	public static class BooleansContext extends ParserRuleContext {
		public BooleansContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_booleans; }
	 
		public BooleansContext() { }
		public void copyFrom(BooleansContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BooleanIDContext extends BooleansContext {
		public TerminalNode ID() { return getToken(SafelangParser.ID, 0); }
		public BooleanIDContext(BooleansContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterBooleanID(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitBooleanID(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BooleanNumberContext extends BooleansContext {
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public BooleanNumberContext(BooleansContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterBooleanNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitBooleanNumber(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BooleanLesserContext extends BooleansContext {
		public List<NumberContext> number() {
			return getRuleContexts(NumberContext.class);
		}
		public NumberContext number(int i) {
			return getRuleContext(NumberContext.class,i);
		}
		public BooleanLesserContext(BooleansContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterBooleanLesser(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitBooleanLesser(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BooleanLiteralContext extends BooleansContext {
		public Token op;
		public TerminalNode TRUEKEY() { return getToken(SafelangParser.TRUEKEY, 0); }
		public TerminalNode FALSEKEY() { return getToken(SafelangParser.FALSEKEY, 0); }
		public BooleanLiteralContext(BooleansContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterBooleanLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitBooleanLiteral(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BooleanOrContext extends BooleansContext {
		public List<BooleansContext> booleans() {
			return getRuleContexts(BooleansContext.class);
		}
		public BooleansContext booleans(int i) {
			return getRuleContext(BooleansContext.class,i);
		}
		public BooleanOrContext(BooleansContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterBooleanOr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitBooleanOr(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BooleanNotContext extends BooleansContext {
		public BooleansContext booleans() {
			return getRuleContext(BooleansContext.class,0);
		}
		public BooleanNotContext(BooleansContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterBooleanNot(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitBooleanNot(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BooleanAndContext extends BooleansContext {
		public List<BooleansContext> booleans() {
			return getRuleContexts(BooleansContext.class);
		}
		public BooleansContext booleans(int i) {
			return getRuleContext(BooleansContext.class,i);
		}
		public BooleanAndContext(BooleansContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterBooleanAnd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitBooleanAnd(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BooleanParentContext extends BooleansContext {
		public BooleansContext booleans() {
			return getRuleContext(BooleansContext.class,0);
		}
		public BooleanParentContext(BooleansContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterBooleanParent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitBooleanParent(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BooleanGreaterEqualContext extends BooleansContext {
		public List<NumberContext> number() {
			return getRuleContexts(NumberContext.class);
		}
		public NumberContext number(int i) {
			return getRuleContext(NumberContext.class,i);
		}
		public BooleanGreaterEqualContext(BooleansContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterBooleanGreaterEqual(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitBooleanGreaterEqual(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BooleanStringContext extends BooleansContext {
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public BooleanStringContext(BooleansContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterBooleanString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitBooleanString(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BooleanLesserEqualContext extends BooleansContext {
		public List<NumberContext> number() {
			return getRuleContexts(NumberContext.class);
		}
		public NumberContext number(int i) {
			return getRuleContext(NumberContext.class,i);
		}
		public BooleanLesserEqualContext(BooleansContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterBooleanLesserEqual(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitBooleanLesserEqual(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BooleanListRetrieveElementContext extends BooleansContext {
		public List<TerminalNode> ID() { return getTokens(SafelangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(SafelangParser.ID, i);
		}
		public TerminalNode IntegerLiteral() { return getToken(SafelangParser.IntegerLiteral, 0); }
		public BooleanListRetrieveElementContext(BooleansContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterBooleanListRetrieveElement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitBooleanListRetrieveElement(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BooleanGreaterContext extends BooleansContext {
		public List<NumberContext> number() {
			return getRuleContexts(NumberContext.class);
		}
		public NumberContext number(int i) {
			return getRuleContext(NumberContext.class,i);
		}
		public BooleanGreaterContext(BooleansContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterBooleanGreater(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitBooleanGreater(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BooleanEqualContext extends BooleansContext {
		public List<BooleansContext> booleans() {
			return getRuleContexts(BooleansContext.class);
		}
		public BooleansContext booleans(int i) {
			return getRuleContext(BooleansContext.class,i);
		}
		public BooleanEqualContext(BooleansContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterBooleanEqual(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitBooleanEqual(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BooleanNotEqualContext extends BooleansContext {
		public List<BooleansContext> booleans() {
			return getRuleContexts(BooleansContext.class);
		}
		public BooleansContext booleans(int i) {
			return getRuleContext(BooleansContext.class,i);
		}
		public BooleanNotEqualContext(BooleansContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).enterBooleanNotEqual(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SafelangListener ) ((SafelangListener)listener).exitBooleanNotEqual(this);
		}
	}

	public final BooleansContext booleans() throws RecognitionException {
		return booleans(0);
	}

	private BooleansContext booleans(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		BooleansContext _localctx = new BooleansContext(_ctx, _parentState);
		BooleansContext _prevctx = _localctx;
		int _startState = 38;
		enterRecursionRule(_localctx, 38, RULE_booleans, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(601);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,60,_ctx) ) {
			case 1:
				{
				_localctx = new BooleanNotContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(571);
				match(T__35);
				setState(572);
				booleans(15);
				}
				break;
			case 2:
				{
				_localctx = new BooleanParentContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(573);
				match(T__26);
				setState(574);
				booleans(0);
				setState(575);
				match(T__30);
				}
				break;
			case 3:
				{
				_localctx = new BooleanLesserContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(577);
				number(0);
				setState(578);
				match(T__37);
				setState(579);
				number(0);
				}
				break;
			case 4:
				{
				_localctx = new BooleanGreaterContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(581);
				number(0);
				setState(582);
				match(T__38);
				setState(583);
				number(0);
				}
				break;
			case 5:
				{
				_localctx = new BooleanLesserEqualContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(585);
				number(0);
				setState(586);
				match(T__39);
				setState(587);
				number(0);
				}
				break;
			case 6:
				{
				_localctx = new BooleanGreaterEqualContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(589);
				number(0);
				setState(590);
				match(T__40);
				setState(591);
				number(0);
				}
				break;
			case 7:
				{
				_localctx = new BooleanListRetrieveElementContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(593);
				match(ID);
				setState(594);
				match(T__6);
				setState(595);
				_la = _input.LA(1);
				if ( !(_la==ID || _la==IntegerLiteral) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(596);
				match(T__7);
				}
				break;
			case 8:
				{
				_localctx = new BooleanIDContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(597);
				match(ID);
				}
				break;
			case 9:
				{
				_localctx = new BooleanLiteralContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(598);
				((BooleanLiteralContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==TRUEKEY || _la==FALSEKEY) ) {
					((BooleanLiteralContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 10:
				{
				_localctx = new BooleanNumberContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(599);
				number(0);
				}
				break;
			case 11:
				{
				_localctx = new BooleanStringContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(600);
				string();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(617);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,62,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(615);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,61,_ctx) ) {
					case 1:
						{
						_localctx = new BooleanEqualContext(new BooleansContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_booleans);
						setState(603);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(604);
						match(T__36);
						setState(605);
						booleans(14);
						}
						break;
					case 2:
						{
						_localctx = new BooleanNotEqualContext(new BooleansContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_booleans);
						setState(606);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(607);
						match(T__41);
						setState(608);
						booleans(9);
						}
						break;
					case 3:
						{
						_localctx = new BooleanAndContext(new BooleansContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_booleans);
						setState(609);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(610);
						match(T__42);
						setState(611);
						booleans(8);
						}
						break;
					case 4:
						{
						_localctx = new BooleanOrContext(new BooleansContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_booleans);
						setState(612);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(613);
						match(T__43);
						setState(614);
						booleans(7);
						}
						break;
					}
					} 
				}
				setState(619);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,62,_ctx);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 15:
			return type_def_expr_sempred((Type_def_exprContext)_localctx, predIndex);
		case 16:
			return expr_sempred((ExprContext)_localctx, predIndex);
		case 18:
			return number_sempred((NumberContext)_localctx, predIndex);
		case 19:
			return booleans_sempred((BooleansContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean type_def_expr_sempred(Type_def_exprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 8);
		}
		return true;
	}
	private boolean number_sempred(NumberContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return precpred(_ctx, 14);
		case 3:
			return precpred(_ctx, 13);
		case 4:
			return precpred(_ctx, 12);
		case 5:
			return precpred(_ctx, 11);
		case 6:
			return precpred(_ctx, 15);
		}
		return true;
	}
	private boolean booleans_sempred(BooleansContext _localctx, int predIndex) {
		switch (predIndex) {
		case 7:
			return precpred(_ctx, 13);
		case 8:
			return precpred(_ctx, 8);
		case 9:
			return precpred(_ctx, 7);
		case 10:
			return precpred(_ctx, 6);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001D\u026d\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0001\u0000\u0001\u0000\u0001\u0000\u0005\u0000"+
		",\b\u0000\n\u0000\f\u0000/\t\u0000\u0001\u0000\u0001\u0000\u0003\u0000"+
		"3\b\u0000\u0003\u00005\b\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0003\u0001D\b\u0001\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002P\b\u0002\u0001"+
		"\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0003"+
		"\u0004X\b\u0004\u0003\u0004Z\b\u0004\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0003\u0005y\b\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0003\u0005"+
		"\u008e\b\u0005\u0003\u0005\u0090\b\u0005\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0003\u0006\u00d5"+
		"\b\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0005\u0007\u00dd\b\u0007\n\u0007\f\u0007\u00e0\t\u0007\u0001\u0007"+
		"\u0001\u0007\u0003\u0007\u00e4\b\u0007\u0003\u0007\u00e6\b\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0005\u0007\u00f1\b\u0007\n\u0007\f\u0007"+
		"\u00f4\t\u0007\u0001\u0007\u0001\u0007\u0003\u0007\u00f8\b\u0007\u0003"+
		"\u0007\u00fa\b\u0007\u0001\u0007\u0001\u0007\u0003\u0007\u00fe\b\u0007"+
		"\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0005\b\u0106\b\b\n\b"+
		"\f\b\u0109\t\b\u0001\b\u0001\b\u0003\b\u010d\b\b\u0003\b\u010f\b\b\u0001"+
		"\b\u0003\b\u0112\b\b\u0001\b\u0001\b\u0001\b\u0001\b\u0005\b\u0118\b\b"+
		"\n\b\f\b\u011b\t\b\u0001\b\u0001\b\u0003\b\u011f\b\b\u0003\b\u0121\b\b"+
		"\u0003\b\u0123\b\b\u0001\t\u0001\t\u0001\t\u0001\t\u0005\t\u0129\b\t\n"+
		"\t\f\t\u012c\t\t\u0001\t\u0001\t\u0003\t\u0130\b\t\u0003\t\u0132\b\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\t\u0005\t\u0139\b\t\n\t\f\t\u013c\t\t"+
		"\u0001\t\u0001\t\u0003\t\u0140\b\t\u0003\t\u0142\b\t\u0001\t\u0001\t\u0001"+
		"\t\u0003\t\u0147\b\t\u0001\n\u0001\n\u0001\n\u0001\n\u0005\n\u014d\b\n"+
		"\n\n\f\n\u0150\t\n\u0001\n\u0001\n\u0003\n\u0154\b\n\u0003\n\u0156\b\n"+
		"\u0001\n\u0001\n\u0001\n\u0001\n\u0005\n\u015c\b\n\n\n\f\n\u015f\t\n\u0001"+
		"\n\u0001\n\u0003\n\u0163\b\n\u0003\n\u0165\b\n\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0005\u000b\u0171\b\u000b\n\u000b\f\u000b\u0174"+
		"\t\u000b\u0001\u000b\u0001\u000b\u0003\u000b\u0178\b\u000b\u0003\u000b"+
		"\u017a\b\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0005\u000b"+
		"\u0186\b\u000b\n\u000b\f\u000b\u0189\t\u000b\u0001\u000b\u0001\u000b\u0003"+
		"\u000b\u018d\b\u000b\u0003\u000b\u018f\b\u000b\u0001\u000b\u0001\u000b"+
		"\u0003\u000b\u0193\b\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0005\f\u019b\b\f\n\f\f\f\u019e\t\f\u0001\f\u0001\f\u0003\f\u01a2\b"+
		"\f\u0003\f\u01a4\b\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0005\f\u01ae\b\f\n\f\f\f\u01b1\t\f\u0001\f\u0001\f\u0003\f"+
		"\u01b5\b\f\u0003\f\u01b7\b\f\u0001\f\u0001\f\u0003\f\u01bb\b\f\u0001\r"+
		"\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001"+
		"\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0005"+
		"\u000f\u01ca\b\u000f\n\u000f\f\u000f\u01cd\t\u000f\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0003\u0010\u01e9\b\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0005\u0010"+
		"\u01ee\b\u0010\n\u0010\f\u0010\u01f1\t\u0010\u0001\u0011\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0003"+
		"\u0011\u0201\b\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0003\u0012\u0226\b\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0005\u0012\u0236"+
		"\b\u0012\n\u0012\f\u0012\u0239\t\u0012\u0001\u0013\u0001\u0013\u0001\u0013"+
		"\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013"+
		"\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013"+
		"\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013"+
		"\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013"+
		"\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0003\u0013\u025a\b\u0013"+
		"\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013"+
		"\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013"+
		"\u0005\u0013\u0268\b\u0013\n\u0013\f\u0013\u026b\t\u0013\u0001\u0013\u0000"+
		"\u0004\u001e $&\u0014\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012"+
		"\u0014\u0016\u0018\u001a\u001c\u001e \"$&\u0000\t\u0002\u0000;>AA\u0001"+
		"\u0000;=\u0001\u0000<=\u0001\u0000\u0019\u001a\u0001\u0000\u001c\u001e"+
		"\u0001\u0000\"#\u0001\u0000 !\u0002\u0000AADD\u0001\u0000?@\u02d3\u0000"+
		"-\u0001\u0000\u0000\u0000\u0002C\u0001\u0000\u0000\u0000\u0004O\u0001"+
		"\u0000\u0000\u0000\u0006Q\u0001\u0000\u0000\u0000\bY\u0001\u0000\u0000"+
		"\u0000\n\u008f\u0001\u0000\u0000\u0000\f\u00d4\u0001\u0000\u0000\u0000"+
		"\u000e\u00fd\u0001\u0000\u0000\u0000\u0010\u0122\u0001\u0000\u0000\u0000"+
		"\u0012\u0146\u0001\u0000\u0000\u0000\u0014\u0164\u0001\u0000\u0000\u0000"+
		"\u0016\u0192\u0001\u0000\u0000\u0000\u0018\u01ba\u0001\u0000\u0000\u0000"+
		"\u001a\u01bc\u0001\u0000\u0000\u0000\u001c\u01bf\u0001\u0000\u0000\u0000"+
		"\u001e\u01c3\u0001\u0000\u0000\u0000 \u01e8\u0001\u0000\u0000\u0000\""+
		"\u0200\u0001\u0000\u0000\u0000$\u0225\u0001\u0000\u0000\u0000&\u0259\u0001"+
		"\u0000\u0000\u0000()\u0003\u0002\u0001\u0000)*\u0005\u0001\u0000\u0000"+
		"*,\u0001\u0000\u0000\u0000+(\u0001\u0000\u0000\u0000,/\u0001\u0000\u0000"+
		"\u0000-+\u0001\u0000\u0000\u0000-.\u0001\u0000\u0000\u0000.4\u0001\u0000"+
		"\u0000\u0000/-\u0001\u0000\u0000\u000002\u0003\u0002\u0001\u000013\u0005"+
		"\u0001\u0000\u000021\u0001\u0000\u0000\u000023\u0001\u0000\u0000\u0000"+
		"35\u0001\u0000\u0000\u000040\u0001\u0000\u0000\u000045\u0001\u0000\u0000"+
		"\u000056\u0001\u0000\u0000\u000067\u0005\u0000\u0000\u00017\u0001\u0001"+
		"\u0000\u0000\u00008D\u0003\n\u0005\u00009D\u0003\b\u0004\u0000:D\u0003"+
		" \u0010\u0000;D\u0003\f\u0006\u0000<D\u0003\u000e\u0007\u0000=D\u0003"+
		"\u0012\t\u0000>D\u0003\u0016\u000b\u0000?D\u0003\u0018\f\u0000@D\u0003"+
		"\u001a\r\u0000AD\u0003\u001c\u000e\u0000BD\u0003\u0006\u0003\u0000C8\u0001"+
		"\u0000\u0000\u0000C9\u0001\u0000\u0000\u0000C:\u0001\u0000\u0000\u0000"+
		"C;\u0001\u0000\u0000\u0000C<\u0001\u0000\u0000\u0000C=\u0001\u0000\u0000"+
		"\u0000C>\u0001\u0000\u0000\u0000C?\u0001\u0000\u0000\u0000C@\u0001\u0000"+
		"\u0000\u0000CA\u0001\u0000\u0000\u0000CB\u0001\u0000\u0000\u0000D\u0003"+
		"\u0001\u0000\u0000\u0000EP\u0003\n\u0005\u0000FP\u0003\b\u0004\u0000G"+
		"P\u0003 \u0010\u0000HP\u0003\u000e\u0007\u0000IP\u0003\u0012\t\u0000J"+
		"P\u0003\u0016\u000b\u0000KP\u0003\u0018\f\u0000LP\u0003\u001a\r\u0000"+
		"MP\u0003\u001c\u000e\u0000NP\u0003\u0006\u0003\u0000OE\u0001\u0000\u0000"+
		"\u0000OF\u0001\u0000\u0000\u0000OG\u0001\u0000\u0000\u0000OH\u0001\u0000"+
		"\u0000\u0000OI\u0001\u0000\u0000\u0000OJ\u0001\u0000\u0000\u0000OK\u0001"+
		"\u0000\u0000\u0000OL\u0001\u0000\u0000\u0000OM\u0001\u0000\u0000\u0000"+
		"ON\u0001\u0000\u0000\u0000P\u0005\u0001\u0000\u0000\u0000QR\u00059\u0000"+
		"\u0000R\u0007\u0001\u0000\u0000\u0000ST\u00052\u0000\u0000TZ\u0003 \u0010"+
		"\u0000UW\u00053\u0000\u0000VX\u0003 \u0010\u0000WV\u0001\u0000\u0000\u0000"+
		"WX\u0001\u0000\u0000\u0000XZ\u0001\u0000\u0000\u0000YS\u0001\u0000\u0000"+
		"\u0000YU\u0001\u0000\u0000\u0000Z\t\u0001\u0000\u0000\u0000[\\\u0005A"+
		"\u0000\u0000\\]\u0005\u0002\u0000\u0000]^\u0003 \u0010\u0000^_\u0005\u0003"+
		"\u0000\u0000_`\u0007\u0000\u0000\u0000`\u0090\u0001\u0000\u0000\u0000"+
		"ab\u0005A\u0000\u0000bc\u0005\u0002\u0000\u0000c\u0090\u0003 \u0010\u0000"+
		"de\u0005A\u0000\u0000ef\u0005\u0004\u0000\u0000f\u0090\u0003 \u0010\u0000"+
		"gh\u0005A\u0000\u0000hi\u0005\u0004\u0000\u0000ij\u0003 \u0010\u0000j"+
		"k\u0005\u0003\u0000\u0000kl\u0007\u0000\u0000\u0000l\u0090\u0001\u0000"+
		"\u0000\u0000mn\u0005A\u0000\u0000no\u0005\u0003\u0000\u0000o\u0090\u0007"+
		"\u0000\u0000\u0000pq\u0005A\u0000\u0000qx\u0005\u0002\u0000\u0000rs\u0005"+
		"\u0005\u0000\u0000st\u0005\u0006\u0000\u0000tu\u0005\u0007\u0000\u0000"+
		"uv\u0007\u0000\u0000\u0000vy\u0005\b\u0000\u0000wy\u0005A\u0000\u0000"+
		"xr\u0001\u0000\u0000\u0000xw\u0001\u0000\u0000\u0000yz\u0001\u0000\u0000"+
		"\u0000z{\u0005\u0003\u0000\u0000{|\u0005\u0006\u0000\u0000|}\u0005\u0007"+
		"\u0000\u0000}~\u0007\u0000\u0000\u0000~\u0090\u0005\b\u0000\u0000\u007f"+
		"\u0080\u0005A\u0000\u0000\u0080\u0081\u0005\u0003\u0000\u0000\u0081\u0082"+
		"\u0005\u0006\u0000\u0000\u0082\u0083\u0005\u0007\u0000\u0000\u0083\u0084"+
		"\u0007\u0000\u0000\u0000\u0084\u0090\u0005\b\u0000\u0000\u0085\u0086\u0005"+
		"A\u0000\u0000\u0086\u008d\u0005\u0002\u0000\u0000\u0087\u0088\u0005\u0005"+
		"\u0000\u0000\u0088\u0089\u0005\u0006\u0000\u0000\u0089\u008a\u0005\u0007"+
		"\u0000\u0000\u008a\u008b\u0007\u0000\u0000\u0000\u008b\u008e\u0005\b\u0000"+
		"\u0000\u008c\u008e\u0005A\u0000\u0000\u008d\u0087\u0001\u0000\u0000\u0000"+
		"\u008d\u008c\u0001\u0000\u0000\u0000\u008e\u0090\u0001\u0000\u0000\u0000"+
		"\u008f[\u0001\u0000\u0000\u0000\u008fa\u0001\u0000\u0000\u0000\u008fd"+
		"\u0001\u0000\u0000\u0000\u008fg\u0001\u0000\u0000\u0000\u008fm\u0001\u0000"+
		"\u0000\u0000\u008fp\u0001\u0000\u0000\u0000\u008f\u007f\u0001\u0000\u0000"+
		"\u0000\u008f\u0085\u0001\u0000\u0000\u0000\u0090\u000b\u0001\u0000\u0000"+
		"\u0000\u0091\u0092\u00054\u0000\u0000\u0092\u0093\u0005A\u0000\u0000\u0093"+
		"\u0094\u0005\u0007\u0000\u0000\u0094\u0095\u0005A\u0000\u0000\u0095\u0096"+
		"\u0005\b\u0000\u0000\u0096\u0097\u0005\u0003\u0000\u0000\u0097\u00d5\u0007"+
		"\u0001\u0000\u0000\u0098\u0099\u00054\u0000\u0000\u0099\u009a\u0005A\u0000"+
		"\u0000\u009a\u009b\u0005\u0007\u0000\u0000\u009b\u009c\u0005A\u0000\u0000"+
		"\u009c\u009d\u0005\t\u0000\u0000\u009d\u009e\u0005A\u0000\u0000\u009e"+
		"\u009f\u0005\b\u0000\u0000\u009f\u00a0\u0005\u0003\u0000\u0000\u00a0\u00d5"+
		"\u0007\u0001\u0000\u0000\u00a1\u00a2\u00054\u0000\u0000\u00a2\u00a3\u0005"+
		"A\u0000\u0000\u00a3\u00a4\u0005\u0002\u0000\u0000\u00a4\u00a5\u0003\u001e"+
		"\u000f\u0000\u00a5\u00a6\u0005\u0003\u0000\u0000\u00a6\u00a7\u0007\u0002"+
		"\u0000\u0000\u00a7\u00d5\u0001\u0000\u0000\u0000\u00a8\u00a9\u00054\u0000"+
		"\u0000\u00a9\u00aa\u0005A\u0000\u0000\u00aa\u00ab\u0005\u0007\u0000\u0000"+
		"\u00ab\u00ac\u0005A\u0000\u0000\u00ac\u00ad\u0005\b\u0000\u0000\u00ad"+
		"\u00ae\u0005\u0002\u0000\u0000\u00ae\u00af\u0003\u001e\u000f\u0000\u00af"+
		"\u00b0\u0005\u0003\u0000\u0000\u00b0\u00b1\u0007\u0002\u0000\u0000\u00b1"+
		"\u00d5\u0001\u0000\u0000\u0000\u00b2\u00b3\u00054\u0000\u0000\u00b3\u00b4"+
		"\u0005A\u0000\u0000\u00b4\u00b5\u0005\u0007\u0000\u0000\u00b5\u00b6\u0005"+
		"A\u0000\u0000\u00b6\u00b7\u0005\t\u0000\u0000\u00b7\u00b8\u0005A\u0000"+
		"\u0000\u00b8\u00b9\u0005\b\u0000\u0000\u00b9\u00ba\u0005\u0002\u0000\u0000"+
		"\u00ba\u00bb\u0003\u001e\u000f\u0000\u00bb\u00bc\u0005\u0003\u0000\u0000"+
		"\u00bc\u00bd\u0007\u0002\u0000\u0000\u00bd\u00d5\u0001\u0000\u0000\u0000"+
		"\u00be\u00bf\u00054\u0000\u0000\u00bf\u00c0\u0005A\u0000\u0000\u00c0\u00c1"+
		"\u0005\u0003\u0000\u0000\u00c1\u00c2\u0007\u0002\u0000\u0000\u00c2\u00c3"+
		"\u0005\u0007\u0000\u0000\u00c3\u00c4\u0005D\u0000\u0000\u00c4\u00d5\u0005"+
		"\b\u0000\u0000\u00c5\u00c6\u00055\u0000\u0000\u00c6\u00c7\u0005A\u0000"+
		"\u0000\u00c7\u00c8\u0005\u0007\u0000\u0000\u00c8\u00c9\u0005A\u0000\u0000"+
		"\u00c9\u00ca\u0005\n\u0000\u0000\u00ca\u00d5\u0003$\u0012\u0000\u00cb"+
		"\u00cc\u00055\u0000\u0000\u00cc\u00cd\u0005A\u0000\u0000\u00cd\u00ce\u0005"+
		"\u0007\u0000\u0000\u00ce\u00cf\u0005A\u0000\u0000\u00cf\u00d0\u0005\t"+
		"\u0000\u0000\u00d0\u00d1\u0005A\u0000\u0000\u00d1\u00d2\u0005\b\u0000"+
		"\u0000\u00d2\u00d3\u0005\u0002\u0000\u0000\u00d3\u00d5\u0003$\u0012\u0000"+
		"\u00d4\u0091\u0001\u0000\u0000\u0000\u00d4\u0098\u0001\u0000\u0000\u0000"+
		"\u00d4\u00a1\u0001\u0000\u0000\u0000\u00d4\u00a8\u0001\u0000\u0000\u0000"+
		"\u00d4\u00b2\u0001\u0000\u0000\u0000\u00d4\u00be\u0001\u0000\u0000\u0000"+
		"\u00d4\u00c5\u0001\u0000\u0000\u0000\u00d4\u00cb\u0001\u0000\u0000\u0000"+
		"\u00d5\r\u0001\u0000\u0000\u0000\u00d6\u00d7\u0005\u000b\u0000\u0000\u00d7"+
		"\u00d8\u0003&\u0013\u0000\u00d8\u00de\u0005\f\u0000\u0000\u00d9\u00da"+
		"\u0003\u0004\u0002\u0000\u00da\u00db\u0005\u0001\u0000\u0000\u00db\u00dd"+
		"\u0001\u0000\u0000\u0000\u00dc\u00d9\u0001\u0000\u0000\u0000\u00dd\u00e0"+
		"\u0001\u0000\u0000\u0000\u00de\u00dc\u0001\u0000\u0000\u0000\u00de\u00df"+
		"\u0001\u0000\u0000\u0000\u00df\u00e5\u0001\u0000\u0000\u0000\u00e0\u00de"+
		"\u0001\u0000\u0000\u0000\u00e1\u00e3\u0003\u0004\u0002\u0000\u00e2\u00e4"+
		"\u0005\u0001\u0000\u0000\u00e3\u00e2\u0001\u0000\u0000\u0000\u00e3\u00e4"+
		"\u0001\u0000\u0000\u0000\u00e4\u00e6\u0001\u0000\u0000\u0000\u00e5\u00e1"+
		"\u0001\u0000\u0000\u0000\u00e5\u00e6\u0001\u0000\u0000\u0000\u00e6\u00e7"+
		"\u0001\u0000\u0000\u0000\u00e7\u00e8\u0003\u0010\b\u0000\u00e8\u00e9\u0005"+
		"\r\u0000\u0000\u00e9\u00fe\u0001\u0000\u0000\u0000\u00ea\u00eb\u0005\u000b"+
		"\u0000\u0000\u00eb\u00ec\u0003&\u0013\u0000\u00ec\u00f2\u0005\f\u0000"+
		"\u0000\u00ed\u00ee\u0003\u0004\u0002\u0000\u00ee\u00ef\u0005\u0001\u0000"+
		"\u0000\u00ef\u00f1\u0001\u0000\u0000\u0000\u00f0\u00ed\u0001\u0000\u0000"+
		"\u0000\u00f1\u00f4\u0001\u0000\u0000\u0000\u00f2\u00f0\u0001\u0000\u0000"+
		"\u0000\u00f2\u00f3\u0001\u0000\u0000\u0000\u00f3\u00f9\u0001\u0000\u0000"+
		"\u0000\u00f4\u00f2\u0001\u0000\u0000\u0000\u00f5\u00f7\u0003\u0004\u0002"+
		"\u0000\u00f6\u00f8\u0005\u0001\u0000\u0000\u00f7\u00f6\u0001\u0000\u0000"+
		"\u0000\u00f7\u00f8\u0001\u0000\u0000\u0000\u00f8\u00fa\u0001\u0000\u0000"+
		"\u0000\u00f9\u00f5\u0001\u0000\u0000\u0000\u00f9\u00fa\u0001\u0000\u0000"+
		"\u0000\u00fa\u00fb\u0001\u0000\u0000\u0000\u00fb\u00fc\u0005\r\u0000\u0000"+
		"\u00fc\u00fe\u0001\u0000\u0000\u0000\u00fd\u00d6\u0001\u0000\u0000\u0000"+
		"\u00fd\u00ea\u0001\u0000\u0000\u0000\u00fe\u000f\u0001\u0000\u0000\u0000"+
		"\u00ff\u0100\u0005\u000e\u0000\u0000\u0100\u0101\u0003&\u0013\u0000\u0101"+
		"\u0107\u0005\f\u0000\u0000\u0102\u0103\u0003\u0004\u0002\u0000\u0103\u0104"+
		"\u0005\u0001\u0000\u0000\u0104\u0106\u0001\u0000\u0000\u0000\u0105\u0102"+
		"\u0001\u0000\u0000\u0000\u0106\u0109\u0001\u0000\u0000\u0000\u0107\u0105"+
		"\u0001\u0000\u0000\u0000\u0107\u0108\u0001\u0000\u0000\u0000\u0108\u010e"+
		"\u0001\u0000\u0000\u0000\u0109\u0107\u0001\u0000\u0000\u0000\u010a\u010c"+
		"\u0003\u0004\u0002\u0000\u010b\u010d\u0005\u0001\u0000\u0000\u010c\u010b"+
		"\u0001\u0000\u0000\u0000\u010c\u010d\u0001\u0000\u0000\u0000\u010d\u010f"+
		"\u0001\u0000\u0000\u0000\u010e\u010a\u0001\u0000\u0000\u0000\u010e\u010f"+
		"\u0001\u0000\u0000\u0000\u010f\u0111\u0001\u0000\u0000\u0000\u0110\u0112"+
		"\u0003\u0010\b\u0000\u0111\u0110\u0001\u0000\u0000\u0000\u0111\u0112\u0001"+
		"\u0000\u0000\u0000\u0112\u0123\u0001\u0000\u0000\u0000\u0113\u0119\u0005"+
		"\u000f\u0000\u0000\u0114\u0115\u0003\u0004\u0002\u0000\u0115\u0116\u0005"+
		"\u0001\u0000\u0000\u0116\u0118\u0001\u0000\u0000\u0000\u0117\u0114\u0001"+
		"\u0000\u0000\u0000\u0118\u011b\u0001\u0000\u0000\u0000\u0119\u0117\u0001"+
		"\u0000\u0000\u0000\u0119\u011a\u0001\u0000\u0000\u0000\u011a\u0120\u0001"+
		"\u0000\u0000\u0000\u011b\u0119\u0001\u0000\u0000\u0000\u011c\u011e\u0003"+
		"\u0004\u0002\u0000\u011d\u011f\u0005\u0001\u0000\u0000\u011e\u011d\u0001"+
		"\u0000\u0000\u0000\u011e\u011f\u0001\u0000\u0000\u0000\u011f\u0121\u0001"+
		"\u0000\u0000\u0000\u0120\u011c\u0001\u0000\u0000\u0000\u0120\u0121\u0001"+
		"\u0000\u0000\u0000\u0121\u0123\u0001\u0000\u0000\u0000\u0122\u00ff\u0001"+
		"\u0000\u0000\u0000\u0122\u0113\u0001\u0000\u0000\u0000\u0123\u0011\u0001"+
		"\u0000\u0000\u0000\u0124\u012a\u0005\u0010\u0000\u0000\u0125\u0126\u0003"+
		"\u0004\u0002\u0000\u0126\u0127\u0005\u0001\u0000\u0000\u0127\u0129\u0001"+
		"\u0000\u0000\u0000\u0128\u0125\u0001\u0000\u0000\u0000\u0129\u012c\u0001"+
		"\u0000\u0000\u0000\u012a\u0128\u0001\u0000\u0000\u0000\u012a\u012b\u0001"+
		"\u0000\u0000\u0000\u012b\u0131\u0001\u0000\u0000\u0000\u012c\u012a\u0001"+
		"\u0000\u0000\u0000\u012d\u012f\u0003\u0004\u0002\u0000\u012e\u0130\u0005"+
		"\u0001\u0000\u0000\u012f\u012e\u0001\u0000\u0000\u0000\u012f\u0130\u0001"+
		"\u0000\u0000\u0000\u0130\u0132\u0001\u0000\u0000\u0000\u0131\u012d\u0001"+
		"\u0000\u0000\u0000\u0131\u0132\u0001\u0000\u0000\u0000\u0132\u0133\u0001"+
		"\u0000\u0000\u0000\u0133\u0147\u0005\r\u0000\u0000\u0134\u013a\u0005\u0010"+
		"\u0000\u0000\u0135\u0136\u0003\u0004\u0002\u0000\u0136\u0137\u0005\u0001"+
		"\u0000\u0000\u0137\u0139\u0001\u0000\u0000\u0000\u0138\u0135\u0001\u0000"+
		"\u0000\u0000\u0139\u013c\u0001\u0000\u0000\u0000\u013a\u0138\u0001\u0000"+
		"\u0000\u0000\u013a\u013b\u0001\u0000\u0000\u0000\u013b\u0141\u0001\u0000"+
		"\u0000\u0000\u013c\u013a\u0001\u0000\u0000\u0000\u013d\u013f\u0003\u0004"+
		"\u0002\u0000\u013e\u0140\u0005\u0001\u0000\u0000\u013f\u013e\u0001\u0000"+
		"\u0000\u0000\u013f\u0140\u0001\u0000\u0000\u0000\u0140\u0142\u0001\u0000"+
		"\u0000\u0000\u0141\u013d\u0001\u0000\u0000\u0000\u0141\u0142\u0001\u0000"+
		"\u0000\u0000\u0142\u0143\u0001\u0000\u0000\u0000\u0143\u0144\u0003\u0014"+
		"\n\u0000\u0144\u0145\u0005\r\u0000\u0000\u0145\u0147\u0001\u0000\u0000"+
		"\u0000\u0146\u0124\u0001\u0000\u0000\u0000\u0146\u0134\u0001\u0000\u0000"+
		"\u0000\u0147\u0013\u0001\u0000\u0000\u0000\u0148\u014e\u0005\u0011\u0000"+
		"\u0000\u0149\u014a\u0003\u0004\u0002\u0000\u014a\u014b\u0005\u0001\u0000"+
		"\u0000\u014b\u014d\u0001\u0000\u0000\u0000\u014c\u0149\u0001\u0000\u0000"+
		"\u0000\u014d\u0150\u0001\u0000\u0000\u0000\u014e\u014c\u0001\u0000\u0000"+
		"\u0000\u014e\u014f\u0001\u0000\u0000\u0000\u014f\u0155\u0001\u0000\u0000"+
		"\u0000\u0150\u014e\u0001\u0000\u0000\u0000\u0151\u0153\u0003\u0004\u0002"+
		"\u0000\u0152\u0154\u0005\u0001\u0000\u0000\u0153\u0152\u0001\u0000\u0000"+
		"\u0000\u0153\u0154\u0001\u0000\u0000\u0000\u0154\u0156\u0001\u0000\u0000"+
		"\u0000\u0155\u0151\u0001\u0000\u0000\u0000\u0155\u0156\u0001\u0000\u0000"+
		"\u0000\u0156\u0165\u0001\u0000\u0000\u0000\u0157\u015d\u0005\u0011\u0000"+
		"\u0000\u0158\u0159\u0003\u0004\u0002\u0000\u0159\u015a\u0005\u0001\u0000"+
		"\u0000\u015a\u015c\u0001\u0000\u0000\u0000\u015b\u0158\u0001\u0000\u0000"+
		"\u0000\u015c\u015f\u0001\u0000\u0000\u0000\u015d\u015b\u0001\u0000\u0000"+
		"\u0000\u015d\u015e\u0001\u0000\u0000\u0000\u015e\u0160\u0001\u0000\u0000"+
		"\u0000\u015f\u015d\u0001\u0000\u0000\u0000\u0160\u0162\u0005\u0012\u0000"+
		"\u0000\u0161\u0163\u0005\u0001\u0000\u0000\u0162\u0161\u0001\u0000\u0000"+
		"\u0000\u0162\u0163\u0001\u0000\u0000\u0000\u0163\u0165\u0001\u0000\u0000"+
		"\u0000\u0164\u0148\u0001\u0000\u0000\u0000\u0164\u0157\u0001\u0000\u0000"+
		"\u0000\u0165\u0015\u0001\u0000\u0000\u0000\u0166\u0167\u0005\u0013\u0000"+
		"\u0000\u0167\u0168\u0005A\u0000\u0000\u0168\u0169\u0005\u0002\u0000\u0000"+
		"\u0169\u016a\u0003$\u0012\u0000\u016a\u016b\u0005\u0014\u0000\u0000\u016b"+
		"\u016c\u0003$\u0012\u0000\u016c\u0172\u0005\u0015\u0000\u0000\u016d\u016e"+
		"\u0003\u0004\u0002\u0000\u016e\u016f\u0005\u0001\u0000\u0000\u016f\u0171"+
		"\u0001\u0000\u0000\u0000\u0170\u016d\u0001\u0000\u0000\u0000\u0171\u0174"+
		"\u0001\u0000\u0000\u0000\u0172\u0170\u0001\u0000\u0000\u0000\u0172\u0173"+
		"\u0001\u0000\u0000\u0000\u0173\u0179\u0001\u0000\u0000\u0000\u0174\u0172"+
		"\u0001\u0000\u0000\u0000\u0175\u0177\u0003\u0004\u0002\u0000\u0176\u0178"+
		"\u0005\u0001\u0000\u0000\u0177\u0176\u0001\u0000\u0000\u0000\u0177\u0178"+
		"\u0001\u0000\u0000\u0000\u0178\u017a\u0001\u0000\u0000\u0000\u0179\u0175"+
		"\u0001\u0000\u0000\u0000\u0179\u017a\u0001\u0000\u0000\u0000\u017a\u017b"+
		"\u0001\u0000\u0000\u0000\u017b\u017c\u0005\r\u0000\u0000\u017c\u0193\u0001"+
		"\u0000\u0000\u0000\u017d\u017e\u0005\u0013\u0000\u0000\u017e\u017f\u0003"+
		"$\u0012\u0000\u017f\u0180\u0005\u0014\u0000\u0000\u0180\u0181\u0003$\u0012"+
		"\u0000\u0181\u0187\u0005\u0015\u0000\u0000\u0182\u0183\u0003\u0004\u0002"+
		"\u0000\u0183\u0184\u0005\u0001\u0000\u0000\u0184\u0186\u0001\u0000\u0000"+
		"\u0000\u0185\u0182\u0001\u0000\u0000\u0000\u0186\u0189\u0001\u0000\u0000"+
		"\u0000\u0187\u0185\u0001\u0000\u0000\u0000\u0187\u0188\u0001\u0000\u0000"+
		"\u0000\u0188\u018e\u0001\u0000\u0000\u0000\u0189\u0187\u0001\u0000\u0000"+
		"\u0000\u018a\u018c\u0003\u0004\u0002\u0000\u018b\u018d\u0005\u0001\u0000"+
		"\u0000\u018c\u018b\u0001\u0000\u0000\u0000\u018c\u018d\u0001\u0000\u0000"+
		"\u0000\u018d\u018f\u0001\u0000\u0000\u0000\u018e\u018a\u0001\u0000\u0000"+
		"\u0000\u018e\u018f\u0001\u0000\u0000\u0000\u018f\u0190\u0001\u0000\u0000"+
		"\u0000\u0190\u0191\u0005\r\u0000\u0000\u0191\u0193\u0001\u0000\u0000\u0000"+
		"\u0192\u0166\u0001\u0000\u0000\u0000\u0192\u017d\u0001\u0000\u0000\u0000"+
		"\u0193\u0017\u0001\u0000\u0000\u0000\u0194\u0195\u0005\u0016\u0000\u0000"+
		"\u0195\u0196\u0003&\u0013\u0000\u0196\u019c\u0005\u0015\u0000\u0000\u0197"+
		"\u0198\u0003\u0004\u0002\u0000\u0198\u0199\u0005\u0001\u0000\u0000\u0199"+
		"\u019b\u0001\u0000\u0000\u0000\u019a\u0197\u0001\u0000\u0000\u0000\u019b"+
		"\u019e\u0001\u0000\u0000\u0000\u019c\u019a\u0001\u0000\u0000\u0000\u019c"+
		"\u019d\u0001\u0000\u0000\u0000\u019d\u01a3\u0001\u0000\u0000\u0000\u019e"+
		"\u019c\u0001\u0000\u0000\u0000\u019f\u01a1\u0003\u0004\u0002\u0000\u01a0"+
		"\u01a2\u0005\u0001\u0000\u0000\u01a1\u01a0\u0001\u0000\u0000\u0000\u01a1"+
		"\u01a2\u0001\u0000\u0000\u0000\u01a2\u01a4\u0001\u0000\u0000\u0000\u01a3"+
		"\u019f\u0001\u0000\u0000\u0000\u01a3\u01a4\u0001\u0000\u0000\u0000\u01a4"+
		"\u01a5\u0001\u0000\u0000\u0000\u01a5\u01a6\u0005\r\u0000\u0000\u01a6\u01bb"+
		"\u0001\u0000\u0000\u0000\u01a7\u01a8\u0005\u0017\u0000\u0000\u01a8\u01a9"+
		"\u0003&\u0013\u0000\u01a9\u01af\u0005\u0015\u0000\u0000\u01aa\u01ab\u0003"+
		"\u0004\u0002\u0000\u01ab\u01ac\u0005\u0001\u0000\u0000\u01ac\u01ae\u0001"+
		"\u0000\u0000\u0000\u01ad\u01aa\u0001\u0000\u0000\u0000\u01ae\u01b1\u0001"+
		"\u0000\u0000\u0000\u01af\u01ad\u0001\u0000\u0000\u0000\u01af\u01b0\u0001"+
		"\u0000\u0000\u0000\u01b0\u01b6\u0001\u0000\u0000\u0000\u01b1\u01af\u0001"+
		"\u0000\u0000\u0000\u01b2\u01b4\u0003\u0004\u0002\u0000\u01b3\u01b5\u0005"+
		"\u0001\u0000\u0000\u01b4\u01b3\u0001\u0000\u0000\u0000\u01b4\u01b5\u0001"+
		"\u0000\u0000\u0000\u01b5\u01b7\u0001\u0000\u0000\u0000\u01b6\u01b2\u0001"+
		"\u0000\u0000\u0000\u01b6\u01b7\u0001\u0000\u0000\u0000\u01b7\u01b8\u0001"+
		"\u0000\u0000\u0000\u01b8\u01b9\u0005\r\u0000\u0000\u01b9\u01bb\u0001\u0000"+
		"\u0000\u0000\u01ba\u0194\u0001\u0000\u0000\u0000\u01ba\u01a7\u0001\u0000"+
		"\u0000\u0000\u01bb\u0019\u0001\u0000\u0000\u0000\u01bc\u01bd\u00058\u0000"+
		"\u0000\u01bd\u01be\u0003&\u0013\u0000\u01be\u001b\u0001\u0000\u0000\u0000"+
		"\u01bf\u01c0\u0003 \u0010\u0000\u01c0\u01c1\u0005\u0018\u0000\u0000\u01c1"+
		"\u01c2\u0005A\u0000\u0000\u01c2\u001d\u0001\u0000\u0000\u0000\u01c3\u01c4"+
		"\u0006\u000f\uffff\uffff\u0000\u01c4\u01c5\u0005A\u0000\u0000\u01c5\u01cb"+
		"\u0001\u0000\u0000\u0000\u01c6\u01c7\n\u0002\u0000\u0000\u01c7\u01c8\u0007"+
		"\u0003\u0000\u0000\u01c8\u01ca\u0003\u001e\u000f\u0003\u01c9\u01c6\u0001"+
		"\u0000\u0000\u0000\u01ca\u01cd\u0001\u0000\u0000\u0000\u01cb\u01c9\u0001"+
		"\u0000\u0000\u0000\u01cb\u01cc\u0001\u0000\u0000\u0000\u01cc\u001f\u0001"+
		"\u0000\u0000\u0000\u01cd\u01cb\u0001\u0000\u0000\u0000\u01ce\u01cf\u0006"+
		"\u0010\uffff\uffff\u0000\u01cf\u01d0\u00056\u0000\u0000\u01d0\u01d1\u0005"+
		"\u001b\u0000\u0000\u01d1\u01d2\u0003 \u0010\u0000\u01d2\u01d3\u0005\t"+
		"\u0000\u0000\u01d3\u01d4\u0003$\u0012\u0000\u01d4\u01d5\u0005\t\u0000"+
		"\u0000\u01d5\u01d6\u0007\u0004\u0000\u0000\u01d6\u01d7\u0005\u001f\u0000"+
		"\u0000\u01d7\u01e9\u0001\u0000\u0000\u0000\u01d8\u01d9\u00056\u0000\u0000"+
		"\u01d9\u01da\u0005\u001b\u0000\u0000\u01da\u01db\u0003 \u0010\u0000\u01db"+
		"\u01dc\u0005\t\u0000\u0000\u01dc\u01dd\u0003$\u0012\u0000\u01dd\u01de"+
		"\u0005\u001f\u0000\u0000\u01de\u01e9\u0001\u0000\u0000\u0000\u01df\u01e0"+
		"\u0005A\u0000\u0000\u01e0\u01e1\u0005\u0007\u0000\u0000\u01e1\u01e2\u0003"+
		"$\u0012\u0000\u01e2\u01e3\u0005\b\u0000\u0000\u01e3\u01e9\u0001\u0000"+
		"\u0000\u0000\u01e4\u01e9\u0005A\u0000\u0000\u01e5\u01e9\u0003\"\u0011"+
		"\u0000\u01e6\u01e9\u0003$\u0012\u0000\u01e7\u01e9\u0003&\u0013\u0000\u01e8"+
		"\u01ce\u0001\u0000\u0000\u0000\u01e8\u01d8\u0001\u0000\u0000\u0000\u01e8"+
		"\u01df\u0001\u0000\u0000\u0000\u01e8\u01e4\u0001\u0000\u0000\u0000\u01e8"+
		"\u01e5\u0001\u0000\u0000\u0000\u01e8\u01e6\u0001\u0000\u0000\u0000\u01e8"+
		"\u01e7\u0001\u0000\u0000\u0000\u01e9\u01ef\u0001\u0000\u0000\u0000\u01ea"+
		"\u01eb\n\b\u0000\u0000\u01eb\u01ec\u0005\t\u0000\u0000\u01ec\u01ee\u0003"+
		" \u0010\t\u01ed\u01ea\u0001\u0000\u0000\u0000\u01ee\u01f1\u0001\u0000"+
		"\u0000\u0000\u01ef\u01ed\u0001\u0000\u0000\u0000\u01ef\u01f0\u0001\u0000"+
		"\u0000\u0000\u01f0!\u0001\u0000\u0000\u0000\u01f1\u01ef\u0001\u0000\u0000"+
		"\u0000\u01f2\u0201\u00050\u0000\u0000\u01f3\u01f4\u0005;\u0000\u0000\u01f4"+
		"\u01f5\u0005\u001b\u0000\u0000\u01f5\u01f6\u0003 \u0010\u0000\u01f6\u01f7"+
		"\u0005\u001f\u0000\u0000\u01f7\u0201\u0001\u0000\u0000\u0000\u01f8\u01f9"+
		"\u00051\u0000\u0000\u01f9\u0201\u0003\"\u0011\u0000\u01fa\u01fb\u0005"+
		"A\u0000\u0000\u01fb\u01fc\u0005\u0007\u0000\u0000\u01fc\u01fd\u0003$\u0012"+
		"\u0000\u01fd\u01fe\u0005\b\u0000\u0000\u01fe\u0201\u0001\u0000\u0000\u0000"+
		"\u01ff\u0201\u0005A\u0000\u0000\u0200\u01f2\u0001\u0000\u0000\u0000\u0200"+
		"\u01f3\u0001\u0000\u0000\u0000\u0200\u01f8\u0001\u0000\u0000\u0000\u0200"+
		"\u01fa\u0001\u0000\u0000\u0000\u0200\u01ff\u0001\u0000\u0000\u0000\u0201"+
		"#\u0001\u0000\u0000\u0000\u0202\u0203\u0006\u0012\uffff\uffff\u0000\u0203"+
		"\u0204\u0005\u001b\u0000\u0000\u0204\u0205\u0003 \u0010\u0000\u0205\u0206"+
		"\u0005\u001f\u0000\u0000\u0206\u0226\u0001\u0000\u0000\u0000\u0207\u0208"+
		"\u0007\u0005\u0000\u0000\u0208\u0226\u0003$\u0012\n\u0209\u020a\u0005"+
		"A\u0000\u0000\u020a\u020b\u0005\u0007\u0000\u0000\u020b\u020c\u0003$\u0012"+
		"\u0000\u020c\u020d\u0005\b\u0000\u0000\u020d\u0226\u0001\u0000\u0000\u0000"+
		"\u020e\u020f\u00057\u0000\u0000\u020f\u0210\u0005\u001b\u0000\u0000\u0210"+
		"\u0211\u0005A\u0000\u0000\u0211\u0226\u0005\u001f\u0000\u0000\u0212\u0213"+
		"\u0005<\u0000\u0000\u0213\u0214\u0005\u001b\u0000\u0000\u0214\u0215\u0003"+
		" \u0010\u0000\u0215\u0216\u0005\u001f\u0000\u0000\u0216\u0226\u0001\u0000"+
		"\u0000\u0000\u0217\u0218\u0005=\u0000\u0000\u0218\u0219\u0005\u001b\u0000"+
		"\u0000\u0219\u021a\u0003 \u0010\u0000\u021a\u021b\u0005\u001f\u0000\u0000"+
		"\u021b\u0226\u0001\u0000\u0000\u0000\u021c\u021d\u0005A\u0000\u0000\u021d"+
		"\u021e\u0005\u001b\u0000\u0000\u021e\u021f\u0003 \u0010\u0000\u021f\u0220"+
		"\u0005\u001f\u0000\u0000\u0220\u0226\u0001\u0000\u0000\u0000\u0221\u0226"+
		"\u0005D\u0000\u0000\u0222\u0226\u0005B\u0000\u0000\u0223\u0226\u0005C"+
		"\u0000\u0000\u0224\u0226\u0005A\u0000\u0000\u0225\u0202\u0001\u0000\u0000"+
		"\u0000\u0225\u0207\u0001\u0000\u0000\u0000\u0225\u0209\u0001\u0000\u0000"+
		"\u0000\u0225\u020e\u0001\u0000\u0000\u0000\u0225\u0212\u0001\u0000\u0000"+
		"\u0000\u0225\u0217\u0001\u0000\u0000\u0000\u0225\u021c\u0001\u0000\u0000"+
		"\u0000\u0225\u0221\u0001\u0000\u0000\u0000\u0225\u0222\u0001\u0000\u0000"+
		"\u0000\u0225\u0223\u0001\u0000\u0000\u0000\u0225\u0224\u0001\u0000\u0000"+
		"\u0000\u0226\u0237\u0001\u0000\u0000\u0000\u0227\u0228\n\u000e\u0000\u0000"+
		"\u0228\u0229\u0005\u0019\u0000\u0000\u0229\u0236\u0003$\u0012\u000f\u022a"+
		"\u022b\n\r\u0000\u0000\u022b\u022c\u0005\u001a\u0000\u0000\u022c\u0236"+
		"\u0003$\u0012\u000e\u022d\u022e\n\f\u0000\u0000\u022e\u022f\u0007\u0006"+
		"\u0000\u0000\u022f\u0236\u0003$\u0012\r\u0230\u0231\n\u000b\u0000\u0000"+
		"\u0231\u0232\u0007\u0005\u0000\u0000\u0232\u0236\u0003$\u0012\f\u0233"+
		"\u0234\n\u000f\u0000\u0000\u0234\u0236\u0005A\u0000\u0000\u0235\u0227"+
		"\u0001\u0000\u0000\u0000\u0235\u022a\u0001\u0000\u0000\u0000\u0235\u022d"+
		"\u0001\u0000\u0000\u0000\u0235\u0230\u0001\u0000\u0000\u0000\u0235\u0233"+
		"\u0001\u0000\u0000\u0000\u0236\u0239\u0001\u0000\u0000\u0000\u0237\u0235"+
		"\u0001\u0000\u0000\u0000\u0237\u0238\u0001\u0000\u0000\u0000\u0238%\u0001"+
		"\u0000\u0000\u0000\u0239\u0237\u0001\u0000\u0000\u0000\u023a\u023b\u0006"+
		"\u0013\uffff\uffff\u0000\u023b\u023c\u0005$\u0000\u0000\u023c\u025a\u0003"+
		"&\u0013\u000f\u023d\u023e\u0005\u001b\u0000\u0000\u023e\u023f\u0003&\u0013"+
		"\u0000\u023f\u0240\u0005\u001f\u0000\u0000\u0240\u025a\u0001\u0000\u0000"+
		"\u0000\u0241\u0242\u0003$\u0012\u0000\u0242\u0243\u0005&\u0000\u0000\u0243"+
		"\u0244\u0003$\u0012\u0000\u0244\u025a\u0001\u0000\u0000\u0000\u0245\u0246"+
		"\u0003$\u0012\u0000\u0246\u0247\u0005\'\u0000\u0000\u0247\u0248\u0003"+
		"$\u0012\u0000\u0248\u025a\u0001\u0000\u0000\u0000\u0249\u024a\u0003$\u0012"+
		"\u0000\u024a\u024b\u0005(\u0000\u0000\u024b\u024c\u0003$\u0012\u0000\u024c"+
		"\u025a\u0001\u0000\u0000\u0000\u024d\u024e\u0003$\u0012\u0000\u024e\u024f"+
		"\u0005)\u0000\u0000\u024f\u0250\u0003$\u0012\u0000\u0250\u025a\u0001\u0000"+
		"\u0000\u0000\u0251\u0252\u0005A\u0000\u0000\u0252\u0253\u0005\u0007\u0000"+
		"\u0000\u0253\u0254\u0007\u0007\u0000\u0000\u0254\u025a\u0005\b\u0000\u0000"+
		"\u0255\u025a\u0005A\u0000\u0000\u0256\u025a\u0007\b\u0000\u0000\u0257"+
		"\u025a\u0003$\u0012\u0000\u0258\u025a\u0003\"\u0011\u0000\u0259\u023a"+
		"\u0001\u0000\u0000\u0000\u0259\u023d\u0001\u0000\u0000\u0000\u0259\u0241"+
		"\u0001\u0000\u0000\u0000\u0259\u0245\u0001\u0000\u0000\u0000\u0259\u0249"+
		"\u0001\u0000\u0000\u0000\u0259\u024d\u0001\u0000\u0000\u0000\u0259\u0251"+
		"\u0001\u0000\u0000\u0000\u0259\u0255\u0001\u0000\u0000\u0000\u0259\u0256"+
		"\u0001\u0000\u0000\u0000\u0259\u0257\u0001\u0000\u0000\u0000\u0259\u0258"+
		"\u0001\u0000\u0000\u0000\u025a\u0269\u0001\u0000\u0000\u0000\u025b\u025c"+
		"\n\r\u0000\u0000\u025c\u025d\u0005%\u0000\u0000\u025d\u0268\u0003&\u0013"+
		"\u000e\u025e\u025f\n\b\u0000\u0000\u025f\u0260\u0005*\u0000\u0000\u0260"+
		"\u0268\u0003&\u0013\t\u0261\u0262\n\u0007\u0000\u0000\u0262\u0263\u0005"+
		"+\u0000\u0000\u0263\u0268\u0003&\u0013\b\u0264\u0265\n\u0006\u0000\u0000"+
		"\u0265\u0266\u0005,\u0000\u0000\u0266\u0268\u0003&\u0013\u0007\u0267\u025b"+
		"\u0001\u0000\u0000\u0000\u0267\u025e\u0001\u0000\u0000\u0000\u0267\u0261"+
		"\u0001\u0000\u0000\u0000\u0267\u0264\u0001\u0000\u0000\u0000\u0268\u026b"+
		"\u0001\u0000\u0000\u0000\u0269\u0267\u0001\u0000\u0000\u0000\u0269\u026a"+
		"\u0001\u0000\u0000\u0000\u026a\'\u0001\u0000\u0000\u0000\u026b\u0269\u0001"+
		"\u0000\u0000\u0000?-24COWYx\u008d\u008f\u00d4\u00de\u00e3\u00e5\u00f2"+
		"\u00f7\u00f9\u00fd\u0107\u010c\u010e\u0111\u0119\u011e\u0120\u0122\u012a"+
		"\u012f\u0131\u013a\u013f\u0141\u0146\u014e\u0153\u0155\u015d\u0162\u0164"+
		"\u0172\u0177\u0179\u0187\u018c\u018e\u0192\u019c\u01a1\u01a3\u01af\u01b4"+
		"\u01b6\u01ba\u01cb\u01e8\u01ef\u0200\u0225\u0235\u0237\u0259\u0267\u0269";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}