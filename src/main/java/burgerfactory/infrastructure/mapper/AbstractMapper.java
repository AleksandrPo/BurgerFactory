package burgerfactory.infrastructure.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public abstract class AbstractMapper<S, D> implements Mapper<S, D> {

    private ModelMapper modelMapper;

    public AbstractMapper() {
        this.modelMapper = new ModelMapper();
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    protected ModelMapper getMapper() {
        return this.modelMapper;
    }
}
