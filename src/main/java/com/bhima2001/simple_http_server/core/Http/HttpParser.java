package com.bhima2001.simple_http_server.core.Http;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HttpParser {
    private final Logger LOGGER = LogManager.getLogger();
    private final static int SP = 0x20;
    private final static int CR = 0x0D;
    private final static int LF = 0x0A;

    public HttpRequest parseHttpMessage(InputStream stream) throws IOException, HttpParserException {
        HttpRequest request = new HttpRequest();
        InputStreamReader reader = new InputStreamReader(stream, StandardCharsets.US_ASCII);

        parseRequestLine(reader, request);
        parseHeader(reader, request);
        parseBody(reader, request);

        return request;
    }

    private void parseRequestLine(InputStreamReader reader, HttpRequest request)
            throws IOException, HttpParserException {
        StringBuilder stringBuider = new StringBuilder();

        int _byte = 0;
        Boolean methodFlag = false, endpointFlag = false;
        while ((_byte = reader.read()) >= 0) {
            if (_byte == CR) {
                _byte = reader.read();
                if (_byte == LF) {
                    if (!methodFlag || !endpointFlag) {
                        throw new HttpParserException(HttpException.Client_Error_400_Bad_Request);
                    } else {
                        request.setHttpVersion(stringBuider.toString());
                        stringBuider.delete(0, stringBuider.length());
                        LOGGER.info("RequestLine has been successfully parsed.");
                    }
                    return;
                } else {
                    throw new HttpParserException(HttpException.Client_Error_400_Bad_Request);
                }
            }

            if (_byte == SP) {
                if (!methodFlag) {
                    request.setHttpMethod(stringBuider.toString());
                    stringBuider.delete(0, stringBuider.length());
                    methodFlag = true;
                } else {
                    request.setEndpoint(stringBuider.toString());
                    stringBuider.delete(0, stringBuider.length());
                    endpointFlag = true;
                }
            } else {
                stringBuider.append((char) _byte);
            }
        }
    }

    private void parseHeader(InputStreamReader reader, HttpRequest request) throws IOException, HttpParserException {
        StringBuilder stringBuilder = new StringBuilder();
        Boolean crlfFlag = false;
        int _byte;

        while ((_byte = reader.read()) >= 0) {
            if (_byte == CR) {
                _byte = reader.read();
                if (_byte == LF) {
                    if (crlfFlag) {
                        LOGGER.info("Headers has been successfully parsed.");
                        return;
                    } else {
                        crlfFlag = true;
                        request.processHeader(stringBuilder.toString());
                        stringBuilder.delete(0, stringBuilder.length());
                    }
                } else {
                    throw new HttpParserException(HttpException.Client_Error_400_Bad_Request);
                }
            } else {
                stringBuilder.append((char) _byte);
                crlfFlag = false;
            }
        }
    }

    private void parseBody(InputStreamReader reader, HttpRequest request) throws IOException, HttpParserException {
        int _byte;
        StringBuilder stringBuilder = new StringBuilder();
        while (reader.ready() && (_byte = reader.read()) >= 0) {
            stringBuilder.append((char) _byte);
        }
        request.setBody(stringBuilder.toString());
        stringBuilder.delete(0, stringBuilder.length());
        LOGGER.info("Body has been successfully parsed.");
    }

}
