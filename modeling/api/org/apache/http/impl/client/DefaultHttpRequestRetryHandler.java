package org.apache.http.impl.client;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;

import javax.net.ssl.SSLHandshakeException;

import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;

public class DefaultHttpRequestRetryHandler implements HttpRequestRetryHandler {
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:39.141 -0500", hash_original_field = "D9FEB852094E33F39686A155B39B7EAB", hash_generated_field = "1D6143EFD067DE65E0F1632FD027ED68")

    private  int retryCount;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:39.144 -0500", hash_original_field = "C39C11279B9BC7C1FB28ED41444C6FED", hash_generated_field = "5229ECE23C7E3ED8EB78411CF48864E4")

    private  boolean requestSentRetryEnabled;
    
    /**
     * Default constructor
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:39.147 -0500", hash_original_method = "6D9057C3F9363D21510D9933DD6755F5", hash_generated_method = "641F2797EA6247F70460050F779F6068")
    
public DefaultHttpRequestRetryHandler(int retryCount, boolean requestSentRetryEnabled) {
        super();
        this.retryCount = retryCount;
        this.requestSentRetryEnabled = requestSentRetryEnabled;
    }
    
    /**
     * Default constructor
     */
    @DSSafe(DSCat.SAFE_OTHERS)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:39.150 -0500", hash_original_method = "5F4132CBD367F2EB70F7284BB1D2E959", hash_generated_method = "AD0DC257A3D41506A418B6E40F879F9E")
    
public DefaultHttpRequestRetryHandler() {
        this(3, false);
    }
    /** 
     * Used <code>retryCount</code> and <code>requestSentRetryEnabled</code> to determine
     * if the given method should be retried.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:39.152 -0500", hash_original_method = "CD15A57681F805F21E0C1F750415FEC8", hash_generated_method = "42C8B26585FEBA593CB52CC3A9149B6A")
    
public boolean retryRequest(
            final IOException exception, 
            int executionCount,
            final HttpContext context) {
        if (exception == null) {
            throw new IllegalArgumentException("Exception parameter may not be null");
        }
        if (context == null) {
            throw new IllegalArgumentException("HTTP context may not be null");
        }
        if (executionCount > this.retryCount) {
            // Do not retry if over max retry count
            return false;
        }
        if (exception instanceof NoHttpResponseException) {
            // Retry if the server dropped connection on us
            return true;
        }
        if (exception instanceof InterruptedIOException) {
            // Timeout
            return false;
        }
        if (exception instanceof UnknownHostException) {
            // Unknown host
            return false;
        }
        if (exception instanceof SSLHandshakeException) {
            // SSL handshake exception
            return false;
        }
        Boolean b = (Boolean)
            context.getAttribute(ExecutionContext.HTTP_REQ_SENT);
        boolean sent = (b != null && b.booleanValue());
        if (!sent || this.requestSentRetryEnabled) {
            // Retry if the request has not been sent fully or
            // if it's OK to retry methods that have been sent
            return true;
        }
        // otherwise do not retry
        return false;
    }
    
    /**
     * @return <code>true</code> if this handler will retry methods that have 
     * successfully sent their request, <code>false</code> otherwise
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:39.155 -0500", hash_original_method = "D094CEC45107E8B04F6D27DB66E61646", hash_generated_method = "A847113868D21B6BEB4F4931C33984F8")
    
public boolean isRequestSentRetryEnabled() {
        return requestSentRetryEnabled;
    }

    /**
     * @return the maximum number of times a method will be retried
     */
    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:39.157 -0500", hash_original_method = "C82A3B7CA490A8560B081A320AF1EB0A", hash_generated_method = "64E61A17028D879E6ECB4FA7147907DE")
    
public int getRetryCount() {
        return retryCount;
    }
    
}
