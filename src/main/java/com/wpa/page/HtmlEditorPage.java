package com.wpa.page;
import atlantafx.base.controls.ToggleSwitch;
import atlantafx.base.theme.Theme;
import atlantafx.base.util.BBCodeParser;
import java.net.URI;
import javafx.css.PseudoClass;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.web.HTMLEditor;
import org.springframework.stereotype.Component;

@Component
public final class HtmlEditorPage extends AbstractPage {

    private static final PseudoClass USE_LOCAL_URL = PseudoClass.getPseudoClass("use-local-url");

    private HTMLEditor editor;

    @Override
    Node buildContentNode() {
//        addFormattedText("""
//            A control that allows for users to edit text, and apply styling to this text. \
//            The underlying data model is HTML, although this is not shown visually to the end-user."""
//        );
        return editorSample();
    }

    public HtmlEditorPage() {
        super();
//        editor.requestFocus();
    }

    private VBox editorSample() {
        editor = createHtmlEditor();
        var description = BBCodeParser.createFormattedText("""
            Since AtlantaFX themes are also distributed as CSS files, they can't contain any images. \
            Unfortunately, reusing Modena resources in theme also isn't possible, because the they \
            are located in [font=monospace]'com/sun/javafx/*'[/font] package, which isn't opened in \
            OpenJFX [font=monospace]'module-info'[/font]. But you can still copy Modena images and \
            overwrite [i]HMTLEditor[/i] CSS in your app."""
        );

        var fixToggle = new ToggleSwitch("Apply Fix");

        var content = new VBox(20, editor, description, fixToggle);
        content.setAlignment(Pos.TOP_CENTER);
        VBox.setVgrow(content, Priority.ALWAYS);

        fixToggle.selectedProperty().addListener((obs, old, val) -> {
            // toolbar icons can't be changed back without creating new editor instance #javafx-bug
            try {
                editor = createHtmlEditor();
                editor.pseudoClassStateChanged(USE_LOCAL_URL, val);
                content.getChildren().set(0, editor);
                editor.requestFocus();
            } catch (Exception ignored) {
                // hush internal HTML editor errors, because everything
                // we do here is an ugly hack around legacy control anyway
            }
        });

        content.setPadding(new Insets(10));

        return content;
    }

    private HTMLEditor createHtmlEditor() {
        var editor = new HTMLEditor();
        editor.setPrefHeight(400);
        editor.setHtmlText(generateContent());
        VBox.setVgrow(editor, Priority.ALWAYS);
        return editor;
    }

    private String generateContent() {


        return "<!DOCTYPE html>"
                + "<html>"

                + "</body>"
                + "</html>";
    }
}
