package corruptionhades.utils;

import corruptionhades.Agent;
import javassist.*;
import javassist.expr.ExprEditor;
import javassist.expr.FieldAccess;

import java.lang.instrument.ClassDefinition;

public class CodeInjection {

    public static void injectMethod(Class<?> clazz, String methodName, String code, Location where, Class<?>... parameterTypes) throws Exception {
        ClassLoader targetClassLoader = ClassLoader.getSystemClassLoader();
        ClassPool classPool = ClassPool.getDefault();
        classPool.appendSystemPath();
        classPool.insertClassPath(new LoaderClassPath(targetClassLoader));
        CtClass ctClass = classPool.get(clazz.getName());
        ctClass.stopPruning(true);
        if (ctClass.isFrozen()) {
            ctClass.defrost();
        }
        CtMethod method = ctClass.getDeclaredMethod(methodName, toCtClasses(classPool, parameterTypes));
        if (where == Location.BEFORE) {
            method.insertBefore(code);
        } else if (where == Location.AFTER) {
            method.insertAfter(code);
        }
        byte[] bytecode = ctClass.toBytecode();
        ClassDefinition definition = new ClassDefinition(clazz, bytecode);
        redefineClasses(definition);
    }

    public static void redefineMethod(Class<?> clazz, String methodName, String code, Class<?>... parameterTypes) throws Exception {
        ClassLoader targetClassLoader = clazz.getClassLoader();
        ClassPool classPool = ClassPool.getDefault();
        classPool.appendSystemPath();
        classPool.insertClassPath(new LoaderClassPath(targetClassLoader));
        CtClass ctClass = classPool.get(clazz.getName());
        ctClass.stopPruning(true);
        if (ctClass.isFrozen()) {
            ctClass.defrost();
        }
        CtMethod method = ctClass.getDeclaredMethod(methodName, toCtClasses(classPool, parameterTypes));
        method.setBody(code);

        byte[] bytecode = ctClass.toBytecode();
        ClassDefinition definition = new ClassDefinition(clazz, bytecode);
        redefineClasses(definition);
    }

    private static CtClass[] toCtClasses(ClassPool classPool, Class<?>... classes) throws NotFoundException {
        CtClass[] ctClasses = new CtClass[classes.length];
        for (int i = 0; i < classes.length; i++) {
            ctClasses[i] = classPool.get(classes[i].getName());
        }
        return ctClasses;
    }

    public static void injectMethod(Class<?> clazz, String methodName, String code, int line) throws Exception {
        ClassLoader targetClassLoader = clazz.getClassLoader();
        ClassPool classPool = new ClassPool();
        classPool.appendSystemPath();
        classPool.insertClassPath(new LoaderClassPath(targetClassLoader));
        CtClass ctClass = classPool.get(clazz.getName());
        ctClass.stopPruning(true);
        if (ctClass.isFrozen()) {
            ctClass.defrost();
        }
        CtMethod method = ctClass.getDeclaredMethod(methodName);

        method.insertAt(line, code);
        byte[] bytecode = ctClass.toBytecode();
        ClassDefinition definition = new ClassDefinition(clazz, bytecode);
        redefineClasses(definition);
    }

    public static void redefineClasses(ClassDefinition... definitions) throws Exception {
        Agent.instrumentation.redefineClasses(definitions);
    }

    public enum Location {
        BEFORE,
        AFTER,
    }
}
