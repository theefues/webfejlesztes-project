package inf.unideb.hu.dao;

import inf.unideb.hu.dao.entity.FilmEntity;
import inf.unideb.hu.dao.entity.InventoryEntity;
import inf.unideb.hu.dao.entity.StoreEntity;
import inf.unideb.hu.exceptions.InventoryAlreadyExistsException;
import inf.unideb.hu.exceptions.UnknownFilmException;
import inf.unideb.hu.exceptions.UnknownInventoryException;
import inf.unideb.hu.exceptions.UnknownStoreException;
import inf.unideb.hu.model.Inventory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
@RequiredArgsConstructor
public class InventoryDaoImpl implements InventoryDao {

    private final InventoryRepository InventoryRepository;
    private final StoreRepository StoreRepository;
    private final FilmRepository FilmRepository;

    @Override
    public void createInventory(Inventory inventory) throws InventoryAlreadyExistsException, UnknownStoreException, UnknownFilmException {
        InventoryEntity InventoryEntity = null;
        try {
             InventoryEntity = queryInventory(inventory.getId());

        }
        catch (UnknownInventoryException e){
            log.error("Exception: {} handled with message: "+e.getMessage(),e.getClass());
            InventoryEntity = InventoryEntity.builder()
                    .id(inventory.getId())
                    .filmId(queryFilm(inventory.getFilmId()))
                    .storeId(queryStore(inventory.getStoreId()))
                    .lastUpdate(inventory.getLastUpdate())
                    .build();
            InventoryRepository.save(InventoryEntity);
            log.trace("Recorded new Inventory: {}",InventoryEntity);
            return;
        }
        InventoryAlreadyExistsException InventoryException = new InventoryAlreadyExistsException("Inventory with id: "+InventoryEntity.getId()+" already exists.");
        log.error("Exception: {} thrown with message: "+InventoryException.getMessage(),InventoryException.getClass());
        throw InventoryException;

    }

    @Override
    public Collection<Inventory> readAll() {
        log.trace("Read all Inventories");
        return StreamSupport.stream(InventoryRepository.findAll().spliterator(),false)
                .map(entity -> new Inventory(
                        entity.getId(),
                        entity.getFilmId().getId(),
                        entity.getStoreId().getId(),
                        entity.getLastUpdate()
                )).collect(Collectors.toList());
    }

    @Override
    public Inventory readById(int id) throws UnknownInventoryException {

        InventoryEntity InventoryEntity = queryInventory(id);
        log.trace("Inventory found by id: {} Inventory: {}", id,InventoryEntity);
        return  new Inventory(InventoryEntity.getId(),
                InventoryEntity.getFilmId().getId(),
                InventoryEntity.getStoreId().getId(),
                InventoryEntity.getLastUpdate());

    }




    @Override
    public void deleteById(int id) throws UnknownInventoryException {

        InventoryEntity InventoryEntity = queryInventory(id);
        InventoryRepository.deleteById(id);
        log.trace("Inventory deleted: {}", InventoryEntity);

    }
    protected StoreEntity queryStore(int StoreId) throws UnknownStoreException {
        Optional<StoreEntity> StoreEntity = StoreRepository.findById(StoreId);

        if (!StoreEntity.isPresent()) {
            UnknownStoreException StoreException = new UnknownStoreException("Store with id: " + Integer.toString(StoreId)+" not exists");
            log.error("Exception: {} thrown with message: "+StoreException.getMessage(),StoreException.getClass());
            throw StoreException;
        }
        return StoreEntity.get();
    }

    @Override
    public void update( Inventory Inventory) throws UnknownInventoryException, UnknownFilmException, UnknownStoreException {

        InventoryEntity InventoryEntity = queryInventory(Inventory.getId());
        InventoryEntity.setId(Inventory.getId());
                InventoryEntity.setFilmId(queryFilm(Inventory.getFilmId()));
                InventoryEntity.setStoreId(queryStore(Inventory.getStoreId()));
                InventoryEntity.setLastUpdate(Inventory.getLastUpdate());
        InventoryRepository.save(InventoryEntity);
        log.trace("Inventory updated: {}", InventoryEntity);
    }

    private InventoryEntity queryInventory(int id) throws UnknownInventoryException {
        Optional<InventoryEntity> InventoryEntity = InventoryRepository.findById(id);

        if (!InventoryEntity.isPresent()) {
            UnknownInventoryException InventoryException = new UnknownInventoryException("Inventory with id: " + Integer.toString(id)+" not exists.");
            log.error("Exception: {} thrown with message: "+InventoryException.getMessage(),InventoryException.getClass());
            throw InventoryException;
        }
        return InventoryEntity.get();
    }

    private FilmEntity queryFilm(int id) throws UnknownFilmException {
        Optional<FilmEntity> FilmEntity = FilmRepository.findById(id);

        if (!FilmEntity.isPresent()) {
            UnknownFilmException FilmException = new UnknownFilmException("Inventory with id: " + Integer.toString(id)+" not exists.");
            log.error("Exception: {} thrown with message: "+FilmException.getMessage(),FilmException.getClass());
            throw FilmException;
        }
        return FilmEntity.get();
    }
}
