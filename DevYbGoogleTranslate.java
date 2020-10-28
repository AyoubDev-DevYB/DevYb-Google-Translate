package com.devyb.devybgoogletranslate;


import android.app.Activity;
import android.content.Context;
import com.google.appinventor.components.annotations.*;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.AndroidNonvisibleComponent;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.ComponentContainer;
import com.google.appinventor.components.runtime.EventDispatcher;



@DesignerComponent(version = 1,  description = "DevYB Google Translate developed by DevYB.\n" +
        "This extension allows for translating any language using Google translate.\n" +
        "This extension works with Auto Detect and without.",
        category = ComponentCategory.EXTENSION,
        nonVisible = true,   iconName = "https://res.cloudinary.com/dujfnjfcz/image/upload/v1596225104/icon16.png")
@UsesPermissions(permissionNames = "android.permission.INTERNET")
@SimpleObject(external = true)
public class DevYbGoogleTranslate extends AndroidNonvisibleComponent {
    private Context context;
    private Activity activity;
    private ComponentContainer container;



    public DevYbGoogleTranslate(ComponentContainer container) {
        super(container.$form());
        this.container = container;
        activity = container.$context();
        context = (Context) container.$context();
    }

    @SimpleFunction
    public void AutoTranslate(String targetLanguage, String textToTranslate){
        TranslateAPI translateAPI = new TranslateAPI("auto", targetLanguage, textToTranslate);
        translateAPI.setTranslateListener(new TranslateAPI.TranslateListener() {
            @Override
            public void onSuccess(String translatedText) {
                Translated(translatedText);
            }

            @Override
            public void onFailure(String ErrorText) {
                Failed(ErrorText);
            }
        });
    }

    @SimpleFunction
    public void Translate(String sourceLanguage, String targetLanguage, String textToTranslate){
        TranslateAPI translateAPI = new TranslateAPI(sourceLanguage, targetLanguage, textToTranslate);
        translateAPI.setTranslateListener(new TranslateAPI.TranslateListener() {
            @Override
            public void onSuccess(String translatedText) {
                Translated(translatedText);
            }

            @Override
            public void onFailure(String ErrorText) {
                Failed(ErrorText);
            }
        });
    }

    @SimpleEvent
    public void Translated(String text){
        EventDispatcher.dispatchEvent(this, "Translated", text);
    }

    @SimpleEvent
    public void Failed(String error){
        EventDispatcher.dispatchEvent(this, "Failed", error);
    }


}
