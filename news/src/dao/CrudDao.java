package dao;

public interface CrudDao<M, I> {
    void save(M model);
    M find(I id);
    void delete(I id);
    void update(M model);
}
