package com.quadratic.rap.web.filters;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.googlecode.htmlcompressor.compressor.HtmlCompressor;

public class HtmlCompressorFilter implements Filter
{

    final HtmlCompressor compressor = new HtmlCompressor();

    @Override
    public void destroy()
    {

    }

    @Override
    public void doFilter( final ServletRequest request, final ServletResponse response, final FilterChain filterChain )
            throws IOException, ServletException
    {
        final HttpServletRequest hrequest = (HttpServletRequest) request;
        if ( hrequest.getRequestURI().contains( "images/" ) || hrequest.getRequestURI().contains( "resources/" ) )
        {
            filterChain.doFilter( request, response );
            return;
        }
        final CharArrayResponse cresponse = new CharArrayResponse( (HttpServletResponse) response );

        filterChain.doFilter( request, cresponse );

        final PrintWriter writer = response.getWriter();
        if ( StringUtils.isNotBlank( cresponse.getContentType() ) && cresponse.getContentType().contains( "html" ) )
        {
            writer.write( compressor.compress( cresponse.toString() ) );
        }
        else
        {
            writer.write( cresponse.toString() );
        }
    }

    @Override
    public void init( final FilterConfig filterConfig ) throws ServletException
    {

    }

}
