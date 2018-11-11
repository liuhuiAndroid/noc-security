package com.noc.wiremock;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

public class MockServer {

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        // 配置WireMock服务器
        WireMock.configureFor(8081);
        // 清空以前的配置
        WireMock.removeAllMappings();

        mock("/order/1", "01");
        mock("/order/2", "02");
    }

    private static void mock(String url, String file) throws IOException {
        ClassPathResource resource = new ClassPathResource("mock/response/" + file + ".txt");
        String content = StringUtils.join(FileUtils.readLines(resource.getFile(), "UTF-8").toArray(), "\n");
        // 伪造测试
        WireMock.stubFor(
                // get请求，url完全匹配
                WireMock.get(WireMock.urlPathEqualTo(url))
                        // 服务器返回数据
                        .willReturn(WireMock.aResponse().withBody(content).withStatus(200)));
    }

}
