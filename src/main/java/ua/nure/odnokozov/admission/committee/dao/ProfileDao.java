package ua.nure.odnokozov.admission.committee.dao;

import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.odnokozov.admission.committee.entity.Profile;
import ua.nure.odnokozov.admission.committee.entity.builder.ProfileBuilder;
import ua.nure.odnokozov.admission.committee.enums.BlockStatus;

public class ProfileDao extends AbstractDao<Profile> {
    
    private static final Logger LOG = Logger.getLogger(ProfileDao.class);
    
    private static final String SQL_SELECT_ENTRANT_BY_USER_ID = "SELECT * FROM entrants, facult WHERE user_id=? LIMIT 1";
    private static final String SQL_SELECT_ALL_ENTRATNS = "SELECT * FROM entrants";
    private static final String SQL_INSERT_INTO_ENTRANTS = "INSERT INTO entrants(user_id, block_status, first_name, last_name, city, region, shool) VALUES(?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE_ENTRANT = "UPDATE entrants(block_status=?, first_name=?, last_name=?, city=?, region=?, shool=?) WHERE user_id = ?";
    private static final String SQL_DELETE_PROFILE = "DELETE FROM entrants WHERE user_id = ?";

    //private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_BLOCK_STATUS = "block_status";
    private static final String COLUMN_FIRST_NAME = "first_name";
    private static final String COLUMN_LAST_NAME = "last_name";
    private static final String COLUMN_CITY = "city";
    private static final String COLUMN_REGION = "region";
    private static final String COLUMN_SCHOOL = "school";
    
    @Override
    public Profile getById(long userId) {
        return getById(SQL_SELECT_ENTRANT_BY_USER_ID, ps -> ps.setLong(1, userId), getMapper());
    }
    
    private EntityMapper<Profile> getMapper(){
        return resultSet -> ProfileBuilder.getInstance()
                .id(resultSet.getLong(COLUMN_BLOCK_STATUS))
                .blockStatus(BlockStatus.valueOf(resultSet.getString(COLUMN_BLOCK_STATUS)))
                .firstName(resultSet.getString(COLUMN_FIRST_NAME))
                .lastName(resultSet.getString(COLUMN_LAST_NAME))
                .city(resultSet.getString(COLUMN_CITY))
                .region(resultSet.getString(COLUMN_REGION))
                .school(resultSet.getString(COLUMN_SCHOOL))
                .build();      
    }

    @Override
    public List<Profile> getAll() {
        return getAll(SQL_SELECT_ALL_ENTRATNS, getMapper());
    }

    @Override
    public Profile create(Profile entity) {
        LOG.debug("Create entrants: " + entity);
        return createUpdate(SQL_INSERT_INTO_ENTRANTS, ps-> {
            int count = 1;
            ps.setLong(count++, entity.getId());
            ps.setString(count++, entity.getBlockStatus().getName());
            ps.setString(count++, entity.getFirstName());
            ps.setString(count++, entity.getLastName());
            ps.setString(count++, entity.getCity());
            ps.setString(count++, entity.getRegion());
            ps.setString(count, entity.getSchool());            
        }, getMapper());
    }

    @Override
    public Profile update(Profile entity) {
        LOG.debug("Update entrants: " + entity);
        return createUpdate(SQL_UPDATE_ENTRANT, ps-> {
            int count = 1;
            ps.setString(count++, entity.getBlockStatus().getName());
            ps.setString(count++, entity.getFirstName());
            ps.setString(count++, entity.getLastName());
            ps.setString(count++, entity.getCity());
            ps.setString(count++, entity.getRegion());
            ps.setString(count, entity.getSchool());
        },getMapper());
    }

    @Override
    public boolean delete(long id) {
        LOG.debug("Delete profile where id = " + id);
        return delete(SQL_DELETE_PROFILE, ps -> {
            ps.setLong(1, id);
        });
    }
}
