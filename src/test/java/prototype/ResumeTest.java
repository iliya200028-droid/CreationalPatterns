package prototype;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ResumeTest {

    @Test
    void cloningDoesNotCallConstructor() {
        int callsBefore = Resume.getConstructorCalls();

        Resume template = new Resume(
                "Шаблон",
                "Senior Java Developer",
                List.of("Java", "SQL", "Git")
        );

        Resume junior = template.clone();
        Resume middle = template.clone();
        Resume senior = template.clone();

        assertEquals(callsBefore + 1, Resume.getConstructorCalls());

        assertNotSame(template, junior);
        assertNotSame(template, middle);
        assertNotSame(template, senior);
        assertNotSame(junior, middle);
        assertNotSame(junior, senior);
        assertNotSame(middle, senior);

        assertEquals(template.getSkills(), junior.getSkills());
        assertEquals(template.getSkills(), middle.getSkills());
        assertEquals(template.getSkills(), senior.getSkills());
    }

    @Test
    void clonedSkillsAreIndependent() {
        Resume template = new Resume(
                "Шаблон",
                "Senior Java Developer",
                List.of("Java", "SQL")
        );

        Resume first = template.clone();
        Resume second = template.clone();

        first.addSkill("JUnit");

        assertEquals(List.of("Java", "SQL", "JUnit"), first.getSkills());
        assertEquals(List.of("Java", "SQL"), template.getSkills());
        assertEquals(List.of("Java", "SQL"), second.getSkills());

        template.addSkill("Git");

        assertEquals(List.of("Java", "SQL", "Git"), template.getSkills());
        assertEquals(List.of("Java", "SQL", "JUnit"), first.getSkills());
        assertEquals(List.of("Java", "SQL"), second.getSkills());
    }
}