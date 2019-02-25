package com.github.yukihane.java.classroom;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.validation.ConstraintTarget;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.metadata.ConstraintDescriptor;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClassRoomTest {

    private static final Logger LOG = LoggerFactory.getLogger(ClassRoomTest.class);

    private static Validator validator;

    @BeforeClass
    public static void setUp() {
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testChildElement() {
        final List<Student> students = Arrays.asList(new Student("suzuki"), new Student(null));
        final ClassRoom classRoom = new ClassRoom("3-2", students);
        final Set<ConstraintViolation<ClassRoom>> res = validator.validate(classRoom);

        res.stream().forEach(e -> {
            final ConstraintDescriptor<?> desc = e.getConstraintDescriptor();
            log(desc);
            LOG.info("ExecutableParameters: {}", e.getExecutableParameters());
            LOG.info("ExecutableReturnValue: {}", e.getExecutableReturnValue());
            LOG.info("InvalidValue: {}", e.getInvalidValue());
            LOG.info("LeafBean: {}", e.getLeafBean());
            LOG.info("PropertyPath: {}", e.getPropertyPath());
            LOG.info("RootBean: {}", e.getRootBean());
            LOG.info("RootBeanClass: {}", e.getRootBeanClass());
        });
    }

    public static void log(final ConstraintDescriptor<?> desc) {
        LOG.info(desc.getAnnotation().toString());
        log(desc.getAttributes());
        desc.getComposingConstraints().stream().forEach(ClassRoomTest::log);
        LOG.info("Desc Groups: {}", desc.getGroups());
        LOG.info("Desc Message Template: {}", desc.getMessageTemplate());
        LOG.info("Desc Payload: {}", desc.getPayload());
        log(desc.getValidationAppliesTo());
        LOG.info("Desc ReportAsSingleViolation: {}", desc.isReportAsSingleViolation());
    }

    private static void log(final ConstraintTarget target) {
        LOG.info("ConstraintTarget: {}", target);
    }

    private static void log(final Map<String, Object> attributes) {
        LOG.info("{}", attributes);
    }

}
