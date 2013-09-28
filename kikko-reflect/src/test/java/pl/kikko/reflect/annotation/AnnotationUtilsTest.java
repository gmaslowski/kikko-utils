package pl.kikko.reflect.annotation;

import org.junit.Test;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static org.fest.assertions.Assertions.assertThat;
import static pl.kikko.reflect.annotation.AnnotationUtils.annotated;
import static pl.kikko.reflect.annotation.AnnotationUtils.annotatedInAnnotationChain;

public class AnnotationUtilsTest {

    @Test
    public void shouldConfirmObjectAnnotated() {
        // then
        assertThat(annotated(ObjectWithCustomAnnotation.class, CustomAnnotation.class)).isTrue();
    }

    @Test
    public void shouldDenyObjectAnnotated() {
        // then
        assertThat(annotated(ObjectWithCustomAnnotation.class, NotUsedAnnotation.class)).isFalse();
    }

    @Test
    public void shouldConfirmAnnotationAnnotated() {
        // then
        assertThat(annotated(CustomAnnotation.class, MetaAnnotation.class)).isTrue();
        assertThat(annotated(MetaAnnotation.class, MetaMetaAnnotation.class)).isTrue();
    }

    @Test
    public void shouldDenyAnnotationAnnotated() {
        // then
        assertThat(annotated(CustomAnnotation.class, NotUsedAnnotation.class)).isFalse();
        assertThat(annotated(MetaAnnotation.class, NotUsedAnnotation.class)).isFalse();
        assertThat(annotated(MetaMetaAnnotation.class, NotUsedAnnotation.class)).isFalse();
    }

    @Test
    public void shouldConfirmObjectAnnotatedInChain() {
        // then
        assertThat(annotatedInAnnotationChain(ObjectWithCustomAnnotation.class, CustomAnnotation.class)).isTrue();
        assertThat(annotatedInAnnotationChain(ObjectWithCustomAnnotation.class, MetaAnnotation.class)).isTrue();
        assertThat(annotatedInAnnotationChain(ObjectWithCustomAnnotation.class, MetaMetaAnnotation.class)).isTrue();
    }

    @Test
    public void shouldDenyObjectAnnotatedInChain() {
        // then
        assertThat(annotatedInAnnotationChain(ObjectWithCustomAnnotation.class, NotUsedAnnotation.class)).isFalse();
    }

    @Test
    public void shouldConfirmAnnotationAnnotatedInChain() {
        // then
        assertThat(annotatedInAnnotationChain(CustomAnnotation.class, MetaAnnotation.class)).isTrue();
        assertThat(annotatedInAnnotationChain(MetaAnnotation.class, MetaMetaAnnotation.class)).isTrue();
        assertThat(annotatedInAnnotationChain(CustomAnnotation.class, MetaMetaAnnotation.class)).isTrue();
    }

    @Test
    public void shouldDenyAnnotationAnnotatedInChain() {
        // then
        assertThat(annotatedInAnnotationChain(CustomAnnotation.class, NotUsedAnnotation.class)).isFalse();
        assertThat(annotatedInAnnotationChain(MetaAnnotation.class, NotUsedAnnotation.class)).isFalse();
        assertThat(annotatedInAnnotationChain(MetaMetaAnnotation.class, NotUsedAnnotation.class)).isFalse();
    }

    @Test
    public void shouldConfirmStandardAnnotationAnnotatedInChain() {
        // then
        assertThat(annotatedInAnnotationChain(Documented.class, Retention.class)).isTrue();
        assertThat(annotatedInAnnotationChain(Retention.class, Documented.class)).isTrue();
        assertThat(annotatedInAnnotationChain(Inherited.class, Documented.class)).isTrue();
        assertThat(annotatedInAnnotationChain(Documented.class, Documented.class)).isTrue();
    }

    @Target(TYPE)
    @Retention(RUNTIME)
    @MetaAnnotation
    public static @interface CustomAnnotation {
    }

    @Target(TYPE)
    @Retention(RUNTIME)
    @MetaMetaAnnotation
    public static @interface MetaAnnotation {
    }

    @Target(TYPE)
    @Retention(RUNTIME)
    public static @interface MetaMetaAnnotation {
    }


    @Target(TYPE)
    @Retention(RUNTIME)
    public static @interface NotUsedAnnotation {
    }

    @CustomAnnotation
    public static class ObjectWithCustomAnnotation {

    }

}
