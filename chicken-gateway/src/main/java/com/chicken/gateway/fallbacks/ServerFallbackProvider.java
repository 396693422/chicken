package com.chicken.gateway.fallbacks;

import com.alibaba.fastjson.JSONObject;
import com.chicken.common.base.enums.ResponseStatusEnum;
import com.chicken.common.base.vo.ResponseVO;
import com.netflix.hystrix.exception.HystrixTimeoutException;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;

/**
 * 所有微服务网关fallback
 *
 * @author huangjianwen
 * @version 1.0
 * @since 2018-12-28
 */
@Component
@Slf4j
public class ServerFallbackProvider implements FallbackProvider {

	/**
	 * 如果要为所有路由提供默认回退，使getRoute方法返回*或null即可
	 *
	 * @return 各个微服务的实例名
	 */
	@Override
	public String getRoute() {
		return "*";
	}

	@Override
	public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
		if (cause instanceof HystrixTimeoutException) {
			return response(HttpStatus.GATEWAY_TIMEOUT);
		} else {
			return fallbackResponse();
		}
	}

	public ClientHttpResponse fallbackResponse() {
		return response(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private ClientHttpResponse response(final HttpStatus status) {
		RequestContext ctx = RequestContext.getCurrentContext();
		return new ClientHttpResponse() {

			@Override
			public HttpStatus getStatusCode() {
				return status;
			}

			@Override
			public int getRawStatusCode() {
				return status.value();
			}

			@Override
			public String getStatusText() {
				return status.getReasonPhrase();
			}

			@Override
			public void close() {
			}

			@Override
			public InputStream getBody() throws IOException {
				ResponseVO<String> responseVO = new ResponseVO<String>();
				responseVO.setCode(ResponseStatusEnum.ZUUL_FALL_BACK.getCode());
				String message = MessageFormat.format(
						ResponseStatusEnum.ZUUL_FALL_BACK.getMessage(),"当前");
				responseVO.setMessage(message);
				String fallbackResponse = JSONObject.toJSONString(responseVO);
				log.info(">>> 触发服务实例{}服务降级回调，默认响应：{}", getRoute(),
						fallbackResponse);
				return new ByteArrayInputStream(fallbackResponse.getBytes(StandardCharsets.UTF_8));
			}

			@Override
			public HttpHeaders getHeaders() {
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
				return headers;
			}
		};
	}
}
