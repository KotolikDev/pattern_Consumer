# Паттерн **Consumer** (Потребитель) в Java

**Consumer** — поведенческий паттерн, инкапсулирующий операцию над переданным объектом.  
В Java реализуется через функциональный интерфейс `java.util.function.Consumer<T>`.

---

## 🔧 Основная концепция

Интерфейс `Consumer<T>`:

- Принимает входной параметр типа `T`;
- Выполняет над ним действие;
- Не возвращает результат (`void`).

---

## ✅ Преимущества паттерна Consumer

1. **Инкапсуляция операций** — логика обработки отделена от данных;
2. **Гибкость** — легко заменять обработчики;
3. **Функциональный стиль** — совместим с лямбда-выражениями;
4. **Композиция** — позволяет создавать цепочки потребителей.

---

## ⚙️ Как работает код

### 🧱 Иерархия классов

```text
Object
  └── Trash
        └── PaperTrash
              └── NewspaperTrash
```

- `Trash` — базовый класс мусора;
- `PaperTrash` — бумажный мусор (наследуется от `Trash`);
- `NewspaperTrash` — газеты (наследуется от `PaperTrash`).

### 📄 Метод `copyPapers`

**Параметры:**

- `source`: `List<? extends PaperTrash>` — список-источник;
- `destination`: `List<? super PaperTrash>` — список-приемник.

Позволяет копировать любой бумажный мусор (и его подтипы) в любой контейнер, который принимает `PaperTrash`.

### ♻️ Интерфейс `Consumer` (пример: `TrashProcessor`)

- Реализует `Consumer<Trash>`;
- Определяет метод `accept()` для обработки объектов;
- Работает с любыми объектами `Trash` и его подклассами.

---

## 💡 Примеры использования

1. Копирование `NewspaperTrash` в контейнер типа `List<Trash>`;
2. Копирование смешанного `PaperTrash` в `List<PaperTrash>`;
3. Копирование в контейнер типа `List<Object>`;
4. Обработка мусора через реализацию `Consumer`.

---

## 📌 Демонстрируемые концепции

- Гибкость `wildcards`: `? extends` и `? super`;
- Использование `Consumer` для обработки элементов;
- Безопасность типов с `generics`;
- Работа с объектами разного уровня иерархии.

---

## 🎯 Вывод: когда выбирать Consumer?
- Нужно применить действие к элементу без возврата результата (forEach, peek).
- Требуется гибкая настройка объектов (Builder, конфигурация).
- Нужно комбинировать операции последовательно (andThen).
- Работаете с Optional (ifPresent).
- Используете колбэки или обработчики событий

![Wildcard](./wildcard.png)
