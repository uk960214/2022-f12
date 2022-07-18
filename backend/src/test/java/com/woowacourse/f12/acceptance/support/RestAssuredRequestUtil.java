package com.woowacourse.f12.acceptance.support;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class RestAssuredRequestUtil {

    private RestAssuredRequestUtil() {
    }

    public static ExtractableResponse<Response> GET_요청을_보낸다(final String url) {
        return RestAssured.given().log().all()
                .when()
                .get(url)
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 로그인된_상태로_POST_요청을_보낸다(final String url, final String token,
                                                                      final Object requestBody) {
        return RestAssured.given().log().all()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(requestBody)
                .when()
                .post(url)
                .then().log().all()
                .extract();
    }
}
