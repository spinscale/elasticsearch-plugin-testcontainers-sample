package de.spinscale.elasticsearch;

import org.elasticsearch.common.bytes.BytesArray;
import org.elasticsearch.common.xcontent.NamedXContentRegistry;
import org.elasticsearch.http.HttpChannel;
import org.elasticsearch.http.HttpRequest;
import org.elasticsearch.rest.RestChannel;
import org.elasticsearch.rest.RestRequest;
import org.elasticsearch.rest.RestResponse;
import org.elasticsearch.rest.RestStatus;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MyRestHandlerTest {

    private final MyRestHandler handler = new MyRestHandler();
    private final HttpRequest request = mock(HttpRequest.class);
    private final HttpChannel channel = mock(HttpChannel.class);
    private final RestChannel restChannel = mock(RestChannel.class);

    @Test
    public void testRestHandlerResponse() throws Exception {
        when(request.uri()).thenReturn("my-rest-handler");
        when(request.content()).thenReturn(BytesArray.EMPTY);
        RestRequest restRequest = RestRequest.request(NamedXContentRegistry.EMPTY, request, channel);

        handler.handleRequest(restRequest, restChannel, null);

        ArgumentCaptor<RestResponse> captor = ArgumentCaptor.forClass(RestResponse.class);
        verify(restChannel).sendResponse(captor.capture());

        final RestResponse response = captor.getValue();
        assertEquals(response.status(), RestStatus.OK);
        assertEquals(response.content().utf8ToString(), "my-rest-handler");
    }
}
