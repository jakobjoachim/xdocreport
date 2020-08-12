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
package fr.opensagres.xdocreport.document.textstyling.wiki.gwiki;

import fr.opensagres.xdocreport.document.textstyling.AbstractDocumentHandler;
import fr.opensagres.xdocreport.document.textstyling.properties.ContainerProperties;
import fr.opensagres.xdocreport.document.textstyling.properties.TableProperties;

import java.io.IOException;

/**
 * Basic Document handler implementation to build html fragment content for unit tests
 */
public class HTMLDocumentHandler
    extends AbstractDocumentHandler
{

    public HTMLDocumentHandler()
    {
        super( null, null, null );
    }

    public void startDocument()
    {
        // super.write("<html>");
        // super.write("<body>");
    }

    public void endDocument()
    {
        // super.write("</body>");
        // super.write("</html>");
    }

    public void startListItem( ContainerProperties properties )
        throws IOException
    {
        super.write( "<li>" );
    }

    public void endListItem()
        throws IOException
    {
        super.write( "</li>" );
    }

    @Override
    protected void doStartOrderedList( ContainerProperties properties )
        throws IOException
    {
        super.write( "<ol>" );
    }

    @Override
    protected void doEndOrderedList()
        throws IOException
    {
        super.write( "</ol>" );
    }

    @Override
    protected void doStartUnorderedList( ContainerProperties properties )
        throws IOException
    {
        super.write( "<ul>" );
    }

    @Override
    protected void doEndUnorderedList()
        throws IOException
    {
        super.write( "</ul>" );
    }

    public void startParagraph( ContainerProperties properties )
        throws IOException
    {
        super.write( "<p>" );
    }

    public void endParagraph()
        throws IOException
    {
        super.write( "</p>" );
    }

    public void startSpan( ContainerProperties properties )
        throws IOException
    {
        // this code is just for one test so we write the properties into the span
        super.write( "<span " + getStyle( properties ) + ">" );
    }

    public void endSpan()
        throws IOException
    {
        super.write( "</span>" );
    }

    public void startHeading( int level, ContainerProperties properties )
        throws IOException
    {
        super.write( "<h" );
        super.write( level );
        super.write( ">" );

    }

    public void endHeading( int level )
        throws IOException
    {
        super.write( "</h" );
        super.write( level );
        super.write( ">" );
    }

    public void handleReference( String ref, String label )
        throws IOException
    {
        super.write( "<a href=\"" );
        super.write( ref );
        super.write( "\" >" );
        super.write( label );
        super.write( "</a>" );

    }

    public void handleImage( String ref, String label )
        throws IOException
    {
        super.write( "<img src=\"" );
        super.write( ref );
        super.write( "/>" );

    }

    public void handleLineBreak()
        throws IOException
    {
        super.write( "<br />" );
    }

    public void doStartTable( TableProperties properties )
        throws IOException
    {
        super.write( "<table>" );
    }

    public void doEndTable(TableProperties properties)
        throws IOException
    {
        super.write( "</table>" );
    }

    protected void doStartTableRow( ContainerProperties properties )
        throws IOException
    {
        super.write( "<tr>" );
    }

    public void doEndTableRow()
        throws IOException
    {
        super.write( "</tr>" );
    }

    protected void doStartTableCell( ContainerProperties properties )
        throws IOException
    {
        super.write( "<td>" );
    }

    public void doEndTableCell()
        throws IOException
    {
        super.write( "</td>" );
    }

    private String getStyle(ContainerProperties containerProperties) {
        StringBuilder builder = new StringBuilder();
        if (containerProperties.isBold()) {
            builder.append( "font-weight: bold;" );
        }
        if (containerProperties.isItalic()) {
            builder.append( "font-style: italic;" );
        }
        if (containerProperties.isUnderline()) {
            builder.append( "text-decoration: underline;" );
        }
        if (builder.toString().isEmpty()) {
            return "";
        }
        return "style=\"" + builder.toString() + "\"";
    }
}
