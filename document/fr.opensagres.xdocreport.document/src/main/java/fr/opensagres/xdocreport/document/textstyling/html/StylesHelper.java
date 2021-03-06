/**
 * Copyright (C) 2011-2015 The XDocReport Team <xdocreport@googlegroups.com>
 *
 * All rights reserved.
 *
 * Permission is hereby granted, free  of charge, to any person obtaining
 * a  copy  of this  software  and  associated  documentation files  (the
 * "Software"), to  deal in  the Software without  restriction, including
 * without limitation  the rights to  use, copy, modify,  merge, publish,
 * distribute,  sublicense, and/or sell  copies of  the Software,  and to
 * permit persons to whom the Software  is furnished to do so, subject to
 * the following conditions:
 *
 * The  above  copyright  notice  and  this permission  notice  shall  be
 * included in all copies or substantial portions of the Software.
 *
 * THE  SOFTWARE IS  PROVIDED  "AS  IS", WITHOUT  WARRANTY  OF ANY  KIND,
 * EXPRESS OR  IMPLIED, INCLUDING  BUT NOT LIMITED  TO THE  WARRANTIES OF
 * MERCHANTABILITY,    FITNESS    FOR    A   PARTICULAR    PURPOSE    AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE,  ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package fr.opensagres.xdocreport.document.textstyling.html;

import fr.opensagres.xdocreport.core.utils.StringUtils;
import fr.opensagres.xdocreport.document.textstyling.properties.*;
import org.xml.sax.Attributes;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Styles Helper.
 */
public class StylesHelper
{

    public static Map<String, String> parse( String style )
    {
        if ( StringUtils.isEmpty( style ) )
        {
            return Collections.emptyMap();
        }
        Map<String, String> stylesMap = new HashMap<String, String>();
        String[] styles = style.split( ";" );
        String s = null;
        int index = 0;
        String name = null;
        String value = null;
        for ( int i = 0; i < styles.length; i++ )
        {
            name = null;
            value = null;
            s = styles[i];
            index = s.indexOf( ':' );
            if ( index != -1 )
            {
                name = s.substring( 0, index ).trim();
                value = s.substring( index + 1, s.length() ).trim();
                stylesMap.put( name, value );
            }
        }
        return stylesMap;
    }

    /**
     * Create {@link ParagraphProperties} from inline style.
     * 
     * @param style
     * @return
     */
    public static ParagraphProperties createParagraphProperties( String style )
    {
        Map<String, String> stylesMap = StylesHelper.parse( style );
        if ( !stylesMap.isEmpty() )
        {
            ParagraphProperties properties = new ParagraphProperties();
            processContainerProperties(properties, stylesMap);
            return properties;
        }
        return null;
    }

    /**
     * Create {@link HeaderProperties} from inline style.
     * 
     * @param style
     * @return
     */
    public static HeaderProperties createHeaderProperties( String style )
    {
        Map<String, String> stylesMap = StylesHelper.parse( style );
        if ( !stylesMap.isEmpty() )
        {
            HeaderProperties properties = new HeaderProperties();
            processContainerProperties( properties, stylesMap );
            return properties;
        }
        return null;
    }

    /**
     * Create {@link ListItemProperties} from inline style.
     * 
     * @param style
     * @return
     */
    public static ListItemProperties createListItemProperties( String style )
    {
        Map<String, String> stylesMap = StylesHelper.parse( style );
        if ( !stylesMap.isEmpty() )
        {
            ListItemProperties properties = new ListItemProperties();
            processContainerProperties( properties, stylesMap );
            return properties;
        }
        return null;
    }

    /**
     * Create {@link ListProperties} from inline style.
     * 
     * @param style
     * @return
     */
    public static ListProperties createListProperties( String style )
    {
        Map<String, String> stylesMap = StylesHelper.parse( style );
        if ( !stylesMap.isEmpty() )
        {
            ListProperties properties = new ListProperties();
            processContainerProperties( properties, stylesMap );
            return properties;
        }
        return null;
    }

    /**
     * Create {@link SpanProperties} from inline style.
     * 
     * @param style
     * @return
     */
    public static SpanProperties createSpanProperties( String style )
    {
        Map<String, String> stylesMap = StylesHelper.parse( style );
        if ( !stylesMap.isEmpty() )
        {
            SpanProperties properties = new SpanProperties();
            processContainerProperties( properties, stylesMap );
            return properties;
        }
        return null;
    }

    /**
     * Create {@link BoldProperties} from inline style.
     *
     * @param style
     * @return
     */
    public static BoldProperties createBoldProperties( String style )
    {
        Map<String, String> stylesMap = StylesHelper.parse( style );
        BoldProperties properties = new BoldProperties();
        if ( !stylesMap.isEmpty() )
        {
            processContainerProperties( properties, stylesMap );
        }
        properties.setBold( true );
        return properties;
    }

    /**
     * Create {@link ItalicsProperties} from inline style.
     *
     * @param style
     * @return
     */
    public static ItalicsProperties createItalicsProperties( String style )
    {
        Map<String, String> stylesMap = StylesHelper.parse( style );
        ItalicsProperties properties = new ItalicsProperties();
        if ( !stylesMap.isEmpty() )
        {
            processContainerProperties( properties, stylesMap );
        }
        properties.setItalic(  true );
        return properties;
    }

