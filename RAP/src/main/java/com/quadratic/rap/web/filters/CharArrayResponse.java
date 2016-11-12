package com.quadratic.rap.web.filters;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class CharArrayResponse extends HttpServletResponseWrapper
{

    final CharArrayWriter buffer;
    PrintWriter writer;

    public CharArrayResponse( final HttpServletResponse response )
    {
        super( response );
        buffer = new CharArrayWriter( 1024 );
    }

    @Override
    public PrintWriter getWriter() throws IOException
    {
        if ( writer == null )
        {
            writer = new PrintWriter( buffer );
        }
        return writer;
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException
    {
        return new ServletOutputStream()
        {
            @Override
            public void write( final int b ) throws IOException
            {
                buffer.write( b );
            }

			@Override
			public boolean isReady() {
				
				return false;
			}

			@Override
			public void setWriteListener(WriteListener arg0) {
				
				
			}

           
        };
    }

    @Override
    public String toString()
    {
        return buffer.toString();
    }
}
