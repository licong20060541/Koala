package com.lijiankun24.koala;

import org.gradle.api.Project;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * KoalaLogVisitor.java
 * <p>
 * Created by lijiankun24 on 2018/7/29.
 */
public class KoalaLogVisitor extends ClassVisitor {

    private String mClassName;
    Project project;

    public KoalaLogVisitor(ClassVisitor classVisitor, Project project) {
        super(Opcodes.ASM5, classVisitor);
        this.project = project;
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces);
        this.mClassName = name;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        MethodVisitor methodVisitor = super.visitMethod(access, name, desc, signature, exceptions);
        methodVisitor = new KoalaLogMethodVisitor(Opcodes.ASM5, methodVisitor, access, mClassName, name, desc, project);
        return methodVisitor;
    }
}