    /**
     * Create {@link StrikeProperties} from inline style.
     *
     * @param style
     * @return
     */
    public static StrikeProperties createStrikeProperties( String style )
    {
        Map<String, String> stylesMap = StylesHelper.parse( style );
        StrikeProperties properties = new StrikeProperties();
        if ( !stylesMap.isEmpty() )
        {
            processContainerProperties( properties, stylesMap );
        }
        properties.setStrike(  true );
        return properties;
    }

    /**
     * Create {@link SubscriptProperties} from inline style.
     *
     * @param style
     * @return
     */
    public static SubscriptProperties createSubscriptProperties( String style )
    {
        Map<String, String> stylesMap = StylesHelper.parse( style );
        SubscriptProperties properties = new SubscriptProperties();
        if ( !stylesMap.isEmpty() )
        {
            processContainerProperties( properties, stylesMap );
        }
        properties.setSubscript( true );
        return properties;
    }

    /**
     * Create {@link SuperscriptProperties} from inline style.
     *
     * @param style
     * @return
     */
    public static SuperscriptProperties createSuperscriptProperties( String style )
    {
        Map<String, String> stylesMap = StylesHelper.parse( style );
        SuperscriptProperties properties = new SuperscriptProperties();
        if ( !stylesMap.isEmpty() )
        {
            processContainerProperties( properties, stylesMap );
        }
        properties.setSuperscript( true );
        return properties;
    }

    /**
     * Create {@link UnderlineProperties} from inline style.
     *
     * @param style
     * @return
     */
    public static UnderlineProperties createUnderlineProperties( String style )
    {
        Map<String, String> stylesMap = StylesHelper.parse( style );
        UnderlineProperties properties = new UnderlineProperties();
        if ( !stylesMap.isEmpty() )
        {
            processContainerProperties( properties, stylesMap );
        }
        properties.setUnderline( true );
        return properties;
    }

    private static void processContainerProperties( ContainerProperties properties, Map<String, String> stylesMap )
    {
        // page-break-before
        properties.setPageBreakBefore( false );
        if ( "always".equals( stylesMap.get( "page-break-before" ) ) )
        {
            properties.setPageBreakBefore( true );
        }

        // page-break-after
        properties.setPageBreakAfter( false );
        if ( "always".equals( stylesMap.get( "page-break-after" ) ) )
        {
            properties.setPageBreakAfter( true );
        }

        // font-weight
        String fontWeight = stylesMap.get( "font-weight" );
        properties.setBold( false );
        if ( fontWeight != null )
        {
            if ( "bold".equals( fontWeight ) || "700".equals( fontWeight ) )
            {
                properties.setBold( true );
            }
        }

        // font-style
        String fontStyle = stylesMap.get( "font-style" );
        properties.setItalic( false );
        if ( fontStyle != null )
        {
            if ( "italic".equals( fontStyle ) )
            {
                properties.setItalic( true );
            }
        }

        // text-decoration
        String textDecoration = stylesMap.get( "text-decoration" );
        properties.setStrike( false );
        properties.setUnderline( false );
        if ( textDecoration != null )
        {
            if ( textDecoration.contains( "underline" ) )
            {
                properties.setUnderline( true );
            }

            if ( textDecoration.contains( "line-through" ) )
            {
                properties.setStrike( true );
            }
        }

        // vertical-align
        String verticalAlign = stylesMap.get( "vertical-align" );
        properties.setSuperscript( false );
        properties.setSubscript( false );
        if ( verticalAlign != null )
        {
            if ( "sub".equals( verticalAlign ) )
            {
                properties.setSubscript( true );
            }
            else if ( "super".equals( verticalAlign ) )
            {
                properties.setSuperscript( true );
            }
        }

        // text-align
        String textAlignment = stylesMap.get( "text-align" );
        if ( textAlignment != null )
        {
            if ( "left".equals( textAlignment ) )
            {
                properties.setTextAlignment( TextAlignment.Left );
            }
            else if ( "center".equals( textAlignment ) )
            {
                properties.setTextAlignment( TextAlignment.Center );
            }
            else if ( "right".equals( textAlignment ) )
            {
                properties.setTextAlignment( TextAlignment.Right );
            }
            else if ( "justify".equals( textAlignment ) )
            {
                properties.setTextAlignment( TextAlignment.Justify );
            }
            else if ( "inherit".equals( textAlignment ) )
            {
                properties.setTextAlignment( TextAlignment.Inherit );
            }

        }

        // color
        String color = stylesMap.get( "color" );
        if ( color != null )
        {
            properties.setColor( HTMLColorParser.parse( color ) );
        }

        // style
        String styleName = stylesMap.get( "name" );
        if ( styleName != null )
        {
            properties.setStyleName( styleName );
        }
    }

    public static TableProperties createTableProperties( Attributes attributes )
    {
        TableProperties properties = new TableProperties();
        return properties;
    }

    public static TableRowProperties createTableRowProperties( Attributes attributes )
    {
        TableRowProperties properties = new TableRowProperties();
        return properties;
    }

    public static TableCellProperties createTableCellProperties( Attributes attributes )
    {
        TableCellProperties properties = new TableCellProperties();
        return properties;
    }
}
