package burgerfactory.infrastructure.mapper;

public interface Mapper<S, D> {
    D map(S from);
}
