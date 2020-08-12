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

import fr.opensagres.xdocreport.document.textstyling.IDocumentHandler;
import fr.opensagres.xdocreport.document.textstyling.properties.ContainerProperties;
import fr.opensagres.xdocreport.document.textstyling.properties.ContainerType;
import fr.opensagres.xdocreport.document.textstyling.properties.TableProperties;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;

/**
 * SAX content handler used to parse HTML content and call the right method of {@link IDocumentHandler} according the
 * HTML content.
 */
public class HTMLTextStylingContentHandler extends DefaultHandler
{

    private static final String STYLE_ATTR = "style";

    // HTML elements for Bold style
    private static final String STRONG_ELT = "strong";

    private static final String B_ELT = "b";

    // HTML elements for Italic style
    private static final String EM_ELT = "em";

    private static final String I_ELT = "i";

    // HTML elements for Underline style
    private static final String U_ELT = "u";

    // HTML elements for Strike style
    private static final String STRIKE_ELT = "strike";

    private static final String S_ELT = "s";

    // HTML elements for list
    private static final String OL_ELT = "ol";

    private static final String UL_ELT = "ul";

    private static final String LI_ELT = "li";

    // HTML elements for paragraph
    private static final String P_ELT = "p";

    // HTML elements for Titles
    private static final String H1_ELT = "h1";

    private static final String H2_ELT = "h2";

    private static final String H3_ELT = "h3";

    private static final String H4_ELT = "h4";

    private static final String H5_ELT = "h5";

    private static final String H6_ELT = "h6";

    private static final String A_ELT = "a";

    private static final String IMG_ELT = "img";

    private static final String HREF_ATTR = "href";

    private static final String SRC_ATTR = "src";

    private static final String BR_ELT = "br";

    // HTML elements for subscript
    private static final String SUB_ELT = "sub";

    // HTML elements for superscript
    private static final String SUP_ELT = "sup";

    // HTML elements for span
    private static final String SPAN_ELT = "span";

    // HTML elements for table
    private static final String TABLE_ELT = "table";

    private static final String TR_ELT = "tr";

    private static final String TD_ELT = "td";

    private final IDocumentHandler documentHandler;

    // current a href + content parsing
    private String aHref;

    private StringBuilder aContent;

    private boolean ignoreCharacters;

    public HTMLTextStylingContentHandler( IDocumentHandler visitor )
    {
        this.documentHandler = visitor;
        this.aHref = null;
        this.aContent = new StringBuilder();
        this.ignoreCharacters = false;
    }

    @Override
    public void startDocument() throws SAXException
    {
        super.startDocument();
        try
        {
            documentHandler.startDocument();
        }
        catch ( IOException e )
        {
            throw new SAXException( e );
        }
    }

    @Override
    public void endDocument() throws SAXException
    {
        super.endDocument();
        try
        {
            documentHandler.endDocument();
        }
        catch ( IOException e )
        {
            throw new SAXException( e );
        }
    }

