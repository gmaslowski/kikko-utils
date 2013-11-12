
public abstract class ValidatedDtoValidatorTemplate<VALIDATED extends ValidatedDto> implements Validator {

    private Class<VALIDATED> validatedDtoClass = getParameterizedTypeClass(this);

    private List<Field> readOnlyFields = newArrayList();
    private List<Field> mandatoryFields = newArrayList();

    public ValidatedDtoValidatorTemplate() {

        doWithFields(validatedDtoClass, new ReflectionUtils.FieldCallback() {

            @Override
            public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
                if (field.isAnnotationPresent(Mandatory.class)) {
                    mandatoryFields.add(field);
                } else if (field.isAnnotationPresent(ReadOnly.class)) {
                    readOnlyFields.add(field);
                }
            }
        });
    }

    public void validateCreate(VALIDATED validatedDto) {
        try {
            validateMandatoryFields(validatedDto);
            validateReadOnlyFields(validatedDto);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            propagate(e);
        }

        validateTypeSpecific(validatedDto);
    }

    private void validateMandatoryFields(VALIDATED validatedDto) throws IllegalAccessException, NoSuchMethodException,
            InvocationTargetException {
        for (Field mandatoryField : mandatoryFields) {
            Method getter = validatedDtoClass.getMethod(prepareGetter(mandatoryField));
            if (getter.invoke(validatedDto) == null) {
                throw new DtoValidationException(String.format("%s field is mandatory.", mandatoryField.getName()));
            }
        }
    }

    private void validateReadOnlyFields(VALIDATED validatedDto) throws IllegalAccessException, NoSuchMethodException,
            InvocationTargetException {
        for (Field readOnlyField : readOnlyFields) {
            Method getter = validatedDtoClass.getMethod(prepareGetter(readOnlyField));
            if (getter.invoke(validatedDto) != null) {
                throw new DtoValidationException(String.format("%s field is read only.", readOnlyField.getName()));
            }
        }
    }

    private String prepareGetter(Field field) {
        return "get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
    }

    @Override
    public boolean supports(Class<?> supportedClass) {
        return supportedClass.equals(validatedDtoClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        for (Annotation ann : o.getClass().getAnnotations())
            System.out.println(ann);
        validateCreate((VALIDATED) o);
    }

    protected abstract void validateTypeSpecific(VALIDATED validatedDto);
}
