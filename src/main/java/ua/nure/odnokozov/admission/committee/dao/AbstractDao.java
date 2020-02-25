package ua.nure.odnokozov.admission.committee.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.odnokozov.admission.committee.factory.ConnectionFactory;

public abstract class AbstractDao<T> implements EntityDao<T> {

    private static final Logger LOG = Logger.getLogger(AbstractDao.class);
    private final ConnectionFactory connectionFactory = ConnectionFactory.getInstance();

    public T getById(String query, StatementMapper<T> statementMapper, EntityMapper<T> entityMapper) {
        T result = null;
        try (Connection connection = connectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            statementMapper.map(preparedStatement);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    result = entityMapper.map(resultSet);
                }
            }
        } catch (SQLException e) {
            LOG.error("Exception while getting entity by id", e);
        }
        return result;
    }

    public List<T> getAll(String query, EntityMapper<T> entityMapper) {
        List<T> result = new ArrayList<>();
        try (Connection connection = connectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                T entity = entityMapper.map(resultSet);
                result.add(entity);
            }
        } catch (SQLException e) {
            LOG.error("Exception while getting all entities", e);
        }
        return result;
    }

    public T createUpdate(String query, StatementMapper<T> statementMapper, EntityMapper<T> entityMapper) {
        T result = null;
        try (Connection connection = connectionFactory.getConnection()) {
            LOG.debug("connection is null = " + (connection == null));
            try (CallableStatement preparedStatement = connection.prepareCall(query)) {
                statementMapper.map(preparedStatement);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    result = entityMapper.map(resultSet);
                }
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                LOG.error("Could not create/update entity!", e);
            }
        } catch (SQLException ex) {
            LOG.error("Excepption while getting connection!", ex);
        }
        return result;
    }

    public boolean delete(String query, StatementMapper<T> statementMapper) {
        try (Connection connection = connectionFactory.getConnection()) {
            LOG.debug("connection is null = " + (connection == null));
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                statementMapper.map(preparedStatement);
                int result = preparedStatement.executeUpdate();
                connection.commit();
                return result == 1;
            } catch (SQLException e) {
                connection.rollback();
                LOG.error("Could not create/update entity!", e);
            }
        } catch (SQLException ex) {
            LOG.error("Excepption while getting connection!", ex);
        }
        return false;
    }
}