    @Override
    public void startElement( String uri, String localName, String name, Attributes attributes ) throws SAXException
    {
        ignoreCharacters = false;
        try
        {
            if ( STRONG_ELT.equals( name ) || B_ELT.equals( name ) )
            {
                // Bold
                ContainerProperties properties = StylesHelper.createProperties( attributes.getValue( STYLE_ATTR ), ContainerType.SPAN );
                properties.setBold( true );
                documentHandler.startSpan( properties );
            }
            else if ( EM_ELT.equals( name ) || I_ELT.equals( name ) )
            {
                // Italic
                ContainerProperties properties = StylesHelper.createProperties( attributes.getValue( STYLE_ATTR ), ContainerType.SPAN );
                properties.setItalic( true );
                documentHandler.startSpan(properties);
            }
            else if ( U_ELT.equals( name ) )
            {
                // Underline
                ContainerProperties properties = StylesHelper.createProperties( attributes.getValue( STYLE_ATTR ), ContainerType.SPAN );
                properties.setUnderline( true );
                documentHandler.startSpan(properties);
            }
            else if ( STRIKE_ELT.equals( name ) || S_ELT.equals( name ) )
            {
                // Strike
                ContainerProperties properties = StylesHelper.createProperties( attributes.getValue( STYLE_ATTR ), ContainerType.SPAN );
                properties.setStrike( true );
                documentHandler.startSpan(properties);
            }
            else if ( SUB_ELT.equals( name ) )
            {
                // Subscript
                ContainerProperties properties = StylesHelper.createProperties( attributes.getValue( STYLE_ATTR ), ContainerType.SPAN );
                properties.setSubscript( true );
                documentHandler.startSpan(properties);
            }
            else if ( SUP_ELT.equals( name ) )
            {
                // Superscript
                ContainerProperties properties = StylesHelper.createProperties( attributes.getValue( STYLE_ATTR ), ContainerType.SPAN );
                properties.setSuperscript( true );
                documentHandler.startSpan(properties);
            }
            else if ( UL_ELT.equals( name ) )
            {
                // Unordered List
                ContainerProperties properties = StylesHelper.createProperties( attributes.getValue( STYLE_ATTR ),
                        ContainerType.LIST );
                startList( false, properties );
            }
            else if ( OL_ELT.equals( name ) )
            {
                // Orderer List
                ContainerProperties properties = StylesHelper.createProperties( attributes.getValue( STYLE_ATTR ),
                        ContainerType.LIST );
                startList( true, properties );
            }
            else if ( LI_ELT.equals( name ) )
            {
                // List item
                ContainerProperties properties = StylesHelper.createProperties( attributes.getValue( STYLE_ATTR ),
                        ContainerType.LIST_ITEM );
                documentHandler.startListItem( properties );
            }
            else if ( P_ELT.equals( name ) )
            {
                // Paragraph
                ContainerProperties properties = StylesHelper.createProperties( attributes.getValue( STYLE_ATTR ),
                        ContainerType.PARAGRAPH );
                documentHandler.startParagraph( properties );
            }
            else if ( H1_ELT.equals( name ) )
            {
                // Header 1
                ContainerProperties properties = StylesHelper.createProperties( attributes.getValue( STYLE_ATTR ),
                        ContainerType.HEADER );
                documentHandler.startHeading( 1, properties );
            }
            else if ( H2_ELT.equals( name ) )
            {
                // Header 2
                ContainerProperties properties = StylesHelper.createProperties( attributes.getValue( STYLE_ATTR ),
                        ContainerType.HEADER );
                documentHandler.startHeading( 2, properties );
            }
            else if ( H3_ELT.equals( name ) )
            {
                // Header 3
                ContainerProperties properties = StylesHelper.createProperties( attributes.getValue( STYLE_ATTR ),
                        ContainerType.HEADER );
                documentHandler.startHeading( 3, properties );
            }
            else if ( H4_ELT.equals( name ) )
            {
                // Header 4
                ContainerProperties properties = StylesHelper.createProperties( attributes.getValue( STYLE_ATTR ),
                        ContainerType.HEADER );
                documentHandler.startHeading( 4, properties );
            }
            else if ( H5_ELT.equals( name ) )
            {
                // Header 5
                ContainerProperties properties = StylesHelper.createProperties( attributes.getValue( STYLE_ATTR ),
                        ContainerType.HEADER );
                documentHandler.startHeading( 5, properties );
            }
            else if ( H6_ELT.equals( name ) )
            {
                // Header 6
                ContainerProperties properties = StylesHelper.createProperties( attributes.getValue( STYLE_ATTR ),
                        ContainerType.HEADER );
                documentHandler.startHeading( 6, properties );
            }
            else if ( A_ELT.equals( name ) )
            {
                // start reference
                this.aHref = attributes.getValue( HREF_ATTR );
            }
            else if ( IMG_ELT.equals( name ) )
            {
                // image
                String src = attributes.getValue( SRC_ATTR );
                documentHandler.handleImage( src, "" );
            }
            else if ( SPAN_ELT.equals( name ) )
            {
                // <span>
                ContainerProperties properties = StylesHelper.createProperties( attributes.getValue( STYLE_ATTR ),
                        ContainerType.SPAN );
                documentHandler.startSpan( properties );
            }
            else if ( TABLE_ELT.equals( name ) )
            {
                // <table>
                TableProperties properties = StylesHelper.createTableProperties( attributes );
                documentHandler.startTable( properties );
            }
            else if ( TR_ELT.equals( name ) )
            {
                // <tr>
                ContainerProperties properties = StylesHelper.createProperties( attributes.getValue( STYLE_ATTR ),
                        ContainerType.TABLE_ROW );
                documentHandler.startTableRow( properties );
            }
            else if ( TD_ELT.equals( name ) )
            {
                // <td>
                ContainerProperties properties = StylesHelper.createProperties( attributes.getValue( STYLE_ATTR ),
                        ContainerType.TABLE_CELL );
                documentHandler.startTableCell( properties );
            }
        }
        catch ( IOException e )
        {
            throw new SAXException( e );
        }
        super.startElement( uri, localName, name, attributes );
    }

