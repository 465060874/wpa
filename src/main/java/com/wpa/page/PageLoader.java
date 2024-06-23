package com.wpa.page;

@FunctionalInterface
public interface PageLoader {
    void loadPage(Class<? extends Page> pageClass,String name);
}
