package com.dom.Friend_IM.model.http.response;

/**
 * Created by dom4j on 2017/3/24.
 */

public class TestResult<T> {

    private boolean error;

    private T results;

    public boolean getError() {
        return error;
    }

    public T getResults() {
        return results;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public void setResults(T results) {
        this.results = results;
    }
}
