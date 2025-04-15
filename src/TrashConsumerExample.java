import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

// Иерархия классов мусора
class Trash {}
class PaperTrash extends Trash {
    private String type;

    public PaperTrash(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Бумажный мусор: " + type;
    }
}
class NewspaperTrash extends PaperTrash {
    public NewspaperTrash() {
        super("газета");
    }
}

public class TrashConsumerExample {

    // Метод для копирования бумажного мусора
    // Метод принимает Consumer с нижней границей wildcard
    public static void copyPapers(
            List<? extends PaperTrash> src,  // Источник (читаем)
            List<? super PaperTrash> dest    // Приёмник (пишем)
    ) {
        for (PaperTrash p : src) {
            dest.add(p);
            System.out.println("Скопировано: " + p); // Логирование
        }
    }

    // Consumer для обработки мусора
    static class TrashProcessor implements Consumer<Trash> {
        @Override
        public void accept(Trash t) {
            System.out.println("Обрабатываем: " + t);
        }
    }

    public static void main(String[] args) {
        // Создаем источники данных
        List<NewspaperTrash> newspapers = new ArrayList<>();
        newspapers.add(new NewspaperTrash());
        newspapers.add(new NewspaperTrash());

        List<PaperTrash> mixedPaper = new ArrayList<>();
        mixedPaper.add(new PaperTrash("офисная бумага"));
        mixedPaper.add(new NewspaperTrash());

        // Создаем приемники
        List<Trash> generalTrashBin = new ArrayList<>();
        List<PaperTrash> paperTrashBin = new ArrayList<>();
        List<Object> anyObjectsBin = new ArrayList<>();

        // 1. Копируем газеты в общий мусорный бак
        System.out.println("\nКопируем newspapers в generalTrashBin:");
        copyPapers(newspapers, generalTrashBin);

        // 2. Копируем смешанный мусор в спец.контейнер для бумаги
        System.out.println("\nКопируем mixedPaper в paperTrashBin:");
        copyPapers(mixedPaper, paperTrashBin);

        // 3. Копируем газеты в контейнер для любых объектов
        System.out.println("\nКопируем newspapers в anyObjectsBin:");
        copyPapers(newspapers, anyObjectsBin);

        // Создаем и используем Consumer
        Consumer<Trash> processor = new TrashProcessor();

        System.out.println("\nОбрабатываем generalTrashBin:");
        generalTrashBin.forEach(processor);

        System.out.println("\nОбрабатываем paperTrashBin:");
        paperTrashBin.forEach(processor);
    }
}