package prototype;

import java.util.ArrayList;
import java.util.List;

class Resume implements Cloneable {
    private static int constructorCalls;

    private String name;
    private String position;
    private List<String> skills;

    public Resume(String name, String position, List<String> skills) {
        System.out.println("Тяжёлый конструктор: создание шаблона...");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Создание резюме прервано", e);
        }

        this.name = name;
        this.position = position;
        this.skills = new ArrayList<>(skills);
        constructorCalls++;
    }

    @Override
    public Resume clone() {
        try {
            Resume copy = (Resume) super.clone();
            copy.skills = new ArrayList<>(skills);
            return copy;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void addSkill(String skill) {
        skills.add(skill);
    }

    public List<String> getSkills() {
        return new ArrayList<>(skills);
    }

    public static int getConstructorCalls() {
        return constructorCalls;
    }

    @Override
    public String toString() {
        return "Имя: " + name
                + ", должность: " + position
                + ", навыки: " + skills;
    }
}

public class Main {
    public static void main(String[] args) {
        long start = System.nanoTime();

        Resume template = new Resume(
                "Шаблон",
                "Senior Java Developer",
                List.of("Java", "SQL", "Git")
        );

        long creationTime = System.nanoTime() - start;

        start = System.nanoTime();

        Resume junior = template.clone();
        junior.setName("Иван");
        junior.setPosition("Junior Java Developer");

        Resume middle = template.clone();
        middle.setName("Анна");
        middle.setPosition("Middle Java Developer");

        Resume senior = template.clone();
        senior.setName("Илья");
        senior.setPosition("Senior Java Developer");

        long cloningTime = System.nanoTime() - start;

        System.out.println("\nШаблон:");
        System.out.println(template);

        System.out.println("\nТри копии:");
        System.out.println(junior);
        System.out.println(middle);
        System.out.println(senior);

        System.out.println("\nВызовов тяжёлого конструктора: "
                + Resume.getConstructorCalls());

        System.out.printf("Создание шаблона: %.3f мс%n",
                creationTime / 1_000_000.0);
        System.out.printf("Создание трёх копий: %.3f мс%n",
                cloningTime / 1_000_000.0);

        // Отдельная проверка независимости списка навыков.
        junior.addSkill("JUnit");

        System.out.println("\nПосле добавления JUnit только Junior:");
        System.out.println("Junior: " + junior.getSkills());
        System.out.println("Шаблон: " + template.getSkills());
        System.out.println("Middle: " + middle.getSkills());
        System.out.println("Senior: " + senior.getSkills());
    }
}