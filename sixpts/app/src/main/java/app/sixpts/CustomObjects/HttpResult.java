package app.sixpts.CustomObjects;

/**
 * Created by paulkowa on 12/10/15.
 */
public class HttpResult {
    static String result;

    public HttpResult() {

    }

    public HttpResult(String result) {
        this.result = result;
    }

    static public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
