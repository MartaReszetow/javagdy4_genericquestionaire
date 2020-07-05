package javagdy4.genericquestionnaire.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
@Log4j
public class Loadable {
    private final static Map<Class<?>, String> MAPPED_FILES = new HashMap<>();

    // bok ładowalny przy odpaleniu programu - static
    static {
        MAPPED_FILES.put(Question.class, "pytania.json");
        MAPPED_FILES.put(Questionnaire.class, "odpowiedzi.json");
    }
    public static Optional<String> getLoadableFilePath(Class<?> tclass){
        return Optional.ofNullable(MAPPED_FILES.get(tclass));
    }


}
