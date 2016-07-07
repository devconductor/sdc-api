package br.com.conductor.sdc.api.v1.invoker;

import org.apache.http.client.methods.*;

public class HttpPatch extends HttpPost {
    public static final String METHOD_PATCH = "PATCH";

    public HttpPatch(final String url) {
        super(url);
    }

    @Override
    public String getMethod() {
        return METHOD_PATCH;
    }
}