package ua.nure.odnokozov.admission.committee.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import ua.nure.odnokozov.admission.committee.entity.Faculty;
import ua.nure.odnokozov.admission.committee.entity.Language;

import ua.nure.odnokozov.admission.committee.factory.ConnectionFactory;

public class FacultyDao extends AbstractDao<Faculty> {

    private static final Logger LOG = Logger.getLogger(FacultyDao.class);

    private static final String SQL_INSERT_FACULTY = "INSERT INTO faculties(total_seats, budget_seats) VALUES (?, ?)";
    private static final String SQL_SELECT_FACULTY_BY_ID = "CALL select_faculty(?)";
    private static final String SQL_SELECT_ALL_FACULTIES = "CALL select_all_faculties()";

    private static final String SQL_INSERT_INTO_FACULTIES_TITLES = "INSERT INTO "
            + "faculties_titles(faculty_id, language_id, title) VALUES(?, ?, ?)";

    private static final String COLUMN_FACULTY_ID = "faculty_id";
    private static final String COLUMN_TOTAL_SEATS = "total_seats";
    private static final String COLUMN_BUDGET_SEATS = "budget_seats";
    private static final String COLUMN_LANGUAGE_ID = "language_id";
    private static final String COLUMN_LANGUAGE_LABLE = "language_label";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_DESCRIPTION = "description";

    private static final String SQL_INSERT_INTO_FACULTIES_DESCRIPTIONS = "INSERT INTO "
            + "faculties_descriptions(faculty_id, language_id, description) VALUES(?, ?, ?)";

    private ConnectionFactory connectionFactory = ConnectionFactory.getInstance();

    @Override
    public Faculty getById(long id) {
        return getById(SQL_SELECT_FACULTY_BY_ID, cs -> cs.setLong(1, id), getMapper());
    }

    private EntityMapper<Faculty> getMapper() {
        return resultSet -> {
            Faculty result = new Faculty();
            Map<Language, String> titles = new HashMap<>();
            Map<Language, String> descriptions = new HashMap<>();
            result.setId(resultSet.getLong(COLUMN_FACULTY_ID));
            result.setTotalSeats(resultSet.getInt(COLUMN_TOTAL_SEATS));
            result.setBudgetSeats(resultSet.getInt(COLUMN_BUDGET_SEATS));
            do {
                long languageId = resultSet.getLong(COLUMN_LANGUAGE_ID);
                String languageLabel = resultSet.getString(COLUMN_LANGUAGE_LABLE);
                titles.put(new Language(languageId, languageLabel), resultSet.getString(COLUMN_TITLE));
                descriptions.put(new Language(languageId, languageLabel), resultSet.getString(COLUMN_DESCRIPTION));
            } while (resultSet.next());
            result.setTitles(titles);
            result.setDescriptions(descriptions);
            return result;
        };
    }

    @Override
    public List<Faculty> getAll() {
        return getAll(SQL_SELECT_ALL_FACULTIES, getMapper());
    }

    @Override
    public Faculty create(Faculty entity) {
        Faculty result = null;
        Map<Language, String> newTitles = new HashMap<>();
        Map<Language, String> newDescriptions = new HashMap<>();
        try (Connection connection = connectionFactory.getConnection()) {
            LOG.debug("connection is null = " + (connection == null));
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_FACULTY,
                    Statement.RETURN_GENERATED_KEYS)) {
                int count = 1;
                preparedStatement.setInt(count++, entity.getTotalSeats());
                preparedStatement.setInt(count, entity.getBudgetSeats());
                entity.setId(preparedStatement.executeUpdate());
                try (PreparedStatement prep = connection.prepareStatement(SQL_INSERT_INTO_FACULTIES_TITLES)) {
                    Map<Language, String> titles = entity.getTitles();
                    Map<Language, String> descriptions = entity.getDescriptions();
                    for (Map.Entry<Language, String> entry : titles.entrySet()) {
                        count = 1;
                        long languageId = entry.getKey().getId();
                        prep.setLong(count++, entity.getId());
                        prep.setLong(count++, languageId);
                        prep.setString(count, entry.getValue());
                        prep.addBatch();
                    }
                    prep.executeBatch();
                }
                try (PreparedStatement prep = connection.prepareStatement(SQL_INSERT_INTO_FACULTIES_DESCRIPTIONS)) {
                    Map<Language, String> descriptions = entity.getDescriptions();
                    for (Map.Entry<Language, String> entry : descriptions.entrySet()) {
                        count = 1;
                        prep.setLong(count++, entity.getId());
                        prep.setLong(count++, entry.getKey().getId());
                        prep.setString(count, entry.getValue());
                        prep.addBatch();
                    }
                    prep.executeBatch();
                }
                try (CallableStatement callStatement = connection.prepareCall(SQL_SELECT_FACULTY_BY_ID)) {
                    callStatement.setLong(1, entity.getId());
                    try (ResultSet resultSet = callStatement.executeQuery()) {
                        while (resultSet.next()) {
                            result = new Faculty();
                            result.setId(resultSet.getLong(COLUMN_FACULTY_ID));
                            result.setTotalSeats(resultSet.getInt(COLUMN_TOTAL_SEATS));
                            result.setBudgetSeats(resultSet.getInt(COLUMN_BUDGET_SEATS));
                            long languageId = resultSet.getLong(COLUMN_LANGUAGE_ID);
                            String languageLabel = resultSet.getString(COLUMN_LANGUAGE_LABLE);
                            String title = resultSet.getString(COLUMN_TITLE);
                            String description = resultSet.getString(COLUMN_DESCRIPTION);
                            newTitles.put(new Language(languageId, languageLabel), title);
                            newDescriptions.put(new Language(languageId, languageLabel), description);
                        }
                    }
                }
                result.setTitles(newTitles);
                result.setDescriptions(newDescriptions);
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                LOG.error("Could not create/update entity!", e);
            }
        } catch (SQLException ex) {
            LOG.error("Connection error!", ex);
        }
        return result;
    }

    @Override
    public Faculty update(Faculty entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean delete(long id) {
        // TODO Auto-generated method stub
        return false;
    }
}
