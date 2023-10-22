package com.padr.gys.domain.statusmanager.handler;

import com.padr.gys.domain.statusmanager.model.StatusChangeModel;

public interface StatusChangeHandler {

    void handle(StatusChangeModel statusChangeModel);
}