    @Override
    public void endElement( String uri, String localName, String name ) throws SAXException
    {
        ignoreCharacters = false;
        try
        {
            if ( STRONG_ELT.equals( name ) || B_ELT.equals( name ) )
            {
                // Bold
                documentHandler.endSpan();
            }
            else if ( EM_ELT.equals( name ) || I_ELT.equals( name ) )
            {
                // Italic
                documentHandler.endSpan();
            }
            else if ( U_ELT.equals( name ) )
            {
                // Underline
                documentHandler.endSpan();
            }
            else if ( STRIKE_ELT.equals( name ) || S_ELT.equals( name ) )
            {
                // Strike
                documentHandler.endSpan();
            }
            else if ( SUB_ELT.equals( name ) )
            {
                // Subscript
                documentHandler.endSpan();
            }
            else if ( SUP_ELT.equals( name ) )
            {
                // Superscript
                documentHandler.endSpan();
            }
            else if ( UL_ELT.equals( name ) )
            {
                // Unordered List
                endList( false );
            }
            else if ( OL_ELT.equals( name ) )
            {
                // Orderer List
                endList( true );
            }
            else if ( LI_ELT.equals( name ) )
            {
                ignoreCharacters = true;
                // List item
                documentHandler.endListItem();
            }
            else if ( P_ELT.equals( name ) )
            {
                // Paragraph
                documentHandler.endParagraph();
            }
            else if ( H1_ELT.equals( name ) )
            {
                // Header 1
                documentHandler.endHeading( 1 );
            }
            else if ( H2_ELT.equals( name ) )
            {
                // Header 2
                documentHandler.endHeading( 2 );
            }
            else if ( H3_ELT.equals( name ) )
            {
                // Header 3
                documentHandler.endHeading( 3 );
            }
            else if ( H4_ELT.equals( name ) )
            {
                // Header 4
                documentHandler.endHeading( 4 );
            }
            else if ( H5_ELT.equals( name ) )
            {
                // Header 5
                documentHandler.endHeading( 5 );
            }
            else if ( H6_ELT.equals( name ) )
            {
                // Header 6
                documentHandler.endHeading( 6 );
            }
            else if ( A_ELT.equals( name ) )
            {
                // end reference
                documentHandler.handleReference( aHref, aContent.toString() );
                this.aHref = null;
                this.aContent.setLength( 0 );
            }
            else if ( BR_ELT.equals( name ) )
            {
                // <br/>
                documentHandler.handleLineBreak();
            }
            else if ( SPAN_ELT.equals( name ) )
            {
                // </span>
                documentHandler.endSpan();
            }
            else if ( TABLE_ELT.equals( name ) )
            {
                // </table>
                documentHandler.endTable();
            }
            else if ( TR_ELT.equals( name ) )
            {
                // </tr>
                documentHandler.endTableRow();
            }
            else if ( TD_ELT.equals( name ) )
            {
                // </td>
                documentHandler.endTableCell();
            }
        }
        catch ( IOException e )
        {
            throw new SAXException( e );
        }
        super.endElement( uri, localName, name );
    }

    @Override
    public void characters( char[] ch, int start, int length ) throws SAXException
    {
        if ( !ignoreCharacters )
        {
            try
            {
                if ( aHref != null )
                {
                    aContent.append( ch, start, length );
                }
                else
                {
                    documentHandler.handleString( String.valueOf( ch, start, length ) );
                }
            }
            catch ( IOException e )
            {
                throw new SAXException( e );
            }
        }
        super.characters( ch, start, length );
    }

    private void startList( boolean ordered, ContainerProperties properties ) throws IOException
    {
        ignoreCharacters = true;
        if ( ordered )
        {
            documentHandler.startOrderedList( properties );
        }
        else
        {
            documentHandler.startUnorderedList( properties );
        }
    }

    private void endList( boolean ordered ) throws IOException
    {
        if ( ordered )
        {
            documentHandler.endOrderedList();
        }
        else
        {
            documentHandler.endUnorderedList();
        }
    }
}
