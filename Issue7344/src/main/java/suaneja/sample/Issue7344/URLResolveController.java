package suaneja.sample.Issue7344;


import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.reactivex.Single;

import javax.annotation.Nullable;

@Controller
public class URLResolveController {

    @Get(uris = {
            "/ic/{apiType}/integration/v1/flows/{appType}/{flowId}/{flow_version}",
            "/ic/{apiType}/integration/v1/flows/{appType}/{flowId}/{flow_version}/{+resourcePath}",
            "/ic/{apiType}/integration/v1/flows/{appType}/automation/{automationId}/{automationVersion}/{flowId}",
            "/ic/{apiType}/integration/v1/flows/{appType}/automation/{automationId}/{automationVersion}/{flowId}/{+resourcePath}"
    })
    @Produces(MediaType.ALL)
//    @ExecuteOn(AsyncRuntimeController.ADAPTER_INBOUND_EXECUTOR)
    public Single<HttpResponse> triggerGet(@PathVariable("apiType") String apiType,
                                           @PathVariable("appType") String appType,
                                           @PathVariable("automationId") @Nullable String automationId,
                                           @PathVariable("automationVersion") @Nullable String automationVersion,
                                           @PathVariable("flowId") String flowId,
                                           @PathVariable("flow_version") @Nullable String flow_version,
                                           @PathVariable("resourcePath") @Nullable String resourcePath,
                                           HttpRequest httpRequest) {

        System.out.println("triggerGet");
        return Single.fromCallable(() -> HttpResponse.ok("{ \"message\" : [\"hello world\",\"\"] }"));
    }

    @Put(uris = {
            "/ic/{apiType}/integration/v1/flows/{appType}/{flowId}/{flow_version}",
            "/ic/{apiType}/integration/v1/flows/{appType}/{flowId}/{flow_version}/{+resourcePath}",
            "/ic/{apiType}/integration/v1/flows/{appType}/automation/{automationId}/{automationVersion}/{flowId}",
            "/ic/{apiType}/integration/v1/flows/{appType}/automation/{automationId}/{automationVersion}/{flowId}/{+resourcePath}"
    })
    @Produces(MediaType.ALL)
    @Consumes(MediaType.ALL)
//    @ExecuteOn(AsyncRuntimeController.ADAPTER_INBOUND_EXECUTOR)
    public Single<HttpResponse> triggerPut(@PathVariable("apiType") String apiType,
                                           @PathVariable("appType") String appType,
                                           @PathVariable("automationId") @Nullable String automationId,
                                           @PathVariable("automationVersion") @Nullable String automationVersion,
                                           @PathVariable("flowId") String flowId,
                                           @PathVariable("flow_version") @Nullable String flow_version,
                                           @PathVariable("resourcePath") @Nullable String resourcePath,
                                           @Body @Nullable byte[] payload, HttpRequest httpRequest) {

        System.out.println("triggerPUT");
        return Single.fromCallable(() -> HttpResponse.ok("{ \"message\" : [\"hello world\",\"\"] }"));
    }

}

