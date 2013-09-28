package pl.kikko.reflect.annotation;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

public class AnnotationUtils {

    public static boolean annotated(Class<? extends Object> inputTypeClass, Class<? extends Annotation> annotationTypeClass) {
        return inputTypeClass.getAnnotation(annotationTypeClass) != null;
    }

    public static boolean annotatedInAnnotationChain(Class<? extends Object> inputTypeClass, Class<? extends Annotation> annotationTypeClass) {
        Map<Class, Annotation> annotationMap = null;

        if (annotated(inputTypeClass, annotationTypeClass)) {
            return true;
        }
        if (inputTypeClass.isAnnotation()) {
            annotationMap = collectAnnotations(inputTypeClass., annotationMap);
        } else {
            annotationMap = collectAnnotations(inputTypeClass, new HashMap<Class, Annotation>());
        }
        return annotationMap.containsKey(annotationTypeClass);
    }

    private static Map<Class, Annotation> collectAnnotations(Class<? extends Object> inputTypeClass, Map<Class, Annotation> annotationMap) {
        for (Annotation annotation : inputTypeClass.getAnnotations()) {
            if (!annotationMap.containsKey(inputTypeClass)) {
                annotationMap.put(annotation.annotationType(), annotation);
                return collectAnnotations(annotation.annotationType(), annotationMap);
            }
        }
        return annotationMap;
    }

    private static Map<Class, Annotation> collectAnnotations(Annotation inputAnnotation, Map<Class, Annotation> annotationMap) {
        for (Annotation annotation : inputAnnotation.annotationType().getAnnotations()) {
            if (!annotationMap.containsKey(inputAnnotation.annotationType())) {
                annotationMap.put(annotation.annotationType(), annotation);
                return collectAnnotations(annotation.annotationType(), annotationMap);
            }
        }
        return annotationMap;
    }


}
