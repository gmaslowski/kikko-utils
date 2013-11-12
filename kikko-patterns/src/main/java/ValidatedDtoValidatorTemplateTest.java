import com.aida.dam.AbstractUnitTest;
import com.aida.dam.api.dto.Mandatory;
import com.aida.dam.api.dto.ReadOnly;
import com.aida.dam.api.dto.ValidatedDto;
import com.aida.dam.domain.common.validation.exception.DtoValidationException;
import org.junit.Test;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class ValidatedDtoValidatorTemplateTest extends AbstractUnitTest {

    public static final String VALUE = "value";

    private TestedDtoValidator validator = new TestedDtoValidator();

    private TestedDto dto;

    @Test
    public void shouldThrowDtoValidationExceptionWhenMandatoryFieldNotSet() {
        // given
        dto = new TestedDto();

        // then
        expectedException.expect(DtoValidationException.class);
        expectedException.expectMessage("mandatory");

        // when
        validateWithCustomAnn(dto);

    }

    @Test
    public void shouldThrowDtoValidationExceptionWhenReadOnlyFieldSet() {
        // given
        dto = new TestedDto();
        dto.readOnly = VALUE;
        dto.mandatory = VALUE;

        // then
        expectedException.expect(DtoValidationException.class);
        expectedException.expectMessage("read only");

        // when
        validateWithCustomAnn(dto);

    }

    @Test
    public void shouldNotThrowAnyExceptionWhenDtoFilledProperly() {

        // given
        dto = new TestedDto();
        dto.mandatory = VALUE;
        dto.readOnly = null;

        // when
        validateWithCustomAnn(dto);

        // then expect nothing
    }

    private void validateWithCustomAnn(@chuj TestedDto dto) {
        validator.validate(dto, null);
    }

    class TestedDtoValidator extends ValidatedDtoValidatorTemplate<TestedDto> {

        @Override
        protected void validateTypeSpecific(TestedDto ignored) {}
    }

    class TestedDto implements ValidatedDto {

        @Mandatory
        private String mandatory;

        @ReadOnly
        private String readOnly;

        public String getMandatory() {
            return mandatory;
        }

        public String getReadOnly() {
            return readOnly;
        }
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface chuj {}
}
