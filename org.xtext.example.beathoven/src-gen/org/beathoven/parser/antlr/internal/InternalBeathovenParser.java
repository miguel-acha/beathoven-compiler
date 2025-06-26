package org.beathoven.parser.antlr.internal;

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import org.beathoven.services.BeathovenGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalBeathovenParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_INT", "RULE_FRACTION", "RULE_STRING", "RULE_ID", "RULE_NOTE_TOKEN", "RULE_DUR_TOKEN", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'TEMPO'", "'TIME'", "'KEY'", "'TITLE'", "'INSTRUMENT'", "'R'", "'['", "','", "']'", "'REPEAT'", "'{'", "'}'", "'MARK'", "'GOTO'"
    };
    public static final int RULE_DUR_TOKEN=9;
    public static final int RULE_STRING=6;
    public static final int RULE_NOTE_TOKEN=8;
    public static final int RULE_SL_COMMENT=11;
    public static final int T__19=19;
    public static final int T__15=15;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__14=14;
    public static final int EOF=-1;
    public static final int RULE_ID=7;
    public static final int RULE_WS=12;
    public static final int RULE_ANY_OTHER=13;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int RULE_INT=4;
    public static final int T__22=22;
    public static final int RULE_FRACTION=5;
    public static final int RULE_ML_COMMENT=10;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__20=20;
    public static final int T__21=21;

    // delegates
    // delegators


        public InternalBeathovenParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalBeathovenParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalBeathovenParser.tokenNames; }
    public String getGrammarFileName() { return "InternalBeathoven.g"; }



     	private BeathovenGrammarAccess grammarAccess;

        public InternalBeathovenParser(TokenStream input, BeathovenGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }

        @Override
        protected String getFirstRuleName() {
        	return "Model";
       	}

       	@Override
       	protected BeathovenGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}




    // $ANTLR start "entryRuleModel"
    // InternalBeathoven.g:64:1: entryRuleModel returns [EObject current=null] : iv_ruleModel= ruleModel EOF ;
    public final EObject entryRuleModel() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleModel = null;


        try {
            // InternalBeathoven.g:64:46: (iv_ruleModel= ruleModel EOF )
            // InternalBeathoven.g:65:2: iv_ruleModel= ruleModel EOF
            {
             newCompositeNode(grammarAccess.getModelRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleModel=ruleModel();

            state._fsp--;

             current =iv_ruleModel; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleModel"


    // $ANTLR start "ruleModel"
    // InternalBeathoven.g:71:1: ruleModel returns [EObject current=null] : ( (lv_blocks_0_0= ruleBlock ) )* ;
    public final EObject ruleModel() throws RecognitionException {
        EObject current = null;

        EObject lv_blocks_0_0 = null;



        	enterRule();

        try {
            // InternalBeathoven.g:77:2: ( ( (lv_blocks_0_0= ruleBlock ) )* )
            // InternalBeathoven.g:78:2: ( (lv_blocks_0_0= ruleBlock ) )*
            {
            // InternalBeathoven.g:78:2: ( (lv_blocks_0_0= ruleBlock ) )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==RULE_NOTE_TOKEN||(LA1_0>=14 && LA1_0<=20)||LA1_0==23||(LA1_0>=26 && LA1_0<=27)) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // InternalBeathoven.g:79:3: (lv_blocks_0_0= ruleBlock )
            	    {
            	    // InternalBeathoven.g:79:3: (lv_blocks_0_0= ruleBlock )
            	    // InternalBeathoven.g:80:4: lv_blocks_0_0= ruleBlock
            	    {

            	    				newCompositeNode(grammarAccess.getModelAccess().getBlocksBlockParserRuleCall_0());
            	    			
            	    pushFollow(FOLLOW_3);
            	    lv_blocks_0_0=ruleBlock();

            	    state._fsp--;


            	    				if (current==null) {
            	    					current = createModelElementForParent(grammarAccess.getModelRule());
            	    				}
            	    				add(
            	    					current,
            	    					"blocks",
            	    					lv_blocks_0_0,
            	    					"org.beathoven.Beathoven.Block");
            	    				afterParserOrEnumRuleCall();
            	    			

            	    }


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleModel"


    // $ANTLR start "entryRuleBlock"
    // InternalBeathoven.g:100:1: entryRuleBlock returns [EObject current=null] : iv_ruleBlock= ruleBlock EOF ;
    public final EObject entryRuleBlock() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBlock = null;


        try {
            // InternalBeathoven.g:100:46: (iv_ruleBlock= ruleBlock EOF )
            // InternalBeathoven.g:101:2: iv_ruleBlock= ruleBlock EOF
            {
             newCompositeNode(grammarAccess.getBlockRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleBlock=ruleBlock();

            state._fsp--;

             current =iv_ruleBlock; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleBlock"


    // $ANTLR start "ruleBlock"
    // InternalBeathoven.g:107:1: ruleBlock returns [EObject current=null] : (this_Tempo_0= ruleTempo | this_Time_1= ruleTime | this_Key_2= ruleKey | this_Title_3= ruleTitle | this_Instrument_4= ruleInstrument | this_NoteStmt_5= ruleNoteStmt | this_Rest_6= ruleRest | this_Chord_7= ruleChord | this_Repeat_8= ruleRepeat | this_Mark_9= ruleMark | this_Goto_10= ruleGoto ) ;
    public final EObject ruleBlock() throws RecognitionException {
        EObject current = null;

        EObject this_Tempo_0 = null;

        EObject this_Time_1 = null;

        EObject this_Key_2 = null;

        EObject this_Title_3 = null;

        EObject this_Instrument_4 = null;

        EObject this_NoteStmt_5 = null;

        EObject this_Rest_6 = null;

        EObject this_Chord_7 = null;

        EObject this_Repeat_8 = null;

        EObject this_Mark_9 = null;

        EObject this_Goto_10 = null;



        	enterRule();

        try {
            // InternalBeathoven.g:113:2: ( (this_Tempo_0= ruleTempo | this_Time_1= ruleTime | this_Key_2= ruleKey | this_Title_3= ruleTitle | this_Instrument_4= ruleInstrument | this_NoteStmt_5= ruleNoteStmt | this_Rest_6= ruleRest | this_Chord_7= ruleChord | this_Repeat_8= ruleRepeat | this_Mark_9= ruleMark | this_Goto_10= ruleGoto ) )
            // InternalBeathoven.g:114:2: (this_Tempo_0= ruleTempo | this_Time_1= ruleTime | this_Key_2= ruleKey | this_Title_3= ruleTitle | this_Instrument_4= ruleInstrument | this_NoteStmt_5= ruleNoteStmt | this_Rest_6= ruleRest | this_Chord_7= ruleChord | this_Repeat_8= ruleRepeat | this_Mark_9= ruleMark | this_Goto_10= ruleGoto )
            {
            // InternalBeathoven.g:114:2: (this_Tempo_0= ruleTempo | this_Time_1= ruleTime | this_Key_2= ruleKey | this_Title_3= ruleTitle | this_Instrument_4= ruleInstrument | this_NoteStmt_5= ruleNoteStmt | this_Rest_6= ruleRest | this_Chord_7= ruleChord | this_Repeat_8= ruleRepeat | this_Mark_9= ruleMark | this_Goto_10= ruleGoto )
            int alt2=11;
            switch ( input.LA(1) ) {
            case 14:
                {
                alt2=1;
                }
                break;
            case 15:
                {
                alt2=2;
                }
                break;
            case 16:
                {
                alt2=3;
                }
                break;
            case 17:
                {
                alt2=4;
                }
                break;
            case 18:
                {
                alt2=5;
                }
                break;
            case RULE_NOTE_TOKEN:
                {
                alt2=6;
                }
                break;
            case 19:
                {
                alt2=7;
                }
                break;
            case 20:
                {
                alt2=8;
                }
                break;
            case 23:
                {
                alt2=9;
                }
                break;
            case 26:
                {
                alt2=10;
                }
                break;
            case 27:
                {
                alt2=11;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }

            switch (alt2) {
                case 1 :
                    // InternalBeathoven.g:115:3: this_Tempo_0= ruleTempo
                    {

                    			newCompositeNode(grammarAccess.getBlockAccess().getTempoParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_Tempo_0=ruleTempo();

                    state._fsp--;


                    			current = this_Tempo_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalBeathoven.g:124:3: this_Time_1= ruleTime
                    {

                    			newCompositeNode(grammarAccess.getBlockAccess().getTimeParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_Time_1=ruleTime();

                    state._fsp--;


                    			current = this_Time_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 3 :
                    // InternalBeathoven.g:133:3: this_Key_2= ruleKey
                    {

                    			newCompositeNode(grammarAccess.getBlockAccess().getKeyParserRuleCall_2());
                    		
                    pushFollow(FOLLOW_2);
                    this_Key_2=ruleKey();

                    state._fsp--;


                    			current = this_Key_2;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 4 :
                    // InternalBeathoven.g:142:3: this_Title_3= ruleTitle
                    {

                    			newCompositeNode(grammarAccess.getBlockAccess().getTitleParserRuleCall_3());
                    		
                    pushFollow(FOLLOW_2);
                    this_Title_3=ruleTitle();

                    state._fsp--;


                    			current = this_Title_3;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 5 :
                    // InternalBeathoven.g:151:3: this_Instrument_4= ruleInstrument
                    {

                    			newCompositeNode(grammarAccess.getBlockAccess().getInstrumentParserRuleCall_4());
                    		
                    pushFollow(FOLLOW_2);
                    this_Instrument_4=ruleInstrument();

                    state._fsp--;


                    			current = this_Instrument_4;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 6 :
                    // InternalBeathoven.g:160:3: this_NoteStmt_5= ruleNoteStmt
                    {

                    			newCompositeNode(grammarAccess.getBlockAccess().getNoteStmtParserRuleCall_5());
                    		
                    pushFollow(FOLLOW_2);
                    this_NoteStmt_5=ruleNoteStmt();

                    state._fsp--;


                    			current = this_NoteStmt_5;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 7 :
                    // InternalBeathoven.g:169:3: this_Rest_6= ruleRest
                    {

                    			newCompositeNode(grammarAccess.getBlockAccess().getRestParserRuleCall_6());
                    		
                    pushFollow(FOLLOW_2);
                    this_Rest_6=ruleRest();

                    state._fsp--;


                    			current = this_Rest_6;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 8 :
                    // InternalBeathoven.g:178:3: this_Chord_7= ruleChord
                    {

                    			newCompositeNode(grammarAccess.getBlockAccess().getChordParserRuleCall_7());
                    		
                    pushFollow(FOLLOW_2);
                    this_Chord_7=ruleChord();

                    state._fsp--;


                    			current = this_Chord_7;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 9 :
                    // InternalBeathoven.g:187:3: this_Repeat_8= ruleRepeat
                    {

                    			newCompositeNode(grammarAccess.getBlockAccess().getRepeatParserRuleCall_8());
                    		
                    pushFollow(FOLLOW_2);
                    this_Repeat_8=ruleRepeat();

                    state._fsp--;


                    			current = this_Repeat_8;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 10 :
                    // InternalBeathoven.g:196:3: this_Mark_9= ruleMark
                    {

                    			newCompositeNode(grammarAccess.getBlockAccess().getMarkParserRuleCall_9());
                    		
                    pushFollow(FOLLOW_2);
                    this_Mark_9=ruleMark();

                    state._fsp--;


                    			current = this_Mark_9;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 11 :
                    // InternalBeathoven.g:205:3: this_Goto_10= ruleGoto
                    {

                    			newCompositeNode(grammarAccess.getBlockAccess().getGotoParserRuleCall_10());
                    		
                    pushFollow(FOLLOW_2);
                    this_Goto_10=ruleGoto();

                    state._fsp--;


                    			current = this_Goto_10;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleBlock"


    // $ANTLR start "entryRuleTempo"
    // InternalBeathoven.g:217:1: entryRuleTempo returns [EObject current=null] : iv_ruleTempo= ruleTempo EOF ;
    public final EObject entryRuleTempo() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTempo = null;


        try {
            // InternalBeathoven.g:217:46: (iv_ruleTempo= ruleTempo EOF )
            // InternalBeathoven.g:218:2: iv_ruleTempo= ruleTempo EOF
            {
             newCompositeNode(grammarAccess.getTempoRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleTempo=ruleTempo();

            state._fsp--;

             current =iv_ruleTempo; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTempo"


    // $ANTLR start "ruleTempo"
    // InternalBeathoven.g:224:1: ruleTempo returns [EObject current=null] : (otherlv_0= 'TEMPO' ( (lv_value_1_0= RULE_INT ) ) ) ;
    public final EObject ruleTempo() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_value_1_0=null;


        	enterRule();

        try {
            // InternalBeathoven.g:230:2: ( (otherlv_0= 'TEMPO' ( (lv_value_1_0= RULE_INT ) ) ) )
            // InternalBeathoven.g:231:2: (otherlv_0= 'TEMPO' ( (lv_value_1_0= RULE_INT ) ) )
            {
            // InternalBeathoven.g:231:2: (otherlv_0= 'TEMPO' ( (lv_value_1_0= RULE_INT ) ) )
            // InternalBeathoven.g:232:3: otherlv_0= 'TEMPO' ( (lv_value_1_0= RULE_INT ) )
            {
            otherlv_0=(Token)match(input,14,FOLLOW_4); 

            			newLeafNode(otherlv_0, grammarAccess.getTempoAccess().getTEMPOKeyword_0());
            		
            // InternalBeathoven.g:236:3: ( (lv_value_1_0= RULE_INT ) )
            // InternalBeathoven.g:237:4: (lv_value_1_0= RULE_INT )
            {
            // InternalBeathoven.g:237:4: (lv_value_1_0= RULE_INT )
            // InternalBeathoven.g:238:5: lv_value_1_0= RULE_INT
            {
            lv_value_1_0=(Token)match(input,RULE_INT,FOLLOW_2); 

            					newLeafNode(lv_value_1_0, grammarAccess.getTempoAccess().getValueINTTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getTempoRule());
            					}
            					setWithLastConsumed(
            						current,
            						"value",
            						lv_value_1_0,
            						"org.eclipse.xtext.common.Terminals.INT");
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTempo"


    // $ANTLR start "entryRuleTime"
    // InternalBeathoven.g:258:1: entryRuleTime returns [EObject current=null] : iv_ruleTime= ruleTime EOF ;
    public final EObject entryRuleTime() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTime = null;


        try {
            // InternalBeathoven.g:258:45: (iv_ruleTime= ruleTime EOF )
            // InternalBeathoven.g:259:2: iv_ruleTime= ruleTime EOF
            {
             newCompositeNode(grammarAccess.getTimeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleTime=ruleTime();

            state._fsp--;

             current =iv_ruleTime; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTime"


    // $ANTLR start "ruleTime"
    // InternalBeathoven.g:265:1: ruleTime returns [EObject current=null] : (otherlv_0= 'TIME' ( (lv_value_1_0= RULE_FRACTION ) ) ) ;
    public final EObject ruleTime() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_value_1_0=null;


        	enterRule();

        try {
            // InternalBeathoven.g:271:2: ( (otherlv_0= 'TIME' ( (lv_value_1_0= RULE_FRACTION ) ) ) )
            // InternalBeathoven.g:272:2: (otherlv_0= 'TIME' ( (lv_value_1_0= RULE_FRACTION ) ) )
            {
            // InternalBeathoven.g:272:2: (otherlv_0= 'TIME' ( (lv_value_1_0= RULE_FRACTION ) ) )
            // InternalBeathoven.g:273:3: otherlv_0= 'TIME' ( (lv_value_1_0= RULE_FRACTION ) )
            {
            otherlv_0=(Token)match(input,15,FOLLOW_5); 

            			newLeafNode(otherlv_0, grammarAccess.getTimeAccess().getTIMEKeyword_0());
            		
            // InternalBeathoven.g:277:3: ( (lv_value_1_0= RULE_FRACTION ) )
            // InternalBeathoven.g:278:4: (lv_value_1_0= RULE_FRACTION )
            {
            // InternalBeathoven.g:278:4: (lv_value_1_0= RULE_FRACTION )
            // InternalBeathoven.g:279:5: lv_value_1_0= RULE_FRACTION
            {
            lv_value_1_0=(Token)match(input,RULE_FRACTION,FOLLOW_2); 

            					newLeafNode(lv_value_1_0, grammarAccess.getTimeAccess().getValueFractionTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getTimeRule());
            					}
            					setWithLastConsumed(
            						current,
            						"value",
            						lv_value_1_0,
            						"org.beathoven.Beathoven.Fraction");
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTime"


    // $ANTLR start "entryRuleKey"
    // InternalBeathoven.g:299:1: entryRuleKey returns [EObject current=null] : iv_ruleKey= ruleKey EOF ;
    public final EObject entryRuleKey() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKey = null;


        try {
            // InternalBeathoven.g:299:44: (iv_ruleKey= ruleKey EOF )
            // InternalBeathoven.g:300:2: iv_ruleKey= ruleKey EOF
            {
             newCompositeNode(grammarAccess.getKeyRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleKey=ruleKey();

            state._fsp--;

             current =iv_ruleKey; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleKey"


    // $ANTLR start "ruleKey"
    // InternalBeathoven.g:306:1: ruleKey returns [EObject current=null] : (otherlv_0= 'KEY' ( (lv_note_1_0= ruleNoteValue ) ) ) ;
    public final EObject ruleKey() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_note_1_0 = null;



        	enterRule();

        try {
            // InternalBeathoven.g:312:2: ( (otherlv_0= 'KEY' ( (lv_note_1_0= ruleNoteValue ) ) ) )
            // InternalBeathoven.g:313:2: (otherlv_0= 'KEY' ( (lv_note_1_0= ruleNoteValue ) ) )
            {
            // InternalBeathoven.g:313:2: (otherlv_0= 'KEY' ( (lv_note_1_0= ruleNoteValue ) ) )
            // InternalBeathoven.g:314:3: otherlv_0= 'KEY' ( (lv_note_1_0= ruleNoteValue ) )
            {
            otherlv_0=(Token)match(input,16,FOLLOW_6); 

            			newLeafNode(otherlv_0, grammarAccess.getKeyAccess().getKEYKeyword_0());
            		
            // InternalBeathoven.g:318:3: ( (lv_note_1_0= ruleNoteValue ) )
            // InternalBeathoven.g:319:4: (lv_note_1_0= ruleNoteValue )
            {
            // InternalBeathoven.g:319:4: (lv_note_1_0= ruleNoteValue )
            // InternalBeathoven.g:320:5: lv_note_1_0= ruleNoteValue
            {

            					newCompositeNode(grammarAccess.getKeyAccess().getNoteNoteValueParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_2);
            lv_note_1_0=ruleNoteValue();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getKeyRule());
            					}
            					set(
            						current,
            						"note",
            						lv_note_1_0,
            						"org.beathoven.Beathoven.NoteValue");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleKey"


    // $ANTLR start "entryRuleTitle"
    // InternalBeathoven.g:341:1: entryRuleTitle returns [EObject current=null] : iv_ruleTitle= ruleTitle EOF ;
    public final EObject entryRuleTitle() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTitle = null;


        try {
            // InternalBeathoven.g:341:46: (iv_ruleTitle= ruleTitle EOF )
            // InternalBeathoven.g:342:2: iv_ruleTitle= ruleTitle EOF
            {
             newCompositeNode(grammarAccess.getTitleRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleTitle=ruleTitle();

            state._fsp--;

             current =iv_ruleTitle; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTitle"


    // $ANTLR start "ruleTitle"
    // InternalBeathoven.g:348:1: ruleTitle returns [EObject current=null] : (otherlv_0= 'TITLE' ( (lv_name_1_0= RULE_STRING ) ) ) ;
    public final EObject ruleTitle() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;


        	enterRule();

        try {
            // InternalBeathoven.g:354:2: ( (otherlv_0= 'TITLE' ( (lv_name_1_0= RULE_STRING ) ) ) )
            // InternalBeathoven.g:355:2: (otherlv_0= 'TITLE' ( (lv_name_1_0= RULE_STRING ) ) )
            {
            // InternalBeathoven.g:355:2: (otherlv_0= 'TITLE' ( (lv_name_1_0= RULE_STRING ) ) )
            // InternalBeathoven.g:356:3: otherlv_0= 'TITLE' ( (lv_name_1_0= RULE_STRING ) )
            {
            otherlv_0=(Token)match(input,17,FOLLOW_7); 

            			newLeafNode(otherlv_0, grammarAccess.getTitleAccess().getTITLEKeyword_0());
            		
            // InternalBeathoven.g:360:3: ( (lv_name_1_0= RULE_STRING ) )
            // InternalBeathoven.g:361:4: (lv_name_1_0= RULE_STRING )
            {
            // InternalBeathoven.g:361:4: (lv_name_1_0= RULE_STRING )
            // InternalBeathoven.g:362:5: lv_name_1_0= RULE_STRING
            {
            lv_name_1_0=(Token)match(input,RULE_STRING,FOLLOW_2); 

            					newLeafNode(lv_name_1_0, grammarAccess.getTitleAccess().getNameSTRINGTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getTitleRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_1_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTitle"


    // $ANTLR start "entryRuleInstrument"
    // InternalBeathoven.g:382:1: entryRuleInstrument returns [EObject current=null] : iv_ruleInstrument= ruleInstrument EOF ;
    public final EObject entryRuleInstrument() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInstrument = null;


        try {
            // InternalBeathoven.g:382:51: (iv_ruleInstrument= ruleInstrument EOF )
            // InternalBeathoven.g:383:2: iv_ruleInstrument= ruleInstrument EOF
            {
             newCompositeNode(grammarAccess.getInstrumentRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleInstrument=ruleInstrument();

            state._fsp--;

             current =iv_ruleInstrument; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleInstrument"


    // $ANTLR start "ruleInstrument"
    // InternalBeathoven.g:389:1: ruleInstrument returns [EObject current=null] : (otherlv_0= 'INSTRUMENT' ( (lv_name_1_0= RULE_ID ) ) ) ;
    public final EObject ruleInstrument() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;


        	enterRule();

        try {
            // InternalBeathoven.g:395:2: ( (otherlv_0= 'INSTRUMENT' ( (lv_name_1_0= RULE_ID ) ) ) )
            // InternalBeathoven.g:396:2: (otherlv_0= 'INSTRUMENT' ( (lv_name_1_0= RULE_ID ) ) )
            {
            // InternalBeathoven.g:396:2: (otherlv_0= 'INSTRUMENT' ( (lv_name_1_0= RULE_ID ) ) )
            // InternalBeathoven.g:397:3: otherlv_0= 'INSTRUMENT' ( (lv_name_1_0= RULE_ID ) )
            {
            otherlv_0=(Token)match(input,18,FOLLOW_8); 

            			newLeafNode(otherlv_0, grammarAccess.getInstrumentAccess().getINSTRUMENTKeyword_0());
            		
            // InternalBeathoven.g:401:3: ( (lv_name_1_0= RULE_ID ) )
            // InternalBeathoven.g:402:4: (lv_name_1_0= RULE_ID )
            {
            // InternalBeathoven.g:402:4: (lv_name_1_0= RULE_ID )
            // InternalBeathoven.g:403:5: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_2); 

            					newLeafNode(lv_name_1_0, grammarAccess.getInstrumentAccess().getNameIDTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getInstrumentRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_1_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleInstrument"


    // $ANTLR start "entryRuleNoteStmt"
    // InternalBeathoven.g:423:1: entryRuleNoteStmt returns [EObject current=null] : iv_ruleNoteStmt= ruleNoteStmt EOF ;
    public final EObject entryRuleNoteStmt() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNoteStmt = null;


        try {
            // InternalBeathoven.g:423:49: (iv_ruleNoteStmt= ruleNoteStmt EOF )
            // InternalBeathoven.g:424:2: iv_ruleNoteStmt= ruleNoteStmt EOF
            {
             newCompositeNode(grammarAccess.getNoteStmtRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleNoteStmt=ruleNoteStmt();

            state._fsp--;

             current =iv_ruleNoteStmt; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleNoteStmt"


    // $ANTLR start "ruleNoteStmt"
    // InternalBeathoven.g:430:1: ruleNoteStmt returns [EObject current=null] : ( ( (lv_pitch_0_0= ruleNoteValue ) ) ( (lv_duration_1_0= ruleDuration ) ) ) ;
    public final EObject ruleNoteStmt() throws RecognitionException {
        EObject current = null;

        EObject lv_pitch_0_0 = null;

        EObject lv_duration_1_0 = null;



        	enterRule();

        try {
            // InternalBeathoven.g:436:2: ( ( ( (lv_pitch_0_0= ruleNoteValue ) ) ( (lv_duration_1_0= ruleDuration ) ) ) )
            // InternalBeathoven.g:437:2: ( ( (lv_pitch_0_0= ruleNoteValue ) ) ( (lv_duration_1_0= ruleDuration ) ) )
            {
            // InternalBeathoven.g:437:2: ( ( (lv_pitch_0_0= ruleNoteValue ) ) ( (lv_duration_1_0= ruleDuration ) ) )
            // InternalBeathoven.g:438:3: ( (lv_pitch_0_0= ruleNoteValue ) ) ( (lv_duration_1_0= ruleDuration ) )
            {
            // InternalBeathoven.g:438:3: ( (lv_pitch_0_0= ruleNoteValue ) )
            // InternalBeathoven.g:439:4: (lv_pitch_0_0= ruleNoteValue )
            {
            // InternalBeathoven.g:439:4: (lv_pitch_0_0= ruleNoteValue )
            // InternalBeathoven.g:440:5: lv_pitch_0_0= ruleNoteValue
            {

            					newCompositeNode(grammarAccess.getNoteStmtAccess().getPitchNoteValueParserRuleCall_0_0());
            				
            pushFollow(FOLLOW_9);
            lv_pitch_0_0=ruleNoteValue();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getNoteStmtRule());
            					}
            					set(
            						current,
            						"pitch",
            						lv_pitch_0_0,
            						"org.beathoven.Beathoven.NoteValue");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalBeathoven.g:457:3: ( (lv_duration_1_0= ruleDuration ) )
            // InternalBeathoven.g:458:4: (lv_duration_1_0= ruleDuration )
            {
            // InternalBeathoven.g:458:4: (lv_duration_1_0= ruleDuration )
            // InternalBeathoven.g:459:5: lv_duration_1_0= ruleDuration
            {

            					newCompositeNode(grammarAccess.getNoteStmtAccess().getDurationDurationParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_2);
            lv_duration_1_0=ruleDuration();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getNoteStmtRule());
            					}
            					set(
            						current,
            						"duration",
            						lv_duration_1_0,
            						"org.beathoven.Beathoven.Duration");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleNoteStmt"


    // $ANTLR start "entryRuleRest"
    // InternalBeathoven.g:480:1: entryRuleRest returns [EObject current=null] : iv_ruleRest= ruleRest EOF ;
    public final EObject entryRuleRest() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRest = null;


        try {
            // InternalBeathoven.g:480:45: (iv_ruleRest= ruleRest EOF )
            // InternalBeathoven.g:481:2: iv_ruleRest= ruleRest EOF
            {
             newCompositeNode(grammarAccess.getRestRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleRest=ruleRest();

            state._fsp--;

             current =iv_ruleRest; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleRest"


    // $ANTLR start "ruleRest"
    // InternalBeathoven.g:487:1: ruleRest returns [EObject current=null] : (otherlv_0= 'R' ( (lv_duration_1_0= ruleDuration ) ) ) ;
    public final EObject ruleRest() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_duration_1_0 = null;



        	enterRule();

        try {
            // InternalBeathoven.g:493:2: ( (otherlv_0= 'R' ( (lv_duration_1_0= ruleDuration ) ) ) )
            // InternalBeathoven.g:494:2: (otherlv_0= 'R' ( (lv_duration_1_0= ruleDuration ) ) )
            {
            // InternalBeathoven.g:494:2: (otherlv_0= 'R' ( (lv_duration_1_0= ruleDuration ) ) )
            // InternalBeathoven.g:495:3: otherlv_0= 'R' ( (lv_duration_1_0= ruleDuration ) )
            {
            otherlv_0=(Token)match(input,19,FOLLOW_9); 

            			newLeafNode(otherlv_0, grammarAccess.getRestAccess().getRKeyword_0());
            		
            // InternalBeathoven.g:499:3: ( (lv_duration_1_0= ruleDuration ) )
            // InternalBeathoven.g:500:4: (lv_duration_1_0= ruleDuration )
            {
            // InternalBeathoven.g:500:4: (lv_duration_1_0= ruleDuration )
            // InternalBeathoven.g:501:5: lv_duration_1_0= ruleDuration
            {

            					newCompositeNode(grammarAccess.getRestAccess().getDurationDurationParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_2);
            lv_duration_1_0=ruleDuration();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getRestRule());
            					}
            					set(
            						current,
            						"duration",
            						lv_duration_1_0,
            						"org.beathoven.Beathoven.Duration");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleRest"


    // $ANTLR start "entryRuleChord"
    // InternalBeathoven.g:522:1: entryRuleChord returns [EObject current=null] : iv_ruleChord= ruleChord EOF ;
    public final EObject entryRuleChord() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleChord = null;


        try {
            // InternalBeathoven.g:522:46: (iv_ruleChord= ruleChord EOF )
            // InternalBeathoven.g:523:2: iv_ruleChord= ruleChord EOF
            {
             newCompositeNode(grammarAccess.getChordRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleChord=ruleChord();

            state._fsp--;

             current =iv_ruleChord; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleChord"


    // $ANTLR start "ruleChord"
    // InternalBeathoven.g:529:1: ruleChord returns [EObject current=null] : ( ( (lv_chord_0_0= '[' ) ) ( (lv_notes_1_0= ruleNoteValue ) ) (otherlv_2= ',' ( (lv_notes_3_0= ruleNoteValue ) ) )* otherlv_4= ']' ( (lv_duration_5_0= ruleDuration ) ) ) ;
    public final EObject ruleChord() throws RecognitionException {
        EObject current = null;

        Token lv_chord_0_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_notes_1_0 = null;

        EObject lv_notes_3_0 = null;

        EObject lv_duration_5_0 = null;



        	enterRule();

        try {
            // InternalBeathoven.g:535:2: ( ( ( (lv_chord_0_0= '[' ) ) ( (lv_notes_1_0= ruleNoteValue ) ) (otherlv_2= ',' ( (lv_notes_3_0= ruleNoteValue ) ) )* otherlv_4= ']' ( (lv_duration_5_0= ruleDuration ) ) ) )
            // InternalBeathoven.g:536:2: ( ( (lv_chord_0_0= '[' ) ) ( (lv_notes_1_0= ruleNoteValue ) ) (otherlv_2= ',' ( (lv_notes_3_0= ruleNoteValue ) ) )* otherlv_4= ']' ( (lv_duration_5_0= ruleDuration ) ) )
            {
            // InternalBeathoven.g:536:2: ( ( (lv_chord_0_0= '[' ) ) ( (lv_notes_1_0= ruleNoteValue ) ) (otherlv_2= ',' ( (lv_notes_3_0= ruleNoteValue ) ) )* otherlv_4= ']' ( (lv_duration_5_0= ruleDuration ) ) )
            // InternalBeathoven.g:537:3: ( (lv_chord_0_0= '[' ) ) ( (lv_notes_1_0= ruleNoteValue ) ) (otherlv_2= ',' ( (lv_notes_3_0= ruleNoteValue ) ) )* otherlv_4= ']' ( (lv_duration_5_0= ruleDuration ) )
            {
            // InternalBeathoven.g:537:3: ( (lv_chord_0_0= '[' ) )
            // InternalBeathoven.g:538:4: (lv_chord_0_0= '[' )
            {
            // InternalBeathoven.g:538:4: (lv_chord_0_0= '[' )
            // InternalBeathoven.g:539:5: lv_chord_0_0= '['
            {
            lv_chord_0_0=(Token)match(input,20,FOLLOW_6); 

            					newLeafNode(lv_chord_0_0, grammarAccess.getChordAccess().getChordLeftSquareBracketKeyword_0_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getChordRule());
            					}
            					setWithLastConsumed(current, "chord", lv_chord_0_0, "[");
            				

            }


            }

            // InternalBeathoven.g:551:3: ( (lv_notes_1_0= ruleNoteValue ) )
            // InternalBeathoven.g:552:4: (lv_notes_1_0= ruleNoteValue )
            {
            // InternalBeathoven.g:552:4: (lv_notes_1_0= ruleNoteValue )
            // InternalBeathoven.g:553:5: lv_notes_1_0= ruleNoteValue
            {

            					newCompositeNode(grammarAccess.getChordAccess().getNotesNoteValueParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_10);
            lv_notes_1_0=ruleNoteValue();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getChordRule());
            					}
            					add(
            						current,
            						"notes",
            						lv_notes_1_0,
            						"org.beathoven.Beathoven.NoteValue");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalBeathoven.g:570:3: (otherlv_2= ',' ( (lv_notes_3_0= ruleNoteValue ) ) )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==21) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // InternalBeathoven.g:571:4: otherlv_2= ',' ( (lv_notes_3_0= ruleNoteValue ) )
            	    {
            	    otherlv_2=(Token)match(input,21,FOLLOW_6); 

            	    				newLeafNode(otherlv_2, grammarAccess.getChordAccess().getCommaKeyword_2_0());
            	    			
            	    // InternalBeathoven.g:575:4: ( (lv_notes_3_0= ruleNoteValue ) )
            	    // InternalBeathoven.g:576:5: (lv_notes_3_0= ruleNoteValue )
            	    {
            	    // InternalBeathoven.g:576:5: (lv_notes_3_0= ruleNoteValue )
            	    // InternalBeathoven.g:577:6: lv_notes_3_0= ruleNoteValue
            	    {

            	    						newCompositeNode(grammarAccess.getChordAccess().getNotesNoteValueParserRuleCall_2_1_0());
            	    					
            	    pushFollow(FOLLOW_10);
            	    lv_notes_3_0=ruleNoteValue();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getChordRule());
            	    						}
            	    						add(
            	    							current,
            	    							"notes",
            	    							lv_notes_3_0,
            	    							"org.beathoven.Beathoven.NoteValue");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);

            otherlv_4=(Token)match(input,22,FOLLOW_9); 

            			newLeafNode(otherlv_4, grammarAccess.getChordAccess().getRightSquareBracketKeyword_3());
            		
            // InternalBeathoven.g:599:3: ( (lv_duration_5_0= ruleDuration ) )
            // InternalBeathoven.g:600:4: (lv_duration_5_0= ruleDuration )
            {
            // InternalBeathoven.g:600:4: (lv_duration_5_0= ruleDuration )
            // InternalBeathoven.g:601:5: lv_duration_5_0= ruleDuration
            {

            					newCompositeNode(grammarAccess.getChordAccess().getDurationDurationParserRuleCall_4_0());
            				
            pushFollow(FOLLOW_2);
            lv_duration_5_0=ruleDuration();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getChordRule());
            					}
            					set(
            						current,
            						"duration",
            						lv_duration_5_0,
            						"org.beathoven.Beathoven.Duration");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleChord"


    // $ANTLR start "entryRuleRepeat"
    // InternalBeathoven.g:622:1: entryRuleRepeat returns [EObject current=null] : iv_ruleRepeat= ruleRepeat EOF ;
    public final EObject entryRuleRepeat() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRepeat = null;


        try {
            // InternalBeathoven.g:622:47: (iv_ruleRepeat= ruleRepeat EOF )
            // InternalBeathoven.g:623:2: iv_ruleRepeat= ruleRepeat EOF
            {
             newCompositeNode(grammarAccess.getRepeatRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleRepeat=ruleRepeat();

            state._fsp--;

             current =iv_ruleRepeat; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleRepeat"


    // $ANTLR start "ruleRepeat"
    // InternalBeathoven.g:629:1: ruleRepeat returns [EObject current=null] : (otherlv_0= 'REPEAT' ( (lv_times_1_0= RULE_INT ) ) otherlv_2= '{' ( (lv_blocks_3_0= ruleBlock ) )* otherlv_4= '}' ) ;
    public final EObject ruleRepeat() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_times_1_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_blocks_3_0 = null;



        	enterRule();

        try {
            // InternalBeathoven.g:635:2: ( (otherlv_0= 'REPEAT' ( (lv_times_1_0= RULE_INT ) ) otherlv_2= '{' ( (lv_blocks_3_0= ruleBlock ) )* otherlv_4= '}' ) )
            // InternalBeathoven.g:636:2: (otherlv_0= 'REPEAT' ( (lv_times_1_0= RULE_INT ) ) otherlv_2= '{' ( (lv_blocks_3_0= ruleBlock ) )* otherlv_4= '}' )
            {
            // InternalBeathoven.g:636:2: (otherlv_0= 'REPEAT' ( (lv_times_1_0= RULE_INT ) ) otherlv_2= '{' ( (lv_blocks_3_0= ruleBlock ) )* otherlv_4= '}' )
            // InternalBeathoven.g:637:3: otherlv_0= 'REPEAT' ( (lv_times_1_0= RULE_INT ) ) otherlv_2= '{' ( (lv_blocks_3_0= ruleBlock ) )* otherlv_4= '}'
            {
            otherlv_0=(Token)match(input,23,FOLLOW_4); 

            			newLeafNode(otherlv_0, grammarAccess.getRepeatAccess().getREPEATKeyword_0());
            		
            // InternalBeathoven.g:641:3: ( (lv_times_1_0= RULE_INT ) )
            // InternalBeathoven.g:642:4: (lv_times_1_0= RULE_INT )
            {
            // InternalBeathoven.g:642:4: (lv_times_1_0= RULE_INT )
            // InternalBeathoven.g:643:5: lv_times_1_0= RULE_INT
            {
            lv_times_1_0=(Token)match(input,RULE_INT,FOLLOW_11); 

            					newLeafNode(lv_times_1_0, grammarAccess.getRepeatAccess().getTimesINTTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getRepeatRule());
            					}
            					setWithLastConsumed(
            						current,
            						"times",
            						lv_times_1_0,
            						"org.eclipse.xtext.common.Terminals.INT");
            				

            }


            }

            otherlv_2=(Token)match(input,24,FOLLOW_12); 

            			newLeafNode(otherlv_2, grammarAccess.getRepeatAccess().getLeftCurlyBracketKeyword_2());
            		
            // InternalBeathoven.g:663:3: ( (lv_blocks_3_0= ruleBlock ) )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==RULE_NOTE_TOKEN||(LA4_0>=14 && LA4_0<=20)||LA4_0==23||(LA4_0>=26 && LA4_0<=27)) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // InternalBeathoven.g:664:4: (lv_blocks_3_0= ruleBlock )
            	    {
            	    // InternalBeathoven.g:664:4: (lv_blocks_3_0= ruleBlock )
            	    // InternalBeathoven.g:665:5: lv_blocks_3_0= ruleBlock
            	    {

            	    					newCompositeNode(grammarAccess.getRepeatAccess().getBlocksBlockParserRuleCall_3_0());
            	    				
            	    pushFollow(FOLLOW_12);
            	    lv_blocks_3_0=ruleBlock();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getRepeatRule());
            	    					}
            	    					add(
            	    						current,
            	    						"blocks",
            	    						lv_blocks_3_0,
            	    						"org.beathoven.Beathoven.Block");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);

            otherlv_4=(Token)match(input,25,FOLLOW_2); 

            			newLeafNode(otherlv_4, grammarAccess.getRepeatAccess().getRightCurlyBracketKeyword_4());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleRepeat"


    // $ANTLR start "entryRuleMark"
    // InternalBeathoven.g:690:1: entryRuleMark returns [EObject current=null] : iv_ruleMark= ruleMark EOF ;
    public final EObject entryRuleMark() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMark = null;


        try {
            // InternalBeathoven.g:690:45: (iv_ruleMark= ruleMark EOF )
            // InternalBeathoven.g:691:2: iv_ruleMark= ruleMark EOF
            {
             newCompositeNode(grammarAccess.getMarkRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleMark=ruleMark();

            state._fsp--;

             current =iv_ruleMark; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleMark"


    // $ANTLR start "ruleMark"
    // InternalBeathoven.g:697:1: ruleMark returns [EObject current=null] : (otherlv_0= 'MARK' ( (lv_label_1_0= RULE_ID ) ) ) ;
    public final EObject ruleMark() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_label_1_0=null;


        	enterRule();

        try {
            // InternalBeathoven.g:703:2: ( (otherlv_0= 'MARK' ( (lv_label_1_0= RULE_ID ) ) ) )
            // InternalBeathoven.g:704:2: (otherlv_0= 'MARK' ( (lv_label_1_0= RULE_ID ) ) )
            {
            // InternalBeathoven.g:704:2: (otherlv_0= 'MARK' ( (lv_label_1_0= RULE_ID ) ) )
            // InternalBeathoven.g:705:3: otherlv_0= 'MARK' ( (lv_label_1_0= RULE_ID ) )
            {
            otherlv_0=(Token)match(input,26,FOLLOW_8); 

            			newLeafNode(otherlv_0, grammarAccess.getMarkAccess().getMARKKeyword_0());
            		
            // InternalBeathoven.g:709:3: ( (lv_label_1_0= RULE_ID ) )
            // InternalBeathoven.g:710:4: (lv_label_1_0= RULE_ID )
            {
            // InternalBeathoven.g:710:4: (lv_label_1_0= RULE_ID )
            // InternalBeathoven.g:711:5: lv_label_1_0= RULE_ID
            {
            lv_label_1_0=(Token)match(input,RULE_ID,FOLLOW_2); 

            					newLeafNode(lv_label_1_0, grammarAccess.getMarkAccess().getLabelIDTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getMarkRule());
            					}
            					setWithLastConsumed(
            						current,
            						"label",
            						lv_label_1_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleMark"


    // $ANTLR start "entryRuleGoto"
    // InternalBeathoven.g:731:1: entryRuleGoto returns [EObject current=null] : iv_ruleGoto= ruleGoto EOF ;
    public final EObject entryRuleGoto() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGoto = null;


        try {
            // InternalBeathoven.g:731:45: (iv_ruleGoto= ruleGoto EOF )
            // InternalBeathoven.g:732:2: iv_ruleGoto= ruleGoto EOF
            {
             newCompositeNode(grammarAccess.getGotoRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleGoto=ruleGoto();

            state._fsp--;

             current =iv_ruleGoto; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleGoto"


    // $ANTLR start "ruleGoto"
    // InternalBeathoven.g:738:1: ruleGoto returns [EObject current=null] : (otherlv_0= 'GOTO' ( (lv_label_1_0= RULE_ID ) ) ) ;
    public final EObject ruleGoto() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_label_1_0=null;


        	enterRule();

        try {
            // InternalBeathoven.g:744:2: ( (otherlv_0= 'GOTO' ( (lv_label_1_0= RULE_ID ) ) ) )
            // InternalBeathoven.g:745:2: (otherlv_0= 'GOTO' ( (lv_label_1_0= RULE_ID ) ) )
            {
            // InternalBeathoven.g:745:2: (otherlv_0= 'GOTO' ( (lv_label_1_0= RULE_ID ) ) )
            // InternalBeathoven.g:746:3: otherlv_0= 'GOTO' ( (lv_label_1_0= RULE_ID ) )
            {
            otherlv_0=(Token)match(input,27,FOLLOW_8); 

            			newLeafNode(otherlv_0, grammarAccess.getGotoAccess().getGOTOKeyword_0());
            		
            // InternalBeathoven.g:750:3: ( (lv_label_1_0= RULE_ID ) )
            // InternalBeathoven.g:751:4: (lv_label_1_0= RULE_ID )
            {
            // InternalBeathoven.g:751:4: (lv_label_1_0= RULE_ID )
            // InternalBeathoven.g:752:5: lv_label_1_0= RULE_ID
            {
            lv_label_1_0=(Token)match(input,RULE_ID,FOLLOW_2); 

            					newLeafNode(lv_label_1_0, grammarAccess.getGotoAccess().getLabelIDTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getGotoRule());
            					}
            					setWithLastConsumed(
            						current,
            						"label",
            						lv_label_1_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleGoto"


    // $ANTLR start "entryRuleNoteValue"
    // InternalBeathoven.g:772:1: entryRuleNoteValue returns [EObject current=null] : iv_ruleNoteValue= ruleNoteValue EOF ;
    public final EObject entryRuleNoteValue() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNoteValue = null;


        try {
            // InternalBeathoven.g:772:50: (iv_ruleNoteValue= ruleNoteValue EOF )
            // InternalBeathoven.g:773:2: iv_ruleNoteValue= ruleNoteValue EOF
            {
             newCompositeNode(grammarAccess.getNoteValueRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleNoteValue=ruleNoteValue();

            state._fsp--;

             current =iv_ruleNoteValue; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleNoteValue"


    // $ANTLR start "ruleNoteValue"
    // InternalBeathoven.g:779:1: ruleNoteValue returns [EObject current=null] : ( (lv_value_0_0= RULE_NOTE_TOKEN ) ) ;
    public final EObject ruleNoteValue() throws RecognitionException {
        EObject current = null;

        Token lv_value_0_0=null;


        	enterRule();

        try {
            // InternalBeathoven.g:785:2: ( ( (lv_value_0_0= RULE_NOTE_TOKEN ) ) )
            // InternalBeathoven.g:786:2: ( (lv_value_0_0= RULE_NOTE_TOKEN ) )
            {
            // InternalBeathoven.g:786:2: ( (lv_value_0_0= RULE_NOTE_TOKEN ) )
            // InternalBeathoven.g:787:3: (lv_value_0_0= RULE_NOTE_TOKEN )
            {
            // InternalBeathoven.g:787:3: (lv_value_0_0= RULE_NOTE_TOKEN )
            // InternalBeathoven.g:788:4: lv_value_0_0= RULE_NOTE_TOKEN
            {
            lv_value_0_0=(Token)match(input,RULE_NOTE_TOKEN,FOLLOW_2); 

            				newLeafNode(lv_value_0_0, grammarAccess.getNoteValueAccess().getValueNOTE_TOKENTerminalRuleCall_0());
            			

            				if (current==null) {
            					current = createModelElement(grammarAccess.getNoteValueRule());
            				}
            				setWithLastConsumed(
            					current,
            					"value",
            					lv_value_0_0,
            					"org.beathoven.Beathoven.NOTE_TOKEN");
            			

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleNoteValue"


    // $ANTLR start "entryRuleDuration"
    // InternalBeathoven.g:807:1: entryRuleDuration returns [EObject current=null] : iv_ruleDuration= ruleDuration EOF ;
    public final EObject entryRuleDuration() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDuration = null;


        try {
            // InternalBeathoven.g:807:49: (iv_ruleDuration= ruleDuration EOF )
            // InternalBeathoven.g:808:2: iv_ruleDuration= ruleDuration EOF
            {
             newCompositeNode(grammarAccess.getDurationRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleDuration=ruleDuration();

            state._fsp--;

             current =iv_ruleDuration; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleDuration"


    // $ANTLR start "ruleDuration"
    // InternalBeathoven.g:814:1: ruleDuration returns [EObject current=null] : ( (lv_value_0_0= RULE_DUR_TOKEN ) ) ;
    public final EObject ruleDuration() throws RecognitionException {
        EObject current = null;

        Token lv_value_0_0=null;


        	enterRule();

        try {
            // InternalBeathoven.g:820:2: ( ( (lv_value_0_0= RULE_DUR_TOKEN ) ) )
            // InternalBeathoven.g:821:2: ( (lv_value_0_0= RULE_DUR_TOKEN ) )
            {
            // InternalBeathoven.g:821:2: ( (lv_value_0_0= RULE_DUR_TOKEN ) )
            // InternalBeathoven.g:822:3: (lv_value_0_0= RULE_DUR_TOKEN )
            {
            // InternalBeathoven.g:822:3: (lv_value_0_0= RULE_DUR_TOKEN )
            // InternalBeathoven.g:823:4: lv_value_0_0= RULE_DUR_TOKEN
            {
            lv_value_0_0=(Token)match(input,RULE_DUR_TOKEN,FOLLOW_2); 

            				newLeafNode(lv_value_0_0, grammarAccess.getDurationAccess().getValueDUR_TOKENTerminalRuleCall_0());
            			

            				if (current==null) {
            					current = createModelElement(grammarAccess.getDurationRule());
            				}
            				setWithLastConsumed(
            					current,
            					"value",
            					lv_value_0_0,
            					"org.beathoven.Beathoven.DUR_TOKEN");
            			

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleDuration"

    // Delegated rules


 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x000000000C9FC102L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000600000L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x000000000E9FC100L});

}