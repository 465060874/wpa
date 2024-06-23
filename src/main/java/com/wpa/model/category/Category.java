package com.wpa.model.category;


import com.wpa.model.CodeNamePair;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private int id;
    private String code;
    private String name;
    private List<CategoryItem> items = new ArrayList<CategoryItem>();


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CategoryItem> getItems() {
        return items;
    }

    public void setItems(List<CategoryItem> items) {
        this.items = items;
    }

    private CodeNamePair parent;
    private List<CodeNamePair> children = new ArrayList<CodeNamePair>();

    public CodeNamePair getParent() {
        return parent;
    }

    public void setParent(CodeNamePair parent) {
        this.parent = parent;
    }

    public List<CodeNamePair> getChildren() {
        return children;
    }

    public void setChildren(List<CodeNamePair> children) {
        this.children = children;
    }

    public static List<Category> getCategories() {
        List<Category> categories = new ArrayList<Category>();
        Category br = new Category();
        br.setParent(new CodeNamePair("BR", "Booking Request"));
        br.getChildren().add(new CodeNamePair("br_business","BR Business Knowledge"));
        br.getChildren().add(new CodeNamePair("br_message","BR Message Samples"));
        br.getChildren().add(new CodeNamePair("br_integration","BR Integration Flow"));
        categories.add(br);

        Category si = new Category();
        si.setParent(new CodeNamePair("si", "Shipping Instruction"));
        si.getChildren().add(new CodeNamePair("si_business","SI Business Knowledge"));
        si.getChildren().add(new CodeNamePair("si_message","SI Message Samples"));
        si.getChildren().add(new CodeNamePair("si_integration","SI Integration Flow"));
        categories.add(si);

        Category jenkov = new Category();
        jenkov.setParent(new CodeNamePair("jenkov", "jenkov"));
        categories.add(jenkov);

        Category controlsfx = new Category();
        controlsfx.setParent(new CodeNamePair("controlsfx", "controlsfx"));
        categories.add(controlsfx);

        Category FormsFX = new Category();
        FormsFX.setParent(new CodeNamePair("FormsFX", "FormsFX"));
        categories.add(FormsFX);

        Category AtlantaFX = new Category();
        AtlantaFX.setParent(new CodeNamePair("AtlantaFX", "AtlantaFX"));
        categories.add(AtlantaFX);

        Category CSPROD = new Category();
        CSPROD.setParent(new CodeNamePair("CSPROD", "CargoSmart PROD IS"));
        categories.add(CSPROD);

        return categories;
    }
}
