package utils;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.logging.Logger;

public class RetryListener implements IAnnotationTransformer {
    Logger logger = Logger.getLogger(RetryListener.class.getName());

    @Override
    public void transform(ITestAnnotation iTestAnnotation, Class aClass, Constructor constructor, Method method) {
        IRetryAnalyzer retryAnalyzer = iTestAnnotation.getRetryAnalyzer();
        if (retryAnalyzer == null) {
            iTestAnnotation.setRetryAnalyzer(Retry.class);
        }
    }
}
