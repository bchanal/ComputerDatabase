package com.excilys.cdb.web.wrapper;

import java.util.List;

public class ListWrapper<T> {

    private List<T> list;

    public ListWrapper() {}

    public void setList(List<T> pList) {
        list = pList;
    }

    public List<T> getList() {
        return list;
    }
}
