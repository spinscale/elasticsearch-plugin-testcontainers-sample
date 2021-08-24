package de.spinscale.elasticsearch;

import org.elasticsearch.client.node.NodeClient;
import org.elasticsearch.rest.BaseRestHandler;
import org.elasticsearch.rest.BytesRestResponse;
import org.elasticsearch.rest.RestRequest;
import org.elasticsearch.rest.RestStatus;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class MyRestHandler extends BaseRestHandler {

    private static final BytesRestResponse RESPONSE = new BytesRestResponse(RestStatus.OK, "my-rest-handler");

    @Override
    public String getName() {
        return "my-rest-handler";
    }

    @Override
    public List<Route> routes() {
        return Collections.singletonList(new Route(RestRequest.Method.GET, "my-rest-handler"));
    }

    @Override
    protected RestChannelConsumer prepareRequest(RestRequest request, NodeClient client) throws IOException {
        return channel -> channel.sendResponse(RESPONSE);
    }
}
