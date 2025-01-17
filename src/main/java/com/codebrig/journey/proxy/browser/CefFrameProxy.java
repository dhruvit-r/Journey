package com.codebrig.journey.proxy.browser;

import org.joor.Reflect;

/**
 * Journey local proxy for CefFrame.
 * <p>
 * Javadoc taken from: https://bitbucket.org/chromiumembedded/java-cef
 *
 * @author <a href="mailto:brandon.fergerson@codebrig.com">Brandon Fergerson</a>
 * @version 0.3.3
 * @since 0.2.18
 */
@SuppressWarnings("unused")
public interface CefFrameProxy {

    Reflect.ProxyArgumentsConverter PROXY_ARGUMENTS_CONVERTER = (methodName, args) -> {
    };

    Reflect.ProxyValueConverter PROXY_VALUE_CONVERTER = (methodName, returnValue) -> {
        if ("getParent".equals(methodName)) {
            return Reflect.on(returnValue).as(CefFrameProxy.class);
        }
        return returnValue;
    };

    /**
     * Returns the globally unique identifier for this frame or < 0 if the
     * underlying frame does not yet exist.
     *
     * @return The frame identifier
     */
    long getIdentifier();

    /**
     * Emits the URL currently loaded in this frame.
     *
     * @return the URL currently loaded in this frame.
     */
    String getURL();

    /**
     * Returns the name for this frame. If the frame has an assigned name (for
     * example, set via the iframe "name" attribute) then that value will be
     * returned. Otherwise a unique name will be constructed based on the frame
     * parent hierarchy. The main (top-level) frame will always have an empty name
     * value.
     *
     * @return The frame name
     */
    String getName();

    /**
     * Returns true if this is the main (top-level) frame.
     *
     * @return True if this frame is top-level otherwise false.
     */
    boolean isMain();

    /**
     * True if this object is currently attached to a valid frame.
     *
     * @return True if valid otherwise false.
     */
    boolean isValid();

    /**
     * Returns true if this is the focused frame.
     *
     * @return True if valid otherwise false.
     */
    boolean isFocused();

    /**
     * Returns the parent of this frame or NULL if this is the main (top-level)
     * frame.
     *
     * @return The parent frame or NULL if this is the main frame
     */
    CefFrameProxy getParent();

    /**
     * Execute a string of JavaScript code in this frame. The url
     * parameter is the URL where the script in question can be found, if any.
     * The renderer may request this URL to show the developer the source of the
     * error. The line parameter is the base line number to use for error
     * reporting.
     *
     * @param code The code to be executed.
     * @param url  The URL where the script in question can be found.
     * @param line The base line number to use for error reporting.
     */
    void executeJavaScript(String code, String url, int line);
}
