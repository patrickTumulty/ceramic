package com.ptumulty.ceramic_ui_api;

import javafx.scene.Scene;

public class CeramicStyleSheetLoader
{
    public static void load(Scene scene)
    {
        scene.getStylesheets().add("css/ceramic_global_styles.css");
        scene.getStylesheets().add("css/indicator_component.css");
    }
}
