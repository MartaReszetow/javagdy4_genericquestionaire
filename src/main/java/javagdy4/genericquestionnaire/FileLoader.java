package javagdy4.genericquestionnaire;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javagdy4.genericquestionnaire.Exceptions.LoaderException;
import javagdy4.genericquestionnaire.model.Loadable;
import lombok.extern.log4j.Log4j;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@Log4j
public class FileLoader<T> {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private Class<T> tclass;

    public FileLoader(Class<T> tclass) {
        this.tclass = tclass;
    }

    public Map<Long, T> load() {
        Optional<String> stringOptional = Loadable.getLoadableFilePath(tclass);     //adres pliku
        if (stringOptional.isPresent()) {
            String path = stringOptional.get();
            try {
//                objectMapper.readValue(new File(path), Map<Long, T>.class);
                Map<Long, T> map = objectMapper.readValue(new File(path), new TypeReference<Map<Long, T>>() {
                });
                log.info(map);
                return map;
            } catch (IOException ioe) {
                log.error("Error while loading json file:" + path + ":", ioe);
            }
        }
        throw new LoaderException("Unable to load json file");
    }

    public void save(Map<Long, T> map) {
        Optional<String> stringOptional = Loadable.getLoadableFilePath(tclass);     //załadowanie adresu do zapisu
        if (stringOptional.isPresent()) {
            String path = stringOptional.get();
            try {
                objectMapper.writeValue(new File(path), map);                       //zapis do pliku
            } catch (IOException e) {
                log.error("Error while saving json file: " + path + " : ", e);
            }
        }
    }

}
