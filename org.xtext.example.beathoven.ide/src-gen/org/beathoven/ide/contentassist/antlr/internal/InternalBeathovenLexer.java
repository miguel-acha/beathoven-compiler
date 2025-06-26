package org.beathoven.ide.contentassist.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.Lexer;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalBeathovenLexer extends Lexer {
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

    public InternalBeathovenLexer() {;} 
    public InternalBeathovenLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public InternalBeathovenLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "InternalBeathoven.g"; }

    // $ANTLR start "T__14"
    public final void mT__14() throws RecognitionException {
        try {
            int _type = T__14;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalBeathoven.g:11:7: ( 'TEMPO' )
            // InternalBeathoven.g:11:9: 'TEMPO'
            {
            match("TEMPO"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__14"

    // $ANTLR start "T__15"
    public final void mT__15() throws RecognitionException {
        try {
            int _type = T__15;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalBeathoven.g:12:7: ( 'TIME' )
            // InternalBeathoven.g:12:9: 'TIME'
            {
            match("TIME"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__15"

    // $ANTLR start "T__16"
    public final void mT__16() throws RecognitionException {
        try {
            int _type = T__16;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalBeathoven.g:13:7: ( 'KEY' )
            // InternalBeathoven.g:13:9: 'KEY'
            {
            match("KEY"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__16"

    // $ANTLR start "T__17"
    public final void mT__17() throws RecognitionException {
        try {
            int _type = T__17;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalBeathoven.g:14:7: ( 'TITLE' )
            // InternalBeathoven.g:14:9: 'TITLE'
            {
            match("TITLE"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__17"

    // $ANTLR start "T__18"
    public final void mT__18() throws RecognitionException {
        try {
            int _type = T__18;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalBeathoven.g:15:7: ( 'INSTRUMENT' )
            // InternalBeathoven.g:15:9: 'INSTRUMENT'
            {
            match("INSTRUMENT"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__18"

    // $ANTLR start "T__19"
    public final void mT__19() throws RecognitionException {
        try {
            int _type = T__19;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalBeathoven.g:16:7: ( 'R' )
            // InternalBeathoven.g:16:9: 'R'
            {
            match('R'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__19"

    // $ANTLR start "T__20"
    public final void mT__20() throws RecognitionException {
        try {
            int _type = T__20;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalBeathoven.g:17:7: ( ']' )
            // InternalBeathoven.g:17:9: ']'
            {
            match(']'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__20"

    // $ANTLR start "T__21"
    public final void mT__21() throws RecognitionException {
        try {
            int _type = T__21;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalBeathoven.g:18:7: ( ',' )
            // InternalBeathoven.g:18:9: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__21"

    // $ANTLR start "T__22"
    public final void mT__22() throws RecognitionException {
        try {
            int _type = T__22;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalBeathoven.g:19:7: ( 'REPEAT' )
            // InternalBeathoven.g:19:9: 'REPEAT'
            {
            match("REPEAT"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__22"

    // $ANTLR start "T__23"
    public final void mT__23() throws RecognitionException {
        try {
            int _type = T__23;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalBeathoven.g:20:7: ( '{' )
            // InternalBeathoven.g:20:9: '{'
            {
            match('{'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__23"

    // $ANTLR start "T__24"
    public final void mT__24() throws RecognitionException {
        try {
            int _type = T__24;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalBeathoven.g:21:7: ( '}' )
            // InternalBeathoven.g:21:9: '}'
            {
            match('}'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__24"

    // $ANTLR start "T__25"
    public final void mT__25() throws RecognitionException {
        try {
            int _type = T__25;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalBeathoven.g:22:7: ( 'MARK' )
            // InternalBeathoven.g:22:9: 'MARK'
            {
            match("MARK"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__25"

    // $ANTLR start "T__26"
    public final void mT__26() throws RecognitionException {
        try {
            int _type = T__26;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalBeathoven.g:23:7: ( 'GOTO' )
            // InternalBeathoven.g:23:9: 'GOTO'
            {
            match("GOTO"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__26"

    // $ANTLR start "T__27"
    public final void mT__27() throws RecognitionException {
        try {
            int _type = T__27;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalBeathoven.g:24:7: ( '[' )
            // InternalBeathoven.g:24:9: '['
            {
            match('['); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__27"

    // $ANTLR start "RULE_NOTE_TOKEN"
    public final void mRULE_NOTE_TOKEN() throws RecognitionException {
        try {
            int _type = RULE_NOTE_TOKEN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalBeathoven.g:1601:17: ( 'A' .. 'G' ( '#' | 'b' )? ( '0' .. '8' )? )
            // InternalBeathoven.g:1601:19: 'A' .. 'G' ( '#' | 'b' )? ( '0' .. '8' )?
            {
            matchRange('A','G'); 
            // InternalBeathoven.g:1601:28: ( '#' | 'b' )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0=='#'||LA1_0=='b') ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // InternalBeathoven.g:
                    {
                    if ( input.LA(1)=='#'||input.LA(1)=='b' ) {
                        input.consume();

                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;}


                    }
                    break;

            }

            // InternalBeathoven.g:1601:39: ( '0' .. '8' )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( ((LA2_0>='0' && LA2_0<='8')) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // InternalBeathoven.g:1601:40: '0' .. '8'
                    {
                    matchRange('0','8'); 

                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_NOTE_TOKEN"

    // $ANTLR start "RULE_DUR_TOKEN"
    public final void mRULE_DUR_TOKEN() throws RecognitionException {
        try {
            int _type = RULE_DUR_TOKEN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalBeathoven.g:1603:16: ( ( 'W' | 'H' | 'Q' | 'E' | 'S' ) )
            // InternalBeathoven.g:1603:18: ( 'W' | 'H' | 'Q' | 'E' | 'S' )
            {
            if ( input.LA(1)=='E'||input.LA(1)=='H'||input.LA(1)=='Q'||input.LA(1)=='S'||input.LA(1)=='W' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_DUR_TOKEN"

    // $ANTLR start "RULE_FRACTION"
    public final void mRULE_FRACTION() throws RecognitionException {
        try {
            int _type = RULE_FRACTION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalBeathoven.g:1605:15: ( RULE_INT '/' RULE_INT )
            // InternalBeathoven.g:1605:17: RULE_INT '/' RULE_INT
            {
            mRULE_INT(); 
            match('/'); 
            mRULE_INT(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_FRACTION"

    // $ANTLR start "RULE_ID"
    public final void mRULE_ID() throws RecognitionException {
        try {
            int _type = RULE_ID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalBeathoven.g:1607:9: ( ( '^' )? ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )* )
            // InternalBeathoven.g:1607:11: ( '^' )? ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            {
            // InternalBeathoven.g:1607:11: ( '^' )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0=='^') ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // InternalBeathoven.g:1607:11: '^'
                    {
                    match('^'); 

                    }
                    break;

            }

            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // InternalBeathoven.g:1607:40: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0>='0' && LA4_0<='9')||(LA4_0>='A' && LA4_0<='Z')||LA4_0=='_'||(LA4_0>='a' && LA4_0<='z')) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // InternalBeathoven.g:
            	    {
            	    if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_ID"

    // $ANTLR start "RULE_INT"
    public final void mRULE_INT() throws RecognitionException {
        try {
            int _type = RULE_INT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalBeathoven.g:1609:10: ( ( '0' .. '9' )+ )
            // InternalBeathoven.g:1609:12: ( '0' .. '9' )+
            {
            // InternalBeathoven.g:1609:12: ( '0' .. '9' )+
            int cnt5=0;
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( ((LA5_0>='0' && LA5_0<='9')) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // InternalBeathoven.g:1609:13: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt5 >= 1 ) break loop5;
                        EarlyExitException eee =
                            new EarlyExitException(5, input);
                        throw eee;
                }
                cnt5++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_INT"

    // $ANTLR start "RULE_STRING"
    public final void mRULE_STRING() throws RecognitionException {
        try {
            int _type = RULE_STRING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalBeathoven.g:1611:13: ( ( '\"' ( '\\\\' . | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' . | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' ) )
            // InternalBeathoven.g:1611:15: ( '\"' ( '\\\\' . | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' . | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' )
            {
            // InternalBeathoven.g:1611:15: ( '\"' ( '\\\\' . | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' . | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' )
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0=='\"') ) {
                alt8=1;
            }
            else if ( (LA8_0=='\'') ) {
                alt8=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }
            switch (alt8) {
                case 1 :
                    // InternalBeathoven.g:1611:16: '\"' ( '\\\\' . | ~ ( ( '\\\\' | '\"' ) ) )* '\"'
                    {
                    match('\"'); 
                    // InternalBeathoven.g:1611:20: ( '\\\\' . | ~ ( ( '\\\\' | '\"' ) ) )*
                    loop6:
                    do {
                        int alt6=3;
                        int LA6_0 = input.LA(1);

                        if ( (LA6_0=='\\') ) {
                            alt6=1;
                        }
                        else if ( ((LA6_0>='\u0000' && LA6_0<='!')||(LA6_0>='#' && LA6_0<='[')||(LA6_0>=']' && LA6_0<='\uFFFF')) ) {
                            alt6=2;
                        }


                        switch (alt6) {
                    	case 1 :
                    	    // InternalBeathoven.g:1611:21: '\\\\' .
                    	    {
                    	    match('\\'); 
                    	    matchAny(); 

                    	    }
                    	    break;
                    	case 2 :
                    	    // InternalBeathoven.g:1611:28: ~ ( ( '\\\\' | '\"' ) )
                    	    {
                    	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='!')||(input.LA(1)>='#' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFF') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;}


                    	    }
                    	    break;

                    	default :
                    	    break loop6;
                        }
                    } while (true);

                    match('\"'); 

                    }
                    break;
                case 2 :
                    // InternalBeathoven.g:1611:48: '\\'' ( '\\\\' . | ~ ( ( '\\\\' | '\\'' ) ) )* '\\''
                    {
                    match('\''); 
                    // InternalBeathoven.g:1611:53: ( '\\\\' . | ~ ( ( '\\\\' | '\\'' ) ) )*
                    loop7:
                    do {
                        int alt7=3;
                        int LA7_0 = input.LA(1);

                        if ( (LA7_0=='\\') ) {
                            alt7=1;
                        }
                        else if ( ((LA7_0>='\u0000' && LA7_0<='&')||(LA7_0>='(' && LA7_0<='[')||(LA7_0>=']' && LA7_0<='\uFFFF')) ) {
                            alt7=2;
                        }


                        switch (alt7) {
                    	case 1 :
                    	    // InternalBeathoven.g:1611:54: '\\\\' .
                    	    {
                    	    match('\\'); 
                    	    matchAny(); 

                    	    }
                    	    break;
                    	case 2 :
                    	    // InternalBeathoven.g:1611:61: ~ ( ( '\\\\' | '\\'' ) )
                    	    {
                    	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='&')||(input.LA(1)>='(' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFF') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;}


                    	    }
                    	    break;

                    	default :
                    	    break loop7;
                        }
                    } while (true);

                    match('\''); 

                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_STRING"

    // $ANTLR start "RULE_ML_COMMENT"
    public final void mRULE_ML_COMMENT() throws RecognitionException {
        try {
            int _type = RULE_ML_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalBeathoven.g:1613:17: ( '/*' ( options {greedy=false; } : . )* '*/' )
            // InternalBeathoven.g:1613:19: '/*' ( options {greedy=false; } : . )* '*/'
            {
            match("/*"); 

            // InternalBeathoven.g:1613:24: ( options {greedy=false; } : . )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0=='*') ) {
                    int LA9_1 = input.LA(2);

                    if ( (LA9_1=='/') ) {
                        alt9=2;
                    }
                    else if ( ((LA9_1>='\u0000' && LA9_1<='.')||(LA9_1>='0' && LA9_1<='\uFFFF')) ) {
                        alt9=1;
                    }


                }
                else if ( ((LA9_0>='\u0000' && LA9_0<=')')||(LA9_0>='+' && LA9_0<='\uFFFF')) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // InternalBeathoven.g:1613:52: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);

            match("*/"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_ML_COMMENT"

    // $ANTLR start "RULE_SL_COMMENT"
    public final void mRULE_SL_COMMENT() throws RecognitionException {
        try {
            int _type = RULE_SL_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalBeathoven.g:1615:17: ( '//' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )? )
            // InternalBeathoven.g:1615:19: '//' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )?
            {
            match("//"); 

            // InternalBeathoven.g:1615:24: (~ ( ( '\\n' | '\\r' ) ) )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( ((LA10_0>='\u0000' && LA10_0<='\t')||(LA10_0>='\u000B' && LA10_0<='\f')||(LA10_0>='\u000E' && LA10_0<='\uFFFF')) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // InternalBeathoven.g:1615:24: ~ ( ( '\\n' | '\\r' ) )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='\uFFFF') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);

            // InternalBeathoven.g:1615:40: ( ( '\\r' )? '\\n' )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0=='\n'||LA12_0=='\r') ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // InternalBeathoven.g:1615:41: ( '\\r' )? '\\n'
                    {
                    // InternalBeathoven.g:1615:41: ( '\\r' )?
                    int alt11=2;
                    int LA11_0 = input.LA(1);

                    if ( (LA11_0=='\r') ) {
                        alt11=1;
                    }
                    switch (alt11) {
                        case 1 :
                            // InternalBeathoven.g:1615:41: '\\r'
                            {
                            match('\r'); 

                            }
                            break;

                    }

                    match('\n'); 

                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_SL_COMMENT"

    // $ANTLR start "RULE_WS"
    public final void mRULE_WS() throws RecognitionException {
        try {
            int _type = RULE_WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalBeathoven.g:1617:9: ( ( ' ' | '\\t' | '\\r' | '\\n' )+ )
            // InternalBeathoven.g:1617:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
            {
            // InternalBeathoven.g:1617:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
            int cnt13=0;
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( ((LA13_0>='\t' && LA13_0<='\n')||LA13_0=='\r'||LA13_0==' ') ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // InternalBeathoven.g:
            	    {
            	    if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    if ( cnt13 >= 1 ) break loop13;
                        EarlyExitException eee =
                            new EarlyExitException(13, input);
                        throw eee;
                }
                cnt13++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_WS"

    // $ANTLR start "RULE_ANY_OTHER"
    public final void mRULE_ANY_OTHER() throws RecognitionException {
        try {
            int _type = RULE_ANY_OTHER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalBeathoven.g:1619:16: ( . )
            // InternalBeathoven.g:1619:18: .
            {
            matchAny(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_ANY_OTHER"

    public void mTokens() throws RecognitionException {
        // InternalBeathoven.g:1:8: ( T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | RULE_NOTE_TOKEN | RULE_DUR_TOKEN | RULE_FRACTION | RULE_ID | RULE_INT | RULE_STRING | RULE_ML_COMMENT | RULE_SL_COMMENT | RULE_WS | RULE_ANY_OTHER )
        int alt14=24;
        alt14 = dfa14.predict(input);
        switch (alt14) {
            case 1 :
                // InternalBeathoven.g:1:10: T__14
                {
                mT__14(); 

                }
                break;
            case 2 :
                // InternalBeathoven.g:1:16: T__15
                {
                mT__15(); 

                }
                break;
            case 3 :
                // InternalBeathoven.g:1:22: T__16
                {
                mT__16(); 

                }
                break;
            case 4 :
                // InternalBeathoven.g:1:28: T__17
                {
                mT__17(); 

                }
                break;
            case 5 :
                // InternalBeathoven.g:1:34: T__18
                {
                mT__18(); 

                }
                break;
            case 6 :
                // InternalBeathoven.g:1:40: T__19
                {
                mT__19(); 

                }
                break;
            case 7 :
                // InternalBeathoven.g:1:46: T__20
                {
                mT__20(); 

                }
                break;
            case 8 :
                // InternalBeathoven.g:1:52: T__21
                {
                mT__21(); 

                }
                break;
            case 9 :
                // InternalBeathoven.g:1:58: T__22
                {
                mT__22(); 

                }
                break;
            case 10 :
                // InternalBeathoven.g:1:64: T__23
                {
                mT__23(); 

                }
                break;
            case 11 :
                // InternalBeathoven.g:1:70: T__24
                {
                mT__24(); 

                }
                break;
            case 12 :
                // InternalBeathoven.g:1:76: T__25
                {
                mT__25(); 

                }
                break;
            case 13 :
                // InternalBeathoven.g:1:82: T__26
                {
                mT__26(); 

                }
                break;
            case 14 :
                // InternalBeathoven.g:1:88: T__27
                {
                mT__27(); 

                }
                break;
            case 15 :
                // InternalBeathoven.g:1:94: RULE_NOTE_TOKEN
                {
                mRULE_NOTE_TOKEN(); 

                }
                break;
            case 16 :
                // InternalBeathoven.g:1:110: RULE_DUR_TOKEN
                {
                mRULE_DUR_TOKEN(); 

                }
                break;
            case 17 :
                // InternalBeathoven.g:1:125: RULE_FRACTION
                {
                mRULE_FRACTION(); 

                }
                break;
            case 18 :
                // InternalBeathoven.g:1:139: RULE_ID
                {
                mRULE_ID(); 

                }
                break;
            case 19 :
                // InternalBeathoven.g:1:147: RULE_INT
                {
                mRULE_INT(); 

                }
                break;
            case 20 :
                // InternalBeathoven.g:1:156: RULE_STRING
                {
                mRULE_STRING(); 

                }
                break;
            case 21 :
                // InternalBeathoven.g:1:168: RULE_ML_COMMENT
                {
                mRULE_ML_COMMENT(); 

                }
                break;
            case 22 :
                // InternalBeathoven.g:1:184: RULE_SL_COMMENT
                {
                mRULE_SL_COMMENT(); 

                }
                break;
            case 23 :
                // InternalBeathoven.g:1:200: RULE_WS
                {
                mRULE_WS(); 

                }
                break;
            case 24 :
                // InternalBeathoven.g:1:208: RULE_ANY_OTHER
                {
                mRULE_ANY_OTHER(); 

                }
                break;

        }

    }


    protected DFA14 dfa14 = new DFA14(this);
    static final String DFA14_eotS =
        "\1\uffff\3\31\1\35\4\uffff\1\31\1\45\1\uffff\2\45\1\50\1\51\1\26\1\uffff\3\26\2\uffff\2\31\1\uffff\3\31\5\uffff\2\31\1\45\1\uffff\1\45\4\uffff\1\51\4\uffff\3\31\1\73\5\31\1\101\1\31\1\uffff\2\31\1\105\1\106\1\107\1\uffff\1\110\2\31\4\uffff\1\31\1\114\1\31\1\uffff\2\31\1\120\1\uffff";
    static final String DFA14_eofS =
        "\121\uffff";
    static final String DFA14_minS =
        "\1\0\2\105\1\116\1\60\4\uffff\1\101\1\60\1\uffff\3\60\1\57\1\101\1\uffff\2\0\1\52\2\uffff\2\115\1\uffff\1\131\1\123\1\120\5\uffff\1\122\1\124\1\60\1\uffff\1\60\4\uffff\1\57\4\uffff\1\120\1\105\1\114\1\60\1\124\1\105\1\113\2\117\1\60\1\105\1\uffff\1\122\1\101\3\60\1\uffff\1\60\1\125\1\124\4\uffff\1\115\1\60\1\105\1\uffff\1\116\1\124\1\60\1\uffff";
    static final String DFA14_maxS =
        "\1\uffff\1\111\1\105\1\116\1\172\4\uffff\1\101\1\172\1\uffff\3\172\1\71\1\172\1\uffff\2\uffff\1\57\2\uffff\1\115\1\124\1\uffff\1\131\1\123\1\120\5\uffff\1\122\1\124\1\172\1\uffff\1\172\4\uffff\1\71\4\uffff\1\120\1\105\1\114\1\172\1\124\1\105\1\113\2\117\1\172\1\105\1\uffff\1\122\1\101\3\172\1\uffff\1\172\1\125\1\124\4\uffff\1\115\1\172\1\105\1\uffff\1\116\1\124\1\172\1\uffff";
    static final String DFA14_acceptS =
        "\5\uffff\1\7\1\10\1\12\1\13\2\uffff\1\16\5\uffff\1\22\3\uffff\1\27\1\30\2\uffff\1\22\3\uffff\1\6\1\7\1\10\1\12\1\13\3\uffff\1\17\1\uffff\1\16\1\20\1\23\1\21\1\uffff\1\24\1\25\1\26\1\27\13\uffff\1\3\5\uffff\1\2\3\uffff\1\14\1\15\1\1\1\4\3\uffff\1\11\3\uffff\1\5";
    static final String DFA14_specialS =
        "\1\0\21\uffff\1\2\1\1\75\uffff}>";
    static final String[] DFA14_transitionS = {
            "\11\26\2\25\2\26\1\25\22\26\1\25\1\26\1\22\4\26\1\23\4\26\1\6\2\26\1\24\12\17\7\26\4\15\1\14\1\15\1\12\1\16\1\3\1\21\1\2\1\21\1\11\3\21\1\16\1\4\1\16\1\1\2\21\1\16\3\21\1\13\1\26\1\5\1\20\1\21\1\26\32\21\1\7\1\26\1\10\uff82\26",
            "\1\27\3\uffff\1\30",
            "\1\32",
            "\1\33",
            "\12\31\7\uffff\4\31\1\34\25\31\4\uffff\1\31\1\uffff\32\31",
            "",
            "",
            "",
            "",
            "\1\42",
            "\11\46\1\31\7\uffff\16\31\1\43\13\31\4\uffff\1\31\1\uffff\1\31\1\44\30\31",
            "",
            "\11\46\1\31\7\uffff\32\31\4\uffff\1\31\1\uffff\1\31\1\44\30\31",
            "\11\46\1\31\7\uffff\32\31\4\uffff\1\31\1\uffff\1\31\1\44\30\31",
            "\12\31\7\uffff\32\31\4\uffff\1\31\1\uffff\32\31",
            "\1\52\12\53",
            "\32\31\4\uffff\1\31\1\uffff\32\31",
            "",
            "\0\54",
            "\0\54",
            "\1\55\4\uffff\1\56",
            "",
            "",
            "\1\60",
            "\1\61\6\uffff\1\62",
            "",
            "\1\63",
            "\1\64",
            "\1\65",
            "",
            "",
            "",
            "",
            "",
            "\1\66",
            "\1\67",
            "\11\46\1\31\7\uffff\32\31\4\uffff\1\31\1\uffff\32\31",
            "",
            "\12\31\7\uffff\32\31\4\uffff\1\31\1\uffff\32\31",
            "",
            "",
            "",
            "",
            "\1\52\12\53",
            "",
            "",
            "",
            "",
            "\1\70",
            "\1\71",
            "\1\72",
            "\12\31\7\uffff\32\31\4\uffff\1\31\1\uffff\32\31",
            "\1\74",
            "\1\75",
            "\1\76",
            "\1\77",
            "\1\100",
            "\12\31\7\uffff\32\31\4\uffff\1\31\1\uffff\32\31",
            "\1\102",
            "",
            "\1\103",
            "\1\104",
            "\12\31\7\uffff\32\31\4\uffff\1\31\1\uffff\32\31",
            "\12\31\7\uffff\32\31\4\uffff\1\31\1\uffff\32\31",
            "\12\31\7\uffff\32\31\4\uffff\1\31\1\uffff\32\31",
            "",
            "\12\31\7\uffff\32\31\4\uffff\1\31\1\uffff\32\31",
            "\1\111",
            "\1\112",
            "",
            "",
            "",
            "",
            "\1\113",
            "\12\31\7\uffff\32\31\4\uffff\1\31\1\uffff\32\31",
            "\1\115",
            "",
            "\1\116",
            "\1\117",
            "\12\31\7\uffff\32\31\4\uffff\1\31\1\uffff\32\31",
            ""
    };

    static final short[] DFA14_eot = DFA.unpackEncodedString(DFA14_eotS);
    static final short[] DFA14_eof = DFA.unpackEncodedString(DFA14_eofS);
    static final char[] DFA14_min = DFA.unpackEncodedStringToUnsignedChars(DFA14_minS);
    static final char[] DFA14_max = DFA.unpackEncodedStringToUnsignedChars(DFA14_maxS);
    static final short[] DFA14_accept = DFA.unpackEncodedString(DFA14_acceptS);
    static final short[] DFA14_special = DFA.unpackEncodedString(DFA14_specialS);
    static final short[][] DFA14_transition;

    static {
        int numStates = DFA14_transitionS.length;
        DFA14_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA14_transition[i] = DFA.unpackEncodedString(DFA14_transitionS[i]);
        }
    }

    class DFA14 extends DFA {

        public DFA14(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 14;
            this.eot = DFA14_eot;
            this.eof = DFA14_eof;
            this.min = DFA14_min;
            this.max = DFA14_max;
            this.accept = DFA14_accept;
            this.special = DFA14_special;
            this.transition = DFA14_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | RULE_NOTE_TOKEN | RULE_DUR_TOKEN | RULE_FRACTION | RULE_ID | RULE_INT | RULE_STRING | RULE_ML_COMMENT | RULE_SL_COMMENT | RULE_WS | RULE_ANY_OTHER );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA14_0 = input.LA(1);

                        s = -1;
                        if ( (LA14_0=='T') ) {s = 1;}

                        else if ( (LA14_0=='K') ) {s = 2;}

                        else if ( (LA14_0=='I') ) {s = 3;}

                        else if ( (LA14_0=='R') ) {s = 4;}

                        else if ( (LA14_0==']') ) {s = 5;}

                        else if ( (LA14_0==',') ) {s = 6;}

                        else if ( (LA14_0=='{') ) {s = 7;}

                        else if ( (LA14_0=='}') ) {s = 8;}

                        else if ( (LA14_0=='M') ) {s = 9;}

                        else if ( (LA14_0=='G') ) {s = 10;}

                        else if ( (LA14_0=='[') ) {s = 11;}

                        else if ( (LA14_0=='E') ) {s = 12;}

                        else if ( ((LA14_0>='A' && LA14_0<='D')||LA14_0=='F') ) {s = 13;}

                        else if ( (LA14_0=='H'||LA14_0=='Q'||LA14_0=='S'||LA14_0=='W') ) {s = 14;}

                        else if ( ((LA14_0>='0' && LA14_0<='9')) ) {s = 15;}

                        else if ( (LA14_0=='^') ) {s = 16;}

                        else if ( (LA14_0=='J'||LA14_0=='L'||(LA14_0>='N' && LA14_0<='P')||(LA14_0>='U' && LA14_0<='V')||(LA14_0>='X' && LA14_0<='Z')||LA14_0=='_'||(LA14_0>='a' && LA14_0<='z')) ) {s = 17;}

                        else if ( (LA14_0=='\"') ) {s = 18;}

                        else if ( (LA14_0=='\'') ) {s = 19;}

                        else if ( (LA14_0=='/') ) {s = 20;}

                        else if ( ((LA14_0>='\t' && LA14_0<='\n')||LA14_0=='\r'||LA14_0==' ') ) {s = 21;}

                        else if ( ((LA14_0>='\u0000' && LA14_0<='\b')||(LA14_0>='\u000B' && LA14_0<='\f')||(LA14_0>='\u000E' && LA14_0<='\u001F')||LA14_0=='!'||(LA14_0>='#' && LA14_0<='&')||(LA14_0>='(' && LA14_0<='+')||(LA14_0>='-' && LA14_0<='.')||(LA14_0>=':' && LA14_0<='@')||LA14_0=='\\'||LA14_0=='`'||LA14_0=='|'||(LA14_0>='~' && LA14_0<='\uFFFF')) ) {s = 22;}

                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA14_19 = input.LA(1);

                        s = -1;
                        if ( ((LA14_19>='\u0000' && LA14_19<='\uFFFF')) ) {s = 44;}

                        else s = 22;

                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA14_18 = input.LA(1);

                        s = -1;
                        if ( ((LA14_18>='\u0000' && LA14_18<='\uFFFF')) ) {s = 44;}

                        else s = 22;

                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 14, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

}