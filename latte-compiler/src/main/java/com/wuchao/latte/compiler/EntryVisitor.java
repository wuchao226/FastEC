package com.wuchao.latte.compiler;


import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.SimpleAnnotationValueVisitor7;

/**
 * @author: wuchao
 * @date: 2017/12/27 22:39
 * @desciption:
 */

public class EntryVisitor extends SimpleAnnotationValueVisitor7<Void, Void> {

    private final Filer mFiler;
    private String mPackageName = null;

    EntryVisitor(Filer filer) {
        mFiler = filer;
    }

    @Override
    public Void visitString(String s, Void p) {
        mPackageName = s;
        return p;
    }

    @Override
    public Void visitType(TypeMirror t, Void p) {
        generateJavaCode(t);
        return p;
    }

    private void generateJavaCode(TypeMirror typeMirror) {
        final TypeSpec targetActivity =
                TypeSpec.classBuilder("WXEntryActivity")
                        .addModifiers(Modifier.PUBLIC)
                        .addModifiers(Modifier.FINAL)
                        .superclass(TypeName.get(typeMirror))
                        .build();
        final JavaFile javaFile = JavaFile.builder(mPackageName + ".wxapi", targetActivity)
                .addFileComment("微信入口文件")
                .build();
        try {
            javaFile.writeTo(mFiler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
