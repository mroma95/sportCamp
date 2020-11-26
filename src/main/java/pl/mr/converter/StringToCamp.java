package pl.mr.converter;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.mr.model.Camp;
import pl.mr.repository.CampRepository;

@Component
public class StringToCamp implements Converter<String, Camp> {
    @Autowired
    private CampRepository campRepository;
    @Override
    public Camp convert(String s) {
        Long id= Long.valueOf(s);
        return campRepository.findById(id).orElseThrow();
    }

    @Override
    public JavaType getInputType(TypeFactory typeFactory) {
        return null;
    }

    @Override
    public JavaType getOutputType(TypeFactory typeFactory) {
        return null;
    }
}
