package org.heavywater.HttpMini;
import java.util.Map;

public class HttpResponse {
    private HttpHeader header;
    private String content;

    public HttpResponse(){
        header = new HttpHeader();
        content = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
        
    }
    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content += content;
    }

    /**
     * @return the header
     */
    public HttpHeader getHeader() {
        return header;
    }

    /**
     * @param header the header to set
     */
    public void setHeader(HttpHeader header) {
        this.header = header;
    }

    public void setHeader(Map headermap) {
        header.setProperties(headermap);
    }
    @Override
    public String toString(){
        String s = "[ response ]\n";
        s += header.toString() + "\n";
        s += content;
        return s;
    }

    public void print(){
        System.err.println(this.toString());
    }
}
