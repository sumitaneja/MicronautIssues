package com.example;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MutableHttpRequest;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.reactor.http.client.ReactorHttpClient;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
class TestControllerTest {

    @Inject @Client("/")
    ReactorHttpClient client;

    @Test
    public void testController() throws InterruptedException {
        String body = "{\"message\":\"hello World\"}";
        String TOKEN="eyJ4NXQjUzI1NiI6IkJfYVBJTjNQQnZNdEpJZW9oVjhUZk1FeU1UYmIzRVgzZVBiWjdFMS10bWsiLCJ4NXQiOiI4bFEx" +
                "T2N3eHJVZnlsb3BOLVdNTkk4TUwtSFUiLCJraWQiOiJTSUdOSU5HX0tFWSIsImFsZyI6IlJTMjU2In0.eyJjbGllbnRfb2Np" +
                "ZCI6Im9jaWQxLmRvbWFpbmFwcC5vYzEucGh4LmFtYWFhYWFhN3ljM2l0YWFwbDMyYzZhcHE2ZHR2d3NiN2Eyb3FudG5mcXB" +
                "nZGgzdmxkbnhvaWpxeWwycSIsInVzZXJfdHoiOiJBbWVyaWNhXC9DaGljYWdvIiwic3ViIjoiZG1pdHJ5LmxpcGluQG9y" +
                "YWNsZS5jb20iLCJ1c2VyX2xvY2FsZSI6ImVuIiwic2lkbGUiOjQ4MCwidXNlci50ZW5hbnQubmFtZSI6ImlkY3MtZTk2" +
                "MThkOWRlYTg3NDNhOWJmMmIzNjRmYWI5NWZiMDIiLCJpc3MiOiJodHRwczpcL1wvaWRlbnRpdHkub3JhY2xlY2xvdWQu" +
                "Y29tXC8iLCJkb21haW5faG9tZSI6InVzLXBob2VuaXgtMSIsImNhX29jaWQiOiJvY2lkMS50ZW5hbmN5Lm9jMS4uYW" +
                "FhYWFhYWF6NGt3dXRmcXp6emZzcm1oZG56YXhrcGR2dGl4aDV5eTR0YmI3Y2h4dmk0cGNvdHc0MnphIiwidXNlcl90" +
                "ZW5hbnRuYW1lIjoiaWRjcy1lOTYxOGQ5ZGVhODc0M2E5YmYyYjM2NGZhYjk1ZmIwMiIsImNsaWVudF9pZCI6ImU4M2" +
                "UzZTEzNzg0MDRkZTA5NmZmOTg2ZmEwMDYyYWVjIiwiZG9tYWluX2lkIjoib2NpZDEuZG9tYWluLm9jMS4uYWFhYW" +
                "FhYWF2Nmo0N2R6NXRla2Zma2V0aXp1ZHVrM3Izbnp0bTQ3YW1uNHkyZXY2NmVnZmFjcHZsZnNxIiwic3ViX3R5cGUi" +
                "OiJ1c2VyIiwic2NvcGUiOiJ1cm46b3BjOnJlc291cmNlOmNvbnN1bWVyOjphbGwiLCJ1c2VyX29jaWQiOiJvY2lkMS" +
                "51c2VyLm9jMS4uYWFhYWFhYWFxeW5mNmtscHltb3hqeDVwNW9oMzRsaXlpYnViNWk3YnU0bWx2NnphZHVtcWNhczNub" +
                "mJhIiwiY2xpZW50X3RlbmFudG5hbWUiOiJpZGNzLWU5NjE4ZDlkZWE4NzQzYTliZjJiMzY0ZmFiOTVmYjAyIiwicmVna" +
                "W9uX25hbWUiOiJ1cy1waG9lbml4LWlkY3MtMyIsInVzZXJfbGFuZyI6ImVuIiwiZXhwIjoxNzAyOTI5OTUxLCJpYXQi" +
                "OjE3MDI5MjYzNTEsImNsaWVudF9ndWlkIjoiNjA4M2ZiMmEzYzI2NDRiM2FhODU5ZWNiYzgxMDYzZmUiLCJjbGllbnRfb" +
                "mFtZSI6ImRsaXBpbl9mYWNhZGVfYXBpX2QxX2FwcCIsInRlbmFudCI6ImlkY3MtZTk2MThkOWRlYTg3NDNhOWJmMmIzNj" +
                "RmYWI5NWZiMDIiLCJqdGkiOiI4OWM3MGE1OWM1NjE0YmEwYjYzNjBkZDBkM2JkNzhkNSIsImd0cCI6InJvIiwidXNlcl" +
                "9kaXNwbGF5bmFtZSI6IkRtaXRyaWkgTGlwaW4iLCJvcGMiOmZhbHNlLCJzdWJfbWFwcGluZ2F0dHIiOiJ1c2VyTmFtZS" +
                "IsInByaW1UZW5hbnQiOmZhbHNlLCJ0b2tfdHlwZSI6IkFUIiwiYXVkIjpbImh0dHBzOlwvXC9GQjA4RTc3OTEwRTI0R" +
                "UNCOUI3NzlEQzJFMDNEODczRS5pbnRlZ3JhdGlvbi51cy1waG9lbml4LTEub2NwLm9yYWNsZWNsb3VkLmNvbTo0NDMiL" +
                "CJodHRwczpcL1wvZGVzaWduLmludGVncmF0aW9uLnVzLXBob2VuaXgtMS5vY3Aub3JhY2xlY2xvdWQuY29tP2ludGVn" +
                "cmF0aW9uSW5zdGFuY2U9c2xhcnRpYmFydGZhc3QtaWRxbjk0aGJrYTR3LXB4IiwiaHR0cHM6XC9cL3NsYXJ0aWJhcnRm" +
                "YXN0LWlkcW45NGhia2E0dy1weC5pbnRlZ3JhdGlvbi51cy1waG9lbml4LTEub2NwLm9yYWNsZWNsb3VkLmNvbTo0NDMi" +
                "LCJ1cm46b3BjOmxiYWFzOmxvZ2ljYWxndWlkPUZCMDhFNzc5MTBFMjRFQ0I5Qjc3OURDMkUwM0Q4NzNFIl0sImNhX25hbWU" +
                "iOiJhcmNoZGV2IiwidXNlcl9pZCI6IjhiMGViODJjODI0NjQ2OWU5NjNmNDQ1ZjEwZThmZTQyIiwiZG9tYWluIjoiSGFwcH" +
                "lEb21haW4iLCJ0ZW5hbnRfaXNzIjoiaHR0cHM6XC9cL2lkY3MtZTk2MThkOWRlYTg3NDNhOWJmMmIzNjRmYWI5NWZiMDIua" +
                "WRlbnRpdHkub3JhY2xlY2xvdWQuY29tOjQ0MyIsInJlc291cmNlX2FwcF9pZCI6Ijg2ZjkxZDgxNGUyOTQyZGY4ZDJkYmN" +
                "mMjg0NWM4Nzk4In0.E7tdurIj4-2zMfvyxszGloHZfw1aD8Cyx9CnAgYSikRAKEM8j7UnVF7Ue6inMdbcQX681WYgzKj-Gj" +
                "MGiiXFwFZhMBsSZGHaEgqlEIpedeWsUaEqyLwo11LFAyRvanUKeJZjgkbjM1N1-_lzZ_1HyewnrsA0rlm2snSr8_LsGZNzj" +
                "jjMQeXJdhiW37dYuOrDZlljMwkoeSasbofKrHw0jNPcN431MVcRL1FXyx0-kWLWxFy3hzndZQVNngBjw5ToOJi8gKnLsoU5" +
                "re4rj53eG2ThpujbdN3jYC_KY46drTqs6rq12ebZpjesBidxD1AJDx6OKdXrwCP8EJhE4H8qwg";
        String SUB_TOKEN1= "23402139i40912309481230984098231094810923840912394823019840982309480293184098123" +
                "09480291384098123094809123804812312341324921384972138947918237498723198479823749872398749821" +
                "73472139874981237947129323412342314123421341234213412312342342342134123423423423142314321423" +
                "42134213412341242323492318409812309482130840123849123804812093480912380948123904809123804981" +
                "23098409123841234190238409128304981230948092183409823190840912830948019232342380948092138409" +
                "823104812384092183094812304809123823498123094809832948091212342342134121";

        String SUB_TOKEN2= "23402139i409123094812309840982310948109238409123948230198409823094802931840981230948" +
                "029138409812309480912380481231234132492138497213894791823749872319847982374987239874982173472139" +
                "874981237947129323412342314123421341234213412312342342342134123423423423142314321423421342134123" +
                "412423234923184098123094821308401238491238048120934809123809481239048091238049812309840912384123" +
                "4190238409128304981230948092183409823190840912830948019232342380948092138409823104812384092183" +
                "094812304809123823498123094809832948091212342342134121 23402139i409123094812309840982310948109" +
                "2384091239482301984098230948029318409812309480291384098123094809123804812312341324921384972138" +
                "94791823749872319847982374987239874982173472139874981237947129323412342314123421341234213412312" +
                "34234234213412342342342314231432142342134213412341242323492318409812309482130840123849123804812" +
                "09348091238094812390480912380498123098409123841234190238409128304981230948092183409823190840912" +
                "830948019232342380948092138409823104812384092183094812304809123823498123094809832948091212342342134121";


        MutableHttpRequest req = HttpRequest.POST("/endpoint/outbound/1234/request", body)
                .header("X-B3-TraceId", "c2c5411ff5475bce")
                .header("TESTHeader1",TOKEN)
                .header("TESTHeader1",TOKEN)
        .header("TESTHeader2",TOKEN + " " + SUB_TOKEN2 );

       try {
           HttpResponse resp = client
                   .toBlocking()
                   .exchange(req, String.class);

           System.out.println(resp.getStatus());
           System.out.println(new String((byte[]) resp.getBody(byte[].class).get()));
       }catch (Exception e){
           System.out.println(e.getMessage());
       }


        Thread.sleep(12000l);
    }

}